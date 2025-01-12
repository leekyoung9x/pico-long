package nro.models.boss.Omega;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.player.Player;
import nro.services.*;
import nro.utils.Util;

import java.util.List;
import java.util.Random;
import nro.models.item.Item;
import nro.models.map.ItemMap;

public class OmegaPlus extends Boss {

    private List<Long> playerAttack;

    public OmegaPlus() {
        super(BossFactory.OMEGA_PLUS, BossData.OMEGA_PLUS);
    }

    protected OmegaPlus(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            ItemMap itemMap = new ItemMap(this.zone, 1950, Util.nextInt(1, 10), this.location.x, this.location.y, plKill.id);
            if (itemMap != null) {
                Service.getInstance().dropItemMap(this.zone, itemMap);
            }
        }
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
        if (this.isDie()) {
            return 0;
        } else {
            long dame = super.injured(plAtt, damage, piercing, isMobAttack);
            if (this.isDie()) {
                rewards(plAtt);
            }
            dame /= 10;
            return dame;
        }
    }

}
