package nro.models.map;

import nro.models.item.ItemTemplate;
import java.util.ArrayList;
import java.util.List;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.utils.Util;
import nro.services.ItemMapService;
import nro.services.ItemService;
import nro.services.Service;

public class ItemMap {

    public Zone zone;
    public int itemMapId;
    public ItemTemplate itemTemplate;
    public int quantity;

    public int x;
    public int y;
    public long playerId;
    public short range;
    public List<ItemOption> options;

    public long createTime;
    public boolean isCheckDuplicate = false;

    private final int timeMoveToPlayer = 10000;
    private long lastTimeMoveToPlayer;
    public boolean isBlackBall;
    public boolean isNamecBall;

    public boolean isNamecBallTranhDoat;
    public byte typeHaveBallTranhDoat = -1;

    public boolean isPickedUp;

    public ItemMap(Zone zone, int tempId, int quantity, int x, int y, long playerId) {
        this.zone = zone;
        this.itemMapId = zone.countItemAppeaerd++;
        if (zone.countItemAppeaerd >= 2000000000) {
            zone.countItemAppeaerd = 0;
        }
        this.itemTemplate = ItemService.gI().getTemplate((short) tempId);
        this.quantity = quantity;
        this.x = x;
        this.y = y;
        this.playerId = playerId != -1 ? Math.abs(playerId) : playerId;
        this.createTime = System.currentTimeMillis();
        this.options = new ArrayList<>();
        this.isBlackBall = ItemMapService.gI().isBlackBall(this.itemTemplate.id);
        this.isNamecBall = ItemMapService.gI().isNamecBall(this.itemTemplate.id);
        this.lastTimeMoveToPlayer = System.currentTimeMillis();
        this.zone.addItem(this);
//        System.out.println("id item: " + tempId + " zone id:" + this.zone.zoneId + " quantity: " + quantity);
    }

    public ItemMap(Zone zone, int tempId, int quantity, int x, int y, long playerId, Player player) {
        this.zone = zone;
        this.itemMapId = zone.countItemAppeaerd++;

        int specialRule = 1;

        if (zone.countItemAppeaerd >= 2_000_000_000) {
            zone.countItemAppeaerd = 0;
        }
        this.itemTemplate = ItemService.gI().getTemplate((short) tempId);
        if (tempId == 77 || tempId == 861) {
            if (player.isPl() && player.nPoint.isX2ItemReward) {
                // x3 ngọc và HN nhận được
                specialRule = 3;
                if (player.isPl() && player.getSession().actived >= 1 ) {
                    this.quantity = quantity * specialRule;
                } else {
                    this.quantity = quantity;
                }
            } else if (player.isPl() && player.getSession().actived >= 1) {
                this.quantity = quantity * specialRule;
            } else {
                this.quantity = quantity;
            }
        } else if (player.isPl() && player.getSession().actived >= 1) {
            this.quantity = quantity * specialRule;
        } else {
            this.quantity = quantity;
        }
        this.x = x;
        this.y = y;
        this.playerId = playerId != -1 ? Math.abs(playerId) : playerId;
        this.createTime = System.currentTimeMillis();
        this.options = new ArrayList<>();
        this.isBlackBall = ItemMapService.gI().isBlackBall(this.itemTemplate.id);
        this.isNamecBall = ItemMapService.gI().isNamecBall(this.itemTemplate.id);
        this.lastTimeMoveToPlayer = System.currentTimeMillis();
        this.zone.addItem(this);
    }

//    public ItemMap(Zone zone, int tempId, int quantity, int x, int y, long playerId, Player player) {
////        System.out.println("player name: " + player.name + " active: " + player.getSession().actived);
//        this.zone = zone;
//        this.itemMapId = zone.countItemAppeaerd++;
//        if (zone.countItemAppeaerd >= 2_000_000_000) {
//            zone.countItemAppeaerd = 0;
//        }
//        this.itemTemplate = ItemService.gI().getTemplate((short) tempId);
//        if (player.isPl() && player.getSession().actived) {
//            this.quantity = quantity * 2;
//        } else {
//            this.quantity = quantity;
//        }
//        this.x = x;
//        this.y = y;
//        this.playerId = playerId != -1 ? Math.abs(playerId) : playerId;
//        this.createTime = System.currentTimeMillis();
//        this.options = new ArrayList<>();
//        this.isBlackBall = ItemMapService.gI().isBlackBall(this.itemTemplate.id);
//        this.isNamecBall = ItemMapService.gI().isNamecBall(this.itemTemplate.id);
//        this.lastTimeMoveToPlayer = System.currentTimeMillis();
//        this.zone.addItem(this);
//    }
    private int checkOption(Player player) {
        int qt = 0;
        if (player != null && player.inventory != null && player.inventory.itemsBag != null) {
            for (Item item : player.inventory.itemsBag) {
                if (item.isNotNullItem()) {
                    for (ItemOption io : item.itemOptions) {
                        if (io.optionTemplate.id == 234) {
                            qt = 1;
                            break;
                        }
                    }
                    if (qt == 1) {
                        break;
                    }
                }
            }
        }
        return qt;
    }

    public ItemMap(ItemMap itemMap) {
        this.zone = itemMap.zone;
        this.itemMapId = itemMap.itemMapId;
        this.itemTemplate = itemMap.itemTemplate;
        this.quantity = itemMap.quantity;
        this.x = itemMap.x;
        this.y = itemMap.y;
        this.playerId = itemMap.playerId;
        this.options = itemMap.options;
        this.isBlackBall = itemMap.isBlackBall;
        this.isNamecBall = itemMap.isNamecBall;
        this.lastTimeMoveToPlayer = itemMap.lastTimeMoveToPlayer;
        this.createTime = System.currentTimeMillis();
        this.zone.addItem(this);
    }

    public void update() {
        if (this.isNamecBallTranhDoat) {
            return;
        }
        if (this.isBlackBall) {
            if (Util.canDoWithTime(lastTimeMoveToPlayer, timeMoveToPlayer)) {
                if (this.zone != null) {
                    List<Player> players = this.zone.getPlayers();
                    if (!players.isEmpty()) {
                        Player player = players.get(0);
                        if (player.zone != null && player.zone.equals(this.zone)) {
                            this.x = player.location.x;
                            this.y = this.zone.map.yPhysicInTop(this.x, player.location.y - 24);
                            reAppearItem();
                            this.lastTimeMoveToPlayer = System.currentTimeMillis();
                        }
                    }
                }
            }
            return;
        }
        if (Util.canDoWithTime(createTime, 30000)) {
            if (this.zone.map.mapId != 21 && this.zone.map.mapId != 22
                    && this.zone.map.mapId != 23 && this.itemTemplate.id != 78) {
                ItemMapService.gI().removeItemMapAndSendClient(this);
            }
        }
        if (Util.canDoWithTime(createTime, 15000)) {
            this.playerId = -1;
        }
    }

    public void reAppearItem() {
        ItemMapService.gI().sendItemMapDisappear(this);
        Service.getInstance().dropItemMap(this.zone, this);
    }

    public void dispose() {
        this.zone = null;
        this.itemTemplate = null;
        this.options = null;
    }
}
