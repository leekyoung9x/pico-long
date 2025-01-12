package nro.models.boss.bosstuonglai;

import java.util.Random;
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

public class zamas3 extends Boss {
 private static final Random RANDOM = new Random();
    public zamas3() {
        super(BossFactory.ZAMAS3, BossData.ZAMAS2);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

       @Override
    public void rewards(Player pl) {
        int sl = 1;
        int[] tempIds = {2110, 898}; // Các ID item

        // Chọn một chỉ mục ngẫu nhiên từ mảng
        int tempId = tempIds[RANDOM.nextInt(tempIds.length)];

        // In ra tempId để kiểm tra
        System.out.println("Temp ID: " + tempId);

        ItemMap itemMap = new ItemMap(this.zone, tempId, sl, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);

        // Thêm các tùy chọn cho itemMap dựa trên tempId
        if (tempId == 2110) {
            itemMap.options.add(new ItemOption(50, 3));
            itemMap.options.add(new ItemOption(77, 3));
            itemMap.options.add(new ItemOption(103, 3));
            itemMap.options.add(new ItemOption(72, 1));

            if (Util.isTrue(80, 100)) { // 80% xác suất thêm tùy chọn 93
                itemMap.options.add(new ItemOption(93, 1));
            }
        } else if (tempId == 898) {
            itemMap.options.add(new ItemOption(50, 50));
            itemMap.options.add(new ItemOption(77, 50));
            itemMap.options.add(new ItemOption(103, 50));
            itemMap.options.add(new ItemOption(247, 1));
            itemMap.options.add(new ItemOption(93, 1));
        }

        // Khởi tạo và thả item map
        RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
        Service.getInstance().dropItemMap(this.zone, itemMap);
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
                        // Nếu HP mới sau khi nhận sát thương dưới 20 triệu
                        if (newHp < 20000000) {
                            this.nPoint.hp = 20000000; // Đặt HP của boss còn lại là 20 triệu
                            Service.getInstance().point(this);
                            Service.getInstance().Send_Info_NV(this);
                            Service.getInstance().sendInfoPlayerEatPea(this);
                            damage = 0; // Không gây thêm sát thương

                        }
                    }
                }

                // Giới hạn damage nếu HP của boss <= 2%
                if (this.nPoint.getCurrPercentHP() <= 2) {
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
        textTalkMidle = new String[]{"Công lý sẽ được thực thi?"};
        textTalkAfter = new String[]{"Zamas ta sẽ cho các ngươi thấy sức mạnh tối thượng..."};
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
