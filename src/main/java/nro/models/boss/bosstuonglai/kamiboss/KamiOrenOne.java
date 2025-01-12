package nro.models.boss.bosstuonglai.kamiboss;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;

import java.util.Iterator;

public class KamiOrenOne extends KamiOren {
    public KamiOrenOne() {
        super(BossFactory.KAMI_OREN_ONE);
    }

    @Override
    public void joinMap() {
        super.joinMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.KAMI_OREN_TWO) {
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                KamiOrenTwo kamiOrenTwo = new KamiOrenTwo(this.zone);
                kamiOrenTwo.respawnBoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
