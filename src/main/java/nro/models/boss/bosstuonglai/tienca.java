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
import nro.services.RewardService;
import nro.services.Service;
import nro.utils.Util;

public class tienca extends Boss{
    public tienca() {
        super(BossFactory.TIENCA, BossData.TIENCA);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        int tempId = -1;
        int sl = 1;

        // 50% tỷ lệ nhận được random 1 đến 10 vật phẩm : 695, 696, 697, 698
        if (Util.isTrue(4, 10)) {
            int[] items = {695, 696, 697, 698};
            tempId = items[Util.nextInt(0, items.length - 1)];
            sl = Util.nextInt(1, 10);
        }
        // 20% tỷ lệ nhận được Ván bay Rùa (random 1 đến 25% option 50, 77, 103)
        else if (Util.isTrue(3, 10)) {
            tempId = 897;  // giả sử 2008 là ID của Ván bay Rùa
            sl = 1;
        }
        // 30% tỷ lệ nhận được item 2107
        else if (Util.isTrue(3, 10)) {
            tempId = 2107;
        }

        if (tempId != -1) {
            ItemMap itemMap = new ItemMap(this.zone, tempId, sl, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);

            // Nếu nhận được Ván bay Rùa
            if (tempId == 897) {
                itemMap.options.add(new ItemOption(50, Util.nextInt(1, 25)));
                itemMap.options.add(new ItemOption(77, Util.nextInt(1, 25)));
                itemMap.options.add(new ItemOption(103, Util.nextInt(1, 25)));
                itemMap.options.add(new ItemOption(197, 1));
            }

            RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
            Service.getInstance().dropItemMap(this.zone, itemMap);
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
//                if (this.nPoint.getCurrPercentHP() <= 10) {
//                    if (skill == Skill.TU_SAT || skill == Skill.MAKANKOSAPPO || skill == Skill.QUA_CAU_KENH_KHI){
//                        damage *= 1;
//                    }else {
//                        damage = 0;
//                        Service.getInstance().chat(this, "Chỉ kĩ năng đặc biệt mới có tác dụng với ta, ngươi không có khả năng đó đâu haha");
//                    }
//                }
//                else if(plAtt.pet != null && plAtt.pet.typePet == ConstPet.BILL_CON){
//                    damage *= 2;
//                }
//            }
//            int dame = super.injured(plAtt,damage/2,piercing,isMobAttack);
//            return dame;
//        }
    }
//}