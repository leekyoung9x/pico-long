package nro.models.boss;

import nro.consts.ConstEvent;
import nro.consts.ConstMap;
import nro.consts.ConstPlayer;
import nro.models.boss.NguHanhSon.DuongTang;
import nro.models.boss.NguHanhSon.NgoKhong;
import nro.models.boss.NgucTu.Cumber;
import nro.models.boss.NgucTu.SuperCumber;
import nro.models.boss.Omega.OmegaPlus;
import nro.models.boss.baconsoi.Basil;
import nro.models.boss.baconsoi.Bergamo;
import nro.models.boss.baconsoi.Lavender;
import nro.models.boss.bill.*;
import nro.models.boss.blackgoku.BlackGoku;
import nro.models.boss.blackgoku.BlackGokuPink;
import nro.models.boss.boss_doanh_trai.BossDoanhTrai;
import nro.models.boss.bosstuonglai.*;
import nro.models.boss.bosstuonglai.kamiboss.KamiOrenOne;
import nro.models.boss.broly.*;
import nro.models.boss.cell.*;
import nro.models.boss.chill.*;
import nro.models.boss.cold.*;
import nro.models.boss.event.HoaHong;
import nro.models.boss.event.SantaClaus;
import nro.models.boss.event.hellowin.MaVuongOne;
import nro.models.boss.event.noel.NoelBoss;
import nro.models.boss.event.noel.NoelBossBall;
import nro.models.boss.event.noel.NoelBossOne;
import nro.models.boss.event.noel.NoelBossTwo;
import nro.models.boss.event.noel.tuanloc.TuanLoc;
import nro.models.boss.event.noel.tuanloc.TuanLocOne;
import nro.models.boss.event.tet.MeoThanTai;
import nro.models.boss.fide.*;
import nro.models.boss.halloween.BoXuong;
import nro.models.boss.halloween.MaTroi;
import nro.models.boss.hell.DraculaHutMau;
import nro.models.boss.hell.SatanKing;
import nro.models.boss.list_boss.*;
import nro.models.boss.mabu_war.*;
import nro.models.boss.nappa.*;
import nro.models.boss.nappa.namec.KukuNamec;
import nro.models.boss.nappa.namec.MapDauDinhNamec;
import nro.models.boss.nappa.namec.RamboNamec;
import nro.models.boss.robotsatthu.*;
import nro.models.boss.tieudoisatthu.*;
import nro.models.map.Map;
import nro.models.map.Zone;
import nro.models.map.mabu.MabuWar;
import nro.models.map.mabu.MabuWar14h;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.server.Manager;
import nro.services.MapService;
import org.apache.log4j.Logger;

/**
 * @Stole Arriety
 */
public class BossFactory {

    public static final byte BASIL = -45;
    public static final byte BERGAMO = -46;
    public static final byte LAVENDER = -47;

    public static final byte SO4NM = -48;
    public static final byte SO3NM = -49;
    public static final byte SO2NM = -50;
    public static final byte SO1NM = -51;
    public static final byte TIEU_DOI_TRUONGNM = -52;
    public static final byte ROBIN = -53;

    // TDST
    public static final byte SO4 = -28;
    public static final byte SO3 = -29;
    public static final byte SO2 = -30;
    public static final byte SO1 = -31;
    public static final byte TIEU_DOI_TRUONG = -32;

