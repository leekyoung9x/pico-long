package nro.models.mob;

import java.util.ArrayList;
import nro.consts.ConstMap;
import nro.consts.ConstMob;
import java.util.List;

import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.map.dungeon.zones.ZSnakeRoad;
import nro.models.player.Location;
import nro.models.player.Player;
import nro.power.CaptionManager;
import nro.services.MapService;
import nro.services.Service;
import nro.utils.Util;
import nro.services.MobService;
import nro.services.TaskService;

public class Mob {

    public int id;
    public Zone zone;
    public int tempId;
    public String name;
    public byte level;

    public MobPoint point;
    public MobEffectSkill effectSkill;
    public Location location;

    public byte pDame;
    public int pTiemNang;
    private long maxTiemNang;

    public long lastTimeDie;
    public int sieuquai = 0;
    public int lvMob = 0;

    public boolean actived;

    public Mob(Mob mob) {
        this.point = new MobPoint(this);
        this.effectSkill = new MobEffectSkill(this);
        this.location = new Location();
        this.id = mob.id;
        this.tempId = mob.tempId;
        this.level = mob.level;
        this.point.setHpFull(mob.point.getHpFull());
        this.point.setHP(this.point.getHpFull());
        this.location.x = mob.location.x;
        this.location.y = mob.location.y;
        this.pDame = mob.pDame;
        this.pTiemNang = mob.pTiemNang;
        this.setTiemNang();
        this.status = 5;
    }

    public Mob() {
        this.point = new MobPoint(this);
        this.effectSkill = new MobEffectSkill(this);
        this.location = new Location();
    }

    public int getSys() {
        return 0;
    }

    public void setTiemNang() {
        if (this.point.getHpFull() > 500_000) {
            this.maxTiemNang = this.point.getHpFull() / 1550;
        } else {
            this.maxTiemNang = (long) this.point.getHpFull() * (this.pTiemNang + Util.nextInt(-2, 2)) / 200;
        }
    }

    public byte status;
    protected long lastTimeAttackPlayer;

    public boolean isDie() {
        return this.point.getHP() <= 0;
    }

    public synchronized void injured(Player plAtt, long damage, boolean dieWhenHpFull) {
        List<ItemMap> list = new ArrayList<>();
        if (!this.isDie()) {
            if (plAtt != null) {
                int mapid = plAtt.zone.map.mapId;
            }
            if (damage >= this.point.hp) {
                damage = this.point.hp;
            }
            if (!dieWhenHpFull) {
                if (this.point.hp == this.point.maxHp && damage >= this.point.hp) {
                    damage = this.point.hp - 1;
                }
//                if (this.tempId == 0 && damage > 10) {
//                    damage = 10;
//                }
            }

//            if (plAtt.getSession() != null && plAtt.isAdmin()) {
//                damage = this.point.maxHp;
//            }

            this.point.hp -= damage;

            if (this.isDie()) {
                MobService.gI().sendMobDieAffterAttacked(this, plAtt, damage);
                MobService.gI().dropItemTask(plAtt, this);
                TaskService.gI().checkDoneTaskKillMob(plAtt, this);
                TaskService.gI().checkDoneSideTaskKillMob(plAtt, this);
                setDie();
            } else {
                MobService.gI().sendMobStillAliveAffterAttacked(this, damage, plAtt != null ? plAtt.nPoint.isCrit : false, plAtt);
            }
            if (plAtt != null) {// xu ly khi mob die tra tnsm cho player & pet
                int mapid = this.zone.map.mapId;
                long i;

                if (MapService.isMapDiaNguc(mapid)) {
                    i = 0;
                }
//                else if (MapService.gI().isMapHTTV(mapid)) {
//
//                    if (plAtt.isPet) {
//                        i = plAtt.nPoint.isX3TnsmHTTV ? 300000 : 100000;
//                    } else {
//
//                        i = 500000;
//                    }
//                }
                else {
                    i = plAtt.isPl() ? getTiemNangForPlayer(plAtt, damage) : getTiemNangForPet(plAtt, damage, this.tempId);
                }

                // ekko x# TNSM
                if (plAtt.nPoint.isXTNSM) {
                    i = i * plAtt.nPoint.isXTNSMVal;
                }

                // Giảm TNSM sv xuống còn 1/2
                if (plAtt != null && plAtt.isPl() && plAtt.getSession() != null && plAtt.getSession().actived == 0) {
                    i = i / 2;
                }

                if (plAtt.zone.map.mapId == ConstMap.LANH_DIA_BANG_HOI) {
                    i = 0;
                }

                // ekko map thung lũng đá 100k TNSM
                if (MapService.gI().isMapThungLungDa(mapid)) {
                    i = 100_000;

                    if (plAtt.getSession() != null && plAtt.getSession().actived == 2) {
                        i = i * 2;
                    }
                }

                Service.getInstance().addSMTN(plAtt, (byte) 2, i, true);

//                if (MapService.gI().isMapHTTV(mapid)) {
//                    i = plAtt.nPoint.isX3TnsmHTTV ? 1_500_000 : 500_000;
//                } else {
//                    i = plAtt.isPl() ? getTiemNangForPlayer(plAtt, damage) : getTiemNangForPet(plAtt, damage, this.tempId);
//                }
            }
        }
    }

