package nro.models.boss.blackgoku;

import nro.consts.ConstPet;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.Service;

public class BlackGokuBase extends Boss {

    public BlackGokuBase() {
        super(BossFactory.BILLCON, BossData.BILLCON);
    }

    public BlackGokuBase(int bossId, BossData bossData) {
        super(bossId, bossData);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (plAtt != null) {
                if (plAtt.pet != null && plAtt.pet.typePet == ConstPet.BLACK_GOKU) {
                    damage = 0; // Nếu pet là BLACK_GOKU thì không
                }

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

//            if (plAtt.getSession() != null && plAtt.isAdmin()) {
//                damage = this.nPoint.hpMax;
//            }

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
}
