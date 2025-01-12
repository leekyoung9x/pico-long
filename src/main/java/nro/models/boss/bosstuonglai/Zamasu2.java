package nro.models.boss.bosstuonglai;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.Zone;
import nro.models.player.Player;

public class Zamasu2 extends Boss {
    public Zamasu2(int id , Zone zone) {
        super(id, BossData.POCTHO);
        this.zone = zone;
    }
    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        this.dropItemReward(195, (int) pl.id);
        generalRewards(pl);
    }

    @Override
    public void idle() {

    }
    @Override
    public void joinMap() {
        super.joinMap();
    }
    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkMidle = new String[]{};
        textTalkAfter = new String[]{};
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }
}
