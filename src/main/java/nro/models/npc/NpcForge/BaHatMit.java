/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstCombine;
import nro.consts.ConstNpc;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.ExcellentBook;
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
public class BaHatMit extends Npc {

    public BaHatMit(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 5:
                    // ekko rem
//                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "Ngươi tìm ta có việc gì?",
//                            "Ép sao\ntrang bị",
//                            "Pha lê\nhóa\ntrang bị",
//                            "Nâng cấp\nGiáp Luyện Tập",
//                            "Nâng cấp\nSet kích hoạt",
//                            "Nâng ô\nhành trang",
//                            "Nâng cấp\nđá sức mạnh",
//                            "Nâng cấp\n Hủy Diệt"
//                    );
//                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "Ngươi tìm ta có việc gì?",
//                            "Ép sao\ntrang bị",
//                            "Pha lê\nhóa\ntrang bị",
//                            "Tẩy sao \npha lê",
//                            "Pha lê\nhóa\ncải trang",
//                            "Nâng cấp\n chân quang",
//                            "Khảm bùa",
//                            "Ép ngọc\n hắc ám"
////                            "Chuyển hoá\nSKH\nHUỶ DIỆT"
//                    );
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi tìm ta có việc gì?",
                            "Ép sao\ntrang bị",
                            "Pha lê\nhóa\ntrang bị",
                            "Tẩy sao \npha lê",
                            "Pha lê\nhóa\ncải trang"
                    );
                    break;
                case 121:
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi tìm ta có việc gì?",
                            "Về đảo\nrùa");
                    break;
                default:
                    // ekko
//                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
//                            "Ngươi tìm ta có việc gì?",
//                            "Cửa hàng\nBùa",
//                            "Nâng cấp\nVật phẩm",
//                            "Nâng cấp\nBông tai\nPorata",
//                            "Nâng cấp\nChỉ số\nBông tai",
//                            "Nâng cấp\nBông tai\nPorata 3",
//                            "Nâng cấp\nChỉ số\nBông tai 3",
//                            "Nhập\nNgọc Rồng",
//                            "Sách\nTuyệt kỹ"
//                    );
                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                            "Ngươi tìm ta có việc gì?",
                            "Cửa hàng\nBùa",
                            "Nâng cấp\nVật phẩm",
                            "Nâng cấp\nBông tai\nPorata",
                            "Nâng cấp\nChỉ số\nBông tai",
                            "Nâng cấp\nBông tai\nPorata 3",
                            "Nâng cấp\nChỉ số\nBông tai 3",
                            "Nhập\nNgọc Rồng",
                            "Sách\nTuyệt kỹ"
                    );
                    break;
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 5:
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.EP_SAO_TRANG_BI);
                                break;
                            case 1:
                                this.createOtherMenu(player, ConstNpc.MENU_PHA_LE_HOA_TRANG_BI,
                                        "Ngươi muốn pha lê hóa trang bị bằng cách nào?", "Một Lần", "x100 Lần", "Từ chối");
                                break;
                            case 2:
                                this.createOtherMenu(player, ConstCombine.REMOVE_OPTION,
                                "Ngươi có muốn tẩy sao pha lê cho trang bị", "Có", "Từ chối");
                                break;
                            case 3:
                                this.createOtherMenu(player, ConstNpc.MENU_PHA_LE_HOA_CAI_TRANG,
                                        "Ngươi muốn pha lê hóa cải trang bằng cách nào?", "Một Lần", "Từ chối");
                                break;
