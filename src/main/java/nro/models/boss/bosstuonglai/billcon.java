package nro.models.boss.bosstuonglai;

import nro.consts.ConstPet;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.PlayerService;
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

public class billcon extends Boss {

    public billcon() {
        super(BossFactory.BILLCON, BossData.BILLCON);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            this.dropItemReward(2101, (int) pl.id);
        }
    }


    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (plAtt != null) {
                int skill = plAtt.playerSkill.skillSelect.template.id;
                long newHp = this.nPoint.hp - damage; // Tính HP mới sau khi nhận sát thương

                // Kiểm tra nếu HP của boss bị đánh dưới 20 triệu
                if (this.nPoint.getCurrPercentHP() <= 20) {
                    // Kiểm tra nếu kỹ năng không phải là các kỹ năng đặc biệt
                    if (skill != Skill.TU_SAT && skill != Skill.MAKANKOSAPPO && skill != Skill.QUA_CAU_KENH_KHI) {
                        // nếu không phải skill đặc biệt mà máu dưới 10tr thì boss không mất máu
                        if (newHp < 10000000) {
                            this.nPoint.hp = 10000000; // Đặt HP của boss còn lại là 10 triệu
                            Service.getInstance().point(this);
                            Service.getInstance().Send_Info_NV(this);
                            Service.getInstance().sendInfoPlayerEatPea(this);
                            damage = 0; // Không gây thêm sát thương
                        }
                    }
                }

                // Giới hạn damage nếu HP của boss <= 2%
                if (this.nPoint.getCurrPercentHP() <= 5) {
                    if (skill == Skill.TU_SAT || skill == Skill.MAKANKOSAPPO || skill == Skill.QUA_CAU_KENH_KHI) {
                        damage *= 1; // Có thể bỏ qua nếu không cần thay đổi sát thương
                    } else {
                        damage = 0; // Các kỹ năng không đặc biệt sẽ không gây sát thương
                        Service.getInstance().chat(this, "Chỉ kĩ năng đặc biệt mới có tác dụng với ta, ngươi không có khả năng đó đâu haha");
                    }
                } else if (plAtt.pet != null && plAtt.pet.typePet == ConstPet.BILL_CON) {
                    damage *= 2; // Tăng gấp đôi sát thương nếu pet là BILL_CON
                }
            }

            // Tính sát thương thực tế
            long dame = super.injured(plAtt, damage / 2, piercing, isMobAttack);

            return dame;

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

    public void leaveMap() {
        super.leaveMap();
        this.changeToIdle();
    }

//    @Override
//    public int injured(Player plAtt, int damage, boolean piercing, boolean isMobAttack) {
//        if (this.isDie()) {
//            return 0;
//        } else {
//            if (plAtt != null) {
//                int skill = plAtt.playerSkill.skillSelect.template.id;
//                if (this.nPoint.getCurrPercentHP() <= 5) {
//                    if (skill == Skill.TU_SAT || skill == Skill.MAKANKOSAPPO || skill == Skill.QUA_CAU_KENH_KHI) {
//                        damage *= 1;
//                    } else {
//                        damage = 0;
//                        Service.getInstance().chat(this, "Chỉ kĩ năng đặc biệt mới có tác dụng với ta, ngươi không có khả năng đó đâu haha");
//                    }
//                } else if (plAtt.pet != null && plAtt.pet.typePet == ConstPet.BILL_CON) {
//                    damage *= 2;
//                }
//            }
//            int dame = super.injured(plAtt, damage / 2, piercing, isMobAttack);
//            return dame;
//        }
//    }
}
