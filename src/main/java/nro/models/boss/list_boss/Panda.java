package nro.models.boss.list_boss;

import java.util.Random;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

public class Panda extends Boss {

    public Panda() {
        super(BossFactory.PANDA, BossData.PANDA);
    }

    protected Panda(byte id, BossData bossData) {
        super(id, bossData);
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
                Item skh = ItemService.gI().createNewItem((short) 1962);
                InventoryService.gI().addItemBag(plKill, skh, 9999);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được " + skh.template.name);
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
        if (plAtt == null) {
            return 0;
        }
        if (this.isDie()) {
            return 0;
        } else {
            if (SkillUtil.isUseSkillNew(plAtt)) {
                PlayerService.gI().hoiPhuc(this, 1, 10);
                return 0;
            }
            if (this.isDie()) {
                rewards(plAtt);
            }
            return super.injured(plAtt, 1, piercing, isMobAttack);
        }
    }

}
