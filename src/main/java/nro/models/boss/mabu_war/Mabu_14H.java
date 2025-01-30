package nro.models.boss.mabu_war;

import java.util.logging.Level;
import java.util.logging.Logger;

import nro.consts.ConstItem;
import nro.consts.ConstPet;
import nro.consts.ConstRatio;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.mob.ArrietyDrop;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.*;
import nro.services.func.ChangeMapService;
import nro.utils.Log;
import nro.utils.SkillUtil;
import nro.utils.Util;
import java.util.List;
import java.util.Random;

/**
 *
 * @author DUY
 */
public class Mabu_14H extends BossMabuWar {

    public Mabu_14H(int mapID, int zoneId) {
        super(BossFactory.MABU_MAP, BossData.MABU_MAP2);
        this.mapID = mapID;
        this.zoneId = zoneId;
        this.zoneHold = zoneId;
        this.isMabuBoss = true;
    }

    @Override
    public void attack() {
        if (this.isDie()) {
            die();
            return;
        }
        try {
            if (Util.isTrue(50, 100)) {
                this.talk();
            }
            Player pl = getPlayerAttack();
            if (pl != null) {
                if (!useSpecialSkill()) {
                    if (this.id != BossFactory.MABU_MAP) {
                        if (this.id == BossFactory.BU_HAN && this.nPoint.hp <= 200000) {
                            isUseSpeacialSkill = true;
                            this.playerSkill.skillSelect = this.playerSkill.skills.get(8);
                            SkillService.gI().useSkill(this, pl, null);
                            return;
                        }
                        byte idSkill = (byte) Util.nextInt(0, 2);
                        List<Player> list = getListPlayerAttack(70);
                        switch (idSkill) {
                            case 0:
                                if (pl.isPl() && MapService.gI().getZoneJoinByMapIdAndZoneId(this, 128, zoneHold).getNumOfPlayers() < 4 && Util.canDoWithTime(pl.effectSkill.lastTimeHoldMabu, 9000) && pl.zone.map.mapId != 128 && pl.effectSkill.isTaskHoldMabu <= 0 && Util.canDoWithTime(this.lastTimeUseSpeacialSkill, 12000)) {
                                    Service.getInstance().eatPlayer(this, pl);
                                    if (Util.isTrue(20, 100)) {
                                        this.nextMabu(false);
                                    }
                                }
                                break;
                            case 1:
                                if (Util.canDoWithTime(lastTimeUseSpeacialSkill, 10000) && Util.isTrue(30, 100)) {
                                    Service.getInstance().Mabu14hAttack(this, pl, pl.location.x, pl.location.y, (byte) 1);
                                }
                                break;
                            default:
                                if (Util.canDoWithTime(lastTimeUseSpeacialSkill, 7000) && !list.isEmpty() && Util.isTrue(40, 100)) {
                                    Service.getInstance().Mabu14hAttack(this, pl, this.location.x + ((pl.location.x <= this.location.x) ? -30 : 30), this.location.y, (byte) 0);
                                }
                                break;
                        }
                        list.clear();
                    }
                    if (!isUseSpeacialSkill && pl.effectSkill.isTaskHoldMabu <= 0) {
                        this.playerSkill.skillSelect = this.getSkillAttack();
                        if (this.id == BossFactory.BU_HAN) {
                            while (this.playerSkill.skillSelect.template.id == Skill.QUA_CAU_KENH_KHI) {
                                this.playerSkill.skillSelect = this.getSkillAttack();
                            }
                        }
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
                }
            }
        } catch (Exception ex) {
            Log.error(Mabu_14H.class, ex);
        }
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
//        if (this.isDie()) {
//            return 0;
//        } else {
//            int dame = super.injuredNotCheckDie(plAtt, damage, piercing);
//            if (this.isDie()) {
//                rewards(plAtt);
//            }
//            return dame;
//        }
//        if (plAtt != null && plAtt.isAdmin()) {
//            damage = this.nPoint.hpMax;
//        }

        // ekko cơ chế gây dame giống bill nhí
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
    public void joinMap() {
        this.zone = getMapCanJoin(mapID);
        int x = Util.nextInt(50, this.zone.map.mapWidth - 50);
        ChangeMapService.gI().changeMap(this, this.zone, x, this.zone.map.yPhysicInTop(x, 0));
    }

    @Override
    public Zone getMapCanJoin(int mapId) {
        Zone map = MapService.gI().getZoneJoinByMapIdAndZoneId(this, mapId, zoneId);
        if (map.isBossCanJoin(this)) {
            return map;
        } else {
            return getMapCanJoin(mapJoin[Util.nextInt(0, mapJoin.length - 1)]);
        }
    }

    @Override
    public void idle() {
    }

    @Override
    public void rewards(Player pl) {
        for (int i = 0; i < zone.getPlayers().size(); i++) {
            Player plAll = zone.getPlayers().get(i);
            if (plAll != null) {
                if (plAll.effectSkill.isHoldMabu) {
                    Service.getInstance().removeMabuEat(plAll);
                }
                plAll.effectSkill.lastTimeHoldMabu = System.currentTimeMillis();
                ChangeMapService.gI().changeMap(plAll, 114, this.zoneHold, (short) -1, (short) 5);
            }
        }
        try {
            // 90% ra ngọc rồng
            if(Util.isTrue(90, 100)) {
                int[] lstNgocRong = new int[]{ConstItem.NGOC_RONG_5_SAO, ConstItem.NGOC_RONG_6_SAO, ConstItem.NGOC_RONG_7_SAO};

                // Tạo đối tượng Random
                Random random = new Random();

                // Sinh chỉ số ngẫu nhiên từ 0 đến (array.length - 1)
                int totalIndex = lstNgocRong.length - 1;
                int randomIndex = random.nextInt(totalIndex);

                // Lấy phần tử ngẫu nhiên từ mảng
                int randomElement = lstNgocRong[randomIndex];
                ItemMap itemMap = new ItemMap(this.zone, randomElement, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
                Service.getInstance().dropItemMap(this.zone, itemMap);
            } else {
                int slDrop = Util.nextInt(1, 5);
                ItemMap itemMap = null;
                for (int i = 0; i < slDrop; i++) {
                    itemMap = ArrietyDrop.DropItemReWardDoTL(pl, 1, pl.location.x, pl.location.y);
                    if(itemMap != null) {
                        Service.getInstance().dropItemMap(this.zone, itemMap);
                    }
                }
            }

            //tạo spl siêu cấp
//            Item spl = ItemService.gI().createNewItem((short) randomElement, 1);
//            spl.itemOptions.add(new ItemOption(optionId, optionValue));
//            InventoryService.gI().addItemBag(pl, spl, 999);
//            InventoryService.gI().sendItemBags(pl);
//            Service.getInstance().sendThongBao(pl, "Bạn đã nhận được " + spl.template.name);
//            ItemMap itemMap = new ItemMap(this.zone, randomElement, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
//            itemMap.options.add(new ItemOption(optionId, optionValue));
//            Service.getInstance().dropItemMap(this.zone, itemMap);

//            ItemMap skh = new ItemMap(this.zone, 2013, 3, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
//            skh.options.add(new ItemOption(30, 1));
//            Service.getInstance().dropItemMap(this.zone, skh);
//            ItemMap dauThan = new ItemMap(this.zone, ConstItem.DAU_THAN_CAP_11, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
//            Service.getInstance().dropItemMap(this.zone, dauThan);
        } catch (Exception ex) {
            Logger.getLogger(Mabu_14H.class.getName()).log(Level.SEVERE, null, ex);
        }
        TaskService.gI().checkDoneTaskKillBoss(pl, this);
    }

    @Override
    public void checkPlayerDie(Player pl) {
    }

    @Override
    public void initTalk() {
        this.textTalkBefore = new String[]{"Bư! Bư! Bư!", "Bư! Bư! Bư!"};
        this.textTalkMidle = new String[]{"Oe Oe Oe"};
        this.textTalkAfter = new String[]{"Huhu"};
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        this.changeToIdle();
    }
}
