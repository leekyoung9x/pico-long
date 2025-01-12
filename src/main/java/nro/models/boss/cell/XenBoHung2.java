package nro.models.boss.cell;

import java.util.Calendar;
import java.util.Random;
import nro.consts.ConstRatio;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.FutureBoss;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.Service;
import nro.services.SkillService;
import nro.services.TaskService;
import nro.utils.Log;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 *
 * Arriety
 *
 */
public class XenBoHung2 extends FutureBoss {

    public XenBoHung2() {
        super(BossFactory.XEN_BO_HUNG_2, BossData.XEN_BO_HUNG_2);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        long dame = 0;
        if (this.isDie()) {
            return dame;
        } else {
            if (Util.isTrue(1, 5) && plAtt != null) {
                switch (plAtt.playerSkill.skillSelect.template.id) {
                    case Skill.TU_SAT:
                    case Skill.QUA_CAU_KENH_KHI:
                    case Skill.MAKANKOSAPPO:
                        break;
                    default:
                        return 0;
                }
            }
            final Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(11);
            if (hour >= 17 && hour < 20) {
                String bossName = this.name;
                int requiredTaskIndex = -1;
                switch (bossName) {
                    case "Xên bọ hung 1":
                        requiredTaskIndex = 1;
                        break;
                    case "Xên bọ hung 2":
                        requiredTaskIndex = 2;
                        break;
                    case "Xên hoàn thiện":
                        requiredTaskIndex = 3;
                        break;
                }
                long initdame = damage / 30;
                if (plAtt != null && plAtt.playerTask.taskMain.id != 24) {
                    damage = initdame;
                    Service.getInstance().sendThongBao(plAtt, "Bây giờ là giờ nhiệm vụ, không phải nhiệm vụ hiện tại của bạn, boss miễn nhiễm sát thương");
                } else if (plAtt != null && plAtt.playerTask.taskMain.id == 24) {
                    if (plAtt.playerTask.taskMain.index != requiredTaskIndex) {
                        damage = initdame;
                        Service.getInstance().sendThongBao(plAtt, "Bây giờ là giờ nhiệm vụ, không phải nhiệm vụ hiện tại của bạn, boss miễn nhiễm sát thương");
                    }
                }
            }
            dame = super.injured(plAtt, damage, piercing, isMobAttack);
            if (this.isDie()) {
                rewards(plAtt);
                notifyPlayeKill(plAtt);
                die();
            }
            return dame;
        }
    }

    @Override
    public void rewards(Player plKill) {
        int[] itemDos = new int[]{
            555, 557, 559,
            562, 564, 566,
            563, 565, 567};
        int randomDo = new Random().nextInt(itemDos.length);
        if (Util.isTrue(10, 100)) {
            if (Util.isTrue(1, 5)) {
                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 561, 1, this.location.x, this.location.y, plKill.id));
                return;
            }
            Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, itemDos[randomDo], 1, this.location.x, this.location.y, plKill.id));
        } else {
            if (plKill != null) {
                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 1992, 50, this.location.x, this.location.y, plKill.id));
                Service.getInstance().dropItemMap(this.zone, Util.ratiItem(zone, 1993, 50, this.location.x, this.location.y, plKill.id));
                generalRewards(plKill);
            }
        }
        TaskService.gI().checkDoneTaskKillBoss(plKill, this);
    }

    @Override
    public void attack() {
        try {
            Player pl = getPlayerAttack();
            if (pl != null) {
                this.playerSkill.skillSelect = this.getSkillAttack();
                if (Util.getDistance(this, pl) <= this.getRangeCanAttackWithSkillSelect()) {
                    if (Util.isTrue(15, ConstRatio.PER100) && SkillUtil.isUseSkillChuong(this)) {
                        goToXY(pl.location.x + (Util.getOne(-1, 1) * Util.nextInt(20, 80)),
                                Util.nextInt(10) % 2 == 0 ? pl.location.y : pl.location.y - Util.nextInt(0, 50), false);
                    }
                    SkillService.gI().useSkill(this, pl, null);
                    checkPlayerDie(pl);
                } else {
                    goToPlayer(pl, false);
                }
            }
        } catch (Exception ex) {
            Log.error(XenBoHung2.class, ex);
        }
    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        this.textTalkBefore = new String[]{};
        this.textTalkMidle = new String[]{"Tất cả nhào vô", "Mình ta cũng đủ để hủy diệt các ngươi"};
        this.textTalkAfter = new String[]{};
    }

    @Override
    public void leaveMap() {
        Boss xht = BossFactory.createBoss(BossFactory.XEN_BO_HUNG_HOAN_THIEN);
        xht.zone = this.zone;
        this.setJustRestToFuture();
        super.leaveMap();
    }

}
