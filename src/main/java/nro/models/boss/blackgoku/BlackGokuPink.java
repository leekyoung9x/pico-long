package nro.models.boss.blackgoku;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.Zone;
import nro.models.player.Player;

import java.util.Iterator;

public class BlackGokuPink extends BlackGokuBase {
    public BlackGokuPink() {
        super(BossFactory.BLACK_GOKU_PINK, BossData.BlackGokuPink);
    }

    public BlackGokuPink(Zone zone) {
        super(BossFactory.BLACK_GOKU_PINK, BossData.BlackGokuPink);
        this.zone = zone;
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            this.dropItemReward(ConstItem.DE_TU_BLACK_GOKU, (int) pl.id);
        }
    }

    public void leaveMap() {
        super.leaveMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.BLACK_GOKU_PINK) {
                    iterator.remove();
                    boss.dispose();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
