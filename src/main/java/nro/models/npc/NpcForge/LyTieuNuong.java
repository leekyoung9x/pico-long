/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.*;
import nro.dialog.MenuDialog;
import nro.dialog.MenuRunable;
import nro.jdbc.daos.PlayerDAO;
import nro.manager.EventTurnManager;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Pet;
import nro.models.player.Player;
import nro.models.shop.ItemShop;
import nro.models.shop.Shop;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.services.func.CombineServiceNew;
import nro.services.func.Input;
import nro.services.func.MiniGame;
import nro.services.func.ShopService;
import nro.utils.Util;

import java.time.LocalTime;

/**
 * @author Arriety
 */
public class LyTieuNuong extends Npc {

    public LyTieuNuong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
//        if (canOpenNpc(player)) {
//            createOtherMenu(player, ConstNpc.BASE_MENU,
//                    "Xin chào, tôi có 1 sự kiện đặc biệt bạn có muốn tham gia không?\n"
//                    + "Số tiền nạp tích lũy của bạn hiện tại là: ["
//                    + player.getSession().poinCharging + "]",
//                    "1 hộp quà\n[10.000 điểm]",
//                    "12 hộp quà\n[100.000 điểm]");
//        }
        if (canOpenNpc(player)) {
            // ekko
//            createOtherMenu(player, ConstNpc.BASE_MENU,
//                    "Hi Chào cậu",
//                    "Quy đổi\nCoint",
//                    "Quy đổi\nPoint nạp[MỚI]",
//                    "Đổi danh hiệu\n[5000 đá ngũ sắc]",
//                    "Đổi danh hiệu\n[30 điểm point]","CSMM");
//            createOtherMenu(player, ConstNpc.BASE_MENU,
//                    "Bạn có muốn mở VIP không - điều này góp phần duy trì sever đếy!\n" +
//                            "Với 50k vnđ bạn có thể sỡ hữu các đặc quyền sau:\n" +
//                            "- Hào quang goku vô cực\n" +
//                            "- Capsule nhiệm vụ (có thể next đến nhiệm vụ nappa)\n" +
//                            "- 2 Hộp SKH (Chọn 1 set kích hoạt tùy thích mỗi hộp\n" +
//                            "- Giao dịch được ngay mà không cần đạt 80 tỷ sức mạnh\n" +
//                            "- 500k Hồng ngọc & 500k ngọc xanh\n" +
//                            "- Nhận được x2 tiềm năng sức mạnh khi up quái\n" +
//                            "|7|Lưu ý bạn có thể mở VIP nhiều lần!\n" +
//                            "|7|Số point nạp bạn có là: [" + player.getSession().poinCharging + "]",
//                    "Mở VIP 1 (Tốn 50k vnđ)", "Mở VIP 2 (Tốn 50k vnđ)", "Quy Đổi Thỏi vàng\n[VIP]", "Cửa hàng", "Đổi\nhộp quà\nNoel", "Đổi điểm tích lũy", "Đổi\nBóng tuyết\n10-1", "Đổi\nBóng tuyết\n100-12");
            createOtherMenu(player, ConstNpc.BASE_MENU,
                    "Bạn có muốn mở VIP không - điều này góp phần duy trì sever đếy!\n" +
                            "Với 50k vnđ bạn có thể sỡ hữu các đặc quyền sau:\n" +
                            "- Hào quang goku vô cực\n" +
                            "- Capsule nhiệm vụ (có thể next đến nhiệm vụ nappa)\n" +
                            "- Giao dịch được ngay mà không cần đạt 80 tỷ sức mạnh\n" +
                            "- 500k Hồng ngọc & 500k ngọc xanh\n" +
                            "- Nhận được x2 tiềm năng sức mạnh khi up quái\n" +
                            "|7|Lưu ý bạn có thể mở VIP nhiều lần!\n" +
                            "|7|Số point nạp bạn có là: [" + player.getSession().poinCharging + "]",
                    "Mở VIP 1 (Tốn 50k vnđ)", "Quy Đổi Thỏi vàng\n[VIP]", "Đổi đá thần linh", "Cửa hàng");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            // bổ sung thêm 3 map làng nữa
            if (this.mapId == 5 || this.mapId == 0 || this.mapId == 7 || this.mapId == 14) {
                if (player.iDMark.isBaseMenu()) {
                    switch (select) {
                        case 0:
                            if (player.getSession().vnd >= 50000) {
                                if (InventoryService.gI().getCountEmptyBag(player) > 1) {
                                    if (PlayerDAO.active(player, 50000)) {
                                        // Capsule nhiệm vụ
                                        Item item = ItemService.gI().createNewItem((short) 2137, 1);
                                        item.itemOptions.add(new ItemOption(30, 0));
                                        InventoryService.gI().addItemBag(player, item, 1);

                                        // 2 hộp SKH
//                                        item = ItemService.gI().createNewItem((short) ConstItem.HOP_QUA_THUONG, 2);
//                                        item.itemOptions.add(new ItemOption(30, 0));
//                                        InventoryService.gI().addItemBag(player, item, 2);

                                        // 500k ruby and ngọc xanh
                                        item = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, 500_000);
                                        // ekko ghi log add ruby
                                        Manager.addPlayerRubyHistory(player.id, player.inventory.ruby, player.inventory.ruby + 500_000, "LyTieuNuong-confirmMenu");
                                        InventoryService.gI().addItemBag(player, item, 0);
                                        item = ItemService.gI().createNewItem((short) ConstItem.NGOC, 500_000);
                                        InventoryService.gI().addItemBag(player, item, 0);

                                        Service.getInstance().sendMoney(player);
                                        Service.getInstance().player(player);
                                        Service.getInstance().Send_Caitrang(player);
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
//                        case 1:
//                            if (player.getSession().actived == 1) {
//                                MenuDialog menu = new MenuDialog("Bạn có chắc chắn muốn mở VIP lần 2 \n" +
//                                        "Chỉ với 50k bạn sẽ nhận được các phần quà cực hấp dẫn như sau:\n" +
//                                        " -Nhận được 30k Thỏi vàng VIP\n" +
//                                        " -x2 Tnsm ở Map thung lũng đá\n" +
//                                        " -x1 Đệ tử Whis\n" +
//                                        " -Danh hiệu: Đệ tứ (40% SĐ,HP,KI)\n" +
//                                        " -10tr ngọc xanh &10tr Hồng ngọc\n" +
//                                        " -x300 Bùa sức đánh,x300 bùa Hp,x300 bùa Ki\n" +
//                                        "|7|Bạn chỉ có thể mở VIP 2 khi bạn đã mở VIP 1", new String[]{"Có mở", "Đéo mở"}, new MenuRunable() {
//                                    @Override
//                                    public void run() {
//                                        switch (getIndexSelected()) {
//                                            case 0: {
//                                                if (!(InventoryService.gI().getCountEmptyBag(player) > 1)) {
//                                                    Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
//                                                    return;
//                                                }
//
//                                                if (player.getSession().vnd >= 50_000) {
////                                                    player.getSession().vnd -= 50_000;
////                                                    PlayerDAO.subVND(player, 50_000);
//                                                    PlayerDAO.activeVipTwo(player, 50_000);
//
//                                                    // 30k Thỏi vàng VIP
//                                                    Item item = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, 30_000);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//
//                                                    // Đệ tử Whis
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.DE_TU_WHIS, 1);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//
//                                                    // Danh hiệu: Đệ tứ (40% SĐ,HP,KI)
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.DANH_HIEU_DE_TU, 1);
//                                                    item.itemOptions.add(new ItemOption(50, 40));
//                                                    item.itemOptions.add(new ItemOption(77, 40));
//                                                    item.itemOptions.add(new ItemOption(103, 40));
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//
//                                                    // 10tr ngọc xanh &10tr Hồng ngọc
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.NGOC, 10_000_000);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, 10_000_000);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//
//                                                    // x300 Bùa sức đánh,x300 bùa Hp,x300 bùa Ki
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.BUA_PHAP_SU_HP, 300);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.BUA_PHAP_SU_KI, 300);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//                                                    item = ItemService.gI().createNewItem((short) ConstItem.BUA_PHAP_SU_SD, 300);
//                                                    InventoryService.gI().addItemBag(player, item, 0);
//
//                                                    // ekko ghi log add ruby
//                                                    Manager.addPlayerRubyHistory(player.id, player.inventory.ruby, player.inventory.ruby + 10_000_000, "LyTieuNuong-confirmMenu");
//
//                                                    Service.getInstance().sendMoney(player);
//                                                    InventoryService.gI().sendItemBags(player);
//                                                    Service.getInstance().sendThongBao(player, "Bạn đã kích hoạt víp thành công và nhận được 30k Thỏi vàng VIP, 1 Đệ tử Whis, 1 Danh hiệu Đệ tứ (40% SĐ,HP,KI), 10tr ngọc xanh, 10tr Hồng ngọc, 300 Bùa sức đánh, 300 Bùa Hp, 300 Bùa Ki");
//                                                } else {
//                                                    Service.getInstance().sendThongBao(player, "Số dư vnd không đủ vui lòng nạp thêm năm chục :D");
//                                                }
//                                                break;
//                                            }
//                                            case 1:
//                                                break;
//                                        }
//                                    }
//                                });
//                                menu.show(player);
//                                return;
//                            } else if(player.getSession().actived == 0) {
//                                Service.getInstance().sendThongBao(player, "Bạn chưa mở VIP 1");
//                            } else if(player.getSession().actived == 2) {
//                                Service.getInstance().sendThongBao(player, "Bạn đã mở VIP 2 rồi");
//                            }
//                            break;
                        case 1:
                            Input.gI().createFormTradeRuby(player);
                            break;
                        case 2:
                            this.createOtherMenu(player, ConstNpc.DOI_DA_THAN_LINH,
                                    "Con có muốn đổi 1 đồ thần linh thành 50 đá thần linh không ?", "Đồng ý", "Từ chối");
                            break;
                        case 3: {
//                            ShopService.gI().openShopSpecial(player, this, ConstNpc.SHOP_LTN_SPEC, 1, -1);
                            Shop shop = ShopService.gI().getShop(this.tempId, 1, -1);

                            for (int i = 0; i < shop.tabShops.size(); i++) {
                                if (shop.tabShops.get(i).id == 47) {
                                    for (int j = 0; j < shop.tabShops.get(i).itemShops.size(); j++) {
                                        ItemShop itemShop = shop.tabShops.get(i).itemShops.get(j);

                                        if (itemShop.temp.id == 555 || itemShop.temp.id == 557 || itemShop.temp.id == 559) {
                                            switch (player.gender) {
                                                case (byte) ConstPlayer.TRAI_DAT:
                                                    itemShop.temp = ItemService.gI().getTemplate(555);
                                                    break;
                                                case (byte) ConstPlayer.NAMEC:
                                                    itemShop.temp = ItemService.gI().getTemplate(557);
                                                    break;
                                                case (byte) ConstPlayer.XAYDA:
                                                    itemShop.temp = ItemService.gI().getTemplate(559);
                                                    break;
                                            }
                                        }

                                        if (itemShop.temp.id == 562 || itemShop.temp.id == 564 || itemShop.temp.id == 566) {
                                            switch (player.gender) {
                                                case (byte) ConstPlayer.TRAI_DAT:
                                                    itemShop.temp = ItemService.gI().getTemplate(562);
                                                    break;
                                                case (byte) ConstPlayer.NAMEC:
                                                    itemShop.temp = ItemService.gI().getTemplate(564);
                                                    break;
                                                case (byte) ConstPlayer.XAYDA:
                                                    itemShop.temp = ItemService.gI().getTemplate(566);
                                                    break;
                                            }
                                        }

                                        if (itemShop.temp.id == 563 || itemShop.temp.id == 565 || itemShop.temp.id == 567) {
                                            switch (player.gender) {
                                                case (byte) ConstPlayer.TRAI_DAT:
                                                    itemShop.temp = ItemService.gI().getTemplate(563);
                                                    break;
                                                case (byte) ConstPlayer.NAMEC:
                                                    itemShop.temp = ItemService.gI().getTemplate(565);
                                                    break;
                                                case (byte) ConstPlayer.XAYDA:
                                                    itemShop.temp = ItemService.gI().getTemplate(567);
                                                    break;
                                            }
                                        }

                                        if (itemShop.temp.id == 556 || itemShop.temp.id == 558 || itemShop.temp.id == 560) {
                                            switch (player.gender) {
                                                case (byte) ConstPlayer.TRAI_DAT:
                                                    itemShop.temp = ItemService.gI().getTemplate(556);
                                                    break;
                                                case (byte) ConstPlayer.NAMEC:
                                                    itemShop.temp = ItemService.gI().getTemplate(558);
                                                    break;
                                                case (byte) ConstPlayer.XAYDA:
                                                    itemShop.temp = ItemService.gI().getTemplate(560);
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                            ShopService.gI().openShopType3(player, shop, ConstNpc.SHOP_LTN_SPEC);
                            break;
                        }
//                        case 4: {
//                            int point = 10, quantity = 1, itemID = ConstItem.HOP_QUA_NOEL;
//
//                            ChangeGift(player, point, (short) itemID, quantity);
//                            break;
//                        }
//                        case 5: {
//                            if(player != null && player.getSession() != null) {
//                                String npcSaid = "Con đang có " + player.getSession().poinCharging + " điểm tích lũy. Nếu đổi 50 điểm con sẽ nhận được 1 rương ngọc hắc ám.";
//                                createOtherMenu(player, ConstNpc.DOI_QUA_TICH_LUY, npcSaid, "50 điểm", "Hủy");
//                            }
//                            break;
//                        }
//                        case 6: {
//                            int point = 10, quantity = 1, itemID = ConstItem.HOP_QUA_BONG_TUYET;
//
//                            ChangeGift(player, point, (short) itemID, quantity);
//                            break;
//                        }
//                        case 7: {
//                            int point = 100, quantity = 12, itemID = ConstItem.HOP_QUA_BONG_TUYET;
//
//                            ChangeGift(player, point, (short) itemID, quantity);
//                            break;
//                        }

                        // ekko
//                        case 1:
//                            createOtherMenu(player, ConstNpc.QUY_DOI_DIEM_SKIEN,
//                                    "Xin chào, tôi có 1 sự kiện đặc biệt bạn có muốn tham gia không?\n"
//                                    + "số điểm sự kiện bạn đã tích lũy là: ["
//                                    + player.getSession().poinCharging + "]",
//                                    "1 hộp quà\n[10 điểm]", "12 hộp quà\n[100 điểm]");
//                            break;
//                        case 2:
//                            Item pi = InventoryService.gI().findItemBagByTemp(player, 674);
//                            if (pi == null || pi.quantity < 4999) {
//                                this.npcChat(player, "Hí hí cu iem chưa đủ x4999 đá ngũ sắc rồi");
//                                return;
//                            }
//                            Item dcc2 = ItemService.gI().createNewItem((short) 2100, 1);
//                            dcc2.itemOptions.add(new ItemOption(50, 20));
//                            dcc2.itemOptions.add(new ItemOption(77, 20));
//                            dcc2.itemOptions.add(new ItemOption(103, 20));
//                            InventoryService.gI().addItemBag(player, dcc2, 1_500_000);
//
//                            InventoryService.gI().subQuantityItemsBag(player, pi, 4999);
//                            InventoryService.gI().sendItemBags(player);
//                            this.npcChat(player, "Đổi thành công danh hiệu bất bại");
//                            break;
//                         case 3:
//                            if (player.getSession().poinCharging < 30) {
//                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
//                            } else {
//                                PlayerDAO.subPoin(player, 30);
//                                Item pet = ItemService.gI().createNewItem((short) 2136);
//                                pet.itemOptions.add(new ItemOption(50,Util.nextInt(5, 30)));
//                                pet.itemOptions.add(new ItemOption(77,Util.nextInt(5, 30) ));
//                                pet.itemOptions.add(new ItemOption(103, Util.nextInt(5, 30)));
//                                pet.itemOptions.add(new ItemOption(14,5));
//                                pet.itemOptions.add(new ItemOption(37,5));
//                                InventoryService.gI().addItemBag(player, pet, 1);
//                                InventoryService.gI().sendItemBags(player);
//                                this.npcChat(player, "Đổi thành công danh hiệu trùm cuối");
//                            }
//
//                            break;
//                        case 4:
//                            xửLýLựaChọnMiniGame_Gold(player);
//                            break;

                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI_DIEM_SKIEN) {
                    switch (select) {
                        case 0:
                            if (player.getSession().poinCharging < 10) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 10);
                                Item hopqua = ItemService.gI().createNewItem((short) 2132, 1);
                                InventoryService.gI().addItemBag(player, hopqua, 9999);
                                Service.getInstance().sendThongBao(player, "Bạn nhận x1 hộp quà SIU CẤP VIP");
                                InventoryService.gI().sendItemBags(player);
                            }
                            break;
                        case 1:
                            if (player.getSession().poinCharging < 100) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 100);
                                Item hopqua = ItemService.gI().createNewItem((short) 2132, 12);
                                InventoryService.gI().addItemBag(player, hopqua, 9999);
                                Service.getInstance().sendThongBao(player, "Bạn nhận x12 hộp quà SIU CẤP VIP ");
                                InventoryService.gI().sendItemBags(player);
                            }
                            break;
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI_POINT_NAP) {
                    switch (select) {
                        case 0:
                            if (player.getSession().poinCharging < 10) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 10);
                                Item gokuVoCuc = ItemService.gI().createNewItem((short) 2092, 1);

                                InventoryService.gI().addItemBag(player, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
                                Service.getInstance().sendThongBao(player, "Bạn nhận x1 hộp quà");
                                InventoryService.gI().sendItemBags(player);
                            }
                            break;
                        case 1:
                            if (player.getSession().poinCharging < 100) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 100);
                                Item gokuVoCuc = ItemService.gI().createNewItem((short) 2092, 12);

                                InventoryService.gI().addItemBag(player, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
                                Service.getInstance().sendThongBao(player, "Bạn nhận x12 hộp quà");
                                InventoryService.gI().sendItemBags(player);
                            }
                            break;
//                        case 1:
//                            if (player.getSession().poinCharging < 100) {
//                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
//                            } else {
//                                PlayerDAO.subPoin(player, 100);
//                                Item coint = ItemService.gI().createNewItem((short) 1283, 12);
//                                InventoryService.gI().addItemBag(player, coint, 1_500_000);
//                                InventoryService.gI().sendItemBags(player);
//                                Service.getInstance().sendThongBao(player, "Đã đổi thành công bạn nhận được hộp quà");
//                            }
//                            break;
//                        case 2:
//                            if (player.getSession().poinCharging < 100) {
//                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
//                            } else {
//                                PlayerDAO.subPoin(player, 100);
//                                Item pet = ItemService.gI().createNewItem((short) 2057);
//                                pet.itemOptions.add(new ItemOption(50, 21));
//                                pet.itemOptions.add(new ItemOption(77, 22));
//                                pet.itemOptions.add(new ItemOption(103, 22));
//                                pet.itemOptions.add(new ItemOption(234, 1));
//                                InventoryService.gI().addItemBag(player, pet, 99);
//                                InventoryService.gI().sendItemBags(player);
//                                Service.getInstance().sendThongBao(player, "Đã đổi thành công bạn nhận được Pet haizzz shibaaa");
//                            }
//                            break;
//                        case 3:
//                            if (player.getSession().poinCharging < 100) {
//                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
//                            } else {
//                                PlayerDAO.subPoin(player, 100);
//                                Item pet = ItemService.gI().createNewItem((short) 2086);
//                                pet.quantity = 50;
//                                InventoryService.gI().addItemBag(player, pet, 9999);
//                                InventoryService.gI().sendItemBags(player);
//                                Service.getInstance().sendThongBao(player, "Đã đổi thành công");
//                            }
//                            break;
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI_POINT_NAP2) {
                    switch (select) {
                        case 0:
                            if (player.getSession().poinCharging < 10) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 10);
                                Item pet = ItemService.gI().createNewItem((short) 2084);
                                pet.itemOptions.add(new ItemOption(50, 30));
                                pet.itemOptions.add(new ItemOption(77, 30));
                                pet.itemOptions.add(new ItemOption(103, 20));
                                pet.itemOptions.add(new ItemOption(101, 500));
                                if (Util.isTrue(95, 100)) {
                                    pet.itemOptions.add(new ItemOption(93, Util.nextInt(1, 2)));
                                }
                                InventoryService.gI().addItemBag(player, pet, 1);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Đã đổi thành công bạn nhận được Pet hi Panda");
                            }
                            break;
                        case 1:
                            if (player.getSession().poinCharging < 200) {
                                Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
                            } else {
                                PlayerDAO.subPoin(player, 200);
                                Item pet2 = ItemService.gI().createNewItem((short) 2084);
                                pet2.itemOptions.add(new ItemOption(50, 30));
                                pet2.itemOptions.add(new ItemOption(77, 30));
                                pet2.itemOptions.add(new ItemOption(103, 20));
                                pet2.itemOptions.add(new ItemOption(101, 500));

                                InventoryService.gI().addItemBag(player, pet2, 1);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Đã đổi thành công bạn nhận được Pet hi Panda");

                                break;

                            }
                    }

                }
                // Đổi quà tích lũy
                else if (player.iDMark.getIndexMenu() == ConstNpc.DOI_QUA_TICH_LUY) {
                    // Trên 3 điểm thì mới được đổi quà tích lũy
                    if(player != null && player.getSession() != null) {
                        switch (select) {
                            // đổi 50 điểm
                            case 0:
                                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                                    if(player.getSession().poinCharging < 50) {
                                        Service.getInstance().sendThongBao(player, "Con không đủ 50 điểm tích lũy, hãy nạp thêm và quay lại gặp ta nhé!");
                                    } else {
                                        // trừ 50 điểm tích lũy
                                        PlayerDAO.subPoin(player, 50);
                                        int quantity = 1;
                                        Item ruong = ItemService.gI().createNewItem((short) ConstItem.RUONG_NGOC_HAC_AM, quantity);
                                        InventoryService.gI().addItemBag(player, ruong, 0);
                                        InventoryService.gI().sendItemBags(player);
                                        Service.getInstance().sendThongBao(player, "Con nhận được " + ruong.template.name + " số lượng " + quantity);
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                                }
                                break;
                            default:
                                break;
                        }
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                    switch (player.combineNew.typeCombine) {
                        case CombineServiceNew.DOI_DA_THAN_LINH:
                            if (select == 0) {
                                CombineServiceNew.gI().startCombine(player);
                            }
                            break;
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.DOI_DA_THAN_LINH) {
                    switch (select) {
                        case 0:
                            CombineServiceNew.gI().openTabCombine(player,
                                    CombineServiceNew.DOI_DA_THAN_LINH);
                            break;
                        case 1:
                            break;
                    }
                }
            }
        }
    }

    private static void ChangeGift(Player player, int point, short itemID, int quantity) {
        if (player.getSession().poinCharging < point) {
            Service.getInstance().sendThongBao(player, "Số point không đủ vui lòng nạp thêm!");
        } else {
            PlayerDAO.subPoin(player, point);
            Item hopqua = ItemService.gI().createNewItem(itemID, quantity);
            InventoryService.gI().addItemBag(player, hopqua, 0);
            Service.getInstance().sendThongBao(player, "Bạn nhận x" + quantity + " " + hopqua.template.name);
            InventoryService.gI().sendItemBags(player);
        }
    }

    private void xửLýLựaChọnMiniGame_Gold(Player player) {
        LocalTime thoi_gian_hien_tai = LocalTime.now();
        int gio = thoi_gian_hien_tai.getHour();
        int phut = thoi_gian_hien_tai.getMinute();
        String plWin = MiniGame.gI().MiniGame_S1.result_name;
        String KQ = MiniGame.gI().MiniGame_S1.result + "";
        String Money = Util.mumberToLouis(MiniGame.gI().MiniGame_S1.gold) + "";
        String count = MiniGame.gI().MiniGame_S1.players.size() + "";
        String second = MiniGame.gI().MiniGame_S1.second + "";
        String number = MiniGame.gI().MiniGame_S1.strNumber((int) player.id);
        StringBuilder previousResults = new StringBuilder("");
        if (MiniGame.gI().MiniGame_S1.dataKQ_CSMM != null && !MiniGame.gI().MiniGame_S1.dataKQ_CSMM.isEmpty()) {
            int maxResultsToShow = Math.min(10, MiniGame.gI().MiniGame_S1.dataKQ_CSMM.size());
            for (int i = MiniGame.gI().MiniGame_S1.dataKQ_CSMM.size() - maxResultsToShow; i < MiniGame.gI().MiniGame_S1.dataKQ_CSMM.size(); i++) {
                previousResults.append(MiniGame.gI().MiniGame_S1.dataKQ_CSMM.get(i));
                if (i < MiniGame.gI().MiniGame_S1.dataKQ_CSMM.size() - 1) {
                    previousResults.append(",");
                }
            }
        }

        String npcSay = ""
                + "Kết quả giải trước: " + KQ + "\n"
                + (previousResults.toString() != "" ? previousResults.toString() + "\n" : "")
                + "Tổng giải thưởng: " + Money + " thỏi vàng\n"
                + "<" + second + ">giây\n"
                + (number != "" ? "Các số bạn chọn: " + number : "");
        String[] Menus = {
                "Cập nhật"
        };
        createOtherMenu(player, ConstNpc.CON_SO_MAY_MAN_VANG, npcSay, Menus);
        return;
    }
}
