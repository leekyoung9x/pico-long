/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstItemName;
import nro.consts.ConstMap;
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.ExcellentBook;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.services.func.CombineServiceNew;

import static nro.services.func.CombineServiceNew.UPGRADE_THAN_LINH;

import nro.services.func.ShopService;
import nro.utils.Util;

import java.time.LocalTime;
import java.util.Calendar;

/**
 * @author Arriety
 */
public class duongtank extends Npc {

    public duongtank(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 0:
                    // ekko tạm đóng shop đồ chay
//                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "A di phò phò",
//                            "Đến ngũ\nhành sơn","Shop\nĐồ Chay","Nâng cấp\n Chỉ số SKH\nBằng Thỏi Vàng [VIP]","Nâng cấp\n Chỉ số SKH\nBằng Hồng ngọc"
//                    );
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "A di phò phò",
                            "Đến ngũ\nhành sơn", "Nâng cấp\n Chỉ số SKH\nBằng " + ConstItemName.MONEY_UNIT, "Nâng cấp\n Chỉ số SKH\nBằng Hồng ngọc"
                    );
                    break;
                case 121:
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi tìm ta có việc gì?",
                            "Về đảo\nrùa");
                    break;
                case 124:
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Con muốn làm gì",
                            "Về nhà");
                    break;
                default:

                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "A di đà phò, ผู้บริจาคเป็นเกย์หรือไม่?",
                            "Tặng quả đào"

                    );
                    break;
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 124:
                    Service.gI().sendThongBao(player, "Mày đã bị nhốt");
                    break;
                case 0:
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
//                            case 0:
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.EP_SAO_TRANG_BI);
//                                break;
//                            case 1:
//                                this.createOtherMenu(player, ConstNpc.MENU_PHA_LE_HOA_TRANG_BI,
//                                        "Ngươi muốn pha lê hóa trang bị bằng cách nào?", "Một Lần", "x100 Lần", "Từ chối");
//                                break;
//                            case 2:
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.UPDATE_TRAIN_ARMOR);
//                                break;
//                            case 3:
//                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.UPGRADE_THAN_LINH);
//                                break;
//                            case 4:
//                                this.createOtherMenu(player, ConstNpc.MENU_INVENTORY,
//                                        "Con có muốn nâng thêm 1 ô hành trang??\nVới tỉ lệ là 50% nâng - 1 lần nâng sẽ là 100k ngọc xanh", "Đồng ý", "Từ chối");
//                                break;
//                            case 5://
//                                this.createOtherMenu(player, ConstNpc.MENU_START_UPDATE,
//                                        "Ngươi muốn nâng cấp viên đá nâng cấp bằng phương thức nào", "20k ruby", "5k coint", "Từ chối");
//                                break;
                            // ekko tạm đóng shop đồ chay
//                                        case 1:
//                                            ShopService.gI().openShopSpecial(player, this, ConstNpc.SHOP_VIP2, 0, -1);
//                                            break;
                            case 0:
                                LocalTime now = LocalTime.now();
                                LocalTime start = ConstMap.start;
                                LocalTime end = ConstMap.end;
                                // admin thì cho vô map ngũ hành sơn
                                if ((now.isBefore(start) || now.isAfter(end)) && !player.isAdmin()) {
                                    Service.gI().sendThongBao(player, "Ngũ hành sơn sẽ mở vào " + start.getHour() + "h mỗi ngày ");

                                } else {
                                    ChangeMapService.gI().changeMapInYard(player, 124, -1, Util.nextInt(300, 500));
                                }
                                break;
                            case 1:
                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_SKH_NEW);
                                break;
                            case 2:
                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_SKH_NEW_RUBY);
                                break;

                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_INVENTORY) {
                        switch (select) {
                            case 0:
                                upgradeInventory(player);
                                break;
                            case 1:
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_UPDATE) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.UPGRADE_POWER_STONE);
                                break;
                            case 1:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.UPGRADE_POWER_STONE_COINT);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.UPGRADE_POWER_STONE:
                                CombineServiceNew.gI().startCombineStone(player, CombineServiceNew.UPGRADE_POWER_STONE);
                                break;
                            case CombineServiceNew.UPGRADE_POWER_STONE_COINT:
                                CombineServiceNew.gI().startCombineStone(player, CombineServiceNew.UPGRADE_POWER_STONE_COINT);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHA_LE_HOA_TRANG_BI) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.PHA_LE_HOA_TRANG_BI);
                                break;
                            case 1:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.PHA_LE_HOA_TRANG_BI_X10);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.EP_SAO_TRANG_BI:
                            case CombineServiceNew.PHA_LE_HOA_TRANG_BI:
                            case CombineServiceNew.PHA_LE_HOA_TRANG_BI_X10:
                            case CombineServiceNew.UPDATE_TRAIN_ARMOR:
                            case CombineServiceNew.UPGRADE_THAN_LINH:
                            case CombineServiceNew.UPGRADE_HUY_DIET:
                            case CombineServiceNew.NANG_SKH_NEW:
                            case CombineServiceNew.NANG_SKH_NEW_RUBY:
                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    }
                    break;
                case 112:
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                ChangeMapService.gI().changeMapBySpaceShip(player, 5, -1, 1156);
                                break;
                        }
                    }
                    break;
                case 42:
                case 43:
                case 122:
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0: // shop bùa
                                // ekko
