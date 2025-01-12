/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.ToxicOverlord;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.map.ToxicAbyss.KhiGas;
import nro.models.player.Player;

/**
 *
 * @author Arriety
 */
public abstract class ToxicOverlord extends Boss {

    protected KhiGas khigas;
    private short px, py;

    public ToxicOverlord(long id, short x, short y, KhiGas khigas, BossData data) {
        super((byte) 0, data);
        this.id = id;
        this.khigas = khigas;
        this.px = x;
        this.py = y;
    }

    @Override
    public void leaveMap() {
        super.leaveMap();
        khigas.removeBoss(this);
        ToxicOverlord boss = khigas.getBoss(0);
        if (boss != null) {
            boss.changeToAttack();
        }
    }

    @Override
    public void joinMap() {

    }

    @Override
    protected abstract boolean useSpecialSkill();

    @Override
    public abstract void rewards(Player pl);

    @Override
    public abstract void idle();

    @Override
    public abstract void checkPlayerDie(Player pl);

    @Override
    public abstract void initTalk();

}
