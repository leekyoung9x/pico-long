package nro.models.player;

import lombok.Getter;
import lombok.Setter;
import nro.consts.ConstPet;
import nro.consts.ConstPlayer;
import nro.models.item.CaiTrang;
import nro.models.mob.Mob;
import nro.models.skill.Skill;
import nro.server.Manager;
import nro.server.io.Message;
import nro.services.*;
import nro.utils.SkillUtil;
import nro.utils.TimeUtil;
import nro.utils.Util;

/**
 * @Build by Arriety
 */
public class Pet extends Player {

    @Getter
    @Setter
    public int lever;

    private static final short ARANGE_CAN_ATTACK = 200;
    private static final short ARANGE_ATT_SKILL1 = 50;

    private static final short[][] PET_ID = {{285, 286, 287}, {288, 289, 290}, {282, 283, 284},
            {304, 305, 303}};

    public static final byte FOLLOW = 0;
    public static final byte PROTECT = 1;
    public static final byte ATTACK = 2;
    public static final byte GOHOME = 3;
    public static final byte FUSION = 4;
    public static final byte ForeverFusion = 5;
    public static boolean ANGRY;

    public Player master;
    public byte status = 0;

    public byte typePet;

    // public boolean isMabu;
    public boolean isTransform;

    public long lastTimeDie;

    private boolean goingHome;

    private Mob mobAttack;
    private Player playerAttack;

    private static final int TIME_WAIT_AFTER_UNFUSION = 5000;
    private long lastTimeUnfusion;

    public byte getStatus() {
        return this.status;
    }

    @Override
    public int version() {
        return 214;
    }

    public Pet(Player master) {
        this.master = master;
        this.isPet = true;
    }

    public byte getAura() {
        if (this.getHead() == 508) { // sửa id
            return 18; // sửa 1 thành id aura mong muốn
        }

        if (this.typePet == ConstPet.BLACK_GOKU) {
            return 32;
        }

        return -1;
    }


    public void changeStatus(byte status) {
        if (goingHome || master.fusion.typeFusion != 0 || (this.isDie() && status == FUSION)
                || this.master.zone.map.mapId == 128) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        Service.getInstance().chatJustForMe(master, this, getTextStatus(status));
        switch (status) {
            case GOHOME:
                goHome();
                break;
            case FUSION:
                fusion(false);
                break;
            case ForeverFusion:
                foreverfusion();
                break;
        }
        this.status = status;
    }

    public void joinMapMaster() {
        if (!MapService.gI().isMapVS(master.zone.map.mapId)) {
            if (status != GOHOME && status != FUSION && !isDie()) {
                this.location.x = master.location.x + Util.nextInt(-10, 10);
                this.location.y = master.location.y;
                MapService.gI().goToMap(this, master.zone);
                this.zone.load_Me_To_Another(this);
            }
        }
    }

