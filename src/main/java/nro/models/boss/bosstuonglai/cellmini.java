package nro.models.boss.bosstuonglai;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.Util;

public class cellmini extends Boss{
    public cellmini() {
        super(BossFactory.CELLMINI, BossData.cellmini);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        int tempId = -1;
        int sl = 1;
        if (Util.isTrue(1, 10)) {
            tempId = 2089;
        } else if (Util.isTrue(3, 10)) {
            tempId = 2088;
        } else if (Util.isTrue(7, 10)) {
            tempId = ConstItem.THOI_VANG_VIP;
        }

        if (tempId != -1) {
            if (tempId == ConstItem.THOI_VANG_VIP) {
                // ekko rơi từ 1 -> 800 TVV
                sl = Util.nextInt(1, 800);
            } else if (tempId == 2088) {
                sl = Util.nextInt(1, 10); // Random số lượng từ 1 đến 10 cho tempId 2009
            }
            ItemMap itemMap = new ItemMap(this.zone, tempId, sl,pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
            if (tempId == 2089) {
                // ekko nerf lại còn 50% chỉ số
                itemMap.options.add(new ItemOption(50, 50));
                itemMap.options.add(new ItemOption(77, 50));
                itemMap.options.add(new ItemOption(103, 50));
                itemMap.options.add(new ItemOption(93, 1));
            }                RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        }

        int slDrop = 1;
        ItemMap itemMap = null;
        // đồ thần linh là 2%
        if (Util.isTrue(2, 100)) {
            itemMap = ArrietyDrop.DropItemReWardDoTL(pl, 1, pl.location.x, pl.location.y);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        }
        // ngọc rồng 4 sao là 98%
        if (Util.isTrue(98, 100)) {
            tempId = ConstItem.NGOC_RONG_4_SAO;
            ItemMap itemDragon = new ItemMap(this.zone, tempId, slDrop ,pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
            Service.getInstance().dropItemMap(this.zone, itemDragon);
        }
    }



    @Override
    public void idle() {

    }
    @Override
    public void joinMap() {
        super.joinMap();
//        for (int i = 0; i < 4; i++) {
//            Zamasu2 zamasu = new Zamasu2((int) (BossFactory.ZAMAS2 - 1 - i ), zone);
//        }
    }
    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkMidle = new String[]{"Ta chính là đệ nhất vũ trụ cao thủ"};
        textTalkAfter = new String[]{"Ác quỷ biến hình aaa..."};
    }

    @Override

    public void leaveMap() {
        super.leaveMap();
        this.changeToIdle();
    }
}