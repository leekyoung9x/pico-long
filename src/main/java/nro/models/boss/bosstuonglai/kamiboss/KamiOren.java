package nro.models.boss.bosstuonglai.kamiboss;

import nro.consts.ConstItem;
import nro.consts.ConstOption;
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

import java.util.ArrayList;
import java.util.List;

public class KamiOren extends Boss {

    public KamiOren(int bossId) {
        super(bossId, BossData.KAMIOREN);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            damage = damage / 3;

//            if (plAtt != null && plAtt.getSession() != null && plAtt.isAdmin()) {
//                damage = this.nPoint.hpMax;
//            }

            long dame = super.injured(plAtt, damage, piercing, isMobAttack);
            return dame;
        }
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        short itemId = 0;
        short quantity = 0;
        List<ItemOption> options = new ArrayList<>();

//        if (Util.isTrue(1, 2)) {
//            quantity = (short) Util.nextInt(5);
//            itemId = (short) ConstItem.CUC_XUONG;
//        } else if (Util.isTrue(3, 5)) {
//            int nrx = Util.nextInt(ConstItem.NGOC_RONG_BANG_1_SAO, ConstItem.NGOC_RONG_BANG_7_SAO);
//            itemId = (short) nrx;
//            quantity = 1;
//        } else {
//            if (Util.isTrue(3, 5)) {
//                itemId = (short) ConstItem.DA_NGU_SAC;
//                quantity = (short) Util.nextInt(5);
//            } else {
//                itemId = (short) ConstItem.DANH_HIEU_BAT_BAI;
//                quantity = 1;
//
//                options.add(new ItemOption(ConstOption.SUC_DANH_CONG_PHAN_TRAM, (short) Util.nextInt(1, 30)));
//                options.add(new ItemOption(ConstOption.HP_CONG_PHAN_TRAM, (short) Util.nextInt(1, 30)));
//                options.add(new ItemOption(ConstOption.KI_CONG_PHAN_TRAM, (short) Util.nextInt(1, 30)));
//            }
//        }
        if(Util.isTrue(10, 100)) {
            itemId = (short) ConstItem.DANH_HIEU_BAT_BAI;
            quantity = 1;
            options.add(new ItemOption(ConstOption.SUC_DANH_CONG_PHAN_TRAM, (short) Util.nextInt(5, 15)));
            options.add(new ItemOption(ConstOption.HP_CONG_PHAN_TRAM, (short) Util.nextInt(5, 15)));
            options.add(new ItemOption(ConstOption.KI_CONG_PHAN_TRAM, (short) Util.nextInt(5, 15)));
        } else {
            if(Util.isTrue(50, 100)) {
                itemId = (short) ConstItem.NGOC_RONG_6_SAO;
            } else {
                itemId = (short) ConstItem.NGOC_RONG_7_SAO;
            }
            quantity = 1;
        }

        AddItemReward(pl, itemId, quantity, options);
    }

    private static void AddItemReward(Player pl, short itemId, short quantity, List<ItemOption> options) {
        Item item = ItemService.gI().createNewItem(itemId);
        item.quantity = quantity;
        item.itemOptions = options;
        InventoryService.gI().addItemBag(pl, item, 0);
        InventoryService.gI().sendItemBags(pl);
        Service.getInstance().sendThongBao(pl, "Bạn nhận được x" + item.quantity + " " + item.template.name);
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
