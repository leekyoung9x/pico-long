package nro.models.boss.bill;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 *
 * @author Arrriety
 *
 */
public class Whis extends Boss {

    public Whis() {
        super(BossFactory.WHIS, BossData.WHIS);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (Util.isTrue(50, 100) && !plAtt.isAdmin()) {
                this.chat("Xí hụt lêu lêu");
                return 0;
            }
            // không bị ảnh hưởng bởi Super Kame, Super Atomic
            if (SkillUtil.isUseSkillNew(plAtt)) {
                return 0;
            }
            return super.injured(plAtt, 1, piercing, isMobAttack);
        }
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.DE_TU_WHIS, 1, this.location.x, this.location.y, pl.id);
            Service.getInstance().dropItemMap(pl.zone, itemMap);
        }
    }

    @Override
    public void idle() {
    }

//    @Override
//    public void leaveMap() {
//        super.leaveMap();
//        BossManager.gI().removeBoss(this);
//        BossManager.gI().getBossById(BossFactory.BILL).changeToAttack();
//    }

    @Override
    public void checkPlayerDie(Player pl) {
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Nếu muốn vượt qua ta thì nhà ngươi còn non và xanh lắm"};
    }

//    @Override
//    public void joinMap() {
//        super.joinMap();
//        if (this.zone != null) {
//            BossFactory.createBoss(BossFactory.BILL).zone = this.zone;
//        } else {
//            BossManager.gI().removeBoss(this);
//        }
//    }
}
