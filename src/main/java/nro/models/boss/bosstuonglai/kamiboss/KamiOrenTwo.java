package nro.models.boss.bosstuonglai.kamiboss;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.Zone;

import java.util.Iterator;

public class KamiOrenTwo extends KamiOren {
    public KamiOrenTwo() {
        super(BossFactory.KAMI_OREN_TWO);
    }

    public KamiOrenTwo(Zone zone) {
        super(BossFactory.KAMI_OREN_TWO);
        this.zone = zone;
    }

    public void leaveMap() {
        super.leaveMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.KAMI_OREN_TWO) {
                    iterator.remove();
                    boss.dispose();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
