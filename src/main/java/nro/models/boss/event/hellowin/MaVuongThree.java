package nro.models.boss.event.hellowin;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.Zone;

import java.util.Iterator;

public class MaVuongThree extends MaVuong {
    public MaVuongThree() {
        super(BossFactory.MAVUONG_THREE);
    }

    public MaVuongThree(Zone zone) {
        super(BossFactory.MAVUONG_THREE);
        this.zone = zone;
    }

    public void leaveMap() {
        super.leaveMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.MAVUONG_THREE) {
                    iterator.remove();
                    boss.dispose();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
