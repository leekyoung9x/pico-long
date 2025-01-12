/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import java.util.Calendar;

import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.map.dungeon.SnakeRoad;
import nro.models.npc.Npc;

import static nro.models.npc.NpcFactory.PLAYERID_OBJECT;

import nro.models.player.Player;
import nro.server.ServerManager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.services.func.Input;

/**
 * @author Administrator
 */
public class ThanVuTru extends Npc {

    public ThanVuTru(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (this.mapId == 48) {
                this.createOtherMenu(player, ConstNpc.BASE_MENU, "Con muốn làm gì nào", "Di chuyển", "Tăng kẹo tuyết");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (this.mapId == 48) {
                if (player.iDMark.isBaseMenu()) {
                    switch (select) {
                        case 0:
                            // ekko
//                            this.createOtherMenu(player, ConstNpc.MENU_DI_CHUYEN,
//                                    "Con muốn đi đâu?", "Về\nthần điện", "Thánh địa\nKaio",
//                                    "Con\nđường\nrắn độc", "Từ chối");
                            this.createOtherMenu(player, ConstNpc.MENU_DI_CHUYEN,
                                    "Con muốn đi đâu?", "Về\nthần điện", "Thánh địa\nKaio",
                                    "Từ chối");
                            break;
                        case 1: {
                            Item item = InventoryService.gI().findItemBagByTemp(player, ConstItem.KEO_NGUOI_TUYET);

                            if (item == null || item.quantity < 99) {
                                Service.getInstance().sendThongBao(player, "Con không có đủ 99 kẹo người tuyết!");
                                return;
                            }

                            InventoryService.gI().subQuantityItemsBag(player, item, 99);

                            Item reward = ItemService.gI().createNewItem((short) ConstItem.HOP_QUA_GIANG_SINH);
                            reward.quantity = 1;

                            InventoryService.gI().addItemBag(player, reward, 0);
                            InventoryService.gI().sendItemBags(player);

                            break;
                        }
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_DI_CHUYEN) {
                    switch (select) {
                        case 0:
                            ChangeMapService.gI().changeMapBySpaceShip(player, 45, -1, 354);
                            break;
                        case 1:
                            ChangeMapService.gI().changeMap(player, 50, -1, 318, 336);
                            break;
                        // ekko
//                        case 2:
//                            // con đường rắn độc
////                            if (!player.getSession().actived) {
////                                Service.getInstance().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sửa dụng chức năng này!");
////                                return;
////                            }
//                            if (player.clan != null) {
//                                Calendar calendar = Calendar.getInstance();
//                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
////                                if (!(dayOfWeek == Calendar.MONDAY
////                                        || dayOfWeek == Calendar.WEDNESDAY
////                                        || dayOfWeek == Calendar.FRIDAY
////                                        || dayOfWeek == Calendar.SUNDAY)) {
////                                    Service.getInstance().sendThongBao(player,
////                                            "Chỉ mở vào thứ 2, 4, 6, CN hàng tuần!");
////                                    return;
////                                }
//                                if (player.clanMember.getNumDateFromJoinTimeToToday() < 2) {
//                                    Service.getInstance().sendThongBao(player,
//                                            "Phải tham gia bang hội ít nhất 2 ngày mới có thể tham gia!");
//                                    return;
//                                }
//                                if (player.clan.snakeRoad == null) {
//                                    this.createOtherMenu(player, ConstNpc.MENU_CHON_CAP_DO,
//                                            "Hãy mau trở về bằng con đường rắn độc\nbọn Xayda đã đến Trái Đất",
//                                            "Chọn\ncấp độ", "Từ chối");
//                                } else {
//                                    if (player.clan.snakeRoad.isClosed()) {
//                                        Service.getInstance().sendThongBao(player,
//                                                "Bang hội đã hết lượt tham gia!");
//                                    } else {
//                                        this.createOtherMenu(player,
//                                                ConstNpc.MENU_ACCEPT_GO_TO_CDRD,
//                                                "Con có chắc chắn muốn đến con đường rắn độc cấp độ "
//                                                + player.clan.snakeRoad.getLevel() + "?",
//                                                "Đồng ý", "Từ chối");
//                                    }
//                                }
//                            } else {
//                                Service.getInstance().sendThongBao(player,
//                                        "Chỉ dành cho những người trong bang hội!");
//                            }
//                            break;

                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_CHON_CAP_DO) {
                    switch (select) {
                        case 0:
                            Input.gI().createFormChooseLevelCDRD(player);
                            break;

                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_ACCEPT_GO_TO_CDRD) {
                    switch (select) {
                        case 0:
                            if (player.clan != null) {
//                                if (!player.getSession().actived) {
//                                    Service.getInstance().sendThongBao(player, "Vui lòng kích hoạt tài khoản để sửa dụng chức năng này!");
//                                    return;
//                                }
                                synchronized (player.clan) {
                                    if (player.clan.snakeRoad == null) {
                                        int level = Byte.parseByte(
                                                String.valueOf(PLAYERID_OBJECT.get(player.id)));
                                        SnakeRoad road = new SnakeRoad(level);
                                        ServerManager.gI().getDungeonManager().addDungeon(road);
                                        road.join(player);
                                        player.clan.snakeRoad = road;
                                    } else {
                                        player.clan.snakeRoad.join(player);
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }
    }

}