    //id boss
    public static final byte BROLY = -1;
    public static final byte SUPER_BROLY = -2;
    public static final byte TRUNG_UY_TRANG = -3;
    public static final byte TRUNG_UY_XANH_LO = -4;
    public static final byte TRUNG_UD_THEP = -5;
    public static final byte NINJA_AO_TIM = -6;
    public static final byte NINJA_AO_TIM_FAKE_1 = -7;
    public static final byte NINJA_AO_TIM_FAKE_2 = -8;
    public static final byte NINJA_AO_TIM_FAKE_3 = -9;
    public static final byte NINJA_AO_TIM_FAKE_4 = -10;
    public static final byte NINJA_AO_TIM_FAKE_5 = -11;
    public static final byte NINJA_AO_TIM_FAKE_6 = -12;
    public static final byte ROBOT_VE_SI_1 = -13;
    public static final byte ROBOT_VE_SI_2 = -14;
    public static final byte ROBOT_VE_SI_3 = -15;
    public static final byte ROBOT_VE_SI_4 = -16;
    public static final byte XEN_BO_HUNG_1 = -17;
    public static final byte XEN_BO_HUNG_2 = -18;
    public static final byte XEN_BO_HUNG_HOAN_THIEN = -19;
    public static final byte XEN_BO_HUNG = -20;
    public static final byte SIEU_BO_HUNG = -22;
    public static final byte KUKU = -23;
    public static final byte MAP_DAU_DINH = -24;
    public static final byte RAMBO = -25;
    public static final byte COOLER = -26;
    public static final byte COOLER2 = -27;
    public static final byte FIDE_DAI_CA_1 = -33;
    public static final byte FIDE_DAI_CA_2 = -34;
    public static final byte FIDE_DAI_CA_3 = -35;
    public static final byte ANDROID_19 = -36;
    public static final byte ANDROID_20 = -37;
    public static final byte ANDROID_13 = -38;
    public static final byte ANDROID_14 = -39;
    public static final byte ANDROID_15 = -40;
    public static final byte PIC = -41;
    public static final byte POC = -42;
    public static final byte KINGKONG = -43;
    public static final byte SUPER_BROLY_RED = -44;
    public static final byte WHIS = -54;
    public static final byte BILL = -55;
    public static final byte CHILL = -56;
    public static final byte CHILL2 = -57;
    public static final byte BULMA = -58;
    public static final byte POCTHO = -59;
    public static final byte CHICHITHO = -60;
    public static final byte BLACKGOKU = -61;
    public static final byte SUPERBLACKGOKU = -62;
    public static final byte SANTA_CLAUS = -63;
    public static final byte MABU_MAP = -64;
    public static final byte SUPER_BU = -65;
    public static final byte BU_TENK = -66;
    public static final byte DRABULA_TANG1 = -67;
    public static final byte BUIBUI_TANG2 = -68;
    public static final byte BUIBUI_TANG3 = -69;
    public static final byte YACON_TANG4 = -70;
    public static final byte DRABULA_TANG5 = -71;
    public static final byte GOKU_TANG5 = -72;
    public static final byte CADIC_TANG5 = -73;
    public static final byte DRABULA_TANG6 = -74;
    public static final byte XEN_MAX = -75;
    public static final byte HOA_HONG = -76;
    public static final byte SOI_HEC_QUYN = -77;
    public static final byte O_DO = -78;
    public static final byte XINBATO = -79;
    public static final byte CHA_PA = -80;
    public static final byte PON_PUT = -81;
    public static final byte CHAN_XU = -82;
    public static final byte TAU_PAY_PAY = -83;
    public static final byte YAMCHA = -84;
    public static final byte JACKY_CHUN = -85;
    public static final byte THIEN_XIN_HANG = -86;
    public static final byte LIU_LIU = -87;
    public static final byte THIEN_XIN_HANG_CLONE = -88;
    public static final byte THIEN_XIN_HANG_CLONE1 = -89;
    public static final byte THIEN_XIN_HANG_CLONE2 = -90;
    public static final byte THIEN_XIN_HANG_CLONE3 = -91;
    public static final byte QILIN = -92;
    public static final byte NGO_KHONG = -93;
    public static final byte BAT_GIOI = -94;
    public static final byte FIDEGOLD = -95;
    public static final byte CUMBER = -96;
    public static final byte CUMBER2 = -97;

    public static final byte TAP_SU_1 = -104;
    public static final byte TAP_SU_2 = -105;
    public static final byte TAP_SU_3 = -106;
    public static final byte TAP_SU_4 = -107;
    public static final byte TAP_SU_5 = -108;

