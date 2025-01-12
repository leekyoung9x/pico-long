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

public class KingCooler extends Boss {

    public KingCooler() {
        super(BossFactory.KING_COOLER, BossData.KING_COOLER);
    }

    protected KingCooler(byte id, BossData bossData) {
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
            int ngocBangID = random.nextInt(ConstItem.NGOC_RONG_BANG_3_SAO, ConstItem.NGOC_RONG_BANG_7_SAO);
            int slDrop = 1;
            ItemMap itemMap = new ItemMap(this.zone, ngocBangID, slDrop, this.location.x, this.location.y, plKill.id);
            Service.getInstance().dropItemMap(plKill.zone, itemMap);
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Ngươi đừng hòng lấy Ngọc Băng từ ta."};
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
            // dame gây ra chia 5
            long damageAfter = damage / 5;
            return super.injured(plAtt, damageAfter, piercing, isMobAttack);
        }
    }
}