//                            case 4:
//                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CHAN_MENH);
//                                break;
//                            case 5:
//                                this.createOtherMenu(player, ConstNpc.KHAM_BUA,
//                                        "Ngươi có muốn khảm bùa không?\n Để có thể khảm bùa yêu cầu tối thiểu phải có bùa để khảm\n Mỗi lần khảm bùa tốn x10 bùa cùng loại với tỷ lệ thành công là 10%.", "Đồng ý", "Từ chối");
//                                break;
//                            case 6:
//                                CombineServiceNew.gI().openTabCombine(player,
//                                        CombineServiceNew.EP_NGOC_HAC_AM);
//                                break;
//                            case 5:
//                                this.createOtherMenu(player, ConstNpc.CHUYEN_HOA_SKH_HUY_DIET,
//                                        "Ngươi có muốn chuyển hóa SKH Thần thành SKH Hủy Diệt không ?\n Để có thể chuyển hoá yêu cầu tối thiểu phải có SKH thần\n Mỗi lần chuyển hoá tốn 10k thỏi vàng VIP với tỷ lệ thành công là 5%.", "Đồng ý", "Từ chối");
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
//                            case 6:
//                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.UPGRADE_HUY_DIET);
//                                break;
//                            case 7:
//                                CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_SKH_NEW);
//                                break;
//                            case 8:
//                                LocalTime now = LocalTime.now();
//                                LocalTime start = LocalTime.of(17, 0, 0); // 17:00:00
//                                LocalTime end = LocalTime.of(18, 59, 59); // 18:59:59
//                                if (now.isBefore(start) || now.isAfter(end)) {
//                                    Service.gI().sendThongBao(player, "Ngũ hành sơn bắt đầu mở vào lúc 17h");
//
//                                } else {
//                                    ChangeMapService.gI().changeMap(player, 212, -1, 333, 123);
//                                }
//                                break;
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
                    }
                    else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_PHA_LE_HOA_CAI_TRANG) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.PHA_LE_HOA_CAI_TRANG);
                                break;
                        }
                    }
                    else if (player.iDMark.getIndexMenu() == ConstNpc.CHUYEN_HOA_SKH_HUY_DIET) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.CHUYEN_HOA_SKH_HUY_DIET);
                                break;
                        }
                    }
                    else if (player.iDMark.getIndexMenu() == ConstNpc.KHAM_BUA) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.KHAM_BUA);
                                break;
                        }
                    }
                    else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                        switch (player.combineNew.typeCombine) {
                            case CombineServiceNew.EP_SAO_TRANG_BI:
                            case CombineServiceNew.PHA_LE_HOA_TRANG_BI:
                            case CombineServiceNew.PHA_LE_HOA_TRANG_BI_X10:
                            case CombineServiceNew.PHA_LE_HOA_CAI_TRANG:
                            case CombineServiceNew.UPDATE_TRAIN_ARMOR:
                            case CombineServiceNew.UPGRADE_THAN_LINH:
                            case CombineServiceNew.UPGRADE_HUY_DIET:
                            case CombineServiceNew.NANG_SKH_NEW:
                            case ConstCombine.REMOVE_OPTION:
                            case CombineServiceNew.NANG_CHAN_MENH:
                            case CombineServiceNew.CHUYEN_HOA_SKH_HUY_DIET:
                            case CombineServiceNew.KHAM_BUA:
                            case CombineServiceNew.EP_NGOC_HAC_AM:
                                if (select == 0) {
                                    CombineServiceNew.gI().startCombine(player);
                                }
                                break;
                        }
                    } else if (player.iDMark.getIndexMenu() == ConstCombine.REMOVE_OPTION) {
                        switch (select) {
                            case 0:
                                CombineServiceNew.gI().openTabCombine(player,
                                        ConstCombine.REMOVE_OPTION);
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
                case 44:
                    if (player.iDMark.isBaseMenu()) {
                        switch (select) {
                            // ekko
                            case 0: // shop bùa
                                createOtherMenu(player, ConstNpc.MENU_OPTION_SHOP_BUA,
                                        "Bùa của ta rất lợi hại, nhìn ngươi yếu đuối thế này, chắc muốn mua bùa để "
                                                + "mạnh mẽ à, mua không ta bán cho, xài rồi lại thích cho mà xem.",
                                        "Bùa\n1 giờ", "Bùa\n8 giờ", "Bùa\n1 tháng", "Đóng");
                                break;
                            case 1:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.NANG_CAP_VAT_PHAM);
                                break;
                            case 2: // nâng cấp bông tai
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.NANG_CAP_BONG_TAI);
                                break;
                            case 3: // làm phép nhập đá
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.MO_CHI_SO_BONG_TAI);
                                break;
                            case 4: // nâng cấp bông tai 3
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.NANG_CAP_BONG_TAI_CAP3);
                                break;
                            case 5: // Nâng cấp Chỉ số bông tai 3
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.DAP_BONG_TAI_CAP_3);
                                break;
                            case 6:
                                CombineServiceNew.gI().openTabCombine(player,
                                        CombineServiceNew.NHAP_NGOC_RONG);
                                break;
                            case 7:
                                createOtherMenu(player, ConstNpc.INDEX_MENU_SACH_TUYET_KY,
                                        "Ta có thể giúp gì cho ngươi",
                                        "Đóng thành\nSách cũ", "Đổi sách\nTuyệt kỹ", "Giám định\nSách", "Tẩy\nSách", "Nâng cấp\nSách\nTuyệt kỹ", "Hồi phục\nSách", "Phân rã\nSách");
                                break;
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
