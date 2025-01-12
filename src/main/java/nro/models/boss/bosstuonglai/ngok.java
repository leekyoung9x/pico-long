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
import nro.server.ServerNotify;
import nro.services.PlayerService;
import nro.services.RewardService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.Random;

public class ngok extends Boss {

    public ngok() {
        super(BossFactory.NGOK, BossData.NGOK);
    }
    private static final Random RANDOM = new Random();
    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        // Số lượng item mặc định
        int sl = 1;
        int[] itemIds = {2118, 2119, 2120, 542, 2134}; // Các ID item

        // Xác suất tương ứng cho từng item
        double[] probabilities = {0.05, 0.05, 0.05, 0.45, 0.40};

        // Chọn item dựa trên xác suất
        int tempId = getItemByProbability(itemIds, probabilities);

        // Số lượng item tùy thuộc vào ID
        int quantity = (tempId == 542) ? RANDOM.nextInt(5) + 1 : sl; // Số lượng ngẫu nhiên từ 1 đến 5 cho item 542

        // In ra thông tin về item được chọn và số lượng
        System.out.println("Item được chọn: ID = " + tempId + ", Số lượng = " + quantity);

        ItemMap itemMap = new ItemMap(this.zone, tempId, quantity, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);

        // Thêm các tùy chọn cho itemMap dựa trên tempId
        if (tempId == 2118) {
            itemMap.options.add(new ItemOption(77, 8));
        } else if (tempId == 2119) {
            itemMap.options.add(new ItemOption(103, 10));
        } else if (tempId == 2120) {
            itemMap.options.add(new ItemOption(50, 4));

        }

        // Khởi tạo và thả item map
        RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
        Service.getInstance().dropItemMap(this.zone, itemMap);
    }

    private int getItemByProbability(int[] itemIds, double[] probabilities) {
        double rand = RANDOM.nextDouble(); // Tạo một số ngẫu nhiên trong khoảng [0, 1)
        double cumulativeProbability = 0.0;

        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (rand < cumulativeProbability) {
                return itemIds[i];
            }
        }

        // Nếu không tìm thấy (trong trường hợp có lỗi), trả về item mặc định
        return itemIds[itemIds.length - 1];
    }


    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
          Service.getInstance().point(this);
        Service.getInstance().Send_Info_NV(this);
//        if (this.isDie()) {
//            return 0;
//        } else {
//            if (plAtt != null) {
//                int skill = plAtt.playerSkill.skillSelect.template.id;
//                int newHp = this.nPoint.hp - damage; // Tính HP mới sau khi nhận sát thương
//
//                // Kiểm tra nếu HP của boss bị đánh dưới 20 triệu
//                if (this.nPoint.getCurrPercentHP() <= 20) {
//                    // Kiểm tra nếu kỹ năng không phải là các kỹ năng đặc biệt
//                    if (skill != Skill.TU_SAT && skill != Skill.MAKANKOSAPPO && skill != Skill.QUA_CAU_KENH_KHI) {
//                        // Nếu HP mới sau khi nhận sát thương dưới 20 triệu
//                        if (newHp < 30000000) {
//                            this.nPoint.hp = 30000000; // Đặt HP của boss còn lại là 20 triệu
//                            Service.getInstance().point(this);
//                            Service.getInstance().Send_Info_NV(this);
//                            Service.getInstance().sendInfoPlayerEatPea(this);
//                            damage = 0; // Không gây thêm sát thương
//
//                        }
//                    }
//                }
//
//                // Giới hạn damage nếu HP của boss <= 2%
//                if (this.nPoint.getCurrPercentHP() <= 5) {
//                    if (skill == Skill.TU_SAT || skill == Skill.MAKANKOSAPPO || skill == Skill.QUA_CAU_KENH_KHI) {
//                        damage *= 1; // Có thể bỏ qua nếu không cần thay đổi sát thương
//                    } else {
//                        damage = 0; // Các kỹ năng không đặc biệt sẽ không gây sát thương
//                        Service.getInstance().chat(this, "Chỉ kĩ năng đặc biệt mới có tác dụng với ta, ngươi không có khả năng đó đâu haha");
//                    }
//                } else if (plAtt.pet != null && plAtt.pet.typePet == ConstPet.BILL_CON) {
//                    damage *= 2; // Tăng gấp đôi sát thương nếu pet là BILL_CON
//                }
//            }
//        rewards(plAtt);
            // Tính sát thương thực tế
        long dame = super.injured(plAtt, damage / 20, piercing, isMobAttack);

            return dame;

        }



    @Override
    public void idle() {

    }

    @Override
    public void joinMap() {
        if (this.zone == null) {
            this.zone = getMapCanJoin(mapJoin[Util.nextInt(0, mapJoin.length - 1)]);
        }
        if (this.zone != null) {
//            BossFactory.createBoss(BossFactory.BATGIOI).zone = this.zone;

            ChangeMapService.gI().changeMapBySpaceShip(this, this.zone, ChangeMapService.TENNIS_SPACE_SHIP);
            ServerNotify.gI().notify("Boss " + this.name + " vừa xuất hiện tại " + this.zone.map.mapName);
        }
    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        textTalkMidle = new String[]{"Ăn 1 gậy của lão tôn"};
        textTalkAfter = new String[]{"Tề thiên đại thánh!"};
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
