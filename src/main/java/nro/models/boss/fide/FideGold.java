package nro.models.boss.fide;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.mabu.MabuWar;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.*;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.time.LocalTime;
import java.util.Random;
import nro.consts.ConstPet;
import nro.models.item.Item;
import nro.models.skill.Skill;

/**
 * @Build by Arriety
 */
public class FideGold extends Boss {

    public FideGold() {
        super(BossFactory.FIDEGOLD, BossData.FIDEGOLD);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        if (plKill != null) {
            if (Util.isTrue(50, 100)) {
                ItemMap ao = new ItemMap(this.zone, 1958, 1,
                        plKill.location.x - 50, this.zone.map.yPhysicInTop(plKill.location.x, plKill.location.y - 24), plKill.id);
                ao.options.add(new ItemOption(50, 50));
                ao.options.add(new ItemOption(77, 50));
                ao.options.add(new ItemOption(103, 50));
                ao.options.add(new ItemOption(93, 1));
                ao.options.add(new ItemOption(247, 0));
                dropNe(ao);
            } else if (Util.isTrue(90, 100)) {
                Item tksl = ItemService.gI().createNewItem((short) 992);
                tksl.itemOptions.add(new ItemOption(93, 1));
                InventoryService.gI().addItemBag(plKill, tksl, 9999);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill, "Bạn nhận được " + tksl.template.name);
            } else {
                Item ic = ItemService.gI().createNewItem((short) 1951);
                ic.itemOptions.add(new ItemOption(257, 0));

                InventoryService.gI().addItemBag(plKill, ic, 9999);
                InventoryService.gI().sendItemBags(plKill);
                Service.getInstance().sendThongBao(plKill, "Bạn nhận được " + ic.template.name);
            }
        }
    }

//    @Override
//    public void rewards(Player pl) {
//        ItemMap ao = new ItemMap(this.zone, 629, 1,
//                pl.location.x - 50, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
//        ao.options.add(new ItemOption(50, Util.nextInt(23, 30)));
//        ao.options.add(new ItemOption(77, Util.nextInt(23, 30)));
//        ao.options.add(new ItemOption(103, Util.nextInt(23, 30)));
//        ao.options.add(new ItemOption(101, 100));
//        ao.options.add(new ItemOption(93, Util.nextInt(1, 2)));
//        ao.options.add(new ItemOption(106, 0));
//
////        ItemMap non = new ItemMap(this.zone, 629, 1,
////                pl.location.x - 25, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
////        non.options.add(new ItemOption(50, 23));
////        non.options.add(new ItemOption(77, 23));
////        non.options.add(new ItemOption(103, 23));
////        non.options.add(new ItemOption(93, Util.nextInt(1, 2)));
////        non.options.add(new ItemOption(106, 0));
////
////        ItemMap mu = new ItemMap(this.zone, 629, 1,
////                pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
////        mu.options.add(new ItemOption(50, 23));
////        mu.options.add(new ItemOption(77, 23));
////        mu.options.add(new ItemOption(103, 23));
////        mu.options.add(new ItemOption(93, Util.nextInt(1, 2)));
////        mu.options.add(new ItemOption(106, 0));
////
////        ItemMap toc = new ItemMap(this.zone, 629, 1,
////                pl.location.x + 25, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
////        toc.options.add(new ItemOption(50, 23));
////        toc.options.add(new ItemOption(77, 23));
////        toc.options.add(new ItemOption(103, 23));
////        toc.options.add(new ItemOption(93, Util.nextInt(1, 2)));
////        toc.options.add(new ItemOption(106, 0));
////
////        ItemMap rada = new ItemMap(this.zone, 629, 1,
////                pl.location.x + 50, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
////        rada.options.add(new ItemOption(50, 23));
////        rada.options.add(new ItemOption(77, 23));
////        rada.options.add(new ItemOption(103, 23));
////        rada.options.add(new ItemOption(93, Util.nextInt(1, 2)));
////        rada.options.add(new ItemOption(106, 0));
//        dropNe(ao);
////        dropNe(non);
////        dropNe(mu);
////        dropNe(toc);
////        dropNe(rada);
//        generalRewards(pl);
//    }
    private void dropNe(ItemMap itemMap) {
        itemMap.isCheckDuplicate = true;
        RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
        Service.getInstance().dropItemMap(this.zone, itemMap);
    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        this.textTalkMidle = new String[]{"Oải rồi hả?", "Ê cố lên nhóc",
            "Chán", "Ta có nhầm không nhỉ"};
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (plAtt != null) {
                int skill = plAtt.playerSkill.skillSelect.template.id;
                if (this.nPoint.getCurrPercentHP() <= 5) {
                    if (skill == Skill.TU_SAT || skill == Skill.MAKANKOSAPPO || skill == Skill.QUA_CAU_KENH_KHI) {
                        damage *= 1;
                    } else {
                        damage = 0;
                        Service.getInstance().chat(this, "Chỉ kĩ năng đặc biệt mới có tác dụng với ta, ngươi không có khả năng đó đâu haha");
                    }
                } else if (plAtt.pet != null && plAtt.pet.typePet == ConstPet.BILL_CON) {
                    damage *= 2;
                }
            }
            long dame = super.injured(plAtt, damage / 2, piercing, isMobAttack);
            return dame;
        }
    }

//    @Override
//    protected void rest() {
//        boolean isInTimeRange = isCurrentTimeInRange(20, 0, 23, 59);
//
//        if (isInTimeRange) {
//            super.rest();
//        }
//    }
    public static boolean isCurrentTimeInRange(int startHour, int startMinute, int endHour, int endMinute) {
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = LocalTime.of(startHour, startMinute);
        LocalTime endTime = LocalTime.of(endHour, endMinute);

        if (endTime.isBefore(startTime)) {
            return !currentTime.isBefore(startTime) || !currentTime.isAfter(endTime);
        } else {
            return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
        }
    }
}
