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
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class LanCon extends Boss {

    public LanCon() {
        super(BossFactory.LAN_CON, BossData.LAN_CON);
    }

    protected LanCon(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            // rơi 1 capsule tết
            ItemMap itemMap = new ItemMap(this.zone, ConstItem.CAPSULE_TET_2024, 1, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, itemMap);
            // rơi 1 -> 5 viên đá ngũ sắc
            int slDrop = Util.nextInt(1, 5);
            ItemMap dns = new ItemMap(this.zone, ConstItem.DA_NGU_SAC, slDrop, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, dns);
            // rơi 1 -> 5 viên đá ngũ sắc vip
            int slDropVip = Util.nextInt(1, 5);
            ItemMap dnsVip = new ItemMap(this.zone, ConstItem.DA_NGU_SAC_VIP, slDropVip, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, dnsVip);
            // 50% rơi 1 viên đá bảo vệ
            if(Util.isTrue(1, 2)) {
                int slDropDBV = 1;
                ItemMap itemDBV = new ItemMap(this.zone, ConstItem.DA_BAO_VE, slDropDBV, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemDBV);
            }
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Capsule Tết ở trong tay ta, ngon nhào vô kiếm ăn !"};
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
        // player có sức mạnh > 80 tỷ mới gây dame cho boss
        if (plAtt.nPoint != null && plAtt.nPoint.power >= 80_000_000_000L) {
            if (this.isDie()) {
                return 0;
            } else {
                // dame tối đa gây ra cho boss là 1
                int point = 1;
                Item item = plAtt.inventory.itemsBody.get(14);
                // đang mặc trên người danh hiệu này gây 2 dame
                if (item != null && item.template != null && item.template.id == ConstItem.DANH_HIEU_MEMORI) {
                    point = 2;
                }
                // không bị ảnh hưởng bởi Super Kame, Super Atomic
                if (SkillUtil.isUseSkillNew(plAtt)) {
                    return 0;
                }
                return super.injured(plAtt, point, piercing, isMobAttack);
            }
        } else {
            return 0;
        }
    }
}
