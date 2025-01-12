/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.list_boss;

import java.util.Random;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class BuiBui extends Boss {

    public BuiBui() {
        super(BossFactory.BUIBUI, BossData.BUIBUI);
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
            if (Util.isTrue(5, 100)) {
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
                Item skh = ItemService.gI().createNewItem((short) 1991);
                InventoryService.gI().addItemBag(plKill, skh, 9999);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được " + skh.template.name);
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
