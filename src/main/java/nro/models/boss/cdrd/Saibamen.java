/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nro.models.boss.cdrd;

import nro.models.boss.BossData;
import nro.models.item.Item;
import nro.models.map.dungeon.SnakeRoad;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.EffectSkillService;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.SkillService;
import nro.utils.Log;
import nro.utils.Util;

/**
 *
 * @Build by Arriety
 */
public class Saibamen extends CBoss {

    private boolean selfExplosion;

    public Saibamen(long id, short x, short y, SnakeRoad dungeon, BossData data) {
        super(id, x, y, dungeon, data);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            Item nr = ItemService.gI().createNewItem((short) 16);
            nr.quantity = 3;
            InventoryService.gI().addItemBag(pl, nr, 0);
            InventoryService.gI().sendItemBags(pl);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + nr.template.name);
        }
 
    }

    @Override
    public void idle() {

    }

    @Override
    public void checkPlayerDie(Player pl) {

    }

    @Override
    public void initTalk() {
        this.textTalkBefore = new String[]{};
        this.textTalkMidle = new String[]{};
        this.textTalkAfter = new String[]{};
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        long hp = nPoint.hp;
        if (!selfExplosion) {
            if (hp > 1) {
                if (damage > hp) {
                    damage = hp - 1;
                    selfExplosion = true;
                    chat("He he he");
                    if (plAtt != null) {
                        Service.getInstance().chat(plAtt, "Trời ơi muộn mất rồi");
                        Service.getInstance().sendThongBao(plAtt, plAtt.name + " coi chừng đấy!");
                        EffectSkillService.gI().setBlindDCTT(plAtt, System.currentTimeMillis(), 3000);
                        EffectSkillService.gI().sendEffectPlayer(this, plAtt, EffectSkillService.TURN_ON_EFFECT, EffectSkillService.BLIND_EFFECT);
                    }
                    selfExplosion();
                }
            } else {
                damage = 0;
            }
        }
        return super.injured(plAtt, damage, piercing, isMobAttack);
    }

    private void selfExplosion() {
        try {
            this.nPoint.hpMax = 1000000000;
            this.playerSkill.skillSelect = this.getSkillById(Skill.TU_SAT);
            SkillService.gI().useSkill(this, null, null);
            Util.setTimeout(() -> {
                SkillService.gI().useSkill(this, null, null);
            }, 2000);
        } catch (Exception e) {
            Log.error(Saibamen.class, e);
        }
    }

}
