package nro.models.boss.cold;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.player.Player;
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.Util;

import java.util.Random;

public class CoolerGold extends Boss {
    private static final Random RANDOM = new Random();

    public CoolerGold(Zone zone) {
        super(BossFactory.COOLER_VANG, BossData.COOLER_VANG);
        this.zone = zone;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            damage = damage / 5;

//            if (plAtt != null && plAtt.getSession() != null && plAtt.isAdmin()) {
//                damage = this.nPoint.hpMax;
//            }

            long dame = super.injured(plAtt, damage, piercing, isMobAttack);
            return dame;
        }
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        if (Util.isTrue(1, 2)) {
            int sl = 1;
            int[] tempIds = {2119, 2118, 2120}; // Các ID item

            // Chọn một chỉ mục ngẫu nhiên từ mảng
            int tempId = tempIds[RANDOM.nextInt(tempIds.length)];

            ItemMap itemMap = new ItemMap(this.zone, tempId, sl, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);

            // Thêm các tùy chọn cho itemMap dựa trên tempId
            if (tempId == 2118) {
                itemMap.options.add(new ItemOption(77, 8));
            }
            if (tempId == 2119) {
                itemMap.options.add(new ItemOption(103, 8));
            }
            if (tempId == 2120) {
                itemMap.options.add(new ItemOption(50, 4));
            }

            // Khởi tạo và thả item map
            RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        } else {
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.RUONG_NGOC_BANG, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);

            // Khởi tạo và thả item map
            RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        }
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

    public void leaveMap() {
        try {
            super.leaveMap();
            BossManager.BOSSES_IN_GAME.remove(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}