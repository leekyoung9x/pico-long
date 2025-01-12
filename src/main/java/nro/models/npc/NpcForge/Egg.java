/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.consts.ConstPet;
import nro.consts.ConstPlayer;
import nro.dialog.MenuDialog;
import nro.dialog.MenuRunable;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Inventory;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.Service;
import nro.services.TaskService;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class Egg extends Npc {

    public Egg(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (!canOpenNpc(player)) {
            return;
        }
        if (this.mapId == (21 + player.gender)) {
            handleMabuEgg(player);
        } else if (this.mapId == 154) {
            if (player.billEgg != null) {
                handleBillEgg(player);
            } else {
                this.npcChat(player, "o mai phac king dog check");
            }
        }
    }

    private void handleMabuEgg(Player player) {
        player.mabuEgg.sendMabuEgg();
        if (player.mabuEgg.getSecondDone() != 0) {
            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG,
                    "Khẹc khẹc khẹc khẹc...",
                    "Hủy bỏ\ntrứng",
                    "Ấp nhanh", "Cày bò mộng", "Đóng");
        } else {
            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG,
                    "Khẹc khẹc khẹc khẹc...", "Nở",
                    "Hủy bỏ\ntrứng", "Đóng");
        }
    }

    private void handleBillEgg(Player player) {
        player.billEgg.sendBillEgg();
        if (player.billEgg.getSecondDone() != 0) {
            this.createOtherMenu(player, ConstNpc.CAN_NOT_OPEN_EGG,
                    "Khẹc khẹc khẹc khẹc...",
                    "Hủy bỏ\ntrứng",
                    "Ấp nhanh", "Đóng");
        } else {
            this.createOtherMenu(player, ConstNpc.CAN_OPEN_EGG,
                    "Khẹc khẹc khẹc khẹc...", "Nở",
                    "Hủy bỏ\ntrứng", "Đóng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (this.mapId == (21 + player.gender)) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.CAN_NOT_OPEN_EGG:
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                        "Bạn có muốn bỏ trứng Meo Bư?",
                                        "Đồng ý", "Từ chối");
                                break;
                            case 1:
                                int gem = 50_000;
                                if (player.inventory.gem >= gem) {
                                    player.inventory.addGem(-gem);
                                    player.mabuEgg.timeDone = 0;
                                    player.mabuEgg.sendMabuEgg();
                                    Service.getInstance().sendMoney(player);
                                } else {
                                    Service.getInstance().sendThongBao(player, "Bạn thiếu 50k gem!");
                                }
                                break;
                            case 2:
                                this.createOtherMenu(player, ConstNpc.GO_TO_MAP_TASK,
                                        "Khi bạn tham gia làm nhiệm vụ bò mộng cấp độ khó\n Quả trứng của bạn sẽ được giảm đi 5 giờ",
                                        "Đi làm nhiệm vụ", "Từ chối");
                                break;
                            default:
                                break;
                        }
                        break;
                    case ConstNpc.CAN_OPEN_EGG:
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
                                        "Bạn có chắc chắn cho trứng nở?\n"
                                                + "Đệ tử của bạn sẽ được thay thế bằng đệ Mao Bư",
                                        "Đệ Mao Bư\nTrái Đất", "Đệ Mao Bư\nNamếc", "Đệ Mao Bư\nXayda",
                                        "Từ chối");
                                break;
                            case 1:
                                this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                        "Bạn có chắc chắn muốn hủy bỏ trứng Mao Bư?", "Đồng ý",
                                        "Từ chối");
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_EGG:
                        switch (select) {
                            case 0:
                                player.mabuEgg.openEgg(ConstPlayer.TRAI_DAT);
                                break;
                            case 1:
                                player.mabuEgg.openEgg(ConstPlayer.NAMEC);
                                break;
                            case 2:
                                player.mabuEgg.openEgg(ConstPlayer.XAYDA);
                                break;
                            default:
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_DESTROY_EGG:
                        if (select == 0) {
                            player.mabuEgg.destroyEgg();
                        }
                        break;
                    case ConstNpc.GO_TO_MAP_TASK:
                        if (select == 0) {
                            ChangeMapService.gI().changeMapNonYard(player, 47, 627, 336);
                        }
                        break;
                }
            } else if (this.mapId == 154) {
                switch (player.iDMark.getIndexMenu()) {
                    case ConstNpc.CAN_NOT_OPEN_EGG:
                        switch (select) {
                            case 0:
                                this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                        "Bạn có muốn bỏ trứng Bill Egg?",
                                        "Đồng ý", "Từ chối");
                                break;
                            case 1:
                                MenuDialog menu = new MenuDialog("Bạn có muốn ấp\n" +
                                        "trứng nhanh với giá 200k ngọc xanh không ?", new String[]{"Có", "Không"}, new MenuRunable() {
                                    @Override
                                    public void run() {
                                        switch (getIndexSelected()) {
                                            case 0:
                                                // ekko ấp nhanh cần 200k ngọc xanh
                                                if(player.inventory.gem < 200000) {
                                                    Service.getInstance().sendThongBao(player, "Bạn không đủ ngọc xanh");
                                                    return;
                                                } else {
                                                    // trừ 200k ngọc xanh
                                                    player.inventory.addGem(-200000);
                                                    player.billEgg.timeDone = 0;
                                                    player.billEgg.sendBillEgg();
                                                    // cập nhật thông tin ngọc xanh
                                                    Service.getInstance().sendMoney(player);
                                                }
                                                break;
                                            case 1:
                                                break;
                                        }
                                    }
                                });
                                menu.show(player);
                                break;
//                                Item[] items = new Item[]{
//                                    InventoryService.gI().findItemBagByTemp(player, 663),
//                                    InventoryService.gI().findItemBagByTemp(player, 664),
//                                    InventoryService.gI().findItemBagByTemp(player, 665),
//                                    InventoryService.gI().findItemBagByTemp(player, 666),
//                                    InventoryService.gI().findItemBagByTemp(player, 667)
//                                };
//
//                                boolean hasAllItems = true;
//                                for (Item item : items) {
//                                    if (item == null || item.quantity < 99) {
//                                        hasAllItems = false;
//                                        break;
//                                    }
//                                }
//
//                                if (hasAllItems) {
//                                    for (Item item : items) {
//                                        InventoryService.gI().subQuantityItemsBag(player, item, 99);
//                                    }
//                                    InventoryService.gI().sendItemBags(player);
//
//                                    player.billEgg.timeDone = 0;
//                                    player.billEgg.sendBillEgg();
//                                } else {
//                                    Service.getInstance().sendThongBao(player, "Bạn không đủ x99 5 loại thức ăn để thực hiện");
//                                }
//                                break;

                            default:
                                break;
                        }
                        break;
                    case ConstNpc.CAN_OPEN_EGG:
                        switch (select) {
                            case 0:
                                // để nở trứng yêu cầu đệ
                                //thường hoặc đệ ma bư mập đạt 100 tỷ sức mạnh
                                // chưa có pet thì hiện thông báo
                                if(player.pet == null) {
                                    Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử thường hoặc đệ Ma Bư mập đạt 100 tỷ sức mạnh");
                                    return;
                                }
                                else if(player.pet != null && player.pet.nPoint != null) {
                                    // phải có đệ mabu hoặc đệ thường
                                    if(player.pet.typePet != ConstPet.NORMAL && player.pet.typePet != ConstPet.MABU) {
                                        Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử thường hoặc đệ Ma Bư mập đạt 100 tỷ sức mạnh");
                                        return;
                                    }
                                    if(player.pet.nPoint.power < 100_000_000_000L) {
                                        Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử thường hoặc đệ Ma Bư mập đạt 100 tỷ sức mạnh");
                                        return;
                                    }
                                    this.createOtherMenu(player, ConstNpc.CONFIRM_OPEN_EGG,
                                            "Bạn có chắc chắn cho trứng nở?\n"
                                                    + "Đệ tử của bạn sẽ được thay thế bằng đệ Bill Egg",
                                            "Đệ Bill Egg\nTrái Đất", "Đệ Bill Egg\nNamếc", "Đệ Bill Egg\nXayda",
                                            "Từ chối");
                                }
                                break;
                            case 1:
                                this.createOtherMenu(player, ConstNpc.CONFIRM_DESTROY_EGG,
                                        "Bạn có chắc chắn muốn hủy bỏ trứng Bill Egg?", "Đồng ý",
                                        "Từ chối");
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_OPEN_EGG:
                        switch (select) {
                            case 0:
                                player.billEgg.openEggBill(ConstPlayer.TRAI_DAT);
                                break;
                            case 1:
                                player.billEgg.openEggBill(ConstPlayer.NAMEC);
                                break;
                            case 2:
                                player.billEgg.openEggBill(ConstPlayer.XAYDA);
                                break;
                            default:
                                break;
                        }
                        break;
                    case ConstNpc.CONFIRM_DESTROY_EGG:
                        if (select == 0) {
                            player.billEgg.destroyEggBill();
                        }
                        break;
                    case ConstNpc.GO_TO_MAP_TASK:
                        if (select == 0) {
                            ChangeMapService.gI().changeMapNonYard(player, 47, 627, 336);
                        }
                        break;
                }
            }
        }
    }
}
