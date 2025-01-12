package nro.models.boss.list_boss;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class ThanMeoKarinV2 extends Boss {

    public ThanMeoKarinV2() {
        super(BossFactory.THAN_MEO_KARIN_V2, BossData.THAN_MEO_KARIN_V2);
    }

    protected ThanMeoKarinV2(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            Random random = new Random();
            int slDrop = 0;
            ItemMap itemMap = null;
            // 5% Tỷ lệ nhận được 1 món thần linh ngẫu nhiên
            if (Util.isTrue(5, 100)) {
                slDrop = 1;
                itemMap = ArrietyDrop.DropItemReWardDoTL(plKill, slDrop, plKill.location.x, plKill.location.y);
                Service.getInstance().dropItemMap(this.zone, itemMap);
            }
            else if (Util.isTrue(10, 100)) {
                // 10% Tỷ lệ nhận được random 1->1000 thỏi vàng Vip
                slDrop = random.nextInt(1, 1000);
                itemMap = new ItemMap(this.zone, ConstItem.THOI_VANG_VIP, slDrop, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
            }
            else if (Util.isTrue(50, 100)) {
                // 50% Tỷ lệ nhận được 1 Hộp SKH VIP
                slDrop = 1;
                itemMap = new ItemMap(this.zone, ConstItem.SKH_VIP, slDrop, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
            }
            else if (Util.isTrue(35, 100)) {
                // 35% Tỷ lệ nhận được x1 Hộp Kẹo ma quái
                slDrop = 1;
                itemMap = new ItemMap(this.zone, ConstItem.HOP_KEO_MA_QUAI, slDrop, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
            }
            else {
                // xịt thì ra SKH VIP
                // 50% Tỷ lệ nhận được 1 Hộp SKH VIP
                slDrop = 1;
                itemMap = new ItemMap(this.zone, ConstItem.SKH_VIP, slDrop, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
            }
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Đừng hòng lấy đồ VIP của ta nha"};
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
            // dame tối đa gây ra cho boss là 1
            int point = 1;
            // không bị ảnh hưởng bởi Super Kame, Super Atomic
            if (SkillUtil.isUseSkillNew(plAtt)) {
                return 0;
            }
            return super.injured(plAtt, point, piercing, isMobAttack);
        }
    }
}
