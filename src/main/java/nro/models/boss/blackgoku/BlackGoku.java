package nro.models.boss.blackgoku;

import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.Zone;

public class BlackGoku extends BlackGokuBase {
    public BlackGoku() {
        super(BossFactory.BLACK_GOKU, BossData.BlackGoku);
    }

    @Override
    public void leaveMap() {
        Zone zone = this.zone;

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                BlackGokuPink bossGoku = new BlackGokuPink(zone);
                bossGoku.respawnBoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        super.leaveMap();
    }
}
