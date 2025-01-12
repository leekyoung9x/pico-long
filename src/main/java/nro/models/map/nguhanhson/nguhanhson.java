package nro.models.map.mabu;

import nro.consts.ConstNpc;
import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.map.Map;
import nro.models.map.Zone;
import nro.models.npc.Npc;
import nro.models.npc.NpcManager;
import nro.models.player.Player;
import nro.services.EffSkinService;
import nro.services.MapService;
import nro.services.NpcService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.TimeUtil;
import nro.utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @build by arriety
 */
public class nguhanhson {

    private static MabuWar i;
    public final List<Boss> bosses = new ArrayList<>();
    public static long TIME_OPEN;

    public static long TIME_CLOSE;
    public static final byte HOUR_OPEN = 17;
    public static final byte MIN_OPEN = 0;
    public static final byte SECOND_OPEN = 0;
    public static final byte HOUR_CLOSE = 19;
    public static final byte MIN_CLOSE = 0;
    public static final byte SECOND_CLOSE = 0;
    private int day2 = -1;
    public boolean initBoss;
    public boolean clearBoss;

    public static MabuWar gI() {
        if (i == null) {
            i = new MabuWar();
        }
        i.setTime();
        return i;
    }

    public void setTime() {
        if (i.day == -1 || i.day != TimeUtil.getCurrDay()) {
            i.day = TimeUtil.getCurrDay();
            try {
                this.TIME_OPEN = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_OPEN + ":" + MIN_OPEN + ":" + SECOND_OPEN, "dd/MM/yyyy HH:mm:ss");
                this.TIME_CLOSE = TimeUtil.getTime(TimeUtil.getTimeNow("dd/MM/yyyy") + " " + HOUR_CLOSE + ":" + MIN_CLOSE + ":" + SECOND_CLOSE, "dd/MM/yyyy HH:mm:ss");
            } catch (Exception e) {
            }
        }
    }

    public boolean isTimenguhanhson() {
        long now = System.currentTimeMillis();
        if (now > TIME_OPEN && now < TIME_CLOSE) {
            return true;
        }
        return false;
    }

    public void update(Player player) {
        try {
            if (player != null && player.zone != null) {
                if (MapService.gI().isMapNguhanhson(player.zone.map.mapId)) {
                    if (isTimenguhanhson()) {
                        if (!initBoss) {
                            BossFactory.initBossnguhanhson();
                            initBoss = true;
                        }



                    }
                    try {
                        if (!isTimenguhanhson() ) {
                            kickOutOfMap(player);
                            removeAllBoss();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void kickOutOfMap(Player player) {
        Service.getInstance().sendThongBao(player, "Trận đại chiến đã kết thúc, tàu vận chuyển sẽ đưa bạn về nhà");
        ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, 250);
    }



    public void removeAllBoss() {
        if (!clearBoss) {
            for (Boss boss : bosses) {
                boss.leaveMap();
            }
            this.bosses.clear();
            clearBoss = true;
        }
    }



}
