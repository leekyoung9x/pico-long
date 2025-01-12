package nro.models.boss.NgucTu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nro.models.boss.*;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.server.Client;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.Util;

/**
 * @Build by Arriety
 */
public class SuperCumber extends Boss {
//public class SuperCumber extends FutureBoss {

    public SuperCumber() {
        super(BossFactory.CUMBER2, BossData.CUMBER2);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (plAtt == null) {
            return 0;
        }
        if (plAtt.setClothes.godClothes) {
            if (this.isDie()) {
                return 0;
            } else {
                if (this.isDie()) {
                    rewards(plAtt);
                }
                long point = damage / 2;
                return super.injured(plAtt, point, piercing, isMobAttack);
            }
        } else {
            Service.getInstance().chat(plAtt, "Chà chà phải có set thần linh mới tiêu diệt được tên này");
            return 0;
        }
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            if (Util.isTrue(5, 100)) {
//                int x = this.location.x + Util.nextInt(-30, 30);
//                if (x < 30) {
//                    x = 30;
//                } else if (x > zone.map.mapWidth - 30) {
//                    x = zone.map.mapWidth - 30;
//                }
//                int y = this.location.y;
//                if (y > 24) {
//                    y = this.zone.map.yPhysicInTop(x, y - 24);
//                }
//                ItemMap item = ArrietyDrop.DropItemSetHDKichHoat(plKill, plKill.gender, 1, x, y);
//                Service.getInstance().dropItemMap(plKill.zone, item);
            } else {
                int rubyAdd = Util.nextInt(1, 5000);
                int oldRuby = plKill.inventory.ruby;
                plKill.inventory.addRuby(rubyAdd);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(plKill.id, oldRuby, plKill.inventory.ruby, "SuperCumber-rewards");
            }

        }
    }

//    @Override
//    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
//        if (this.isDie()) {
//            return 0;
//        } else {
//            if (plAtt != null) {
//                if (Util.isTrue(25, 70)) {
//                    damage = 1;
//                    Service.getInstance().chat(this, "Xí hụt..");
//                }
//            }
//            int dame = super.injured(plAtt, damage, piercing, isMobAttack);
//            if (this.isDie()) {
//                rewards(plAtt);
//            }
//            return dame;
//        }
//    }
//    @Override
//    public void rewards(Player pl) {
//        List<Map.Entry<Long, Integer>> entryList = new ArrayList<>(Util.sortHashMapByValue(topDame).entrySet());
//        if (!entryList.isEmpty()) {
//            Player playerTop = Client.gI().getPlayer(entryList.get(0).getKey());
//            if (playerTop != null) {
////                Item da = ItemService.gI().createNewItem((short) 2040);
////                da.quantity = 2;
////                playerTop.inventory.ruby += 5000;
////                InventoryService.gI().addItemBag(playerTop, da, 0);
////                InventoryService.gI().sendItemBags(playerTop);
////                Service.getInstance().sendMoney(pl);
////                Service.getInstance().sendThongBao(playerTop, "Bạn nhận được " + da.template.name);
//                playerTop.inventory.ruby += 2000;
//                Service.getInstance().sendMoney(playerTop);
//                Service.getInstance().sendThongBao(pl, "Bạn đã nhận được 2000 ruby");
//
//            }
//            if (entryList.size() > 1) {
//                Player playerTop2 = Client.gI().getPlayer(entryList.get(1).getKey());
//                if (playerTop2 != null) {
////                    Item da = ItemService.gI().createNewItem((short) 2040);
////                    Item bdkb = ItemService.gI().createNewItem((short) 611);
////                    InventoryService.gI().addItemBag(playerTop2, da, 0);
////                    InventoryService.gI().addItemBag(playerTop2, bdkb, 0);
////                    InventoryService.gI().sendItemBags(playerTop2);
//                    playerTop2.inventory.ruby += 1000;
//                    Service.getInstance().sendMoney(playerTop2);
//                    Service.getInstance().sendThongBao(pl, "Bạn đã nhận được 1000 ruby");
//
//                }
//            }
//            if (entryList.size() > 2) {
//                Player playerTop3 = Client.gI().getPlayer(entryList.get(2).getKey());
//                if (playerTop3 != null) {
//                    playerTop3.inventory.ruby += 200;
//                    Service.getInstance().sendMoney(playerTop3);
//                    Service.getInstance().sendThongBao(pl, "Bạn đã nhận được 200 ruby");
////                    Item mewmew = ItemService.gI().createNewItem((short) 2040);
////                    InventoryService.gI().addItemBag(playerTop3, mewmew, 0);
////                    InventoryService.gI().sendItemBags(playerTop3);
////                    Service.getInstance().sendThongBao(playerTop3, "Bạn nhận được " + mewmew.template.name);
//                }
//            }
//        }
//        entryList.clear();
//    }
    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkAfter = new String[]{"Ta đã giấu hết ngọc rồng rồi, các ngươi tìm vô ích hahaha"};
    }

//    @Override
//    public void leaveMap() {
//        this.topDame.clear();
//        Service.getInstance().sendTextTime(this, (byte) 10, "", (short) 0);
//        Service.getInstance().sendTextTime(this, (byte) 11, "", (short) 0);
//        Service.getInstance().sendTextTime(this, (byte) 12, "", (short) 0);
//        BossManager.gI().getBossById(BossFactory.CUMBER).setJustRest();
//        super.leaveMap();
//        BossManager.gI().removeBoss(this);
//    }
}
