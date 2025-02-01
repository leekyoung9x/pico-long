/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.shop.ItemShop;
import nro.models.shop.Shop;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.services.func.ShopService;

/**
 *
 * @author Arriety
 */
public class Bill extends Npc {

    public Bill(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 48:
//                    if (!player.setClothes.godClothes) {
//                        this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
//                                "Ngươi hãy mang 5 món thần linh\nvà x99 thức ăn đến đây...\nrồi ta nói tiếp",
//                                "Từ chối");
//                    } else {
//                    createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "Ngươi muốn gì nào?",
//                            "Mua đồ\nhủy diệt", "SKH\nHỦY DIỆT", "Đóng");
                    createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi muốn gì nào?",
                            "Mua đồ\nhủy diệt", "Đóng");
//                    }
                    break;
                case 154:
                    this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
//                            "...", "Về\nthánh địa\n Kaio", "Đổi cá\nTiên tri", "Từ chối");
                            "...", "Về\nthánh địa\n Kaio", "Từ chối");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 48:
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            switch (select) {
                                case 0:
                                    ShopService.gI().openShopNormal(player, this, ConstNpc.SHOP_BILL_HUY_DIET_0, 0, -1);
                                    break;
//                                case 1:
//                                    // Để có thể mua được đồ huỷ diệt ở SHOP này Player phải sỡ hữu full 5 món SKH Thần linh trên người
//                                    int totalSKHTL = 0;
//                                    for (Item current : player.inventory.itemsBody) {
//                                        if (current.isNotNullItem()) {
//                                            if (current.isNotNullItem() && current.isItemThanLinh()) {
//                                                for (ItemOption io : current.itemOptions) {
//                                                    if (io.optionTemplate.id == 129 || io.optionTemplate.id == 248 || io.optionTemplate.id == 128 || io.optionTemplate.id == 135 || io.optionTemplate.id == 134 || io.optionTemplate.id == 133 || io.optionTemplate.id == 130 || io.optionTemplate.id == 131 || io.optionTemplate.id == 132) {
//                                                        totalSKHTL++;
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }
//                                    if (totalSKHTL < 5) {
//                                        Service.getInstance().sendThongBao(player, "Để mua được đồ huỷ diệt ở SHOP này bạn phải sở hữu full 5 món SKH Thần linh trên người");
//                                        return;
//                                    } else {
//                                        Shop shop = ShopService.gI().getShop(this.tempId, 1, -1);
//                                        for (int i = 0; i < shop.tabShops.size(); i++) {
//                                            if (shop.tabShops.get(i).id == 51) {
//                                                for (int j = 0; j < shop.tabShops.get(i).itemShops.size(); j++) {
//                                                    ItemShop itemShop = shop.tabShops.get(i).itemShops.get(j);
//
//                                                    if (itemShop.temp.id == ConstItem.AO_HUY_DIET || itemShop.temp.id == ConstItem.AO_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.AO_HUY_DIET_XAYDA) {
//                                                        switch (player.gender) {
//                                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET);
//                                                                break;
//                                                            case (byte) ConstPlayer.NAMEC:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET_NAMEC);
//                                                                break;
//                                                            case (byte) ConstPlayer.XAYDA:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET_XAYDA);
//                                                                break;
//                                                        }
//                                                    }
//
//                                                    if (itemShop.temp.id == ConstItem.QUAN_HUY_DIET || itemShop.temp.id == ConstItem.QUAN_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.QUAN_HUY_DIET_XAYDA) {
//                                                        switch (player.gender) {
//                                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET);
//                                                                break;
//                                                            case (byte) ConstPlayer.NAMEC:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET_NAMEC);
//                                                                break;
//                                                            case (byte) ConstPlayer.XAYDA:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET_XAYDA);
//                                                                break;
//                                                        }
//                                                    }
//
//                                                    if (itemShop.temp.id == ConstItem.GANG_HUY_DIET || itemShop.temp.id == ConstItem.GANG_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.GANG_HUY_DIET_XAYDA) {
//                                                        switch (player.gender) {
//                                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET);
//                                                                break;
//                                                            case (byte) ConstPlayer.NAMEC:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET_NAMEC);
//                                                                break;
//                                                            case (byte) ConstPlayer.XAYDA:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET_XAYDA);
//                                                                break;
//                                                        }
//                                                    }
//
//                                                    if (itemShop.temp.id == ConstItem.GIAY_HUY_DIET || itemShop.temp.id == ConstItem.GIAY_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.GIAY_HUY_DIET_XAYDA) {
//                                                        switch (player.gender) {
//                                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET);
//                                                                break;
//                                                            case (byte) ConstPlayer.NAMEC:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET_NAMEC);
//                                                                break;
//                                                            case (byte) ConstPlayer.XAYDA:
//                                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET_XAYDA);
//                                                                break;
//                                                        }
//                                                    }
//                                                }
//                                                break;
//                                            }
//                                        }
//                                        ShopService.gI().openShopType3(player, shop, ConstNpc.SHOP_BILL_SPEC);
//                                    }
//                                    break;
                            }
                    }
                    break;
                case 154:
                    switch (select) {
                        case 0:
                            ChangeMapService.gI().changeMapBySpaceShip(player, 50, -1, 387);
                            break;
//                        case 1: {
//                            Item i = InventoryService.gI().findItemBagByTemp(player, 1999);
//                            if (i == null) {
//                                this.npcChat(player, "Bạn thiếu Cá tiên tri");
//                                return;
//                            }
//                            if (i.quantity <= 99) {
//                                this.npcChat(player, "Bạn thiếu x99 Cá tiên tri");
//                                return;
//                            }
//                            Item ct = ItemService.gI().createNewItem((short) 1277, 1);
//                            ct.itemOptions.add(new ItemOption(50, 40));
//                            ct.itemOptions.add(new ItemOption(77, 40));
//                            ct.itemOptions.add(new ItemOption(103, 40));
//                            ct.itemOptions.add(new ItemOption(101, 500));
//                            ct.itemOptions.add(new ItemOption(106, 1));
//                            if (Util.isTrue(1, 100)) {
//                                ct.itemOptions.add(new ItemOption(74, 1));
//                            } else {
//                                ct.itemOptions.add(new ItemOption(93, 1));
//                            }
//                            InventoryService.gI().subQuantityItemsBag(player, i, 99);
//                            InventoryService.gI().addItemBag(player, ct, 99);
//                            InventoryService.gI().sendItemBags(player);
//                            break;
//                        }
                    }

                    break;
            }
        }
    }
}
