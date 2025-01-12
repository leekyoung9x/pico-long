/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.map.ToxicAbyss;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import nro.models.map.ToxicAbyss.zones.ZonePoisonous;

/**
 *
 * @author Arriety
 */
@Getter
@Setter
public abstract class Poisonous {

    protected final List<ZonePoisonous> zones = new ArrayList<>();
    protected int level;
    protected int countDown;
    protected boolean isClose;

    public Poisonous(int level) {
        setLevel(level);
        init();
    }

    public void update() {
        if (countDown > 0) {
            countDown--;
            if (countDown == 0) {
                close();
            }
        }
    }

    public void close() {
        if (!isClose) {
            isClose = true;
            synchronized (zones) {
                zones.forEach((z) -> {
                    z.close();
                });
                zones.clear();
            }
        }
    }

    protected abstract void init();

}
