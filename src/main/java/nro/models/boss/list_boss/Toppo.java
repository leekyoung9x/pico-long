package nro.models.boss.list_boss;

import java.util.Random;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
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
//            if (Util.isTrue(5, 100)) {
//                Item it = ItemService.gI().createNewItem((short) (plKill.gender + 1054));
//                it.itemOptions.add(new ItemOption(0, Util.nextInt(11000, 15500)));
//                it.itemOptions.add(new ItemOption(21, 105));
//                it.itemOptions.add(new ItemOption(30, 1));
//                InventoryService.gI().addItemBag(plKill, it, 999);
//                InventoryService.gI().sendItemBags(plKill);
//                Service.getInstance().sendThongBao(plKill,
//                        "Chúc mừng bạn vừa nhận được gang thiên sứ");
//            } else {
//                this.dropItemReward(1991, (int) plKill.id);
//            }

            // 100% nhận được random 1 trong 3 item cuồng nộ, bổ huyết, bổ khí
//            int[] itemIDs = {ConstItem.CUONG_NO, ConstItem.BO_HUYET, ConstItem.BO_KHI};
//            // Khởi tạo đối tượng Random
//            Random random = new Random();
//            int randomIndex = random.nextInt(itemIDs.length);
//            // Lấy số ngẫu nhiên từ mảng
//            int randomItemID = itemIDs[randomIndex];
//            Item it = ItemService.gI().createNewItem((short)randomItemID);
//            InventoryService.gI().addItemBag(plKill, it, 999);
//            InventoryService.gI().sendItemBags(plKill);
//            Service.getInstance().sendThongBao(plKill,
//                    "Chúc mừng bạn vừa nhận được 1 " + it.getName());
            int slDrop = 1;
            ItemMap itemMap = null;
            int itemID = -1;
            // 20% rơi 100 -> 1k TVV
            if (Util.isTrue(20, 100)) {
                Item tvvip = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, Util.nextInt(100, 1_000));
                InventoryService.gI().addItemBag(plKill, tvvip, 0); // Không đặt hạn sử dụng
                Service.getInstance().sendThongBao(plKill, "Bạn nhận được" + tvvip.quantity + " thỏi vàng vip ");
            }
            // item cấp 2
            else if (Util.isTrue(10, 100)) {
                // rơi ra item cấp 2
                int cnRandom = ConstItem.BO_KHI_2;
                if(Util.isTrue(35, 100)) {
                    cnRandom = ConstItem.GIAP_XEN_BO_HUNG_2;
                } else if(Util.isTrue(15, 100)) {
                    cnRandom = ConstItem.BO_HUYET_2;
                } else if(Util.isTrue(15, 100)) {
                    cnRandom = ConstItem.CUONG_NO_2;
                }
                ItemMap itemMapCN = new ItemMap(this.zone, cnRandom, 1, plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, itemMapCN);
            }
            // 50% ra hộp quà hồng ngọc
            else {
                itemID = ConstItem.HOP_QUA_HONG_NGOC;
                ItemMap itemDragon = new ItemMap(this.zone, itemID, slDrop ,plKill.location.x, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                Service.getInstance().dropItemMap(this.zone, itemDragon);
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
            if (this.isDie()) {
                rewards(plAtt);
            }
            // ekko dame gây ra / 3
            long point = damage / 3;
            return super.injured(plAtt, point, piercing, isMobAttack);
        }
    }
}
