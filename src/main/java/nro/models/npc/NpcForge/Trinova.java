/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.consts.ConstTask;
import nro.jdbc.DBService;
import nro.jdbc.daos.AccountDAO;
import nro.jdbc.daos.PlayerDAO;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.item.ItemReward;
import nro.models.item_reward.ItemRewardQuantity;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.player.PlayerReward;
import nro.models.shop.Shop;
import nro.server.Manager;
import nro.services.*;
import nro.services.func.Input;
import nro.utils.Log;
import nro.utils.Util;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Arriety
 */
public class Trinova extends Npc {

    public Trinova(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    private static boolean nhanDeTu = true;

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                // ekko
//                this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                        "|7|CODE: tanthu teongu vatpham thoivang teodeptrai bokhi thieunhi113 denbuvip emdeplam bienthai chanquang bantumlum danhngu teongu denbupico canoc",
//                        "Giftcode", "Sự kiện hè", "Nhận quà\n mốc nạp ");
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "|7|Chào mừng bạn đến với ngọc rồng HAT",
                        "Giftcode", "Nhận vàng\n miễn phí", "Nhận ngọc xanh\n miễn phí", "Nhận hồng ngọc\n miễn phí", "Nhận đệ tử miễn phí");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                var quantity = 0;
                short itemID = 0;
                Item item = null;
                Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); // Ví dụ timestamp hiện tại
                // tồn tại item chua
                boolean isExist = false;
                // trạng thái nhận item
                boolean isReceived = false;
                switch (select) {
                    case 0:
                        Input.gI().createFormGiftCode(player);
                        break;
                    case 1:
                        // mỗi lần nhân x99 thỏi vàng
                        quantity = 99;
                        itemID = ConstItem.THOI_VANG;
                        item = ItemService.gI().createNewItem(itemID, quantity);
                        InventoryService.gI().addItemBag(player, item, 99999);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                        break;
                    case 2:
                        //Mỗi ngày nhận 100k ngọc xanh
                        quantity = 100000;
                        itemID = ConstItem.NGOC;
                        if (player.getSession().actived >= 1) {
                            quantity = quantity * 2;
                        }
                        if(player.playerReward != null && player.playerReward.items != null && !player.playerReward.items.isEmpty()) {
                            // kiểm tra hôm nay đã nhận ngọc xanh chưa
                            Optional<ItemRewardQuantity> greenGem = player.playerReward.items.stream()
                                    .filter(x -> x.getId() == ConstItem.NGOC).findFirst();
                            if(greenGem.isPresent()) {
                                // Chuyển đổi Timestamp sang LocalDate
                                ZonedDateTime zdt = greenGem.get().getDateReward().toInstant().atZone(ZoneId.systemDefault());
                                LocalDate timestampDate = zdt.toLocalDate();
                                // Lấy ngày hiện tại
                                LocalDate today = LocalDate.now();
                                // Kiểm tra xem ngày nhận quà có phải là ngày hôm nay không
                                if (timestampDate.equals(today)) {
                                    isReceived = true;
                                }
                                // ton tai roi
                                isExist = true;
                            }

                            // chưa nhận quà
                            if(!isReceived) {
                                //Mỗi ngày nhận 100k ngọc xanh
                                item = ItemService.gI().createNewItem(itemID, quantity);
                                InventoryService.gI().addItemBag(player, item, 99999);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                                // tồn tại rồi thì update
                                if (isExist) {
                                    int itemsSize = player.playerReward.items.size();
                                    for (int i = 0; i < itemsSize; i++) {
                                        ItemRewardQuantity currentItem = player.playerReward.items.get(i);
                                        if (currentItem.getId() == ConstItem.NGOC) {
                                            // Thay đổi giá trị của phần tử tại chỉ số i
                                            player.playerReward.items.set(i, new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                                            break; // Ngừng tìm kiếm nếu chỉ cần thay đổi giá trị của phần tử đầu tiên tìm thấy
                                        }
                                    }
                                } else {
                                    player.playerReward.items.add(new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                                }
                            } else {
                                Service.getInstance().sendThongBao(player,"Hôm nay húp rồi, ngày mai quay lại nha con");
                            }
                        }
                        // chưa có thông tin nhận quà thì cho nhận luôn
                        else {
                            //Mỗi ngày nhận 100k ngọc xanh
                            item = ItemService.gI().createNewItem(itemID, quantity);
                            InventoryService.gI().addItemBag(player, item, 99999);
                            InventoryService.gI().sendItemBags(player);
                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                            player.playerReward = new PlayerReward();
                            player.playerReward.player_id = player.id;
                            player.playerReward.items = new ArrayList<>();
                            player.playerReward.items.add(new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                        }
                        break;
                    case 3:
                        //Mỗi ngày nhận 100k hồng ngọc
                        quantity = 100000;
                        itemID = ConstItem.HONG_NGOC;
                        if (player.getSession().actived >= 1) {
                            quantity = quantity * 2;
                        }
                        if(player.playerReward != null && player.playerReward.items != null && !player.playerReward.items.isEmpty()) {
                            // kiểm tra hôm nay đã nhận hồng ngọc chưa
                            Optional<ItemRewardQuantity> pinkGem = player.playerReward.items.stream()
                                    .filter(x -> x.getId() == ConstItem.HONG_NGOC).findFirst();
                            if(pinkGem.isPresent()) {
                                // Chuyển đổi Timestamp sang LocalDate
                                ZonedDateTime zdt = pinkGem.get().getDateReward().toInstant().atZone(ZoneId.systemDefault());
                                LocalDate timestampDate = zdt.toLocalDate();
                                // Lấy ngày hiện tại
                                LocalDate today = LocalDate.now();
                                // Kiểm tra xem ngày nhận quà có phải là ngày hôm nay không
                                if (timestampDate.equals(today)) {
                                    isReceived = true;
                                }
                                // ton tai hong ngoc thi ty chi update
                                isExist = true;
                            }

                            // chưa nhận quà
                            if(!isReceived) {
                                //Mỗi ngày nhận 100k hồng ngọc
                                item = ItemService.gI().createNewItem(itemID, quantity);
                                // ekko ghi log add ruby
                                Manager.addPlayerRubyHistory(player.id, player.inventory.ruby, player.inventory.ruby + quantity, "Trinova-confirmMenu");
                                InventoryService.gI().addItemBag(player, item, 99999);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                                // tồn tại rồi thì update
                                if(isExist) {
                                    int itemsSize = player.playerReward.items.size();
                                    for (int i = 0; i < itemsSize; i++) {
                                        ItemRewardQuantity currentItem = player.playerReward.items.get(i);
                                        if (currentItem.getId() == ConstItem.HONG_NGOC) {
                                            // Thay đổi giá trị của phần tử tại chỉ số i
                                            player.playerReward.items.set(i, new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                                            break; // Ngừng tìm kiếm nếu chỉ cần thay đổi giá trị của phần tử đầu tiên tìm thấy
                                        }
                                    }
                                } else {
                                    player.playerReward.items.add(new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                                }
                            } else {
                                Service.getInstance().sendThongBao(player,"Hôm nay húp rồi, ngày mai quay lại nha con");
                            }
                        }
                        // chưa có thông tin nhận quà thì cho nhận luôn
                        else {
                            //Mỗi ngày nhận 100k hồng ngọc
                            item = ItemService.gI().createNewItem(itemID, quantity);
                            InventoryService.gI().addItemBag(player, item, 99999);
                            InventoryService.gI().sendItemBags(player);
                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                            player.playerReward = new PlayerReward();
                            player.playerReward.player_id = player.id;
                            player.playerReward.items = new ArrayList<>();
                            player.playerReward.items.add(new ItemRewardQuantity(itemID, quantity, currentTimestamp.getTime()));
                        }
                        break;
                    case 4:
                        createOtherMenu(player, ConstNpc.NHAN_DE_TU_MIEN_PHI, "Đệ tử nhận được cùng hệ với sư phụ", "Nhận đệ thường", "Nhận đệ tử Mabu mập");
                        break;
                    // ekko
//                    case 1:
//                        createOtherMenu(player, ConstNpc.QUY_DOI_DIEM_SKIEN,
//                                "Xin chào, tôi có 1 sự kiện đặc biệt bạn có muốn tham gia không?\n"
//                                        + "số điểm sự kiện bạn đã tích lũy là: ["
//                                        + player.diem_skien + "]",
//                                "Danh hiệu\n Hè cùng Pico\n[20 điểm]");
//
//                        break;
//                    case 2:
////                        if (!player.isAdmin()){
////                            this.npcChat(player, "ADMIN ĐANG TEST ĐỢI XÍU");
////                            return;
////                        }
//
//                        createOtherMenu(player, ConstNpc.QUY_DOI_DIEM_SKIEN2,
//                                "Đây là phần thưởng của bạn khi đã cống hiến rất nhiều cho Server ^^\n"
//                                        + "số máu bạn đã hiến là : ["
//                                        + player.getSession().tongnap + "]",
//                                "Nhận quà mốc[2tr]", "Nhận quà mốc[5tr]", "Nhận quà mốc[10tr]");
//
//                        break;

//                    case 1:
//                        if (player.inventory.gem < 1000) {
//                            this.npcChat(player, "Bạn không đủ 1k ngọc xanh để hỗ trợ nhiệm vụ");
//                            return;
//                        }
//                        if (player.playerTask.taskMain.id >= 22) {
//                            this.npcChat(player, "Nv khong duoc ho tro");
//                            return;
//                        }
//                        if (player.playerTask.taskMain.id == 20) {
//                            switch (player.playerTask.taskMain.index) {
//                                case 0:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_0);
//                                    break;
//                                case 1:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_1);
//                                    break;
//                                case 2:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_2);
//                                    break;
//                                case 3:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_3);
//                                    break;
//                                case 4:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_4);
//                                    break;
//                                case 5:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_20_5);
//                                    break;
//                                default:
//                                    Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
//                                    break;
//                            }
//                            player.inventory.subGem(1000);
//                            Service.getInstance().sendMoney(player);
//                            return;
//                        }
//                        if (player.playerTask.taskMain.id == 21) {
//                            switch (player.playerTask.taskMain.index) {
//                                case 0:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_21_0);
//                                    break;
//                                case 1:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_21_1);
//                                    break;
//                                case 2:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_21_2);
//                                    break;
//                                case 3:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_21_3);
//                                    break;
//                                case 4:
//                                    TaskService.gI().DoneTask(player, ConstTask.TASK_21_4);
//                                    break;
//                                default:
//                                    Service.getInstance().sendThongBao(player, "Ta đã giúp con hoàn thành nhiệm vụ rồi mau đi trả nhiệm vụ");
//                                    break;
//                            }
//                            player.inventory.subGem(1000);
//                            Service.getInstance().sendMoney(player);
//                            return;
//                        }
//                        TaskService.gI().supportTask(player);
//                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MAINTAIN) {
                switch (select) {
                    case 0:
                        if (player.maintain < 1) {
                            player.maintain++;
                            Service.getInstance().sendThongBaoOK(player, "Bạn đã duy trì thành công");
                        } else {
                            this.npcChat(player, "Tài khoản bạn đã được gia hạn");
                        }
                        break;
                    case 1:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_SHOW_TOP) {
                switch (select) {
                    case 0:
                        Service.ShowTopPower(player);
                        break;
                    case 1:
                        Service.ShowTopNap(player);
                        break;

                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.QUA_TAN_THU) {
                switch (select) {
                    case 0:
                        if (true) {
                            player.inventory.gem = 100000;
                            Service.getInstance().sendMoney(player);
                            Service.getInstance().sendThongBao(player,
                                    "Bạn vừa nhận được 100K ngọc xanh");
                            player.gift.gemTanThu = true;
                        } else {
                            this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con đã nhận phần quà này rồi mà", "Đóng");
                        }
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI_DIEM_SKIEN) {
                switch (select) {
                    case 0:
//                        if (InventoryService.gI().getCountEmptyBag(player) == 0) {
//                            Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
//                            return;
//                        }
//                        if (player.diem_skien < 20) {
                            this.npcChat(player, "Đóng");
//                            return;
//                        }
//                        player.diem_skien -= 20;
//                        Item dcc = ItemService.gI().createNewItem((short) 2104);
//                        dcc.itemOptions.add(new ItemOption(50, Util.nextInt(1, 15)));
//                        dcc.itemOptions.add(new ItemOption(77, Util.nextInt(1, 15)));
//                        dcc.itemOptions.add(new ItemOption(103, Util.nextInt(1, 15)));
//                        dcc.itemOptions.add(new ItemOption(244, Util.nextInt(1, 10)));
//                        InventoryService.gI().addItemBag(player, dcc, 1_500_000);
//                        InventoryService.gI().sendItemBags(player);
//                        this.npcChat(player, "Đã đổi thành công");
////                            this.npcChat(player, "Soon");
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.QUY_DOI_DIEM_SKIEN2) {
                switch (select) {
                    case 0:
                        if (player.getSession().tongnap < 2000000) {
                            this.npcChat(player, "Cần tích lũy đúng mốc nạp mới được nhận ");
                            return;
                        }
                        if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                            Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                            return;
                        }
                        if (player.data_tong_nap.get(0) == 0) {
                            Item qua1 = ItemService.gI().createNewItem((short) 2124);
                            InventoryService.gI().addItemBag(player, qua1, 1_500_000);
                            InventoryService.gI().sendItemBags(player);
                            this.npcChat(player, "Đã đổi thành công");
                            player.data_tong_nap.set(0, 1);
                        } else {
                            this.npcChat(player, "Đã đã nhận rồi");
                        }
                        break;
                    case 1:
                        if (player.getSession().tongnap < 5000000) {
                            this.npcChat(player, "Cần tích lũy đúng mốc nạp mới được nhận ");
                            return;
                        }
                        if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                            Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                            return;
                        }
                        if (player.data_tong_nap.get(1) == 0) {
                            Item qua2 = ItemService.gI().createNewItem((short) 2125);
                            InventoryService.gI().addItemBag(player, qua2, 1_500_000);
                            InventoryService.gI().sendItemBags(player);
                            this.npcChat(player, "Đã đổi thành công");
                            player.data_tong_nap.set(1, 1);
                        } else {
                            this.npcChat(player, "Đã đã nhận rồi");
                        }
                        break;
                    case 2:
                        if (player.getSession().tongnap < 10000000) {
                            this.npcChat(player, "Cần tích lũy đúng mốc nạp mới được nhận ");
                            return;
                        }
                        if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                            Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                            return;
                        }
                        if (player.data_tong_nap.get(2) == 0) {
                            Item qua3 = ItemService.gI().createNewItem((short) 2126);
                            InventoryService.gI().addItemBag(player, qua3, 1_500_000);
                            InventoryService.gI().sendItemBags(player);
                            this.npcChat(player, "Đã đổi thành công");
                            player.data_tong_nap.set(2, 1);
                        } else {
                            this.npcChat(player, "Đã đã nhận rồi");
                        }
                        break;
                }
            }
            // nhận đệ tử miễn phí
            else if (player.iDMark.getIndexMenu() == ConstNpc.NHAN_DE_TU_MIEN_PHI) {
                switch (select) {
                    case 0:
                        if (player.pet == null) {
                            PetService.gI().createNormalPet(player, player.gender);
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn có đệ tử rồi");
                        }
                        break;
                    case 1:
                        if (player.pet == null) {
                            PetService.gI().createMabuPet(player, player.gender);
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn có đệ tử rồi");
                        }
                        break;
                    default:
                        break;
                }
            }
            // Đổi quà tích lũy
//            else if (player.iDMark.getIndexMenu() == ConstNpc.DOI_QUA_TICH_LUY) {
//                // Trên 3 điểm thì mới được đổi quà tích lũy
//                if(player != null && player.getSession() != null) {
//                    switch (select) {
//                        // đổi 50 điểm
//                        case 0:
//
//                            if(player.getSession().poinCharging < 50) {
//                                Service.getInstance().sendThongBao(player, "Con không đủ 50 điểm tích lũy, hãy nạp thêm và quay lại gặp ta nhé!");
//                            } else {
//                                // trừ 50 điểm tích lũy
//                                PlayerDAO.subPoin(player, 50);
//                                int quantity = 1;
//                                Item ruong = ItemService.gI().createNewItem((short) ConstItem.RUONG_NGOC_HAC_AM, quantity);
//                                InventoryService.gI().addItemBag(player, ruong, 0);
//                                InventoryService.gI().sendItemBags(player);
//                                Service.getInstance().sendThongBao(player, "Con nhận được " + ruong.template.name + " số lượng " + quantity);
//                            }
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            }
        }
    }

    // Phương thức để phân tích chuỗi và tạo danh sách các đối tượng ItemQuantity
    public static List<ItemRewardQuantity> parseItemQuantities(String data) {
        List<ItemRewardQuantity> itemList = new ArrayList<>();

        // Loại bỏ dấu ngoặc vuông bao quanh chuỗi và dấu ngoặc kép
        data = data.replace("[\"", "").replace("\"]", "").replace("\",\"", ",");

        // Chia chuỗi thành các phần tử bằng dấu , (phân cách các cặp ID và số lượng)
        String[] pairs = data.split("\\],\\[");

        for (String pair : pairs) {
            // Loại bỏ dấu ngoặc vuông ở đầu và cuối của mỗi cặp
            pair = pair.replace("[", "").replace("]", "");

            // Chia từng phần tử thành ID và số lượng
            String[] parts = pair.split(",");
            int id = Integer.parseInt(parts[0].trim());
            int quantity = Integer.parseInt(parts[1].trim());
            long date_reward = Long.parseLong(parts[2].trim());

            // Tạo đối tượng ItemQuantity và thêm vào danh sách
            itemList.add(new ItemRewardQuantity(id, quantity, date_reward));
        }

        return itemList;
    }

    // Phương thức để phân tích chuỗi và tạo danh sách các đối tượng ItemQuantity
//    public static String parseItemRewardInfo(List<ItemRewardQuantity> lst) {
//        List<String> jsonList = new ArrayList<>();
//
//        lst.forEach(item -> {
//            // Tạo chuỗi JSON cho mỗi dòng dữ liệu
//            String jsonArray = "[" + item.getId() + "," + item.getQuantity() + "," + item.getLongDateReward() + "]";
//
//            // Thêm vào danh sách kết quả
//            jsonList.add(jsonArray);
//        });
//
//        // Tạo chuỗi JSON tổng hợp từ danh sách các chuỗi JSON
//        StringBuilder resultJsonBuilder = new StringBuilder();
//        resultJsonBuilder.append("[");
//
//        for (int i = 0; i < jsonList.size(); i++) {
//            resultJsonBuilder.append(jsonList.get(i));
//            if (i < jsonList.size() - 1) {
//                resultJsonBuilder.append(",");
//            }
//        }
//
//        resultJsonBuilder.append("]");
//        return resultJsonBuilder.toString();
//    }

    // Phương thức để chuyển đổi danh sách các đối tượng ItemQuantity thành định dạng chuỗi
    public static String convertToDataFormat(List<ItemRewardQuantity> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < items.size(); i++) {
            ItemRewardQuantity item = items.get(i);
            sb.append("\"[")
                    .append(item.getId())
                    .append(",")
                    .append(item.getQuantity())
                    .append(",")
                    .append(item.getLongDateReward())
                    .append("]\"");

            if (i < items.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }



}
