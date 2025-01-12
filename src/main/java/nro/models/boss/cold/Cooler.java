package nro.models.boss.cold;

import java.util.Random;
import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.FutureBoss;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 * Arriety
 */
public class Cooler extends Boss {

    public Cooler() {
        super(BossFactory.COOLER, BossData.COOLER);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
//        if (this.isDie()) {
//            return 0;
//        } else {
//            if (!piercing && Util.isTrue(25, 100)) {
//                this.chat("Xí hụt lêu lêu");
//                return 0;
//            }
//            if (this.isDie()) {
//                rewards(plAtt);
//            }
//            return super.injured(plAtt, damage, piercing, isMobAttack);
//        }
        if (plAtt == null) {
            return 0;
        }
        if (this.isDie()) {
            return 0;
        } else {
            // dame tối đa gây ra cho boss là 1
            int point = 1;
            // không bị ảnh hưởng bởi Super Kame, Super Atomic
            if (SkillUtil.isUseSkillNew(plAtt)) {
                return 0;
            }
            return super.injured(plAtt, point, piercing, isMobAttack);
        }
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            int slDrop = 1;
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.NGOC_RONG_BANG_2_SAO, slDrop, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, itemMap);
        }
    }
//
//    @Override
//    public void rewards(Player plKill) {
//        if (Util.isTrue(15, 100)) {
//            if (Util.isTrue(1, 5)) {
//                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
//            } else {
//                Service.getInstance().dropItemMap(this.zone, new ItemMap(zone, 457, 1, this.location.x, zone.map.yPhysicInTop(this.location.x, this.location.y - 24), plKill.id));
//            }
//        } else {
//            int hlt = Util.nextInt(3, 5);
//            for (int i = 0; i < hlt; i++) {
//                ItemMap hon = new ItemMap(zone, 2029, 1, 10 * i + this.location.x, zone.map.yPhysicInTop(this.location.x, 0), -1);
//                Service.getInstance().dropItemMap(this.zone, hon);
//            }
//        }
//    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkMidle = new String[]{"Ta chính là đệ nhất vũ trụ cao thủ"};
        textTalkAfter = new String[]{"Ác quỷ biến hình aaa..."};
    }

//    @Override
//    public void leaveMap() {
//        Boss cooler2 = BossFactory.createBoss(BossFactory.COOLER2);
//        cooler2.zone = this.zone;
//        this.setJustRestToFuture();
//        super.leaveMap();
//    }

}
