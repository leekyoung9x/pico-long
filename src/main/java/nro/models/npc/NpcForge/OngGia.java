package nro.models.npc.NpcForge;

import nro.consts.ConstAction;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.consts.ConstRatio;
import nro.manager.EventTurnManager;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossManager;
import nro.models.boss.list_boss.Shiba;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.utils.Util;

/**
 * @author Arriety
 */
public class OngGia extends Npc {

    private static final int POINT = 2000;

    public OngGia(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Ta đi lạc rồi giúp ta trở về nhé!", "Hộ tống", "Đổi quà\nSự kiện: \n" + player.pointShiba + " điểm");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {

        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
                    case 0: {
//                        this.createOtherMenu(player, ConstNpc.INDEX_MENU_ONG_GIA,
//                                "Ta không tin tưởng con sẽ đưa ta đi đúng đường\nNgươi phải cọc cống phẩm",
//                                "Free " + turn + " lượt\n1 ngày",
//                                "Hộ tống phí\n2k Ruby"
////                                "Hộ tống phí\nx99 bông hồng xanh"
//                        );
                        break;
                    }
                    case 1: {
//                        this.createOtherMenu(player, ConstNpc.INDEX_MENU_REWARD_ONG_GIA,
//                                "Con đang có: " + player.pointShiba + " điểm sự kiện \nCon muốn đổi gì nào?",
//                                "Pet chó Shiba\nCó tỉ lệ vv",
//                                "x1\nBông hoa cúc"
//                        );

                        if (player.pointShiba > 1) {
                            if (Util.isTrue(1, ConstRatio.PER100)) {
                                Item ct = ItemService.gI().createNewItem((short) 1276, 1);
                                ct.itemOptions.add(new ItemOption(50, 45));
                                ct.itemOptions.add(new ItemOption(77, 45));
                                ct.itemOptions.add(new ItemOption(103, 45));
                                if (Util.isTrue(30, ConstRatio.PER100)) {
                                    ct.itemOptions.add(new ItemOption(108, 1));
                                }
                                InventoryService.gI().addItemBag(player, ct, 1);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendThongBao(player, "Bạn nhận được " + ct.template.name);
                            } else {

                                if (Util.isTrue(60, 99)) {
                                    int rate = Util.nextInt(1, 3);

                                    switch (rate) {
                                        case 1: {
                                            int ruby = Util.nextInt(100, 3000);
                                            int oldRuby = player.inventory.ruby;
                                            player.inventory.addRuby(ruby);
                                            // ekko ghi log add ruby
                                            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "OngGia-confirmMenu");
                                            Service.getInstance().sendMoney(player);
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + ruby + " hồng ngọc");
                                            break;
                                        }
                                        case 2: {
                                            Item item = ItemService.gI().createNewItem((short) 20, 1);
                                            InventoryService.gI().addItemBag(player, item, 999);
                                            InventoryService.gI().sendItemBags(player);
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name);
                                            break;
                                        }
                                        case 3: {
                                            int nr = Util.nextInt(1982, 1988);

                                            Item item = ItemService.gI().createNewItem((short) nr, 1);
                                            InventoryService.gI().addItemBag(player, item, 999);
                                            InventoryService.gI().sendItemBags(player);
                                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name);
                                            break;
                                        }
                                        default:
                                    }
                                } else {
                                    // 459
                                    Item pgg = ItemService.gI().createNewItem((short) 459, 1);
                                    InventoryService.gI().addItemBag(player, pgg, 999);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn nhận được " + pgg.template.name);
                                }
                            }

                            player.pointShiba -= 1;
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn không đủ điểm để đổi");
                        }

                        break;
                    }
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_ONG_GIA) {
                switch (select) {
                    case 0: {
//                        if (turn <= 0) {
//                            this.npcChat(player, "Bạn đã hết lượt free!");
//                        } else {
//                            try {
//                                EventTurnManager.ManageEventShiba(ConstAction.UPDATE_BY_ID, player.id);
//                                CallShiba(player);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                this.npcChat(player, "Có lỗi xảy ra vui lòng liên hệ Péo Ngu Học!");
//                            }
//                        }
                        break;
                    }
                    case 1: {
                        try {
                            callShibaPetRuby(player);
                        } catch (Exception e) {
                            e.printStackTrace();
                            this.npcChat(player, "Có lỗi xảy ra vui lòng liên hệ Péo Ngu Học!");
                        }
                        break;
                    }
                    case 2: {
                        callShibaPetWithGreenRose(player);
                        break;
                    }
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_REWARD_ONG_GIA) {
                switch (select) {
                    case 0: {
                        if (player.pointShiba > 1) {
                            Item shiba = ItemService.gI().createNewItem((short) 2057, 1);
                            shiba.itemOptions.add(new ItemOption(50, 22));
                            shiba.itemOptions.add(new ItemOption(77, 22));
                            shiba.itemOptions.add(new ItemOption(103, 22));
                            if (Util.isTrue(90, 100)) {
                                shiba.itemOptions.add(new ItemOption(93, Util.nextInt(1, 3)));
                            } else {
                                shiba.itemOptions.add(new ItemOption(74, 0));
                            }
                            player.pointShiba -= 1;
                            InventoryService.gI().addItemBag(player, shiba, 999);
                            InventoryService.gI().sendItemBags(player);
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn không đủ điểm để đổi");
                        }

                        break;
                    }
                    case 1: {
                        if (player.pointShiba > 1) {
                            Item hoa = ItemService.gI().createNewItem((short) 2063, 1);
                            player.pointShiba -= 1;
                            InventoryService.gI().addItemBag(player, hoa, 999);
                            InventoryService.gI().sendItemBags(player);
                        } else {
                            Service.getInstance().sendThongBao(player, "Bạn không đủ điểm để đổi");
                        }
                        break;
                    }

                }
            }
        }
    }

    private void callShibaPetWithGreenRose(Player player) {
        int quantity = 500;

        Item hoahong = InventoryService.gI().findItemBagByTemp(player, 1098);
        Boss oldShiba = BossManager.gI().getBossById(Util.createIdShiba((int) player.id));
        if (oldShiba != null) {
            this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldShiba.zone.zoneId);
        } else if (hoahong != null && hoahong.quantity >= quantity) {
            InventoryService.gI().subQuantityItemsBag(player, hoahong, quantity);
            CallShiba(player);
        } else {
            this.npcChat(player, "Thiếu hoa hồng hoặc x" + quantity + " hoa hồng");
        }
        InventoryService.gI().sendItemBags(player);
    }

    private void callShibaPetRuby(Player player) {
        if (player.inventory.ruby < POINT) {
            this.npcChat(player, "Con thiếu 2k Ruby");
            return;
        }
        Boss oldShiba = BossManager.gI().getBossById(Util.createIdShiba((int) player.id));
        if (oldShiba != null) {
            this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước gọi ra đã, con boss đó đang ở khu " + oldShiba.zone.zoneId);
        } else {
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(-2000);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "OngGia-callShibaPetRuby");
            CallShiba(player);
        }
        Service.getInstance().sendMoney(player);
    }

    private static void CallShiba(Player player) {
        BossData bossDataClone = new BossData(
                "Lý Tiểu Nương", //name
                ConstPlayer.XAYDA, //gender
                Boss.DAME_NORMAL, //type dame
                Boss.HP_NORMAL, //type hp
                500_000, //dame
                new long[][]{{19_062_006}}, //hp
//                new short[]{1314, 1315, 1316}, //outfit
                new short[]{306, 307, 308}, //outfit
                new short[]{144},
                new int[][]{},
                60
        );
        try {
            Shiba shiba = new Shiba(Util.createIdShiba((int) player.id), bossDataClone, player.zone, player.location.x - 20, player.location.y);
            shiba.zoneFinal = player.zone;
            shiba.playerTarger = player;
            player.haveShiba = true;
            shiba.joinMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
