package nro.models.boss.event.hellowin;

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
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class MaVuong extends Boss {

    public MaVuong(int bossId) {
        super(bossId, BossData.MAVUONG);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (plAtt == null) {
            return 0;
        }
        if (this.isDie()) {
            return 0;
        } else {
            // dame tối đa gây ra cho boss là 1
            int point = 1;
            // không bị ảnh hưởng bởi Super Kame, Super Atomic
            if (SkillUtil.isUseSkillNew(plAtt)) {
                return 0;
            }
            return super.injured(plAtt, point, piercing, isMobAttack);
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
        Service.getInstance().sendThongBao(pl, "Bạn nhận được x" + quantity + " " + item.template.name);
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
