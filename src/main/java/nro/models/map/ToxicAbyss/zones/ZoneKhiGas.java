/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.map.ToxicAbyss.zones;

import java.util.List;
import java.util.stream.Collectors;
import nro.models.map.Map;
import nro.models.map.ToxicAbyss.KhiGas;
import nro.models.map.ToxicAbyss.Poisonous;
import nro.models.mob.Mob;
import nro.models.player.Player;
import nro.services.func.ChangeMapService;

/**
 *
 * @author Arriety
 */
public class ZoneKhiGas extends ZonePoisonous {

    protected Poisonous poisonous;

    public ZoneKhiGas(Map map, Poisonous poisonous) {
        super(map, poisonous);
    }

    @Override
    public void initMob(Mob mob) {
        int level = ((KhiGas) poisonous).getLevel();
        long maxHP = mob.point.getHpFull() * 30 * level;
        mob.point.setHP(maxHP);
        mob.point.setHpFull(maxHP);
        mob.setTiemNang();
    }

    @Override
    public void close() {
        List<Player> players = getPlayers();
        synchronized (players) {
            players = players.stream().collect(Collectors.toList());
            for (Player player : players) {
                ChangeMapService.gI().changeMapBySpaceShip(player, player.gender + 21, -1, -1);
            }
        }
        super.close();
    }
}