    public static final byte TAN_BINH_1 = -109;
    public static final byte TAN_BINH_2 = -110;
    public static final byte TAN_BINH_3 = -111;
    public static final byte TAN_BINH_4 = -112;
    public static final byte TAN_BINH_5 = -113;
    public static final byte TAN_BINH_6 = -114;

    public static final byte DOI_TRUONG_1 = -115;

    public static final byte CHIEN_BINH_1 = -98;
    public static final byte CHIEN_BINH_2 = -99;
    public static final byte CHIEN_BINH_3 = -100;
    public static final byte CHIEN_BINH_4 = -101;
    public static final byte CHIEN_BINH_5 = -102;
    public static final byte CHIEN_BINH_6 = -103;

    public static final byte THO_DAI_KA = -110;

    public static final byte KID_BU = -111;
    public static final byte BU_HAN = -112;
    public static final byte XEN_CON = -122;

    public static final byte RAITY = -123;

    public static final byte MA_TROI = -124;
    public static final byte BO_XUONG = -125;
    public static final byte DOI_NHI = -126;
    public static final byte WHIS_TOP = -127;

    public static final byte CLONE_PLAYER = -128;

    public static final int NOEL_BOSS_ONE = -129;
    public static final int NOEL_BOSS_TWO = -130;
    public static final int NOEL_BOSS_BALL = -131;
    public static final int NOEL_BOSS_BALL_2 = -132;
    public static final int NOEL_BOSS_BALL_3 = -133;
    public static final int NOEL_BOSS_BALL_4 = -134;
    public static final int NOEL_BOSS_BALL_5 = -135;

    public static final int CLONE_NHAN_BAN = -136;
    public static final int OMEGA_PLUS = -137;

    public static final int OMEGA_PLUS_DOUBLE = -138;
    public static final int SATAN_KING = -139;
    public static final int DRACULA_HUT_MAU = -140;
    public static final int THAN_MEO_KARIN = -141;
    public static final int PICOLO_DEMON_KING = -142;
    public static final int MECHA_ROBOT = -143;
    public static final int SAIBAMEN = -144;
    public static final int SAIBAMEN_ONE = -145;
    public static final int SAIBAMEN_TWO = -146;
    public static final int SAIBAMEN_THREE = -147;
    public static final int CON_XEN = -148;
    public static final int VEGETA = -149;

    public static final int KUKU_NAMEC = -150;
    public static final int RAMBO_NAMEC = -151;
    public static final int MAP_DAU_BUOI_NAMEC = -152;

    public static final int XEN_CON_ONE = -153;
    public static final int XEN_CON_TWO = -154;
    public static final int XEN_CON_THREE = -155;
    public static final int WHIS_BILL = -156;
    public static final int HIT = -157;
    public static final int DR_HACHI = -158;
    public static final int DR_LEE_CHE = -159;
    public static final int YA_CON = -160;
    public static final int XINBATOR = -161;
    public static final int ODO = -162;
    public static final int PANDA = -163;
    public static final int BROLY_XANH = -164;
    public static final int ZAMASU = -165;
    public static final int TOPPO = -166;
    public static final int BUIBUI = -167;
    public static final int ZAMAS = -168;

