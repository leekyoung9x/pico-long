package nro.models.player;

import lombok.Getter;
import lombok.Setter;
import nro.card.Card;
import nro.card.CollectionBook;
import nro.consts.ConstMap;
import nro.consts.ConstPet;
import nro.consts.ConstPlayer;
import nro.consts.ConstTask;
import nro.data.DataGame;
import nro.dialog.ConfirmDialog;
import nro.models.boss.event.EscortedBoss;
import nro.models.boss.event.noel.NoelBossBall;
import nro.models.clan.Buff;
import nro.models.clan.Clan;
import nro.models.clan.ClanMember;
import nro.models.intrinsic.IntrinsicPlayer;
import nro.models.item.CaiTrang;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.item.ItemTime;
import nro.models.map.ItemMap;
import nro.models.map.TrapMap;
import nro.models.map.Zone;
import nro.models.map.mabu.MabuWar;
import nro.models.map.mabu.MabuWar14h;
import nro.models.map.war.BlackBallWar;
import nro.models.map.war.NamekBallWar;
import nro.models.mob.MobMe;
import nro.models.npc.specialnpc.BillEgg;
import nro.models.npc.specialnpc.EggLinhThu;
import nro.models.npc.specialnpc.MabuEgg;
import nro.models.npc.specialnpc.MagicTree;
import nro.models.phuban.DragonNamecWar.TranhNgocService;
import nro.models.pvp.PVP;
import nro.models.skill.PlayerSkill;
import nro.models.task.TaskPlayer;
import nro.server.Client;
import nro.server.Manager;
import nro.server.io.Message;
import nro.server.io.Session;
import nro.services.*;
import nro.services.func.ChangeMapService;
import nro.services.func.CombineNew;
import nro.services.func.PVPServcice;
import nro.utils.Log;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import nro.models.skill.PlayerNewSkill;

/**
 * Arriety
 */
public class Player {

    public PVP pvp = new PVP() {
        @Override
        public void sendResultMatch(Player winer, Player loser, byte typeWin) {

        }

        @Override
        public void reward(Player plWin) {

        }
    };

    public int maxTime;
    public byte type;
    public long lastTimeHoiSinh;
    public int partDanhHieu;
    public boolean titleitem;
    public boolean isMapPk;
    public BillEgg billEgg;
    public EggLinhThu egglinhthu;
    public short lvpet;
    public long lastTimeCTG;
    public long lastTimeChat;
    public boolean isAttackDHVT23 = false;
    public PlayerNewSkill newSkill;
    public SkillSpecial skillSpecial;
    public List<Integer> idEffChar = new ArrayList<>();
    public boolean haveShiba;
    public int server;
    public byte[] buyLimit;
    public boolean isLinhThuFollow;
    public long dameAttack = 1000;
    public long lastTimeUpdateBallWar;
    public long lastTimeChatTheGioi;
    public PlayerEvent event;
    public List<String> textRuongGo = new ArrayList<>();
    public boolean receivedWoodChest;
    public int goldChallenge;
    public int levelWoodChest;
    public boolean isInvisible;
    public boolean sendMenuGotoNextFloorMabuWar;
    public long lastTimeBabiday;
    public long lastTimeChangeZone;
    public long lastTimeChatGlobal;
    public long lastTimeChatPrivate;
    public long lastTimeChangeMap;

    public Date firstTimeLogin;
    public boolean canGeFirstTimeLogin;

    private Session session;
    public byte countSaveFail;
    public boolean beforeDispose;

    public long timeFixInventory;
    public boolean isPet;
    public boolean isBoss;
    public boolean isMiniPet;

    public int maintain;

    public int playerTradeId = -1;
    public Player playerTrade;

    public int pointShiba;
    public int diem_skien;

    public int mapIdBeforeLogout;
    public List<Zone> mapBlackBall;
    public Zone zone;
    public Zone mapBeforeCapsule;
    public List<Zone> mapCapsule;
    public Pet pet;
    public MiniPet minipet;

    public long lastTimeNotifyTimeHoldBlackBall;
    public long lastTimeHoldBlackBall;
    public int tempIdBlackBallHold = -1;
    public boolean isHoldBlackBall;
    public boolean isHoldNamecBall;
    public boolean isHoldNamecBallTranhDoat;
    public int tempIdNamecBallHoldTranhDoat = -1;

