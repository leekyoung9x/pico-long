/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

/**
 *
 * @author Arriety
 */
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.func.ChangeMapService;
import nro.utils.Util;

public class Dracule extends Npc {

    public Dracule(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                    "Haha ngươi đang nằm trong bụng ta hahaa",
                    "Quay về\nĐảo khơ mer",
                    "Đổi pet\nHồn ma Đíctên",
                    "Từ chối");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
                    case 0:
                        ChangeMapService.gI().changeMap(player, 5, -1, 1072, 408);
                        break;
                    case 1:
                        this.createOtherMenu(player, ConstNpc.MENU_TRADE_DOG,
                                "Bạn có muốn đổi lấy pet Hồn ma Đíctên\nVới tỉ lệ 5% vv || tỉ lệ 95% HSD 1 ngày",
                                "Đổi\nx999 Đá\nluyện ngục", "Từ chối");
                        break;
                    case 2:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_TRADE_DOG) {
                switch (select) {
                    case 0:
                        Item dln = InventoryService.gI().findItemBagByTemp(player, 1950);
                        if (dln == null || dln.quantity < 999) {
                            this.npcChat(player, "chưa đủ x999 đá Luyện Ngục");
                            return;
                        }
                        Item pet = ItemService.gI().createNewItem((short) 1949, 1);
                        if (Util.isTrue(5, 95)) {
                            pet.itemOptions.add(new ItemOption(50, 30));
                            pet.itemOptions.add(new ItemOption(77, 30));
                            pet.itemOptions.add(new ItemOption(103, 30));
                            pet.itemOptions.add(new ItemOption(42, 20));
                            InventoryService.gI().addItemBag(player, pet, 1_500_000);
                            this.npcChat(player, "Bạn đã đổi được pet Hồn ma Đíctên");
                        } else {
                            pet.itemOptions.add(new ItemOption(50, 30));
                            pet.itemOptions.add(new ItemOption(77, 30));
                            pet.itemOptions.add(new ItemOption(103, 30));
                            pet.itemOptions.add(new ItemOption(42, 20));
                            pet.itemOptions.add(new ItemOption(93, 1));
                            InventoryService.gI().addItemBag(player, pet, 1_500_000);

                            this.npcChat(player, "Bạn đã đổi được pet Hồn ma Đíctên, Hạn sửa dụng 1 ngày");
                        }

                        InventoryService.gI().subQuantityItemsBag(player, dln, 999);
                        InventoryService.gI().sendItemBags(player);
                        break;

                }
            }
        }
    }
}
