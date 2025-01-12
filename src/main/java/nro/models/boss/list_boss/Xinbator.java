package nro.models.boss.list_boss;

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
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class Xinbator extends Boss {

    public Xinbator() {
        super(BossFactory.XINBATOR, BossData.XINBATOR);
    }

    protected Xinbator(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            Item skh = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP);
            int quantity = Util.nextInt(1, 800);
            skh.quantity = quantity;
            InventoryService.gI().addItemBag(plKill, skh, 9999);
            InventoryService.gI().sendItemBags(plKill);
            Service.getInstance().sendThongBao(plKill, "Bạn đã nhận được x" + quantity + " " + skh.template.name);


            Random random = new Random();
            int tempId = 0;
            int slDrop = 1;
            // 50% thỏi vàng vip random từ 1 -> 1000 tvvip
            if (Util.isTrue(50, 100)) {
                tempId = ConstItem.THOI_VANG_VIP;
                // ekko rơi từ 1 -> 800 TVV
                slDrop = random.nextInt(1, 800);
                ItemMap itemTTVIP = new ItemMap(this.zone, tempId, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, itemTTVIP);
            }
            // đồ thần linh là 2%
            if (Util.isTrue(2, 100)) {
                ItemMap itemDrop = ArrietyDrop.DropItemReWardDoTL(plKill, 1, plKill.location.x, plKill.location.y);
                Service.getInstance().dropItemMap(this.zone, itemDrop);
            }
            // 48% ngọc xanh từ 1 -> 3k
            if (Util.isTrue(48, 100)) {
                tempId = ConstItem.NGOC;
                slDrop = random.nextInt(1, 3000);
                ItemMap itemNX = new ItemMap(this.zone, tempId, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, itemNX);
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
                damage = 0;
            }
            if (this.isDie()) {
                rewards(plAtt);
            }
            Service.getInstance().chat(plAtt, "Đụ má xinbato ơi, khó chịu quáaaaaaa");
            return super.injured(plAtt, 1, piercing, isMobAttack);
        }
    }

}