    public static final int CELLMINI = -169;
    public static final int FIDEMINI = -170;
    public static final int BUBU = -171;
    public static final int BILLCON = -172;
    public static final int TIENCA = -173;
    public static final int LOPOCO = -174;
    public static final int MAXUONG = -175;
    public static final int MABU_COM = -176;
    public static final int MECHA_SAT = -177;
    public static final int THAN_MEO_KARIN_V2 = -178;
    public static final int KAMI_OREN_ONE = -180;
    public static final int KAMI_OREN_TWO = -181;
    public static final int MAVUONG_ONE = -182;
    public static final int MAVUONG_TWO = -183;
    public static final int MAVUONG_THREE = -184;
    public static final int KING_COOLER = -185;
    public static final int TUAN_LOC_ONE = -186;
    public static final int TUAN_LOC_TWO = -187;
    public static final int TUAN_LOC_THREE = -188;
    public static final int TUAN_LOC_FOUR = -189;
    public static final int TUAN_LOC_FIVE = -190;
    public static final int TUAN_LOC_SIX = -191;
    public static final int TUAN_LOC_SEVEN = -192;
    public static final int TUAN_LOC_EIGHT = -193;
    public static final int TUAN_LOC_NINE = -194;
    public static final int TUAN_LOC_TEN = -195;
    public static final int COOLER_VANG = -196;
    public static final int ZENO = -197;
    public static final int LAN_CON = -198;
    public static final int MEO_THAN_TAI = -199;
    public static final int BLACK_GOKU = -200;
    public static final int BLACK_GOKU_PINK = -201;
    public static final int DUONG_TANG = -202;
    public static final int ZAMAS2 = -116788;

    public static final int ZAMAS3 = -116789;
    public static final int OREN = -116790;

    public static final int NGOK = -116791;

    public static final int BATGIOI = -116792;

    public static final int HATCHIJACK = -19062006;
    public static final int DR_LYCHEE = -19062007;

    private static final Logger logger = Logger.getLogger(BossFactory.class);
    public static final int[] MAP_APPEARED_QILIN = {ConstMap.VACH_NUI_ARU_42, ConstMap.VACH_NUI_MOORI_43, ConstMap.VACH_NUI_KAKAROT,
        ConstMap.LANG_ARU, ConstMap.LANG_MORI, ConstMap.LANG_KAKAROT, ConstMap.DOI_HOA_CUC, ConstMap.DOI_NAM_TIM, ConstMap.DOI_HOANG,
        ConstMap.TRAM_TAU_VU_TRU, ConstMap.TRAM_TAU_VU_TRU_25, ConstMap.TRAM_TAU_VU_TRU_26, ConstMap.LANG_PLANT, ConstMap.RUNG_NGUYEN_SINH,
        ConstMap.RUNG_CO, ConstMap.RUNG_THONG_XAYDA, ConstMap.RUNG_DA, ConstMap.THUNG_LUNG_DEN, ConstMap.BO_VUC_DEN, ConstMap.THANH_PHO_VEGETA,
        ConstMap.THUNG_LUNG_TRE, ConstMap.RUNG_NAM, ConstMap.RUNG_BAMBOO, ConstMap.RUNG_XUONG, ConstMap.RUNG_DUONG_XI, ConstMap.NAM_KAME,
        ConstMap.DAO_BULONG, ConstMap.DONG_KARIN, ConstMap.THI_TRAN_MOORI, ConstMap.THUNG_LUNG_MAIMA, ConstMap.NUI_HOA_TIM, ConstMap.NUI_HOA_VANG,
        ConstMap.NAM_GURU, ConstMap.DONG_NAM_GURU, ConstMap.THUNG_LUNG_NAMEC
    };

    private BossFactory() {

    }

    public static boolean isYar(byte id) {
        return (id == TAP_SU_1 || id == TAP_SU_2 || id == TAP_SU_3 || id == TAP_SU_4 || id == TAP_SU_5 || id == TAN_BINH_1 || id == TAN_BINH_2
                || id == TAN_BINH_3 || id == TAN_BINH_4 || id == TAN_BINH_5 || id == TAN_BINH_6 || id == DOI_TRUONG_1 || id == CHIEN_BINH_1 || id == CHIEN_BINH_2
                || id == CHIEN_BINH_3 || id == CHIEN_BINH_4 || id == CHIEN_BINH_5 || id == CHIEN_BINH_6 || id == TRUNG_UY_XANH_LO);
    }

    public static boolean isDoanhTrai(Boss boss) {
        return boss instanceof BossDoanhTrai;
    }

