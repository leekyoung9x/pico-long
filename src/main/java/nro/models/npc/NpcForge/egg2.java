/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class egg2 extends Npc {

    public egg2(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (this.mapId == (21 + player.gender)) {
                player.billEgg.sendBillEgg();
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
                }
            }
        }
    }
}
