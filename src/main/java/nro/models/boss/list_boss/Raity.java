/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.list_boss;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class Raity extends Boss {

    public Raity() {
        super(BossFactory.RAITY, BossData.RAITY);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (this.isDie()) {
                rewards(plAtt);
            }
            if (damage > 5_000_000) { 
                return 5_000_000;
            } else {
                return super.injured(plAtt, damage, piercing, isMobAttack);
            }
        }
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            if (Util.isTrue(20, 100)) {
                int[] itemDos = new int[]{
                    555, 557, 559,
                    562, 564, 566,
                    563, 565, 567};
                int randomDo = new Random().nextInt(itemDos.length);
                if (Util.isTrue(1, 5)) {
                    Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
                    return;
                }
                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
            } else {
                if (Util.isTrue(10, 70)) {
                    Item skh = ItemService.gI().createNewItem((short) 2013);
                    InventoryService.gI().addItemBag(plKill, skh, 9999);
                    InventoryService.gI().sendItemBags(plKill);
                    Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được " + skh.template.name);
                } else {
                    Item nr = ItemService.gI().createNewItem((short) Util.nextInt(2045, 2051));
                    InventoryService.gI().addItemBag(plKill, nr, 9999);
                    InventoryService.gI().sendItemBags(plKill);
                    Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được " + nr.template.name);
                }
            }

            int slDrop = 1;
            ItemMap itemMapDrop = null;
            // đồ thần linh là 2%
            if (Util.isTrue(2, 100)) {
                itemMapDrop = ArrietyDrop.DropItemReWardDoTL(plKill, 1, plKill.location.x, plKill.location.y);
                Service.getInstance().dropItemMap(this.zone, itemMapDrop);
            }
            // ngọc rồng 4 sao là 98%
            if (Util.isTrue(98, 100)) {
                int itemDragonID = ConstItem.NGOC_RONG_4_SAO;
                ItemMap itemDragon = new ItemMap(this.zone, itemDragonID, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, itemDragon);
            }
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
}
