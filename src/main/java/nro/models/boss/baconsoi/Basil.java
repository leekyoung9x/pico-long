/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.baconsoi;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.FutureBoss;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class Basil extends FutureBoss {

    public Basil() {
        super(BossFactory.BASIL, BossData.BASIL);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

  @Override
public void rewards(Player plKill) {
//    int[] itemDos = new int[]{
//        555, 557, 559,
//        562, 564, 566,
//        563, 565, 567};
//    int randomDo = new Random().nextInt(itemDos.length);
//    int selectedItem = itemDos[randomDo];
//
//    if (Util.isTrue(30, 100)) {
//        if (Util.isTrue(1, 5)) {
//            Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
//            return;
//        }
//
//        // Tạo item và thêm option 30
//        ItemMap itemMap = Util.ratiItem(zone, selectedItem, 1, this.location.x, this.location.y, plKill.id);
//        itemMap.options.add(new ItemOption(30, 0)); // Thêm option 30 cho tất cả các item
//        Service.getInstance().dropItemMap(this.zone, itemMap);
//    } else {
//        if (plKill != null) {
//            this.dropItemReward(1991, (int) plKill.id);
////                generalRewards(plKill);
//        }
//    }

    int slDrop = 1;
    ItemMap itemMap = null;
    int itemID = -1;
    // đồ thần linh là 10%
    if (Util.isTrue(10, 100)) {
      itemMap = ArrietyDrop.DropItemReWardDoTL(plKill, 1, plKill.location.x, plKill.location.y);
      Service.getInstance().dropItemMap(this.zone, itemMap);
    }
    // ngọc rồng 4 sao là 40%
    else if (Util.isTrue(40, 100)) {
        itemID = Util.nextInt(ConstItem.NGOC_RONG_4_SAO, ConstItem.NGOC_RONG_7_SAO);
        ItemMap itemDragon = new ItemMap(this.zone, itemID, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
        Service.getInstance().dropItemMap(this.zone, itemDragon);
    }
      // 50% ra hộp quà hồng ngọc
      else {
          itemID = ConstItem.HOP_QUA_HONG_NGOC;
          ItemMap itemDragon = new ItemMap(this.zone, itemID, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
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
        this.textTalkAfter = new String[]{"Ác quỷ biến hình, hêy aaa......."};
    }

    @Override
    public void leaveMap() {
        Boss fd2 = BossFactory.createBoss(BossFactory.BERGAMO);
        fd2.zone = this.zone;
        fd2.location.x = this.location.x;
        fd2.location.y = this.location.y;
        super.leaveMap();
        this.setJustRestToFuture();
    }

}
