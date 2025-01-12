package nro.models.boss.event.noel.tuanloc;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.Zone;

import java.util.Iterator;

public class TuanLocSix extends TuanLoc {
    public TuanLocSix() {
        super(BossFactory.TUAN_LOC_SIX);
    }

    public TuanLocSix(Zone zone) {
        super(BossFactory.TUAN_LOC_SIX);
        this.zone = zone;
    }

    public void leaveMap() {
        super.leaveMap();

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.TUAN_LOC_SIX) {
                    iterator.remove();
                    boss.dispose();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