//                                            Item i2 = InventoryService.gI().findItemBagByTemp(player, 541);
//                                            if (i2 == null) {
//                                                this.npcChat(player, "Bạn không đủ x99 quả đào");
//                                                return;
//                                            }
//                                            if (i2.quantity <= 99) {
//                                                this.npcChat(player, "Bạn không đủ x99 quả đào");
//                                                return;
//
//                                            }
//                                            Item thoivip = ItemService.gI().createNewItem((short) 542, 1);
//                                            InventoryService.gI().subQuantityItemsBag(player, i2, 99);
//
//                                            InventoryService.gI().addItemBag(player, thoivip, 9999);
//                                            InventoryService.gI().sendItemBags(player);
//                                            this.npcChat(player, "Đổi quả đào chín thành công");
                                this.npcChat(player, "Shop bùa tạm đóng con ạ !");

                                break;
//                            case 1:
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.NANG_CAP_VAT_PHAM);
//                                break;
//                            case 2: // nâng cấp bông tai
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.NANG_CAP_BONG_TAI);
//                                break;
//                            case 3: // làm phép nhập đá
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.MO_CHI_SO_BONG_TAI);
//                                break;
//                            case 4: // nâng cấp bông tai 3
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.NANG_CAP_BONG_TAI_CAP3);
//                                break;
//                            case 5: //3
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.DAP_BONG_TAI_CAP_3);
//                                break;
//                            case 6:
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.NHAP_NGOC_RONG);
//                            case 7:
//                                createOtherMenu(player, ConstNpc.INDEX_MENU_SACH_TUYET_KY,
//                                        "Ta có thể giúp gì cho ngươi",
//                                        "Đóng thành\nSách cũ", "Đổi sách\nTuyệt kỹ", "Giám định\nSách", "Tẩy\nSách", "Nâng cấp\nSách\nTuyệt kỹ", "Hồi phục\nSách", "Phân rã\nSách");
//                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_OPTION_SHOP_BUA) {
                        switch (select) {
                            case 0:
                                ShopService.gI().openShopBua(player, ConstNpc.SHOP_BA_HAT_MIT_0, 0);
                                break;
                            case 1:
                                ShopService.gI().openShopBua(player, ConstNpc.SHOP_BA_HAT_MIT_1, 1);
                                break;
                            case 2:
                                ShopService.gI().openShopBua(player, ConstNpc.SHOP_BA_HAT_MIT_2, 2);
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.NANG_CAP_VAT_PHAM:
                            case CombineServiceNew.NANG_CAP_BONG_TAI:
                            case CombineServiceNew.MO_CHI_SO_BONG_TAI:
                            case CombineServiceNew.LAM_PHEP_NHAP_DA:
                            case CombineServiceNew.NANG_CAP_BONG_TAI_CAP3:
                            case CombineServiceNew.DAP_BONG_TAI_CAP_3:
                            case CombineServiceNew.NHAP_NGOC_RONG:
                            case CombineServiceNew.GIAM_DINH:
                            case CombineServiceNew.TAY_SACH:
                            case CombineServiceNew.NANG_SACH:
                            case CombineServiceNew.PHUC_HOI:
                            case CombineServiceNew.PHAN_RA:
                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_SACH_TUYET_KY) {
                        ExcellentBook.handleExcellentBook(player, select);
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_CLOSE_OLD_BOOK) {
                        ExcellentBook.closeOldBook(player, select);
                    } else if (player.iDMark.getIndexMenu() == ConstNpc.INDEX_MENU_CHANGE_OLD_BOOK) {
                        ExcellentBook.changeOldBook(player, select);
                    }
                    break;
                default:
                    break;
            }
        }
    }


    private void upgradeInventory(Player player) {
        if (player.inventory.itemsBody.size() > 12) {
            this.npcChat(player, "Con đã mở max ô");
            return;
        }
        if (player.inventory.gem < 100_000) {
            this.npcChat(player, "Con thiếu 100k ngọc xanh để thực hiện");
            return;
        }
        if (Util.isTrue(50, 100)) {
            player.inventory.itemsBody.add(ItemService.gI().createItemNull());
            Service.getInstance().player(player);
            this.npcChat(player, "Con đã nâng cấp thành công !!!");
        } else {
            this.npcChat(player, "Con đã nâng cấp xịt rồi con zai của ta :Đ");
        }
        player.inventory.subGem(100_000);
        Service.getInstance().sendMoney(player);
    }

}