    public MobMe mobMe;
    public Location location;
    public SetClothes setClothes;
    public EffectSkill effectSkill;
    public MabuEgg mabuEgg;
    public TaskPlayer playerTask;
    public ItemTime itemTime;
    public Fusion fusion;
    public MagicTree magicTree;
    public IntrinsicPlayer playerIntrinsic;
    public Inventory inventory;
    public PlayerSkill playerSkill;
    public CombineNew combineNew;
    public IDMark iDMark;
    public Charms charms;
    public EffectSkin effectSkin;
    public Gift gift;
    public NPoint nPoint;
    public RewardBlackBall rewardBlackBall;
    public EffectFlagBag effectFlagBag;

    public Clan clan;
    public ClanMember clanMember;

    public ListFriendEnemy<Friend> friends;
    public ListFriendEnemy<Enemy> enemies;

    protected boolean actived = false;
    public boolean loaded;

    public long id;
    public String name;
    public byte gender;// td 0, xd 1, nm 2
    public boolean isNewMember = true;
    public short head;

    public byte typePk;
    public boolean BuffNormel;
    public byte cFlag;
    public long lastTimeChangeFlag;
    public long lastTimeTrade;

    public boolean haveTennisSpaceShip;
    private byte useSpaceShip;

    public boolean isGoHome;
    public boolean isPhu;

    public boolean justRevived;
    public long lastTimeRevived;
    public boolean immortal;

    public long lastTimeBan;
    public long lastTimeUpdate;
    public boolean isBan;

    public boolean isGotoFuture;
    public long lastTimeGoToFuture;
    public boolean isgotoPrimaryForest;
    public long lastTimePrimaryForest;

    public boolean isGoToBDKB;
    public long lastTimeGoToBDKB;
    public long lastTimeAnXienTrapBDKB;
    private short powerPoint;
    private short percentPowerPont;

    public long lastTimePickItem;
    public int tongnap;
    public boolean isPending = false;

    public ArrayList<Integer> data_tong_nap = new ArrayList<>();

    // thông tin nhận đồ hàng ngày
    public PlayerReward playerReward;

    public int diemboss;
    @Setter
    @Getter
    private CollectionBook collectionBook;
    @Getter
    @Setter
    private boolean isSaving, isDisposed;
    @Getter
    @Setter
    private boolean interactWithKarin;
    @Getter
    @Setter
    private EscortedBoss escortedBoss;
    @Setter
    @Getter
    private ConfirmDialog confirmDialog;
    @Getter
    @Setter
    public byte[] rewardLimit;
    @Setter
    @Getter
    private PetFollow petFollow;
    @Setter
    @Getter
    private Buff buff;

    @Setter
    @Getter
    private LocalDateTime timeCache;

    public Player() {
        location = new Location();
        nPoint = new NPoint(this);
        inventory = new Inventory(this);
        playerSkill = new PlayerSkill(this);
        setClothes = new SetClothes(this);
        effectSkill = new EffectSkill(this);
        fusion = new Fusion(this);
        playerIntrinsic = new IntrinsicPlayer(this);
        rewardBlackBall = new RewardBlackBall(this);
        effectFlagBag = new EffectFlagBag(this);
        skillSpecial = new SkillSpecial(this);
        newSkill = new PlayerNewSkill();
        //----------------------------------------------------------------------
        iDMark = new IDMark();
        combineNew = new CombineNew();
        playerTask = new TaskPlayer(this);
        friends = new ListFriendEnemy<>(this);
        enemies = new ListFriendEnemy<>(this);
        itemTime = new ItemTime(this);
        charms = new Charms(this);
        gift = new Gift(this);
        effectSkin = new EffectSkin(this);
        event = new PlayerEvent(this);
        buyLimit = new byte[13];
        buff = Buff.NONE;
        data_tong_nap.add(0);
        data_tong_nap.add(0);
        data_tong_nap.add(0);
    }

    //--------------------------------------------------------------------------
    public short getPowerPoint() {
        return powerPoint;
    }

    public void addPowerPoint(int value) {
        powerPoint += value;
    }

    public short getPercentPowerPont() {
        return percentPowerPont;
    }

    public void addPercentPowerPoint(int value) {
        percentPowerPont += value;
    }

    public void resetPowerPoint() {
        percentPowerPont = 0;
        powerPoint = 0;
    }

    public void setUseSpaceShip(byte useSpaceShip) {
        // 0 - không dùng
        // 1 - tàu vũ trụ theo hành tinh
        // 2 - dịch chuyển tức thời
        // 3 - tàu tenis
        this.useSpaceShip = useSpaceShip;
    }

    public byte getUseSpaceShip() {
        return this.useSpaceShip;
    }