    public long getTiemNangForPlayer(Player pl, long dame) {
//        System.out.println(pl.name);
        int levelPlayer = CaptionManager.getInstance().getLevel(pl);
        int n = levelPlayer - this.level;
        long pDameHit = dame * 100 / point.getHpFull();
//        long tiemNang = pDameHit * maxTiemNang / 100;
        long tiemNang = pDameHit * maxTiemNang * 2 / 3;
        if (tiemNang <= 0) {
            tiemNang = 1;
        }
        if (n >= 0) {
            for (int i = 0; i < n; i++) {
                long sub = tiemNang * 15 / 100;
                if (sub <= 0) {
                    sub = 1;
                }
                tiemNang -= sub;
            }
        } else {
            for (int i = 0; i < -n; i++) {
                long add = tiemNang * 10 / 100;
                if (add <= 0) {
                    add = 1;
                }
                tiemNang += add;
            }
        }

        if (tiemNang <= 0) {
            tiemNang = 1;
        }
        tiemNang = (int) pl.nPoint.calSucManhTiemNang(tiemNang);
        // Set gohan đủ 5 món thì x3 tnsm
        if(pl.setClothes.setTNSM == 5) {
            tiemNang *= 3;
        }

        return tiemNang;
    }

    public long getTiemNangForPet(Player pl, long dame, int idMob) {
        int levelPlayer = CaptionManager.getInstance().getLevel(pl);
        int n = levelPlayer - this.level;
        long pDameHit = dame * 10 / point.getHpFull();
        if (pDameHit == 0) {
            pDameHit = 2;
        }
        long tiemNang = pDameHit * maxTiemNang;
//        System.out.println("tn: " + tiemNang);
//        System.out.println("pDameHit: " + pDameHit);
//        System.out.println("maxtn: " + maxTiemNang);
        if (tiemNang <= 0) {
            tiemNang = 1;
        }
        for (int i = 0; i < -n; i++) {
            long add = tiemNang * 10 / 100;
            if (add <= 0) {
                add = 1;
            }
            tiemNang += add;
        }
        if (pl.zone.map.mapId == 212 && pl.nPoint.power < 100_000_000_000L && pl.isPet) {
            tiemNang = 0;
        }
        tiemNang = (int) pl.nPoint.calSucManhTiemNang(tiemNang);
        // Set gohan đủ 5 món thì x3 tnsm
        if(pl.setClothes.setTNSM == 5) {
            tiemNang *= 3;
        }
        return tiemNang;
    }

    public void update() {
        if (this.isDie()) {
            if (!(zone instanceof ZSnakeRoad)) {
                if ((zone.map.type == ConstMap.MAP_NORMAL
                        || zone.map.type == ConstMap.MAP_OFFLINE
                        || zone.map.type == ConstMap.MAP_BLACK_BALL_WAR) && (tempId != ConstMob.HIRUDEGARN) && Util.canDoWithTime(lastTimeDie, 2000)) {
                    MobService.gI().hoiSinhMob(this);
                } else if (this.zone.map.type == ConstMap.MAP_DOANH_TRAI && Util.canDoWithTime(lastTimeDie, 10000)) {
                    MobService.gI().hoiSinhMobDoanhTrai(this);
                }
            }
            return;
        }
        if (zone != null) {
            effectSkill.update();
            if (!zone.getPlayers().isEmpty() && Util.canDoWithTime(lastTimeAttackPlayer, 2000)) {
                attackPlayer();
            }
        }
    }

    public void attackPlayer() {
        if (!isDie() && !effectSkill.isHaveEffectSkill() && !(tempId == 0)) {
            Player pl = getPlayerCanAttack();
            if (pl != null) {
                long damage = MobService.gI().mobAttackPlayer(this, pl);
                MobService.gI().sendMobAttackMe(this, pl, damage);
                MobService.gI().sendMobAttackPlayer(this, pl);
            }
            this.lastTimeAttackPlayer = System.currentTimeMillis();
        }
    }

    public List<Player> playerAttackList = new ArrayList<>();

    public void addPlayerAttack(Player player) {
        if (player != null && !this.isDie() && !player.isBoss && !player.isMiniPet) {
            if (!playerAttackList.contains(player)) {
                this.playerAttackList.add(player);
            }
        } else {
            this.playerAttackList.clear();
        }
    }

    public Player getPlayerCanAttack() {
        int distance = 500;
        Player plAttack = null;
        distance = 100;
        try {
            List<Player> players = this.zone.getNotBosses();
            for (Player pl : players) {
                if (!pl.isDie() && !pl.isBoss && !pl.effectSkin.isVoHinh && !pl.isMiniPet && !pl.nPoint.buffDefenseSatellite) {
                    int dis = Util.getDistance(pl, this);
                    if (dis <= distance) {
                        plAttack = pl;
                        distance = dis;
                    }
                }
            }
        } catch (Exception e) {

        }
        return plAttack;
    }

    public void setDie() {
        this.lastTimeDie = System.currentTimeMillis();
    }
}
