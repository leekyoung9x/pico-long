/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstCombine;
import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.manager.ChuyenKhoanManager;
import nro.models.Transaction;
import nro.models.clan.Clan;
import nro.models.clan.ClanMember;
import nro.models.map.phoban.BanDoKhoBau;
import nro.models.npc.Npc;

import static nro.models.npc.NpcFactory.PLAYERID_OBJECT;

import nro.models.player.Player;
import nro.server.ServerManager;
import nro.services.*;
import nro.services.func.ChangeMapService;
import nro.services.func.CombineServiceNew;
import nro.services.func.Input;
import nro.utils.TimeUtil;
import nro.utils.Util;

import java.awt.*;
import java.net.URI;
import java.time.LocalDateTime;

import nro.jdbc.daos.PlayerDAO;
import nro.models.boss.Boss;
import nro.models.boss.BossManager;
import nro.models.item.Item;
import nro.models.item.ItemOption;


/**
 * @author Arriety
 */
public class QuyLaoKame extends Npc {
    public QuyLaoKame(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                // ekko
//                this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                        "Chào con, con muốn ta giúp gì nào?",
//                        "Nói chuyện", "Tiến đến\n thung lũng đá ", "Tiến tới\nĐịa ngục", "Từ chối");
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Chào con, con muốn ta giúp gì nào?",
                        "Nói chuyện", "Phân rã\nSKH VIP", "Tuần lộc ở đâu ?", "Chế tạo SKH", "Từ chối");
//                this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                        "Chào con, con muốn ta giúp gì nào?",
//                        "Nói chuyện", "Từ chối");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
                    case 0:
                        // ekko
//                        this.createOtherMenu(player, ConstNpc.NOI_CHUYEN,
//                                "Chào con, ta rất vui khi gặp con\nCon muốn làm gì nào?",
//                                "Nhiệm vụ", "Về khu\nvực bang",
//                                "Giản tán\nBang hội", "Kho báu\ndưới biển", "Reset BDKB", "Nâng cấp cải trang", "Tẩy sao \npha lê");
//                        break;
//                        this.createOtherMenu(player, ConstNpc.NOI_CHUYEN,
//                                "Chào con, ta rất vui khi gặp con\nCon muốn làm gì nào?",
//                                "Nhiệm vụ",
//                                "Giản tán\nBang hội",
//                                "Kho báu\ndưới biển",
//                                "Reset BDKB",
//                                "Vào Lãnh địa bang hội",
//                                "Mở giới hạn bang hội"
//                        );
                        this.createOtherMenu(player, ConstNpc.NOI_CHUYEN,
                                "Chào con, ta rất vui khi gặp con\nCon muốn làm gì nào?",
                                "Nhiệm vụ",
                                "Giản tán\nBang hội",
                                "Kho báu\ndưới biển",
                                "Reset BDKB"
                        );
                        break;
                    // ekko
                    case 1:
                        this.createOtherMenu(player, ConstNpc.PHAN_RA_SKH_VIP,
                                "Con có muốn phân rã SKH VIP không ?\nVới 1 hồng ngọc cùng 1 món SKH VIP bất kỳ sau khi phân rã con sẽ nhận được x1 \"Đá ngũ sắc\" đó.", "Đồng ý", "Từ chối");
                        break;
                    case 2:
                        BossManager.gI().showListBossTuanLoc(player);
                        break;
                    case 3:
                        this.createOtherMenu(player, ConstNpc.NANG_CAP_SKH,
                                "Con có muốn nâng cấp SKH không ?\nVí dụ 1: với 2 áo thần linh, x10 đá thần linh sau khi nâng cấp con sẽ nhận được x1 \"Áo vải thô kích hoạt (Random SKH)\" đó.\n Ví dụ 2: Ví dụ từ 2 áo vải thô set Nappa và x10 \"Đá thần linh\" sẽ lên 1 áo thun thô set Nappa.", "Đồng ý", "Từ chối");
                        break;
//                    case 1:
//                        ChangeMapService.gI().changeMap(player, 208, -1, 705, 432);

////                        this.npcChat(player, "Coming soon");
//                        Item[] items = new Item[]{
//                                InventoryService.gI().findItemBagByTemp(player, 695),
//                                InventoryService.gI().findItemBagByTemp(player, 696),
//                                InventoryService.gI().findItemBagByTemp(player, 697),
//                                InventoryService.gI().findItemBagByTemp(player, 698),
//                        };
//
//                        boolean allItemsSufficient = true;
//
//// Kiểm tra xem tất cả các vật phẩm có đủ số lượng x99 không
//                        for (Item item : items) {
//                            if (item == null || item.quantity < 99) {
//                                allItemsSufficient = false;
//                                break;
//                            }
//                        }
//
//                        if (allItemsSufficient) {
//                            // Trừ số lượng của từng vật phẩm đi 99
//                            for (Item item : items) {
//                                InventoryService.gI().subQuantityItemsBag(player, item, 99);
//                            }
//
//                            // Tạo và thêm vật phẩm mới vào túi đồ của người chơi
//                            Item dcc2 = ItemService.gI().createNewItem((short) 2106, 1);
//                            dcc2.itemOptions.add(new ItemOption(30, 1));
//                            InventoryService.gI().addItemBag(player, dcc2, 1_500_000);
//
//                            // Gửi cập nhật túi đồ cho người chơi
//                            InventoryService.gI().sendItemBags(player);
//                            this.npcChat(player, "Đổi thành công hộp quà hè");
//                            InventoryService.gI().sendItemBags(player);
//                        } else {
//                            Service.getInstance().sendThongBao(player, "Bạn không đủ x4 x99 vật phẩm sự kiện");
//                        }
//                        break;

//                    case 1: {
//                        if (player != null && player.haveShiba) {
//                            int idShiba = Util.createIdShiba((int) player.id);
//
//                            if (idShiba != 0) {
//                                Boss boss = BossManager.gI().getBossById(idShiba);
//
//                                if (boss != null
//                                        && boss.zone.map.mapId == player.zone.map.mapId
//                                        && boss.zone.zoneId == player.zone.zoneId
//                                        && Util.getDistance(player, boss) <= 300) {
//                                    boss.leaveMap();
//                                    BossManager.gI().removeBoss(boss);
//                                    player.haveShiba = false;
//                                    Service.getInstance().sendThongBao(player, "Cảm ơn con đã giúp ta tìm Lý tiểu nương");
//                                    player.pointShiba++;
//                                    player.diem_skien++;
//                                } else {
//                                    Service.getInstance().sendThongBao(player, "Lý Tiểu Nương đâu");
//                                }
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(player, "Lý Tiểu Nương đâu");
//                        }
//                    }
//                    break;
//                    case 2:
//
//                            this.createOtherMenu(player, ConstNpc.INDEX_MENU_QUY_NAP, "Khà khà khà, chào con con có muốn quy đổi gì không?\n"
//                                    + "Số quỹ nạp tích lũy của bạn hiện tại là: [" + player.getSession().poinCharging + "]",
//                                    "1 túi\n[10 fund]",
//                                    "10 túi\n[100 fund]");
//
//                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.NOI_CHUYEN) {
                // ekko
//                switch (select) {
//                    case 0:
//                        NpcService.gI().createTutorial(player, 564, "Nhiệm vụ tiếp theo của bạn là: "
//                                + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
//                        break;
//
//                    case 1:
//                        if (player.clan == null) {
//                            Service.getInstance().sendThongBao(player, "Chưa có bang hội");
//                            return;
//                        }
//                        ChangeMapService.gI().changeMap(player, player.clan.getClanArea(), 910,
//                                190);
//                        break;
//                    case 2:
//                        Clan clan = player.clan;
//                        if (clan != null) {
//                            ClanMember cm = clan.getClanMember((int) player.id);
//                            if (cm != null) {
//                                if (clan.members.size() > 1) {
//                                    Service.getInstance().sendThongBao(player, "Bang phải còn một người");
//                                    break;
//                                }
//                                if (!clan.isLeader(player)) {
//                                    Service.getInstance().sendThongBao(player, "Phải là bảng chủ");
//                                    break;
//                                }
//                                NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1,
//                                        "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
//                                        "Yes you do!", "Từ chối!");
//                            }
//                            break;
//                        }
//                        Service.getInstance().sendThongBao(player, "Có bang hội đâu ba!!!");
//                        break;
//                    case 3:
//                        Clan c = player.clan;
//                        if (c != null) {
//                            ClanMember clanMember = player.clan.getClanMember((int) player.id);
//                            int days = (int) (((System.currentTimeMillis() / 1000) - clanMember.joinTime) / 60 / 60 / 24);
//                            if (days < 0) {
//                                NpcService.gI().createTutorial(player, avartar,
//                                        "Chỉ những thành viên gia nhập bang hội tối thiểu 1 ngày mới có thể tham gia");
//                                return;
//                            }
//
//                            if (player.clan != null) {
//                                if (player.clan.banDoKhoBau != null) {
//                                    this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
//                                            "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
//                                            + player.clan.banDoKhoBau.level
//                                            + "\nCon có muốn đi theo không?",
//                                            "Đồng ý", "Từ chối");
//                                } else {
//                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
//                                            "Đây là bản đồ kho báu hải tặc tí hon\nCác con cứ yên tâm lên đường\n"
//                                            + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
//                                            "Chọn\ncấp độ", "Từ chối");
//                                }
//                            } else {
//                                NpcService.gI().createTutorial(player, 564, "Con phải có bang hội ta mới có thể cho con đi");
//                            }
//                        }
//                        break;
//                    case 4:
//                        if (player.clan != null) {
//                            player.clan.banDoKhoBau = null;
//                            ItemTimeService.gI().removeTextBDKB(player);
//                        } else {
//                            NpcService.gI().createTutorial(player, 564, "bang hội của con đâu");
//                        }
//                        break;
//                    case 5:
//                        this.createOtherMenu(player, ConstItem.LINH_HON_CAI_TRANG,
//                                "Ngươi muốn nâng cấp cải trang không ? Cần x99 linh hồn cải trang, 1 cải trang và 50k ngọc", "Có", "Từ chối");
//                        break;
//                    case 6:
//                        this.createOtherMenu(player, ConstCombine.REMOVE_OPTION,
//                                "Ngươi có muốn tẩy sao pha lê cho trang bị", "Có", "Từ chối");
//                        break;
//                }
                switch (select) {
                    case 0:
                        NpcService.gI().createTutorial(player, 564, "Nhiệm vụ tiếp theo của bạn là: "
                                + player.playerTask.taskMain.subTasks.get(player.playerTask.taskMain.index).name);
                        break;

//                    case 1:
//                        if (player.clan == null) {
//                            Service.getInstance().sendThongBao(player, "Chưa có bang hội");
//                            return;
//                        }
//                        ChangeMapService.gI().changeMap(player, player.clan.getClanArea(), 910,
//                                190);
//                        break;
                    case 1:
                        Clan clan = player.clan;
                        if (clan != null) {
                            ClanMember cm = clan.getClanMember((int) player.id);
                            if (cm != null) {
                                if (clan.members.size() > 1) {
                                    Service.getInstance().sendThongBao(player, "Bang phải còn một người");
                                    break;
                                }
                                if (!clan.isLeader(player)) {
                                    Service.getInstance().sendThongBao(player, "Phải là bảng chủ");
                                    break;
                                }
                                NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_DISSOLUTION_CLAN, -1,
                                        "Con có chắc chắn muốn giải tán bang hội không? Ta cho con 2 lựa chọn...",
                                        "Yes you do!", "Từ chối!");
                            }
                            break;
                        }
                        Service.getInstance().sendThongBao(player, "Có bang hội đâu ba!!!");
                        break;
                    case 2: {
                        NpcService.gI().createTutorial(player, avartar,
                                "Bảo trì (Tạm đóng sau update sau)");
//                        Clan c = player.clan;
//                        if (c != null) {
//                            ClanMember clanMember = player.clan.getClanMember((int) player.id);
//                            int days = (int) (((System.currentTimeMillis() / 1000) - clanMember.joinTime) / 60 / 60 / 24);
//                            if (days < 0) {
//                                NpcService.gI().createTutorial(player, avartar,
//                                        "Chỉ những thành viên gia nhập bang hội tối thiểu 1 ngày mới có thể tham gia");
//                                return;
//                            }
//
//                            if (player.clan != null) {
//
//                                if (player.clan.banDoKhoBau != null) {
//                                    this.createOtherMenu(player, ConstNpc.MENU_OPENED_DBKB,
//                                            "Bang hội của con đang đi tìm kho báu dưới biển cấp độ "
//                                                    + player.clan.banDoKhoBau.level
//                                                    + "\nCon có muốn đi theo không?",
//                                            "Đồng ý", "Từ chối");
//                                } else {
//                                    this.createOtherMenu(player, ConstNpc.MENU_OPEN_DBKB,
//                                            "Đây là bản đồ kho báu hải tặc tí hon\nCác con cứ yên tâm lên đường\n"
//                                                    + "Ở đây có ta lo\nNhớ chọn cấp độ vừa sức mình nhé",
//                                            "Chọn\ncấp độ", "Từ chối");
//                                }
//                            } else {
//                                NpcService.gI().createTutorial(player, 564, "Con phải có bang hội ta mới có thể cho con đi");
//                            }
//                        }
                        break;
                    }
                    case 3:
                        if (player.clan != null) {
                            player.clan.banDoKhoBau = null;
                            ItemTimeService.gI().removeTextBDKB(player);
                        } else {
                            NpcService.gI().createTutorial(player, 564, "bang hội của con đâu");
                        }
                        break;
//                    case 4: {
//                        if (player.clan == null) {
//                            Service.getInstance().sendThongBao(player, "Chưa có bang hội");
//                            return;
//                        }
//                        ChangeMapService.gI().changeMap(player, player.clan.getClanArea(), 910,
//                                190);
//                        break;
//                    }
//                    case 5: {
//                        if (player.clan == null) {
//                            Service.getInstance().sendThongBao(player, "Chưa có bang hội");
//                            return;
//                        }
//
//                        Clan clanNe = player.clan;
//                        ClanMember cm = player.clanMember;
//                        if (cm == null) {
//                            this.npcChat(player, "@@");
//                            return;
//                        }
//
//                        if (cm.role == Clan.LEADER) {
//                            Item item = InventoryService.gI().findItemBagByTemp(player, ConstItem.CAPSULE_BANG_HOI);
//
//                            if (clanNe.maxMember >= 20) {
//                                Service.getInstance().sendThongBao(player, "Bang hội chỉ có thể mở tối đa 20 thành viên");
//                                return;
//                            }
//
//                            if (item == null || item.quantity < 99) {
//                                Service.getInstance().sendThongBao(player, "Bạn không có đủ 99 Capsule bang hội");
//                                return;
//                            }
//
//                            boolean isSuccess = Util.isTrue(30, 100);
//
//                            if (isSuccess) {
//                                clanNe.level++;
//                                clanNe.maxMember++;
//                                ClanService.gI().sendMyClan(player);
//                                Service.getInstance().sendThongBao(player, "Nâng cấp bang hội thành công");
//                            } else {
//                                Service.getInstance().sendThongBao(player, "Con xui như chó vậy, hẹn lần sau");
//                            }
//
//                            InventoryService.gI().subQuantityItemsBag(player, item, 99);
//                            InventoryService.gI().sendItemBags(player);
//                        } else {
//                            Service.getInstance().sendThongBao(player, "Chỉ bang chủ mới có thể mở giới hạn bang hội");
//                            return;
//                        }
//                        break;
//                    }
//                    case 5:
//                        this.createOtherMenu(player, ConstItem.LINH_HON_CAI_TRANG,
//                                "Ngươi muốn nâng cấp cải trang không ? Cần x99 linh hồn cải trang, 1 cải trang và 50k ngọc", "Có", "Từ chối");
//                        break;
//                    case 6:
//                        this.createOtherMenu(player, ConstCombine.REMOVE_OPTION,
//                                "Ngươi có muốn tẩy sao pha lê cho trang bị", "Có", "Từ chối");
//                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstCombine.REMOVE_OPTION) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                ConstCombine.REMOVE_OPTION);
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstItem.LINH_HON_CAI_TRANG) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.NANG_CAP_CAI_TRANG);
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                switch (player.combineNew.typeCombine) {
                    case CombineServiceNew.NANG_CAP_CAI_TRANG:
                    case ConstCombine.REMOVE_OPTION:
                    case CombineServiceNew.NANG_CAP_SKH:
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                        break;
                    case CombineServiceNew.PHAN_RA_SKH_VIP:
                        if (select == 0) {
                            Item skhVIPChiSoAn = null;
                            int slSKHVIP = 1;
                            int slRuby = 1;
                            for (Item item : player.combineNew.itemsCombine) {
                                if (item.isNotNullItem()) {
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 249) {
                                            skhVIPChiSoAn = item;
                                        }
                                    }
                                }
                            }
                            InventoryService.gI().subQuantityItemsBag(player, skhVIPChiSoAn, slSKHVIP);
                            player.inventory.addRuby(-slRuby);
                            Item item = ItemService.gI().createNewItem((short) ConstItem.DA_NGU_SAC, 1);
                            InventoryService.gI().addItemBag(player, item, 0);
                            InventoryService.gI().sendItemBags(player);
                            InventoryService.gI().sendItemBody(player);
                            Service.getInstance().sendMoney(player);
                            CombineServiceNew.gI().reOpenItemCombine(player);
                            Service.getInstance().sendThongBao(player, "Phân rã thành công, bạn nhận được x1 Đá ngũ sắc");
                        }
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPENED_DBKB) {
                switch (select) {
                    case 0:

                        if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                            ChangeMapService.gI().goToDBKB(player);
                        } else {
                            this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                    + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                        }
                        break;

                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPEN_DBKB) {
                switch (select) {
                    case 0:
                        if (player.isAdmin() || player.nPoint.power >= BanDoKhoBau.POWER_CAN_GO_TO_DBKB) {
                            Input.gI().createFormChooseLevelBDKB(player);
                        } else {
                            this.npcChat(player, "Sức mạnh của con phải ít nhất phải đạt "
                                    + Util.numberToMoney(BanDoKhoBau.POWER_CAN_GO_TO_DBKB));
                        }
                        break;
                }

            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_BDKB) {
                switch (select) {
                    case 0:
                        BanDoKhoBauService.gI().openBanDoKhoBau(player,
                                Byte.parseByte(String.valueOf(PLAYERID_OBJECT.get(player.id))));
                        break;
                }

            } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_QUY_NAP) {
                switch (select) {
                    case 0:
                        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                            if (player.getSession().poinCharging >= 10) {
                                if (PlayerDAO.subPoin(player, 10)) {
                                    Item pet = ItemService.gI().createNewItem((short) 2067);
//                                    pet.quantity = 12;
                                    InventoryService.gI().addItemBag(player, pet, 0);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn đã nhận được x1 Túi Skien Tháng Bar");
                                } else {
                                    this.npcChat(player, "Lỗi vui lòng báo admin...");
                                }
                            } else {
                                Service.getInstance().sendThongBao(player, "Số dư poin không đủ vui lòng nạp thêm ");
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                        }
                        break;
                    case 1:
                        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                            if (player.getSession().poinCharging >= 100) {
                                if (PlayerDAO.subPoin(player, 100)) {
                                    Item pet = ItemService.gI().createNewItem((short) 2067);
                                    pet.quantity = 12;
                                    InventoryService.gI().addItemBag(player, pet, 0);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn đã nhận được x10 Túi Skien Tháng Bar");
                                } else {
                                    this.npcChat(player, "Lỗi vui lòng báo admin...");
                                }
                            } else {
                                Service.getInstance().sendThongBao(player, "Số dư Quy Nạp không đủ vui lòng nạp thêm ");
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
                        }
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.PHAN_RA_SKH_VIP) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.PHAN_RA_SKH_VIP);
                        break;
                    case 1:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.NANG_CAP_SKH) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.NANG_CAP_SKH);
                        break;
                    case 1:
                        break;
                }
            }
        }
    }

    private static void openLink(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();

            // Kiểm tra xem máy tính có hỗ trợ mở liên kết không
            if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(new URI(url));
            } else {
                // Nếu không hỗ trợ, bạn có thể xử lý tùy ý ở đây
                System.out.println("Không thể mở liên kết trên thiết bị này.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
