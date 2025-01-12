/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.consts.ConstPet;
import nro.models.npc.Npc;
import nro.models.player.NPoint;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.OpenPowerService;
import nro.services.Service;
import nro.services.func.CombineServiceNew;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class ToSu extends Npc {

    public ToSu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        // ekko
//        this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                "Con muốn nâng giới hạn sức mạnh cho đệ tử?", "Đệ tử", "Nâng Lever\nMabu Nhí","Nâng cấp\n chân quang","Nâng cấp\n nhẫn thời không",
//                "Từ chối");
        this.createOtherMenu(player, ConstNpc.BASE_MENU,
                "Con muốn nâng giới hạn sức mạnh cho đệ tử?",
                "Từ chối");
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                // ekko
//                switch (select) {
//                    case 0:
//                        if (player.pet != null) {
//                            if (player.pet.nPoint.limitPower < NPoint.MAX_LIMIT) {
//                                this.createOtherMenu(player, ConstNpc.OPEN_POWER_PET,
//                                        "Ta sẽ truền năng lượng giúp con mở giới hạn sức mạnh của đệ tử lên "
//                                        + Util.numberToMoney(
//                                                player.pet.nPoint.getPowerNextLimit()),
//                                        "Nâng ngay\n" + Util.numberToMoney(
//                                                OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER)
//                                        + " vàng",
//                                        "Đóng");
//                            } else {
//                                this.createOtherMenu(player, ConstNpc.IGNORE_MENU,
//                                        "Sức mạnh của đệ con đã đạt tới giới hạn", "Đóng");
//                            }
//                        } else {
//                            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
//                        }
//                        break;
//                    case 1:
//                        long powerSub = 100_000_000_000L;
//
//                        if (player.pet == null) {
//                            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Mabư Nhí");
//                            return;
//                        }
//                        if (player.pet.typePet != ConstPet.MABU_NHI) {
//                            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Mabư Nhí");
//                            return;
//                        }
//                        if (player.pet.getLever() >= 7) {
//                            Service.getInstance().sendThongBaoOK(player, "Ta chỉ giúp con nâng lever đệ tử con lên lever 7");
//                            return;
//                        }
//                        if (player.pet.getLever() < 5) {
//                            Service.getInstance().sendThongBaoOK(player, "Đệ con đã lever 5 đâu mà đến đây");
//                            return;
//                        }
//                        if (player.pet.nPoint.power < powerSub) {
//                            Service.getInstance().sendThongBaoOK(player, "Đệ con thiếu 100 tỷ sức mạnh để được nâng cấp");
//                            return;
//                        }
//                        player.pet.nPoint.power -= powerSub;
//                        player.pet.setLever(player.pet.getLever() + 1);
//                        InventoryService.gI().sendItemBags(player);
//                        Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
//                        break;
//                    case 2:
//
////                        Service.getInstance().sendThongBao(player, "Tạm đóng để xử lí mấy thằng cu bug");
//                        CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CHAN_MENH);
//                        break;
//                    case 3:
//                        CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_NHAN);
//                        break;
//                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.OPEN_POWER_PET) {
                if (select == 0) {
                    if (player.inventory.gold >= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER) {
                        if (OpenPowerService.gI().openPowerSpeed(player.pet)) {
                            player.inventory.gold -= OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER;
                            Service.getInstance().sendMoney(player);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player,
                                "Bạn không đủ vàng để mở, còn thiếu " + Util
                                        .numberToMoney((OpenPowerService.COST_SPEED_OPEN_LIMIT_POWER
                                                - player.inventory.gold))
                                + " vàng");
                    }
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                switch (player.combineNew.typeCombine) {
                    case CombineServiceNew.NANG_CAP_NHAN:
                    case CombineServiceNew.NANG_CHAN_MENH:

                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }

                        break;
                }
            }
        }
    }
}
