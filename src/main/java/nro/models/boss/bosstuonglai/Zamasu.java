package nro.models.boss.bosstuonglai;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.Service;
import nro.utils.Util;

public class Zamasu extends Boss{
    public Zamasu() {
        super(BossFactory.ZAMAS, BossData.ZAMAS);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            ItemMap itemMap = null;
            int x = this.location.x;
            int y = this.zone.map.yPhysicInTop(x, this.location.y - 24);
            if (Util.isTrue(90, 100)) {
                int uchihahahaha = Util.nextInt(1,11);
                for (int i = 0; i < uchihahahaha; i++){
                    itemMap = new ItemMap(this.zone,1962 , 1, x + 5 + uchihahahaha, y, pl.id);
                }
            } else {
                itemMap = new ItemMap(this.zone, ConstItem.THOI_VANG_VIP, Util.nextInt(1,800), x, y, pl.id);
            }
            if (itemMap != null) {

                Service.getInstance().dropItemMap(zone, itemMap);
            }
            pl.diemboss += 1;
            notifyPlayeKill(pl);
        }
    }

    @Override
    public void idle() {

    }
    @Override
    public void joinMap() {
        super.joinMap();
//        for (int i = 0; i < 4; i++) {
//            Zamasu2 zamasu = new Zamasu2((int) (BossFactory.ZAMAS2 - 1 - i ), zone);
//        }
    }
    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkMidle = new String[]{"Ta chính là đệ nhất vũ trụ cao thủ"};
        textTalkAfter = new String[]{"Ác quỷ biến hình aaa..."};
    }
    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (plAtt != null) {
                int skill = plAtt.playerSkill.skillSelect.template.id;
                if (skill == Skill.DE_TRUNG || skill == Skill.MAFUBA || skill == Skill.SUPER_KAME || skill == Skill.LIEN_HOAN
                        || skill == Skill.SUPER_ANTOMIC) {
                    damage = 0;
                    Service.getInstance().chat(this, "Xí hụt");
                }
                else if(plAtt.itemTime != null && plAtt.itemTime.isUsebinhtangluc){
                    damage = 2;
                }
                else {
                    damage = 1;
                }
            }
            long dame = super.injured(plAtt, damage, piercing, isMobAttack);
//            if (this.isDie()) {
//                rewards(plAtt);
//            }
            return dame;
        }
    }
    @Override

    public void leaveMap() {
        super.leaveMap();
        this.changeToIdle();
    }
}