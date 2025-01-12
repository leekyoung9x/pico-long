/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.bill;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.player.Player;

/**
 *
 * @author Arriety
 */
public class WhisBill extends Boss {

    public WhisBill() {
        super(BossFactory.WHIS_BILL, BossData.WHIS_BILL);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (this.isDie()) {
                rewards(plAtt);
            }
            return super.injured(plAtt, damage, piercing, isMobAttack);
        }
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            this.dropItemReward(1968, (int) pl.id);
            generalRewards(pl);
        }
    }

    @Override
    public void idle() {
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

    @Override
    public void checkPlayerDie(Player pl) {
    }

    @Override
    public void initTalk() {
    }

    @Override
    public void joinMap() {
        super.joinMap();
        if (this.zone != null) {
        } else {
            BossManager.gI().removeBoss(this);
        }
    }
}
