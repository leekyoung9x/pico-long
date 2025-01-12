package nro.models.boss.list_boss;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.Util;

import java.util.Random;

public class BrolyXanh extends Boss {

    public BrolyXanh() {
        super(BossFactory.BROLY_XANH, BossData.BROLY_XANH);
    }

    protected BrolyXanh(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            if (Util.isTrue(1, 100)) {
//                int x = this.location.x + Util.nextInt(-30, 30);
//                if (x < 30) {
//                    x = 30;
//                } else if (x > zone.map.mapWidth - 30) {
//                    x = zone.map.mapWidth - 30;
//                }
//                int y = this.location.y;
//                if (y > 24) {
//                    y = this.zone.map.yPhysicInTop(x, y - 24);
//                }
//                ItemMap item = ArrietyDrop.DropItemSetHDKichHoat(plKill, plKill.gender, 1, x, y);
//                Service.getInstance().dropItemMap(plKill.zone, item);
            } else {
                this.dropItemReward(Util.nextInt(14, 20), (int) plKill.id);
            }

            // 10% nhận được đá trí tuệ
            if(Util.isTrue(10, 100)) {
                int x = this.location.x + Util.nextInt(-30, 30);
                if (x < 30) {
                    x = 30;
                } else if (x > zone.map.mapWidth - 30) {
                    x = zone.map.mapWidth - 30;
                }
                int y = this.location.y;
                if (y > 24) {
                    y = this.zone.map.yPhysicInTop(x, y - 24);
                }
                ItemMap itemMap = new ItemMap(this.zone, ConstItem.DA_TRI_TUE, 1, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
            }

            // 90% nhận được 1 -> 2000 hồng ngọc
            if(Util.isTrue(90, 100)) {
                int x = this.location.x + Util.nextInt(-30, 30);
                if (x < 30) {
                    x = 30;
                } else if (x > zone.map.mapWidth - 30) {
                    x = zone.map.mapWidth - 30;
                }
                int y = this.location.y;
                if (y > 24) {
                    y = this.zone.map.yPhysicInTop(x, y - 24);
                }
                Random random = new Random();
                int quantityDrop = random.nextInt(1, 2000);
                ItemMap itemMap = new ItemMap(this.zone, ConstItem.HONG_NGOC, quantityDrop, this.location.x, this.location.y, plKill.id);
                Service.getInstance().dropItemMap(plKill.zone, itemMap);
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
        if (plAtt.setClothes.godClothes) {
            if (this.isDie()) {
                return 0;
            } else {
                if (this.isDie()) {
                    rewards(plAtt);
                }
                // ekko dame gây ra / 5
                long point = damage / 5;
                return super.injured(plAtt, point, piercing, isMobAttack);
            }
        } else {
            Service.getInstance().chat(plAtt, "Chà chà phải có set thần linh mới tiêu diệt được tên này");
            return 0;
        }
    }
}
