package nro.models.boss.event.noel.tuanloc;

import nro.consts.ConstItem;
import nro.consts.ConstOption;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class TuanLoc extends Boss {

    public TuanLoc(int bossId) {
        super(bossId, BossData.TUANLOC);
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
        if (Util.isTrue(30, 100)) {
            int slDrop = 1;
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.DA_NGU_SAC, slDrop, this.location.x, this.location.y, pl.id);
            Service.getInstance().dropItemMap(pl.zone, itemMap);
        }
        // 30% rớt item bảo vệ
        else if(Util.isTrue(30, 100)) {
            int slDrop = 1;
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.DA_BAO_VE, slDrop, this.location.x, this.location.y, pl.id);
            Service.getInstance().dropItemMap(pl.zone, itemMap);
        } else {
            // 40% rớt DNS VIP
            int slDrop = 1;
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.DA_NGU_SAC_VIP, slDrop, this.location.x, this.location.y, pl.id);
            Service.getInstance().dropItemMap(pl.zone, itemMap);
        }
        // cộng điểm sự kiện
//        pl.diem_skien++;
//        Service.getInstance().sendThongBao(pl, "Bạn đang sở hữu " + pl.diem_skien + " điểm sự kiện");
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
