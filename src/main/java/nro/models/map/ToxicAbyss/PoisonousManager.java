/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.map.ToxicAbyss;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arriety
 */
public class PoisonousManager implements Runnable {

    private boolean running;
    private final List<Poisonous> list = new ArrayList<>();

    public void start() {
        this.running = true;
    }

    public void shutdown() {
        this.running = false;
    }

    public void update() {
        synchronized (list) {
            List<Poisonous> p = new ArrayList<>();
            for (Poisonous poisonous : list) {
                try {
                    poisonous.update();
                } catch (Exception e) {
                }
                if (poisonous.isClose()) {
                    p.add(poisonous);
                }
            }
            list.removeAll(p);
        }
    }

    @Override
    public void run() {
        while (running) {
            long now = System.currentTimeMillis();
            long now2 = System.currentTimeMillis();
            update();
            if (now2 - now < 1000) {
                try {
                    Thread.sleep(1000 - (now2 - now));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
