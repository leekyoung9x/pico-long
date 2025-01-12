package nro.models.npc.NpcForge;

import nro.consts.ConstCombine;
import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.Service;
import nro.services.func.CombineServiceNew;
import nro.services.func.ShopService;

public class CayNeu extends Npc {
    public CayNeu(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            createOtherMenu(player, ConstNpc.BASE_MENU,
                    "Chúc mừng năm mới! Kính chúc bạn và gia đình một năm mới an khang, thịnh vượng, sức khỏe dồi dào, vạn sự như ý và luôn tràn đầy hạnh phúc! Mong rằng năm mới sẽ mang đến nhiều may mắn, niềm vui, và thành công cho bạn!",
                    "Đổi quà\nTết", "Pha lê hóa\nVòng thiên sứ", "Chế tạo\nBánh quy", "Chế tạo\nKẹo giáng sinh", "Nâng cấp \nCải trang\nhóa xương");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {
//                    case 0: {
//                        int quantity = 1;
//                        changeCard(player, quantity);
//                        break;
//                    }
//                    case 1: {
//                        int quantity = 10;
//                        changeCard(player, quantity);
//                        break;
//                    }
                    case 0:
                        ShopService.gI().openShopSpecial(player, this, 28, 0, -1);
                        break;
                    case 1:
                        this.createOtherMenu(player, ConstNpc.PHA_LE_HOA_VONG_THIEN_SU,
                                "Ngươi có muốn pha lê hóa vòng thiên sứ không ?", "Một Lần", "Từ chối");
                        break;
                    case 2:
                        this.createOtherMenu(player, ConstNpc.CHE_TAO_BANH_QUY,
                                "Ngươi có muốn chế tạo bánh quy không ?", "Một Lần", "Từ chối");
                        break;
                    case 3:
                        this.createOtherMenu(player, ConstNpc.CHE_TAO_KEO_GIANG_SINH,
                                "Ngươi có muốn chế tạo kẹo giáng sinh không ?", "Một Lần", "Từ chối");
                        break;
                    case 4:
                        this.createOtherMenu(player, ConstNpc.NANG_CAP_CAI_TRANG_HOA_XUONG,
                                "Ngươi có muốn nâng cấp cải trang không ?", "Một Lần", "Từ chối");
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.PHA_LE_HOA_VONG_THIEN_SU) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.PHA_LE_HOA_VONG_THIEN_SU);
                        break;
                    default:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.CHE_TAO_BANH_QUY) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.CHE_TAO_BANH_QUY);
                        break;
                    default:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.CHE_TAO_KEO_GIANG_SINH) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.CHE_TAO_KEO_GIANG_SINH);
                        break;
                    default:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.NANG_CAP_CAI_TRANG_HOA_XUONG) {
                switch (select) {
                    case 0:
                        CombineServiceNew.gI().openTabCombine(player,
                                CombineServiceNew.NANG_CAP_CAI_TRANG_HOA_XUONG);
                        break;
                    default:
                        break;
                }
            } else if (player.iDMark.getIndexMenu() == ConstNpc.MENU_START_COMBINE) {
                switch (player.combineNew.typeCombine) {
                    case CombineServiceNew.PHA_LE_HOA_VONG_THIEN_SU:
                    case CombineServiceNew.CHE_TAO_BANH_QUY:
                    case CombineServiceNew.CHE_TAO_KEO_GIANG_SINH:
                    case CombineServiceNew.NANG_CAP_CAI_TRANG_HOA_XUONG:
                        if (select == 0) {
                            CombineServiceNew.gI().startCombine(player);
                        }
                        break;
                }
            }
        }
    }

    private static void changeCard(Player player, int quantity) {
        Item card = InventoryService.gI().findItemBagByTemp(player, ConstItem.PET_MEO_DEN_DUOI_VANG);

        if (card == null || card.quantity < quantity) {
            Service.getInstance().sendThongBao(player, "Bạn không có đủ " + quantity + " thiệp chúc tết!");
            return;
        }

        InventoryService.gI().subQuantityItemsBag(player, card, quantity);
        InventoryService.gI().sendItemBags(player);

        player.diem_skien += quantity;

        Service.getInstance().sendThongBao(player, "Bạn nhận được " + quantity + " điểm sự kiện tết");
    }
}
