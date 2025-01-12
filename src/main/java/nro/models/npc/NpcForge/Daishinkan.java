package nro.models.npc.NpcForge;

import nro.consts.ConstAction;
import nro.consts.ConstNpc;
import nro.jdbc.daos.PlayerDAO;
import nro.manager.EventTurnManager;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.PetService;
import nro.services.Service;
import nro.services.func.ShopService;

/**
 * @author Arriety
 */
public class Daishinkan extends Npc {

    public Daishinkan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                    "Mở vip bạn sẽ nhận được:"
                    + "\nX2 tnsm, 10% thêm toàn chỉ số HP/MP/DAME GỐC"
                    + "\nLinh thú VIP 20% chỉ số"
                    + "\nĐệ tử Fide Nhí siêu đẹp trai hợp thể + 20% chỉ số"
                    + "\n200k Hồng ngọc + 200k ngọc xanh"
                    + "\nNhận được 30k đồng Coin"
                    + "\nHào quang rồng thần cực múp cực nứng"
                    + "\nKhi hạ được quái rơi 1 item nhặt được x2 vật phẩm",
                    "Mở Víp 50.000 Vnd",
                    "Shop\nCoint", "Điểm danh\nHàng ngày", "Mở víp 2");

        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                int turn = EventTurnManager.ManageEventShiba(ConstAction.GET_BY_ID, player.id);
                switch (select) {
                    case 0:
                        if (player.getSession().actived >= 1) {
                            this.npcChat(player, "Bạn đã mở víp rồi");
                            return;
                        }
                        if (player.getSession().vnd >= 50000) {
                            if (InventoryService.gI().getCountEmptyBag(player) > 1) {
                                if (PlayerDAO.active(player, 50000)) {
                                    Item item = ItemService.gI().createNewItem((short) 1967, 1);// dt
                                    InventoryService.gI().addItemBag(player, item, 9999);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn đã kích hoạt víp thành công");
                                } else {
                                    this.npcChat(player, "Lỗi vui lòng báo admin...");
                                }
                            } else {
                                Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Số dư vnd không đủ vui lòng nạp thêm năm chục :D");
                        }
                        break;
                    case 1:
                        ShopService.gI().openShopSpecial(player, this, ConstNpc.SHOP_CHIEN_LUC, 0, -1);
                        break;
                    case 2:
                        if (turn <= 0) {
                            this.npcChat(player, "Ngươi đã nhận quà hôm nay rồi còn muốn gì nữa?");
                        } else {
                            int i = 75_000;
                            try {
                                player.inventory.addGem(i);
                                int oldRuby = player.inventory.ruby;
                                player.inventory.addRuby(i);
                                // ekko ghi log add ruby
                                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Daishinkan-confirmMenu");
                                Service.getInstance().sendMoney(player);
                                Service.getInstance().sendThongBao(player, "Ta sẽ đã cho ngươi món quà đặc biệt ngày hôm nay rồi\nMai lại gặp lại nhé");
                                EventTurnManager.ManageEventShiba(ConstAction.UPDATE_BY_ID, player.id);
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.npcChat(player, "Có lỗi xảy ra vui lòng liên hệ admin!");
                            }
                        }
                        break;
                    case 3:
                        Item cl = InventoryService.gI().findItemBagByTemp(player, 2000);
                        int requiredQuantity = (cl == null) ? 0 : cl.quantity;
                        createOtherMenu(player, ConstNpc.NAP_VIP_2,
                                "Bạn có muốn mở Vip 2 để nhận được\n"
                                + " 100k ngọc xanh + 100k hn + 1 bộ nrsc + 1 Ván bay phượng hoàng không\n"
                                + "mảnh điểm chiến lực bạn đang có là: ["
                                + requiredQuantity + "] mảnh\n"
                                + "số vnd bạn đang có là: ["
                                + player.getSession().vnd + "]",
                                "Lựa chọn 1\n[100 mảnh chiến lực]", "Lựa chọn 2[50.000 VND]");

                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.NAP_VIP_2) {
                switch (select) {
                    case 0:
                        if (player.getSession().actived == 0) {
                            this.npcChat(player, "Bạn chưa mở víp 1");
                            return;
                        }
                        Item cl = InventoryService.gI().findItemBagByTemp(player, 2000);
                        if (cl == null) {
                            this.npcChat(player, "Bạn không có điểm chiến lực");
                            return;
                        }
                        if (cl.quantity >= 100) {
                            if (InventoryService.gI().getCountEmptyBag(player) > 1) {
//                                player.pointShiba++;
                                Item item = ItemService.gI().createNewItem((short) 1953, 1);
                                InventoryService.gI().addItemBag(player, item, 9999);
                                InventoryService.gI().subQuantityItemsBag(player, cl, 100);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Bạn đã kích hoạt víp thành công");
                            } else {
                                Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn không đủ điểm sự kiện");
                        }
                        break;
                    case 1:
                        if (player.getSession().actived == 0) {
                            this.npcChat(player, "Bạn chưa mở víp 1");
                            return;
                        }
                        if (player.getSession().vnd >= 50000) {
                            if (InventoryService.gI().getCountEmptyBag(player) > 1) {
                                if (PlayerDAO.active(player, 50000)) {
//                                    player.pointShiba++;
                                    Item item = ItemService.gI().createNewItem((short) 1953, 1);
                                    InventoryService.gI().addItemBag(player, item, 9999);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn đã kích hoạt víp thành công");
                                } else {
                                    this.npcChat(player, "Lỗi vui lòng báo admin...");
                                }
                            } else {
                                Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Số dư vnd không đủ vui lòng nạp thêm năm chục :D");
                        }
                        break;

                }

            }
        }
    }

}
