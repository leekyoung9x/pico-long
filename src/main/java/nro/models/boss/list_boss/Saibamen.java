package nro.models.boss.list_boss;

import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.server.Manager;
import nro.server.ServerNotify;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

public class Saibamen extends Boss {

    public Saibamen() {
        super(BossFactory.SAIBAMEN, BossData.Saibamen);
    }

    protected Saibamen(byte id, BossData bossData) {
        super(id, bossData);
    }

    @Override
    public void joinMap() {
        if (this.zone == null) {
            this.zone = getMapCanJoin(mapJoin[Util.nextInt(0, mapJoin.length - 1)]);
        }
        if (this.zone != null) {
            ChangeMapService.gI().changeMapBySpaceShip(this, this.zone, ChangeMapService.TELEPORT_YARDRAT);
            ServerNotify.gI().notify("Boss " + this.name + " vừa xuất hiện tại " + this.zone.map.mapName);
            System.out.println("Boss: " + this.name + " xuất hiện mapId: " + this.zone.map.mapId + " zone: " + this.zone.zoneId);

            if (id == BossFactory.SAIBAMEN) {
                createCopy(this);
            }
        }
    }

    protected void createCopy(Boss boss) {
        CreateClone(boss, (byte) BossFactory.SAIBAMEN_ONE);
        CreateClone(boss, (byte) BossFactory.SAIBAMEN_TWO);
        CreateClone(boss, (byte) BossFactory.SAIBAMEN_THREE);
    }

    private static void CreateClone(Boss boss, byte id) {
        Boss saibamen = new Saibamen(id, BossData.Saibamen);
        saibamen.zone = boss.zone;
        saibamen.joinMap();
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player plKill) {
        int slRuby = 5000;
        for (int i = 0; i < slRuby; i++) {
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(plKill.id, plKill.inventory.ruby, plKill.inventory.ruby + 1, "Saibamen-rewards");
            ItemMap ruby = new ItemMap(zone, 861, 1, 10 * i + this.location.x,
                     zone.map.yPhysicInTop(this.location.x, 0), -1);
            Service.getInstance().dropItemMap(this.zone, ruby);
        }
    }

    @Override
    public void initTalk() {
        this.textTalkAfter = new String[]{"Các ngươi chờ đấy, ta sẽ quay lại sau"};
    }

    @Override
    public void idle() {
        if (this.countIdle >= this.maxIdle) {
            this.maxIdle = Util.nextInt(0, 3);
            this.countIdle = 0;
            this.changeAttack();
        } else {
            this.countIdle++;
        }
    }

    @Override
    public void checkPlayerDie(Player pl) {
        if (pl.isDie()) {
            Service.getInstance().chat(this, "Chừa nha " + plAttack.name + " động vào ta chỉ có chết.");
            this.plAttack = null;
        }
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        return 1;
    }

    @Override
    public void leaveMap() {
        super.leaveMap();

        if (id != BossFactory.SAIBAMEN) {
            BossManager.gI().removeBoss(this);
        }
    }
}
