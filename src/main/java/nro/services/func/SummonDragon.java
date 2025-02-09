package nro.services.func;

import nro.consts.*;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.Zone;
import nro.models.player.Player;
import nro.server.Manager;
import nro.server.io.Message;
import nro.services.*;
import nro.utils.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nro.utils.SkillUtil;

/**
 *
 * @Build Arriety
 *
 */
public class SummonDragon {

    public static final byte WISHED = 0;
    public static final byte TIME_UP = 1;

    public static final byte DRAGON_SHENRON = 0;
    public static final byte DRAGON_PORUNGA = 1;
    public static final byte DRAGON_BLACK_SHENRON = 2;
    public static final byte DRAGON_ICE_SHENRON = 3;
    public static final byte DRAGON_NOEL = 4;

    public static final short NGOC_RONG_1_SAO = 14;
    public static final short NGOC_RONG_2_SAO = 15;
    public static final short NGOC_RONG_3_SAO = 16;
    public static final short NGOC_RONG_4_SAO = 17;
    public static final short NGOC_RONG_5_SAO = 18;
    public static final short NGOC_RONG_6_SAO = 19;
    public static final short NGOC_RONG_7_SAO = 20;

//    public static final short[] NGOC_RONG_BANG = {1982, 1983, 1984, 1985, 1986, 1987, 1988};//
    public static final short[] NGOC_RONG_SIEU_CAP = {2045, 2046, 2047, 2048, 2049, 2050, 2051};//
    public static final short[] NGOC_RONG_NOEL = {2186, 2187, 2188, 2189, 2190, 2191, 2192};
    public static final short[] NGOC_RONG_HALLOWEEN = {702, 703, 704, 705, 706, 707, 708};
    public static final short[] NGOC_RONG_NAMEK = {353, 354, 355, 356, 357, 358, 359};
    public static final short NGOC_RONG_SIEU_CAP_FAKE = 1015;
    public static final short NGOC_RONG_NOEL_1_SAO = 2186;
    public static final String SUMMON_SHENRON_TUTORIAL
            = "Có 3 cách gọi rồng thần. Gọi từ ngọc 1 sao, gọi từ ngọc 2 sao, hoặc gọi từ ngọc 3 sao\n"
            + "Các ngọc 4 sao đến 7 sao không thể gọi rồng thần được\n"
            + "Để gọi rồng 1 sao cần ngọc từ 1 sao đến 7 sao\n"
            + "Để gọi rồng 2 sao cần ngọc từ 2 sao đến 7 sao\n"
            + "Để gọi rồng 3 sao cần ngọc từ 3 sao đến 7sao\n"
            + "Điều ước rồng 3 sao: Capsule 3 sao, hoặc 2 triệu sức mạnh, hoặc 200k vàng\n"
            + "Điều ước rồng 2 sao: Capsule 2 sao, hoặc 20 triệu sức mạnh, hoặc 2 triệu vàng\n"
            + "Điều ước rồng 1 sao: Capsule 1 sao, hoặc 200 triệu sức mạnh, hoặc 20 triệu vàng, hoặc đẹp trai, hoặc....\n"
            + "Ngọc rồng sẽ mất ngay khi gọi rồng dù bạn có ước hay không\n"
            + "Quá 5 phút nếu không ước rồng thần sẽ bay mất";
    public static final String SHENRON_SAY
            = "Ta sẽ ban cho người 1 điều ước, ngươi có 5 phút,"
            + " hãy suy nghĩ thật kỹ trước khi quyết định";

    public static final String[] SHENRON_1_STAR_WISHES_1
            = new String[]{"Giàu có\n+2 Tỏi\nVàng", "Găng tay\nđang mang\nlên 1 cấp", "Chí mạng\nGốc +2%",
                "Thay\nChiêu 2-3\nĐệ tử", "Điều ước\nkhác"};
//    public static final String[] SHENRON_1_STAR_WISHES_2
//            = new String[]{"Đẹp trai\nnhất\nVũ trụ", "Nâng cấp \nbông tai cấp 4", "Giàu có\n+2tr\nHồng ngọc", "+200 Tr\nSức mạnh\nvà tiềm\nnăng",
//                "Găng tay đệ\nđang mang\nlên 1 cấp",
//                "Điều ước\nkhác"};
    public static final String[] SHENRON_1_STAR_WISHES_2
            = new String[]{"Đẹp trai\nnhất\nVũ trụ", "Giàu có\n+2tr\nHồng ngọc", "+200 Tr\nSức mạnh\nvà tiềm\nnăng",
            "Găng tay đệ\nđang mang\nlên 1 cấp",
            "Điều ước\nkhác"};
    public static final String[] SHENRON_1_STAR_WISHES_3
            = new String[]{"Nâng cấp \nbông tai\ncấp 4", "Găng tay\nđang mang\nlên 1 cấp", "Nâng quần\nđang mang\nlên cấp 8",
            "Nâng găng\nđang mang\nlên cấp 8", "Nâng giày\nđang mang\nlên cấp 8"};
    public static final String[] SHENRON_2_STARS_WHISHES
            = new String[]{"Giàu có\n+5K\nNgọc hồng", "+20 Tr\nSức mạnh\nvà tiềm năng", "Giàu có\n+200 Tr\nVàng"};
    public static final String[] SHENRON_3_STARS_WHISHES
            = new String[]{"Giàu có\n+5K\nNgọc hồng", "+2 Tr\nSức mạnh\nvà tiềm năng", "Giàu có\n+20 Tr\nVàng"};
    //--------------------------------------------------------------------------
    public static final String BLACK_SHENRON_SAY
            = "Ta sẽ ban cho người 1 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định"
            + "\n 1) Cải trang Gohan Siêu Nhân (Chỉ số ngẫu nhiên 60 Ngày) "
            + "\n 2) Cải trang Biden Siêu Nhân (Chỉ số ngẫu nhiên 60 Ngày) "
            + "\n 3) Cải trang Cô nương Siêu Nhân (Chỉ số ngẫu nhiên 60 Ngày) "
            + "\n 4) Pet Thỏ Ốm (Chỉ số ngẫu nhiên 60 Ngày) "
            + "\n 5) Pet Thỏ Mập (Chỉ số ngẫu nhiên 60 Ngày) ";

    public static final String[] BLACK_SHENRON_WISHES
            = new String[]{"Điều\nước 1", "Điều\nước 2", "Điều\nước 3", "Điều\nước 4", "Điều\nước 5"};
    //-----------------------------------------------------------------------------
    // ekko
//    public static final String ICE_SHENRON_SAY
//            = "Ta sẽ ban cho người 6 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định"
//            + "\n 1) x2 TNSM sư phụ trong vòng 1 giờ"
//            + "\n 2) + 100tr TNSM cho sư phụ"
//            + "\n 3) + 100tr TNSM cho đệ tử"
//            + "\n 4) + 1% tỉ lệ chí mạng cho đệ tử"
//            + "\n 5) Đổi skill 2 đệ tử thành Kamejoko"
//            + "\n 6) Đổi skill 1 đệ tử thành đấm Liên hoàn"
//            + "\n 7) Nâng cấp skill 5 đệ tử lên 1 cấp"
//            + "\n 8) Nâng cấp quần đang mặc lên 1 cấp"
//            + "\n 9) Nâng cấp nâng giày đang mặc lên 1 cấp";

