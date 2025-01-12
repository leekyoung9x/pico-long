package nro.models.boss.event.tet;

import nro.consts.ConstItem;
import nro.consts.ConstOption;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.ItemOption;
import nro.models.item_reward.Reward;
import nro.models.player.Player;
import nro.utils.BossRewardUtil;
import nro.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MeoThanTai extends Boss {
    public MeoThanTai() {
        super(BossFactory.MEO_THAN_TAI, BossData.MEO_THAN_TAI);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        List<Reward> rewards = new ArrayList<>();

        rewards.add(new Reward(40, () -> BossRewardUtil.createItem(ConstItem.PET_MEO_DEN_DUOI_VANG, Util.nextInt(1, 3))));
        rewards.add(new Reward(20, () -> BossRewardUtil.createItem(ConstItem.BUA_PHAP_SU_HP, 10)));
        rewards.add(new Reward(20, () -> BossRewardUtil.createItem(ConstItem.BUA_PHAP_SU_KI, 10)));
        rewards.add(new Reward(20, () -> BossRewardUtil.createItem(ConstItem.BUA_PHAP_SU_SD, 10)));

        BossRewardUtil.DropBossItem(pl, rewards);
    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {

    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        damage = damage / 10;

        return super.injured(plAtt, damage, piercing, isMobAttack);
    }
}
