package nro.models.boss.event.noel.tuanloc;

import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;

import java.util.Iterator;

public class TuanLocOne extends TuanLoc {
    public TuanLocOne() {
        super(BossFactory.TUAN_LOC_ONE);
    }

    @Override
    public void joinMap() {
        super.joinMap();

//        try {
//            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
//            while (iterator.hasNext()) {
//                Boss boss = iterator.next();
//                if (boss != null && (boss.id == BossFactory.TUAN_LOC_TWO || boss.id == BossFactory.TUAN_LOC_THREE || boss.id == BossFactory.TUAN_LOC_FOUR || boss.id == BossFactory.TUAN_LOC_FIVE || boss.id == BossFactory.TUAN_LOC_SIX || boss.id == BossFactory.TUAN_LOC_SEVEN || boss.id == BossFactory.TUAN_LOC_EIGHT || boss.id == BossFactory.TUAN_LOC_NINE || boss.id == BossFactory.TUAN_LOC_TEN)) {
//                    iterator.remove();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        new Thread(() -> {
//            try {
//                Thread.sleep(1000);
//                TuanLocTwo tuanLocTwo = new TuanLocTwo(this.zone);
//                tuanLocTwo.respawnBoss();
//                TuanLocThree tuanLocThree = new TuanLocThree(this.zone);
//                tuanLocThree.respawnBoss();
//                TuanLocFour tuanLocFour = new TuanLocFour(this.zone);
//                tuanLocFour.respawnBoss();
//                TuanLocFive tuanLocFive = new TuanLocFive(this.zone);
//                tuanLocFive.respawnBoss();
//                TuanLocSix tuanLocSix = new TuanLocSix(this.zone);
//                tuanLocSix.respawnBoss();
//                TuanLocSeven tuanLocSeven = new TuanLocSeven(this.zone);
//                tuanLocSeven.respawnBoss();
//                TuanLocEight tuanLocEight = new TuanLocEight(this.zone);
//                tuanLocEight.respawnBoss();
//                TuanLocNine tuanLocNine = new TuanLocNine(this.zone);
//                tuanLocNine.respawnBoss();
//                TuanLocTen tuanLocTen = new TuanLocTen(this.zone);
//                tuanLocTen.respawnBoss();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