    public static final String ICE_SHENRON_SAY
            = "Ta sẽ ban cho người 6 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định"
             + "\n 1) x3 TNSM sư phụ trong vòng 1 giờ"
            + "\n 2) + 100tr TNSM cho sư phụ"
            + "\n 3) + 100tr TNSM cho đệ tử"
            + "\n 4) + 1% tỉ lệ chí mạng cho đệ tử"
            + "\n 5) Đổi skill 2 đệ tử thành Kamejoko"
            + "\n 6) Đổi skill 1 đệ tử thành đấm Liên hoàn"
            + "\n 7) Nâng cấp skill 5 đệ tử lên 1 cấp"
            + "\n 8) Nâng cấp quần đang mặc lên 1 cấp"
            + "\n 9) Nâng cấp nâng giày đang mặc lên 1 cấp";
        // ekko bỏ tạm 4 điều ước
    public static final String[] ICE_SHENRON_WISHES = new String[]{"Điều\nước 1",
        "Điều\nước 2", "Điều\nước 3", "Điều\nước 4", "Điều\nước 5", "Điều\nước 6", "Điều\nước 7", "Điều\nước 8", "Điều\nước 9"};
//        public static final String[] ICE_SHENRON_WISHES = new String[]{"Điều\nước 1",
//        "Điều\nước 2", "Điều\nước 3", "Điều\nước 4", "Điều\nước 5"};

    public static final String NOEL_SAY = "Ta sẽ ban cho người 6 điều ước, ngươi có 5 phút, hãy suy nghĩ thật kỹ trước khi quyết định"
            + "\n 1) Mạnh nhất vũ trụ (Tăng 10% chỉ số Sức đánh,Hp,Ki trong 10 phút)"
            + "\n 2) Đẹp trai nhất vũ trụ"
            + "\n 3) Sở hữu đệ tử Whis"
            + "\n 4) Đệ tử Zeno Tăng thêm 1 Tỷ sức mạnh"
            + "\n 5) Sở hữu đệ Zeno"
            + "\n 6) Đệ tử Goku Tăng thêm 1 Tỷ sức mạnh"
            ;

    public static final String[] NOEL_SHENRON_WISHES = new String[]{"Điều\nước 1",
            "Điều\nước 2", "Điều\nước 3", "Điều\nước 4", "Điều\nước 5", "Điều\nước 6"};

    private static SummonDragon instance;
    private final Map pl_dragonStar;
    private long lastTimeShenronAppeared;
    private long lastTimeShenronWait;
    private final int timeResummonShenron = 30_000;
    // ekko thời gian hồi sinh rồng siêu cấp
    private long lastTimeBlackShenronAppeared;
    // thời gian hồi sinh rồng siêu cấp là 1 phút
    private final int timeResummonBlackShenron = 60_000;
    // ekko thời gian hồi sinh rồng xương
    private long lastTimeNoelShenronAppeared;
    // thời gian hồi sinh rồng xương là 30s
    private final int timeResummonNoelShenron = 30_000;

    // ekko thời gian hồi sinh rồng siêu cấp theo 7 viên siêu cấp
    private long lastTimeIceShenronAppeared;
    // thời gian hồi sinh rồng siêu cấp theo 7 viên siêu cấp là 1 phút
    private final int timeResummonIceShenron = 60_000;

    private boolean isShenronAppear;
    public boolean isBlackShenronAppear;
    public boolean isNoelShenronAppear;
    public boolean isIcecShenronAppear;
    private final int timeShenronWait = 300000;

    private final Thread update;
    private boolean active;

    public boolean isPlayerDisconnect;

    public Player playerSummonShenron;

    private int playerSummonShenronId;
    private Zone mapShenronAppear;
    private byte shenronStar;
    private int menuShenron;
    private byte select;