    public boolean isDie() {
        if (this.nPoint != null) {
            return this.nPoint.hp <= 0;
        } else {
            return true;
        }
    }

    //--------------------------------------------------------------------------
    public void setSession(Session session) {
        this.session = session;
    }

    public void sendMessage(Message msg) {
        if (this.session != null) {
            session.sendMessage(msg);
        }
    }

    public Session getSession() {
        return this.session;
    }

    public boolean isPl() {
        return !isPet && !isBoss && !isMiniPet;
    }

    public int version() {
        return session.version;
    }

    public boolean isVersionAbove(int version) {
        return version() >= version;
    }

    public void update() {
        if (!this.beforeDispose) {
            try {
                if (!isBan) {
                    if (nPoint != null) {
                        nPoint.update();
                    }
                    if (fusion != null) {
                        fusion.update();
                    }
                    if (effectSkin != null) {
                        effectSkill.update();
                    }
                    if (mobMe != null) {
                        mobMe.update();
                    }
                    if (effectSkin != null) {
                        effectSkin.update();
                    }
                    if (pet != null) {
                        pet.update();
                    }
                    if (minipet != null) {
                        minipet.update();
                    }
                    if (magicTree != null) {
                        magicTree.update();
                    }
                    if (itemTime != null) {
                        itemTime.update();
                    }
                    if (event != null) {
                        event.update();
                    }
                    UpdateEffChar.getInstance().updateEff(this);
                    BlackBallWar.gI().update(this);
                    if (this.isPl()) {
                        MabuWar.gI().update(this);
                        MabuWar14h.gI().update(this);
                        checkPlayerInMap();

                    }
                    if (isGotoFuture && Util.canDoWithTime(lastTimeGoToFuture, 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 102, -1, Util.nextInt(60, 200));
                        this.isGotoFuture = false;
                    }
                    if (isGoToBDKB && Util.canDoWithTime(lastTimeGoToBDKB, 6000)) {
                        ChangeMapService.gI().changeMapBySpaceShip(this, 135, -1, 35);
                        this.isGoToBDKB = false;
                    }
                    if (isgotoPrimaryForest && Util.canDoWithTime(lastTimePrimaryForest, 6000)) {
                        ChangeMapService.gI().changeMap(this, 161, -1, 169, 312);
                        this.isgotoPrimaryForest = false;
                    }
                    if (this.zone != null) {
                        TrapMap trap = this.zone.isInTrap(this);
                        if (trap != null) {
                            trap.doPlayer(this);
                        }
                    }
                    ConstMap.now = LocalTime.now();
                    // admin thì không cần validate thời gian vào map ngũ hành sơn
                    if (!this.isBoss && this.session != null && !this.isAdmin()) {
                        // ekko đóng ngũ hành sơn
                        if (ConstMap.now.isBefore(ConstMap.start) || ConstMap.now.isAfter(ConstMap.end)) {
                            if (Objects.nonNull(zone) && Objects.nonNull(zone.map) && zone.map.mapId >= 122 && zone.map.mapId <= 124) {
                                ChangeMapService.gI().changeMap(this, 5, -1, 860, 200);
                                Service.getInstance().sendBigMessage(this, 1139,
                                        "|1|NGŨ HÀNH SƠN ĐÃ ĐÓNG");
                                Thread.sleep(1000);
                            }
                        }
                    }
                } else {
                    if (Util.canDoWithTime(lastTimeBan, 5000)) {
                        Client.gI().kickSession(session);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.error(Player.class, e, "Lỗi tại player: " + this.name);
            }
        }
    }

    public boolean checkHutMau() {
        if (this.nPoint.tlHutHp > 0 || this.nPoint.tlHutMp > 0 || this.nPoint.tlHutHpMob > 0) {
            return true;
        }
        return false;
    }

    private void checkPlayerInMap() {
        if (this != null && this.zone != null) {
            int mapid = this.zone.map.mapId;
            if (this.zone.map.mapId == 155 || this.zone.map.mapId == 153) {
                if (this.nPoint.power < 80_000_000_000L) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải 80 tỷ sức mạnh mới được qua map này");
                }
            }
            if (this.zone.map.mapId == 212) {
                if (this.nPoint.power < 100_000_000_000L) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải 100 tỷ sức mạnh mới được qua map này");
                }
            }
            if (this.zone.map.mapId == 139 || this.zone.map.mapId == 140) {
                if (this.nPoint.power < 90_000_000_000L) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải 90 tỷ sức mạnh mới được qua map này");
                }
            }
            if (MapService.gI().isMapHTTV(mapid)) {
                if (this.nPoint.power < 120_000_000_000L) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải trên 110 tỷ mới được vào map này");
                }
            }

