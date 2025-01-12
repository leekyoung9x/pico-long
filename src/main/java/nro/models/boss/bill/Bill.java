package nro.models.boss.bill;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.FutureBoss;
import nro.models.map.mabu.MabuWar;
import nro.models.npc.specialnpc.BillEgg;
import nro.models.player.Player;
import nro.services.EffectSkillService;
import nro.services.MapService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 * @author Arriety
 */
public class Bill extends FutureBoss {

    public Bill() {
        super(BossFactory.BILL, BossData.BILL);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        try {
            if (plAtt == null) {
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
                    if (!piercing && Util.isTrue(this.nPoint.tlNeDon, 100)) {
                        return 0;
                    }
                }
                if (isMobAttack && (this.charms.tdBatTu > System.currentTimeMillis() || this.itemTime.isMaTroi) && damage >= this.nPoint.hp) {
                    damage = this.nPoint.hp - 1;
                }
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
                damage /= 2;

                if (plAtt.getSession() != null && plAtt.isAdmin()) {
                    damage = this.nPoint.hpMax / 3;
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
                    rewards(plAtt);
                    notifyPlayeKill(plAtt);
                    die();
                }
                return damage;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void idle() {
    }

    @Override
    public void checkPlayerDie(Player pl) {
    }

    @Override
    public void initTalk() {
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            this.dropItemReward(1968, (int) pl.id);
//            generalRewards(pl);
        }
    }

    @Override
    public void leaveMap() {
        Boss whis = BossFactory.createBoss(BossFactory.WHIS);
        this.setJustRestToFuture();
        super.leaveMap();
    }
}