    public void goHome() {
        if (this.status == GOHOME) {
            return;
        }
        goingHome = true;
        new Thread(() -> {
            try {
                Pet.this.status = Pet.ATTACK;
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            // MapService.gI().goToMap(this,MapManager.gI().getListMapById(master.gender +
            // 21).get(0));
            MapService.gI().goToMap(this, MapService.gI().getMapCanJoin(this, master.gender + 21));
            this.zone.load_Me_To_Another(this);
            Pet.this.status = Pet.GOHOME;
            goingHome = false;
        }).start();
    }

    private String getTextStatus(byte status) {
        switch (status) {
            case FOLLOW:
                return "Ok con theo sư phụ";
            case PROTECT:
                return "Ok con sẽ bảo vệ sư phụ";
            case ATTACK:
                return "Ok sư phụ để con lo cho";
            case GOHOME:
                return "Ok con về, bibi sư phụ";
            default:
                return "";
        }
    }

    public void fusion3(boolean porata) {
        if (this.isDie()) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        if (Util.canDoWithTime(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION)) {
            if (porata) {
                master.fusion.typeFusion = ConstPlayer.HOP_THE_PORATA3;
            } else {
                master.fusion.lastTimeFusion = System.currentTimeMillis();
                master.fusion.typeFusion = ConstPlayer.LUONG_LONG_NHAT_THE;
                ItemTimeService.gI().sendItemTime(master, master.gender == ConstPlayer.NAMEC ? 3901 : 3790,
                        Fusion.TIME_FUSION / 1000);
            }
            this.status = FUSION;
            exitMapFusion();
            fusionEffect(master.fusion.typeFusion);
            Service.getInstance().Send_Caitrang(master);
            master.nPoint.calPoint();
            master.nPoint.setFullHpMp();
            Service.getInstance().point(master);
        } else {
            Service.getInstance().sendThongBao(this.master, "Vui lòng đợi "
                    + TimeUtil.getTimeLeft(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION / 1000) + " nữa");
        }
    }

    public void fusion4(boolean porata) {
        if (this.isDie()) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        if (Util.canDoWithTime(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION)) {
            if (porata) {
                master.fusion.typeFusion = ConstPlayer.HOP_THE_PORATA4;
            } else {
                master.fusion.lastTimeFusion = System.currentTimeMillis();
                master.fusion.typeFusion = ConstPlayer.LUONG_LONG_NHAT_THE;
                ItemTimeService.gI().sendItemTime(master, master.gender == ConstPlayer.NAMEC ? 3901 : 3790,
                        Fusion.TIME_FUSION / 1000);
            }
            this.status = FUSION;
            exitMapFusion();
            fusionEffect(master.fusion.typeFusion);
            Service.getInstance().Send_Caitrang(master);
            master.nPoint.calPoint();
            master.nPoint.setFullHpMp();
            Service.getInstance().point(master);
        } else {
            Service.getInstance().sendThongBao(this.master, "Vui lòng đợi "
                    + TimeUtil.getTimeLeft(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION / 1000) + " nữa");
        }
    }

    public void fusion2(boolean porata) {
        if (this.isDie()) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        if (Util.canDoWithTime(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION)) {
            if (porata) {
                master.fusion.typeFusion = ConstPlayer.HOP_THE_PORATA2;
            } else {
                master.fusion.lastTimeFusion = System.currentTimeMillis();
                master.fusion.typeFusion = ConstPlayer.LUONG_LONG_NHAT_THE;
                ItemTimeService.gI().sendItemTime(master, master.gender == ConstPlayer.NAMEC ? 3901 : 3790,
                        Fusion.TIME_FUSION / 1000);
            }
            this.status = FUSION;
            exitMapFusion();
            fusionEffect(master.fusion.typeFusion);
            Service.getInstance().Send_Caitrang(master);
            master.nPoint.calPoint();
            master.nPoint.setFullHpMp();
            Service.getInstance().point(master);
        } else {
            Service.getInstance().sendThongBao(this.master, "Vui lòng đợi "
                    + TimeUtil.getTimeLeft(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION / 1000) + " nữa");
        }
    }

    public void fusion(boolean porata) {
        if (this.isDie()) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        if (Util.canDoWithTime(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION)) {
            if (porata) {
                master.fusion.typeFusion = ConstPlayer.HOP_THE_PORATA;
            } else {
                master.fusion.lastTimeFusion = System.currentTimeMillis();
                master.fusion.typeFusion = ConstPlayer.LUONG_LONG_NHAT_THE;
                ItemTimeService.gI().sendItemTime(master, master.gender == ConstPlayer.NAMEC ? 3901 : 3790,
                        Fusion.TIME_FUSION / 1000);
            }
            this.status = FUSION;
            exitMapFusion();
            fusionEffect(master.fusion.typeFusion);
            Service.getInstance().Send_Caitrang(master);
            master.nPoint.calPoint();
            master.nPoint.setFullHpMp();
            Service.getInstance().point(master);
        } else {
            Service.getInstance().sendThongBao(this.master, "Vui lòng đợi "
                    + TimeUtil.getTimeLeft(lastTimeUnfusion, TIME_WAIT_AFTER_UNFUSION / 1000) + " nữa");
        }
    }

    private void foreverfusion() {
        if (master.gender != ConstPlayer.NAMEC) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        if (this.isDie()) {
            Service.getInstance().sendThongBao(master, "Không thể thực hiện");
            return;
        }
        master.fusion.typeFusion = ConstPlayer.LUONG_LONG_NHAT_THE;
        exitMapFusion();
        if (master.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            master.pet.unFusion();
        }
        MapService.gI().exitMap(master.pet);
        master.pet.dispose();
        master.pet = null;
        fusionEffect(master.fusion.typeFusion);
        Service.getInstance().Send_Caitrang(master);
        long power = this.nPoint.power;
        PlayerService.gI().sendTNSM(master, (byte) 0, +power);
        master.nPoint.tiemNangUp(power);
    }

    public void unFusion() {
        master.fusion.typeFusion = 0;
        this.status = PROTECT;
        Service.getInstance().point(master);
        joinMapMaster();
        fusionEffect(master.fusion.typeFusion);
        Service.getInstance().Send_Caitrang(master);
        Service.getInstance().point(master);
        this.lastTimeUnfusion = System.currentTimeMillis();
    }

    private void fusionEffect(int type) {
        Message msg;
        try {
            msg = new Message(125);
            msg.writer().writeByte(type);
            msg.writer().writeInt((int) master.id);
            Service.getInstance().sendMessAllPlayerInMap(master, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    private void exitMapFusion() {
        if (this.zone != null) {
            MapService.gI().exitMap(this);
        }
    }

    public long lastTimeMoveIdle;
    private int timeMoveIdle;
    public boolean idle;

    private void moveIdle() {
        if (status == GOHOME || status == FUSION) {
            return;
        }
        if (idle && Util.canDoWithTime(lastTimeMoveIdle, timeMoveIdle)) {
            int dir = this.location.x - master.location.x <= 0 ? -1 : 1;
            PlayerService.gI().playerMove(this, master.location.x
                    + Util.nextInt(dir == -1 ? 30 : -50, dir == -1 ? 50 : 30), master.location.y);
            lastTimeMoveIdle = System.currentTimeMillis();
            timeMoveIdle = Util.nextInt(5000, 8000);
        }
    }

    private byte directAtHome = -1;

    @Override
    public void update() {
        try {
            super.update();
            increasePoint(); // cộng chỉ số
            updatePower(); // check mở skill...
            if (isDie()) {
                if (System.currentTimeMillis() - lastTimeDie > 50_000) {
                    Service.getInstance().hsChar(this, nPoint.hpMax, nPoint.mpMax);
                } else {
                    return;
                }
            }
            if (justRevived && this.zone == master.zone) {
                Service.getInstance().chatJustForMe(master, this, "Sư phụ ơi, con đây nè!");
                justRevived = false;
            }
            if (this.zone == null || this.zone != master.zone) {
                joinMapMaster();
            }
            if (master.isDie() || this.isDie() || effectSkill.isHaveEffectSkill()) {
                return;
            }
            moveIdle();
            switch (status) {
                case FOLLOW:
                    // followMaster(60);
                    break;
                case PROTECT:
                    if (useSkill3() || useSkill4() || useSkill5()) {
                        break;
                    }
                    mobAttack = findMobAttack();
                    if (mobAttack != null) {
                        int disToMob = Util.getDistance(this, mobAttack);
                        if (disToMob <= ARANGE_ATT_SKILL1) {
                            // đấm
                            this.playerSkill.skillSelect = getSkill(1);
                            if (SkillService.gI().canUseSkillWithCooldown(this)) {
                                if (SkillService.gI().canUseSkillWithMana(this)) {
                                    PlayerService.gI().playerMove(this, mobAttack.location.x + Util.nextInt(-20, 20),
                                            mobAttack.location.y);
                                    SkillService.gI().useSkill(this, null, mobAttack);
                                } else {
                                    askPea();
                                }
                            }
                        } else {
                            // chưởng
                            this.playerSkill.skillSelect = getSkill(2);
                            if (this.playerSkill.skillSelect.skillId != -1) {
                                if (SkillService.gI().canUseSkillWithCooldown(this)) {
                                    if (SkillService.gI().canUseSkillWithMana(this)) {
                                        SkillService.gI().useSkill(this, null, mobAttack);
                                    } else {
                                        askPea();
                                    }
                                }
                            }
                        }

                    } else {
                        idle = true;
                    }
                    break;
                case ATTACK:
                    if (useSkill3() || useSkill4() || useSkill5()) {
                        break;
                    }
                    mobAttack = findMobAttack();
                    if (mobAttack != null) {
                        int disToMob = Util.getDistance(this, mobAttack);
                        if (disToMob <= ARANGE_ATT_SKILL1) {
                            this.playerSkill.skillSelect = getSkill(1);
                            if (SkillService.gI().canUseSkillWithCooldown(this)) {
                                if (SkillService.gI().canUseSkillWithMana(this)) {
                                    PlayerService.gI().playerMove(this, mobAttack.location.x + Util.nextInt(-20, 20),
                                            mobAttack.location.y);
                                    SkillService.gI().useSkill(this, null, mobAttack);
                                } else {
                                    askPea();
                                }
                            }
                        } else {
                            this.playerSkill.skillSelect = getSkill(2);
                            if (this.playerSkill.skillSelect.skillId != -1) {
                                if (SkillService.gI().canUseSkillWithMana(this)) {
                                    SkillService.gI().useSkill(this, null, mobAttack);
                                } else {
                                    askPea();
                                }
                            } else {
                                this.playerSkill.skillSelect = getSkill(1);
                                if (SkillService.gI().canUseSkillWithCooldown(this)) {
                                    if (SkillService.gI().canUseSkillWithMana(this)) {
                                        PlayerService.gI().playerMove(this,
                                                mobAttack.location.x + Util.nextInt(-20, 20), mobAttack.location.y);
                                        SkillService.gI().useSkill(this, null, mobAttack);
                                    } else {
                                        askPea();
                                    }
                                }
                            }
                        }

                    } else {
                        idle = true;
                    }
                    break;
                case GOHOME:
                    if (this.zone != null
                            && (this.zone.map.mapId == 21 || this.zone.map.mapId == 22 || this.zone.map.mapId == 23)) {
                        switch (this.zone.map.mapId) {
                            case 21:
                                if (directAtHome == -1) {
                                    PlayerService.gI().playerMove(this, 250, 336);
                                    directAtHome = 1;
                                } else {
                                    PlayerService.gI().playerMove(this, 200, 336);
                                    directAtHome = -1;
                                }
                                break;
                            case 22:
                                if (directAtHome == -1) {
                                    PlayerService.gI().playerMove(this, 500, 336);
                                    directAtHome = 1;
                                } else {
                                    PlayerService.gI().playerMove(this, 452, 336);
                                    directAtHome = -1;
                                }
                                break;
                            case 23:
                                if (directAtHome == -1) {
                                    PlayerService.gI().playerMove(this, 250, 336);
                                    directAtHome = 1;
                                } else {
                                    PlayerService.gI().playerMove(this, 200, 336);
                                    directAtHome = -1;
                                }
                                break;
                        }
                        Service.getInstance().chatJustForMe(master, this, "Hello sư phụ!");
                    }
                    break;
            }
            // }
        } catch (Exception e) {
        }
    }

    private long lastTimeAskPea;

    public void askPea() {
        if (this.typePet == 1 && master.charms.tdDeTuMabu > System.currentTimeMillis()) {
            InventoryService.gI().eatPea(master);
        } else if (Util.canDoWithTime(lastTimeAskPea, 10000)) {
            Service.getInstance().chatJustForMe(master, this, "Sư phụ ơi cho con đậu thần");
            lastTimeAskPea = System.currentTimeMillis();
        }
    }

    private int countTTNL;

    private boolean useSkill3() {
        try {
            playerSkill.skillSelect = getSkill(3);
            if (playerSkill.skillSelect.skillId == -1) {
                return false;
            }
            switch (this.playerSkill.skillSelect.template.id) {
                case Skill.THAI_DUONG_HA_SAN:
                    if (SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        SkillService.gI().useSkill(this, null, null);
                        Service.getInstance().chatJustForMe(master, this, "Thái dương hạ san");
                        return true;
                    }
                    return false;
                case Skill.TAI_TAO_NANG_LUONG:
                    if (this.effectSkill.isCharging && this.countTTNL < Util.nextInt(3, 5)) {
                        this.countTTNL++;
                        return true;
                    }
                    if (SkillService.gI().canUseSkillWithCooldown(this) && SkillService.gI().canUseSkillWithMana(this)
                            && (this.nPoint.getCurrPercentHP() <= 20 || this.nPoint.getCurrPercentMP() <= 20)) {
                        SkillService.gI().useSkill(this, null, null);
                        this.countTTNL = 0;
                        return true;
                    }
                    return false;
                case Skill.KAIOKEN:
                    if (SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        mobAttack = this.findMobAttack();
                        if (mobAttack == null) {
                            return false;
                        }
                        int dis = Util.getDistance(this, mobAttack);
                        if (dis > ARANGE_ATT_SKILL1) {
                            PlayerService.gI().playerMove(this, mobAttack.location.x, mobAttack.location.y);
                        } else {
                            if (SkillService.gI().canUseSkillWithCooldown(this)
                                    && SkillService.gI().canUseSkillWithMana(this)) {
                                PlayerService.gI().playerMove(this, mobAttack.location.x + Util.nextInt(-20, 20),
                                        mobAttack.location.y);
                            }
                        }
                        SkillService.gI().useSkill(this, playerAttack, mobAttack);
                        getSkill(1).lastTimeUseThisSkill = System.currentTimeMillis();
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean useSkill5() {

        try {
            playerSkill.skillSelect = getSkill(5);
            if (playerSkill.skillSelect.skillId == -1) {
                return false;
            }
            if (mobAttack == null || mobAttack.location == null) {
                return false;
            }
            int dir = mobAttack.location.x < this.location.x ? -1 : 1;
            switch (this.playerSkill.skillSelect.template.id) {
                case Skill.SUPER_KAME:
                    if (SkillService.gI().canUseSkillWithCooldown(this) && SkillService.gI().canUseSkillWithMana(this)) {
                        SpecialSkill.gI().executeSpecialSkill(this, (short) Skill.SUPER_KAME, (short) this.location.x,
                                (short) this.location.y, (byte) dir, (short) mobAttack.location.x, (short) mobAttack.location.y);
                        return true;
                    }
                    return false;
//                case Skill.SUPER_ANTOMIC:
//                    if (SkillService.gI().canUseSkillWithCooldown(this)
//                            && SkillService.gI().canUseSkillWithMana(this)) {
//                        SpecialSkill.gI().executeSpecialSkill(this, (short) Skill.SUPER_ANTOMIC, (short) this.location.x,
//                                (short) this.location.y, (byte) dir, (short) mobAttack.location.x, (short) mobAttack.location.y);
//                        return true;
//                    }
//                    return false;
                case Skill.MAFUBA:
                    // ekko nếu đang dùng skill mafuba thì không thể dùng skill laze
                    if (!Util.canDoWithTime(this.skillSpecial.lastTimeSkillSpecial, SkillSpecial.TIME_USE_MAFUBA) && this.playerSkill.skillSelect.template.id == Skill.MAKANKOSAPPO) {
                        Service.gI().sendThongBao(this, "Đang sử dụng kỹ năng Ma Phong Ba, không thể sử dụng kỹ năng khác");
                        return false;
                    }
                    if (SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        SpecialSkill.gI().executeSpecialSkill(this, (short) Skill.MAFUBA, (short) this.location.x,
                                (short) this.location.y, (byte) dir, (short) mobAttack.location.x, (short) mobAttack.location.y);
                        return true;
                    }
                    return false;
//                    Service.gI().sendThongBao(this.master, "Kỹ năng bị đang bảo trì do quá OP!");
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean useSkill4() {
        try {
            this.playerSkill.skillSelect = getSkill(4);
            if (this.playerSkill.skillSelect.skillId == -1) {
                return false;
            }
            switch (this.playerSkill.skillSelect.template.id) {
                case Skill.BIEN_KHI:
                    if (!this.effectSkill.isMonkey && SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        SkillService.gI().useSkill(this, null, null);
                        return true;
                    }
                    return false;
                case Skill.KHIEN_NANG_LUONG:
                    if (!this.effectSkill.isShielding && SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        SkillService.gI().useSkill(this, null, null);
                        return true;
                    }
                    return false;
                case Skill.DE_TRUNG:
                    if (this.mobMe == null && SkillService.gI().canUseSkillWithCooldown(this)
                            && SkillService.gI().canUseSkillWithMana(this)) {
                        SkillService.gI().useSkill(this, null, null);
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void increasePoint() {
        if (status != FUSION) {
            this.nPoint.increasePoint((byte) Util.nextInt(0, 2), (short) 10);
        }
    }

    public void followMaster() {
        if (this.isDie() || effectSkill.isHaveEffectSkill()) {
            return;
        }
        switch (this.status) {
            case ATTACK:
                if (ANGRY) {
                    followMaster(80);
                } else {
                    if ((mobAttack != null && Util.getDistance(this, master) <= 500)) {
                        break;
                    }
                }
            case FOLLOW:
            case PROTECT:
                followMaster(60);
                break;
        }
    }

    private void followMaster(int dis) {
        int mX = master.location.x;
        int mY = master.location.y;
        int disX = this.location.x - mX;
        if (Math.sqrt(Math.pow(mX - this.location.x, 2) + Math.pow(mY - this.location.y, 2)) >= dis) {
            if (disX < 0) {
                this.location.x = mX - Util.nextInt(0, dis);
            } else {
                this.location.x = mX + Util.nextInt(0, dis);
            }
            this.location.y = mY;
            PlayerService.gI().playerMove(this, this.location.x, this.location.y);
        }
    }


    // new short[]{550, 551, 552}, //outfit
    // new short[]{553, 551, 552}, //outfit
    public short getAvatar() {
        switch (this.typePet) {
            case ConstPet.MABU_HAN:
                return 427;
            case ConstPet.FIDE_NHI:
                return 1369;
            case ConstPet.MABU:
                return 297;
            case ConstPet.BILL:
                return 508;
            case ConstPet.VIDEL:
                return 810;
            case ConstPet.WHIS:
                return 1469;
            case ConstPet.ZENO:
                return 1471;
            case ConstPet.BLACK_GOKU:
                if (this.nPoint.power < 100_000_000_000L) {
                    return 1500;
                } else {
                    return 1947;
                }
            case ConstPet.MABU_NHI:
                return 1393;
            case ConstPet.BILL_CON:
                if (this.lever >= 7) {

                    return 508;
                } else {
                    return 1405;
                }
            case ConstPet.SUPER:
                if (this.nPoint.power < 10_000_000_000L) {
                    return 550;
                } else {
                    return 553;
                }
            case ConstPet.CELL:
                switch (this.master.pet.getLever()) {
                    case 1:
                        return 228;
                    case 2:
                        return 231;
                    case 3:
                        return 234;
                }
            default:
                return PET_ID[3][this.gender];
        }
    }

    @Override
    public short getHead() {
        if (effectSkill.isMonkey) {
            int index = effectSkill.levelMonkey - 1;
            if (index >= 0 && index < ConstPlayer.HEADMONKEY.length) {
                return (short) ConstPlayer.HEADMONKEY[index];
            } else {
                return PET_ID[3][this.gender];
            }
        } else if (effectSkill.isSocola || effectSkin.isSocola) {
            return 412;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 454;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 545;
        } else if (this.typePet == ConstPet.MABU && !this.isTransform) {
            return 297;
        } else if (this.typePet == ConstPet.MABU_NHI && !this.isTransform) {
            return 1393;
        } else if (this.typePet == ConstPet.BILL_CON && !this.isTransform) {
            if (this.lever >= 7) {
                return 508;
            } else {
                return 1405;
            }


        } else if (this.typePet == ConstPet.MABU_HAN && !this.isTransform) {
            return 427;
        } else if (this.typePet == ConstPet.FIDE_NHI && !this.isTransform) {
            return 1369;
        } else if (this.typePet == ConstPet.BILL && !this.isTransform) {
            return 508;
        } else if (this.typePet == ConstPet.VIDEL && !this.isTransform) {
            return 810;
        } else if (this.typePet == ConstPet.WHIS && !this.isTransform) {
            return 505;
        } else if (this.typePet == ConstPet.ZENO && !this.isTransform) {
            return 1471;
        } else if (this.typePet == ConstPet.BLACK_GOKU && !this.isTransform) {
            if (this.nPoint.power < 100_000_000_000L) {
                return 1500;
            } else {
                return 1497;
            }
        } else if (typePet == ConstPet.SUPER && !this.isTransform) {
            if (this.nPoint.power < 10_000_000_000L) {
                return 550;
            } else {
                return 553;
            }
        } else if (typePet == ConstPet.CELL && !this.isTransform) {
            switch (this.master.pet.getLever()) {
                case 1:
                    return 228;
                case 2:
                    return 231;
                case 3:
                    return 234;
            }
        } else if (inventory.itemsBody.get(5).isNotNullItem()) {
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (ct != null) {
                return (short) ((short) ct.getID()[0] != -1 ? ct.getID()[0] : inventory.itemsBody.get(5).template.part);
            }
        }

        if (this.nPoint.power < 1500000) {
            return PET_ID[this.gender][0];
        } else {
            return PET_ID[3][this.gender];
        }
    }

    @Override
    public short getBody() {
        if (effectSkill.isMonkey) {
            return 193;
        } else if (effectSkill.isSocola || effectSkin.isSocola) {
            return 413;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 455;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 548;
        } else if (this.typePet == ConstPet.MABU && !this.isTransform) {
            return 298;
        } else if (this.typePet == ConstPet.MABU_NHI && !this.isTransform) {
            return 1394;
        } else if (this.typePet == ConstPet.MABU_HAN && !this.isTransform) {
            return 428;
        } else if (this.typePet == ConstPet.FIDE_NHI && !this.isTransform) {
            return 1370;
        } else if (this.typePet == ConstPet.BILL && !this.isTransform) {
            return 509;
        } else if (this.typePet == ConstPet.BILL_CON && !this.isTransform) {
            if (this.lever >= 7) {
                return 509;
            } else {
                return 1406;
            }
        } else if (this.typePet == ConstPet.VIDEL && !this.isTransform) {
            return 811;
        } else if (typePet == ConstPet.SUPER && !this.isTransform) {
            if (this.nPoint.power < 10_000_000_000L) {
                return 551;
            } else {
                return 551;
            }
        } else if (this.typePet == ConstPet.WHIS && !this.isTransform) {
            return 506;
        } else if (this.typePet == ConstPet.ZENO && !this.isTransform) {
            return 1472;
        } else if (this.typePet == ConstPet.BLACK_GOKU && !this.isTransform) {
            return 1501;
        } else if (typePet == ConstPet.CELL && !this.isTransform) {
            switch (this.master.pet.getLever()) {
                case 1:
                    return 229;
                case 2:
                    return 232;
                case 3:
                    return 235;
            }
        } else if (inventory.itemsBody.get(5).isNotNullItem()) {
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (ct != null && ct.getID()[1] != -1) {
                return (short) ct.getID()[1];
            }
        }
        if (inventory.itemsBody.get(0).isNotNullItem()) {
            return inventory.itemsBody.get(0).template.part;
        }
        if (this.nPoint.power < 1500000) {
            return PET_ID[this.gender][1];
        } else {
            return (short) (gender == ConstPlayer.NAMEC ? 59 : 57);
        }
    }

    @Override
    public short getLeg() {
        if (effectSkill.isMonkey) {
            return 194;
        } else if (effectSkill.isSocola || effectSkin.isSocola) {
            return 414;
        } else if (this.typePet == ConstPet.MABU_NHI && !this.isTransform) {
            return 1395;
        } else if (effectSkin != null && effectSkin.isHoaDa) {
            return 456;
        } else if (effectSkin != null && effectSkin.isHoaXuong) {
            return 548;
        } else if (this.typePet == ConstPet.MABU && !this.isTransform) {
            return 299;
        } else if (this.typePet == ConstPet.MABU_HAN && !this.isTransform) {
            return 429;
        } else if (this.typePet == ConstPet.FIDE_NHI && !this.isTransform) {
            return 1371;
        } else if (this.typePet == ConstPet.BILL && !this.isTransform) {
            return 510;
        } else if (this.typePet == ConstPet.BILL_CON && !this.isTransform) {
            if (this.lever >= 7) {
                return 510;
            } else {
                return 1407;
            }

        } else if (this.typePet == ConstPet.VIDEL && !this.isTransform) {
            return 812;
        } else if (typePet == ConstPet.SUPER && !this.isTransform) {
            if (this.nPoint.power < 10_000_000_000L) {
                return 552;
            } else {
                return 552;
            }
        } else if (this.typePet == ConstPet.WHIS && !this.isTransform) {
            return 507;
        } else if (this.typePet == ConstPet.ZENO && !this.isTransform) {
            return 1473;
        } else if (this.typePet == ConstPet.BLACK_GOKU && !this.isTransform) {
            return 1502;
        } else if (typePet == ConstPet.CELL && !this.isTransform) {
            switch (this.master.pet.getLever()) {
                case 1:
                    return 230;
                case 2:
                    return 233;
                case 3:
                    return 236;
            }
        } else if (inventory.itemsBody.get(5).isNotNullItem()) {
            CaiTrang ct = Manager.getCaiTrangByItemId(inventory.itemsBody.get(5).template.id);
            if (ct != null && ct.getID()[2] != -1) {
                return (short) ct.getID()[2];
            }
        }
        if (inventory.itemsBody.get(1).isNotNullItem()) {
            return inventory.itemsBody.get(1).template.part;
        }

        if (this.nPoint.power < 1500000) {
            return PET_ID[this.gender][2];
        } else {
            return (short) (gender == ConstPlayer.NAMEC ? 60 : 58);
        }
    }

    private Mob findMobAttack() {
        int dis = ARANGE_CAN_ATTACK;
        Mob mobAtt = null;
        for (Mob mob : zone.mobs) {
            if (mob.isDie()) {
                continue;
            }
            int d = Util.getDistance(this, mob);
            if (d <= dis) {
                dis = d;
                mobAtt = mob;
            }
        }
        return mobAtt;
    }

    private void updatePower() {
        if (this.playerSkill != null) {
            switch (this.playerSkill.getSizeSkill()) {
                case 1:
                    if (this.nPoint.power >= 150000000) {
                        openSkill2();
                    }
                    break;
                case 2:
                    if (this.nPoint.power >= 1500000000) {
                        openSkill3();
                    }
                    break;
                case 3:
                    if (this.nPoint.power >= 30_000_000_000L) {
                        openSkill4();
                    }
                    break;
                case 4:
                    if (this.nPoint.power >= 100_000_000_000L) {
                        openSkill5();
                        this.nPoint.calPoint();
                    }

                    break;
            }
        }
    }

    public void openSkill5() {
        Skill skill = null;
        int tiLeBienKhi = 50;
        int tiLeDeTrung = 25;
        int tiLeKNL = 25;

        int rd = Util.nextInt(1, 100);
        if (rd <= tiLeBienKhi) {
            skill = SkillUtil.createSkill(Skill.SUPER_KAME, 1);
        } else if (rd <= tiLeBienKhi + tiLeDeTrung) {
            skill = SkillUtil.createSkill(Skill.SUPER_ANTOMIC, 1);
        } else if (rd <= tiLeBienKhi + tiLeDeTrung + tiLeKNL) {
            skill = SkillUtil.createSkill(Skill.MAFUBA, 1);
        }
        this.playerSkill.skills.set(4, skill);
    }

    public void randomSkill5() {
        Skill skill = null;
        if (Util.isTrue(50, 100)) {
            skill = SkillUtil.createSkill(Skill.HUYT_SAO, 1);
        } else {
            skill = SkillUtil.createSkill(Skill.DICH_CHUYEN_TUC_THOI, 1);
        }
        this.playerSkill.skills.set(4, skill);
    }

    public void openSkillLienhoan() {
        Skill skill = null;
        skill = SkillUtil.createSkill(Skill.LIEN_HOAN, 1);
        // skill.coolDown = 1000;
        this.playerSkill.skills.set(0, skill);
    }

    public void openSkill2Kame() {
        Skill skill = null;
        skill = SkillUtil.createSkill(Skill.KAMEJOKO, 1);
        skill.coolDown = 1000;
        this.playerSkill.skills.set(1, skill);

    }

    public void openSkill2() {
        Skill skill = null;
        int tiLeKame = 40;
        int tiLeMasenko = 20;
        int tiLeAntomic = 40;

        int rd = Util.nextInt(1, 100);
        if (rd <= tiLeKame) {
            skill = SkillUtil.createSkill(Skill.KAMEJOKO, 1);
        } else if (rd <= tiLeKame + tiLeMasenko) {
            skill = SkillUtil.createSkill(Skill.MASENKO, 1);
        } else if (rd <= tiLeKame + tiLeMasenko + tiLeAntomic) {
            skill = SkillUtil.createSkill(Skill.ANTOMIC, 1);
        }
        skill.coolDown = 1000;
        this.playerSkill.skills.set(1, skill);
    }

    public void openSkill4Khi() {
        Skill skill = null;
        skill = SkillUtil.createSkill(Skill.BIEN_KHI, 1);
        this.playerSkill.skills.set(3, skill);
    }

    public void openSkill3() {
        Skill skill = null;
        int tiLeTDHS = 30;
        int tiLeTTNL = 40;
        int tiLeKOK = 30;

        int rd = Util.nextInt(1, 100);
        if (rd <= tiLeTDHS) {
            skill = SkillUtil.createSkill(Skill.THAI_DUONG_HA_SAN, 1);
        } else if (rd <= tiLeTDHS + tiLeTTNL) {
            skill = SkillUtil.createSkill(Skill.TAI_TAO_NANG_LUONG, 1);
        } else if (rd <= tiLeTDHS + tiLeTTNL + tiLeKOK) {
            skill = SkillUtil.createSkill(Skill.KAIOKEN, 1);
        }
        this.playerSkill.skills.set(2, skill);
    }

    public void openSkill4() {
        Skill skill = null;
        int tiLeBienKhi = 10;
        int tiLeDeTrung = 70;
        int tiLeKNL = 20;

        int rd = Util.nextInt(1, 100);
        if (rd <= tiLeBienKhi) {
            skill = SkillUtil.createSkill(Skill.BIEN_KHI, 1);
        } else if (rd <= tiLeBienKhi + tiLeDeTrung) {
            skill = SkillUtil.createSkill(Skill.DE_TRUNG, 1);
        } else if (rd <= tiLeBienKhi + tiLeDeTrung + tiLeKNL) {
            skill = SkillUtil.createSkill(Skill.KHIEN_NANG_LUONG, 1);
        }
        this.playerSkill.skills.set(3, skill);
    }

    private Skill getSkill(int indexSkill) {
        return this.playerSkill.skills.get(indexSkill - 1);
    }

    public void transform() {
        switch (this.typePet) {
            case ConstPet.MABU:
                this.isTransform = !this.isTransform;
                Service.getInstance().Send_Caitrang(this);
                Service.getInstance().chat(this, "Bư bư bư....");
                break;
            case ConstPet.BILL:
            case ConstPet.MABU_HAN:
            case ConstPet.FIDE_NHI:
            case ConstPet.VIDEL:
            case ConstPet.SUPER:
            case ConstPet.WHIS:
            case ConstPet.ZENO:
            case ConstPet.BLACK_GOKU:
                this.isTransform = !this.isTransform;
                Service.getInstance().Send_Caitrang(this);
                Service.getInstance().chat(this, "Bruuu bruuu bruuuuuuuu....");
                break;
            default:
                break;
        }
    }

    public void angry(Player plAtt) {
        ANGRY = true;
        if (plAtt != null) {
            this.playerAttack = plAtt;
            Service.getInstance().chatJustForMe(master, this, "Mi làm ta nổi giận rồi " + playerAttack.name
                    .replace("$", ""));
        }
    }
}
