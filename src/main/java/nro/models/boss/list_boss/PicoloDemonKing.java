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

public class PicoloDemonKing extends Boss {

    public PicoloDemonKing() {
        super(BossFactory.PICOLO_DEMON_KING, BossData.PICOLO_DEMON_KING);
    }

    protected PicoloDemonKing(byte id, BossData bossData) {
        super(id, bossData);
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
//                    Item skh = ItemService.gI().createNewItem((short) 2013);
//                    InventoryService.gI().addItemBag(plKill, skh, 9999);
//                    InventoryService.gI().sendItemBags(plKill);
//                    Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được " + skh.template.name);
                } else {
                    plKill.inventory.addGem(Util.nextInt(1, 10_000));
                    Service.getInstance().sendMoney(plKill);
                }
            }
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Nếu muốn vượt qua ta thì nhà ngươi còn non và xanh lắm"};
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
        if (this.isDie()) {
            return 0;
        } else {
            if (this.isDie()) {
                rewards(plAtt);
            }
            return super.injured(plAtt, damage, piercing, isMobAttack);
        }
    }

}
