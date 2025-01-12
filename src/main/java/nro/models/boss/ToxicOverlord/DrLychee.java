/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.boss.ToxicOverlord;

import nro.models.boss.BossData;
import nro.models.map.ToxicAbyss.KhiGas;
import nro.models.player.Player;

/**
 *
 * @author Arriety
 */
public class DrLychee extends ToxicOverlord {

    public DrLychee(long id, short x, short y, KhiGas khigas, BossData data) {
        super(id, x, y, khigas, data);
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
    }

    @Override
    public void idle() {
    }

    @Override
    public void checkPlayerDie(Player pl) {
    }

    @Override
    public void initTalk() {
    }

}
