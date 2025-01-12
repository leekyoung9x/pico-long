package nro.models.boss.list_boss;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.utils.Util;

public class Toppo extends Boss {

    public Toppo() {
        super(BossFactory.TOPPO, BossData.TOPPO);
    }

    protected Toppo(byte id, BossData bossData) {
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
                Item it = ItemService.gI().createNewItem((short) (plKill.gender + 1054));
                it.itemOptions.add(new ItemOption(0, Util.nextInt(11000, 15500))); 
                it.itemOptions.add(new ItemOption(21, 105));
                it.itemOptions.add(new ItemOption(30, 1));
                InventoryService.gI().addItemBag(plKill, it, 999);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill,
                        "Chúc mừng bạn vừa nhận được gang thiên sứ");
            } else {
                this.dropItemReward(1991, (int) plKill.id);
            }

            // 100% nhận được random 1 trong 3 item cuồng nộ, bổ huyết, bổ khí
            int[] itemIDs = {ConstItem.CUONG_NO, ConstItem.BO_HUYET, ConstItem.BO_KHI};
            // Khởi tạo đối tượng Random
            Random random = new Random();
            int randomIndex = random.nextInt(itemIDs.length);
            // Lấy số ngẫu nhiên từ mảng
            int randomItemID = itemIDs[randomIndex];
            Item it = ItemService.gI().createNewItem((short)randomItemID);
            InventoryService.gI().addItemBag(plKill, it, 999);
            InventoryService.gI().sendItemBags(plKill);
            Service.getInstance().sendThongBao(plKill,
                    "Chúc mừng bạn vừa nhận được 1 " + it.getName());
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
            if (this.isDie()) {
                rewards(plAtt);
            }
            // ekko dame gây ra / 3
            long point = damage / 3;
            return super.injured(plAtt, point, piercing, isMobAttack);
        }
    }
}
