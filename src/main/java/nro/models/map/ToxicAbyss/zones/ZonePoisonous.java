/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.map.ToxicAbyss.zones;

import lombok.Getter;
import nro.models.map.Map;
import nro.models.map.MapTemplate;
import nro.models.map.ToxicAbyss.Poisonous;
import nro.models.map.Zone;
import nro.models.mob.Mob;
import nro.models.mob.MobTemplate;
import nro.server.Manager;

/**
 *
 * @author Administrator
 */
@Getter
public abstract class ZonePoisonous extends Zone {

    protected Poisonous poisonous;

    public ZonePoisonous(Map map, Poisonous poisonous) {
        super(map, 0, 20);
        map.addZone(this);
        this.poisonous = poisonous;
        init();
    }

    private void init() {
        MapTemplate template = Manager.getMapTemplate(map.mapId);
        if (template != null) {
            for (int i = 0; i < template.mobTemp.length; i++) {
                MobTemplate temp = Manager.getMobTemplateByTemp(template.mobTemp[i]);
                Mob mob = new Mob();
                mob.tempId = template.mobTemp[i];
                mob.level = template.mobLevel[i];
                mob.point.setHpFull(template.mobHp[i]);
                mob.point.setHP(template.mobHp[i]);
                mob.location.x = template.mobX[i];
                mob.location.y = template.mobY[i];
                mob.pDame = temp.percentDame;
                mob.pTiemNang = temp.percentTiemNang;
                mob.setTiemNang();
                mob.status = 5;
                mob.zone = this;
                initMob(mob);
                addMob(mob);
            }
        }
    }

    public abstract void initMob(Mob mob);

    public void close() {
        map.removeZone(this);
    }

}