            if (MapService.gI().isMapTuongLai(mapid)) {
                if (this.playerTask.taskMain.id < 21) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành xong nhiệm vụ Tiểu đội sát thủ mới qua được khu vực này");
                }
            }
            if (MapService.isMapDiaNguc(this.zone.map.mapId)) {
                if (this.playerTask.taskMain.id < 21) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành xong nhiệm vụ Tiểu đội sát thủ mới qua được khu vực này");
                }
            }

            if (MapService.gI().isMapHTTV(mapid)) {
                if (this.playerTask.taskMain.id < 25) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành nhiệm vụ nâng 10k sức đánh mới được đến khu vực này");
                }
            }
            if (MapService.gI().isMapHTTV(mapid)) {
                if (this.playerTask.taskMain.id < 24) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành nhiệm vụ Pic Poc King Kong mới được qua khu vực này");
                }
            }
            if (MapService.gI().isMapCold(this.zone.map)) {
                if (this.playerTask.taskMain.id < 25 && !Util.checkTimeInRange(LocalDate.now(), "2024-10-24", "2024-11-05")) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành nhiệm vụ nâng 10k sức đánh mới được đến khu vực này");
                }
            }
            if (MapService.gI().isMapYardart(mapid)) {
                if (this.playerTask.taskMain.id < 22) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn phải hoàn thành nhiệm vụ đánh bại Fide mới được đến khu vực này");
                }
            }
            if (MapService.gI().isMapQuestTDST(mapid)) {
                // hoàn thành nhiệm vụ TDST thì không được vào map có TDST
                // ekko khu 0 có TDST thì vẫn cho vào
                if (MapService.gI().isMapCoTieuDoiSatThu(this.zone) && this.playerTask.taskMain.id > 20 && this.zone.zoneId != 0) {
                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                    Service.getInstance().sendBigMessage(this, 1139,
                            "|1|Bạn đã hoàn thành nhiệm vụ Tiểu đội sát thủ nên không thể vào khu vực này");
                }
            }
