package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstItemName;
import nro.consts.ConstNpc;
import nro.models.boss.Boss;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.boss.cold.CoolerGold;
import nro.models.clan.Clan;
import nro.models.clan.ClanMember;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.Service;
import nro.services.TaskService;

import java.util.Iterator;

public class GiumaDauBo extends Npc {

    public GiumaDauBo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Chào mừng đến với địa ngục của ta?",
                        "Gọi Boss bang\n999 Capsule\nBang hội", "Gọi Boss bang\n10k TVV", "Từ chối");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (player.iDMark.getIndexMenu()) {
                case ConstNpc.BASE_MENU:
                    switch (select) {
                        case 0: {
                            Item item = InventoryService.gI().findItemBagByTemp(player, ConstItem.CAPSULE_BANG_HOI);

                            ClanMember cm = player.clanMember;
                            if (cm == null) {
                                Service.getInstance().sendThongBao(player, "@@");
                                return;
                            }

                            if (cm.role != Clan.LEADER) {
                                Service.getInstance().sendThongBao(player, "Chỉ bang chủ có thể triệu hồi Cooler Gold");
                                return;
                            }

                            if (item == null || item.quantity < 999) {
                                Service.getInstance().sendThongBao(player,
                                        "Cần 999 Capsule bang hội để có thể triệu hồi Cooler Gold");
                                return;
                            }

                            InventoryService.gI().subQuantityItemsBag(player, item, 999);
                            InventoryService.gI().sendItemBags(player);

                            boolean canSummonBoss = true;
                            int khu = 0;

                            try {
                                Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
                                while (iterator.hasNext()) {
                                    Boss boss = iterator.next();
                                    if (boss != null && boss.id == BossFactory.COOLER_VANG && !boss.isDie()) {
                                        canSummonBoss = false;
                                        khu = boss.zone.zoneId;
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (canSummonBoss) {
                                new Thread(() -> {
                                    try {
                                        Thread.sleep(1000);
                                        CoolerGold coolerGold = new CoolerGold(player.zone);
                                        coolerGold.respawnBoss();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }).start();
                            } else {
                                Service.getInstance().sendThongBao(player,
                                        "Tên quái vật đó đã được giải phóng ở khu " + khu + " rồi");
                            }
                            break;
                        }
                        case 1: {
                            callBossWithItem(player, ConstItem.THOI_VANG_VIP, 10_000);
                        }
                    }
                    break;
            }
        }
    }

    private void callBossWithItem(Player player, int itemId, int quantity) {
        Item item = InventoryService.gI().findItemBagByTemp(player, itemId);

        ClanMember cm = player.clanMember;
        if (cm == null) {
            Service.getInstance().sendThongBao(player, "@@");
            return;
        }

        if (cm.role != Clan.LEADER) {
            Service.getInstance().sendThongBao(player, "Chỉ bang chủ có thể triệu hồi Cooler Gold");
            return;
        }

        if (item == null || item.quantity < quantity) {

            String name = itemId == ConstItem.CAPSULE_BANG_HOI ? "Capsule Bang hội" : ConstItemName.MONEY_UNIT;

            Service.getInstance().sendThongBao(player, "Cần " + quantity + " " + name +" để có thể triệu hồi Cooler Gold");
            return;
        }

        InventoryService.gI().subQuantityItemsBag(player, item, quantity);
        InventoryService.gI().sendItemBags(player);

        boolean canSummonBoss = true;
        int khu = 0;

        try {
            Iterator<Boss> iterator = BossManager.BOSSES_IN_GAME.iterator();
            while (iterator.hasNext()) {
                Boss boss = iterator.next();
                if (boss != null && boss.id == BossFactory.COOLER_VANG && !boss.isDie()) {
                    canSummonBoss = false;
                    khu = boss.zone.zoneId;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (canSummonBoss) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    CoolerGold coolerGold = new CoolerGold(player.zone);
                    coolerGold.respawnBoss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        } else {
            Service.getInstance().sendThongBao(player, "Tên quái vật đó đã được giải phóng ở khu " + khu + " rồi");
        }
    }
}