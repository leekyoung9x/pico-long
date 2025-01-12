package nro.models.boss.list_boss;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class MaXuong extends Boss {

    public MaXuong() {
        super(BossFactory.MAXUONG, BossData.MA_XUONG);
    }

    protected MaXuong(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            // rơi 1 cục xương
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.CUC_XUONG, 1, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, itemMap);
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Đừng hòng lấy cục xương của ta nha"};
    }

    @Override
    public void idle() {
        if (this.countIdle >= this.maxIdle) {
            this.maxIdle = Util.nextInt(0, 3);
            this.countIdle = 0;
            this.changeAttack();
        } else {
            this.countIdle++;
        }
    }

    @Override
    public void checkPlayerDie(Player pl) {
        if (pl.isDie()) {
            Service.getInstance().chat(this, "Chừa nha " + plAttack.name + " động vào ta chỉ có chết.");
            this.plAttack = null;
        }
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (plAtt == null) {
            return 0;
        }
        if (this.isDie()) {
            return 0;
        } else {
            // dame tối đa gây ra cho boss là 1
            int point = 1;
            // sử dụng kẹo ma quái thì - 3 lên boss Ma Xương
            if(plAtt.itemTime != null && plAtt.itemTime.rateDameMaXuong) {
                point = 3;
            }
            // không bị ảnh hưởng bởi Super Kame, Super Atomic
            if (SkillUtil.isUseSkillNew(plAtt)) {
                return 0;
            }
            return super.injured(plAtt, point, piercing, isMobAttack);
        }
    }
}
