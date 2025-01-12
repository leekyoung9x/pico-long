/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.baconsoi;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.boss.FutureBoss;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.server.ServerNotify;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

/**
 *
 * @author Administrator
 */
public class Lavender extends FutureBoss {

    public Lavender() {
        super(BossFactory.LAVENDER, BossData.LAVENDER);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        int[] itemDos = new int[]{
            555, 557, 559,
            562, 564, 566,
            563, 565, 567};
        int randomDo = new Random().nextInt(itemDos.length);
        if (Util.isTrue(5, 100)) {
            if (Util.isTrue(1, 5)) {
                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
                return;
            }
            Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
        } else {
            if (plKill != null) {
                this.dropItemReward(16, (int) plKill.id);
                generalRewards(plKill);
            }
        }

        int slDrop = 1;
        ItemMap itemMap = null;
        // đồ thần linh là 2%
        if (Util.isTrue(2, 100)) {
            itemMap = ArrietyDrop.DropItemReWardDoTL(plKill, 1, plKill.location.x, plKill.location.y);
            Service.getInstance().dropItemMap(this.zone, itemMap);
        }
        // ngọc rồng 4 sao là 98%
        if (Util.isTrue(98, 100)) {
            int itemDragonID = ConstItem.NGOC_RONG_4_SAO;
            ItemMap itemDragon = new ItemMap(this.zone, itemDragonID, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
            Service.getInstance().dropItemMap(this.zone, itemDragon);
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
        this.textTalkMidle = new String[]{"Xem bản lĩnh của ngươi như nào đã", "Các ngươi tới số mới gặp phải ta"};

    }

    @Override
    public void leaveMap() {
        BossFactory.createBoss(BossFactory.BASIL).setJustRest();
        super.leaveMap();
        BossManager.gI().removeBoss(this);
    }

    @Override
    public void joinMap() {
        if (this.zone != null) {
            ChangeMapService.gI().changeMap(this, zone, this.location.x, this.location.y);
            ServerNotify.gI().notify("Boss " + this.name + " vừa xuất hiện tại " + this.zone.map.mapName);
        }
    }
}
