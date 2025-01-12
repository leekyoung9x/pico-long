package nro.models.boss.dhvt;

import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.mabu.MabuWar;
import nro.models.player.Player;
import nro.services.EffectSkillService;
import nro.services.MapService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

public class Locopo  extends BossDHVT{
    public Locopo(Player player) {
        super((byte) BossFactory.LOPOCO, BossData.LOCOPO);
        this.playerAtt = player;
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

                this.nPoint.subHP(damage);

                if (this.nPoint.hp <= this.nPoint.hpMax / 2) {
                    this.outfit = new short[] {1449, 1450, 1451};
                    Service.getInstance().Send_Caitrang(this);
                }

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
}
