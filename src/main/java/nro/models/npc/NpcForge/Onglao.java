/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItemName;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossManager;
import nro.models.boss.list_boss.Shiba;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.services.func.Input;
import nro.services.func.ShopService;
import nro.utils.Util;

/**
 *
 * @author Administrator
 */
public class Onglao extends Npc {

    public Onglao(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Xin chào cậu cần gì?", "Trả khế\n đổi vàng","Shop VIP","Hối lộ","Đổi thỏi \nvàng [VIP]","Đổi thỏi \nvàng [VIP]");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
                    case 0:// Shop

                        Item i2 = InventoryService.gI().findItemBagByTemp(player, 1287);
                        if (i2 == null) {
                            this.npcChat(player, "Bạn không có quả khế");
                            return;
                        }
                        if (i2.quantity <= 99) {
                            this.npcChat(player, "Bạn không đủ x99 quả khế");
                            return;

                        }
                        Item thoivip = ItemService.gI().createNewItem((short) 1288, 1);
                        InventoryService.gI().subQuantityItemsBag(player, i2, 99);

                        InventoryService.gI().addItemBag(player, thoivip, 9999);
                        InventoryService.gI().sendItemBags(player);
                        this.npcChat(player, "Đổi thỏi vàng vip thành công");


                        break;
                    case 1:// Shop
                        ShopService.gI().openShopSpecial(player, this, ConstNpc.SHOP_VIP, 0, -1);
                        break;
                    case 2:// Shop
                        Item i = InventoryService.gI().findItemBagByTemp(player, 1288);
                        if (i == null) {
                            this.npcChat(player, "Bạn không có thỏi vàng VIP");
                            return;
                        }
                        if (i.quantity <= 10) {
                            this.npcChat(player, "Bạn không đủ 10 thỏi vàng vip");
                            return;

                        }
                        InventoryService.gI().subQuantityItemsBag(player, i, 10);
                        player.nPoint.nangdong++;
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().point2(player);
                        this.npcChat(player, "Mi đã hối lộ thành công hehehe");
                        break;


                case 3:// Shop

                    if (  player.nPoint.nangdong < 1) {
                        this.npcChat(player, "Cần 1 điểm năng động");
                        return;

                    }
                    Item thoivip2 = ItemService.gI().createNewItem((short) 1288, 1);
                    InventoryService.gI().addItemBag(player, thoivip2, 9999);
                    player.nPoint.nangdong -= 1;
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().point2(player);
                    this.npcChat(player, "Đổi thành công 1 " + ConstItemName.MONEY_UNIT);
                    break;

                    case 4:// Shop

                        Input.gI().doithoivip(player);
                        break;

            }
            }
        }
    }
}