    private SummonDragon() {
        this.pl_dragonStar = new HashMap<>();
        this.update = new Thread(() -> {
            while (active) {
                try {
                    if (isShenronAppear) {
                        if (isPlayerDisconnect) {
                            List<Player> players = mapShenronAppear.getPlayers();
                            synchronized (players) {
                                for (Player plMap : players) {
                                    if (plMap.id == playerSummonShenronId) {
                                        playerSummonShenron = plMap;
                                        reSummonShenron(DRAGON_SHENRON);
                                        isPlayerDisconnect = false;
                                        break;
                                    }
                                }
                            }

                        }
                        if (Util.canDoWithTime(lastTimeShenronWait, timeShenronWait)) {
                            shenronLeave(playerSummonShenron, TIME_UP);
                        }
                    } else if (isBlackShenronAppear) {
                        if (isPlayerDisconnect) {
                            List<Player> players = mapShenronAppear.getPlayers();
                            synchronized (players) {
                                for (Player plMap : players) {
                                    if (plMap.id == playerSummonShenronId) {
                                        playerSummonShenron = plMap;
                                        reSummonShenron(DRAGON_BLACK_SHENRON);
                                        isPlayerDisconnect = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (Util.canDoWithTime(lastTimeShenronWait, timeShenronWait)) {
                            shenronLeave(playerSummonShenron, TIME_UP);
                        }
                    } else if (isIcecShenronAppear) {
                        if (isPlayerDisconnect) {
                            List<Player> players = mapShenronAppear.getPlayers();
                            synchronized (players) {
                                for (Player plMap : players) {
                                    if (plMap.id == playerSummonShenronId) {
                                        playerSummonShenron = plMap;
                                        reSummonShenron(DRAGON_ICE_SHENRON);
                                        isPlayerDisconnect = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if (Util.canDoWithTime(lastTimeShenronWait, timeShenronWait)) {
                            shenronLeave(playerSummonShenron, TIME_UP);
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.active();
    }

    private void active() {
        if (!active) {
            active = true;
            this.update.start();
        }
    }

    public static SummonDragon gI() {
        if (instance == null) {
            instance = new SummonDragon();
        }
        return instance;
    }

    public void openMenuSummonShenron(Player pl, byte dragonBallStar, byte Dragon) {
        switch (Dragon) {
            case DRAGON_SHENRON:
                this.pl_dragonStar.put(pl, dragonBallStar);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_SHENRON, -1, "Bạn muốn gọi rồng thần ?",
                        "Hướng\ndẫn thêm\n(mới)", "Gọi\nRồng Thần\n" + dragonBallStar + " Sao");
                break;
            case DRAGON_BLACK_SHENRON:
                this.pl_dragonStar.put(pl, dragonBallStar);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_BLACK_SHENRON, -1, "Bạn muốn gọi Rồng Siêu Cấp ?",
                        "Đồng ý", "Chê");
                break;
            case DRAGON_ICE_SHENRON:
                this.pl_dragonStar.put(pl, dragonBallStar);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_ICE_SHENRON, -1, "Bạn muốn gọi Rồng không?",
                        "Đồng ý", "No");
                break;
            case DRAGON_NOEL:
                this.pl_dragonStar.put(pl, dragonBallStar);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.SUMMON_DRAGON_NOEL, -1, "Bạn muốn gọi Rồng Hắc Ám không?",
                        "Đồng ý", "No");
                break;
        }

    }

    public void summonDragonNoel(Player pl) {
        if (pl.zone.map.mapId == 39 || pl.zone.map.mapId == 44 || pl.zone.map.mapId == 43) {
            if (checkShenronBall(pl, DRAGON_NOEL)) {
                if (isNoelShenronAppear) {
                    Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }
                // ekko sửa lại thời gian chờ gọi rồng hắc ám là 30s
                if (Util.canDoWithTime(lastTimeNoelShenronAppeared, timeResummonNoelShenron) || pl.isAdmin()) {
                    playerSummonShenron = pl;
                    playerSummonShenronId = (int) pl.id;
                    mapShenronAppear = pl.zone;

                    for (int i : NGOC_RONG_NOEL) {
                        InventoryService.gI().subQuantityItemsBag(pl, InventoryService.gI().findItemBagByTemp(pl, i), 1);
                    }

                    InventoryService.gI().sendItemBags(pl);
                    isNoelShenronAppear = true;
                    activeDragonNew(pl, true, (byte) 59);
                    sendWhishesShenron(pl);
                } else {
                    int timeLeft = (int) ((timeResummonNoelShenron - (System.currentTimeMillis() - lastTimeNoelShenronAppeared)) / 1000);
                    Service.getInstance().sendThongBao(pl, "Vui lòng đợi " + (timeLeft < 60 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Chỉ được gọi Rồng Hắc Ám ở Vách núi Moori, Vách núi Kakarot, Vách núi Aru");
        }
    }

    public void summonShenron(Player pl) {
        if (pl.zone.map.mapId == 0 || pl.zone.map.mapId == 7 || pl.zone.map.mapId == 14) {
            if (checkShenronBall(pl, DRAGON_SHENRON)) {
                if (isShenronAppear) {
                    Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }

                if (Util.canDoWithTime(lastTimeShenronAppeared, timeResummonShenron)) {
                    //gọi rồng
                    playerSummonShenron = pl;
                    playerSummonShenronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    byte dragonStar = (byte) pl_dragonStar.get(playerSummonShenron);
                    int begin = NGOC_RONG_1_SAO;
                    switch (dragonStar) {
                        case 2:
                            begin = NGOC_RONG_2_SAO;
                            break;
                        case 3:
                            begin = NGOC_RONG_3_SAO;
                            break;
                    }
                    for (int i = begin; i <= NGOC_RONG_7_SAO; i++) {
                        InventoryService.gI().subQuantityItemsBag(pl, InventoryService.gI().findItemBagByTemp(pl, i), 1);
                    }
                    InventoryService.gI().sendItemBags(pl);
//                    sendNotifyShenronAppear(DRAGON_SHENRON);
                    activeShenron(pl, true);
                    sendWhishesShenron(pl);
                } else {
                    int timeLeft = (int) ((timeResummonShenron - (System.currentTimeMillis() - lastTimeShenronAppeared)) / 1000);
                    Service.getInstance().sendThongBao(pl, "Vui lòng đợi " + (timeLeft < 60 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Chỉ được gọi rồng thần ở ngôi làng trước nhà");
        }
    }

    public void summonBlackShenron(Player pl) {
        if (pl.zone.map.mapId == 5) {
            if (checkShenronBall(pl, DRAGON_BLACK_SHENRON)) {
                if (isBlackShenronAppear) {
                    Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    return;
                }
                // ekko sửa lại thời gian chờ gọi rồng siêu cấp là 1 phút
                if (Util.canDoWithTime(lastTimeBlackShenronAppeared, timeResummonBlackShenron)) {
                    playerSummonShenron = pl;
                    playerSummonShenronId = (int) pl.id;
                    mapShenronAppear = pl.zone;
                    InventoryService.gI().subQuantityItemsBag(pl, InventoryService.gI().findItemBagByTemp(pl, NGOC_RONG_SIEU_CAP_FAKE), 1);
                    InventoryService.gI().sendItemBags(pl);
//                    sendNotifyShenronAppear(DRAGON_BLACK_SHENRON);
                    isBlackShenronAppear = true;
                    activeDragonNew(pl, true, (byte) 60);
                    sendWhishesShenron(pl);
                } else {
                    int timeLeft = (int) ((timeResummonBlackShenron - (System.currentTimeMillis() - lastTimeBlackShenronAppeared)) / 1000);
                    Service.getInstance().sendThongBao(pl, "Vui lòng đợi " + (timeLeft < 60 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
                }
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Chỉ được gọi rồng siêu cấp ở Đảo Kame");
        }
    }

    public void summonIceShenron(Player pl) {
        if (checkShenronBall(pl, DRAGON_ICE_SHENRON)) {
            if (isIcecShenronAppear) {
                Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                return;
            }
            // ekko sửa lại thời gian chờ gọi rồng siêu cấp là 1 phút
            // admin thì không phải đợi
            if (Util.canDoWithTime(lastTimeIceShenronAppeared, timeResummonIceShenron) || pl.isAdmin()) {
                playerSummonShenron = pl;
                playerSummonShenronId = (int) pl.id;
                mapShenronAppear = pl.zone;
                for (int i : NGOC_RONG_SIEU_CAP) {
                    InventoryService.gI().subQuantityItemsBag(pl, InventoryService.gI().findItemBagByTemp(pl, i), 1);
                }
                InventoryService.gI().sendItemBags(pl);
                sendNotifyShenronAppear(DRAGON_ICE_SHENRON);
                isIcecShenronAppear = true;
                activeDragonNew(pl, true, (byte) 50);
//            activeShenron(pl, true);
                sendWhishesShenron(pl);
            } else {
                int timeLeft = (int) ((timeResummonIceShenron - (System.currentTimeMillis() - lastTimeIceShenronAppeared)) / 1000);
                Service.getInstance().sendThongBao(pl, "Vui lòng đợi " + (timeLeft < 60 ? timeLeft + " giây" : timeLeft / 60 + " phút") + " nữa");
            }
        }
    }

    private void reSummonShenron(byte Dragon) {
        switch (Dragon) {
            case DRAGON_SHENRON:
                activeShenron(playerSummonShenron, true);
                sendWhishesShenron(playerSummonShenron);
                break;
            case DRAGON_BLACK_SHENRON:
                isBlackShenronAppear = true;
                activeDragonNew(playerSummonShenron, true, (byte) 60);
                sendWhishesShenron(playerSummonShenron);
                break;
            case DRAGON_ICE_SHENRON:
                isIcecShenronAppear = true;
                activeDragonNew(playerSummonShenron, true, (byte) 50);
                sendWhishesShenron(playerSummonShenron);
                break;
        }

    }

    private void sendWhishesShenron(Player pl) {
        byte dragonStar;//id item ngọc rồng
        try {
            dragonStar = (byte) pl_dragonStar.get(pl);
            this.shenronStar = dragonStar;
        } catch (Exception e) {
            dragonStar = this.shenronStar;
        }
        switch (dragonStar) {
            case 1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                break;
            case 2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_2, SHENRON_SAY, SHENRON_2_STARS_WHISHES);
                break;
            case 3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_3, SHENRON_SAY, SHENRON_3_STARS_WHISHES);
                break;
            case (byte) NGOC_RONG_SIEU_CAP_FAKE:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.BLACK_SHENRON, BLACK_SHENRON_SAY, BLACK_SHENRON_WISHES);
                break;
            case (byte) 2045:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.ICE_SHENRON, ICE_SHENRON_SAY, ICE_SHENRON_WISHES);
                break;
//            case (byte) NGOC_RONG_NOEL_1_SAO: {
//                NpcService.gI().createMenuRongThieng(pl, ConstNpc.NOEL_SHENRON, NOEL_SAY, NOEL_SHENRON_WISHES);
//                break;
//            }
        }
    }

    private void activeShenron(Player pl, boolean appear) {
        Message msg;
        try {
            msg = new Message(-83);
            msg.writer().writeByte(appear ? 0 : (byte) 1);
            if (appear) {
                msg.writer().writeShort(pl.zone.map.mapId);
                msg.writer().writeShort(pl.zone.map.bgId);
                msg.writer().writeByte(pl.zone.zoneId);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeUTF("");
                msg.writer().writeShort(pl.location.x);
                msg.writer().writeShort(pl.location.y);
                msg.writer().writeByte(DRAGON_SHENRON);
                lastTimeShenronWait = System.currentTimeMillis();
                isShenronAppear = true;
            }
            Service.getInstance().sendMessAllPlayer(msg);
        } catch (Exception e) {
        }
    }

    public void activeDragonNew(Player pl, boolean appear, byte eff) {
        Message msg;
        try {
            msg = new Message(Cmd.CALL_DRAGON);
            msg.writer().writeByte(appear ? 0 : (byte) 1);
            if (appear) {
                msg.writer().writeShort(pl.zone.map.mapId);
                msg.writer().writeShort(pl.zone.map.bgId);
                msg.writer().writeByte(pl.zone.zoneId);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeUTF("");
                msg.writer().writeShort(pl.location.x);
                msg.writer().writeShort(pl.location.y);
                msg.writer().writeByte(1);
                pl.zone.effDragon = eff;
                lastTimeShenronWait = System.currentTimeMillis();
            }
            Service.getInstance().sendMessAllPlayer(msg);
        } catch (Exception e) {
        }
    }

    private boolean checkShenronBall(Player pl, byte Dragon) {
        byte dragonStar = (byte) this.pl_dragonStar.get(pl);
        switch (Dragon) {
            case DRAGON_SHENRON:
                if (dragonStar == 1) {
                    if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_2_SAO)) {
                        Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 2 sao");
                        return false;
                    }
                    if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_3_SAO)) {
                        Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                        return false;
                    }
                } else if (dragonStar == 2) {
                    if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_3_SAO)) {
                        Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 3 sao");
                        return false;
                    }
                }
                if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_4_SAO)) {
                    Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 4 sao");
                    return false;
                }
                if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_5_SAO)) {
                    Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 5 sao");
                    return false;
                }
                if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_6_SAO)) {
                    Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 6 sao");
                    return false;
                }
                if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_7_SAO)) {
                    Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng 7 sao");
                    return false;
                }
                return true;
            case DRAGON_BLACK_SHENRON:
                if (!InventoryService.gI().existItemBag(pl, NGOC_RONG_SIEU_CAP_FAKE)) {
                    Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng siêu cấp");
                    return false;
                }
                return true;
            case DRAGON_ICE_SHENRON:
                for (int i : NGOC_RONG_SIEU_CAP) {
                    if (!InventoryService.gI().existItemBag(pl, i)) {
                        Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên ngọc rồng Siêu cấp");
                        return false;
                    }
                }
                return true;
            case DRAGON_NOEL: {
                for (int i : NGOC_RONG_NOEL) {
                    if (!InventoryService.gI().existItemBag(pl, i)) {
                        Service.getInstance().sendThongBao(pl, "Bạn còn thiếu 1 viên Ngọc Rồng Hắc Ám");
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private void sendNotifyShenronAppear(int Dragon) {// thong bao
        Message msg;
        try {
            msg = new Message(-25);
            switch (Dragon) {
                case DRAGON_SHENRON:
                    msg.writer().writeUTF(playerSummonShenron.name + " vừa gọi rồng thần tại "
                            + playerSummonShenron.zone.map.mapName + " khu vực " + playerSummonShenron.zone.zoneId);
                    Service.getInstance().sendMessAllPlayerIgnoreMe(playerSummonShenron, msg);
                    break;
                case DRAGON_BLACK_SHENRON:
                    msg.writer().writeUTF(playerSummonShenron.name + " vừa gọi rồng siêu cấp tại "
                            + playerSummonShenron.zone.map.mapName + " khu vực " + playerSummonShenron.zone.zoneId);
                    Service.getInstance().sendMessAllPlayerIgnoreMe(playerSummonShenron, msg);
                    break;
                case DRAGON_ICE_SHENRON:
                    msg.writer().writeUTF(playerSummonShenron.name + " vừa gọi rồng Namec tại "
                            + playerSummonShenron.zone.map.mapName + " khu vực " + playerSummonShenron.zone.zoneId);
                    Service.getInstance().sendMessAllPlayerIgnoreMe(playerSummonShenron, msg);
                    break;
                case DRAGON_NOEL:
                    msg.writer().writeUTF(playerSummonShenron.name + " vừa gọi rồng hắc ám tại "
                            + playerSummonShenron.zone.map.mapName + " khu vực " + playerSummonShenron.zone.zoneId);
                    Service.getInstance().sendMessAllPlayerIgnoreMe(playerSummonShenron, msg);
                    break;
            }

            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmWish() {
        boolean isWishSuccess = true;
        switch (this.menuShenron) {
            case ConstNpc.SHENRON_1_1:
                switch (this.select) {
                    case 0: //20 tr vàng
                        this.playerSummonShenron.inventory.addGold(2000000000);
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: //găng tay đang đeo lên 1 cấp
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 0) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    InventoryService.gI().sendItemBody(playerSummonShenron);
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Găng tay của ngươi đã đạt cấp tối đa");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo găng đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    case 2: //chí mạng +2%
                        if (this.playerSummonShenron != null) {
                            if (this.playerSummonShenron.nPoint.critg < 9) {
                                this.playerSummonShenron.nPoint.critg += 2;
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước này đã quá sức với ta, ta sẽ cho ngươi chọn lại");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra || this.playerSummonShenron == null");
                            reOpenShenronWishes(playerSummonShenron);
                        }
                        break;
                    case 3: //thay chiêu 2-3 đệ tử
                        if (playerSummonShenron != null && playerSummonShenron.pet != null) {
                            if (playerSummonShenron.pet.playerSkill.skills.get(1).skillId != -1) {
                                playerSummonShenron.pet.openSkill2();
                                if (playerSummonShenron.pet.playerSkill.skills.get(2).skillId != -1) {
                                    playerSummonShenron.pet.openSkill3();
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ít nhất đệ tử ngươi phải có chiêu 2 chứ!");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi làm gì có đệ tử?");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;
            case ConstNpc.SHENRON_1_2:
                switch (this.select) {
                    case 0: //đẹp trai nhất vũ trụ
                        if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            byte gender = this.playerSummonShenron.gender;
                            Item avtVip = ItemService.gI().createNewItem((short) (gender == ConstPlayer.TRAI_DAT ? 227
                                    : gender == ConstPlayer.NAMEC ? 228 : 229));
                            avtVip.itemOptions.add(new ItemOption(97, Util.nextInt(5, 10)));
                            avtVip.itemOptions.add(new ItemOption(77, Util.nextInt(10, 20)));
                            InventoryService.gI().addItemBag(playerSummonShenron, avtVip, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
//                    case 1: //+5k ngọc
////                        this.playerSummonShenron.inventory.addGem(5_000);
////                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
////                        break;
//                        if (this.playerSummonShenron != null) {
//                            if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
//                                Item btc3 = InventoryService.gI().findItemBagByTemp(playerSummonShenron, ConstItem.BONG_TAI_CAP_3);
//                                if (btc3 != null) {
//                                    // tỉ lệ thành công 5%
//                                    if(Util.isTrue(5, 100)) {
//                                        Item btc4 = ItemService.gI().createNewItem((short) ConstItem.BONG_TAI_CAP_4, 1);
//                                        // gán giữ nguyên option của btc3
//                                        for(ItemOption io : btc3.itemOptions) {
//                                            btc4.itemOptions.add(new ItemOption(io.optionTemplate.id, io.param));
//                                        }
//                                        // Sức mạnh rồng thiên tăng 10% SĐ,HP,KI
//                                        btc4.itemOptions.add(new ItemOption(245, 10));
//                                        InventoryService.gI().addItemBag(playerSummonShenron, btc4, 0);
//                                        InventoryService.gI().subQuantityItemsBag(playerSummonShenron, btc3, 1);
//                                        InventoryService.gI().sendItemBags(playerSummonShenron);
//                                    } else {
//                                        isWishSuccess = false;
//                                        Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
//                                    }
//                                } else {
//                                    Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu bông tai cấp 3 mới thực hiện được điều ước này");
//                                    reOpenShenronWishes(playerSummonShenron);
//                                    return;
//                                }
//                            } else {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
//                        break;
                    case 1: //+5k ruby
                        int oldRuby = this.playerSummonShenron.inventory.ruby;
                        this.playerSummonShenron.inventory.addRuby(2_000_000);
                        // ekko ghi log add ruby
                        Manager.addPlayerRubyHistory(this.playerSummonShenron.id, oldRuby, this.playerSummonShenron.inventory.ruby, "SummonDragon-confirmWish");
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 2: //+200 tr smtn
                        Service.getInstance().addSMTN(this.playerSummonShenron, (byte) 2, 200000000, false);
//                        if (false) {
//                            Service.getInstance().addSMTN(this.playerSummonShenron, (byte) 2, 200000000, false);
//                        }
//                        else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Xin lỗi, điều ước này khó quá, ta không thể thực hiện.");
//                            reOpenShenronWishes(playerSummonShenron);
//                            return;
//                        }
                        break;
                    case 3: //găng tay đệ lên 1 cấp
                        if (this.playerSummonShenron.pet != null) {
                            Item item = this.playerSummonShenron.pet.inventory.itemsBody.get(2);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 0) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    Service.getInstance().point(playerSummonShenron);
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Găng tay của đệ ngươi đã đạt cấp tối đa");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Đệ ngươi hiện tại có đeo găng đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi đâu có đệ tử");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;
            case ConstNpc.SHENRON_1_3:
                switch (this.select) {
                    case 0:
                        // btc3 -> btc4
                        if (this.playerSummonShenron != null) {
                            if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                                Item btc3 = InventoryService.gI().findItemBagByTemp(playerSummonShenron, ConstItem.BONG_TAI_CAP_3);
                                if (btc3 != null) {
                                    // tỉ lệ thành công 5%
                                    if(Util.isTrue(5, 100)) {
                                        Item btc4 = ItemService.gI().createNewItem((short) ConstItem.BONG_TAI_CAP_4, 1);
                                        // gán giữ nguyên option của btc3
                                        for(ItemOption io : btc3.itemOptions) {
                                            btc4.itemOptions.add(new ItemOption(io.optionTemplate.id, io.param));
                                        }
                                        // Sức mạnh rồng thiên tăng 10% SĐ,HP,KI
                                        btc4.itemOptions.add(new ItemOption(245, 10));
                                        InventoryService.gI().addItemBag(playerSummonShenron, btc4, 0);
                                        InventoryService.gI().subQuantityItemsBag(playerSummonShenron, btc3, 1);
                                        InventoryService.gI().sendItemBags(playerSummonShenron);
                                    } else {
                                        isWishSuccess = false;
                                        Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                                    }
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu bông tai cấp 3 mới thực hiện được điều ước này");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    case 1: //găng tay đang đeo lên 1 cấp
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 0) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    InventoryService.gI().sendItemBody(playerSummonShenron);
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Găng tay của ngươi đã đạt cấp tối đa");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo găng đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    // Nâng quần đang mang lên cấp 8
                    case 2:
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(1);
                            boolean isValid = false;
                            if(item.isNotNullItem()) {
                                for (ItemOption io : item.itemOptions) {
                                    // nếu găng đạt level 7 thì mới thực hiện điều ước
                                    if (io.optionTemplate.id == 72 && io.param == 7) {
                                        isValid = true;
                                        break;
                                    }
                                }
                            }
                            if (isValid) {
                                // tỉ lệ thành công 5%
                                if(Util.isTrue(5, 100)) {
                                    int level = 0;
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 72) {
                                            level = io.param;
                                            if (level < 8) {
                                                io.param++;
                                            }
                                            break;
                                        }
                                    }
                                    if (level < 8) {
                                        if (level == 0) {
                                            item.itemOptions.add(new ItemOption(72, 1));
                                        }
                                        for (ItemOption io : item.itemOptions) {
                                            if (io.optionTemplate.id == 6 || io.optionTemplate.id == 22) {
                                                io.param += (io.param * 10 / 100);
                                                break;
                                            }
                                        }
                                        InventoryService.gI().sendItemBody(playerSummonShenron);
                                    } else {
                                        Service.getInstance().sendThongBao(playerSummonShenron, "Quần đã cấp 8");
                                        reOpenShenronWishes(playerSummonShenron);
                                        return;
                                    }
                                } else {
                                    isWishSuccess = false;
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải mang quần cấp 7 mới thực hiện được điều ước này");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    // Nâng găng đang mang lên cấp 8
                    case 3:
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
                            boolean isValid = false;
                            if(item.isNotNullItem()) {
                                for (ItemOption io : item.itemOptions) {
                                    // nếu găng đạt level 7 thì mới thực hiện điều ước
                                    if (io.optionTemplate.id == 72 && io.param == 7) {
                                        isValid = true;
                                        break;
                                    }
                                }
                            }
                            if (isValid) {
                                // tỉ lệ thành công 5%
                                if(Util.isTrue(5, 100)) {
                                    int level = 0;
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 72) {
                                            level = io.param;
                                            if (level < 8) {
                                                io.param++;
                                            }
                                            break;
                                        }
                                    }
                                    if (level < 8) {
                                        if (level == 0) {
                                            item.itemOptions.add(new ItemOption(72, 1));
                                        }
                                        for (ItemOption io : item.itemOptions) {
                                            if (io.optionTemplate.id == 0) {
                                                io.param += (io.param * 10 / 100);
                                                break;
                                            }
                                        }
                                        InventoryService.gI().sendItemBody(playerSummonShenron);
                                    } else {
                                        Service.getInstance().sendThongBao(playerSummonShenron, "Găng đã cấp 8");
                                        reOpenShenronWishes(playerSummonShenron);
                                        return;
                                    }
                                } else {
                                    isWishSuccess = false;
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải mang găng cấp 7 mới thực hiện được điều ước này");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    // Nâng giày đang mang lên cấp 8
                    case 4:
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(3);
                            boolean isValid = false;
                            if(item.isNotNullItem()) {
                                for (ItemOption io : item.itemOptions) {
                                    // nếu găng đạt level 7 thì mới thực hiện điều ước
                                    if (io.optionTemplate.id == 72 && io.param == 7) {
                                        isValid = true;
                                        break;
                                    }
                                }
                            }
                            if (isValid) {
                                // tỉ lệ thành công 5%
                                if(Util.isTrue(5, 100)) {
                                    int level = 0;
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 72) {
                                            level = io.param;
                                            if (level < 8) {
                                                io.param++;
                                            }
                                            break;
                                        }
                                    }
                                    if (level < 8) {
                                        if (level == 0) {
                                            item.itemOptions.add(new ItemOption(72, 1));
                                        }
                                        for (ItemOption io : item.itemOptions) {
                                            if (io.optionTemplate.id == 7 || io.optionTemplate.id == 23) {
                                                io.param += (io.param * 10 / 100);
                                                break;
                                            }
                                        }
                                        InventoryService.gI().sendItemBody(playerSummonShenron);
                                    } else {
                                        Service.getInstance().sendThongBao(playerSummonShenron, "Giày đã cấp 8");
                                        reOpenShenronWishes(playerSummonShenron);
                                        return;
                                    }
                                } else {
                                    isWishSuccess = false;
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải mang giày cấp 7 mới thực hiện được điều ước này");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                }
                break;
            case ConstNpc.SHENRON_2:
                switch (this.select) {
                    case 0: //+150 ngọc
                        this.playerSummonShenron.inventory.addGem(2000);
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: //+20 tr smtn
                        if (false) {
                            Service.getInstance().addSMTN(this.playerSummonShenron, (byte) 2, 20000000, false);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Xin lỗi, điều ước này khó quá, ta không thể thực hiện.");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }

                        break;
                    case 2: //2 tr vàng
                        this.playerSummonShenron.inventory.addGold(200000000);
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                }
                break;
            case ConstNpc.SHENRON_3:
                switch (this.select) {
                    case 0: //+15 ngọc
                        this.playerSummonShenron.inventory.addGem(200);
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                    case 1: //+2 tr smtn
                        if (false) {
                            Service.getInstance().addSMTN(this.playerSummonShenron, (byte) 2, 2000000, false);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Xin lỗi, điều ước này khó quá, ta không thể thực hiện.");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 2: //200k vàng
                        this.playerSummonShenron.inventory.addGold(20000000);
                        PlayerService.gI().sendInfoHpMpMoney(this.playerSummonShenron);
                        break;
                }
                break;
            case ConstNpc.BLACK_SHENRON:
                switch (this.select) {
                    case 0:
                        if (playerSummonShenron != null) {
                            if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                                Item ctGohan = ItemService.gI().createNewItem((short) 989);
                                ctGohan.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));// suc danh
                                ctGohan.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));// hp
                                ctGohan.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));// ki
                                ctGohan.itemOptions.add(new ItemOption(5, Util.nextInt(20, 30)));// 
                                ctGohan.itemOptions.add(new ItemOption(47, Util.nextInt(5, 15)));
                                ctGohan.itemOptions.add(new ItemOption(93, 60));
                                InventoryService.gI().addItemBag(playerSummonShenron, ctGohan, 0);
                                InventoryService.gI().sendItemBags(playerSummonShenron);
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Lỗi || playerSummonShenron == null ");
                            reOpenShenronWishes(playerSummonShenron);
                        }
                        break;
                    case 1:
                        if (playerSummonShenron == null) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Da xay  ra loi");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            Item ctBiden = ItemService.gI().createNewItem((short) 990);
                            ctBiden.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                            ctBiden.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                            ctBiden.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                            ctBiden.itemOptions.add(new ItemOption(5, Util.nextInt(20, 30)));
                            ctBiden.itemOptions.add(new ItemOption(47, Util.nextInt(5, 15)));

                            ctBiden.itemOptions.add(new ItemOption(93, 60));
                            InventoryService.gI().addItemBag(playerSummonShenron, ctBiden, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 2:
                        if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            Item ctConuong = ItemService.gI().createNewItem((short) 991);
                            ctConuong.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                            ctConuong.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                            ctConuong.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                            ctConuong.itemOptions.add(new ItemOption(5, Util.nextInt(20, 30)));
                            ctConuong.itemOptions.add(new ItemOption(47, Util.nextInt(5, 15)));

                            ctConuong.itemOptions.add(new ItemOption(93, 60));
                            InventoryService.gI().addItemBag(playerSummonShenron, ctConuong, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 3:
                        if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            Item PetThoOm = ItemService.gI().createNewItem((short) 1039);
                            PetThoOm.itemOptions.add(new ItemOption(50, Util.nextInt(5, 10)));
                            PetThoOm.itemOptions.add(new ItemOption(77, Util.nextInt(5, 10)));
                            PetThoOm.itemOptions.add(new ItemOption(103, Util.nextInt(5, 10)));
                            PetThoOm.itemOptions.add(new ItemOption(93, 60));
                            InventoryService.gI().addItemBag(playerSummonShenron, PetThoOm, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 4:
                        if (InventoryService.gI().getCountEmptyBag(playerSummonShenron) > 0) {
                            Item PetThoMap = ItemService.gI().createNewItem((short) 1040);
                            PetThoMap.itemOptions.add(new ItemOption(50, Util.nextInt(5, 10)));
                            PetThoMap.itemOptions.add(new ItemOption(77, Util.nextInt(5, 10)));
                            PetThoMap.itemOptions.add(new ItemOption(103, Util.nextInt(5, 10)));
                            PetThoMap.itemOptions.add(new ItemOption(93, 60));
                            InventoryService.gI().addItemBag(playerSummonShenron, PetThoMap, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Hành trang đã đầy");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                }
                break;
            case ConstNpc.ICE_SHENRON:
                if (this.playerSummonShenron == null) {
                    return;
                }
                switch (this.select) {
                    case 0://1
                        if (playerSummonShenron.itemTime.rateDame) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước này vẫn còn tồn tại");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        playerSummonShenron.itemTime.lastTimeDameDr = System.currentTimeMillis();
                        playerSummonShenron.itemTime.rateDame = true;
                        ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        break;
                    case 1://2
                        Service.getInstance().addSMTN(playerSummonShenron, (byte) 2, 100_000_000, false);
                        break;
                    case 2://3
                        Service.getInstance().addSMTN(playerSummonShenron.pet, (byte) 2, 100_000_000, false);
                        break;
                    case 3://4
                        playerSummonShenron.pet.nPoint.increasePoint((byte) 4, (short) 1);
                        break;
                    case 4://5
                        if (playerSummonShenron.pet == null) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi làm gì có đệ tử?");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        if (playerSummonShenron.pet.playerSkill.skills.get(1).skillId != -1) {
                            playerSummonShenron.pet.openSkill2Kame();
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã đổi thành công Skill Kamejoko");
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ít nhất đệ tử ngươi phải có chiêu 2 chứ!");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 5:
                        if (playerSummonShenron.pet == null) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi làm gì có đệ tử?");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        if (playerSummonShenron.pet.playerSkill.skills.get(0).skillId != -1) {
                            playerSummonShenron.pet.openSkillLienhoan();
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã đổi thành công Skill Liên hoan");
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ít nhất đệ tử ngươi phải có chiêu 1 chứ!");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 6:
                        if (playerSummonShenron.pet == null) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi làm gì có đệ tử?");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        if (SkillUtil.upSkillPet(playerSummonShenron.pet.playerSkill.skills, 4)) {
                            Service.getInstance().chatJustForMe(playerSummonShenron, playerSummonShenron.pet, "Cảm ơn sư phụ");
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Không thể thực hiện");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        break;
                    case 7: //găng tay đang đeo lên 1 cấp
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(1);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 6 || io.optionTemplate.id == 22) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    InventoryService.gI().sendItemBody(playerSummonShenron);
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Quần đã cấp 7");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo quần đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    case 8: //găng tay đang đeo lên 1 cấp
                        if (this.playerSummonShenron != null) {
                            Item item = this.playerSummonShenron.inventory.itemsBody.get(3);
                            if (item.isNotNullItem()) {
                                int level = 0;
                                for (ItemOption io : item.itemOptions) {
                                    if (io.optionTemplate.id == 72) {
                                        level = io.param;
                                        if (level < 7) {
                                            io.param++;
                                        }
                                        break;
                                    }
                                }
                                if (level < 7) {
                                    if (level == 0) {
                                        item.itemOptions.add(new ItemOption(72, 1));
                                    }
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 7 || io.optionTemplate.id == 23) {
                                            io.param += (io.param * 10 / 100);
                                            break;
                                        }
                                    }
                                    InventoryService.gI().sendItemBody(playerSummonShenron);
                                } else {
                                    Service.getInstance().sendThongBao(playerSummonShenron, "Giày đã cấp 7");
                                    reOpenShenronWishes(playerSummonShenron);
                                    return;
                                }
                            } else {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi hiện tại có đeo giày đâu");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                }
                break;
                // ekko thực hiện điều ước rồng xương
            case ConstNpc.NOEL_SHENRON:
                switch (this.select) {
//                    case 1:
//                        // x5 tnsm đệ wish trong 30 phút
//                        if(playerSummonShenron != null && playerSummonShenron.itemTime != null) {
//                            if (playerSummonShenron.itemTime.rateBoneDrTNSM) {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước này vẫn còn tồn tại");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                            playerSummonShenron.itemTime.lastTimeTNSMBoneDr = System.currentTimeMillis();
//                            playerSummonShenron.itemTime.rateBoneDrTNSM = true;
//                            ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
//                        break;
                    case 0:
                        if(playerSummonShenron != null && playerSummonShenron.itemTime != null) {
                            // + 10% sức đánh, HP, KI trong 10 phút
                            if (playerSummonShenron.itemTime.rateNoelDrSucDanhHPKI) {
                                Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước này vẫn còn tồn tại");
                                reOpenShenronWishes(playerSummonShenron);
                                return;
                            }
                            playerSummonShenron.itemTime.lastTimeSucDanhHPKINoelDr = System.currentTimeMillis();
                            playerSummonShenron.itemTime.rateNoelDrSucDanhHPKI = true;
                            ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
                        } else {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
                        }
                        break;
                    case 1:
//                        if(playerSummonShenron != null && playerSummonShenron.itemTime != null) {
//                            // + 15% HP,KI trong 30 phút
//                            if (playerSummonShenron.itemTime.rateNoelDrHPKI) {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước này vẫn còn tồn tại");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                            playerSummonShenron.itemTime.lastTimeHPKINoelDr = System.currentTimeMillis();
//                            playerSummonShenron.itemTime.rateNoelDrHPKI = true;
//                            ItemTimeService.gI().sendAllItemTime(playerSummonShenron);
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
                        if(Util.isTrue(3, 100)) {
                            Item saoHangA = ItemService.gI().createNewItem((short) ConstItem.VONG_THIEN_SU, 1);
                            // option đẹp trai
                            saoHangA.itemOptions.add(new ItemOption(169, 10));
                            InventoryService.gI().addItemBag(playerSummonShenron, saoHangA, 0);
                            InventoryService.gI().sendItemBags(playerSummonShenron);
                            Service.getInstance().sendThongBao(playerSummonShenron, "Mày đẹp trai hơn admin chút xíu thôi cu ạ");
                        } else {
                            isWishSuccess = false;
                            Service.getInstance().sendThongBao(playerSummonShenron, "Mày đéo đẹp trai hơn admin đâu ku ạ");
                        }
                        break;
                    // đệ tử whis
                    case 2:
                        if (!(playerSummonShenron != null && playerSummonShenron.pet != null && playerSummonShenron.pet.typePet == ConstPet.BILL_CON && playerSummonShenron.pet.lever >= 7)) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu đệ Bill nhí level 7 thì mới thực hiện được điều ước này");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        // 30% tỉ lệ thành công
                        if(Util.isTrue(30, 100)) {
                            // đổi sang pet whis
                            PetService.gI().changeWhisPetByLevel(playerSummonShenron, playerSummonShenron.gender, playerSummonShenron.pet.lever);
                            Service.getInstance().sendThongBao(playerSummonShenron, "Chúc mừng ngươi có đệ tử mới");
                        } else {
                            isWishSuccess = false;
                            Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                        }
                        break;
                        // Nâng cấp bông tai cấp 3 lên cấp 4
                    case 3:
//                        if (this.playerSummonShenron != null) {
//                            Item btc3 = InventoryService.gI().findItemBagByTemp(playerSummonShenron, ConstItem.BONG_TAI_CAP_3);
//                            if (btc3 != null) {
//                                // tỉ lệ thành công 5%
//                                if(Util.isTrue(5, 100)) {
//                                    Item btc4 = ItemService.gI().createNewItem((short) ConstItem.BONG_TAI_CAP_4, 1);
//                                    // gán giữ nguyên option của btc3
//                                    for(ItemOption io : btc3.itemOptions) {
//                                        btc4.itemOptions.add(new ItemOption(io.optionTemplate.id, io.param));
//                                    }
//                                    // Sức mạnh rồng thiên tăng 10% SĐ,HP,KI
//                                    btc4.itemOptions.add(new ItemOption(245, 10));
//                                    InventoryService.gI().addItemBag(playerSummonShenron, btc4, 0);
//                                    InventoryService.gI().subQuantityItemsBag(playerSummonShenron, btc3, 1);
//                                    InventoryService.gI().sendItemBags(playerSummonShenron);
//                                } else {
//                                    isWishSuccess = false;
//                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
//                                }
//                            } else {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu bông tai cấp 3 mới thực hiện được điều ước này");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
                        if (!(playerSummonShenron != null && playerSummonShenron.pet != null && playerSummonShenron.pet.typePet == ConstPet.ZENO && playerSummonShenron.pet.nPoint != null && playerSummonShenron.pet.nPoint.power < 150_000_000_000L)) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu đệ Zeno có sức mạnh < 150 tỷ thì mới thực hiện được điều ước này");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        playerSummonShenron.pet.nPoint.powerUp(1_000_000_000L);
                            Service.getInstance().sendThongBao(playerSummonShenron, "Chúc mừng đệ zeno đã tăng thêm 1 tỷ sức mạnh");
                        break;
                    case 4:
                        if (!(playerSummonShenron != null && playerSummonShenron.pet != null && playerSummonShenron.pet.typePet == ConstPet.WHIS && playerSummonShenron.pet.nPoint != null && playerSummonShenron.pet.nPoint.power > 100_000_000_000L)) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu đệ Whis đạt 100 tỷ sức mạnh thì mới thực hiện được điều ước này");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        // 1% tỉ lệ thành công
                        if(Util.isTrue(1, 100)) {
                            // đổi sang pet zeno
                            PetService.gI().changeZenoPetByLevel(playerSummonShenron, playerSummonShenron.gender, playerSummonShenron.pet.lever);
                            Service.getInstance().sendThongBao(playerSummonShenron, "Chúc mừng ngươi có đệ tử mới");
                        } else {
                            isWishSuccess = false;
                            Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
                        }
                        break;
                    case 5:
                        if (!(playerSummonShenron != null && playerSummonShenron.pet != null && playerSummonShenron.pet.typePet == ConstPet.BLACK_GOKU && playerSummonShenron.pet.nPoint != null && playerSummonShenron.pet.nPoint.power < 150_000_000_000L)) {
                            Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải sở hữu đệ Black Goku có sức mạnh < 150 tỷ thì mới thực hiện được điều ước này");
                            reOpenShenronWishes(playerSummonShenron);
                            return;
                        }
                        playerSummonShenron.pet.nPoint.powerUp(1_000_000_000L);
                        Service.getInstance().sendThongBao(playerSummonShenron, "Chúc mừng đệ Goku đã tăng thêm 1 tỷ sức mạnh");
                        break;
                        // Nâng găng đang mang lên cấp 8
//                    case 4:
//                        if (this.playerSummonShenron != null) {
//                            Item item = this.playerSummonShenron.inventory.itemsBody.get(2);
//                            boolean isValid = false;
//                            if(item.isNotNullItem()) {
//                                for (ItemOption io : item.itemOptions) {
//                                    // nếu găng đạt level 7 thì mới thực hiện điều ước
//                                    if (io.optionTemplate.id == 72 && io.param == 7) {
//                                        isValid = true;
//                                        break;
//                                    }
//                                }
//                            }
//                            if (isValid) {
//                                // tỉ lệ thành công 10%
//                                if(Util.isTrue(10, 100)) {
//                                    int level = 0;
//                                    for (ItemOption io : item.itemOptions) {
//                                        if (io.optionTemplate.id == 72) {
//                                            level = io.param;
//                                            if (level < 8) {
//                                                io.param++;
//                                            }
//                                            break;
//                                        }
//                                    }
//                                    if (level < 8) {
//                                        if (level == 0) {
//                                            item.itemOptions.add(new ItemOption(72, 1));
//                                        }
//                                        for (ItemOption io : item.itemOptions) {
//                                            if (io.optionTemplate.id == 0) {
//                                                io.param += (io.param * 10 / 100);
//                                                break;
//                                            }
//                                        }
//                                        InventoryService.gI().sendItemBody(playerSummonShenron);
//                                    } else {
//                                        Service.getInstance().sendThongBao(playerSummonShenron, "Găng đã cấp 8");
//                                        reOpenShenronWishes(playerSummonShenron);
//                                        return;
//                                    }
//                                } else {
//                                    isWishSuccess = false;
//                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
//                                }
//                            } else {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải mang găng cấp 7 mới thực hiện được điều ước này");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
//                        break;
//                    case 5:
//                        if (this.playerSummonShenron != null) {
//                            Item itemQuan = this.playerSummonShenron.inventory.itemsBody.get(1);
//                            boolean isQuanValid = false;
//                            if(itemQuan.isNotNullItem()) {
//                                for (ItemOption io : itemQuan.itemOptions) {
//                                    // nếu quần đạt level 7 thì mới thực hiện điều ước
//                                    if (io.optionTemplate.id == 72 && io.param == 7) {
//                                        isQuanValid = true;
//                                        break;
//                                    }
//                                }
//                            }
//                            if (isQuanValid) {
//                                // tỉ lệ thành công 10%
//                                if(Util.isTrue(10, 100)) {
//                                    int level = 0;
//                                    for (ItemOption io : itemQuan.itemOptions) {
//                                        if (io.optionTemplate.id == 72) {
//                                            level = io.param;
//                                            if (level < 8) {
//                                                io.param++;
//                                            }
//                                            break;
//                                        }
//                                    }
//                                    if (level < 8) {
//                                        if (level == 0) {
//                                            itemQuan.itemOptions.add(new ItemOption(72, 1));
//                                        }
//                                        for (ItemOption io : itemQuan.itemOptions) {
//                                            if (io.optionTemplate.id == 6 || io.optionTemplate.id == 22) {
//                                                io.param += (io.param * 10 / 100);
//                                                break;
//                                            }
//                                        }
//                                        InventoryService.gI().sendItemBody(playerSummonShenron);
//                                    } else {
//                                        Service.getInstance().sendThongBao(playerSummonShenron, "Quần đã cấp 8");
//                                        reOpenShenronWishes(playerSummonShenron);
//                                        return;
//                                    }
//                                } else {
//                                    isWishSuccess = false;
//                                    Service.getInstance().sendThongBao(playerSummonShenron, "Điều ước khó quá ta éo làm được");
//                                }
//                            } else {
//                                Service.getInstance().sendThongBao(playerSummonShenron, "Ngươi phải mang quần cấp 7 mới thực hiện được điều ước này");
//                                reOpenShenronWishes(playerSummonShenron);
//                                return;
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(playerSummonShenron, "Đã có lỗi xảy ra");
//                        }
//                        break;
                }
                break;
        }
        this.isBlackShenronAppear = false;
        if(isWishSuccess) {
            shenronLeave(this.playerSummonShenron, WISHED);
        } else {
            shenronLeaveByCondition(this.playerSummonShenron, WISHED, isWishSuccess);
        }
    }

    public void showConfirmShenron(Player pl, int menu, byte select) {
        this.menuShenron = menu;
        this.select = select;
        String wish = null;
        switch (menu) {
            case ConstNpc.SHENRON_1_1:
                wish = SHENRON_1_STAR_WISHES_1[select];
                break;
            case ConstNpc.SHENRON_1_2:
                wish = SHENRON_1_STAR_WISHES_2[select];
                break;
            case ConstNpc.SHENRON_1_3:
                wish = SHENRON_1_STAR_WISHES_3[select];
                break;
            case ConstNpc.SHENRON_2:
                wish = SHENRON_2_STARS_WHISHES[select];
                break;
            case ConstNpc.SHENRON_3:
                wish = SHENRON_3_STARS_WHISHES[select];
                break;
            case ConstNpc.BLACK_SHENRON:
                wish = BLACK_SHENRON_WISHES[select];
                break;
            case ConstNpc.ICE_SHENRON:
                wish = ICE_SHENRON_WISHES[select];
                break;
            case ConstNpc.NOEL_SHENRON:
                wish = NOEL_SHENRON_WISHES[select];
                break;
        }
        NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_CONFIRM, "Ngươi có chắc muốn ước?", wish, "Từ chối");
    }

    public void reOpenShenronWishes(Player pl) {
        switch (menuShenron) {
            case ConstNpc.SHENRON_1_1:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_1, SHENRON_SAY, SHENRON_1_STAR_WISHES_1);
                break;
            case ConstNpc.SHENRON_1_2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_2, SHENRON_SAY, SHENRON_1_STAR_WISHES_2);
                break;
            case ConstNpc.SHENRON_1_3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_1_3, SHENRON_SAY, SHENRON_1_STAR_WISHES_3);
                break;
            case ConstNpc.SHENRON_2:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_2, SHENRON_SAY, SHENRON_2_STARS_WHISHES);
                break;
            case ConstNpc.SHENRON_3:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.SHENRON_3, SHENRON_SAY, SHENRON_3_STARS_WHISHES);
                break;
            case ConstNpc.BLACK_SHENRON:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.BLACK_SHENRON, BLACK_SHENRON_SAY, BLACK_SHENRON_WISHES);
                break;
            case ConstNpc.ICE_SHENRON:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.ICE_SHENRON, ICE_SHENRON_SAY, ICE_SHENRON_WISHES);
                break;
            case ConstNpc.NOEL_SHENRON:
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.NOEL_SHENRON, NOEL_SAY, NOEL_SHENRON_WISHES);
                break;
        }
    }

    public void shenronLeave(Player pl, byte type) {
        if (pl == null) {
            return;
        }
        try {
            if (type == WISHED) {
                NpcService.gI().createTutorial(pl, -1, "Điều ước của ngươi đã trở thành sự thật\nHẹn gặp ngươi lần sau, ta đi ngủ đây, bái bai");
            } else {
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.IGNORE_MENU, "Ta buồn ngủ quá rồi\nHẹn gặp ngươi lần sau, ta đi đây, bái bai");
            }
            activeShenron(pl, false);
            // ekko nếu là rồng siêu cấp từ ngọc siêu cấp
            if(this.menuShenron == ConstNpc.BLACK_SHENRON) {
                lastTimeBlackShenronAppeared = System.currentTimeMillis();
            }
            // rồng siêu cấp từ ngọc siêu cấp 7 viên
            else if(this.menuShenron == ConstNpc.ICE_SHENRON) {
                lastTimeIceShenronAppeared = System.currentTimeMillis();
            }
            // rồng xương từ ngọc rồng đen
            else if(this.menuShenron == ConstNpc.NOEL_SHENRON) {
                lastTimeNoelShenronAppeared = System.currentTimeMillis();

            }
            else {
                lastTimeShenronAppeared = System.currentTimeMillis();
            }
            this.isBlackShenronAppear = false;
            this.isShenronAppear = false;
            this.isIcecShenronAppear = false;
            this.isNoelShenronAppear = false;
            this.menuShenron = -1;
            this.select = -1;
            this.playerSummonShenron = null;
            this.playerSummonShenronId = -1;
            this.shenronStar = -1;
            this.mapShenronAppear = null;
            if (pl != null && pl.zone != null) {
                pl.zone.effDragon = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Service.getInstance().sendThongBao(pl, "Đã có lỗi xảy ra");
        }

    }

    // ekko rồng rời map hiển thị text theo điều kiện
    public void shenronLeaveByCondition(Player pl, byte type, boolean isSuccess) {
        if (pl == null) {
            return;
        }
        try {
            if (type == WISHED) {
                // thành công
                if(isSuccess) {
                    NpcService.gI().createTutorial(pl, -1, "Điều ước của ngươi đã trở thành sự thật\nHẹn gặp ngươi lần sau, ta đi ngủ đây, bái bai");
                }
                // thất bại
                else {
                    NpcService.gI().createTutorial(pl, -1, "Điều ước của ngươi khó quá ta éo làm được\nHẹn gặp ngươi lần sau, ta đi ngủ đây, bái bai");
                }
            } else {
                NpcService.gI().createMenuRongThieng(pl, ConstNpc.IGNORE_MENU, "Ta buồn ngủ quá rồi\nHẹn gặp ngươi lần sau, ta đi đây, bái bai");
            }
            activeShenron(pl, false);
            // ekko nếu là rồng siêu cấp từ ngọc siêu cấp
            if(this.menuShenron == ConstNpc.BLACK_SHENRON) {
                lastTimeBlackShenronAppeared = System.currentTimeMillis();
            }
            // rồng siêu cấp từ ngọc siêu cấp 7 viên
            else if(this.menuShenron == ConstNpc.ICE_SHENRON) {
                lastTimeIceShenronAppeared = System.currentTimeMillis();
            }
            // rồng xương từ ngọc rồng đen
            else if(this.menuShenron == ConstNpc.NOEL_SHENRON) {
                lastTimeNoelShenronAppeared = System.currentTimeMillis();

            }
            else {
                lastTimeShenronAppeared = System.currentTimeMillis();
            }
            this.isBlackShenronAppear = false;
            this.isShenronAppear = false;
            this.isIcecShenronAppear = false;
            this.isNoelShenronAppear = false;
            this.menuShenron = -1;
            this.select = -1;
            this.playerSummonShenron = null;
            this.playerSummonShenronId = -1;
            this.shenronStar = -1;
            this.mapShenronAppear = null;
            if (pl != null && pl.zone != null) {
                pl.zone.effDragon = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Service.getInstance().sendThongBao(pl, "Đã có lỗi xảy ra");
        }

    }

    //--------------------------------------------------------------------------
}
