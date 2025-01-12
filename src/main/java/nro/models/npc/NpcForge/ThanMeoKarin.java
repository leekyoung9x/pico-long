/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstMap;
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.dungeon.zones.ZSnakeRoad;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class ThanMeoKarin extends Npc {

    public ThanMeoKarin(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (mapId == ConstMap.THAP_KARIN) {
                if (player.zone instanceof ZSnakeRoad) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Hãy cầm lấy hai hạt đậu cuối cùng ở đây\nCố giữ mình nhé "
                            + player.name,
                            "Cảm ơn\nsư phụ");
                } else if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                    // ekko
//                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "Con hãy bay theo cây Gậy Như Ý trên đỉnh tháp để đến Thần Điện gặp Thượng đế\n Con có muốn đổi x99 Bánh bao!\nĐể đổi lấy con gấu trúc đáng yêu này không",
//                            "Đổi x99\nBánh bao", "Từ chối");
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Con hãy bay theo cây Gậy Như Ý trên đỉnh tháp để đến Thần Điện gặp Thượng đế\n Con có muốn đổi x99 Bánh bao!\nĐể đổi lấy con gấu trúc đáng yêu này không",
                            "Từ chối");
                }
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (mapId == ConstMap.THAP_KARIN) {
                if (player.iDMark.isBaseMenu()) {
                    if (player.zone instanceof ZSnakeRoad) {
                        switch (select) {
                            case 0:
                                player.setInteractWithKarin(true);
                                Service.getInstance().sendThongBao(player,
                                        "Hãy mau bay xuống chân tháp Karin");
                                break;
                        }
                    } else {
                        // ekko
//                        switch (select) {
//                            case 0:
//                                Item find = InventoryService.gI().findItemBag(player, 1962);
//                                if (find == null) {
//                                    this.npcChat(player, "Con đã thiếu bánh bao");
//                                    return;
//                                }
//                                if (find.quantity < 99) {
//                                    this.npcChat(player, "Con đã thiếu x99 bánh bao");
//                                    return;
//                                }
//                                if (InventoryService.gI().getCountEmptyBag(player) > 1) {
//                                    Item pet = ItemService.gI().createNewItem((short) 2084);
//                                    pet.itemOptions.add(new ItemOption(50, 30));
//                                    pet.itemOptions.add(new ItemOption(77, 30));
//                                    pet.itemOptions.add(new ItemOption(103, 30));
//                                    if (Util.isTrue(10, 100)) {
//                                        pet.itemOptions.add(new ItemOption(74, 1));
//                                    } else {
//                                        pet.itemOptions.add(new ItemOption(93, 1));
//                                    }
//                                    InventoryService.gI().addItemBag(player, pet, 99);
//                                    InventoryService.gI().subQuantityItemsBag(player, find, 99);
//                                    InventoryService.gI().sendItemBags(player);
//                                    this.npcChat(player, "Chúc mừng con đã được Pet Panda thúi :3");
//                                } else {
//                                    Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
//                                }
//                                break;
//                            case 1:
//                                break;
//                        }
                    }
                }
            }
        }
    }
}
