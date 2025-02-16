/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nro.models.boss.boss_ban_do_kho_bau;

import nro.consts.ConstItem;
import nro.consts.ConstOption;
import nro.consts.ConstRatio;
import nro.consts.MapName;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.item.ItemOption;
import nro.models.map.phoban.BanDoKhoBau;
import nro.models.player.Player;
import nro.services.RewardService;
import nro.services.SkillService;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

import java.util.List;
import nro.models.map.ItemMap;
import nro.services.Service;

/**
 *
 * @Build by Arriety
 */
public class TrungUyXanhLo extends BossBanDoKhoBau {

    private boolean activeAttack;

    public TrungUyXanhLo(BanDoKhoBau banDoKhoBau) {
        super(BossFactory.TRUNG_UY_XANH_LO, BossData.TRUNG_UY_XANH_LO_2, banDoKhoBau);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (this.isDie()) {
            return 0;
        } else {
            if (this.isDie()) {
                rewards(plAtt);
            }
            return super.injured(plAtt, damage, piercing, isMobAttack);
        }
    }

    @Override
    public void attack() {
        try {
            if (activeAttack) {
                if (!useSpecialSkill()) {
                    Player pl = getPlayerAttack();
                    this.playerSkill.skillSelect = this.getSkillAttack();
                    if (Util.getDistance(this, pl) <= this.getRangeCanAttackWithSkillSelect()) {
                        if (Util.isTrue(10, ConstRatio.PER100)) {
                            goToXY(pl.location.x + Util.nextInt(-20, 20),
                                    Util.nextInt(pl.location.y - 80, this.zone.map.yPhysicInTop(pl.location.x, 0)), false);
                        }
                        SkillService.gI().useSkill(this, pl, null);
                        checkPlayerDie(pl);
                    } else {
                        goToPlayer(pl, false);
                    }
                }
            } else {
                List<Player> notBosses = this.zone.getNotBosses();
                for (Player pl : notBosses) {

                    if (pl.location.x >= 820 && !pl.effectSkin.isVoHinh) {
                        this.activeAttack = true;
                        break;
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @Override
    protected boolean useSpecialSkill() {
        //boss này chỉ có chiêu thái dương hạ san
        this.playerSkill.skillSelect = this.getSkillSpecial();
        if (SkillService.gI().canUseSkillWithCooldown(this)) {
            SkillService.gI().useSkill(this, null, null);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void rewards(Player player) {
        if (player != null) {
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


//            dropItem(player, Util.nextInt(2045, 2051), x, y);
//
//            // Drop additional items 2095 and 2096
//            dropItem(player, 2095, x, y);
//            dropItem(player, 2096, x, y);
            // 90% rơi ngọc rồng từ 4s -> 7s
            ItemMap itemMap = null;
            if (Util.isTrue(90, ConstRatio.PER100)) {
                dropItem(player, Util.nextInt(ConstItem.NGOC_RONG_4_SAO, ConstItem.NGOC_RONG_7_SAO), x, y);
            } else {
                itemMap = new ItemMap(this.zone, ConstItem.CAI_TRANG_ZAMASU, 1, x, y, player.id);
                itemMap.options.add(new ItemOption(ConstOption.SUC_DANH_CONG_PHAN_TRAM, Util.nextInt(30, 35)));
                itemMap.options.add(new ItemOption(ConstOption.KI_CONG_PHAN_TRAM, 15));
                itemMap.options.add(new ItemOption(ConstOption.HP_CONG_PHAN_TRAM, 15));
                itemMap.options.add(new ItemOption(ConstOption.HAN_SU_DUNG, 1));
                itemMap.options.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 1));
                RewardService.gI().initBaseOptionClothes(itemMap.itemTemplate.id, itemMap.itemTemplate.type, itemMap.options);
                Service.getInstance().dropItemMap(zone, itemMap);
            }
        }
    }

    // Helper method to drop items
    private void dropItem(Player player, int itemId, int x, int y) {
        ItemMap itemMap = new ItemMap(this.zone, itemId, 1, x, y, player.id);
        if (itemMap != null) {
            Service.getInstance().dropItemMap(zone, itemMap);
        }
    }


    @Override
    public void joinMap() {
        try {
            this.zone = banDoKhoBau.getMapById(MapName.DONG_KHO_BAU);
            ChangeMapService.gI().changeMap(this, this.zone, 1065, this.zone.map.yPhysicInTop(1065, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
