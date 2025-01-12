package nro.models.boss.event.hellowin;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.boss.bosstuonglai.kamiboss.KamiOren;
import nro.models.boss.bosstuonglai.kamiboss.KamiOrenTwo;

import java.util.Iterator;

public class MaVuongOne extends MaVuong {
    public MaVuongOne() {
        super(BossFactory.MAVUONG_ONE);
    }

    @Override
    public void joinMap() {
        super.joinMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.MAVUONG_TWO) {
                    iterator.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                MaVuongTwo maVuongTwo = new MaVuongTwo(this.zone);
                maVuongTwo.respawnBoss();
                MaVuongThree maVuongThree = new MaVuongThree(this.zone);
                maVuongThree.respawnBoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
