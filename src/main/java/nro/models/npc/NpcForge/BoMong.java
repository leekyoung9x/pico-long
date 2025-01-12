/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.consts.ConstTask;
import nro.dialog.MenuDialog;
import nro.dialog.MenuRunable;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.TaskService;
import nro.services.func.Input;
import nro.utils.Util;

/**
 * @author Arriety
 */
public class BoMong extends Npc {

    public BoMong(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (this.mapId == 47 || this.mapId == 84) {
//                this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, cậu muốn tôi giúp gì?",
//                        "Nhiệm vụ\nhàng ngày", "Nhận ngọc\nmiễn phí", "Quy đổi điểm\nnăng động", "Từ chối");
                // ekko bỏ tab nhận ngọc miễn phí
                this.createOtherMenu(player, ConstNpc.BASE_MENU, "Xin chào, cậu muốn tôi giúp gì?",
                        "Nhiệm vụ\nhàng ngày", "Quy đổi điểm\nnăng động", "Bỏ qua\n nhiệm vụ\nTĐST", "Từ chối");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (this.mapId == 47 || this.mapId == 84) {
                if (player.iDMark.isBaseMenu()) {
                    switch (select) {
                        case 0:
                            if (player.playerTask.sideTask.template != null) {
                                String npcSay = "Nhiệm vụ hiện tại: "
                                        + player.playerTask.sideTask.getName() + " ("
                                        + player.playerTask.sideTask.getLevel() + ")"
                                        + "\nHiện tại đã hoàn thành: "
                                        + player.playerTask.sideTask.count + "/"
                                        + player.playerTask.sideTask.maxCount + " ("
                                        + player.playerTask.sideTask.getPercentProcess()
                                        + "%)\nSố nhiệm vụ còn lại trong ngày: "
                                        + player.playerTask.sideTask.leftTask + "/"
                                        + ConstTask.MAX_SIDE_TASK;
                                this.createOtherMenu(player, ConstNpc.MENU_OPTION_PAY_SIDE_TASK,
                                        npcSay, "Trả nhiệm\nvụ", "Hủy nhiệm\nvụ");
                            } else {
                                this.createOtherMenu(player, ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK,
                                        "Tôi có vài nhiệm vụ theo cấp bậc, "
                                                + "sức cậu có thể làm được cái nào?",
                                        "Dễ", "Bình thường", "Khó", "Siêu khó", "Từ chối");
                            }
                            break;
                        // ekko
//                        case 1:
//                            this.npcChat(player, "Bảo trì");
//                            break;
                        case 1:
//                            Service.getInstance().sendThongBao(player, "Chức năng tạm khóa");
                            if (player.nPoint.nangdong < 1) {
                                Service.getInstance().sendThongBao(player, "Bạn không đủ điểm năng động");
                            }
                            else {
                                Input.gI().doiDiemNangDong(player);
                            }
                            break;
                        case 2: {
                            MenuDialog menu = new MenuDialog("Ngươi muốn ta giúp vượt qua nhiệm vụ này ư?\nNgươi sẽ phải tốn 200 điểm năng động đấy!", new String[]{"Có", "Đéo"}, new MenuRunable() {
                                @Override
                                public void run() {
                                    switch (getIndexSelected()) {
                                        case 0:
                                            if (player.nPoint.nangdong < 200) {
                                                Service.getInstance().sendThongBao(player, "Bạn không có đủ 200 điểm năng động");
                                                return;
                                            }
                                            if (player.playerTask.taskMain.id != 20) {
                                                Service.getInstance().sendThongBao(player, "Nhiệm vụ hiện tại của bạn đang là nhiệm vụ tiểu đội sát thủ");
                                                return;
                                            }

                                            player.nPoint.addNangDong(-200);
                                            Service.getInstance().point2(player);
                                            Service.getInstance().sendThongBao(player, "Bạn hiện còn "
                                                    + Util.numberToMoney(player.nPoint.nangdong) + "  năng động") ;
                                            TaskService.gI().sendNextTaskMain(player, 21);
                                            break;
                                        case 1:
                                            break;
                                    }
                                }
                            });
                            menu.show(player);
                            break;
                        }
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_LEVEL_SIDE_TASK) {
                    switch (select) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                            TaskService.gI().changeSideTask(player, (byte) select);
                            break;
                    }
                } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_PAY_SIDE_TASK) {
                    switch (select) {
                        case 0:
                            TaskService.gI().paySideTask(player);
                            break;
                        case 1:
                            TaskService.gI().removeSideTask(player);
                            break;
                    }
                }
            }
        }
    }
}
