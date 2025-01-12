package nro.models.boss.list_boss;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.*;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.services.SkillService;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 *
 * @author Arriety
 *
 */
public class ThoDaiKa extends FutureBoss {

    public ThoDaiKa() {
        super(BossFactory.THO_DAI_KA, BossData.THODAIKA);
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
                damage = 0;
            }
            if (this.isDie()) {
                rewards(plAtt);
            }
            return super.injured(plAtt, 1, piercing, isMobAttack);
        }
    }
//
//    @Override
//    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
//        if (!this.isDie()) {
//            this.playerSkill.skillSelect = this.playerSkill.skills.get(Util.nextInt(0, this.playerSkill.skills.size() - 1));
//            SkillService.gI().useSkill(this, plAtt, null);
//        } else {
//            return 0;
//        }
//        return super.injured(plAtt, 5000, piercing, isMobAttack);
//    }

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
                Item coint = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP);
                // ekko rơi từ 1 -> 800 TVV
                int quantity = Util.nextInt(1, 800);
                coint.quantity = quantity;
                InventoryService.gI().addItemBag(plKill, coint, 1_500_000);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill, "Bạn nhận được  x" + quantity + " " + coint.template.name);
            }
        }
    }

//    @Override
//    public void rewards(Player pl) {
//        int slcarot = Util.nextInt(10, 20);
//        for (int i = 0; i < slcarot; i++) {
//            ItemMap carot = new ItemMap(zone, 462, 1,
//                    10 * i + this.location.x, zone.map.yPhysicInTop(this.location.x, 0), -1);
//            carot.options.add(new ItemOption(30, 0));
//            carot.options.add(new ItemOption(93, 30));
//            Service.getInstance().dropItemMap(this.zone, carot);
//        }
//    }
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
