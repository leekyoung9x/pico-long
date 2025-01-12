/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.map.ToxicAbyss;

import java.util.ArrayList;
import java.util.List;
import nro.models.boss.ToxicOverlord.ToxicOverlord;

/**
 *
 * @author Arriety
 */
public class KhiGas extends Poisonous {

    protected final List<ToxicOverlord> bosses = new ArrayList<>();

    public KhiGas(int level) {// set time & name 
        super(level);
        initBoss();
    }

    public ToxicOverlord getBoss(int index) {
        synchronized (bosses) {
            if (index < 0 || index >= bosses.size()) {
                return null;
            }
            return bosses.get(index);
        }
    }

    public void removeBoss(ToxicOverlord boss) {
        synchronized (bosses) {
            bosses.remove(boss);
        }
    }

    private void initBoss() {
        int idBoss = -19062006;
        
    }

    @Override
    protected void init() {// init map
    }

}
