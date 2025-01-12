/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nro.models.boss.boss_khi_gas_huy_diet;

import nro.consts.ConstItem;
import nro.consts.ConstPlayer;
import nro.consts.ConstRatio;
import nro.consts.MapName;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.SkillService;
import nro.utils.Util;

import nro.models.boss.Boss;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.phoban.KhiGas;
import nro.models.skill.Skill;
import nro.services.EffectSkillService;
import nro.services.PlayerService;
import nro.services.RewardService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.Log;

/**
 *
 * @author kitakeyos - Hoàng Hữu Dũng
 */
public class DrLychee extends BossKhiGasHuyDiet {

    private boolean isDie = false;

    public DrLychee(KhiGas khigas) {
        super(BossFactory.DR_LYCHEE, BossData.DR_LYCHEE, khigas);
    }


    @Override
    public void initTalk() {
        this.textTalkBefore = new String[]{"Ta đợi các ngươi mãi", "Bọn xayda các ngươi mau đền tội đi"};
        this.textTalkMidle = new String[]{"Đại bác báo thù...", "Heyyyyyyyy yaaaaa", "Hahaha", "ai da"};
        this.textTalkAfter = new String[]{"Các ngươi khá lắm", "Hatchiyack sẽ thay ta báo thù"};
    }

    @Override
    public void leaveMap() {
        System.out.print("dr die");
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

    @Override
    public void rewards(Player pl) {
        pl.clan.khiGas.initBoss2();
        int[] nro = {17, 18, 19, 20 , 539};
        ItemMap itemMap = new ItemMap(this.zone, nro[Util.nextInt(0, nro.length - 1)], 1,
                this.location.x, this.zone.map.yPhysicInTop(this.location.x, 100), -1);
        itemMap.options.add(new ItemOption(73, 0));
        Service.getInstance().dropItemMap(this.zone, itemMap);

        if(pl != null) {
            int slDrop = 1;
            ItemMap itemMapDrop = null;
            // đồ thần linh là 2%
            if (Util.isTrue(2, 100)) {
                itemMapDrop = ArrietyDrop.DropItemReWardDoTL(pl, 1, pl.location.x, pl.location.y);
                Service.getInstance().dropItemMap(this.zone, itemMapDrop);
            }
            // ngọc rồng 4 sao là 98%
            if (Util.isTrue(98, 100)) {
                int itemDragonID = ConstItem.NGOC_RONG_4_SAO;
                ItemMap itemDragon = new ItemMap(this.zone, itemDragonID, slDrop ,pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
                Service.getInstance().dropItemMap(this.zone, itemDragon);
            }
        }
    }
    @Override
    public void joinMap() {
        try {
            this.zone = khiGas.getMapById(148);
            ChangeMapService.gI().changeMap(this, this.zone, 513, 480);
//            System.out.println("Đã join map");
        } catch (Exception e) {
        }
    }
}