//            if (MapService.gI().ismap212(mapid)) {
//                // ekko người chơi đạt 200 tỉ sức mạnh thì bị sút khỏi map
//                if (this.nPoint != null && this.nPoint.power >= 200_000_000_000L) {
//                    ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
//                    Service.getInstance().sendBigMessage(this, 1139,
//                            "|1|Bạn đã đạt 200 tỷ sức mạnh nên không thể vào khu vực này");
//                }
//            }
            if (MapService.gI().isMapHTTV(mapid)) {
                ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
                Service.getInstance().sendBigMessage(this, 1139,
                        "|1|Khu vực này đang bảo trì, vui lòng quay lại sau");
            }
        }
    }

    private void checkLocation() {
        if (this.location.x > this.zone.map.mapWidth || this.location.x < 0
                || this.location.y > this.zone.map.mapHeight || this.location.y < 0) {
            if (this.inventory.gold >= 500000000) {
                this.inventory.subGold(500000000);
            } else {
                this.inventory.gold = 0;
            }
            PlayerService.gI().sendInfoHpMpMoney(this);
            ChangeMapService.gI().changeMapNonSpaceship(this, this.gender + 21, 400, 336);
            Service.getInstance().sendBigMessage(this, 1139, "|1|Do phát hiện có hành vi bất thường nên\n "
                    + "chúng tôi đã đưa bạn về nhà và xử phạt 500Tr vàng\n"
                    + "|7|nếu còn tiếp tục tái phạm sẽ khóa vĩnh viễn");
        }
    }

    //--------------------------------------------------------------------------
    /*
     * {380, 381, 382}: ht lưỡng long nhất thể xayda trái đất
     * {383, 384, 385}: ht porata xayda trái đất
     * {391, 392, 393}: ht namếc
     * {870, 871, 872}: ht c2 trái đất
     * {873, 874, 875}: ht c2 namếc
     * {867, 878, 869}: ht c2 xayda
     */
    private static final short[][] idOutfitFusion = {
        {380, 381, 382},//ht lưỡng long nhất thể xayda trái đất
        {383, 384, 385},//ht porata xayda trái đất
        {391, 392, 393},//ht namếc

        {870, 871, 872},//ht c2 trái đất
        {873, 874, 875},//ht c2 namếc
        {867, 868, 869},//ht c2 xayda

        {1332, 1333, 1334},//ht c3 trái đất
        {1329, 1330, 1331},//ht c3 namếc
        {1335, 1336, 1337},//ht c3 xayda

        {1440, 1441, 1442},//ht c3 trái đất
        {1446, 1447, 1448},//ht c3 namếc
        {1443, 1444, 1445}//ht c3 xayda
    };

    public byte getEffFront() {
        if (this.inventory.itemsBody.isEmpty() || this.inventory.itemsBody.size() < 10) {
            return -1;
        }
        Item caitrang = this.inventory.itemsBody.get(5);
        if (!caitrang.isNotNullItem()) {
            return -1;
        }
        switch (caitrang.template.id) {
            case 2061:
                return 8;
        }
        int[] levels = new int[5];
        ItemOption[] options = new ItemOption[5];
        Item[] items = new Item[]{
            this.inventory.itemsBody.get(0),
            this.inventory.itemsBody.get(1),
            this.inventory.itemsBody.get(2),
            this.inventory.itemsBody.get(3),
            this.inventory.itemsBody.get(4)
        };
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            for (ItemOption io : item.itemOptions) {
                if (io.optionTemplate.id == 72) {
                    levels[i] = io.param;
                    options[i] = io;
                    break;
                }
            }
        }
        int minLevel = Integer.MAX_VALUE;
        int count = 0;
        for (int level : levels) {
            if (level >= 4 && level <= 8) {
                minLevel = Math.min(minLevel, level);
                count++;
            }
        }
        if (count == 5) {
            return (byte) minLevel;
        } else {
            return -1;
        }

    }

    public byte getAura() {
        // Kiểm tra hào quang đặc biệt của người chơi
        for (PlayerSpecialAura aura : Manager.PLAYER_SPECIAL_AURA) {
            if (this.id == aura.player_id && aura.status == 1) {
                return (byte) aura.aura_id;
            }
        }

        if (this.setClothes.setMaThuat >= 5) {
            return 32;
        }

        // Kiểm tra hào quang theo sách bộ sưu tập
        CollectionBook book = getCollectionBook();
        boolean isVip = this.getSession() != null && this.getSession().actived >= 1;
        byte defaultAura = isVip ? (byte) Manager.AURA_VIP : -1;

        if (book != null) {
            Card card = book.getCards().stream()
                    .filter(t -> t.isUse() && t.getCardTemplate().getAura() != -1)
                    .findAny()
                    .orElse(null);
            if (card != null) {
                return (byte) card.getCardTemplate().getAura();
            }
        }

        // Kiểm tra hào quang từ loại hợp thể và giới tính
        if (this.fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
            return switch (this.gender) {
                case ConstPlayer.NAMEC -> 26;
                case ConstPlayer.XAYDA -> 27;
                case ConstPlayer.TRAI_DAT -> 25;
                default -> defaultAura;
            };
        }

        // Kiểm tra hào quang từ vật phẩm trong túi đồ
        if (this.inventory.itemsBody.size() >= 10) {
            Item item = this.inventory.itemsBody.get(5);
            if (item.isNotNullItem()) {
                if (item.template.id == 2064) return 2;
            } else {
                return defaultAura;
            }
        } else {
            return defaultAura;
        }

        // Kiểm tra hào quang theo id hoặc loại thú cưng
        if (this.id == 54 || this.id == 7636 || this.id == 8761) {
            return 4;
        } else if (this.pet != null) {
            switch (this.pet.typePet) {
                case ConstPet.BILL_CON -> {
                    return 18;
                }
                case ConstPet.WHIS -> {
                    if (this.pet.lever >= 7) return 1;
                }
                case ConstPet.ZENO -> {
                    if (this.pet.lever >= 7) return 12;
                }
                case ConstPet.BLACK_GOKU -> {
                    if (this.pet.lever >= 7) return 12;
                }
            }
        }

        // Trả về hào quang VIP nếu là VIP hoặc hào quang mặc định
        return isVip ? (byte) Manager.AURA_VIP : defaultAura;
    }

    public boolean checkSkinFusion() {
        if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            Short idct = inventory.itemsBody.get(5).template.id;
            if (idct >= 601 && idct <= 603 || idct >= 639 && idct <= 641) {
                return true;
            }
        }
        return false;
    }

    public short getHead() {
        if (this.id == 1000000) {
            return 412;
        }
        if (effectSkill != null && effectSkill.isMonkey) {
            return (short) ConstPlayer.HEADMONKEY[effectSkill.levelMonkey - 1];
        } else if (this.itemTime.isMaTroi) {
            switch (this.itemTime.iconMaTroi) {
                case 6091:
                    return 651;
                case 5101:
                    return 545;
                case 6094:
                    return 654;
            }
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 412;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 454;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 545;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (checkSkinFusion()) {
                CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
                return (short) (ct.getID()[0] != -1 ? ct.getID()[0] : inventory.itemsBody.get(5).template.part);
            } else if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][0];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][0];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (checkSkinFusion()) {
                return this.head;
            }
            if (ct != null) {
                return (short) (ct.getID()[0] != -1 ? ct.getID()[0] : inventory.itemsBody.get(5).template.part);
            }
        }
        return this.head;
    }

    public short getBody() {
        if (this.id == 1000000) {
            return 413;
        }
        if (effectSkill != null && effectSkill.isMonkey) {
            return 193;
        } else if (this.itemTime.isMaTroi) {
            switch (this.itemTime.iconMaTroi) {
                case 6091:
                    return 652;
                case 5101:
                    return 548;
                case 6094:
                    return 655;
            }
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 413;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 455;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 548;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (checkSkinFusion()) {
                CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
                return (short) ct.getID()[1];
            }
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][1];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][1];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (checkSkinFusion()) {
                if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
                    if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
                        return inventory.itemsBody.get(0).template.part;
                    }
                }
            }
            if (ct != null && ct.getID()[1] != -1) {
                return (short) ct.getID()[1];
            }
        }
        if (inventory != null && inventory.itemsBody.get(0).isNotNullItem()) {
            return inventory.itemsBody.get(0).template.part;
        }
        return (short) (gender == ConstPlayer.NAMEC ? 59 : 57);
    }

    public short getLeg() {
        if (this.id == 1000000) {
            return 414;
        }
        if (effectSkill != null && effectSkill.isMonkey) {
            return 194;
        } else if (this.itemTime.isMaTroi) {
            switch (this.itemTime.iconMaTroi) {
                case 6091:
                    return 653;
                case 5101:
                    return 549;
                case 6094:
                    return 656;
            }
        } else if (effectSkill != null && effectSkill.isSocola) {
            return 414;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 456;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 549;
        } else if (fusion != null && fusion.typeFusion != ConstPlayer.NON_FUSION) {
            if (checkSkinFusion()) {
                CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
                return (short) ct.getID()[2];
            }
            if (fusion.typeFusion == ConstPlayer.LUONG_LONG_NHAT_THE) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 0][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA) {
                return idOutfitFusion[this.gender == ConstPlayer.NAMEC ? 2 : 1][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA2) {
                return idOutfitFusion[3 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA3) {
                return idOutfitFusion[6 + this.gender][2];
            } else if (fusion.typeFusion == ConstPlayer.HOP_THE_PORATA4) {
                return idOutfitFusion[9 + this.gender][2];
            }
        } else if (inventory != null && inventory.itemsBody.get(5).isNotNullItem()) {
            if (checkSkinFusion()) {
                if (inventory != null && inventory.itemsBody.get(1).isNotNullItem()) {
                    return inventory.itemsBody.get(1).template.part;
                }
            }
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (ct != null && ct.getID()[2] != -1) {
                return (short) ct.getID()[2];
            }
        }
        if (inventory != null && inventory.itemsBody.get(1).isNotNullItem()) {
            return inventory.itemsBody.get(1).template.part;
        }
        return (short) (gender == 1 ? 60 : 58);
    }

    public short getFlagBag() {
        if (this.isHoldBlackBall) {
            return 31;
        } else if (this.isHoldNamecBall) {
            return 30;
        }
        if (this.inventory.itemsBody.size() >= 8) {
            if (this.inventory.itemsBody.get(7).isNotNullItem()) {
                return this.inventory.itemsBody.get(7).template.part;
            }
        }
        if (TaskService.gI().getIdTask(this) == ConstTask.TASK_3_2) {
            return 28;
        }
        if (this.clan != null) {
            return (short) this.clan.imgId;
        }
        return -1;
    }

    public short getMount() {
        if (this.isVersionAbove(220)) {
            for (Item item : inventory.itemsBody) {
                if (item.isNotNullItem()) {
                    if (item.template.type == 24) {
                        if (item.template.gender == 3 || item.template.gender == this.gender) {
                            return item.template.id;
                        } else {

                            return -1;
                        }
                    }
                    if (item.template.type == 23) {
                        if (item.template.id < 500) {
                            return item.template.id;
                        } else {
                            Object mount = DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
                            if (mount == null) {

                                return -1;
                            }
                            return (short) mount;
                        }
                    }
                }
            }
        } else {
            for (Item item : inventory.itemsBag) {
                if (item.isNotNullItem()) {
                    if (item.template.type == 24) {
                        if (item.template.gender == 3 || item.template.gender == this.gender) {
                            return item.template.id;
                        } else {

                            return -1;
                        }
                    }
                    if (item.template.type == 23) {
                        if (item.template.id < 500) {
                            return item.template.id;
                        } else {
                            Object mount = DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
                            if (mount == null) {

                                return -1;
                            }
                            return (short) mount;
                        }
                    }
                }
            }
        }
        if (this.isPet) {
            if (((Pet) this).master.isVersionAbove(220)) {
                for (Item item : inventory.itemsBody) {
                    if (item.isNotNullItem()) {
                        if (item.template.type == 24) {
                            if (item.template.gender == 3 || item.template.gender == this.gender) {
                                return item.template.id;
                            } else {

                                return -1;
                            }
                        }
                        if (item.template.type == 23) {
                            if (item.template.id < 500) {
                                return item.template.id;
                            } else {
                                Object mount = DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
                                if (mount == null) {

                                    return -1;
                                }
                                return (short) mount;
                            }
                        }
                    }
                }
            } else {
                for (Item item : inventory.itemsBag) {
                    if (item.isNotNullItem()) {
                        if (item.template.type == 24) {
                            if (item.template.gender == 3 || item.template.gender == this.gender) {
                                return item.template.id;
                            } else {

                                return -1;
                            }
                        }
                        if (item.template.type == 23) {
                            if (item.template.id < 500) {
                                return item.template.id;
                            } else {
                                Object mount = DataGame.MAP_MOUNT_NUM.get(String.valueOf(item.template.id));
                                if (mount == null) {

                                    return -1;
                                }
                                return (short) mount;
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private Integer GetDameByNoelBall(Player plAtt, int damage) {
        if (plAtt instanceof NoelBossBall) {
            if (isDie()) {
                if (plAtt != null && plAtt.zone != null) {
                    if (MapService.gI().isMapMabuWar(plAtt.zone.map.mapId) && MabuWar.gI().isTimeMabuWar()) {
                        plAtt.addPowerPoint(5);
                        Service.getInstance().sendPowerInfo(plAtt, "TL", plAtt.getPowerPoint());
                    }
                }
                setDie(plAtt);
            }
            return damage;
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.getSession() != null && this.isAdmin()) {
            return 0;
        }
        int mstChuong = this.nPoint.mstChuong;
        int giamst = this.nPoint.tlGiamst;

        if (!this.isDie()) {
            if (this.isMiniPet) {
                return 0;
            }
            if (plAtt != null) {
                if (!this.isBoss && plAtt.nPoint.xDameChuong && SkillUtil.isUseSkillChuong(plAtt)) {
                    damage = plAtt.nPoint.tlDameChuong * damage;
                    plAtt.nPoint.xDameChuong = false;
                }
                if (mstChuong > 0 && SkillUtil.isUseSkillChuong(plAtt)) {
                    PlayerService.gI().hoiPhuc(this, 0, damage * mstChuong / 100);
                    damage = 0;
                }
            }
            if (!SkillUtil.isUseSkillBoom(plAtt)) {
                int tile = this.nPoint.tlNeDon;
                if (this.nPoint.tlNeDon > 80) {
                    tile = 80;
                }
                if (!piercing && Util.isTrue(tile, 100)) {
                    return 0;
                }
            }
            if (isMobAttack && (this.charms.tdBatTu > System.currentTimeMillis() || this.itemTime.isMaTroi) && damage >= this.nPoint.hp) {
                damage = this.nPoint.hp - 1;
            }

//            if (plAtt.getSession() != null && plAtt.isAdmin()) {
//                damage = this.nPoint.hpMax;
//            }

            damage = this.nPoint.subDameInjureWithDeff(damage);
            if (!piercing && effectSkill.isShielding) {
                if (damage > nPoint.hpMax) {
                    EffectSkillService.gI().breakShield(this);
                }
                damage = 1;
            }
            if (isMobAttack && this.charms.tdBatTu > System.currentTimeMillis() && damage >= this.nPoint.hp) {
                damage = this.nPoint.hp - 1;
            }
            if (giamst > 0) {
                damage -= nPoint.calPercent(damage, giamst);
            }
            if (this.effectSkill.isHoldMabu) {
                damage = 1;
            }
            this.nPoint.subHP(damage);
            if (this.effectSkill.isHoldMabu && Util.isTrue(30, 150)) {
                Service.getInstance().removeMabuEat(this);
            }
            if (isDie()) {
                if (plAtt != null && plAtt.zone != null) {
                    if (MapService.gI().isMapMabuWar(plAtt.zone.map.mapId) && MabuWar.gI().isTimeMabuWar()) {
                        plAtt.addPowerPoint(5);
                        Service.getInstance().sendPowerInfo(plAtt, "TL", plAtt.getPowerPoint());
                    }
                }
                setDie(plAtt);
            }
            return damage;
        } else {
            return 0;
        }
    }

    public void setDie(Player plAtt) {
//        if (this.effectSkill.isMonkey) {
//            rewardDuoiKhi(plAtt);
//        }
        if (this.nPoint.power >= 1_000_000) {
            long powersub = this.nPoint.power / 10000;
            nPoint.powerSub(powersub);
        }
        //xóa phù
        if (this.effectSkin.xHPKI > 1) {
            this.effectSkin.xHPKI = 1;
            this.isPhu = false;
            Service.getInstance().point(this);
        }
        //xóa tụ skill đặc biệt
        this.playerSkill.prepareQCKK = false;
        this.playerSkill.prepareLaze = false;
        this.playerSkill.prepareTuSat = false;
        //xóa hiệu ứng skill
        this.effectSkill.removeSkillEffectWhenDie();
        //
        nPoint.setHp(0);
        nPoint.setMp(0);
        //xóa trứng
        if (this.mobMe != null) {
            this.mobMe.mobMeDie();
        }
        Service.getInstance().charDie(this);
        //add kẻ thù
        if (!this.isPet && !this.isBoss && plAtt != null && !plAtt.isPet && !plAtt.isBoss) {
            if (!plAtt.itemTime.isUseAnDanh) {
                FriendAndEnemyService.gI().addEnemy(this, plAtt);
            }
        }
        if (this.effectSkin.isSocola) {
            reward(plAtt);
        }
        if (this.zone != null) {
            if (MapService.gI().isMapMabuWar(this.zone.map.mapId)) {
                if (this.powerPoint < 20) {
                    this.powerPoint = 0;
                }
                if (this.percentPowerPont < 100) {
                    this.percentPowerPont = 0;
                }
            }
        }
        //kết thúc pk
        PVPServcice.gI().finishPVP(this, PVP.TYPE_DIE);
        BlackBallWar.gI().dropBlackBall(this);
        if (isHoldNamecBall) {
            NamekBallWar.gI().dropBall(this);
        }
        if (isHoldNamecBallTranhDoat) {
            TranhNgocService.getInstance().dropBall(this, (byte) -1);
            TranhNgocService.getInstance().sendUpdateLift(this);
        }
    }

    public void rewardDuoiKhi(Player pl) {
        if (pl != null) {
            int x = this.location.x;
            int y = this.zone.map.yPhysicInTop(x, this.location.y - 24);
            ItemMap itemMap = new ItemMap(this.zone, 579, 1, x, y, pl.id);
            RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            if (itemMap != null) {
                Service.getInstance().dropItemMap(zone, itemMap);
            }
        }
    }

    public void reward(Player pl) {
        if (pl != null) {
            int x = this.location.x;
            int y = this.zone.map.yPhysicInTop(x, this.location.y - 24);
            ItemMap itemMap = new ItemMap(this.zone, 516, 1, x, y, pl.id);
            RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            if (itemMap != null) {
                Service.getInstance().dropItemMap(zone, itemMap);
            }
        }
    }

    //--------------------------------------------------------------------------
    public void setClanMember() {
        if (this.clanMember != null) {
            this.clanMember.powerPoint = this.nPoint.power;
            this.clanMember.head = this.getHead();
            this.clanMember.body = this.getBody();
            this.clanMember.leg = this.getLeg();
        }
    }

    public boolean isAdmin() {
        return this.session.isAdmin;
    }

    public void setJustRevivaled() {
        this.justRevived = true;
        this.lastTimeRevived = System.currentTimeMillis();
        this.immortal = true;
    }

    public void dispose() {
        if (escortedBoss != null) {
            escortedBoss.stopEscorting();
        }
        if (skillSpecial != null) {
            skillSpecial.dispose();
            skillSpecial = null;
        }
        isDisposed = true;
    }
}
