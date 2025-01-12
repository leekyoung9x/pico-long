package nro.models.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nro.models.player.Player;
import nro.server.Client;
import nro.services.Service;
import nro.utils.Util;

/**
 *
 * @stole Arriety
 */
public abstract class FutureBoss extends Boss {

    public HashMap<Long, Long> topDame = new HashMap<>();

    public FutureBoss(byte id, BossData data) {
        super(id, data);
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (plAtt == null) {
            return 0;
        }
        damage = (damage / 100) * 80;
        long da = super.injured(plAtt, damage, piercing, isMobAttack);
        if (this.id == BossFactory.CUMBER || this.id == BossFactory.CUMBER2) {
            long d = da;
            if (topDame.containsKey(plAtt.id)) {
                d += topDame.get(plAtt.id);
            }
            topDame.put(plAtt.id, d);
            HashMap<Long, Long> hashMap = Util.sortHashMapByValue(topDame);
            List<Map.Entry<Long, Long>> entryList = new ArrayList<>(hashMap.entrySet());
            for (int i = 0; i < 3; i++) {
                if (i < entryList.size()) {
                    Map.Entry<Long, Long> entry = entryList.get(i);
                    if (entry != null) {
                        Player pl = Client.gI().getPlayer(entry.getKey());
                        if (pl != null) {
                            Service.getInstance().sendTextTime(plAtt, (byte) (i + 10), String.format("#%s %s [%s", i + 1, pl.name, Math.ceil((double) entry.getValue() / (double) this.nPoint.hpMax * 100)) + " %]", (short) -1);
                        }
                    }
                }
            }
            entryList.clear();
        }
        return da;
    }
}