    public static boolean isTuanLoc(Boss boss) {
        return boss instanceof TuanLoc;
    }

    // ekko kiểm tra boss map mabu
    public static boolean isMabuWar(Boss boss) {
        return boss instanceof BossMabuWar;
    }

    public static boolean iszm(int id) {
        return id == ZAMAS;
    }

    public static void initBoss() {
        new Thread(() -> {
            try {
                createBoss(BILLCON);
                createBoss(CELLMINI);
                createBoss(FIDEMINI);
                createBoss(BUBU);
                createBoss(MAP_DAU_BUOI_NAMEC);
                createBoss(KUKU_NAMEC);
                createBoss(RAMBO_NAMEC);
                createBoss(BASIL);
                createBoss(BLACKGOKU);
                createBoss(CHILL);
//                createBoss(WHIS);
//                createBoss(WHIS_BILL);
                createBoss(HIT);
                createBoss(DR_HACHI);
                createBoss(DR_LEE_CHE);
                createBoss(XINBATOR);
                createBoss(ODO);
                createBoss(ZAMASU);
                createBoss(BROLY_XANH);
                createBoss(YA_CON);
                createBoss(RAITY);
                createBoss(COOLER);
                createBoss(XEN_BO_HUNG);
                createBoss(KUKU);
                createBoss(MAP_DAU_DINH);
                createBoss(RAMBO);
                createBoss(FIDE_DAI_CA_1);
                createBoss(ANDROID_20);
                createBoss(KINGKONG);
                createBoss(XEN_BO_HUNG_1);
                createBoss(OMEGA_PLUS);
                createBoss(TIEU_DOI_TRUONG);
                createBoss(THO_DAI_KA);
                createBoss(BUIBUI);
                createBoss(MABU_COM);
                createBoss(MECHA_SAT);
                createBoss(THAN_MEO_KARIN_V2);
                createBoss(PICOLO_DEMON_KING);
                createBoss(KAMI_OREN_ONE);
                createBoss(MAVUONG_ONE);
                createBoss(KING_COOLER);
//                createBoss(ZENO);
//                createBoss(BLACK_GOKU);
//                createBoss(NGO_KHONG);
//                createBoss(DUONG_TANG);
//                createBoss(BLACK_GOKU);
                for (int i = 0; i < 2; i++) {
                    createBoss(TOPPO);
                    createBoss(KUKU);
                    createBoss(XINBATOR);
                    createBoss(MAP_DAU_DINH);
                    createBoss(RAMBO);
                    createBoss(ODO);
                    createBoss(PANDA);
                }
//                for (int i = 0; i < 3; i++) {
//                    createBoss(LAN_CON);
//                    createBoss(THAN_MEO_KARIN);
//                }
            } catch (Exception e) {
                logger.error("Err initboss", e);
            }
        }).start();
    }

    public static void initBossMabuWar14H() {
        new Thread(() -> {
            Map map = MapService.gI().getMapById(114);
            for (Zone zone : map.zones) {
                Boss boss = new Mabu_14H(114, zone.zoneId);
                MabuWar14h.gI().bosses.add(boss);
            }
            map = MapService.gI().getMapById(128);
            for (Zone zone : map.zones) {
                Boss boss = new SuperBu_14H(128, zone.zoneId);
                MabuWar14h.gI().bosses.add(boss);
            }
        }).start();
    }

