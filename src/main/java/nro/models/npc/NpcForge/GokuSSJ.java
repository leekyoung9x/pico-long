/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstItemName;
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;

/**
 *
 * @author Arriety
 */
public class GokuSSJ extends Npc {

    public GokuSSJ(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (this.mapId == 133) {
                Item biKiep = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.BINH_NUOC);
                int soLuong = 0;
                if (biKiep != null) {
                    soLuong = biKiep.quantity;
                }
                if (soLuong >= 10000) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có " + soLuong
                            + " " + ConstItemName.BINH_NUOC + ".\n"
                            + "Hãy kiếm đủ 10000 " + ConstItemName.BINH_NUOC + " tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart\nNgoài ra bạn sẽ nhận được thêm\nx1 Hộp quà Kích Hoạt",
                            "Học dịch\nchuyển", "Đóng");
                } else {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Bạn đang có " + soLuong
                            + " " + ConstItemName.BINH_NUOC + ".\n"
                            + "Hãy kiếm đủ 10000 " + ConstItemName.BINH_NUOC + " tôi sẽ dạy bạn cách dịch chuyển tức thời của người Yardart",
                            "Đóng");
                }
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (this.mapId == 133) {
                Item biKiep = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.BINH_NUOC);
                int soLuong = 0;
                if (biKiep != null) {
                    soLuong = biKiep.quantity;
                }
                if (soLuong >= 10000 && InventoryService.gI().getCountEmptyBag(player) > 0) {
                    Item yardart = ItemService.gI().createNewItem((short) (player.gender + 592));
                    yardart.itemOptions.add(new ItemOption(47, 400));
                    yardart.itemOptions.add(new ItemOption(108, 10));
                    InventoryService.gI().addItemBag(player, yardart, 0);
                    InventoryService.gI().subQuantityItemsBag(player, biKiep, 10000);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendThongBao(player,
                            "Bạn vừa nhận được trang phục tộc Yardart");
                }
            }
        }
    }
}
