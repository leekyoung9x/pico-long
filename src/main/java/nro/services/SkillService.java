package nro.services;

import nro.consts.ConstAchive;
import nro.consts.ConstOption;
import nro.consts.ConstPlayer;
import nro.models.intrinsic.Intrinsic;
import nro.models.item.ItemOption;
import nro.models.mob.Mob;
import nro.models.mob.MobMe;
import nro.models.player.Pet;
import nro.models.player.Player;
import nro.models.pvp.PVP;
import nro.models.skill.Skill;
import nro.server.io.Message;
import nro.services.func.PVPServcice;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.item.Item;

/**
 * @Build By Arriety
 */
public class SkillService {

    private static SkillService i;

    private SkillService() {

    }

    public static SkillService gI() {
        if (i == null) {
            i = new SkillService();
        }
        return i;
    }

    public boolean useSkill(Player player, Player plTarget, Mob mobTarget) {
        try {
            if (player.playerSkill.skillSelect == null && player.playerSkill == null && player.playerSkill.skillSelect.template == null) {
                return false;
            }
            if (player.playerSkill.skillSelect.template.type == 2 && canUseSkillWithMana(player) && canUseSkillWithCooldown(player)) {
                useSkillBuffToPlayer(player, plTarget);
                return true;
            }
            if ((player.effectSkill.isHaveEffectSkill()
                    && (player.playerSkill.skillSelect.template.id != Skill.TU_SAT
                    && player.playerSkill.skillSelect.template.id != Skill.QUA_CAU_KENH_KHI
                    && player.playerSkill.skillSelect.template.id != Skill.MAKANKOSAPPO))
                    || (plTarget != null && !canAttackPlayer(player, plTarget))
                    || (mobTarget != null && mobTarget.isDie())
                    || !canUseSkillWithMana(player) || !canUseSkillWithCooldown(player)) {
                return false;
            }
            // ekko nếu đang dùng skill mafuba thì không thể dùng skill laze
            if (!Util.canDoWithTime(player.skillSpecial.lastTimeSkillSpecial, SkillSpecial.TIME_USE_MAFUBA) && player.playerSkill.skillSelect.template.id == Skill.MAKANKOSAPPO) {
                Service.gI().sendThongBao(player, "Đang sử dụng kỹ năng Ma Phong Ba, không thể sử dụng kỹ năng khác");
                return false;
            }
            if (player.effectSkill.useTroi) {
                EffectSkillService.gI().removeUseTroi(player);
            }
            if (player.effectSkill.isCharging) {
                EffectSkillService.gI().stopCharge(player);
            }
            if (player.isPet) {
            }
            switch (player.playerSkill.skillSelect.template.type) {
                case 1:
                    useSkillAttack(player, plTarget, mobTarget);
                    break;
                case 3:
                    useSkillAlone(player);
                    break;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void useSkillAttack(Player player, Player plTarget, Mob mobTarget) {
        if (!player.isBoss) {
            if (player.isPet) {
                if (player.nPoint.stamina > 0) {
                    player.nPoint.numAttack++;
                    boolean haveCharmPet = ((Pet) player).master.charms.tdDeTu > System.currentTimeMillis();
                    if (haveCharmPet ? player.nPoint.numAttack >= 5 : player.nPoint.numAttack >= 2) {
                        player.nPoint.numAttack = 0;
                        player.nPoint.stamina--;
                    }
                } else {
                    ((Pet) player).askPea();
                    return;
                }
            } else {
                // TODO: Up quái không tốn thể lực comment
//                if (player.nPoint.stamina > 0) {
//                    if (player.charms.tdDeoDai < System.currentTimeMillis()) {
//                        player.nPoint.numAttack++;
//                        if (player.nPoint.numAttack == 5) {
//                            player.nPoint.numAttack = 0;
//                            player.nPoint.stamina--;
//                            PlayerService.gI().sendCurrentStamina(player);
//                        }
//                    }
//                } else {
//                    Service.getInstance().sendThongBao(player, "Thể lực đã cạn kiệt, hãy nghỉ ngơi để lấy lại sức");
//                    return;
//                }
            }
        }
        List<Mob> mobs;
        boolean miss = false;
        switch (player.playerSkill.skillSelect.template.id) {
            case Skill.KAIOKEN:
//                int hpUse = player.nPoint.hpMax / 100 * 10;
//                if (player.nPoint.hp <= hpUse) {
//                    break;
//                } else {
//                    player.nPoint.setHp(player.nPoint.mp - hpUse);
//                    PlayerService.gI().sendInfoHpMpMoney(player);
//                    Service.getInstance().Send_Info_NV(player);
//                }
            case Skill.DRAGON:
            case Skill.DEMON:
            case Skill.GALICK:
            case Skill.LIEN_HOAN:
                if (plTarget != null && Util.getDistance(player, plTarget) > Skill.RANGE_ATTACK_CHIEU_DAM) {
                    miss = true;
                }
                if (mobTarget != null && Util.getDistance(player, mobTarget) > Skill.RANGE_ATTACK_CHIEU_DAM) {
                    miss = true;
                }
            case Skill.KAMEJOKO:
            case Skill.MASENKO:
            case Skill.ANTOMIC:
                if (plTarget != null) {
                    playerAttackPlayer(player, plTarget, miss);
                }
                if (mobTarget != null) {
                    playerAttackMob(player, mobTarget, miss, false);
                }
                if (player.mobMe != null) {
                    player.mobMe.attack(plTarget, mobTarget);
                }
                if (player.id >= 0 && !player.playerTask.achivements.isEmpty()) {
                    if (player.playerTask.achivements.size() > ConstAchive.NOI_CONG_CAO_CUONG) {
                        player.playerTask.achivements.get(ConstAchive.NOI_CONG_CAO_CUONG).count++;
                    }
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            //******************************************************************
            case Skill.QUA_CAU_KENH_KHI:
                if (!player.playerSkill.prepareQCKK) {
                    //bắt đầu tụ quả cầu
                    player.playerSkill.prepareQCKK = !player.playerSkill.prepareQCKK;
                    player.playerSkill.lastTimeUseQCKK = System.currentTimeMillis();
                    sendPlayerPrepareSkill(player, 4000);
                } else {
                    //ném cầu
                    player.playerSkill.prepareQCKK = !player.playerSkill.prepareQCKK;
                    mobs = new ArrayList<>();
                    if (plTarget != null) {
                        playerAttackPlayer(player, plTarget, false);
                        for (Mob mob : player.zone.mobs) {
                            if (!mob.isDie()
                                    && Util.getDistance(plTarget, mob) <= SkillUtil.getRangeQCKK(player.playerSkill.skillSelect.point)) {
                                mobs.add(mob);
                            }
                        }
                    }
                    if (player.isBoss && player.id == BossFactory.BU_HAN) {
                        for (Player pl : player.zone.getHumanoids()) {
                            if (!pl.isDie()
                                    && Util.getDistance(player, pl) <= SkillUtil.getRangeQCKK(player.playerSkill.skillSelect.point)) {
                                playerAttackPlayer(player, pl, false);
                            }
                        }
                    }
                    if (mobTarget != null) {
                        playerAttackMob(player, mobTarget, false, true);
                        for (Mob mob : player.zone.mobs) {
                            if (!mob.equals(mobTarget) && !mob.isDie()
                                    && Util.getDistance(mob, mobTarget) <= SkillUtil.getRangeQCKK(player.playerSkill.skillSelect.point)) {
                                mobs.add(mob);
                            }
                        }
                    }
                    if (player.isBoss && player.id == BossFactory.BU_HAN) {
                        PlayerService.gI().changeAndSendTypePK(player, ConstPlayer.NON_PK);
                        ((Boss) player).changeStatus((byte) 6);
                    }
                    PlayerService.gI().sendInfoHpMpMoney(player);
                    affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                }
                break;
            case Skill.MAKANKOSAPPO:
                if (!player.playerSkill.prepareLaze) {
                    //bắt đầu nạp laze
                    player.playerSkill.prepareLaze = !player.playerSkill.prepareLaze;
                    sendPlayerPrepareSkill(player, 3000);
                } else {
                    //bắn laze
                    player.playerSkill.prepareLaze = !player.playerSkill.prepareLaze;
                    if (plTarget != null) {
                        playerAttackPlayer(player, plTarget, false);
                    }
                    if (mobTarget != null) {
                        playerAttackMob(player, mobTarget, false, true);
//                        mobTarget.attackMob(player, false, true);
                    }
                    affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                }
                PlayerService.gI().sendInfoHpMpMoney(player);
                break;
            case Skill.SOCOLA:
                EffectSkillService.gI().sendEffectUseSkill(player, Skill.SOCOLA);
                int timeSocola = SkillUtil.getTimeSocola();
                if (plTarget != null) {
                    EffectSkillService.gI().setSocola(plTarget, System.currentTimeMillis(), timeSocola);
                    Service.getInstance().Send_Caitrang(plTarget);
                    ItemTimeService.gI().sendItemTime(plTarget, 3780, timeSocola / 1000);
                }
                if (mobTarget != null) {
                    EffectSkillService.gI().sendMobToSocola(player, mobTarget, timeSocola);
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.DICH_CHUYEN_TUC_THOI:
                int timeChoangDCTT = SkillUtil.getTimeDCTT(player.playerSkill.skillSelect.point);
                if (plTarget != null) {
                    Service.getInstance().setPos(player, plTarget.location.x, plTarget.location.y);
                    playerAttackPlayer(player, plTarget, miss);
                    EffectSkillService.gI().setBlindDCTT(plTarget, System.currentTimeMillis(), timeChoangDCTT);
                    EffectSkillService.gI().sendEffectPlayer(player, plTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.BLIND_EFFECT);
                    PlayerService.gI().sendInfoHpMpMoney(plTarget);
                    ItemTimeService.gI().sendItemTime(plTarget, 3779, timeChoangDCTT / 1000);
                }
                if (mobTarget != null) {
                    Service.getInstance().setPos(player, mobTarget.location.x, mobTarget.location.y);
//                    mobTarget.attackMob(player, false, false);
                    playerAttackMob(player, mobTarget, false, false);
                    mobTarget.effectSkill.setStartBlindDCTT(System.currentTimeMillis(), timeChoangDCTT);
                    EffectSkillService.gI().sendEffectMob(player, mobTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.BLIND_EFFECT);
                }
                player.nPoint.isCrit100 = true;
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.THOI_MIEN:
                EffectSkillService.gI().sendEffectUseSkill(player, Skill.THOI_MIEN);
                int timeSleep = SkillUtil.getTimeThoiMien(player.playerSkill.skillSelect.point);
                if (plTarget != null) {
                    EffectSkillService.gI().setThoiMien(plTarget, System.currentTimeMillis(), timeSleep);
                    EffectSkillService.gI().sendEffectPlayer(player, plTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.SLEEP_EFFECT);
                    ItemTimeService.gI().sendItemTime(plTarget, 3782, timeSleep / 1000);
                }
                if (mobTarget != null) {
                    mobTarget.effectSkill.setThoiMien(System.currentTimeMillis(), timeSleep);
                    EffectSkillService.gI().sendEffectMob(player, mobTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.SLEEP_EFFECT);
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.TROI:
                EffectSkillService.gI().sendEffectUseSkill(player, Skill.TROI);
                int timeHold = SkillUtil.getTimeTroi(player.playerSkill.skillSelect.point);
                EffectSkillService.gI().setUseTroi(player, System.currentTimeMillis(), timeHold);
                if (plTarget != null && (!plTarget.playerSkill.prepareQCKK && !plTarget.playerSkill.prepareLaze && !plTarget.playerSkill.prepareTuSat)) {
                    player.effectSkill.plAnTroi = plTarget;
                    EffectSkillService.gI().sendEffectPlayer(player, plTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.HOLD_EFFECT);
                    EffectSkillService.gI().setAnTroi(plTarget, player, System.currentTimeMillis(), timeHold);
                }
                if (mobTarget != null) {
                    player.effectSkill.mobAnTroi = mobTarget;
                    EffectSkillService.gI().sendEffectMob(player, mobTarget, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.HOLD_EFFECT);
                    mobTarget.effectSkill.setTroi(System.currentTimeMillis(), timeHold);
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
        }
        if (!player.isBoss) {
            player.effectSkin.lastTimeAttack = System.currentTimeMillis();
        }
    }

    private void useSkillAlone(Player player) {
        List<Mob> mobs;
        List<Player> players;
        switch (player.playerSkill.skillSelect.template.id) {
            case Skill.THAI_DUONG_HA_SAN:
                int timeStun = SkillUtil.getTimeStun(player.playerSkill.skillSelect.point);
//                if (player.setClothes.thienXinHang2 == 5) {
//                    timeStun *= 2;
//                }
                if (player.setClothes.thienXinHang1 == 5) {
                    timeStun += ((long) timeStun * 50 / 100);
                }
                mobs = new ArrayList<>();
                players = new ArrayList<>();
                if (player == null || player.zone == null) {
                    break;
                }
                if (!player.zone.map.isMapOffline) {
                    ReentrantLock lock = new ReentrantLock();
                    lock.lock();
                    try {
                        List<Player> playersMap = player.zone.getHumanoids();
                        for (Player pl : playersMap) {
                            if (pl != null && !player.equals(pl)) {
                                if (!pl.nPoint.khangTDHS) {
                                    int distance = Util.getDistance(player, pl);
                                    int rangeStun = SkillUtil.getRangeStun(player.playerSkill.skillSelect.point);
                                    if (distance <= rangeStun && canAttackPlayer(player, pl)) {
                                        if (player.isPet && ((Pet) player).master.equals(pl)) {
                                            continue;
                                        }
                                        EffectSkillService.gI().startStun(pl, System.currentTimeMillis(), timeStun);
                                        if (pl.typePk != ConstPlayer.NON_PK) {
                                            players.add(pl);
                                        }
                                    }
                                } else {
                                    Service.getInstance().chat(pl, "Lew Lew Lew đòi choáng");
                                }
                            }
                        }
                    } catch (Exception e) {
                        lock.unlock();
                    }
                }
                for (Mob mob : player.zone.mobs) {
                    if (Util.getDistance(player, mob) <= SkillUtil.getRangeStun(player.playerSkill.skillSelect.point)) {
                        mob.effectSkill.startStun(System.currentTimeMillis(), timeStun);
                        mobs.add(mob);
                    }
                }
                EffectSkillService.gI().sendEffectBlindThaiDuongHaSan(player, players, mobs, timeStun);
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;

            case Skill.DE_TRUNG:
                EffectSkillService.gI().sendEffectUseSkill(player, Skill.DE_TRUNG);
                if (player.mobMe != null) {
                    player.mobMe.mobMeDie();
                }
                player.mobMe = new MobMe(player);
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.BIEN_KHI:
                EffectSkillService.gI().sendEffectMonkey(player);
                EffectSkillService.gI().setIsMonkey(player);
                EffectSkillService.gI().sendEffectMonkey(player);

                Service.getInstance().sendSpeedPlayer(player, 0);
                Service.getInstance().Send_Caitrang(player);
                Service.getInstance().sendSpeedPlayer(player, -1);
                if (!player.isPet) {
                    PlayerService.gI().sendInfoHpMp(player);
                }
                Service.getInstance().point(player);
                Service.getInstance().Send_Info_NV(player);
                Service.getInstance().sendInfoPlayerEatPea(player);
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.KHIEN_NANG_LUONG:
//            if (!player.isPet && (player.zone.map.mapId >= 85 && player.zone.map.mapId <= 91)) {
//    Service.getInstance().sendThongBao(player, "Không thể sử dụng khiên tại đây");
//    return;
//}
                EffectSkillService.gI().setStartShield(player);
                EffectSkillService.gI().sendEffectPlayer(player, player, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.SHIELD_EFFECT);
                ItemTimeService.gI().sendItemTime(player, 3784, player.effectSkill.timeShield / 1000);
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.HUYT_SAO:
                int tileHP = SkillUtil.getPercentHPHuytSao(player.playerSkill.skillSelect.point);
                if (player.zone != null) {
                    if (!player.zone.map.isMapOffline) {
                        List<Player> playersMap = player.zone.getHumanoids();
                        for (int i = 0; i < playersMap.size(); i++) {
                            Player pl = playersMap.get(i);
//                        for (Player pl : playersMap) {
                            if (pl != null && pl.effectSkill != null && pl.effectSkill.useTroi) {
                                continue;
                            }
                            if (pl != null && pl.effectSkill != null && pl.effectSkill.useTroi) {
                                EffectSkillService.gI().removeUseTroi(pl);
                            }
                            if (pl != null && pl.effectSkill != null) {
                                if (!pl.isBoss && pl.gender != ConstPlayer.NAMEC && player.cFlag == pl.cFlag) {
                                    EffectSkillService.gI().setStartHuytSao(pl, tileHP);
                                    EffectSkillService.gI().sendEffectPlayer(pl, pl, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.HUYT_SAO_EFFECT);
                                    pl.nPoint.calPoint();
                                    pl.nPoint.setHp(pl.nPoint.hp + (int) ((long) pl.nPoint.hp * tileHP / 100));
                                    Service.getInstance().point(pl);
                                    Service.getInstance().Send_Info_NV(pl);
                                    ItemTimeService.gI().sendItemTime(pl, 3781, 30);
                                    PlayerService.gI().sendInfoHpMp(pl);
                                }
                            }
                        }
                    } else {
                        EffectSkillService.gI().setStartHuytSao(player, tileHP);
                        EffectSkillService.gI().sendEffectPlayer(player, player, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.HUYT_SAO_EFFECT);
                        player.nPoint.calPoint();
                        player.nPoint.setHp(player.nPoint.hp + (int) ((long) player.nPoint.hp * tileHP / 100));
                        Service.getInstance().point(player);
                        Service.getInstance().Send_Info_NV(player);
                        ItemTimeService.gI().sendItemTime(player, 3781, 30);
                        PlayerService.gI().sendInfoHpMp(player);
                    }
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;

            case Skill.TAI_TAO_NANG_LUONG:
                EffectSkillService.gI().startCharge(player);
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
            case Skill.TU_SAT:
                if (!player.playerSkill.prepareTuSat) {
                    player.playerSkill.prepareTuSat = !player.playerSkill.prepareTuSat;
                    player.playerSkill.lastTimePrepareTuSat = System.currentTimeMillis();
                    sendPlayerPrepareBom(player, 2000);
                } else {
                    if (!player.isBoss && !Util.canDoWithTime(player.playerSkill.lastTimePrepareTuSat, 1500)) {
                        player.playerSkill.skillSelect.lastTimeUseThisSkill = System.currentTimeMillis();
                        player.playerSkill.prepareTuSat = false;
                        return;
                    }
                    player.playerSkill.prepareTuSat = !player.playerSkill.prepareTuSat;
                    int rangeBom = SkillUtil.getRangeBom(player.playerSkill.skillSelect.point);
                    Intrinsic intrinsic = player.playerIntrinsic.intrinsic;
                    int percentPlus = intrinsic.id == 27 ? intrinsic.param1 : 0;

                    percentPlus += Util.GetOptionValueItemBody(player, ConstOption.PHAN_TRAM_SAT_THUONG_NO);

                    long dame = player.nPoint.hpMax;
                    dame += dame * (percentPlus / 100);
                    for (Mob mob : player.zone.mobs) {
                        if (Util.getDistance(player, mob) <= rangeBom) {
                            mob.injured(player, dame, true);
                        }
                    }
                    List<Player> playersMap = null;
                    if (player.zone != null && player.isBoss) {
                        playersMap = player.zone.getNotBosses();
                    } else if (player != null && player.zone != null) {
                        playersMap = player.zone.getHumanoids();
                    }
                    if (player.zone != null && player.zone.map != null && !MapService.gI().isMapOffline(player.zone.map.mapId)) {
                        List<Player> playersToInjure = new ArrayList<>();
                        for (Player pl : playersMap) {
                            if (pl != null && !player.equals(pl) && canAttackPlayer(player, pl)) {
                                playersToInjure.add(pl);
                            }
                        }
                        for (Player pl : playersToInjure) {
                            pl.injured(player, pl.isBoss ? (dame / 100) * 60 : dame, false, false);
                            PlayerService.gI().sendInfoHpMpMoney(pl);
                            Service.getInstance().Send_Info_NV(pl);
                        }
                    }
                    if (player != null && player.playerSkill != null && player.playerSkill.skillSelect.template != null) {
                        affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                    }
                    player.injured(null, 2_100_000_000, true, false);
                    if (player != null && player.effectSkill != null) {
                        if (player.effectSkill.tiLeHPHuytSao != 0) {
                            player.effectSkill.tiLeHPHuytSao = 0;
                            EffectSkillService.gI().removeHuytSao(player);
                        }
                    }
                }
                break;
        }
        if (player.playerTask.achivements.size() > 0) {
            player.playerTask.achivements.get(ConstAchive.KY_NANG_THANH_THAO).count++;
        }
    }

    private void useSkillBuffToPlayer(Player player, Player plTarget) {
        switch (player.playerSkill.skillSelect.template.id) {
            case Skill.TRI_THUONG:
                List<Player> players = new ArrayList();
                int percentTriThuong = SkillUtil.getPercentTriThuong(player.playerSkill.skillSelect.point);
                if (canHsPlayer(player, plTarget)) {
                    players.add(plTarget);
                    List<Player> playersMap = player.zone.getNotBosses();
                    for (int i = playersMap.size() - 1; i >= 0; i--) {
                        Player pl = playersMap.get(i);
                        if (!pl.equals(plTarget)) {
                            if (canHsPlayer(player, plTarget) && Util.getDistance(player, pl) <= 300) {
                                players.add(pl);
                            }
                        }
                    }
                    playerAttackPlayer(player, plTarget, false);
                    for (Player pl : players) {
                        boolean isDie = pl.isDie();
                        long hpHoi = pl.nPoint.hpMax * percentTriThuong / 100;
                        long mpHoi = pl.nPoint.mpMax * percentTriThuong / 100;
                        pl.nPoint.addHp(hpHoi);
                        pl.nPoint.addMp(mpHoi);
                        if (isDie) {
                            Service.getInstance().hsChar(pl, hpHoi, mpHoi);
                            PlayerService.gI().sendInfoHpMp(pl);
                        } else {
                            Service.getInstance().Send_Info_NV(pl);
                            PlayerService.gI().sendInfoHpMp(pl);
                        }
                    }
                    long hpHoiMe = player.nPoint.hp * percentTriThuong / 100;
                    player.nPoint.addHp(hpHoiMe);
                    PlayerService.gI().sendInfoHp(player);
                }
                affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                break;
        }
    }

    private void phanSatThuong(Player plAtt, Player plTarget, long dame) {
        if (plAtt.id == 202) {
            return;
        }
        int percentPST = plTarget.nPoint.tlPST;
        if (percentPST != 0) {
            long damePST = dame * percentPST / 100;
            Message msg;
            try {
                msg = new Message(56);
                msg.writer().writeInt((int) plAtt.id);
                if (damePST >= plAtt.nPoint.hp) {
                    damePST = plAtt.nPoint.hp - 1;
                }
                damePST = plAtt.injured(null, damePST, true, false);
                msg.writer().writeLong(plAtt.nPoint.hp);
                msg.writer().writeLong(damePST);
                msg.writer().writeBoolean(false);
                msg.writer().writeByte(36);
                Service.getInstance().sendMessAllPlayerInMap(plAtt, msg);
                msg.cleanup();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void hoaXuongPlayer(Player plAtt, Player plTarget) {
        int timeHoi = 60000;
        if (plAtt != null && plAtt.effectSkin != null && Util.canDoWithTime(plAtt.effectSkin.lastTimeUseHoaXuong, timeHoi)) {
            // kiểm tra người chơi tấn công có đang mặc cải trang ngộ không không
            if (plAtt != null && plAtt.inventory != null && plAtt.inventory.itemsBody != null && (long) plAtt.inventory.itemsBody.size() >= 5 && plAtt.inventory.itemsBody.get(5) != null && plAtt.inventory.itemsBody.get(5).template != null) {
                if (plTarget != null && plTarget.inventory != null && plTarget.inventory.itemsBody != null && (long) plTarget.inventory.itemsBody.size() >= 5 && plTarget.inventory.itemsBody.get(5) != null && plTarget.inventory.itemsBody.get(5).template != null) {
                    boolean isHoaXuong = false;
                    int levelHoa = 0;
                    boolean isKhangXuong = false;
                    int levelKhang = 0;
                    boolean isResult = false;
                    Item ctAtt = plAtt.inventory.itemsBody.get(5);
                    Item ctTg = plTarget.inventory.itemsBody.get(5);
                    for (ItemOption io : ctAtt.itemOptions) {
                        if (io.optionTemplate.id == 174) {
                            isHoaXuong = true;
                        } else if (io.optionTemplate.id == 72) {
                            levelHoa = io.param;
                        }
                    }
                    // người tấn công mặc cải trang có option hóa xương
                    if (isHoaXuong) {
                        for (ItemOption io : ctTg.itemOptions) {
                            if (io.optionTemplate.id == 168) {
                                isKhangXuong = true;
                            } else if (io.optionTemplate.id == 72) {
                                levelKhang = io.param;
                            }
                        }
                        // người bị tấn công mặc cải trang kháng xương thì so sánh level của cải trang hóa xương
                        if (isKhangXuong) {
                            // level hóa lớn hơn level kháng thì hóa xương người chơi
                            if (levelHoa > levelKhang) {
                                isResult = true;
                            }
                        } else {
                            isResult = true;
                        }
                        if (isResult && this.canAttackPlayer(plAtt, plTarget)) {
                            {
                                plAtt.effectSkin.lastTimeUseHoaXuong = System.currentTimeMillis();
                                Service.getInstance().chat(plTarget, "Phọt.....");
                                Service.getInstance().chat(plTarget, "hự.....");
                                EffSkinService.gI().setHoaXuong(plTarget, System.currentTimeMillis(), 10000);
                                //EffSkinService.gI().sendEffectPlayer(plAtt, plTarget, EffSkinService.TURN_ON_EFFECT, EffSkinService.STONE_EFFECT);
                                Service.getInstance().Send_Caitrang(plTarget);
                                ItemTimeService.gI().sendItemTime(plTarget, 5101, 10000 / 1000);
                                ItemTimeService.gI().sendItemTime(plAtt, 4468, 60000 / 1000);
                            }
                        }
                    }
                }
            }
        }
    }

    //    private void hutHPMP(Player player, int dame, boolean attackMob) {
//        if (player != null && player.nPoint != null) {
//            int tiLeHutHp = player.nPoint.getTileHutHp(attackMob);
//            int tiLeHutMp = player.nPoint.getTiLeHutMp();
//            int hpHoi = 0;
//            hpHoi += ((long) dame * tiLeHutHp / 100);
//            int mpHoi = 0;
//            mpHoi += ((long) dame * tiLeHutMp / 100);
//            if (hpHoi > 0 || mpHoi > 0) {
//                PlayerService.gI().hoiPhuc(player, hpHoi, mpHoi);
//            }
//        }
//    }
    private void hutHPMP(Player player, long dame, boolean attackMob) {
        int tiLeHutHp = player.nPoint.getTileHutHp(attackMob);
        int tiLeHutMp = player.nPoint.getTiLeHutMp();
        long hpHoi = dame * tiLeHutHp / 100;
        long mpHoi = dame * tiLeHutMp / 100;
        if (hpHoi > 0 || mpHoi > 0) {
            PlayerService.gI().hoiPhuc(player, hpHoi, mpHoi);
        }
    }

    private void playerAttackPlayer(Player plAtt, Player plInjure, boolean miss) {
        if (plInjure.effectSkill.anTroi) {
            plAtt.nPoint.isCrit100 = true;
        }
        long dameHit = plInjure.injured(plAtt, miss ? 0 : plAtt.nPoint.getDameAttack(false), false, false);
        if (plInjure.isBoss) {
            dameHit += dameHit * plAtt.nPoint.percentDameBoss / 100;
        }
        hoaXuongPlayer(plAtt, plInjure);
        phanSatThuong(plAtt, plInjure, dameHit);
        hutHPMP(plAtt, dameHit, false);
        Message msg;
        try {
            msg = new Message(-60);
            msg.writer().writeInt((int) plAtt.id); //id pem
            msg.writer().writeByte(plAtt.playerSkill.skillSelect.skillId); //skill pem
            msg.writer().writeByte(1); //số người pem
            msg.writer().writeInt((int) plInjure.id); //id ăn pem
            byte typeSkill = SkillUtil.getTyleSkillAttack(plAtt.playerSkill.skillSelect);
            msg.writer().writeByte(typeSkill == 2 ? 0 : 1); //read continue
            msg.writer().writeByte(typeSkill); //type skill
            msg.writer().writeLong(dameHit); //dame ăn
            msg.writer().writeBoolean(plInjure.isDie()); //is die
            msg.writer().writeBoolean(plAtt.nPoint.isCrit); //crit
            if (typeSkill != 1) {
                Service.getInstance().sendMessAllPlayerInMap(plAtt, msg);
                msg.cleanup();
            } else {
                plInjure.sendMessage(msg);
                msg.cleanup();
                msg = new Message(-60);
                msg.writer().writeInt((int) plAtt.id); //id pem
                msg.writer().writeByte(plAtt.playerSkill.skillSelect.skillId); //skill pem
                msg.writer().writeByte(1); //số người pem
                msg.writer().writeInt((int) plInjure.id); //id ăn pem
                msg.writer().writeByte(typeSkill == 2 ? 0 : 1); //read continue
                msg.writer().writeByte(0); //type skill
                msg.writer().writeLong(dameHit); //dame ăn
                msg.writer().writeBoolean(plInjure.isDie()); //is die
                msg.writer().writeBoolean(plAtt.nPoint.isCrit); //crit
                Service.getInstance().sendMessAnotherNotMeInMap(plInjure, msg);
                msg.cleanup();
            }
            Service.getInstance().addSMTN(plInjure, (byte) 2, 1, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playerAttackMob(Player plAtt, Mob mob, boolean miss, boolean dieWhenHpFull) {
        if (!mob.isDie()) {
            if (plAtt.effectSkin.isVoHinh) {
                plAtt.effectSkin.isVoHinh = false;
            }
            long dameHit = plAtt.nPoint.getDameAttack(true);
            if ((plAtt.charms.tdBatTu > System.currentTimeMillis() || plAtt.itemTime.isMaTroi) && plAtt.nPoint.hp == 1) {
                dameHit = 0;
            }
            if (plAtt.charms.tdManhMe > System.currentTimeMillis()) {
                dameHit += (dameHit * 150 / 100);
            }
            if (plAtt.isPet) {
                if (((Pet) plAtt).charms.tdDeTu > System.currentTimeMillis()) {
                    dameHit *= 2;
                }
            }
            if (miss) {
                dameHit = 0;
            }
            hutHPMP(plAtt, dameHit, true);
            sendPlayerAttackMob(plAtt, mob);
            mob.injured(plAtt, dameHit, dieWhenHpFull);
        }
    }

    private void sendPlayerPrepareSkill(Player player, int affterMiliseconds) {
        Message msg;
        try {
            msg = new Message(-45);
            msg.writer().writeByte(4);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeShort(player.playerSkill.skillSelect.skillId);
            msg.writer().writeShort(affterMiliseconds);
            Service.getInstance().sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPlayerPrepareBom(Player player, int affterMiliseconds) {
        Message msg;
        try {
            msg = new Message(-45);
            msg.writer().writeByte(7);
            msg.writer().writeInt((int) player.id);
//            msg.writer().writeShort(player.playerSkill.skillSelect.skillId);
            msg.writer().writeShort(104);
            msg.writer().writeShort(affterMiliseconds);
            Service.getInstance().sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean canUseSkillWithMana(Player player) {
        if (player.playerSkill.skillSelect != null) {
//            if (player.playerSkill.skillSelect.template.id == Skill.KAIOKEN) {
//                int hpUse = player.nPoint.hpMax / 100 * 10;
//                if (player.nPoint.hp <= hpUse) {
//                    return false;
//                }
//            }
            switch (player.playerSkill.skillSelect.template.manaUseType) {
                case 0:
                    if (player.nPoint.mp >= player.playerSkill.skillSelect.manaUse) {
                        return true;
                    } else {
                        return false;
                    }
                case 1:
                    int mpUse = (int) (player.nPoint.mpMax * player.playerSkill.skillSelect.manaUse / 100);
                    if (player.nPoint.mp >= mpUse) {
                        return true;
                    } else {
                        return false;
                    }
                case 2:
                    if (player.nPoint.mp > 0) {
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public boolean canUseSkillWithCooldown(Player player) {
        return Util.canDoWithTime(player.playerSkill.skillSelect.lastTimeUseThisSkill, player.playerSkill.skillSelect.coolDown - 50);
    }

    public void affterUseSkill(Player player, int skillId) {
        Intrinsic intrinsic = player.playerIntrinsic.intrinsic;
        switch (skillId) {
            case Skill.DICH_CHUYEN_TUC_THOI:
                if (intrinsic.id == 6) {
                    player.nPoint.dameAfter = intrinsic.param1;
                }
                break;
            case Skill.THOI_MIEN:
                if (intrinsic.id == 7) {
                    player.nPoint.dameAfter = intrinsic.param1;
                }
                break;
            case Skill.SOCOLA:
                if (intrinsic.id == 14) {
                    player.nPoint.dameAfter = intrinsic.param1;
                }
                break;
            case Skill.TROI:
                if (intrinsic.id == 22) {
                    player.nPoint.dameAfter = intrinsic.param1;
                }
                break;
        }
        setMpAffterUseSkill(player);
        setLastTimeUseSkill(player, skillId);
    }

    private void setMpAffterUseSkill(Player player) {
        if (player.playerSkill.skillSelect != null) {
            switch (player.playerSkill.skillSelect.template.manaUseType) {
                case 0:
                    if (player.nPoint.mp >= player.playerSkill.skillSelect.manaUse) {
                        player.nPoint.setMp(player.nPoint.mp - player.playerSkill.skillSelect.manaUse);
                    }
                    break;
                case 1:
                    int mpUse = (int) (player.nPoint.mpMax * player.playerSkill.skillSelect.manaUse / 100);
                    if (player.nPoint.mp >= mpUse) {
                        player.nPoint.setMp(player.nPoint.mp - mpUse);
                    }
                    break;
                case 2:
                    player.nPoint.setMp(0);
                    break;
            }
            PlayerService.gI().sendInfoHpMpMoney(player);
        }
    }

    private void setLastTimeUseSkill(Player player, int skillId) {
        Intrinsic intrinsic = player.playerIntrinsic.intrinsic;
        int subTimeParam = 0;
        switch (skillId) {
            case Skill.TRI_THUONG:
                if (intrinsic.id == 10) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.THAI_DUONG_HA_SAN:
                if (intrinsic.id == 3) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.QUA_CAU_KENH_KHI:
                if (intrinsic.id == 4) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.KHIEN_NANG_LUONG:
                if (!player.isPet && (player.zone.map.mapId >= 85 && player.zone.map.mapId <= 91)) {
                    Service.getInstance().sendThongBao(player, "Nội tại khiên sẽ mất tác dụng tại đây");
                    return;
                }
                if (intrinsic.id == 5 || intrinsic.id == 15 || intrinsic.id == 20) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.MAKANKOSAPPO:
                if (intrinsic.id == 11) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.DE_TRUNG:
                if (intrinsic.id == 12) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.TU_SAT:
                if (intrinsic.id == 19) {
                    subTimeParam = intrinsic.param1;
                }
                break;
            case Skill.HUYT_SAO:
                if (intrinsic.id == 21) {
                    subTimeParam = intrinsic.param1;
                }
                break;
        }
        int coolDown = player.playerSkill.skillSelect.coolDown;
        player.playerSkill.skillSelect.lastTimeUseThisSkill = System.currentTimeMillis() - (coolDown * subTimeParam / 100);
        if (subTimeParam != 0) {
            Service.getInstance().sendTimeSkill(player);
        }
    }

    private boolean canHsPlayer(Player player, Player plTarget) {
        if (plTarget == null) {
            return false;
        }
        if (plTarget.isBoss) {
            return false;
        }
        if (plTarget.typePk == ConstPlayer.PK_ALL) {
            return false;
        }
        if (plTarget.typePk == ConstPlayer.PK_PVP) {
            return false;
        }
        if (player.cFlag != 0) {
            if (plTarget.cFlag != 0 && plTarget.cFlag != player.cFlag) {
                return false;
            }
        } else if (plTarget.cFlag != 0) {
            return false;
        }
        return true;
    }

    public boolean canAttackPlayer(Player pl1, Player pl2) {
        if (pl2 != null && !pl1.isDie() && !pl2.isDie()) {
            if (pl1.typePk > 0 || pl2.typePk > 0) {
                return true;
            }
            if ((pl1.cFlag != 0 && pl2.cFlag != 0)
                    && (pl1.cFlag == 8 || pl2.cFlag == 8 || pl1.cFlag != pl2.cFlag)) {
                return true;
            }
            PVP pvp = PVPServcice.gI().findPvp(pl1);
            if (pvp != null) {
                if ((pvp.player1.equals(pl1) && pvp.player2.equals(pl2)
                        || (pvp.player1.equals(pl2) && pvp.player2.equals(pl1)))) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    private void sendPlayerAttackMob(Player plAtt, Mob mob) {
        Message msg;
        try {
            msg = new Message(54);
            msg.writer().writeInt((int) plAtt.id);
            msg.writer().writeByte(plAtt.playerSkill.skillSelect.skillId);
            msg.writer().writeByte(mob.id);
            Service.getInstance().sendMessAllPlayerInMap(plAtt, msg);
            msg.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectSkill(Player player, int skillId) {
        Skill skillBefore = player.playerSkill.skillSelect;
        for (Skill skill : player.playerSkill.skills) {
            if (skill.skillId != -1 && skill.template.id == skillId) {
                player.playerSkill.skillSelect = skill;
                switch (skillBefore.template.id) {
                    case Skill.DRAGON:
                    case Skill.KAMEJOKO:
                    case Skill.DEMON:
                    case Skill.MASENKO:
                    case Skill.LIEN_HOAN:
                    case Skill.GALICK:
                    case Skill.ANTOMIC:
                        switch (skill.template.id) {
                            case Skill.KAMEJOKO:
                            case Skill.DRAGON:
                            case Skill.DEMON:
                            case Skill.MASENKO:
                            case Skill.LIEN_HOAN:
                            case Skill.GALICK:
                            case Skill.ANTOMIC:
                                break;
                        }
                        break;
                }
                break;
            }
        }
    }

    public void useSKillNotFocus(Player player, short skillID, short xPlayer, short yPlayer, byte dir, short x, short y) {
        try {
            if (player.getSession().isAdmin) {
                Skill skillSelect = player.playerSkill.skillSelect;
                if (skillSelect.template.id != skillID) {
                    selectSkill(player, skillID);
                    return;
                }
                if (SpecialSkill.gI().executeSpecialSkill(player, skillID, xPlayer, yPlayer, dir, x, y)) {
                    return;
                }
            } else {
                if (canUseSkillWithMana(player) && canUseSkillWithCooldown(player)) {
                    Skill skillSelect = player.playerSkill.skillSelect;
                    if (skillSelect.template.id != skillID) {
                        selectSkill(player, skillID);
                        return;
                    }
                    if (SpecialSkill.gI().executeSpecialSkill(player, skillID, xPlayer, yPlayer, dir, x, y)) {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void learSkillSpecial(Player player, byte skillID) {
        Message message = null;
        try {
            Skill curSkill = SkillUtil.createSkill(skillID, 1);
            SkillUtil.setSkill(player, curSkill);
            message = Service.getInstance().messageSubCommand((byte) 23);
            message.writer().writeShort(curSkill.skillId);
            player.sendMessage(message);
            message.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }

        }
    }

    public void sendUpdateSkill(Player player, byte skillID) {
        Message message = null;
        try {
            Skill curSkill = player.playerSkill.getSkillbyId(skillID);

            if (curSkill != null) {
                message = Service.getInstance().messageSubCommand((byte) 62);
                message.writer().writeShort(curSkill.skillId);
                message.writer().writeByte(0);
                message.writer().writeShort(50);
                player.sendMessage(message);
                message.cleanup();

                message.cleanup();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }

        }
    }

    public void updateSkillSpecial(Player player) {
        if (player == null) {
            return;
        }
        try {
            if (player.isDie() || player.effectSkill.isHaveEffectSkill()) {
                player.skillSpecial.closeSkillSpecial();
                return;
            }
            if (player.skillSpecial.skillSpecial.template.id == Skill.MAFUBA) {
//                Service.gI().sendThongBao(player, "Kỹ năng bị đang bảo trì do quá OP!");
                this.activeSkillMaFuBa(player);
            } else {
                if (player.skillSpecial.stepSkillSpecial == 0
                        && Util.canDoWithTime(player.skillSpecial.lastTimeSkillSpecial,
                        SkillSpecial.TIME_GONG)) {
                    player.skillSpecial.lastTimeSkillSpecial = System.currentTimeMillis();
                    player.skillSpecial.stepSkillSpecial = 1;
                    if (player.skillSpecial.skillSpecial.template.id == Skill.SUPER_KAME) {
                        SpecialSkill.gI().sendEffEndUseSkillNotFocus(player, SkillSpecial.TIME_END_24_25);
                    } else {
                        SpecialSkill.gI().sendEffEndUseSkillNotFocus(player, SkillSpecial.TIME_END_24_25);
                    }
                } else if (player.skillSpecial.stepSkillSpecial == 1
                        && !Util.canDoWithTime(player.skillSpecial.lastTimeSkillSpecial,
                        SkillSpecial.TIME_END_24_25)) {
                    if (player.zone == null) {
                        return;
                    }
                    for (int i = 0; i < player.zone.getHumanoids().size(); i++) {
                        Player playerMap = player.zone.getHumanoids().get(i);
                        if (playerMap == null) {
                            continue;
                        }
                        if (player.skillSpecial.dir == -1 && !playerMap.isDie()
                                && playerMap.location.x <= player.location.x - 15
                                && Math.abs(playerMap.location.x - player.skillSpecial._xPlayer) <= player.skillSpecial._xObjTaget
                                && Math.abs(playerMap.location.y - player.skillSpecial._yPlayer) <= player.skillSpecial._yObjTaget
                                && this.canAttackPlayer(player, playerMap)) {
                            this.playerAttackPlayer(player, playerMap, false);
                            PlayerService.gI().sendInfoHpMpMoney(playerMap);
                        }
                        if (player.skillSpecial.dir == 1 && !playerMap.isDie()
                                && playerMap.location.x >= player.location.x + 15
                                && Math.abs(playerMap.location.x - player.skillSpecial._xPlayer) <= player.skillSpecial._xObjTaget
                                && Math.abs(playerMap.location.y - player.skillSpecial._yPlayer) <= player.skillSpecial._yObjTaget
                                && this.canAttackPlayer(player, playerMap)) {
                            this.playerAttackPlayer(player, playerMap, false);
                            PlayerService.gI().sendInfoHpMpMoney(playerMap);
                        }
                    }
                    for (Mob mobMap : player.zone.mobs) {
                        if (mobMap == null) {
                            continue;
                        }
                        if (player.skillSpecial.dir == -1 && !mobMap.isDie()
                                && mobMap.location.x <= player.skillSpecial._xPlayer - 15
                                && Math.abs(mobMap.location.x - player.skillSpecial._xPlayer) <= player.skillSpecial._xObjTaget
                                && Math.abs(mobMap.location.y - player.skillSpecial._yPlayer) <= player.skillSpecial._yObjTaget) {
                            this.playerAttackMob(player, mobMap, false, false);
                        }
                        if (player.skillSpecial.dir == 1 && !mobMap.isDie()
                                && mobMap.location.x >= player.skillSpecial._xPlayer + 15
                                && Math.abs(mobMap.location.x - player.skillSpecial._xPlayer) <= player.skillSpecial._xObjTaget
                                && Math.abs(mobMap.location.y - player.skillSpecial._yPlayer) <= player.skillSpecial._yObjTaget) {
                            this.playerAttackMob(player, mobMap, false, false);
                        }
                    }
                } else if (player.skillSpecial.stepSkillSpecial == 1) {
                    player.skillSpecial.closeSkillSpecial();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startSkillSpecialID24(Player player) {
        Message message = null;
        try {
            message = new Message(-45);
            message.writer().writeByte(21);
            message.writer().writeInt((int) player.id);
            message.writer().writeShort(player.skillSpecial.skillSpecial.template.id);
            message.writer().writeShort(player.skillSpecial._xPlayer + ((player.skillSpecial.dir == -1)
                    ? (-player.skillSpecial._xObjTaget) : player.skillSpecial._xObjTaget));
            message.writer().writeShort(player.skillSpecial._xPlayer);
            message.writer().writeShort(3000); // thời gian skill chưởng
            message.writer().writeShort(player.skillSpecial._yObjTaget);
            message.writer().writeByte(player.newSkill.TypePaint);
            message.writer().writeByte(0);//count mob and boss and player ap dung cho ma fong 3
            message.writer().writeByte(player.newSkill.TypeItem);
            Service.getInstance().sendMessAllPlayerInMap(player, message);
            message.cleanup();
        } catch (final Exception ex) {
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }
        }
    }

    public void startSkillSpecialID25(Player player) {
        Message message = null;
        try {
            message = new Message(-45);
            message.writer().writeByte(21);
            message.writer().writeInt((int) player.id);
            message.writer().writeShort(player.skillSpecial.skillSpecial.template.id);
            message.writer().writeShort(player.skillSpecial._xPlayer + ((player.skillSpecial.dir == -1) ? (-player.skillSpecial._xObjTaget) : player.skillSpecial._xObjTaget));
            message.writer().writeShort(player.skillSpecial._yPlayer);
            message.writer().writeShort(3000);
            message.writer().writeShort(25);
            message.writer().writeByte(player.newSkill.TypePaint);
            message.writer().writeShort(0);//count mob and boss and player
            message.writer().writeByte(player.newSkill.TypeItem);
            Service.getInstance().sendMessAllPlayerInMap(player, message);
            message.cleanup();
        } catch (final Exception ex) {
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }
        }
    }

    public void startSkillSpecialID26(Player player) {
        Message message = null;
        try {
            int typePaint = SpecialSkill.getTypePaintSkill(player, 26);
            message = new Message(-45);
            message.writer().writeByte(21);
            message.writer().writeInt((int) player.id);
            message.writer().writeShort(26);
            message.writer().writeShort(player.skillSpecial._xPlayer
                    + ((player.skillSpecial.dir == -1) ? (-75) : 75));
            message.writer().writeShort(player.skillSpecial._yPlayer);
            message.writer().writeShort(3000);
            message.writer().writeShort(player.skillSpecial._yObjTaget);
//            message.writer().writeByte(player.newSkill.TypePaint);
            message.writer().writeByte(typePaint);
            final byte size = (byte) (player.skillSpecial.playersTaget.size()
                    + player.skillSpecial.mobsTaget.size());
            message.writer().writeByte(size);
            for (Player playerMap : player.skillSpecial.playersTaget) {
                message.writer().writeByte(1);
                message.writer().writeInt((int) playerMap.id);
            }
            for (Mob mobMap : player.skillSpecial.mobsTaget) {
                message.writer().writeByte(0);
                message.writer().writeByte(mobMap.id);
            }
            message.writer().writeByte(0);
            Service.getInstance().sendMessAllPlayerInMap(player, message);
            message.cleanup();
        } catch (final Exception ex) {
            ex.printStackTrace();
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }
        }

    }

    public void activeSkillMaFuBa(Player player) {
        try {
            if (Util.canDoWithTime(player.skillSpecial.lastTimeSkillSpecial, SkillSpecial.TIME_GONG)) {
                player.skillSpecial.lastTimeSkillSpecial = System.currentTimeMillis();
                player.skillSpecial.closeSkillSpecial();
                int timeBinh = SkillUtil.getTimeBinh();//thời gian biến thành bình
                //hút người
                for (Player playerMap : player.zone.getHumanoids()) {
                    if (playerMap == null || playerMap.id == player.id) {
                        continue;
                    }
                    if (player.skillSpecial.dir == -1 && !playerMap.isDie() && Util.getDistance(player, playerMap) <= 500 && this.canAttackPlayer(player, playerMap)) {
                        player.skillSpecial.playersTaget.add(playerMap);

                    } else if (player.skillSpecial.dir == 1 && !playerMap.isDie() && Util.getDistance(player, playerMap) <= 500 && this.canAttackPlayer(player, playerMap)) {
                        player.skillSpecial.playersTaget.add(playerMap);

                    }
                }
                //hút quái
                for (Mob mobMap : player.zone.mobs) {
                    if (mobMap == null) {
                        continue;
                    }
                    if (player.skillSpecial.dir == -1 && !mobMap.isDie() && Util.getDistance(player, mobMap) <= 500) {
                        player.skillSpecial.mobsTaget.add(mobMap);
                    } else if (player.skillSpecial.dir == 1 && !mobMap.isDie() && Util.getDistance(player, mobMap) <= 500) {
                        player.skillSpecial.mobsTaget.add(mobMap);

                    }
                }
                //bắt đầu hút
                this.startSkillSpecialID26(player);
                Thread.sleep(3000);//nghỉ 3s
                //biến quái - bình
                for (Mob mobMap : player.zone.mobs) {
                    if (mobMap == null) {
                        continue;
                    }
                    if (player.skillSpecial.dir == -1 && !mobMap.isDie() && Util.getDistance(player, mobMap) <= 500) {
                        player.skillSpecial.mobsTaget.add(mobMap);
                    } else if (player.skillSpecial.dir == 1 && !mobMap.isDie() && Util.getDistance(player, mobMap) <= 500) {
                        player.skillSpecial.mobsTaget.add(mobMap);
                        {
                            if (player.isPl()) {
                                Item item = player.inventory.itemsBody.get(11);
                                if (item != null && item.isNotNullItem()) {
                                    EffectSkillService.gI().sendMobToBinh2(player, mobMap, timeBinh); // biến mob thành bình
                                } else {
                                    EffectSkillService.gI().sendMobToBinh(player, mobMap, timeBinh); // biến mob thành bình
                                }
                            } else {
                                EffectSkillService.gI().sendMobToBinh(player, mobMap, timeBinh); // biến mob thành bình
                            }
                            playerAttackMob(player, mobMap, false, false); // trừ dame
                        }
                    }
                }

                //biến người - bình
                ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

                for (Player playerMap : player.zone.getHumanoids()) {
                    if (playerMap == null || playerMap.id == player.id) {
                        continue;
                    }
                    if (player.skillSpecial.dir == -1 && !playerMap.isDie() && Util.getDistance(player, playerMap) <= 500
                            && this.canAttackPlayer(player, playerMap)) {
                        player.skillSpecial.playersTaget.add(playerMap);
                    } else if (player.skillSpecial.dir == 1 && !playerMap.isDie() && Util.getDistance(player, playerMap) <= 500
                            && this.canAttackPlayer(player, playerMap)) {
                        player.skillSpecial.playersTaget.add(playerMap);
                    }
                    if (this.canAttackPlayer(player, playerMap)) {
                        {
                            if (player.isPl()) {
                                Item item = player.inventory.itemsBody.get(11);
                                if (item != null && item.isNotNullItem()) {
                                    ItemTimeService.gI().sendItemTime(playerMap, 11237, timeBinh / 1000);
                                    EffectSkillService.gI().setBinh2(playerMap, System.currentTimeMillis(), timeBinh);
                                } else {
                                    ItemTimeService.gI().sendItemTime(playerMap, 11236, timeBinh / 1000);
                                    EffectSkillService.gI().setBinh(playerMap, System.currentTimeMillis(), timeBinh);
                                }
                            } else {
                                ItemTimeService.gI().sendItemTime(playerMap, 11236, timeBinh / 1000);
                                EffectSkillService.gI().setBinh(playerMap, System.currentTimeMillis(), timeBinh);
                            }
                            Service.getInstance().Send_Caitrang(playerMap);
                        }
                        Skill curSkill = SkillUtil.getSkillbyId(player, Skill.MAFUBA);
                        double ptdame = 0;
                        switch (curSkill.point) {
                            case 1:
                                ptdame = 0.1;
                                break;
                            case 2:
                                ptdame = 0.1 + (0.2 * 0.1); // Cộng thêm 20%
                                break;
                            case 3:
                                ptdame = 0.1 + (0.3 * 0.1); // Cộng thêm 20%
                                break;
                            case 4:
                                ptdame = 0.1 + (0.4 * 0.1); // Cộng thêm 20%
                                break;
                            case 5:
                                ptdame = 0.1 + (0.5 * 0.1); // Cộng thêm 20%
                                break;
                            case 6:
                                ptdame = 0.1 + (0.6 * 0.1); // Cộng thêm 20%
                                break;
                            case 7:
                                ptdame = 0.1 + (0.7 * 0.1); // Cộng thêm 20%
                                break;
                            case 8:
                                ptdame = 0.1 + (0.8 * 0.1); // Cộng thêm 20%
                                break;
                            case 9:
                                ptdame = 0.1 + (0.9 * 0.1); // Cộng thêm 20%
                                break;
                            default:
                                ptdame = 0.01;
                                break;
                        }
                        double dameHit = (player.nPoint.hpMax * ptdame);
                        for (int i = 0; i < 10; i++) {
                            final int index = i;
                            executorService.schedule(() -> {
                                playerMap.injured(player, (int) dameHit, false, false);
                                PlayerService.gI().sendInfoHpMpMoney(playerMap); //gửi in4 hp cho player bị nhốt
                                this.playerAttackPlayer(player, playerMap, false);
                                if (index == 0) {
                                    this.playerAttackPlayer(player, playerMap, true);
                                }
                            }, index, TimeUnit.SECONDS);
                        }
                    }
                }
                executorService.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