    public static void initBossnguhanhson() {
        new Thread(() -> {
            for (short mapid : BossData.DRABULA_TANG1.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang1(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.DRABULA_TANG6.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang6(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.GOKU_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Goku_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.CALICH_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Calich_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG2.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang2(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG3.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang3(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.YACON_TANG4.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Yacon_Tang4(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
        }).start();
    }
    public static void initBossMabuWar() {
        new Thread(() -> {
            for (short mapid : BossData.DRABULA_TANG1.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang1(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.DRABULA_TANG6.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Drabula_Tang6(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.GOKU_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Goku_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.CALICH_TANG5.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Calich_Tang5(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG2.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang2(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.BUIBUI_TANG3.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new BuiBui_Tang3(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
            for (short mapid : BossData.YACON_TANG4.mapJoin) {
                Map map = MapService.gI().getMapById(mapid);
                for (Zone zone : map.zones) {
                    Boss boss = new Yacon_Tang4(mapid, zone.zoneId);
                    MabuWar.gI().bosses.add(boss);
                }
            }
        }).start();
    }

    public static Boss createBoss(int bossId) {
        Boss boss = null;
        switch (bossId) {
//            case THAN_MEO_KARIN:
//                boss = new CatGodKarin();
//                break;
//            case BROLY:
//                boss = new Broly();
//                break;
            case ZAMAS3:
                boss = new zamas3();
                break;
//            case BATGIOI:
//                boss = new batgioi();
//                break;
//            case NGOK:
//                boss = new ngok();
//                break;
            case KAMI_OREN_ONE:
                boss = new KamiOrenOne();
                break;
            case TUAN_LOC_ONE:
                boss = new TuanLocOne();
                break;
            case LAVENDER:
                boss = new Lavender();
                break;
            case TIENCA:
                boss = new tienca();
                break;
            case BILLCON:
                boss = new billcon();
                break;
            case CELLMINI:
                boss = new bubu();
                break;
            case FIDEMINI:
                boss = new fidemini();
                break;
            case BUBU:
                boss = new cellmini();
                break;
            case BERGAMO:
                boss = new Bergamo();
                break;
            case SUPER_BROLY:
                boss = new SuperBroly();
                break;
            case BASIL:
                boss = new Basil();
                break;
            case CUMBER:
                boss = new Cumber();
                break;
            case CUMBER2:
                boss = new SuperCumber();
                break;
            case XEN_BO_HUNG_1:
                boss = new XenBoHung1();
                break;
            case XEN_BO_HUNG_2:
                boss = new XenBoHung2();
                break;
            case XEN_BO_HUNG_HOAN_THIEN:
                boss = new XenBoHungHoanThien();
                break;
            case XEN_CON:
                boss = new XenCon();
                break;
            case XEN_BO_HUNG:
                boss = new XenBoHung();
                break;
            case SIEU_BO_HUNG:
                boss = new SieuBoHung();
                break;
            case KUKU:
                boss = new Kuku();
                break;
            case MAP_DAU_DINH:
                boss = new MapDauDinh();
                break;
            case RAMBO:
                boss = new Rambo();
                break;
            case COOLER:
                boss = new Cooler();
                break;
            case COOLER2:
                boss = new Cooler2();
                break;
            case SO4:
                boss = new So4();
                break;
            case SO3:
                boss = new So3();
                break;
//            case DRACULA_HUT_MAU:
//                boss = new DraculaHutMau();
//                break;
//            case SATAN_KING:
//                boss = new SatanKing();
//                break;
//            case OMEGA_PLUS:
//                boss = new OmegaPlus();
//                break;
            case SO2:
                boss = new So2();
                break;
            case SO1:
                boss = new So1();
                break;
            case TIEU_DOI_TRUONG:
                boss = new TieuDoiTruong();
                break;
            case FIDE_DAI_CA_1:
                boss = new FideDaiCa1();
                break;
            case FIDE_DAI_CA_2:
                boss = new FideDaiCa2();
                break;
            case FIDE_DAI_CA_3:
                boss = new FideDaiCa3();
                break;
            case ANDROID_19:
                boss = new Android19();
                break;
            case ANDROID_20:
                boss = new Android20();
                break;
            case POC:
                boss = new Poc();
                break;
            case PIC:
                boss = new Pic();
                break;
            case KINGKONG:
                boss = new KingKong();
                break;
            case RAITY:
                boss = new Raity();
                break;
            case THO_DAI_KA:
                boss = new ThoDaiKa();
                break;
            case BUIBUI:
                boss = new BuiBui();
                break;
            case TOPPO:
                boss = new Toppo();
                break;
            case ZAMASU:
                boss = new ZamasuGod();
                break;
            case BROLY_XANH:
                boss = new BrolyXanh();
                break;
            case PANDA:
                boss = new Panda();
                break;
            case ODO:
                boss = new OdoVailone();
                break;
            case XINBATOR:
                boss = new Xinbator();
                break;
            case YA_CON:
                boss = new Yacon();
                break;
            case DR_LEE_CHE:
                boss = new DrLeeChee();
                break;
            case DR_HACHI:
                boss = new DrHachijack();
                break;
            case HIT:
                boss = new Hit();
                break;
            case WHIS_BILL:
                boss = new WhisBill();
                break;
            case WHIS:
                boss = new Whis();
                break;
            case BILL:
                boss = new Bill();
                break;
            case CHILL:
                boss = new Chill();
                break;
            case CHILL2:
                boss = new Chill2();
                break;
            case BLACKGOKU:
                boss = new Blackgoku();
                break;
            case SUPERBLACKGOKU:
                boss = new Superblackgoku();
                break;
            case MABU_MAP:
                boss = new Mabu_Tang6();
                break;
//            case FIDEGOLD:
//                boss = new FideGold();
//                break;
            case PICOLO_DEMON_KING:
                boss = new PicoloDemonKing();
                break;
            case ZAMAS:
                boss = new Zamasu();
                break;
            case MAXUONG:
                boss = new MaXuong();
                break;
            case MABU_COM:
                boss = new MaBuCom();
                break;
            case MECHA_SAT:
                boss = new MechaSat();
                break;
            case THAN_MEO_KARIN_V2:
                boss = new ThanMeoKarinV2();
                break;
            case MAVUONG_ONE:
                boss = new MaVuongOne();
                break;
            case KING_COOLER:
                boss = new KingCooler();
                break;
            case ZENO:
                boss = new Zeno();
                break;
            case LAN_CON:
                boss = new LanCon();
                break;
            case THAN_MEO_KARIN:
                boss = new MeoThanTai();
                break;
            case BLACK_GOKU:
                boss = new BlackGoku();
                break;
            case BLACK_GOKU_PINK:
                boss = new BlackGokuPink();
                break;
            case NGO_KHONG:
                boss = new NgoKhong();
                break;
            case DUONG_TANG:
                boss = new DuongTang();
                break;
        }
        return boss;
    }

    public static Boss createWhisBoss(long bossId, int level, long player_id) {
        return new WhisTop(bossId, level, player_id);
    }

    public static Boss createBossNhanBan(Player player, BossData data) {
        return new NhanBan(player, data);
    }

    public static NoelBoss createNoelBoss(long bossId, Player player) {
        NoelBoss boss = null;
        switch ((int) bossId) {
            case NOEL_BOSS_ONE -> {
                boss = new NoelBossOne();
            }
            case NOEL_BOSS_TWO -> {
                boss = new NoelBossTwo();
            }
        }
        if (boss != null) {
            boss.setStatus(Boss.ATTACK);
            boss.zone = player.zone;
            boss.typePk = ConstPlayer.PK_ALL;
            boss.location.x = player.location.x;
            boss.location.y = player.location.y;
            boss.joinMap();
            boss.AddPlayerCanAttack(player);
        }
        return boss;
    }

    public static NoelBossBall createNoelBossBall(long bossId, Player player, Boss baseBoss) {
        NoelBossBall boss = null;

        boss = new NoelBossBall(bossId, player);
        if (boss != null) {
            boss.setStatus(Boss.ATTACK);
            boss.zone = player.zone;
            boss.typePk = ConstPlayer.NON_PK;
            boss.location.x = baseBoss.location.x;
            boss.location.y = baseBoss.location.y;
            boss.joinMap();
        }

        return boss;
    }


}
