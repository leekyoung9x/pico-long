package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.NpcService;
import nro.services.Service;
import nro.utils.Util;

public class DocNhan extends Npc {


    public DocNhan(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (player.clan.doanhTrai != null && player.clan.doanhTrai.isClearDoanhTrai()) {
                if (player.clan.doanhTrai.checkPlayerHaveGotReward((int) player.id)) {
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi khá lắm khi chinh phục được doanh trại của ta\n" +
                                    "Đây là phần thưởng dành cho ngươi",
                            "Có", "Không");
                } else {
                    NpcService.gI().createTutorial(player, avartar,
                            "Ngươi đang thèm khát phần thưởng của kẻ khác sao???");
                }
            } else {
                NpcService.gI().createTutorial(player, avartar,
                        "Hãy dọn sạch doanh trại này trước khi đến gặp ta");
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
//                            short id = 2013;
//                            if (Util.isTrue(1, 10)) {
//                                id = 1227;
//                            }
//                            Item item = ItemService.gI().createNewItem(id);
//                            InventoryService.gI().addItemBag(player, item, 0);
//                            InventoryService.gI().sendItemBags(player);
//                            Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.getName());

                            player.inventory.addGem(100_000);
                            int oldRuby = player.inventory.ruby;
                            player.inventory.addRuby(100_000);
                            // ekko ghi log add ruby
                            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "DocNhan-confirmMenu");
                            Service.getInstance().sendMoney(player);
                            Service.getInstance().sendThongBao(player, "Bạn nhận được 100k ngọc xanh và hồng ngọc");

                            player.clan.doanhTrai.addPlayerHaveGotReward((int) player.id);
//                            player.clan.doanhTrai.finish();
                            break;
                        }
                    }
                    break;

            }
        }
    }

}