/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossManager;
import nro.models.boss.list_boss.Shiba;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.shop.ItemShop;
import nro.models.shop.Shop;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.services.func.ShopService;
import nro.utils.Util;

/**
 *
 * @author Administrator
 */
public class Bunma extends Npc {

    public Bunma(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (!TaskService.gI().checkDoneTaskTalkNpc(player, this)) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Cậu cần trang bị gì cứ đến chỗ tôi nhé", "Cửa\nhàng", "Đóng");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
                    case 0:// Shop
                        if (player.gender == ConstPlayer.TRAI_DAT) {
                            this.openShopWithGender(player, ConstNpc.SHOP_BUNMA_QK_0, 0);
                        } else {
                            this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Xin lỗi cưng, chị chỉ bán đồ cho người Trái Đất", "Đóng");
                        }
                        break;
                    case 1:// Shop
//                        Shop shop = ShopService.gI().getShop(this.tempId, 1, -1);
//
//                        for (int i = 0; i < shop.tabShops.size(); i++) {
//                            if (shop.tabShops.get(i).id == 50) {
//                                for (int j = 0; j < shop.tabShops.get(i).itemShops.size(); j++) {
//                                    ItemShop itemShop = shop.tabShops.get(i).itemShops.get(j);
//
//                                    if (itemShop.temp.id == ConstItem.AO_HUY_DIET || itemShop.temp.id == ConstItem.AO_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.AO_HUY_DIET_XAYDA) {
//                                        switch (player.gender) {
//                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET);
//                                                break;
//                                            case (byte) ConstPlayer.NAMEC:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET_NAMEC);
//                                                break;
//                                            case (byte) ConstPlayer.XAYDA:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.AO_HUY_DIET_XAYDA);
//                                                break;
//                                        }
//                                    }
//
//                                    if (itemShop.temp.id == ConstItem.QUAN_HUY_DIET || itemShop.temp.id == ConstItem.QUAN_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.QUAN_HUY_DIET_XAYDA) {
//                                        switch (player.gender) {
//                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET);
//                                                break;
//                                            case (byte) ConstPlayer.NAMEC:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET_NAMEC);
//                                                break;
//                                            case (byte) ConstPlayer.XAYDA:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.QUAN_HUY_DIET_XAYDA);
//                                                break;
//                                        }
//                                    }
//
//                                    if (itemShop.temp.id == ConstItem.GANG_HUY_DIET || itemShop.temp.id == ConstItem.GANG_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.GANG_HUY_DIET_XAYDA) {
//                                        switch (player.gender) {
//                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET);
//                                                break;
//                                            case (byte) ConstPlayer.NAMEC:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET_NAMEC);
//                                                break;
//                                            case (byte) ConstPlayer.XAYDA:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GANG_HUY_DIET_XAYDA);
//                                                break;
//                                        }
//                                    }
//
//                                    if (itemShop.temp.id == ConstItem.GIAY_HUY_DIET || itemShop.temp.id == ConstItem.GIAY_HUY_DIET_NAMEC || itemShop.temp.id == ConstItem.GIAY_HUY_DIET_XAYDA) {
//                                        switch (player.gender) {
//                                            case (byte) ConstPlayer.TRAI_DAT:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET);
//                                                break;
//                                            case (byte) ConstPlayer.NAMEC:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET_NAMEC);
//                                                break;
//                                            case (byte) ConstPlayer.XAYDA:
//                                                itemShop.temp = ItemService.gI().getTemplate(ConstItem.GIAY_HUY_DIET_XAYDA);
//                                                break;
//                                        }
//                                    }
//                                }
//                                break;
//                            }
//                        }
//                        ShopService.gI().openShopType3(player, shop, ConstNpc.SHOP_BUNMA_SPEC);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
