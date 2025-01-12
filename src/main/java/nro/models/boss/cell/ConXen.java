package nro.models.boss.cell;

import nro.models.boss.*;
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
 * @Build Arriety
 */
public class ConXen extends Boss {

    public ConXen() {
        super(BossFactory.CON_XEN, BossData.CON_XEN);
    }

    protected ConXen(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
//
//        BillEgg.createBillEgg(plKill);
//        Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được trứng Cell ở nhà");
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Các ngươi chờ đấy, ta sẽ quay lại sau"};
    }

    @Override
    public void idle() {
        if (this.countIdle >= this.maxIdle) {
            this.maxIdle = Util.nextInt(0, 3);
            this.countIdle = 0;
            this.changeAttack();
        } else {
            this.countIdle++;
        }
    }

    @Override
    public void checkPlayerDie(Player pl) {
        if (pl.isDie()) {
            Service.getInstance().chat(this, "Chừa nha " + plAttack.name + " động vào ta chỉ có chết.");
            this.plAttack = null;
        }
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        try {
            if(plAtt == null){
                return 0;
            }

            if (!this.isDie()) {
                if (this.isMiniPet) {
                    return 0;
                }

                damage = 1;

                if (plAtt.getSession() != null && plAtt.isAdmin()) {
                    damage = 50;
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
    public void joinMap() {
        super.joinMap();

        if (id == BossFactory.CON_XEN) {
            createCopy(this);
        }
    }

    protected void createCopy(Boss boss) {
        CreateClone(boss, (byte) BossFactory.XEN_CON_ONE);
        CreateClone(boss, (byte) BossFactory.XEN_CON_TWO);
    }

    private static void CreateClone(Boss boss, byte id, BossData bossData) {
        Boss xenCon = new ConXen(id, bossData);
        xenCon.zone = boss.zone;
        xenCon.joinMap();
    }

    private static void CreateClone(Boss boss, byte id) {
        Boss xenCon = new ConXen(id, BossData.CON_XEN);
        xenCon.zone = boss.zone;
        xenCon.joinMap();
    }
}
