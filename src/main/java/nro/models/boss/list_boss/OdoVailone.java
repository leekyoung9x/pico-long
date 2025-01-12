package nro.models.boss.list_boss;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class OdoVailone extends Boss {

    public OdoVailone() {
        super(BossFactory.ODO, BossData.ODO_VAI);
    }

    protected OdoVailone(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            if (Util.isTrue(50, 100)) {
                plKill.inventory.addGem(Util.nextInt(1, 5000));
                Service.getInstance().sendMoney(plKill);
            } else {
                int rubyAdd = Util.nextInt(1, 5000);
                int oldRuby = plKill.inventory.ruby;
                plKill.inventory.addRuby(rubyAdd);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(plKill.id, oldRuby, plKill.inventory.ruby, "OdoVailone-rewards");
                Service.getInstance().sendMoney(plKill);
            }

            Random random = new Random();
            int tempId = 0;
            int slDrop = 1;
            // 50% thỏi vàng vip random từ 1 -> 1000 tvvip
            if (Util.isTrue(50, 100)) {
                tempId = ConstItem.THOI_VANG_VIP;
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
            return super.injured(plAtt, 1, piercing, isMobAttack);
        }
    }

}
