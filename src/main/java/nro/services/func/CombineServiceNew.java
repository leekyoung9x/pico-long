package nro.services.func;

import nro.consts.*;
import nro.lib.RandomCollection;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.npc.NpcManager;
import nro.models.player.Player;
import nro.art.ServerLog;
import nro.server.Manager;
import nro.server.ServerNotify;
import nro.server.io.Message;
import nro.services.*;
import nro.utils.ItemUtil;
import nro.utils.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import nro.models.mob.ArrietyDrop;

import static nro.consts.ConstCombine.REMOVE_OPTION;

/**
 * @Build Arriety
 */
public class CombineServiceNew {

    private static final int COST_DOI_VE_DOI_DO_HUY_DIET = 500000000;
    private static final int COST_DAP_DO_KICH_HOAT = 20_000;
    private static final int COST_DAP_DO_KICH_HOAT_LINH_THU = 10_000;
    private static final int COST_LEVER = 5000;
    private static final int COST_DOI_MANH_KICH_HOAT = 500000000;
    private static final int COST_GIA_HAN_CAI_TRANG = 500000000;

    private static final int COST_NANG_DO_TS = 1_000_000_000;
    private static final int COST_RUBY_NANG_DO_TS = 15_000;

    private static final int COST_RUBY_TRADE_TL = 5_000;

    private static final int TIME_COMBINE = 500;

    private static final byte MAX_STAR_ITEM = 9;
    public static final int UPGRADE_HUY_DIET = 5401;
    private static final byte MAX_SAO_FLAG_BAG = 5;

    private static final byte MAX_LEVEL_ITEM = 7;

    private static final byte OPEN_TAB_COMBINE = 0;
    private static final byte REOPEN_TAB_COMBINE = 1;
    private static final byte COMBINE_SUCCESS = 2;
    private static final byte COMBINE_FAIL = 3;
    private static final byte COMBINE_DRAGON_BALL = 5;
    public static final byte OPEN_ITEM = 6;

    public static final int EP_SAO_TRANG_BI = 500;
    public static final int PHA_LE_HOA_TRANG_BI = 501;
    public static final int CHUYEN_HOA_TRANG_BI = 502;
    public static final int DOI_VE_HUY_DIET = 503;
    public static final int DAP_SET_KICH_HOAT = 504;
    public static final int DOI_MANH_KICH_HOAT = 505;
    public static final int NANG_CAP_VAT_PHAM = 506;
    public static final int NANG_CAP_BONG_TAI = 507;
    public static final int LAM_PHEP_NHAP_DA = 508;
    public static final int NHAP_NGOC_RONG = 509;
    public static final int CHE_TAO_DO_THIEN_SU = 510;
    public static final int DAP_SET_KICH_HOAT_CAO_CAP = 511;
    public static final int GIA_HAN_CAI_TRANG = 512;
    public static final int NANG_CAP_DO_THIEN_SU = 513;
    public static final int PHA_LE_HOA_TRANG_BI_X10 = 514;
    public static final int NANG_CAP_DO_TS = 515;
    public static final int TRADE_DO_THAN_LINH = 516;
    public static final int NANG_CAP_DO_KICH_HOAT = 517;
    public static final int UPGRADE_CAITRANG = 518;
    public static final int MO_CHI_SO_BONG_TAI = 519;
    public static final int UPGRADE_LINHTHU = 520;
    public static final int EP_NGOC_RONG_BANG = 521;
    public static final int UPGRADE_THAN_LINH = 522;
    public static final int UPGRADE_PET = 523;
    public static final int TRADE_PET = 524;
    public static final int PHA_LE_HOA_DISGUISE = 525;
    public static final int NANG_CAP_BONG_TAI_CAP3 = 526;
    public static final int DAP_BONG_TAI_CAP_3 = 527;
    public static final int PHA_LE_HOA_CAI_TRANG = 528;
    public static final int UPGRADE_PET_CELL = 529;
    public static final int CHUYEN_HOA_TRANG_BI_6S = 530;
    public static final int NANG_CAP_DE_WHIS = 531;
    public static final int UPDATE_TRAIN_ARMOR = 532;
    public static final int GIAM_DINH = 533;
    public static final int TAY_SACH = 534;
    public static final int NANG_SACH = 535;
    public static final int PHUC_HOI = 536;
    public static final int PHAN_RA = 537;
    public static final int UPGRADE_POWER_STONE_COINT = 538;
    public static final int UPGRADE_POWER_STONE = 539;
    public static final int NANG_CAP_NHAN = 540;
    public static final int PHAN_RA_SKH_VIP = 541;
    public static final int CHUYEN_HOA_SKH_HUY_DIET = 542;
    public static final int KHAM_BUA = 543;
    public static final int EP_NGOC_HAC_AM = 544;
    public static final int PHA_LE_HOA_VONG_THIEN_SU = 545;
    public static final int CHE_TAO_BANH_QUY = 546;
    public static final int CHE_TAO_KEO_GIANG_SINH = 547;
    public static final int NANG_CAP_CAI_TRANG_HOA_XUONG = 548;
    public static final int NANG_CAP_SKH = 549;
    public static final int NANG_CAP_PET = 550;
    public static final int DOI_DA_THAN_LINH = 551;
    private static final int GOLD_MOCS_BONG_TAI = 500_000_000;

    private static final int RUBY_MOCS_BONG_TAI = 10_000;

    private static final int GOLD_BONG_TAI2 = 500_000_000;

    private static final int RUBY_BONG_TAI2 = 20_000;

    private static final int RATIO_NANG_CAP = 44;

    public static final int NANG_CAP_CAI_TRANG = 2088;
    public static final int NANG_CHAN_MENH = 2089;

    public static final int NANG_SKH_NEW = 2090;
    public static final int NANG_SKH_NEW_RUBY = 2091;
    private final Npc baHatMit;
    private final Npc cayNeu;
    private final Npc lyTieuNuong;


    private final Npc bulmatl;
    private final Npc whis;
    private final Npc tosu;
    private final Npc quyLaoKame;

    private final Npc duongtank;
    private static CombineServiceNew i;

    public CombineServiceNew() {
        this.baHatMit = NpcManager.getNpc(ConstNpc.BA_HAT_MIT);
        this.whis = NpcManager.getNpc(ConstNpc.WHIS);
        this.bulmatl = NpcManager.getNpc(ConstNpc.BUNMA_TL);
        this.tosu = NpcManager.getNpc(ConstNpc.TO_SU_KAIO);
        this.quyLaoKame = NpcManager.getNpc(ConstNpc.QUY_LAO_KAME);
        this.duongtank = NpcManager.getNpc(ConstNpc.DUONG_TANG);
        this.cayNeu = NpcManager.getNpc(ConstNpc.CAY_NEU);
        this.lyTieuNuong = NpcManager.getNpc(ConstNpc.LY_TIEU_NUONG);
    }
    public static CombineServiceNew gI() {
        if (i == null) {
            i = new CombineServiceNew();
        }
        return i;
    }

    /**
     * Mở tab đập đồ
     *
     * @param player
     * @param type   kiểu đập đồ
     */
    public void openTabCombine(Player player, int type) {
        player.combineNew.setTypeCombine(type);
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_TAB_COMBINE);
            msg.writer().writeUTF(getTextInfoTabCombine(type));
            msg.writer().writeUTF(getTextTopTabCombine(type));
            if (player.iDMark.getNpcChose() != null) {
                msg.writer().writeShort(player.iDMark.getNpcChose().tempId);
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float getRationangbt(int lvbt) { // tile dap do chi hat mit
        switch (lvbt) {
            case 1:
                return 40f;

        }
        return 0;
    }

    /**
     * Hiển thị thông tin đập đồ
     *
     * @param player
     */
    public void showInfoCombine(Player player, int[] index) {
        player.combineNew.clearItemCombine();
        if (index.length > 0) {
            for (int i = 0; i < index.length; i++) {
                player.combineNew.itemsCombine.add(player.inventory.itemsBag.get(index[i]));
            }
        }
        switch (player.combineNew.typeCombine) {
            case NANG_CAP_NHAN:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item nhan = null;
                    Item coin = null;
                    ItemOption optionLevel_93 = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id == ConstItem.THOI_VANG_VIP) {
                            coin = item;
                        } else if (item.template.id == 2110) {
                            nhan = item;
                        }
                    }
                    if (nhan != null && nhan.template.id == 2110 && coin != null) {
                        for (ItemOption io : nhan.itemOptions) {
                            if (io.optionTemplate.id == 93) {
                                optionLevel_93 = io;
                                break;
                            }
                        }
                        int level = 0;
                        for (ItemOption io : nhan.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        String npcSay = "|2|Nâng Cấp " + nhan.template.name + " (+" + level + ")\n";
                        npcSay += "|7|Cần 10k coin";
                        if (level > 6) {
                            this.tosu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Đã đạt cấp tối đa!!", "Đóng");
                            return;
                        }
                        if (coin.quantity < 10000) {
                            this.tosu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 10k coin!!", "Đóng");
                            return;
                        }
                        if (optionLevel_93 != null) {
                            this.tosu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hàng nhái không thể nâng", "Đóng");
                            return;
                        }
                        this.tosu.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp\ntốn 10k coin ");
                    } else {
                        this.tosu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Nhẫn Thời Không và 10k coin", "Đóng");
                    }
                } else {
                    this.tosu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Nhẫn Thời Không và 10k coin", "Đóng");
                }
                break;
            case GIAM_DINH: {
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item sachtk = null;
                    Item buagiamdinh = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id >= 2071 && item.template.id <= 2073
                                || item.template.id >= 2075 && item.template.id <= 2077) {
                            sachtk = item;
                        } else if (item.template.id == 2070) {
                            buagiamdinh = item;
                        }
                    }
                    ItemOption optionLevel = null;
                    if (sachtk != null && buagiamdinh != null && buagiamdinh.quantity >= 1) {
                        for (ItemOption io : sachtk.itemOptions) {
                            if (io.optionTemplate.id == 236) {
                                optionLevel = io;
                                break;
                            }
                        }
                        String npcSay = "|2|Giám định " + sachtk.template.name + "\n";
                        npcSay += "|7|Cần 1 Bùa giám định";
                        Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                        if (pi == null || pi.quantity < 10_000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn thiếu 10k "  + ConstItemName.MONEY_UNIT, "Đóng");
                            return;
                        }
                        if (player.inventory.ruby < 10000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 10k hồng ngọc!!", "Đóng");
                            return;
                        }

                        if (optionLevel == null) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 Sách Tuyệt Kỹ chưa giám định", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Giám định\ncần 10k hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Sách Tuyệt Kỹ và 1 Bùa giám định", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần đặt vào những thứ cần thiết", "Đóng");
                }
                break;
            }
            case TAY_SACH: {
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachtk = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id >= 2071 && item.template.id <= 2073
                                || item.template.id >= 2075 && item.template.id <= 2077) {
                            sachtk = item;
                        }
                    }
                    int level_236 = 0;
                    int level_237 = 0;
                    ItemOption optionLevel_236 = null;
                    ItemOption optionLevel_237 = null;
                    if (sachtk != null) {
                        for (ItemOption io : sachtk.itemOptions) {
                            if (io.optionTemplate.id == 236) {
                                level_236 = io.param;
                                optionLevel_236 = io;
                                break;
                            }
                        }
                        for (ItemOption io : sachtk.itemOptions) {
                            if (io.optionTemplate.id == 237) {
                                level_237 = io.param;
                                optionLevel_237 = io;
                                break;
                            }
                        }

                        player.combineNew.gemCombine = 5000;
                        String npcSay = "|7|Tẩy sách " + sachtk.template.name + "\n";
                        if (player.inventory.ruby < player.combineNew.gemCombine) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 5k hồng ngọc!!", "Đóng");
                            return;
                        }
                        if (optionLevel_237 != null && level_237 < 1) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Đã hết số lần tẩy", "Đóng");
                            return;
                        }
                        if (optionLevel_236 != null) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 Sách Tuyệt Kỹ đã giám định", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Tẩy sách\ncần " + player.combineNew.gemCombine + " hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 Sách Tuyệt Kỹ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần đặt vào những thứ cần thiết", "Đóng");
                }
                break;
            }
            case NANG_SACH: {
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item sachtk = null;
                    Item kimbam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id >= 2071 && item.template.id <= 2073) {
                            sachtk = item;
                        } else if (item.template.id == 2069) {
                            kimbam = item;
                        }
                    }

                    Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                    if (pi == null || pi.quantity < 10_000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bạn thiếu 10k "  + ConstItemName.MONEY_UNIT, "Đóng");
                        return;
                    }
                    if (sachtk != null && kimbam != null && kimbam.quantity >= 10) {
                        player.combineNew.gemCombine = 2000;
                        String npcSay = "Nâng cấp " + sachtk.template.name + "\n|2|Cần 10 Kìm bấm giấy" + "\n";
                        npcSay += "|7|Tỉ lệ thành công: 20%"
                                + "\n Thành công sẽ tăng thêm 5% mỗi chỉ số đang có"
                                + "\n Riêng sức đánh và giáp chỉ tăng 3%";
                        if (player.inventory.ruby < player.combineNew.gemCombine) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 2k ngọc xanh!!", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc xanh");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Thiếu nguyên liệu rồi bạn ơi!", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Sách Tuyệt Kỹ bậc 1 và 10 Kìm bấm giấy", "Đóng");
                }
                break;
            }
            case PHUC_HOI: {
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachtk = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id >= 2071 && item.template.id <= 2073
                                || item.template.id >= 2075 && item.template.id <= 2077) {
                            sachtk = item;
                        }
                    }
                    int level_238 = 0;
                    ItemOption optionLevel_238 = null;
                    if (sachtk != null) {
                        for (ItemOption io : sachtk.itemOptions) {
                            if (io.optionTemplate.id == 238) {
                                level_238 = io.param;
                                optionLevel_238 = io;
                                break;
                            }
                        }
                        player.combineNew.gemCombine = 5000;
                        String npcSay = "|7|Phục hồi độ bền " + sachtk.template.name + "\n";
                        if (player.inventory.ruby < player.combineNew.gemCombine) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 20k hồng ngọc!!", "Đóng");
                            return;
                        }
                        if (optionLevel_238 != null && level_238 == 1000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Độ bền đã đầy rồi mà", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Phục hồi\ncần " + player.combineNew.gemCombine + " hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 Sách Tuyệt Kỹ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần đặt vào những thứ cần thiết", "Đóng");
                }
                break;
            }
            case PHAN_RA: {
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item sachtk = null;
                    Item sachtk2 = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template.id >= 2071 && item.template.id <= 2073) {
                            sachtk = item;
                        } else if (item.template.id >= 2075 && item.template.id <= 2077) {
                            sachtk2 = item;
                        }
                    }
                    if (sachtk != null && sachtk2 == null) {
                        player.combineNew.gemCombine = 1000;
                        String npcSay = "|7|Phân rã " + sachtk.template.name + "\n";
                        if (player.inventory.ruby < player.combineNew.gemCombine) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 1k hồng ngọc!!", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Phân rã\ncần " + player.combineNew.gemCombine + " hồng ngọc");
                    } else if (sachtk2 != null && sachtk == null) {
                        player.combineNew.gemCombine = 2000;
                        String npcSay = "|7|Phân rã " + sachtk2.template.name + "\n";
                        if (player.inventory.ruby < player.combineNew.gemCombine) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không đủ 2k hồng ngọc!!", "Đóng");
                            return;
                        }
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Phân rã\ncần " + player.combineNew.gemCombine + " hồng ngọc");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 Sách Tuyệt Kỹ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần đặt vào những thứ cần thiết", "Đóng");
                }
                break;
            }
            case NANG_CAP_DE_WHIS:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta x99 Thăng Tinh Thạch và 50k hồng ngọc ta sẽ giúp đệ tử Cell cấp 3 cửa ngươi trở thành đệ tử Whis",
                            "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 1) {
                    Item thang = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id == 2031) {// thang tinh thạch
                                thang = item;
                            }
                        }
                    }
                    if (player.inventory.ruby < 20_000) {
                        Service.getInstance().sendThongBao(player, "bạn thiếu 20k ruby");
                        return;
                    }
                    if (player.pet.typePet != ConstPet.CELL) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ngươi không có đệ tử Black gâu ku", "Đóng");
                        return;
                    }
                    if (thang == null) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Thăng Tinh Thạch", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn nâng cấp đệ tử Cell cấp 3 của con?"
                            + "\n|8|Tỉ lệ mặc định là 50%";
                    this.whis.createOtherMenu(player, ConstNpc.MENU_NANG_CAP_DO_TS,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPDATE_TRAIN_ARMOR:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta x99 Thăng Tinh Thạch để ta tạo cho coan Giáp Luyện Tập cấp 1 nhó",
                            "Đóng");
                    return;
                }
                switch (player.combineNew.itemsCombine.size()) {
                    case 1:
                        Item thang = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if (item.template.id == 2031) {// thang tinh thạch
                                    thang = item;
                                }
                            }
                        }
                        if (thang == null) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Thăng Tinh Thạch", "Đóng");
                            return;
                        }
                        String npcSay = "|2|Con có muốn x99 Thăng Tinh Thạch này\nChuyển hóa thành Giáp Luyện Tập cấp 1?"
                                + "\n|8|Tỉ lệ mặc định là 100%";
                        this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                npcSay, "Nâng cấp", "Từ chối");
                        break;
                    case 2:
                        Item TTT = null;
                        Item GLT_1 = null;
                        Item GLT_2 = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                switch (item.template.id) {
                                    case 2031:
                                        TTT = item;
                                        break;
                                    case 529:
                                        GLT_1 = item;
                                        break;
                                    case 530:
                                        GLT_2 = item;
                                        break;
                                    default:
                                }

                            }
                        }
                        if (GLT_1 == null) {
                            String said = "|2|Con có muốn x9999 Thăng Tinh Thạch và"
                                    + "\nGiáp luyện tập cấp 2"
                                    + "\nChuyển hóa thành Giáp Luyện Tập cấp 3?"
                                    + "\n|8|Tỉ lệ mặc định là 50%";
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    said, "Nâng cấp", "Từ chối");
                        } else {
                            String said = "|2|Con có muốn x999 Thăng Tinh Thạch và"
                                    + "\nGiáp luyện tập cấp 1"
                                    + "\nChuyển hóa thành Giáp Luyện Tập cấp 2?"
                                    + "\n|8|Tỉ lệ mặc định là 50%";
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    said, "Nâng cấp", "Từ chối");
                        }
                        break;
                    default:
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Mày nhét cái máu lồn gì vào tao vậy?",
                                "Đóng");
                        break;
                }

                break;

            case TRADE_PET:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta x10 Gậy thượng đế và 5k hồng ngọc ta sẽ giúp đệ tử ngươi trở thành đệ tử Whis", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 1) {
                    Item gayTD = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id == 1231) {
                                gayTD = item;
                            }
                        }
                    }
                    if (player.pet.typePet != ConstPet.SUPER) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ngươi không có đệ tử Black gâu ku", "Đóng");
                        return;
                    }
                    if (gayTD == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "thiếu Gậy thượng đế", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn nâng cấp đệ tử của con?"
                            + "\n|8|Tỉ lệ mặc định là 10%";
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPGRADE_PET:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta x10 Đá nguyền ấn và 5k hồng ngọc ta sẽ giúp đệ tử ngươi lên 1 Lever", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 1) {
                    Item da = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id == 2040) {
                                da = item;
                            }
                        }
                    }
                    if (da == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "thiếu Đá nguyền ấn", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn nâng cấp đệ tử của con?"
                            + "\n|8|Tỉ lệ mặc định là 50%"
                            + "\n|1|Cần " + Util.numberToMoney(COST_LEVER) + " hồng ngọc";
                    if (player.inventory.ruby < COST_LEVER) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "hết xiền rồi", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST_DAP_DO_KICH_HOAT_LINH_THU) + " hồng ngọc", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPGRADE_PET_CELL:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta x999 ấu trùng xên và 30k hồng ngọc ta sẽ giúp đệ tử ngươi lên 1 Lever", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 1) {
                    if (player.pet.typePet != ConstPet.CELL) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Ngươi không có đệ tử Cell", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn nâng cấp đệ tử của con?"
                            + "\n|8|Cần x999 ấu trùng cell xanh"
                            + "\n|1|Cần " + Util.numberToMoney(30_000) + " hồng ngọc";
                    if (player.inventory.ruby < 30_000) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "hết xiền rồi", "Đóng");
                        return;
                    }

                    if (player.pet.getLever() >= 3) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Đã max Lever", "Đóng");
                        return;
                    }

                    switch (player.pet.getLever()) {
                        case 1:
                            this.bulmatl.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    npcSay, "Nâng cấp\n" + Util.numberToMoney(30_000) + " hồng ngọc", "Từ chối");
                            break;
                        case 2:
                            this.bulmatl.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    "|2|Con có muốn nâng cấp đệ tử của con?"
                                            + "\n|8|Cần x999 ấu trùng cell vàng",
                                    "Nâng cấp\n" + Util.numberToMoney(30_000) + " hồng ngọc", "Từ chối");
                            break;
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPGRADE_POWER_STONE:
            case UPGRADE_POWER_STONE_COINT:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item it = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id == 1956) {
                                it = item;
                            }
                        }
                    }
                    if (it == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn power stone để ta nâng cấp nóa", "Đóng");
                        return;
                    }
                    baHatMit.createOtherMenu(player, ConstNpc.MENU_START, "|7|Tỉ lệ thành công: 20%", "Nâng cấp\nvới 20K ruby", "Nâng cấp\nvới 5K " + ConstItemName.MONEY_UNIT);
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn power stone để ta nâng cấp nóa", "Đóng");
                }
                break;
            case UPGRADE_HUY_DIET:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1  món đồ kích hoạt Thần Linh và 30k ngọc ", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() <= 2) {
                    Item dokh = null;
                    Item pi = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.isSKHVip() && item.template.id >= 555 && item.template.id <= 567) {
                                dokh = item;
                            } else if (item.template.id == ConstItem.THOI_VANG_VIP) {
                                pi = item;
                            }
                        }
                    }
                    if (dokh == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ Kích Hoạt Thần Linh", "Đóng");
                        return;
                    }
                    int gem = 30000;
                    if (player.inventory.gem < gem) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Không đủ ngọc để thực hiện", "Đóng");
                        return;
                    }
                    int tile = 0;
                    if (pi != null && pi.quantity >= 10000) {
                        tile = 10;
                    } else {
                        tile = 5;
                    }
                    String npcSay = "|2|Con có muốn Nâng cấp Đồ KH này thành Đồ KH vip 100% chỉ số set kích hoạt huỷ diệt bất kì không?"
                            + "\n|8|Tỉ lệ là " + tile + "%"
                            + "\n|1|Cần 30k ngọc";

                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "30k ngọc", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPGRADE_THAN_LINH:
                if (player.combineNew.itemsCombine.isEmpty()) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy đưa ta 1  món đồ kích hoạt bất kỳ và 1 món đồ Thần Linh", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() >= 1) {
                    Item manhTL = null;
                    Item dokh = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id >= 555 && item.template.id <= 567) {
                                manhTL = item;
                            } else if (item.isSKHVip()) {
                                dokh = item;
                            }
                        }
                    }
                    if (dokh == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ Kích Hoạt Thường", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn Nâng cấp Đồ KH này thành Đồ KH vip 100% chỉ số set kích hoạt thần linh bất kì không?"
                            + "\n|8|Tỉ lệ mặc định là 10%\n|8|Nếu + thêm x1 món Thần Linh tỉ lệ sẽ là 30%"
                            + "\n|1|Cần 10k đồng coint";
                    Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                    if (pi == null || pi.quantity < 10_000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT, "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT, "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case UPGRADE_LINHTHU:
                if (InventoryService.gI().getCountEmptyBag(player) < 1) {
                    this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn phải có ít nhất 1 ô trống hành trang", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    Item itemLinhThu = null;
                    Item stone = null;
                    Item HLT = null;// hon linh thu
                    Item TTT = null;// thang tinh thach
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            switch (item.template.id) {
                                case 2029:
                                    HLT = item;
                                    break;
                                case 2031:
                                    TTT = item;
                                    break;
                            }
                            if (isLinhThu(item)) {
                                itemLinhThu = item;
                            } else if (isStone(item)) {
                                stone = item;
                            }
                        }
                    }
                    if (player.inventory.ruby < 10_000) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 10k Ruby", "Đóng");
                        return;
                    }
                    if (itemLinhThu == null) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần linh thú", "Đóng");
                        return;
                    }
                    if (HLT == null || HLT.quantity < 99) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần x99 Hồn Linh Thú", "Đóng");
                        return;
                    }
                    if (TTT == null) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần Thăng Tinh Thạch", "Đóng");
                        return;
                    }
                    if (stone == null) {
                        this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần Stone linh thú", "Đóng");
                        return;
                    }
                    String npcSay = "|1|Cậu đang có\n|8|" + itemLinhThu.template.name + "\n"
                            + "|5|" + HLT.quantity + " Hồn linh thú\n"
                            + "|5|" + TTT.quantity + " Thăng tinh thạch\n"
                            + "|5|" + stone.quantity + " " + stone.template.name + "\n"
                            + "|1|Tôi sẽ giúp cậu nâng cấp linh thú này\n"
                            + "|8|Bạn đưa tôi 10 thăng tinh thạch tôi sẽ giúp bạn\n"
                            + "|8|Tôi sẽ giúp bạn có tỉ lệ thành công cao hơn\n"
                            + "|1|Cậu có đồng ý không!";
                    this.bulmatl.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Đồng Ý", "Từ chối");
                } else {
                    this.bulmatl.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn không đủ nguyên liệu", "Đóng");
                }
                break;
            case UPGRADE_CAITRANG:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1  món đồ kích hoạt thường bất kỳ và x5 mảnh Thần Linh", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item ct = null;
                    Item stole = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id == 711) {
                                ct = item;
                            } else if (item.template.id == 1260) {
                                stole = item;
                            }
                        }
                    }
                    if (stole == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thieu da", "Đóng");
                        return;
                    }
                    if (stole == null && stole.quantity < 99) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thieu da", "Đóng");
                        return;
                    }
                    if (ct == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thieu ct", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn nâng cấp cải trang  " + ct.template.name
                            + "|1|Cần 10k ruby";
                    if (player.inventory.ruby < 10_000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n10k ruby", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 2) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NANG_CAP_DO_KICH_HOAT:
                if (player.combineNew.itemsCombine.size() == 0) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta 1  món đồ kích hoạt thường bất kỳ và x5 mảnh Thần Linh", "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item manhTL = null;
                    Item dokh = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id >= 2032 && item.template.id <= 2036) {
                                manhTL = item;
                            } else if (item.isSKHThuong()) {
                                dokh = item;
                            }
                        }
                    }
                    if (manhTL == null || manhTL.quantity < 2) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu mảnh Thần Linh", "Đóng");
                        return;
                    }
                    if (dokh == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ Kích Hoạt Thường", "Đóng");
                        return;
                    }
                    String npcSay = "|2|Con có muốn Nâng cấp Đồ KH thường này thành Đồ KH vip 100% chỉ số set bất kì không?\n|7|"
                            + "|1|Cần " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " hồng ngọc";
                    if (player.inventory.ruby < COST_DAP_DO_KICH_HOAT) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp\n" + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " hồng ngọc", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Nguyên liệu không phù hợp!", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;

            case TRADE_DO_THAN_LINH:
                if (player.combineNew.itemsCombine.size() != 1) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Nguyên liệu", "Đóng");
                    break;
                }
                if (InventoryService.gI().getCountEmptyBag(player) < 1) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn phải có ít nhất 1 ô trống hành trang", "Đóng");
                    break;
                }
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item doTL = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id >= 555 && item.template.id <= 567) {
                                doTL = item;
                            }
                        }
                    }
                    if (doTL == null || doTL.quantity < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ rồi ku!", "Đóng");
                        break;
                    }
                    Item itemMTL = ItemService.gI().createNewItem((short) Util.nextInt(2032, 2036));
                    String npcSay = "Chế tạo " + itemMTL.template.name + "\n"
                            + "Trang bị Thần Linh sẽ được chuyển hóa\n"
                            + "Sang mảnh thần linh\n"
                            + "Mảnh Thần Linh dùng để nâng cấp SKH 100%\n"
                            + "Tỉ lệ thành công: 100%\n"
                            + "Phí nâng cấp: " + Util.numberToMoney(COST_RUBY_TRADE_TL) + " ruby";
                    if (player.inventory.ruby < COST_RUBY_TRADE_TL) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nNạp tiền vào đay Donate cho taoo", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case EP_SAO_TRANG_BI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item trangBi = null;
                    Item daPhaLe = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (isTrangBiEpSaoPhale(item)) {
                            trangBi = item;
                        } else if (isDaPhaLe(item)) {
                            daPhaLe = item;
                        }
                    }
                    int star = 0; // sao pha lê đã ép
                    int starEmpty = 0; // lỗ sao pha lê
                    if (trangBi != null && daPhaLe != null) {
                        for (ItemOption io : trangBi.itemOptions) {
                            if (io.optionTemplate.id == 102) {
                                star = io.param;
                            } else if (io.optionTemplate.id == 107) {
                                starEmpty = io.param;
                            }
                        }
                        if (star < starEmpty) {
                            player.combineNew.gemCombine = getGemEpSao(star);
                            String npcSay = trangBi.template.name + "\n|2|";
                            for (ItemOption io : trangBi.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            if (daPhaLe.template.type == 30) {
                                for (ItemOption io : daPhaLe.itemOptions) {
                                    npcSay += "|7|" + io.getOptionString() + "\n";
                                }
                            } else {
                                npcSay += "|7|" + ItemService.gI().getItemOptionTemplate(getOptionDaPhaLe(daPhaLe)).name
                                        .replaceAll("#", getParamDaPhaLe(daPhaLe) + "") + "\n";
                            }
                            npcSay += "|1|Cần " + Util.numberToMoney(player.combineNew.gemCombine) + " ngọc";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");

                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 trang bị có lỗ sao pha lê và 1 loại đá pha lê để ép vào", "Đóng");
                }
                break;
            case PHA_LE_HOA_TRANG_BI:
            case PHA_LE_HOA_TRANG_BI_X10:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (isTrangBiPhaLeHoa(item)) {
                        int star = 0;
                        for (ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 107) {
                                star = io.param;
                                break;
                            }
                        }
                        if (star < MAX_STAR_ITEM) {
                            // ekko x2 ngọc cần để pha lê hóa
                            player.combineNew.gemCombine = getGemPhaLeHoa(star) * 2;
                            player.combineNew.goldCombine = getGoldPhaLeHoa(star);
                            player.combineNew.ratioCombine = getRatioPhaLeHoa(star);

                            String npcSay = item.template.name + "\n|2|";
                            for (ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                            baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Nâng cấp\ncần " + player.combineNew.gemCombine + " ngọc");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này không thể đục lỗ", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vật phẩm để pha lê hóa", "Đóng");
                }
                break;
            case PHA_LE_HOA_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (isItemCaiTrang(item)) {
                        int star = 0;
                        Item tvv = null;
                        for (ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 107) {
                                star = io.param;
                                break;
                            }
                        }
                        for (Item it : player.combineNew.itemsCombine) {
                            if (it.isNotNullItem()) {
                                switch (it.template.id) {
                                    case ConstItem.THOI_VANG_VIP:
                                        tvv = it;
                                        break;
                                }
                            }
                        }
                        if (star < MAX_STAR_ITEM) {
//                            int ruby = 10_000;
                            String npcSay = item.template.name + "\n|2|";
                            for (ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            if (tvv == null || tvv.quantity < 10_000L) {
                                npcSay += "|1|Cần 10k Thỏi vàng VIP";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay,
                                        "Đóng");
                            }
                            else {
                                npcSay += "|1|Tỉ lệ thành công 20%\nCần " + " 10k Thỏi vàng VIP";
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp\ncần 10k Thỏi vàng VIP");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này không thể đục lỗ",
                                "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vật phẩm để pha lê hóa",
                            "Đóng");
                }
                break;
            case PHA_LE_HOA_DISGUISE:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (isItemPhaLeHoa(item)) {
                        int star = 0;
                        Item capsule = null;
                        for (ItemOption io : item.itemOptions) {
                            if (io.optionTemplate.id == 107) {
                                star = io.param;
                                break;
                            }
                        }
                        for (Item it : player.combineNew.itemsCombine) {
                            if (it.isNotNullItem()) {
                                switch (it.template.id) {
                                    case 869:
                                        capsule = it;
                                        break;
                                }
                            }
                        }
                        if (star < MAX_SAO_FLAG_BAG) {
                            int ruby = 10_000;
                            String npcSay = item.template.name + "\n|2|";
                            for (ItemOption io : item.itemOptions) {
                                if (io.optionTemplate.id != 102) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            if (capsule == null || capsule.quantity < 9) {
                                npcSay += "|1|Cần x9 capsule 1 Sao";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay,
                                        "Đóng");
                            } else if (player.inventory.ruby < ruby) {
                                npcSay += "thiếu ruby";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            } else {
                                npcSay += "|1|Cần " + Util.numberToMoney(ruby) + " ruby";
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp\ncần 10k ruby");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Vật phẩm đã đạt tối đa sao pha lê", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vật phẩm này không thể đục lỗ",
                                "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vật phẩm để pha lê hóa",
                            "Đóng");
                }
                break;
            case NANG_CAP_DO_TS:
                if (player.combineNew.itemsCombine.size() != 4) {
                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu Nguyên liệu", "Đóng");
                    break;
                }
                if (InventoryService.gI().getCountEmptyBag(player) < 1) {
                    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
                    break;
                }
                if (player.combineNew.itemsCombine.size() == 4) {
                    Item manhts = null;
                    Item danc = null;
                    Item damayman = null;
                    Item congthuc = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.isManhTS()) {
                                manhts = item;
                            } else if (item.isdanangcapDoTs()) {
                                danc = item;
                            } else if (item.isDamayman()) {
                                damayman = item;
                            } else if (item.isCongthucNomal() || item.isCongthucVip()) {
                                congthuc = item;
                            }
                        }
                    }
                    if (manhts == null || manhts.quantity < 999) {
                        Service.getInstance().sendThongBao(player, "Cần x999 mảnh thiên sứ!!");
                        break;
                    }
                    if (danc == null) {
                        Service.getInstance().sendThongBao(player, "Thiếu đá nâng cấp!!");
                        break;
                    }
                    if (damayman == null) {
                        Service.getInstance().sendThongBao(player, "Thiếu đá may mắn!!");
                        break;
                    }
                    if (congthuc == null) {
                        Service.getInstance().sendThongBao(player, "Thiếu công thức!!");
                        break;
                    }
                    short[][] itemIds = {{1048, 1051, 1054, 1057, 1060}, {1049, 1052, 1055, 1058, 1061}, {1050, 1053, 1056, 1059, 1062}};
                    Item itemTS = ItemService.gI().createNewItem(itemIds[congthuc.template.gender][manhts.typeIdManh()]);
                    String npcSay = "Chế tạo " + itemTS.template.name + "\n"
                            + "Mạnh hơn trang bị Hủy Diệt từ 20% đến 35%\n"
                            + "Mảnh ghép " + manhts.quantity + "/999 (Thất bại -99 mảnh ghép)\n"
                            + danc.template.name + " (thêm " + (danc.template.id - 1073) * 10 + "% tỉ lệ thành công)\n"
                            + damayman.template.name + " (thêm " + (damayman.template.id - 1078) * 10 + "% tỉ lệ tối đa các chỉ số)\n"
                            + "Tỉ lệ thành công: " + get_Tile_nang_Do_TS(danc, congthuc) + "%\n"
                            + "Phí nâng cấp: " + Util.numberToMoney(COST_NANG_DO_TS) + " vàng"
                            + "Phí nâng cấp: " + Util.numberToMoney(COST_RUBY_NANG_DO_TS) + " ngọc xanh";
                    if (player.inventory.gold < COST_NANG_DO_TS) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hết tiền rồi\nẢo ít thôi con", "Đóng");
                        break;
                    }
                    if (player.inventory.gem < COST_RUBY_NANG_DO_TS) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Bạn thiếu 15k ngọc xanh", "Đóng");
                        break;
                    }
                    this.whis.createOtherMenu(player, ConstNpc.MENU_NANG_CAP_DO_TS,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case NHAP_NGOC_RONG:
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    if (player.combineNew.itemsCombine.size() == 1) {
                        Item item = player.combineNew.itemsCombine.get(0);
                        if (item != null && item.isNotNullItem()) {
                            if ((item.template.id > 14 && item.template.id <= 20) && item.quantity >= 7) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (item.template.id - 1)).name + "\n"
                                        + "|7|Cần 7 " + item.template.name;
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else if ((item.template.id == 14 && item.quantity >= 7)) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (925)).name + "\n" + "\n|7|Cần 7 "
                                        + item.template.name + "\n|7|Cần 500tr Vàng";
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else if (item.template.id == 926 && item.quantity >= 7) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (925)).name + "\n" + "\n|7|Cần 7 "
                                        + item.template.name + "\n|7|Cần 500tr Vàng";
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 7 viên ngọc rồng 2 sao trở lên", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống",
                            "Đóng");
                }
                break;
            case EP_NGOC_HAC_AM:
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    if (player.combineNew.itemsCombine.size() == 1) {
                        Item item = player.combineNew.itemsCombine.get(0);
                        if (item != null && item.isNotNullItem()) {
                            if ((item.template.id > ConstItem.NGOC_HAC_AM_1_SAO && item.template.id <= ConstItem.NGOC_HAC_AM_7_SAO) && item.quantity >= 7) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (item.template.id - 1)).name + "\n"
                                        + "|7|Cần 7 " + item.template.name;
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần 7 viên ngọc rồng hắc ám 2 sao trở lên", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 7 viên ngọc rồng hắc ám 2 sao trở lên", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống",
                            "Đóng");
                }
                break;
            case EP_NGOC_RONG_BANG:
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    if (player.combineNew.itemsCombine.size() == 1) {
                        Item item = player.combineNew.itemsCombine.get(0);
                        if (item != null && item.isNotNullItem()) {
                            if ((item.template.id > 925 && item.template.id <= 931) && item.quantity >= 7) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (item.template.id - 1)).name + "\n"
                                        + "|7|Cần 7 " + item.template.name;
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else if ((item.template.id == 2009 && item.quantity >= 7)) {
                                String npcSay = "|2|Con có muốn biến 7 " + item.template.name + " thành\n" + "1 viên "
                                        + ItemService.gI().getTemplate((short) (931)).name + "\n" + "\n|7|Cần 7 "
                                        + item.template.name + "\n|7|Cần 1k ruby";
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Làm phép",
                                        "Từ chối");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần 7 viên Băng tâm thần thủy trở lên", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Thiếu vật phẩm", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hành trang cần ít nhất 1 chỗ trống",
                            "Đóng");
                }
                break;

            case NANG_CAP_BONG_TAI_CAP3:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongtai = null;
                    Item bikiep = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        switch (item.template.id) {
                            case 921:
                                bongtai = item;
                                break;
                            case ConstItem.BINH_NUOC:
                                bikiep = item;
                                break;

                        }
                    }

                    if (bongtai != null && bikiep != null) {
                        int level = 0;
                        for (ItemOption io : bongtai.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < 3) {
                            int lvbt = lvbt(bongtai);
                            int countmvbt = 9999;

                            String npcSay = "Bông tai Porata Cấp: " + lvbt + " \n|2|";
                            for (ItemOption io : bongtai.itemOptions) {
                                npcSay += io.getOptionString() + "\n";
                            }
                            npcSay += "|7|Tỉ lệ thành công: 50%\n";
                            if (bikiep.quantity >= countmvbt) {
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp");
                            } else {
                                npcSay += "Còn thiếu " + Util.numberToMoney(countmvbt - bikiep.quantity)
                                        + " " + ConstItemName.BINH_NUOC;
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Đã đạt cấp tối đa", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2 và x9999 " + ConstItemName.BINH_NUOC, "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 và x9999 " + ConstItemName.BINH_NUOC, "Đóng");
                }
                break;
            case DAP_BONG_TAI_CAP_3:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongtai = null;
                    Item bikiep = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template == null) {
                            Service.getInstance().sendThongBao(player, "ERROR");
                            break;
                        }
                        switch (item.template.id) {
                            case 1995:
                                bongtai = item;
                                break;
                            case ConstItem.BINH_NUOC:
                                bikiep = item;
                                break;

                        }
                    }
                    Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                    if (pi == null || pi.quantity < 10_000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT, "Đóng");
                        return;
                    }
                    if (bongtai != null && bikiep != null) {
                        String npcSay = "Bông tai Porata cấp 3\n|2|";
                        for (ItemOption io : bongtai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: 50%\n";
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp");
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 3 và x9999 " + ConstItemName.BINH_NUOC, "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 3 và x9999 " + ConstItemName.BINH_NUOC, "Đóng");
                }
                break;
            case NANG_CAP_BONG_TAI:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item bongtai = null;
                    Item manhvobt = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (checkbongtai(item)) {
                            bongtai = item;
                        } else if (item.template.id == 933) {
                            manhvobt = item;
                        }
                    }

                    if (bongtai != null && manhvobt != null) {
                        int level = 0;
                        for (ItemOption io : bongtai.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < 2) {
                            int lvbt = lvbt(bongtai);
                            int countmvbt = getcountmvbtnangbt(lvbt);
                            player.combineNew.ratioCombine = getRationangbt(lvbt);

                            String npcSay = "Bông tai Porata Cấp: " + lvbt + " \n|2|";
                            for (ItemOption io : bongtai.itemOptions) {
                                npcSay += io.getOptionString() + "\n";
                            }
                            npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                            if (manhvobt.quantity >= countmvbt) {
                                Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                                if (pi == null || pi.quantity < 20_000) {
                                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Bạn thiếu 20k " + ConstItemName.MONEY_UNIT, "Đóng");
                                    return;
                                }
                                npcSay += "|1|Cần 20K " + ConstItemName.MONEY_UNIT;
                                baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                        "Nâng cấp cần\n20k " + ConstItemName.MONEY_UNIT);
                            } else {
                                npcSay += "Còn thiếu " + Util.numberToMoney(countmvbt - manhvobt.quantity)
                                        + " Mảnh vỡ bông tai";
                                baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Đóng");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Đã max cấp độ", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 1 hoặc 2 và Mảnh vỡ bông tai", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 1 hoặc 2 và Mảnh vỡ bông tai", "Đóng");
                }
                break;

            case MO_CHI_SO_BONG_TAI:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item bongTai = null;
                    Item manhHon = null;
                    Item daXanhLam = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.template == null) {
                            Service.getInstance().sendThongBao(player, "ERROR");
                            break;
                        }
                        switch (item.template.id) {
                            case 921:
                                bongTai = item;
                                break;
                            case 934:
                                manhHon = item;
                                break;
                            case 935:
                                daXanhLam = item;
                                break;
                            default:
                                break;
                        }
                    }
                    if (bongTai != null && manhHon != null && daXanhLam != null && manhHon.quantity >= 99) {
                        player.combineNew.ratioCombine = RATIO_NANG_CAP;
                        String npcSay = "Bông tai Porata cấp "
                                + (bongTai.template.id == 921 ? bongTai.template.id == 1128 ? "2" : "3" : "1")
                                + " \n|2|";
                        for (ItemOption io : bongTai.itemOptions) {
                            npcSay += io.getOptionString() + "\n";
                        }
                        npcSay += "|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%" + "\n";
                        Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                        if (pi == null || pi.quantity < 20_000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn thiếu 20k " + ConstItemName.MONEY_UNIT, "Đóng");
                            return;
                        }
                        npcSay += "|1|Cần 20k " + ConstItemName.MONEY_UNIT;
                        baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                "Nâng cấp\ncần 20k " + ConstItemName.MONEY_UNIT);
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Cần 1 Bông tai Porata cấp 2 hoặc 3, X99 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Cần 1 Bông tai Porata cấp 2 hoặc 3, X99 Mảnh hồn bông tai và 1 Đá xanh lam", "Đóng");
                }
                break;
            case NANG_SKH_NEW:
                upgradeSKHNewConfirm(player, ConstItem.THOI_VANG_VIP);
                break;
            case NANG_SKH_NEW_RUBY:
                upgradeSKHNewConfirm(player, ConstItem.HONG_NGOC);
                break;
            case NANG_CHAN_MENH:
                if (player.combineNew.itemsCombine.size() != 2) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy đưa ta chân mệnh và x10k" + ConstItemName.MONEY_UNIT, "Đóng");
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item chanmenh = null;
                    Item da = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.type == 97) {
                                chanmenh = item;
                            } else if (item.template.id == ConstItem.THOI_VANG_VIP) {
                                da = item;
                            }
                        }
                    }
                    if (chanmenh == null) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu chân mệnh", "Đóng");
                        return;
                    }
                    if (da == null || da.quantity < 10_000) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu " + ConstItemName.MONEY_UNIT, "Đóng");
                        return;
                    }
                    if (chanmenh.template.id >= 1327) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Đã đạt cấp tối đa!!!", "Đóng");
                        return;
                    }
                    player.combineNew.ratioCombine = (float) getTileNangHonHoan(chanmenh.template.id);
                    String npcSay = "|2|Ngươi muốn nâng cấp chân mệnh của mình sao?\n|7|"
                            + "Hãy đưa ta đủ nguyên liệu ta sẽ làm cho nó mạnh hơn\n"
                            + "|1|Cần x10k " + ConstItemName.MONEY_UNIT + " \n"
                            + "|1|Tỉ lệ thành công: " + player.combineNew.ratioCombine + " %";

                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                            npcSay, "Nâng cấp", "Từ chối");
                } else {
                    if (player.combineNew.itemsCombine.size() > 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        return;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Còn thiếu nguyên liệu để nâng cấp hãy quay lại sau", "Đóng");
                }
                break;
            case REMOVE_OPTION:
                if (!Objects.isNull(checkItemCanCombine(player))) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, "Xác nhận thực hiện", "Nâng cấp", "Đóng");
                }
                break;
            case NANG_CAP_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() != 2) {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần mang linh hồn cải trang và 1 cải trang bất kỳ", "Đóng");
                    break;
                }
                Item linhHonCt = null;
                Item caiTrang = null;
                for (Item item :
                        player.combineNew.itemsCombine) {
                    if (item.template.id == ConstItem.LINH_HON_CAI_TRANG || item.template.id == ConstItem.LINH_HON_CAI_TRANG2) {
                        linhHonCt = item;
                    }
                    if (item.template.type == 5) {
                        caiTrang = item;
                    }
                }
                if (linhHonCt == null || linhHonCt.quantity < 99) {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần mang linh x99 hồn cải trang", "Đóng");
                    break;
                }
                if (caiTrang == null || caiTrang.getLevel() >= 7) {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 cải trang hoặc cải trang thấp hơn 7 sao", "Đóng");
                    break;
                }
                if (player.inventory.ruby < 50_000) {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 50k hồng ngọc", "Đóng");
                    break;
                }

                this.quyLaoKame.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                        "Bạn có muốn nâng cấp x99 " + linhHonCt.template.name + "\n" +
                                caiTrang.template.name + " và 50k hồng ngọc để nâng cấp cải trang ", "Nâng cấp\n", "Từ chối");
                break;

            case NANG_CAP_VAT_PHAM:
                if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đá nâng cấp", "Đóng");
                        break;
                    }
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1143).count() < 1) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thiếu đồ nâng cấp", "Đóng");
                        break;
                    }
                    Item itemDo = null;
                    Item itemDNC = null;
                    Item itemDBV = null;
                    for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                        if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 1143) {
                                itemDBV = player.combineNew.itemsCombine.get(j);
                                continue;
                            }
                            if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                                itemDo = player.combineNew.itemsCombine.get(j);
                            } else {
                                itemDNC = player.combineNew.itemsCombine.get(j);
                            }
                        }
                    }
                    if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                        int level = 0;
                        for (ItemOption io : itemDo.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                break;
                            }
                        }
                        if (level < MAX_LEVEL_ITEM) {
                            player.combineNew.goldCombine = getGoldNangCapDo(level);
                            player.combineNew.ratioCombine = (float) getTileNangCapDo(level);
                            player.combineNew.countDaNangCap = getCountDaNangCapDo(level);
                            player.combineNew.countDaBaoVe = (short) getCountDaBaoVe(level);
                            String npcSay = "|2|Hiện tại " + itemDo.template.name + " (+" + level + ")\n|0|";
                            for (ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id != 72) {
                                    npcSay += io.getOptionString() + "\n";
                                }
                            }
                            String option = null;
                            int param = 0;
                            for (ItemOption io : itemDo.itemOptions) {
                                if (io.optionTemplate.id == 47
                                        || io.optionTemplate.id == 6
                                        || io.optionTemplate.id == 0
                                        || io.optionTemplate.id == 7
                                        || io.optionTemplate.id == 14
                                        || io.optionTemplate.id == 22
                                        || io.optionTemplate.id == 23) {
                                    option = io.optionTemplate.name;
                                    param = io.param + (io.param * 10 / 100);
                                    break;
                                }
                            }
                            npcSay += "|2|Sau khi nâng cấp (+" + (level + 1) + ")\n|7|"
                                    + option.replaceAll("#", String.valueOf(param))
                                    + "\n|7|Tỉ lệ thành công: " + player.combineNew.ratioCombine + "%\n"
                                    + (player.combineNew.countDaNangCap > itemDNC.quantity ? "|7|" : "|1|")
                                    + "Cần " + player.combineNew.countDaNangCap + " " + itemDNC.template.name
                                    + "\n" + (player.combineNew.goldCombine > player.inventory.gold ? "|7|" : "|1|")
                                    + "Cần " + Util.numberToMoney(player.combineNew.goldCombine) + " vàng";

                            String daNPC = player.combineNew.itemsCombine.size() == 3 && itemDBV != null ? String.format("\nCần tốn %s đá bảo vệ", player.combineNew.countDaBaoVe) : "";
                            if ((level == 2 || level == 4 || level == 6) && !(player.combineNew.itemsCombine.size() == 3 && itemDBV != null)) {
                                npcSay += "\nNếu thất bại sẽ rớt xuống (+" + (level - 1) + ")";
                            }
                            if (player.combineNew.countDaNangCap > itemDNC.quantity) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaNangCap - itemDNC.quantity) + " " + itemDNC.template.name);
                            } else if (player.combineNew.goldCombine > player.inventory.gold) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + Util.numberToMoney((player.combineNew.goldCombine - player.inventory.gold)) + " vàng");
                            } else if (player.combineNew.itemsCombine.size() == 3 && Objects.nonNull(itemDBV) && itemDBV.quantity < player.combineNew.countDaBaoVe) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        npcSay, "Còn thiếu\n" + (player.combineNew.countDaBaoVe - itemDBV.quantity) + " đá bảo vệ");
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        npcSay, "Nâng cấp\n" + Util.numberToMoney(player.combineNew.goldCombine) + " vàng" + daNPC, "Từ chối");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Trang bị của ngươi đã đạt cấp tối đa", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                    }
                } else {
                    if (player.combineNew.itemsCombine.size() > 3) {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cất đi con ta không thèm", "Đóng");
                        break;
                    }
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị và 1 loại đá nâng cấp", "Đóng");
                }
                break;
            case DOI_VE_HUY_DIET:
                if (player.combineNew.itemsCombine.size() == 1) {
                    Item item = player.combineNew.itemsCombine.get(0);
                    if (item.isNotNullItem() && item.template.id >= 555 && item.template.id <= 567) {
                        String ticketName = "Vé đổi " + (item.template.type == 0 ? "áo"
                                : item.template.type == 1 ? "quần"
                                : item.template.type == 2 ? "găng" : item.template.type == 3 ? "giày" : "nhẫn")
                                + " hủy diệt";
                        String npcSay = "|6|Ngươi có chắc chắn muốn đổi\n|7|" + item.template.name + "\n";
                        for (ItemOption io : item.itemOptions) {
                            npcSay += "|2|" + io.getOptionString() + "\n";
                        }
                        npcSay += "|6|Lấy\n|7|" + ticketName + "\n|6|Với giá "
                                + Util.numberToMoney(COST_DOI_VE_DOI_DO_HUY_DIET) + " vàng không?";
                        if (player.inventory.gold >= COST_DOI_VE_DOI_DO_HUY_DIET) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Đổi",
                                    "Từ chối");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Còn thiếu\n"
                                            + Util.numberToMoney(COST_DOI_VE_DOI_DO_HUY_DIET - player.inventory.gold) + " vàng",
                                    "Đóng");
                        }

                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hãy chọn 1 trang bị thần linh ngươi muốn trao đổi", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Hãy chọn 1 trang bị thần linh ngươi muốn trao đổi", "Đóng");
                }
                break;
            case DAP_SET_KICH_HOAT:
                if (player.combineNew.itemsCombine.size() == 1 || player.combineNew.itemsCombine.size() == 2) {
                    Item dhd = null, dtl = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.id >= 650 && item.template.id <= 662) {
                                dhd = item;
                            } else if (item.template.id >= 555 && item.template.id <= 567) {
                                dtl = item;
                            }
                        }
                    }
                    if (dhd != null) {
                        String npcSay = "|6|" + dhd.template.name + "\n";
                        for (ItemOption io : dhd.itemOptions) {
                            npcSay += "|2|" + io.getOptionString() + "\n";
                        }
                        if (dtl != null) {
                            npcSay += "|6|" + dtl.template.name + "\n";
                            for (ItemOption io : dtl.itemOptions) {
                                npcSay += "|2|" + io.getOptionString() + "\n";
                            }
                        }
                        npcSay += "Ngươi có muốn chuyển hóa thành\n";
                        npcSay += "|1|" + getNameItemC0(dhd.template.gender, dhd.template.type)
                                + " (ngẫu nhiên kích hoạt)\n|7|Tỉ lệ thành công " + (dtl != null ? "100%" : "40%")
                                + "\n|2|Cần " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng";
                        if (player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Cần " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Còn thiếu\n"
                                    + Util.numberToMoney(player.inventory.gold - COST_DAP_DO_KICH_HOAT) + " vàng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta cần 1 món đồ hủy diệt của ngươi để có thể chuyển hóa 1", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Ta cần 1 món đồ hủy diệt của ngươi để có thể chuyển hóa 2", "Đóng");
                }
                break;
            case DOI_MANH_KICH_HOAT:
                if (player.combineNew.itemsCombine.size() == 2 || player.combineNew.itemsCombine.size() == 3) {
                    Item nr1s = null, doThan = null, buaBaoVe = null;
                    for (Item it : player.combineNew.itemsCombine) {
                        if (it.template.id == 14) {
                            nr1s = it;
                        } else if (it.template.id == 2010) {
                            buaBaoVe = it;
                        } else if (it.template.id >= 555 && it.template.id <= 567) {
                            doThan = it;
                        }
                    }

                    if (nr1s != null && doThan != null) {
                        int tile = 50;
                        String npcSay = "|6|Ngươi có muốn trao đổi\n|7|" + nr1s.template.name
                                + "\n|7|" + doThan.template.name
                                + "\n";
                        for (ItemOption io : doThan.itemOptions) {
                            npcSay += "|2|" + io.getOptionString() + "\n";
                        }
                        if (buaBaoVe != null) {
                            tile = 100;
                            npcSay += buaBaoVe.template.name
                                    + "\n";
                            for (ItemOption io : buaBaoVe.itemOptions) {
                                npcSay += "|2|" + io.getOptionString() + "\n";
                            }
                        }

                        npcSay += "|6|Lấy\n|7|Mảnh kích hoạt\n"
                                + "|1|Tỉ lệ " + tile + "%\n"
                                + "|6|Với giá " + Util.numberToMoney(COST_DOI_MANH_KICH_HOAT) + " vàng không?";
                        if (player.inventory.gold >= COST_DOI_MANH_KICH_HOAT) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    npcSay, "Đổi", "Từ chối");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    npcSay, "Còn thiếu\n"
                                            + Util.numberToMoney(COST_DOI_MANH_KICH_HOAT - player.inventory.gold) + " vàng", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị thần linh và 1 viên ngọc rồng 1 sao", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 trang bị thần linh và 1 viên ngọc rồng 1 sao", "Đóng");
                }
                break;
            case DAP_SET_KICH_HOAT_CAO_CAP:
                if (player.combineNew.itemsCombine.size() == 3) {
                    Item it = player.combineNew.itemsCombine.get(0), it1 = player.combineNew.itemsCombine.get(1),
                            it2 = player.combineNew.itemsCombine.get(2);
                    if (!isDestroyClothes(it.template.id) || !isDestroyClothes(it1.template.id)
                            || !isDestroyClothes(it2.template.id)) {
                        it = null;
                    }
                    if (it != null) {
                        String npcSay = "|1|" + it.template.name + "\n" + it1.template.name + "\n" + it2.template.name
                                + "\n";
                        npcSay += "Ngươi có muốn chuyển hóa thành\n";
                        npcSay += "|7|" + getTypeTrangBi(it.template.type)
                                + " cấp bậc ngẫu nhiên (set kích hoạt ngẫu nhiên)\n|2|Cần "
                                + Util.numberToMoney(10_000) + " vàng";
                        if (player.inventory.ruby >= 10_000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                    "Cần " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng");
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay, "Còn thiếu\n"
                                    + Util.numberToMoney(player.inventory.gold - COST_DAP_DO_KICH_HOAT) + " hồng ngọc");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta cần 3 món đồ hủy diệt của ngươi để có thể chuyển hóa", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Nhót", "Đóng");
                }
                break;
            case GIA_HAN_CAI_TRANG:
                if (player.combineNew.itemsCombine.size() == 2) {
                    Item caitrang = null, vegiahan = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        if (item.isNotNullItem()) {
                            if (item.template.type == 5) {
                                caitrang = item;
                            } else if (item.template.id == 2022) {
                                vegiahan = item;
                            }
                        }
                    }
                    int expiredDate = 0;
                    boolean canBeExtend = true;
                    if (caitrang != null && vegiahan != null) {
                        for (ItemOption io : caitrang.itemOptions) {
                            if (io.optionTemplate.id == 93) {
                                expiredDate = io.param;
                            }
                            if (io.optionTemplate.id == 199) {
                                canBeExtend = false;
                            }
                        }
                        if (canBeExtend) {
                            if (expiredDate > 0) {
                                String npcSay = "|2|" + caitrang.template.name + "\n"
                                        + "Sau khi gia hạn +1 ngày \n Tỷ lệ thành công: 101% \n" + "|7|Cần 500tr vàng";
                                if (player.inventory.gold >= COST_GIA_HAN_CAI_TRANG) {
                                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay,
                                            "Gia hạn");
                                } else {
                                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay,
                                            "Còn thiếu\n"
                                                    + Util.numberToMoney(player.inventory.gold - COST_GIA_HAN_CAI_TRANG)
                                                    + " vàng");
                                }
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Cần cải trang có hạn sử dụng và thẻ gia hạn", "Đóng");
                            }
                        } else {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Vật phẩm này không thể gia hạn", "Đóng");
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Ta Cần cải trang có hạn sử dụng và thẻ gia hạn", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Ta Cần cải trang có hạn sử dụng và thẻ gia hạn", "Đóng");
                }
                break;
            case NANG_CAP_DO_THIEN_SU:
                if (player.combineNew.itemsCombine.size() > 1) {
                    int ratioLuckyStone = 0, ratioRecipe = 0, ratioUpgradeStone = 0, countLuckyStone = 0,
                            countUpgradeStone = 0;
                    Item angelClothes = null;
                    Item craftingRecipe = null;
                    for (Item item : player.combineNew.itemsCombine) {
                        int id = item.template.id;
                        if (item.isNotNullItem()) {
                            if (isAngelClothes(id)) {
                                angelClothes = item;
                            } else if (isLuckyStone(id)) {
                                ratioLuckyStone += getRatioLuckyStone(id);
                                countLuckyStone++;
                            } else if (isUpgradeStone(id)) {
                                ratioUpgradeStone += getRatioUpgradeStone(id);
                                countUpgradeStone++;
                            } else if (isCraftingRecipe(id)) {
                                ratioRecipe += getRatioCraftingRecipe(id);
                                craftingRecipe = item;
                            }
                        }
                    }
                    if (angelClothes == null) {
                        return;
                    }
                    boolean canUpgrade = true;
                    for (ItemOption io : angelClothes.itemOptions) {
                        int optId = io.optionTemplate.id;
                        if (optId == 41) {
                            canUpgrade = false;
                        }
                    }
                    if (angelClothes.template.gender != craftingRecipe.template.gender) {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Vui lòng chọn đúng công thức chế tạo",
                                "Đóng");
                        return;
                    }
                    if (canUpgrade) {
                        if (craftingRecipe != null) {
                            if (countLuckyStone < 2 && countUpgradeStone < 2) {
                                int ratioTotal = (20 + ratioUpgradeStone + ratioRecipe);
                                int ratio = ratioTotal > 75 ? ratio = 75 : ratioTotal;
                                String npcSay = "|1| Nâng cấp " + angelClothes.template.name + "\n|7|";
                                npcSay += ratioRecipe > 0 ? " Công thức VIP (+" + ratioRecipe + "% tỉ lệ thành công)\n"
                                        : "";
                                npcSay += ratioUpgradeStone > 0
                                        ? "Đá nâng cấp cấp " + ratioUpgradeStone / 10 + " (+" + ratioUpgradeStone
                                        + "% tỉ lệ thành công)\n"
                                        : "";
                                npcSay += ratioLuckyStone > 0
                                        ? "Đá nâng may mắn cấp " + ratioLuckyStone / 10 + " (+" + ratioLuckyStone
                                        + "% tỉ lệ tối đa các chỉ số)\n"
                                        : "";
                                npcSay += "Tỉ lệ thành công: " + ratio + "%\n";
                                npcSay += "Phí nâng cấp: " + Util.numberToMoney(COST_DAP_DO_KICH_HOAT) + " vàng";
                                if (player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                                    this.whis.createOtherMenu(player, ConstNpc.MENU_START_COMBINE, npcSay, "Nâng cấp");
                                } else {
                                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU, npcSay,
                                            "Còn thiếu\n"
                                                    + Util.numberToMoney(player.inventory.gold - COST_DAP_DO_KICH_HOAT)
                                                    + " vàng");
                                }
                            } else {
                                this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Chỉ có thể sự dụng tối đa 1 loại nâng cấp và đá may mắn", "Đóng");
                            }
                        } else {
                            this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Người cần ít nhất 1 trang bị thiên sứ và 1 công thức để có thể nâng cấp", "Đóng");
                        }
                    } else {
                        this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Mỗi vật phẩm chỉ có thể nâng cấp 1 lần", "Đóng");
                    }
                } else {
                    this.whis.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Người cần ít nhất 1 trang bị thiên sứ và 1 công thức để có thể nâng cấp", "Đóng");
                }
                break;
            case PHAN_RA_SKH_VIP:
                if (player.combineNew.itemsCombine.size() == 1) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        int slRuby = 1;
                        Item skhVIPChiSoAn = null;
                        if(player.inventory.ruby >= slRuby) {
                            for (Item item : player.combineNew.itemsCombine) {
                                int id = item.template.id;
                                if (item.isNotNullItem()) {
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 249) {
                                             skhVIPChiSoAn = item;
                                        }
                                    }
                                }
                            }
                            if(skhVIPChiSoAn != null) {
                                this.quyLaoKame.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn phân rã " + skhVIPChiSoAn.template.name + "\n" + " và 1 hồng ngọc để đổi lấy x1 Đá ngũ sắc không ?", "Phân rã\n", "Từ chối");
                                break;
                            } else {
                                this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Con cần có 1 món SKH VIP", "Đóng");
                            }
                        } else {
                            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần 1 hồng ngọc để phân rã SKH VIP", "Đóng");
                        }
                    } else {
                        this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 SKH Thần để chuyển hóa", "Đóng");
                }
                break;
            case NANG_CAP_SKH:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        // số lượng đá TL
                        int slDaTLNeed = 10;
                        // số lượng đá TL
                        int totalSlDaTL = 0;
                        // hợp lệ
                        boolean isValid = false;
                        // số item thần linh
                        int totalItemTL = 0;
                        // số item SKH
                        int totalItemSKH = 0;
                        // đồ SKH 1
                        Item skhOne = null;
                        // id option skh
                        int skhOneOptionSKHID = -1;
                        // đồ skh trong bag
                        Item skhOneInbag = null;
                        // đồ skh trong bag
                        Item skhTwoInbag = null;
                        // đồ TL 1 khi trong nâng cấp
                        Item tlOne = null;
                        // đồ thần linh trong túi
                        Item tlOneInBag = null;
                        // đồ thần linh trong túi
                        Item tlTwoInBag = null;
                        // đá TL dùng để nâng cấp
                        Item daThanLinhParam = null;
                        // id của đồ nâng cấp tiếp theo
                        int nextID = -1;
                        boolean isFromTL = false;
                        int skhOneOptionID = -1;
                        int skhTwoOptionID = -1;
                        int totalLoop = player.combineNew.itemsCombine.size();
                        for (int i = 0; i < totalLoop; i++) {
                            Item currentItem = player.combineNew.itemsCombine.get(i);
                            if (currentItem.isNotNullItem()) {
                                // nâng cấp 2 món thần linh lên SKH
                                if(currentItem.isItemThanLinh()) {
                                    totalItemTL += 1;
                                    tlOne = currentItem;
                                } else if (currentItem.template.id == ConstItem.DA_THAN_LINH){
                                    totalSlDaTL = currentItem.quantity;
                                    daThanLinhParam = currentItem;
                                }
                                for (ItemOption io : currentItem.itemOptions) {
                                    if (io.optionTemplate.id == ConstOption.SET_SONGOKU || io.optionTemplate.id == ConstOption.SET_TENSHINHAN || io.optionTemplate.id == ConstOption.SET_KRILLIN || io.optionTemplate.id == ConstOption.SET_NAPPA || io.optionTemplate.id == ConstOption.SET_CADIC || io.optionTemplate.id == ConstOption.SET_KAKAROT || io.optionTemplate.id == ConstOption.SET_PICOLO || io.optionTemplate.id == ConstOption.SET_DENDE || io.optionTemplate.id == ConstOption.SET_DAIMAO) {
                                        totalItemSKH += 1;
                                        skhOne = currentItem;
                                        skhOneOptionSKHID = io.optionTemplate.id;
                                    }
                                }
                            }
                        }

                        // nâng cấp từ 2 món thần linh
                        if(totalSlDaTL >= slDaTLNeed) {
                            if(totalItemTL == 1) {
                                // kiểm tra trong túi còn có món thần linh nữa không
                                int totalTLInBag = 0;
                                // lặp qua bag
                                for (Item item : player.inventory.itemsBag) {
                                    if (item.isNotNullItem() && item.template.id == tlOne.template.id) {
                                        if(totalTLInBag == 0) {
                                            tlOneInBag = item;
                                        } else if(totalTLInBag == 1) {
                                            tlTwoInBag = item;
                                        }
                                        if(totalTLInBag < 2) {
                                            totalTLInBag += 1;
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                if (totalTLInBag >= 2) {
                                    isValid = true;
                                    isFromTL = true;
                                    // nếu là nhẫn thần linh thì lấy theo gender của player
                                    int nextItemGender = tlOne.template.gender;
                                    if(tlOne.template.gender == 3) {
                                        nextItemGender = player.gender;
                                    }
                                    nextID = getFirstItemByPlanetAndType(nextItemGender, tlOne.template.type);
                                }
                            }
                            // nâng cấp từ SKH
                            else if(totalItemSKH == 1) {
                                if(skhOne != null && skhOne.template != null) {
                                    // check xem trong bag còn skh nào thì mới hợp lệ
                                    int totalSKHInBag = 0;
                                    // lặp qua bag
                                    for (Item item : player.inventory.itemsBag) {
                                        if (item.isNotNullItem() && item.template.id == skhOne.template.id) {
                                            // kiểm tra option của đồ
                                            for (ItemOption ioSKH : item.itemOptions) {
                                                if(ioSKH.optionTemplate.id == ConstOption.SET_SONGOKU || ioSKH.optionTemplate.id == ConstOption.SET_TENSHINHAN || ioSKH.optionTemplate.id == ConstOption.SET_KRILLIN || ioSKH.optionTemplate.id == ConstOption.SET_PICOLO || ioSKH.optionTemplate.id == ConstOption.SET_DENDE || ioSKH.optionTemplate.id == ConstOption.SET_DAIMAO || ioSKH.optionTemplate.id == ConstOption.SET_NAPPA || ioSKH.optionTemplate.id == ConstOption.SET_KAKAROT || ioSKH.optionTemplate.id == ConstOption.SET_CADIC) {
                                                    if(totalSKHInBag < 2) {
                                                        totalSKHInBag += 1;
                                                        if(totalSKHInBag == 1) {
                                                            skhOneInbag = item;
                                                            skhOneOptionID = ioSKH.optionTemplate.id;
                                                        } else {
                                                            skhTwoInbag = item;
                                                            skhTwoOptionID = ioSKH.optionTemplate.id;
                                                        }
                                                    } else {
                                                        break;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    // có skh trong bag thì hợp lệ
                                    if(totalSKHInBag >= 2) {
                                        // và 2 món skh phải nằm trong danh sách đồ nâng skh
                                        nextID = getNextIdInList(skhOne.template.id);
                                        if(nextID != -1) {
                                            isValid = true;
                                            isFromTL = false;
                                        }
                                    }
                                }
                            }
                        }
                        // check hợp lệ
                        if(!isValid) {
                            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần 2 món thần linh hoặc 2 món SKH và x10 đá thần linh", "Đóng");
                        } else {
                            if(nextID != -1) {
                                Item nextSKH = ItemService.gI().createNewItem((short) nextID);
                                if(isFromTL) {
                                    this.quyLaoKame.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                            "Con có muốn nâng cấp " + tlOne.template.name + "\n" + " và 10 đá thần linh thành " + nextSKH.template.name + " SKH không ?", "Nâng cấp\n", "Từ chối");
                                } else {
                                    this.quyLaoKame.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                            "Con có muốn nâng cấp SKH " + skhOne.template.name + "\n" + " và 10 đá thần linh thành " + nextSKH.template.name + " SKH không ?", "Nâng cấp\n", "Từ chối");
                                }
                                break;
                            }
                        }
                    } else {
                        this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 2 món thần linh hoặc 2 món SKH và x10 đá thần linh", "Đóng");
                }
                break;
            case DOI_DA_THAN_LINH:
                if (player.combineNew.itemsCombine.size() == 1) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        Item doTL = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem() && item.isItemThanLinh()) {
                                    doTL = item;
                            }
                        }
                        if(doTL != null) {
                            this.lyTieuNuong.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    "Bạn có muốn đổi " + doTL.template.name + " thành 50 đá thần linh không ?", "Đổi\n", "Từ chối");
                            break;
                        } else {
                            this.lyTieuNuong.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1 đồ thần linh", "Đóng");
                        }
                    } else {
                        this.lyTieuNuong.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.lyTieuNuong.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn một món thần linh", "Đóng");
                }
                break;
            case NANG_CAP_PET:
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        Item pet = null;
                        Item daNguyetTu = null;
                        Item tvv = null;
                        Item daThanhTay = null;
                        boolean isNangCap = false;
                        boolean isTay = false;
                        int maxLevel = 8;
                        int slTVV = 1000;
                        int slDaNguyetTu = 3;
                        int slDaThanhTay = 1;
                        int currentLevel = -1;
                        int sdNguyetTu = -1;
                        int kiNguyetTu = -1;
                        int hpNguyetTu = -1;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                // là pet
                                if(item.itemIsPet()) {
                                    // kiểm tra cấp của pet
                                    for (ItemOption option : item.itemOptions) {
                                        if(option.optionTemplate.id == ConstOption.CAP) {
                                            currentLevel = option.param;
                                        } else if(option.optionTemplate.id == ConstOption.SUC_DANH_NGUYET_TU_TANG_PHAN_TRAM) {
                                            sdNguyetTu = option.param;
                                        } else if(option.optionTemplate.id == ConstOption.HP_NGUYET_TU_TANG_PHAN_TRAM) {
                                            hpNguyetTu = option.param;
                                        } else if(option.optionTemplate.id == ConstOption.KI_NGUYET_TU_TANG_PHAN_TRAM) {
                                            kiNguyetTu = option.param;
                                        }
                                    }
                                    pet = item;
                                } else if(item.template.id == ConstItem.DA_THANH_TAY) {
                                    daThanhTay = item;
                                } else if(item.template.id == ConstItem.DA_NGUYET_TU) {
                                    daNguyetTu = item;
                                } else if(item.template.id == ConstItem.THOI_VANG_VIP) {
                                    tvv = item;
                                }
                            }
                        }
                        // nâng cấp
                        if(daNguyetTu != null) {
                            isNangCap = true;
                            isTay = false;
                        }
                        // tẩy
                        else if(daThanhTay != null) {
                            isNangCap = false;
                            isTay = true;
                        }
                        // mặc định là nâng cấp
                        else {
                            isNangCap = true;
                            isTay = false;
                        }
                        if (currentLevel >= maxLevel && isNangCap) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Pet đã đạt cấp độ tối đa", "Đóng");
                        }
                        else if (pet == null) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1 pet", "Đóng");
                        }
                        else if (tvv == null || tvv.quantity < slTVV) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1k TVV", "Đóng");
                        } else if(isNangCap && (daNguyetTu == null || daNguyetTu.quantity < slDaNguyetTu)) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có x3 Đá Nguyệt Tử", "Đóng");
                        } else if(isTay && (daThanhTay == null || daThanhTay.quantity < slDaThanhTay)) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có x1 Đá Thanh Tẩy", "Đóng");
                        } else {
                            if(isNangCap) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn nâng cấp " + pet.template.name + " không ?", "Nâng cấp\n", "Từ chối");
                            } else if(isTay) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn tẩy cấp " + pet.template.name + " không ?", "Tẩy cấp\n", "Từ chối");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 pet, x3 Đá Nguyệt Tử, 1k TVV để nâng cấp\nHoặc 1 pet, x1 Đá Thanh Tẩy, 1k TVV để tẩy cấp", "Đóng");
                }
                break;
            case CHUYEN_HOA_SKH_HUY_DIET:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        int slTVV = 10_000;
                        Item coin = null;
                        Item skhThan = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if(item.template.id == ConstItem.THOI_VANG_VIP) {
                                    coin = item;
                                } else if (item.isNotNullItem() && item.isItemThanLinh()) {
                                    for (ItemOption io : item.itemOptions) {
                                        if (io.optionTemplate.id == 129 || io.optionTemplate.id == 248 || io.optionTemplate.id == 128 || io.optionTemplate.id == 135 || io.optionTemplate.id == 134 || io.optionTemplate.id == 133 || io.optionTemplate.id == 130 || io.optionTemplate.id == 131 || io.optionTemplate.id == 132) {
                                            skhThan = item;
                                        }
                                    }
                                }
                            }
                        }
                        if (coin == null || coin.quantity < slTVV) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 10k Thỏi Vàng Vip để thực hiện chuyển hóa", "Đóng");
                        } else {
                            if (skhThan != null) {
                                this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn chuyển hóa " + skhThan.template.name + "\n" + " và 10k Thỏi vàng VIP để đổi lấy x1 SKH Hủy Diệt không ?", "Chuyển hóa\n", "Từ chối");
                                break;
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Con cần có 1 món SKH Thần", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 SKH Thần và 10k Thỏi vàng VIP để chuyển hóa", "Đóng");
                }
                break;
            case KHAM_BUA:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        Item trangBi = null;
                        Item bua = null;
                        boolean isKham = false;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                // là đồ mặc
                                if(item.itemIsClothes()) {
                                    // kiểm tra xem đồ được khảm chưa
                                    for (ItemOption option : item.itemOptions) {
                                        if(option.optionTemplate.id == 198 || option.optionTemplate.id == 186 || option.optionTemplate.id == 163) {
                                            isKham = true;
                                        }
                                    }
                                    trangBi = item;
                                }
                                // có bùa pháp sư
                                else if (item.template.id == ConstItem.BUA_PHAP_SU_HP || item.template.id == ConstItem.BUA_PHAP_SU_KI || item.template.id == ConstItem.BUA_PHAP_SU_SD) {
                                    bua = item;
                                }
                            }
                        }
                        if (bua == null || bua.quantity < 10) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 10 bùa pháp sư để khảm bùa", "Đóng");
                        } else {
                            if (trangBi != null) {
                                if(isKham) {
                                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                            "Trang bị đã khảm bùa rồi con ơi", "Đóng");
                                } else {
                                    this.baHatMit.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                            "Bạn có muốn khảm bùa " + trangBi.template.name + "\n" + " và 10 bùa pháp sư không ?", "Khảm bùa\n", "Từ chối");
                                }
                                break;
                            } else {
                                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Con cần có 1 trang bị trong số áo, quần găng, giày, rada", "Đóng");
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 trang bị và 10 bùa pháp sư để khảm bùa", "Đóng");
                }
                break;
            case PHA_LE_HOA_VONG_THIEN_SU:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        int slTVV = 50_000;
                        Item coin = null;
                        Item vongThienSu = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if(item.template.id == ConstItem.THOI_VANG_VIP) {
                                    coin = item;
                                } else if (item.isNotNullItem() && item.template.id == ConstItem.VONG_THIEN_SU) {
                                    boolean isValid = true;
                                    for (ItemOption option : item.itemOptions) {
                                        if(option.optionTemplate.id == 72 && option.param >= 7) {
                                            isValid = false;
                                        }
                                    }
                                    if (isValid) {
                                        vongThienSu = item;
                                    }
                                }
                            }
                        }
                        if (coin == null || coin.quantity < slTVV) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 50k Thỏi Vàng Vip để thực hiện pha lê hóa", "Đóng");
                        } else {
                            if (vongThienSu != null) {
                                this.cayNeu.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn pha lê hóa " + vongThienSu.template.name + "\n" + " và 50k Thỏi vàng VIP không ?", "Pha lê hóa\n", "Từ chối");
                                break;
                            } else {
                                this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Con cần có 1 vòng thiên sứ chưa đạt cấp 7", "Đóng");
                            }
                        }
                    } else {
                        this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 vòng thiên sứ và 50k Thỏi vàng VIP để pha lê hóa", "Đóng");
                }
                break;
            case NANG_CAP_CAI_TRANG_HOA_XUONG:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        int slTVV = 100_000;
                        Item coin = null;
                        Item caiTrangHoaXuong = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if(item.template.id == ConstItem.THOI_VANG_VIP) {
                                    coin = item;
                                } else if (item.isNotNullItem() && (item.template.id == ConstItem.CAI_TRANG_NGO_KHONG || item.template.id == ConstItem.CAI_TRANG_DUONG_TANG)) {
                                    boolean isValid = true;
                                    for (ItemOption option : item.itemOptions) {
                                        if(option.optionTemplate.id == 72 && option.param >= 7) {
                                            isValid = false;
                                        }
                                    }
                                    if (isValid) {
                                        caiTrangHoaXuong = item;
                                    }
                                }
                            }
                        }
                        if (coin == null || coin.quantity < slTVV) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 100k Thỏi Vàng Vip để thực hiện nâng cấp", "Đóng");
                        } else {
                            if (caiTrangHoaXuong != null) {
                                this.cayNeu.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn nâng cấp " + caiTrangHoaXuong.template.name + "\n" + " và 100k Thỏi vàng VIP không ?", "Nâng cấp\n", "Từ chối");
                                break;
                            } else {
                                this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                        "Con cần có 1 cải trang Ngộ Không hoặc Đường Tăng chưa đạt cấp 7", "Đóng");
                            }
                        }
                    } else {
                        this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 cải trang Ngộ Không hoặc Đường Tăng và 100k Thỏi vàng VIP để nâng cấp", "Đóng");
                }
                break;
            case CHE_TAO_BANH_QUY:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        Item keoGiangSinh = null;
                        Item botMi = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if(item.template.id == ConstItem.KEO_GIANG_SINH) {
                                    keoGiangSinh = item;
                                } else if (item.isNotNullItem() && item.template.id == ConstItem.BOT_MI) {
                                    botMi = item;
                                }
                            }
                        }
                        if (keoGiangSinh == null || keoGiangSinh.quantity < 1) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1 kẹo giáng sinh để chế tạo", "Đóng");
                        }
                        else if(botMi == null || botMi.quantity < 1) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1 bột mì để chế tạo", "Đóng");
                        }
                        else {
                                this.cayNeu.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                        "Bạn có muốn chế tạo bánh quy không ?", "Chế tạo\n", "Từ chối");
                                break;
                        }
                    } else {
                        this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 kẹo giáng sinh và 1 bột mì để chế tạo", "Đóng");
                }
                break;
            case CHE_TAO_KEO_GIANG_SINH:
                if (player.combineNew.itemsCombine.size() == 2) {
                    if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                        Item gayGo = null;
                        Item keoDuong = null;
                        for (Item item : player.combineNew.itemsCombine) {
                            if (item.isNotNullItem()) {
                                if(item.template.id == ConstItem.GAY_GO) {
                                    gayGo = item;
                                } else if (item.isNotNullItem() && item.template.id == ConstItem.KEO_DUONG) {
                                    keoDuong = item;
                                }
                            }
                        }
                        if (gayGo == null || gayGo.quantity < 1) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 1 gậy gỗ để chế tạo", "Đóng");
                        }
                        else if(keoDuong == null || keoDuong.quantity < 2) {
                            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Con cần có 2 kẹo đường để chế tạo", "Đóng");
                        }
                        else {
                            this.cayNeu.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                                    "Bạn có muốn chế tạo kẹo giáng sinh không ?", "Chế tạo\n", "Từ chối");
                            break;
                        }
                    } else {
                        this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Hàng trang đã đầy", "Đóng");
                    }
                } else {
                    this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 gậy gỗ và 2 kẹo đường để chế tạo", "Đóng");
                }
                break;
        }
    }

    private void upgradeSKHNewConfirm(Player player, int itemId) {
        int combineSize = player.combineNew.itemsCombine.size();
        if (((combineSize == 2 || combineSize == 3) && itemId == ConstItem.THOI_VANG_VIP) ||
                ((combineSize == 1 || combineSize == 2) && itemId == ConstItem.HONG_NGOC)) {
            // case không có đồ thần
            int ratioNangSKH = itemId == ConstItem.THOI_VANG_VIP ? 5 : 1;
            Item coin2008 = null;
            Item skhNew = null;
            Item dtl = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (ItemUtil.isNewSKH(player, item)) {
                    skhNew = item;
                }
                if (item.template.id == ConstItem.THOI_VANG_VIP) {
                    coin2008 = item;
                }
                if (item.isDTL() && !item.isParamSKH()) {
                    ratioNangSKH *= 2;
                    dtl = item;
                }
            }

            if (itemId == ConstItem.THOI_VANG_VIP) {
                if (Objects.isNull(coin2008)) {
                    this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy bỏ 5000 " + ConstItemName.MONEY_UNIT + " vào", "Đóng");

                    return;
                }
                if (coin2008.quantity < 5000) {
                    this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 5000 " + ConstItemName.MONEY_UNIT, "Đóng");
                    return;
                }
            } else {
                if (player.inventory.ruby < 20000) {
                    this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần tối thiểu 20.000 Hồng ngọc", "Đóng");

                    return;
                }
            }

            if (Objects.isNull(skhNew)) {
                this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Không tìm thấy SKH", "Đóng");
                return;
            }

            if(ItemUtil.isNewSKHAndCanUpgrade(player,skhNew) == false){
                this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Sét kích hoạt đã đạt tối đa chỉ số", "Đóng");
                return;
            }

            if (combineSize == 3 && Objects.isNull(dtl)) {
                this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU, "không tìm thấy item đồ thần linh", "Đóng");
                return;
            }

            String content = "Xác nhận nâng cấp \ntỉ lệ là " + ratioNangSKH;
            this.duongtank.createOtherMenu(player, ConstNpc.MENU_START_COMBINE,
                    content, "Nâng cấp", "Từ chối");
        } else {
            this.duongtank.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                    "Cần một món SKH có thể nâng cấp và "  +
                            ((itemId == ConstItem.THOI_VANG_VIP) ? "5k " + ConstItemName.MONEY_UNIT : "10k Hồng ngọc") +
                            " + 1 món thần linh nếu có đồ thần linh sẽ tăng gấp đôi tỉ lệ thành công", "Đóng");
        }
    }

    private static int getRubyPlhByStar(int star, int ruby) {
        switch (star) {
            case 5:
                ruby = 100;
                break;
            case 6:
                ruby = 200;
                break;
            case 7:
                ruby = 500;
                break;
            default:
                break;
        }
        return ruby;
    }

    /**
     * Bắt đầu đập đồ - điều hướng từng loại đập đồ
     *
     * @param player
     */
    public void startCombine(Player player) {
        if (Util.canDoWithTime(player.combineNew.lastTimeCombine, TIME_COMBINE)) {
            switch (player.combineNew.typeCombine) {
                case NANG_CAP_NHAN:
                    nangCapNhan(player);
                    break;
                case GIAM_DINH:
                    ExcellentBook.gI().appraisal(player);
                    break;
                case TAY_SACH:
                    ExcellentBook.gI().easerBook(player);
                    break;
                case NANG_SACH:
                    ExcellentBook.gI().upgradeBook(player);
                    break;
                case PHUC_HOI:
                    ExcellentBook.gI().healBook(player);
                    break;
                case PHAN_RA:
                    ExcellentBook.gI().distributeBook(player);
                    break;
                case UPDATE_TRAIN_ARMOR:
                    updateTrainArmor(player);
                    break;
                case NANG_CAP_DE_WHIS:
                    petWhis(player);
                    break;
                case NANG_CAP_BONG_TAI_CAP3:
                    nangCapBongTaiCap3(player);
                    break;
                case PHA_LE_HOA_CAI_TRANG:
                    phaLeHoaCaiTrang(player);
                    break;
                case PHA_LE_HOA_DISGUISE:
                    phalehoaDisguise(player);
                    break;
                case TRADE_PET:
                    tradePet(player);
                    break;
                case UPGRADE_PET:
                    upgradePet(player);
                    break;
                case UPGRADE_PET_CELL:
                    upgradeCell(player);
                    break;
                case UPGRADE_THAN_LINH:
                    dapDoThanLinh(player);
                    break;
                case UPGRADE_HUY_DIET:
                    dapDoHuydiet(player);
                    break;
                case UPGRADE_LINHTHU:
                    upgradeLinhThu(player);
                    break;
                case UPGRADE_CAITRANG:
                    upgradeDisguise(player);
                    break;
                case NANG_CAP_DO_KICH_HOAT:
                    dapDoKichHoat(player);
                    break;
                case TRADE_DO_THAN_LINH:
                    tradeDoThanLinh(player);
                case EP_SAO_TRANG_BI:
                    epSaoTrangBi(player);
                    break;
                case PHA_LE_HOA_TRANG_BI:
                    phaLeHoaTrangBi(player);
                    break;
                case NANG_CAP_DO_TS:
                    openDTS(player);
                    break;
                case NHAP_NGOC_RONG:
                    nhapNgocRong(player);
                    break;
                case EP_NGOC_HAC_AM:
                    epNgocHacAm(player);
                    break;
                case EP_NGOC_RONG_BANG:
                    epNgocRongBang(player);
                    break;
                case NANG_CAP_CAI_TRANG:
                    nangCapCaiTrang(player);
                    break;
                case REMOVE_OPTION:
                    removeItemOptions(player);
                    break;
                case NANG_SKH_NEW:
                    nangSKHNew(player, ConstItem.THOI_VANG_VIP);
                    break;
                case NANG_SKH_NEW_RUBY:
                    nangSKHNew(player, ConstItem.HONG_NGOC);
                    break;
                case NANG_CHAN_MENH:
                    nangcaphonhoan(player);
                    break;
                case NANG_CAP_VAT_PHAM:
                    nangCapVatPham(player);
                    break;
                case DOI_VE_HUY_DIET:
                    doiVeHuyDiet(player);
                    break;
                case GIA_HAN_CAI_TRANG:
                    giaHanCaiTrang(player);
                    break;
                case NANG_CAP_DO_THIEN_SU:
                    nangCapDoThienSu(player);
                    break;
                case PHA_LE_HOA_TRANG_BI_X10:
                    phaLeHoaTrangBiX10(player);
                    break;
                case NANG_CAP_BONG_TAI:
                    nangCapBongTai(player);
                    break;
                case MO_CHI_SO_BONG_TAI:
                    moChiSoBongTai(player);
                    break;
                case DAP_BONG_TAI_CAP_3:
                    dapBongTaiCap3(player);
                    break;
                case CHUYEN_HOA_SKH_HUY_DIET:
                    chuyenHoaSKHHuyDiet(player);
                    break;
                case KHAM_BUA:
                    khamBua(player);
                    break;
                case PHA_LE_HOA_VONG_THIEN_SU:
                    phaLeHoaVongThienSu(player);
                    break;
                case CHE_TAO_BANH_QUY:
                    cheTaoBanhQuy(player);
                    break;
                case CHE_TAO_KEO_GIANG_SINH:
                    cheTaoKeoGiangSinh(player);
                    break;
                case NANG_CAP_CAI_TRANG_HOA_XUONG:
                    nangCapCaiTrangHoaXuong(player);
                    break;
                case NANG_CAP_SKH:
                    nangCapSKH(player);
                    break;
                case NANG_CAP_PET:
                    nangCapPet(player);
                    break;
                case DOI_DA_THAN_LINH:
                    doiDaThanLinh(player);
                    break;
            }
            player.iDMark.setIndexMenu(ConstNpc.IGNORE_MENU);
            player.combineNew.clearParamCombine();
            player.combineNew.lastTimeCombine = System.currentTimeMillis();
        } else {
            Service.getInstance().sendThongBao(player, "@@");
        }
    }

    private void nangSKHNew(Player player, int itemId) {
        try {
            int combineSize = player.combineNew.itemsCombine.size();
            if ((((combineSize == 2 || combineSize == 3) && itemId == ConstItem.THOI_VANG_VIP) ||
                    ((combineSize == 1 || combineSize == 2) && itemId == ConstItem.HONG_NGOC))) {
                int ratioNangSKH = itemId == ConstItem.THOI_VANG_VIP ? 5 : 1;
                Item coin2008 = null;
                Item skhNew = null;
                Item dtl = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if (ItemUtil.isNewSKHAndCanUpgrade(player, item)) {
                        skhNew = item;
                    }
                    if (item.template.id == ConstItem.THOI_VANG_VIP) {
                        coin2008 = item;
                    }
                    if (item.isDTL() && !item.isParamSKH()) {
                        ratioNangSKH *= 2;
                        dtl = item;
                    }
                }
                if (itemId == ConstItem.THOI_VANG_VIP) {
                    if (Objects.isNull(coin2008)) {
                        Service.gI().sendThongBao(player, "không tìm thấy " + ConstItemName.MONEY_UNIT);
                        return;
                    }
                    if (coin2008.quantity < 5000) {
                        Service.gI().sendThongBao(player, "Thiếu item " + ConstItemName.MONEY_UNIT);
                        return;
                    }
                } else {
                    if (player.inventory.ruby < 20000) {
                        Service.gI().sendThongBao(player, "Thiếu Hồng ngọc");
                        return;
                    }
                }

                if (Objects.isNull(skhNew)) {
                    Service.gI().sendThongBao(player, "không tìm thấy đồ kích hoạt");
                    return;
                }

                if (combineSize == 3 && Objects.isNull(dtl)) {
                    Service.gI().sendThongBao(player, "không tìm thấy đồ thần linh");
                    return;
                }

                if (itemId == ConstItem.THOI_VANG_VIP) {
                    InventoryService.gI().subQuantityItemsBag(player, coin2008, 5000);
                } else {
                    player.inventory.subRuby(20000);
                    Service.getInstance().sendMoney(player);
                }
                if (Objects.nonNull(dtl) && dtl.isNotNullItem()) {
                    InventoryService.gI().subQuantityItemsBag(player, dtl, 1);
                }
                if (Util.isTrue(ratioNangSKH, 100)) {
                    hanldeNangSKHNew(player, skhNew);
                } else {
                    sendEffectFailCombine(player);
                }

                InventoryService.gI().sendItemBags(player);
                reOpenItemCombine(player);

            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Cần một món SKH có thể nâng cấp và "  +
                                ((itemId == ConstItem.THOI_VANG_VIP) ? "5k " + ConstItemName.MONEY_UNIT : "10k Hồng ngọc") +
                                " + 1 món thần linh nếu có đồ thần linh sẽ tăng gấp đôi tỉ lệ thành công", "Đóng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Service.gI().sendThongBao(player, "Đã có lỗi xảy ra, vui lòng thử lại");
        }
    }

    private void hanldeNangSKHNew(Player player, Item skhNew) {
        int ratioMax = 0;
        int currentRatio = 0;
        int percentAdd = 0;
        for (ItemOption itemOption : skhNew.itemOptions) {
            switch (itemOption.optionTemplate.id) {
                case ConstOption.OPTION_PERCENT_HP:
//                case NPoint.OPTION_PERCENT_KI:
                    ratioMax = 200;
                    currentRatio = itemOption.param;
                    percentAdd = 10;
                    if (currentRatio + percentAdd <= ratioMax) {
                        itemOption.param += percentAdd;
                    } else {
                        Service.gI().sendThongBao(player, "SKH đã đạt chỉ số tối đa");
                        return;
                    }
                    break;
                case ConstOption.OPTION_PERCENT_KI:
                    ratioMax = 200;
                    currentRatio = itemOption.param;
                    percentAdd = 10;
                    if (currentRatio + percentAdd <= ratioMax) {
                        itemOption.param += percentAdd;
                    } else {
                        Service.gI().sendThongBao(player, "SKH đã đạt chỉ số tối đa");
                        return;
                    }
                    break;
                case ConstOption.OPTION_PERCENT_KAMEJOKO:
                case ConstOption.OPTION_PERCENT_LIEN_HOAN:
                    ratioMax = 150;
                    currentRatio = itemOption.param;
                    percentAdd = 5;

                    if (currentRatio + percentAdd <= ratioMax) {
                        itemOption.param += percentAdd;
                    } else {
                        Service.gI().sendThongBao(player, "SKH đã đạt chỉ số tối đa");
                        return;
                    }
                    break;
            }
        }
        sendEffectSuccessCombine(player);
    }

    private void nangCapNhan(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item nhan = null;
            Item coin = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id == ConstItem.THOI_VANG_VIP) {
                    coin = item;
                } else if (item.template.id == 2110) {
                    nhan = item;
                }
            }
            int soluong = 10000;
            if (coin != null && coin.quantity >= soluong) {
                ItemOption option = null;
                ItemOption option2 = null;
                ItemOption option3 = null;
                if (nhan != null) {
                    int level = 0;
                    ItemOption optionLevel = null;
                    for (ItemOption io : nhan.itemOptions) {
                        if (io.optionTemplate.id == 72) {
                            level = io.param;
                            optionLevel = io;
                            break;
                        }
                    }
                    if (level < 7) {
                        int tile = level < 5 ? 50 : 10;
                        if (Util.isTrue(tile, 100)) {
                            for (ItemOption io : nhan.itemOptions) {
                                if (io.optionTemplate.id == 50) {
                                    option = io;
                                }
                                if (io.optionTemplate.id == 77) {
                                    option2 = io;
                                }
                                if (io.optionTemplate.id == 103) {
                                    option3 = io;
                                }
                            }
                            int pt = level < 5 ? 3 : 10;
                            if (option != null) {
                                option.param += pt;
                            }
                            if (option2 != null) {
                                option2.param += pt;
                            }
                            if (option3 != null) {
                                option3.param += pt;
                            }
                            if (optionLevel != null) {
                                optionLevel.param++;
                            }
                            InventoryService.gI().subQuantityItemsBag(player, coin, soluong);
                            sendEffectSuccessCombine(player);
                        } else {
                            InventoryService.gI().subQuantityItemsBag(player, coin, soluong);
                            sendEffectFailCombine(player);
                        }
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    } else {
                        Service.getInstance().sendThongBao(player, "Đã đạt cấp tối đa");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Không đủ đồ");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Không đủ coin");
            }
        }
    }

    private void nangcaphonhoan(Player player) {

        if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 97).count() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu chân mệnh");
            return;
        }

        if (player.combineNew.itemsCombine.size() != 2) {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
            return;
        }
        if (InventoryService.gI().getCountEmptyBag(player) < 1) {
            Service.getInstance().sendThongBao(player, "Hành trang không đủ ô trống!");
            return;
        }
        Item itemChanMenh = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 97).findFirst().get();
        Item da = player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == ConstItem.THOI_VANG_VIP).findAny().get();
        if (itemChanMenh.quantity < 1) {
            Service.getInstance().sendThongBaoOK(player, "Thiếu chân mệnh!");
            return;
        }
        if (itemChanMenh.template.id >= 1327) {
            Service.getInstance().sendThongBaoOK(player, "Đã đạt cấp tối đa!!");
            return;
        }
        if (da.quantity < 10_000) {
            Service.getInstance().sendThongBaoOK(player, "Thiếu " + ConstItemName.MONEY_UNIT);
            return;
        }
        if (da.template.id != ConstItem.THOI_VANG_VIP) {
            Service.getInstance().sendThongBaoOK(player, "Thiếu " + ConstItemName.MONEY_UNIT);
            return;
        }
        if (Util.isTrue(getTileNangHonHoan(itemChanMenh.template.id), 100)) {
            ItemOption option = null;
            Item chanMenh = ItemService.gI().createNewItem((short) (itemChanMenh.template.id + 1));
            for (ItemOption io : itemChanMenh.itemOptions) {
                if (io.optionTemplate.id == 50 || io.optionTemplate.id == 77 || io.optionTemplate.id == 103) {
                    io.param += 5;
                }
                if (io.optionTemplate.id == 72) {
                    io.param += 1;
                }
//                if(io.optionTemplate.id == 72){
//                    option = io;
//                }
//                if(io.optionTemplate.id == 72 && io.param < 7){
//                        io.param++;
//                }
//                if(option == null){
//                    io.param++;
//                }
            }

            chanMenh.itemOptions.addAll(itemChanMenh.itemOptions);
            InventoryService.gI().addItemBag(player, chanMenh, -1);
            sendEffectSuccessCombine(player);
            InventoryService.gI().subQuantityItemsBag(player, da, 10_000);
            InventoryService.gI().subQuantityItemsBag(player, itemChanMenh, 1);
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Thành công", "Đóng");

            String str = player.name + "Đã nâng thành công " + itemChanMenh.template.name + " thật là đẹp trai ";

            ServerNotify.gI().notify(str);
        } else {
            InventoryService.gI().subQuantityItemsBag(player, da, 10_000);
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Xịt rồi ku", "Đóng");

        }
//        player.inventory.subRuby(10000);
        Service.getInstance().sendMoney(player);
        InventoryService.gI().sendItemBags(player);
        reOpenItemCombine(player);
        player.combineNew.itemsCombine.clear();
    }

    private Item removeAndAddEmptyStartItem(Item itemCanCombine) {
        int countStarBefore = itemCanCombine.getQuantityStar();
        System.out.println("countStarBefore" + countStarBefore);
        List<ItemOption> copy = new ArrayList<>(itemCanCombine.itemOptions);
        copy.removeIf(option -> ConstCombine.itemOptionsCanRemove.contains(option.optionTemplate.id));
        itemCanCombine.itemOptions = copy;
        int countStarCurrent = itemCanCombine.getQuantityStar();
        int diff = countStarBefore - countStarCurrent;
        diff = (diff == 0) ? countStarBefore : diff;

        for (int i = 0; i < diff; i++) {
            System.out.println("Ok");
            itemCanCombine.itemOptions.add(new ItemOption(102, 0));
        }
        return itemCanCombine;
    }

    private void removeItemOptions(Player player) {
        Item itemCanCombine = checkItemCanCombine(player);
        if (Objects.isNull(itemCanCombine)) {
            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Text 7", "Đóng");
            return;
        }
        // ekko tẩy sao pha lê tốn 200k hồng ngọc
        player.inventory.subRuby(ConstCombine.COST_REMOVE_OPTION);
        if (Util.isTrue(ConstCombine.RATIO_REMOVE_OPTION, 100)) {
            removeAndAddEmptyStartItem(itemCanCombine);
            this.sendEffectSuccessCombine(player);
        } else {
            this.sendEffectFailCombine(player);
        }
//        System.out.println("Item 2008 " + item2008.template.name + "|" + item2008.quantity);
        Service.getInstance().sendMoney(player);
        InventoryService.gI().sendItemBags(player);
        reOpenItemCombine(player);
    }

    private void nangCapCaiTrang(Player player) {
        if (player.combineNew.itemsCombine.size() != 2) {
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần mang linh hồn cải trang và 1 cải trang bất kì", "Đóng");
            return;
        }
        Item linhHonCt = null;
        Item caiTrang = null;
        for (Item item :
                player.combineNew.itemsCombine) {
            if (item.template.id == ConstItem.LINH_HON_CAI_TRANG || item.template.id == ConstItem.LINH_HON_CAI_TRANG2) {
                linhHonCt = item;
            }
            if (item.template.type == 5) {
                caiTrang = item;
            }
        }
        if (linhHonCt == null || linhHonCt.quantity < 99) {
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần x99 linh hồn cải trang", "Đóng");
            return;

        }
        if (caiTrang == null || caiTrang.getLevel() >= 7) {
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 1 cải trang thấp hơn 7 sao", "Đóng");
            return;

        }
        if (player.inventory.ruby < 50_000) {
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Cần 50k hồng ngọc", "Đóng");
            return;

        }

        float tiLe = 20f;
        if (Util.isTrue(tiLe, 100)) {
//            sendEffectSuccessCombine(player);
            if (caiTrang.getLevel() == 0) {
                caiTrang.itemOptions.add(new ItemOption(72, 1));
            } else {
                for (ItemOption itemOption : caiTrang.itemOptions) {
                    if (itemOption.optionTemplate.id == 72 && itemOption.param < 7) {
                        itemOption.param++;
                        break;
                    }
                }
            }
            if (caiTrang.getSaoDen() == 0) {
                caiTrang.itemOptions.add(new ItemOption(107, 1));
            } else {
                for (ItemOption itemOption : caiTrang.itemOptions) {
                    if (itemOption.optionTemplate.id == 107 && itemOption.param < 7) {
                        itemOption.param++;
                        break;
                    }
                }
            }
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Á đù lên rồii");
        } else {
            Service.getInstance().sendThongBao(player, "Xịtttt");

        }
        InventoryService.gI().subQuantityItemsBag(player, linhHonCt, 99);
        InventoryService.gI().sendItemBags(player);
        int oldRuby = player.inventory.ruby;
        player.inventory.addRuby(-50_000);
        // ekko ghi log add ruby
        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-nangCapCaiTrang");

        Service.getInstance().sendMoney(player);
        reOpenItemCombine(player);

    }

    public void startCombineStone(Player player, int type) {
        if (Util.canDoWithTime(player.combineNew.lastTimeCombine, TIME_COMBINE)) {
            int tp = 0;
            switch (type) {
                case UPGRADE_POWER_STONE:
                    tp = 1;
                    openOptionStone(player, tp);
                    break;
                case UPGRADE_POWER_STONE_COINT:
                    tp = 2;
                    openOptionStone(player, tp);
                    break;
            }
            player.iDMark.setIndexMenu(ConstNpc.IGNORE_MENU);
            player.combineNew.clearParamCombine();
            player.combineNew.lastTimeCombine = System.currentTimeMillis();
        } else {
            Service.getInstance().sendThongBao(player, "@@");
        }
    }

    private void openOptionStone(Player player, int type) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item it = null;
            for (Item item : player.combineNew.itemsCombine) {
                switch (item.template.id) {
                    case 1956:
                        it = item;
                        break;
                    default:
                        break;
                }
            }
            if (it != null) {
                switch (type) {
                    case 1:
                        if (player.inventory.ruby < 20_000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn thiếu 20K ruby", "Đóng");
                            Service.getInstance().sendThongBao(player, "Bạn thiếu 20K ruby");
                            return;
                        }
                        if (Util.isTrue(20, 100)) {
                            it.itemOptions.clear();
                            int rdUp = Util.nextInt(0, 7);
                            switch (rdUp) {
                                case 0:
                                    it.itemOptions.add(new ItemOption(50, Util.nextInt(1, 15)));
                                    break;
                                case 1:
                                    it.itemOptions.add(new ItemOption(77, Util.nextInt(1, 15)));
                                    break;
                                case 2:
                                    it.itemOptions.add(new ItemOption(103, Util.nextInt(1, 15)));
                                    break;
                                case 3:
                                    it.itemOptions.add(new ItemOption(108, Util.nextInt(1, 15)));
                                    break;
                                case 4:
                                    it.itemOptions.add(new ItemOption(94, Util.nextInt(1, 15)));
                                    break;
                                case 5:
                                    it.itemOptions.add(new ItemOption(14, Util.nextInt(1, 15)));
                                    break;
                                case 6:
                                    it.itemOptions.add(new ItemOption(80, Util.nextInt(1, 15)));
                                    break;
                                case 7:
                                    it.itemOptions.add(new ItemOption(81, Util.nextInt(5, 25)));
                                    break;
                            }
                            it.itemOptions.add(new ItemOption(241, 0));
                            sendEffectSuccessCombine(player);
                            Service.getInstance().sendThongBao(player, "Nâng cấp đã Thành Công!!");
                        } else {
                            sendEffectFailCombine(player);
                        }
                        player.inventory.subRuby(20_000);
                        break;
                    case 2:
                        Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                        if (pi == null || pi.quantity < 5_000) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Bạn thiếu 5k " + ConstItemName.MONEY_UNIT, "Đóng");
                            Service.getInstance().sendThongBao(player, "Bạn thiếu 5k " + ConstItemName.MONEY_UNIT);
                            return;
                        }
                        if (Util.isTrue(20, 100)) {
                            it.itemOptions.clear();
                            int rdUp = Util.nextInt(0, 7);
                            switch (rdUp) {
                                case 0:
                                    it.itemOptions.add(new ItemOption(50, Util.nextInt(1, 15)));
                                    break;
                                case 1:
                                    it.itemOptions.add(new ItemOption(77, Util.nextInt(1, 15)));
                                    break;
                                case 2:
                                    it.itemOptions.add(new ItemOption(103, Util.nextInt(1, 15)));
                                    break;
                                case 3:
                                    it.itemOptions.add(new ItemOption(108, Util.nextInt(1, 15)));
                                    break;
                                case 4:
                                    it.itemOptions.add(new ItemOption(94, Util.nextInt(1, 15)));
                                    break;
                                case 5:
                                    it.itemOptions.add(new ItemOption(14, Util.nextInt(1, 15)));
                                    break;
                                case 6:
                                    it.itemOptions.add(new ItemOption(80, Util.nextInt(1, 15)));
                                    break;
                                case 7:
                                    it.itemOptions.add(new ItemOption(81, Util.nextInt(1, 15)));
                                    break;
                            }
                            it.itemOptions.add(new ItemOption(241, 0));
                            sendEffectSuccessCombine(player);
                            Service.getInstance().sendThongBao(player, "Nâng cấp đã Thành Công!!");
                        } else {
                            sendEffectFailCombine(player);
                        }
                        InventoryService.gI().subQuantityItemsBag(player, pi, 5_000);
                        break;

                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void phaLeHoaTrangBiX10(Player player) {
        for (int i = 0; i < 100; i++) { // số lần pha lê hóa
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.getInstance().sendThongBao(player,
                        "Thiếu ngọc");
                return;
            }
            if (phaLeHoaTrangBi(player)) {
                Service.getInstance().sendThongBao(player,
                        "Chúc mừng bạn đã pha lê hóa thành công,tổng số lần nâng cấp là: " + i);
                break;
            }
        }
    }

    private void updateTrainArmor(Player player) {
        switch (player.combineNew.itemsCombine.size()) {
            case 1:
                Item thang = null;
                int quantity = 99;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem()) {
                        if (item.template.id == 2031) {// thang tinh thạch
                            thang = item;
                        }
                    }
                }
                if (thang == null && thang.quantity < quantity) {
                    Service.getInstance().sendThongBao(player, "Thiếu x99 Thăng Tinh Thạch");
                    return;
                }
                Item glt = ItemService.gI().createNewItem((short) (529));
                glt.itemOptions.add(new ItemOption(9, 0));
                InventoryService.gI().subQuantityItemsBag(player, thang, quantity);
                InventoryService.gI().addItemBag(player, glt, 99);
                InventoryService.gI().sendItemBags(player);
                endCombine(player);
                break;
            case 2:
                Item TTT = null;
                Item GLT_1 = null;
                Item GLT_2 = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem()) {
                        switch (item.template.id) {
                            case 2031:
                                TTT = item;
                                break;
                            case 529:
                                GLT_1 = item;
                                break;
                            case 530:
                                GLT_2 = item;
                                break;
                        }
                    }
                }
                if (TTT == null) {
                    Service.getInstance().sendThongBao(player, "Đưa cho thần thiếp Thăng Tinh Thạch ạ");
                    return;
                }
                if (GLT_2 == null || GLT_1 == null) {
                    int requiredQuantity = (GLT_1 == null) ? 9999 : 999;
                    if (TTT.quantity > requiredQuantity) {
                        if (Util.isTrue(50, 100)) {
                            Item gltC = ItemService.gI().createNewItem((short) ((GLT_1 == null) ? 531 : 530));
                            gltC.itemOptions.add(new ItemOption(9, 0));
                            InventoryService.gI().subQuantityItemsBag(player, TTT, requiredQuantity);
                            InventoryService.gI().subQuantityItemsBag(player, (GLT_1 == null) ? GLT_2 : GLT_1, 1);
                            player.inventory.subGem((GLT_1 == null) ? 10000 : 0);
                            InventoryService.gI().addItemBag(player, gltC, 99);

                            InventoryService.gI().sendItemBags(player);
                            endCombine(player);
                        } else {
                            sendEffectFailCombine(player);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Thiếu Thăng Tinh Thạch");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Đưa tao con dao");
                }
                break;
            default:
                Service.getInstance().sendThongBao(player, "Mày nhét cái máu lul gì vào tao vậy @@");
                break;
        }
    }

    private void nangCapBongTaiCap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item bongtai = null;
            Item bikiep = null;
            for (Item item : player.combineNew.itemsCombine) {
                switch (item.template.id) {
                    case 921:
                        bongtai = item;
                        break;
                    case ConstItem.BINH_NUOC:
                        bikiep = item;
                        break;
                }
            }
            if (bongtai != null && bikiep != null) {
                int level = 0;
                for (ItemOption io : bongtai.itemOptions) {
                    if (io.optionTemplate.id == 72) {
                        level = io.param;
                        break;
                    }
                }
                if (level < 3) {
                    int lvbt = lvbt(bongtai);
                    int countmvbt = 9999;
                    if (countmvbt > bikiep.quantity) {
                        Service.getInstance().sendThongBao(player, "Không " + ConstItemName.BINH_NUOC);
                        return;
                    }

                    InventoryService.gI().subQuantityItemsBag(player, bikiep, 9999);
                    if (Util.isTrue(50, 100)) {
                        bongtai.template = ItemService.gI().getTemplate(getidbtsaukhilencap(lvbt));
                        bongtai.itemOptions.add(new ItemOption(72, lvbt + 1));
                        sendEffectSuccessCombine(player);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }

            }
        }
    }

    private void nangCapBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
            if (pi == null || pi.quantity < 20_000) {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Bạn thiếu 20k " + ConstItemName.MONEY_UNIT, "Đóng");
                Service.getInstance().sendThongBao(player, "Bạn thiếu 20k " + ConstItemName.MONEY_UNIT);
                return;
            }
            Item bongtai = null;
            Item manhvobt = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (checkbongtai(item)) {//return item.template.id == 454 || item.template.id == 921;
                    bongtai = item;
                } else if (item.template.id == 933) {
                    manhvobt = item;
                }
            }
            if (bongtai != null && manhvobt != null) {
                int level = 0;
                for (ItemOption io : bongtai.itemOptions) {
                    if (io.optionTemplate.id == 72) {
                        level = io.param;
                        break;
                    }
                }
                if (level < 2) {
                    int lvbt = lvbt(bongtai);
                    int countmvbt = getcountmvbtnangbt(lvbt);
                    if (countmvbt > manhvobt.quantity) {
                        Service.getInstance().sendThongBao(player, "Không đủ Mảnh vỡ bông tai");
                        return;
                    }
                    InventoryService.gI().subQuantityItemsBag(player, pi, 20_000);
                    InventoryService.gI().subQuantityItemsBag(player, manhvobt, countmvbt);
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        bongtai.template = ItemService.gI().getTemplate(getidbtsaukhilencap(lvbt));
                        bongtai.itemOptions.clear();
                        bongtai.itemOptions.add(new ItemOption(72, lvbt + 1));
                        sendEffectSuccessCombine(player);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private short getidbtsaukhilencap(int lvbtcu) {
        switch (lvbtcu) {
            case 1:
                return 921;
            case 2:
                return 1995;
        }
        return 0;
    }

    private Item checkItemCanCombine(Player player) {
        List<Item> itemsCombine = player.combineNew.itemsCombine;

        if (itemsCombine.size() != 1) {
            this.baHatMit.createOtherMenu(player, ConstNpc.BA_HAT_MIT, "Cần 1 ô trống trong hành trang", "Đóng");
            return null;
        }
        Item itemCombine = itemsCombine.get(0);

        if (!itemCombine.isNotNullItem()) {
            this.baHatMit.createOtherMenu(player, ConstNpc.BA_HAT_MIT, "Thiếu item", "Đóng");
            return null;
        } else if (!itemCombine.itemCanRemoveOption()) {
            this.baHatMit.createOtherMenu(player, ConstNpc.BA_HAT_MIT, "Chỉ có thể tẩy trang bị: quần áo găng giày rada \nvà trang bị phải có sao pha lê ", "Đóng");
            return null;
        } else {
            if (player.inventory != null && player.inventory.ruby < ConstCombine.COST_REMOVE_OPTION) {
                this.baHatMit.createOtherMenu(player, ConstNpc.BA_HAT_MIT, "Cần 200k hồng ngọc trong hành trang", "Đóng");
                return null;
            } else {
                return itemCombine;
            }
        }
    }

    private void moChiSoBongTai(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
            if (pi == null || pi.quantity < 20_000) {
                Service.getInstance().sendThongBao(player, "Bạn thiếu 20k " + ConstItemName.MONEY_UNIT);
                return;
            }
            Item bongTai = null;
            Item manhHon = null;
            Item daXanhLam = null;
            for (Item item : player.combineNew.itemsCombine) {
                switch (item.template.id) {
                    case 921:
                    case 1128:
                        bongTai = item;
                        break;
                    case 934:
                        manhHon = item;
                        break;
                    case 935:
                        daXanhLam = item;
                        break;
                    default:
                        break;
                }
            }
            if (bongTai != null && daXanhLam != null && manhHon.quantity >= 99) {
                InventoryService.gI().subQuantityItemsBag(player, pi, 20_000);
                InventoryService.gI().subQuantityItemsBag(player, manhHon, 99);
                InventoryService.gI().subQuantityItemsBag(player, daXanhLam, 1);
                if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                    bongTai.itemOptions.clear();
                    if (bongTai.template.id == 921) {
                        bongTai.itemOptions.add(new ItemOption(72, 2));
                    } else if (bongTai.template.id == 1128) {
                        bongTai.itemOptions.add(new ItemOption(72, 3));
                    }
                    int rdUp = Util.nextInt(0, 7);
                    switch (rdUp) {
                        case 0:
                            bongTai.itemOptions.add(new ItemOption(50, Util.nextInt(5, 25)));
                            break;
                        case 1:
                            bongTai.itemOptions.add(new ItemOption(77, Util.nextInt(5, 25)));
                            break;
                        case 2:
                            bongTai.itemOptions.add(new ItemOption(103, Util.nextInt(5, 25)));
                            break;
                        case 3:
                            bongTai.itemOptions.add(new ItemOption(108, Util.nextInt(5, 25)));
                            break;
                        case 4:
                            bongTai.itemOptions.add(new ItemOption(94, Util.nextInt(5, 15)));
                            break;
                        case 5:
                            bongTai.itemOptions.add(new ItemOption(14, Util.nextInt(5, 15)));
                            break;
                        case 6:
                            bongTai.itemOptions.add(new ItemOption(80, Util.nextInt(5, 25)));
                            break;
                        case 7:
                            bongTai.itemOptions.add(new ItemOption(81, Util.nextInt(5, 25)));
                            break;
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    sendEffectFailCombine(player);
                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private void dapBongTaiCap3(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
            if (pi == null || pi.quantity < 10_000) {
                Service.getInstance().sendThongBao(player, "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT);
                return;
            }
            Item bongtai = null;
            Item bikiep = null;
            for (Item item : player.combineNew.itemsCombine) {
                switch (item.template.id) {
                    case 1995:
                        bongtai = item;
                        break;
                    case ConstItem.BINH_NUOC:
                        bikiep = item;
                        break;

                }
            }
            if (bongtai != null && bikiep != null && bikiep.quantity >= 9999) {
                InventoryService.gI().subQuantityItemsBag(player, pi, 10_000);
                InventoryService.gI().subQuantityItemsBag(player, bikiep, 9999);
                if (Util.isTrue(1, 2)) {
                    bongtai.itemOptions.clear();
                    if (bongtai.template.id == 1995) {
                        bongtai.itemOptions.add(new ItemOption(72, 3));
                    }
                    int rdUp = Util.nextInt(0, 7);
                    switch (rdUp) {
                        case 0:
                            bongtai.itemOptions.add(new ItemOption(50, Util.nextInt(5, 30)));
                            break;
                        case 1:
                            bongtai.itemOptions.add(new ItemOption(77, Util.nextInt(5, 30)));
                            break;
                        case 2:
                            bongtai.itemOptions.add(new ItemOption(103, Util.nextInt(5, 30)));
                            break;
                        case 3:
                            bongtai.itemOptions.add(new ItemOption(108, Util.nextInt(5, 30)));
                            break;
                        case 4:
                            bongtai.itemOptions.add(new ItemOption(94, Util.nextInt(5, 30)));
                            break;
                        case 5:
                            bongtai.itemOptions.add(new ItemOption(14, Util.nextInt(5, 30)));
                            break;
                        case 6:
                            bongtai.itemOptions.add(new ItemOption(80, Util.nextInt(5, 30)));
                            break;
                        case 7:
                            bongtai.itemOptions.add(new ItemOption(81, Util.nextInt(5, 30)));
                            break;
                    }
                    sendEffectSuccessCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    sendEffectFailCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                Service.getInstance().sendThongBao(player, "Bạn thiếu Bông tai cấp 2 hoặc x9999" + ConstItemName.BINH_NUOC);
                return;
            }
        }
    }

    // ekko chuyển hóa SKH hủy diệt
    private void chuyenHoaSKHHuyDiet(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
            if (pi == null || pi.quantity < 10_000) {
                Service.getInstance().sendThongBao(player, "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT);
                return;
            }
            Item skhThan = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem() && item.isItemThanLinh()) {
                    for (ItemOption io : item.itemOptions) {
                        if (io.optionTemplate.id == 129 || io.optionTemplate.id == 248 || io.optionTemplate.id == 128 || io.optionTemplate.id == 135 || io.optionTemplate.id == 134 || io.optionTemplate.id == 133 || io.optionTemplate.id == 130 || io.optionTemplate.id == 131 || io.optionTemplate.id == 132) {
                            skhThan = item;
                        }
                    }
                }
            }
            if (skhThan != null) {
                InventoryService.gI().subQuantityItemsBag(player, pi, 10_000);
                InventoryService.gI().subQuantityItemsBag(player, skhThan, 1);
                // 5% thành công
                if (Util.isTrue(5, 100)) {
                    int hdID = skhThan.thanLinhToHuyDiet();
                    Item skhHD = ItemService.gI().createNewItem((short) hdID);
                    // giữ nguyên chỉ số của đồ thần
                    for (ItemOption io : skhThan.itemOptions) {
                        int paramHD = io.param;
                        // tất cả chỉ số = 90% của chỉ số max đồ trao cho top tại hộp 2167
                        switch (io.optionTemplate.id) {
                            case 0:
                                // tấn công
                                paramHD = 8100;
                                break;
                            case 22:
                                // hp
                                paramHD = 81;
                                break;
                            case 23:
                                // KI
                                paramHD = 90;
                                break;
                            case 14:
                                // chí mạng
                                paramHD = 14;
                                break;
                            case 47:
                                // giáp
                                paramHD = 1600;
                                break;
                        }
                        ItemOption newIo = new ItemOption(io.optionTemplate.id, paramHD);
                        skhHD.itemOptions.add(newIo);
                    }
                    InventoryService.gI().addItemBag(player, skhHD, 1);

                    sendEffectSuccessCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    sendEffectFailCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Con cần có 1 món SKH Thần", "Đóng");
            }
        }
    }

    // ekko khảm bùa
    private void khamBua(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item trangBi = null;
                Item bua = null;
                boolean isKham = false;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem()) {
                        // là đồ mặc
                        if(item.itemIsClothes()) {
                            // kiểm tra xem đồ được khảm chưa
                            for (ItemOption option : item.itemOptions) {
                                if(option.optionTemplate.id == 198 || option.optionTemplate.id == 186 || option.optionTemplate.id == 163) {
                                    isKham = true;
                                }
                            }
                            trangBi = item;
                        }
                        // có bùa pháp sư
                        else if (item.template.id == ConstItem.BUA_PHAP_SU_HP || item.template.id == ConstItem.BUA_PHAP_SU_KI || item.template.id == ConstItem.BUA_PHAP_SU_SD) {
                            bua = item;
                        }
                    }
                }
                if (bua == null || bua.quantity < 10) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần có 10 bùa pháp sư để khảm bùa", "Đóng");
                } else {
                    if (trangBi != null) {
                        if(isKham) {
                            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                    "Trang bị đã khảm bùa rồi con ơi", "Đóng");
                        } else {
                            InventoryService.gI().subQuantityItemsBag(player, bua, 10);
                            // 10% thành công
                            if (Util.isTrue(10, 100)) {
                                InventoryService.gI().subQuantityItemsBag(player, trangBi, 1);
                                // thêm option bùa pháp sư
                                int paramBua = 0;
                                int optionID = 0;
                                if (bua.template.id == ConstItem.BUA_PHAP_SU_SD) {
                                    paramBua = 10;
                                    optionID = 198;
                                } else if (bua.template.id == ConstItem.BUA_PHAP_SU_HP) {
                                    paramBua = 15;
                                    optionID = 186;
                                } else if (bua.template.id == ConstItem.BUA_PHAP_SU_KI) {
                                    paramBua = 15;
                                    optionID = 163;
                                }
                                ItemOption newIo = new ItemOption(optionID, paramBua);
                                trangBi.itemOptions.add(newIo);

                                InventoryService.gI().addItemBag(player, trangBi, 1);
                                sendEffectSuccessCombine(player);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendMoney(player);
                                reOpenItemCombine(player);
                            } else {
                                sendEffectFailCombine(player);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendMoney(player);
                                reOpenItemCombine(player);
                            }
                        }
                    } else {
                        this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                                "Con cần có 1 trang bị trong số áo, quần găng, giày, rada", "Đóng");
                    }
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 trang bị và 10 bùa pháp sư để khảm bùa", "Đóng");
        }
    }

    // ekko nâng cấp SKH
    private void nangCapSKH(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                // số lượng đá TL
                int slDaTLNeed = 10;
                // số lượng đá TL
                int totalSlDaTL = 0;
                // hợp lệ
                boolean isValid = false;
                // số item thần linh
                int totalItemTL = 0;
                // số item SKH
                int totalItemSKH = 0;
                // đồ SKH 1
                Item skhOne = null;
                // id option skh
                int skhOneOptionSKHID = -1;
                // đồ skh trong bag
                Item skhOneInbag = null;
                // đồ skh trong bag
                Item skhTwoInbag = null;
                // đồ TL 1 khi trong nâng cấp
                Item tlOne = null;
                // đồ thần linh trong túi
                Item tlOneInBag = null;
                // đồ thần linh trong túi
                Item tlTwoInBag = null;
                // đá TL dùng để nâng cấp
                Item daThanLinhParam = null;
                // id của đồ nâng cấp tiếp theo
                int nextID = -1;
                boolean isFromTL = false;
                int skhOneOptionID = -1;
                int skhTwoOptionID = -1;
                int totalLoop = player.combineNew.itemsCombine.size();
                for (int i = 0; i < totalLoop; i++) {
                    Item currentItem = player.combineNew.itemsCombine.get(i);
                    if (currentItem.isNotNullItem()) {
                        // nâng cấp 2 món thần linh lên SKH
                        if(currentItem.isItemThanLinh()) {
                            totalItemTL += 1;
                            tlOne = currentItem;
                        } else if (currentItem.template.id == ConstItem.DA_THAN_LINH){
                            totalSlDaTL = currentItem.quantity;
                            daThanLinhParam = currentItem;
                        }
                        for (ItemOption io : currentItem.itemOptions) {
                            if (io.optionTemplate.id == ConstOption.SET_SONGOKU || io.optionTemplate.id == ConstOption.SET_TENSHINHAN || io.optionTemplate.id == ConstOption.SET_KRILLIN || io.optionTemplate.id == ConstOption.SET_NAPPA || io.optionTemplate.id == ConstOption.SET_CADIC || io.optionTemplate.id == ConstOption.SET_KAKAROT || io.optionTemplate.id == ConstOption.SET_PICOLO || io.optionTemplate.id == ConstOption.SET_DENDE || io.optionTemplate.id == ConstOption.SET_DAIMAO) {
                                totalItemSKH += 1;
                                skhOne = currentItem;
                                skhOneOptionSKHID = io.optionTemplate.id;
                            }
                        }
                    }
                }

                // nâng cấp từ 2 món thần linh
                if(totalSlDaTL >= slDaTLNeed) {
                    if(totalItemTL == 1) {
                        // kiểm tra trong túi còn có món thần linh nữa không
                        int totalTLInBag = 0;
                        // lặp qua bag
                        for (Item item : player.inventory.itemsBag) {
                            if (item.isNotNullItem() && item.template.id == tlOne.template.id) {
                                if(totalTLInBag == 0) {
                                    tlOneInBag = item;
                                } else if(totalTLInBag == 1) {
                                    tlTwoInBag = item;
                                }
                                if(totalTLInBag < 2) {
                                    totalTLInBag += 1;
                                } else {
                                    break;
                                }
                            }
                        }
                        if (totalTLInBag >= 2) {
                            isValid = true;
                            isFromTL = true;
                            // nếu là nhẫn thần linh thì lấy theo gender của player
                            int nextItemGender = tlOne.template.gender;
                            if(tlOne.template.gender == 3) {
                                nextItemGender = player.gender;
                            }
                            nextID = getFirstItemByPlanetAndType(nextItemGender, tlOne.template.type);
                        }
                    }
                    // nâng cấp từ SKH
                    else if(totalItemSKH == 1) {
                        if(skhOne != null && skhOne.template != null) {
                            // check xem trong bag còn skh nào thì mới hợp lệ
                            int totalSKHInBag = 0;
                            // lặp qua bag
                            for (Item item : player.inventory.itemsBag) {
                                if (item.isNotNullItem() && item.template.id == skhOne.template.id) {
                                    // kiểm tra option của đồ
                                    for (ItemOption ioSKH : item.itemOptions) {
                                        if(ioSKH.optionTemplate.id == ConstOption.SET_SONGOKU || ioSKH.optionTemplate.id == ConstOption.SET_TENSHINHAN || ioSKH.optionTemplate.id == ConstOption.SET_KRILLIN || ioSKH.optionTemplate.id == ConstOption.SET_PICOLO || ioSKH.optionTemplate.id == ConstOption.SET_DENDE || ioSKH.optionTemplate.id == ConstOption.SET_DAIMAO || ioSKH.optionTemplate.id == ConstOption.SET_NAPPA || ioSKH.optionTemplate.id == ConstOption.SET_KAKAROT || ioSKH.optionTemplate.id == ConstOption.SET_CADIC) {
                                            if(totalSKHInBag < 2) {
                                                totalSKHInBag += 1;
                                                if(totalSKHInBag == 1) {
                                                    skhOneInbag = item;
                                                    skhOneOptionID = ioSKH.optionTemplate.id;
                                                } else {
                                                    skhTwoInbag = item;
                                                    skhTwoOptionID = ioSKH.optionTemplate.id;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            // có skh trong bag thì hợp lệ
                            if(totalSKHInBag >= 2) {
                                // và 2 món skh phải nằm trong danh sách đồ nâng skh
                                nextID = getNextIdInList(skhOne.template.id);
                                if(nextID != -1) {
                                    isValid = true;
                                    isFromTL = false;
                                }
                            }
                        }
                    }
                }
                // check hợp lệ
                if(!isValid) {
                    this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần 2 món thần linh hoặc 2 món SKH và x10 đá thần linh", "Đóng");
                } else {
                    // nâng cấp từ đồ thần linh
                    if(isFromTL) {
                        // trừ đồ thần linh và 10 đá thần linh
                        InventoryService.gI().subQuantityItemsBag(player, tlOneInBag, 1);
                        InventoryService.gI().subQuantityItemsBag(player, tlTwoInBag, 1);
                        InventoryService.gI().subQuantityItemsBag(player, daThanLinhParam, 10);
//                        InventoryService.gI().sendItemBags(player);
                        // id của skh sau khi nâng cấp
                        // nếu là nhẫn thần linh thì lấy theo gender của player
                        int nextItemGender = tlOne.template.gender;
                        if(tlOne.template.gender == 3) {
                            nextItemGender = player.gender;
                        }
                        int firstSKHID = getFirstItemByPlanetAndType(nextItemGender, tlOne.template.type);
                        if(firstSKHID != -1) {
                            List<ItemOption> lstOption = new ArrayList<>();
                            int optionSKHID = -1;
                            int optionSetID = -1;
                            int optionSetVal = 0;
                            Item firstSKH = ItemService.gI().createNewItem((short) firstSKHID);
                            // khởi tạo option cho skh
                            lstOption = initOptionSKH(firstSKHID);
                            switch (nextItemGender) {
                                // td
                                case 0:
                                    optionSKHID = ConstOption.SET_KRILLIN;
                                    optionSetID = ConstOption.SET_QCKK;
                                    optionSetVal = 0;
                                    if(Util.isTrue(25, 100)) {
                                        optionSKHID = ConstOption.SET_SONGOKU;
                                        optionSetID = ConstOption.OPTION_PERCENT_KAMEJOKO;
                                        optionSetVal = 100;
                                    }
                                    else if(Util.isTrue(35, 100)) {
                                        optionSKHID = ConstOption.SET_TENSHINHAN;
                                        optionSetID = ConstOption.SET_SAT_THUONG_KAIOKEN;
                                        optionSetVal = 0;
                                    }
                                    break;
                                    // nm
                                case 1:
                                    optionSKHID = ConstOption.SET_DAIMAO;
                                    optionSetID = ConstOption.SET_SAT_THUONG_DE_TRUNG;
                                    optionSetVal = 100;
                                    if(Util.isTrue(25, 100)) {
                                        optionSKHID = ConstOption.SET_PICOLO;
                                        optionSetID = ConstOption.OPTION_PERCENT_KI;
                                        optionSetVal = 100;
                                    }
                                    else if(Util.isTrue(35, 100)) {
                                        optionSKHID = ConstOption.SET_DENDE;
                                        optionSetID = ConstOption.OPTION_PERCENT_LIEN_HOAN;
                                        optionSetVal = 100;
                                    }
                                    break;
                                    // xd
                                case 2:
                                    optionSKHID = ConstOption.SET_CADIC;
                                    optionSetID = ConstOption.SET_X5_THOI_GIAN_HOA_KHI;
                                    optionSetVal = 0;
                                    if(Util.isTrue(25, 100)) {
                                        optionSKHID = ConstOption.SET_NAPPA;
                                        optionSetID = ConstOption.OPTION_PERCENT_HP;
                                        optionSetVal = 100;
                                    }
                                    if(Util.isTrue(35, 100)) {
                                        optionSKHID = ConstOption.SET_KAKAROT;
                                        optionSetID = ConstOption.SET_DAM_GALICK;
                                        optionSetVal = 0;
                                    }
                                    break;
                            }
                            // thêm option cho skh
                            if(optionSKHID != -1 && optionSetID != -1) {
                                lstOption.add(new ItemOption(optionSKHID, 0));
                                lstOption.add(new ItemOption(optionSetID, optionSetVal));
                                firstSKH.itemOptions = lstOption;
                                // gửi đồ cho pl
                                InventoryService.gI().addItemBag(player, firstSKH, 1);
                                sendEffectSuccessCombine(player);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendMoney(player);
                                reOpenItemCombine(player);
                            }
                        }
                    }
                    // nâng cấp từ SKH
                    else {
                        if(nextID != -1) {
                            // trừ đồ skh và 10 đá thần linh
                            InventoryService.gI().subQuantityItemsBag(player, skhOneInbag, 1);
                            InventoryService.gI().subQuantityItemsBag(player, skhTwoInbag, 1);
                            InventoryService.gI().subQuantityItemsBag(player, daThanLinhParam, 10);
//                            InventoryService.gI().sendItemBags(player);
                            Item nextSKH = ItemService.gI().createNewItem((short) nextID);
                            List<ItemOption> lstOption = new ArrayList<>();
                            int optionSKHID = skhOneOptionSKHID;
                            int optionSetID = -1;
                            int optionSetVal = 0;
                            if(Util.isTrue(50, 100)) {
                                optionSKHID = skhOneOptionID;
                            } else {
                                optionSKHID = skhTwoOptionID;
                            }
                            // khởi tạo option cho skh
                            lstOption = initOptionSKH(nextID);

                            switch (optionSKHID) {
                                // td
                                case ConstOption.SET_KRILLIN:
                                    optionSetID = ConstOption.SET_QCKK;
                                    optionSetVal = 0;
                                    break;
                                case ConstOption.SET_SONGOKU:
                                    optionSetID = ConstOption.OPTION_PERCENT_KAMEJOKO;
                                    optionSetVal = 100;
                                    break;
                                case ConstOption.SET_TENSHINHAN:
                                    optionSetID = ConstOption.SET_SAT_THUONG_KAIOKEN;
                                    optionSetVal = 0;
                                    break;
                                // nm
                                case ConstOption.SET_DAIMAO:
                                    optionSetID = ConstOption.SET_SAT_THUONG_DE_TRUNG;
                                    optionSetVal = 100;
                                    break;
                                case ConstOption.SET_PICOLO:
                                    optionSetID = ConstOption.OPTION_PERCENT_KI;
                                    optionSetVal = 100;
                                    break;
                                case ConstOption.SET_DENDE:
                                    optionSetID = ConstOption.OPTION_PERCENT_LIEN_HOAN;
                                    optionSetVal = 100;
                                    break;
                                // xd
                                case ConstOption.SET_CADIC:
                                    optionSetID = ConstOption.SET_X5_THOI_GIAN_HOA_KHI;
                                    optionSetVal = 0;
                                    break;
                                case ConstOption.SET_NAPPA:
                                    optionSetID = ConstOption.OPTION_PERCENT_HP;
                                    optionSetVal = 100;
                                    break;
                                case ConstOption.SET_KAKAROT:
                                    optionSetID = ConstOption.SET_DAM_GALICK;
                                    optionSetVal = 0;
                                    break;
                            }
                            // thêm option cho skh
                            if(optionSKHID != -1 && optionSetID != -1) {
                                lstOption.add(new ItemOption(optionSKHID, 0));
                                lstOption.add(new ItemOption(optionSetID, optionSetVal));
                                nextSKH.itemOptions = lstOption;
                                // gửi đồ cho pl
                                InventoryService.gI().addItemBag(player, nextSKH, 1);
                                sendEffectSuccessCombine(player);
                                InventoryService.gI().sendItemBags(player);
                                Service.getInstance().sendMoney(player);
                                reOpenItemCombine(player);
                            }
                        }
                    }
                }
            } else {
                this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.quyLaoKame.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 2 món thần linh hoặc 2 món SKH và x10 đá thần linh", "Đóng");
        }
    }

    // ekko
    private void phaLeHoaVongThienSu(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                if (pi == null || pi.quantity < 50_000) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 50k " + ConstItemName.MONEY_UNIT);
                    return;
                }
                Item vongThienSu = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem() && item.template.id == ConstItem.VONG_THIEN_SU) {
                       vongThienSu = item;
                    }
                }
                if (vongThienSu == null) {
                    Service.getInstance().sendThongBao(player, "Thiếu vòng thiên sứ rồi con");
                } else {
                    // trừ 50k tvv
                    InventoryService.gI().subQuantityItemsBag(player, pi, 50_000);
                    // 80% thành công
                    if (Util.isTrue(80, 100)) {
                        InventoryService.gI().subQuantityItemsBag(player, vongThienSu, 1);
                        int level = 0;
                        for (ItemOption io : vongThienSu.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                if (level < 7) {
                                    io.param++;
                                    for(ItemOption io2 : vongThienSu.itemOptions) {
                                        if(io2.optionTemplate.id == 169) {
                                            // tăng thêm 5%
                                            io2.param += 5;
                                        }
                                    }
                                }
                            }
                        }
                        if (level < 7) {
                            if (level == 0) {
                                vongThienSu.itemOptions.add(new ItemOption(72, 1));
                                for(ItemOption io3 : vongThienSu.itemOptions) {
                                    if(io3.optionTemplate.id == 169) {
                                        // tăng thêm 5%
                                        io3.param += 5;
                                    }
                                }
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Vòng thiên sứ của ngươi đã đạt cấp tối đa");
                        }
                        InventoryService.gI().addItemBag(player, vongThienSu, 1);
                        sendEffectSuccessCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    } else {
                        sendEffectFailCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 trang bị và 10 bùa pháp sư để khảm bùa", "Đóng");
        }
    }

    private void nangCapCaiTrangHoaXuong(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                if (pi == null || pi.quantity < 100_000) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 100k " + ConstItemName.MONEY_UNIT);
                    return;
                }
                Item caiTrang = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem() && (item.template.id == ConstItem.CAI_TRANG_NGO_KHONG || item.template.id == ConstItem.CAI_TRANG_DUONG_TANG)) {
                        caiTrang = item;
                    }
                }
                if (caiTrang == null) {
                    Service.getInstance().sendThongBao(player, "Thiếu cải trang rồi con");
                } else {
                    // trừ 100k tvv
                    InventoryService.gI().subQuantityItemsBag(player, pi, 100_000);
                    // 50% thành công
                    if (Util.isTrue(50, 100)) {
                        InventoryService.gI().subQuantityItemsBag(player, caiTrang, 1);
                        int level = 0;
                        for (ItemOption io : caiTrang.itemOptions) {
                            if (io.optionTemplate.id == 72) {
                                level = io.param;
                                if (level < 7) {
                                    io.param++;
                                }
                            }
                        }
                        if (level < 7) {
                            if (level == 0) {
                                caiTrang.itemOptions.add(new ItemOption(72, 1));
                            }
                        } else {
                            Service.getInstance().sendThongBao(player, "Cải trang của ngươi đã đạt cấp tối đa");
                        }
                        InventoryService.gI().addItemBag(player, caiTrang, 1);
                        sendEffectSuccessCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);

                    } else {
                        sendEffectFailCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                }
            } else {
                this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy hãy chọn 1 cải trang Ngộ Không hoặc Đường Tăng và 100k Thỏi vàng vip", "Đóng");
        }
    }

    // ekko chế tạo bánh quy
    private void cheTaoBanhQuy(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item keoGiangSinh = null;
                Item botMi = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if(item.isNotNullItem()) {
                        if (item.template.id == ConstItem.KEO_GIANG_SINH) {
                            keoGiangSinh = item;
                        } else if(item.template.id == ConstItem.BOT_MI) {
                            botMi = item;
                        }
                    }
                }
                if (keoGiangSinh == null || keoGiangSinh.quantity < 1) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 1 kẹo giáng sinh");
                    return;
                }
                if (botMi == null || botMi.quantity < 1) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 1 bột mì");
                } else {
                    InventoryService.gI().subQuantityItemsBag(player, keoGiangSinh, 1);
                    InventoryService.gI().subQuantityItemsBag(player, botMi, 1);
                    Item banhQuy = ItemService.gI().createNewItem((short) ConstItem.BANH_QUY, 1);
                    InventoryService.gI().addItemBag(player, banhQuy, 0);
                    sendEffectSuccessCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 kẹo giáng sinh và 1 bột mì", "Đóng");
        }
    }

    // ekko chế tạo kẹo giáng sinh
    private void cheTaoKeoGiangSinh(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item gayGo = null;
                Item keoDuong = null;
                for (Item item : player.combineNew.itemsCombine) {
                    if(item.isNotNullItem()) {
                        if (item.template.id == ConstItem.GAY_GO) {
                            gayGo = item;
                        } else if(item.template.id == ConstItem.KEO_DUONG) {
                            keoDuong = item;
                        }
                    }
                }
                if (gayGo == null || gayGo.quantity < 1) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 1 gậy gỗ");
                    return;
                }
                if (keoDuong == null || keoDuong.quantity < 2) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 2 kẹo đường");
                } else {
                    InventoryService.gI().subQuantityItemsBag(player, gayGo, 1);
                    InventoryService.gI().subQuantityItemsBag(player, keoDuong, 2);
                    Item keoGiangSinh = ItemService.gI().createNewItem((short) ConstItem.KEO_GIANG_SINH, 1);
                    InventoryService.gI().addItemBag(player, keoGiangSinh, 0);
                    sendEffectSuccessCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.cayNeu.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 gậy gỗ và 1 kẹo đường", "Đóng");
        }
    }

    private void nangCapDoThienSu(Player player) {
        if (player.combineNew.itemsCombine.size() > 1) {
            int ratioLuckyStone = 0, ratioRecipe = 0, ratioUpgradeStone = 0;
            List<Item> list = new ArrayList<>();
            Item angelClothes = null;
            Item craftingRecipe = null;
            for (Item item : player.combineNew.itemsCombine) {
                int id = item.template.id;
                if (item.isNotNullItem()) {
                    if (isAngelClothes(id)) {
                        angelClothes = item;
                    } else if (isLuckyStone(id)) {
                        ratioLuckyStone += getRatioLuckyStone(id);
                        list.add(item);
                    } else if (isUpgradeStone(id)) {
                        ratioUpgradeStone += getRatioUpgradeStone(id);
                        list.add(item);
                    } else if (isCraftingRecipe(id)) {
                        ratioRecipe += getRatioCraftingRecipe(id);
                        craftingRecipe = item;
                        list.add(item);
                    }
                }
            }
            boolean canUpgrade = true;
            for (ItemOption io : angelClothes.itemOptions) {
                int optId = io.optionTemplate.id;
                if (optId == 41) {
                    canUpgrade = false;
                }
            }
            if (canUpgrade) {
                if (angelClothes != null && craftingRecipe != null) {
                    int ratioTotal = (20 + ratioUpgradeStone + ratioRecipe);
                    int ratio = ratioTotal > 75 ? ratio = 75 : ratioTotal;
                    if (player.inventory.gold >= COST_DAP_DO_KICH_HOAT) {
                        if (Util.isTrue(ratio, 150)) {
                            int num = 0;
                            if (Util.isTrue(ratioLuckyStone, 150)) {
                                num = 15;
                            } else if (Util.isTrue(5, 100)) {
                                num = Util.nextInt(10, 15);
                            } else if (Util.isTrue(20, 100)) {
                                num = Util.nextInt(1, 10);
                            }
                            RandomCollection<Integer> rd = new RandomCollection<>();
                            rd.add(50, 1 /*, "DDijt nhau au au"*/);
                            rd.add(25, 2 /*, "DDijt nhau au au"*/);
                            rd.add(10, 3 /*, "DDijt nhau au au"*/);
                            rd.add(5, 4 /*, "DDijt nhau au au"*/);
                            int color = rd.next();
                            for (ItemOption io : angelClothes.itemOptions) {
                                int optId = io.optionTemplate.id;
                                switch (optId) {
                                    case 47: // giáp
                                    case 6: // hp
                                    case 26: // hp/30s
                                    case 22: // hp k
                                    case 0: // sức đánh
                                    case 7: // ki
                                    case 28: // ki/30s
                                    case 23: // ki k
                                    case 14: // crit
                                        io.param += ((long) io.param * num / 100);
                                        break;
                                }
                            }
                            angelClothes.itemOptions.add(new ItemOption(41, color));
                            for (int i = 0; i < color; i++) {
                                angelClothes.itemOptions
                                        .add(new ItemOption(Util.nextInt(201, 212), Util.nextInt(1, 10)));
                            }
                            sendEffectSuccessCombine(player);
                            Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp thành công");
                        } else {
                            sendEffectFailCombine(player);
                            Service.getInstance().sendThongBao(player, "Chúc bạn đen nốt lần sau");
                        }
                        for (Item it : list) {
                            InventoryService.gI().subQuantityItemsBag(player, it, 1);
                        }
                        player.inventory.subGold(COST_DAP_DO_KICH_HOAT);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                }
            }
        }
    }

    private void giaHanCaiTrang(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item caitrang = null, vegiahan = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.type == 5) {
                        caitrang = item;
                    } else if (item.template.id == 2022) {
                        vegiahan = item;
                    }
                }
            }
            if (caitrang != null && vegiahan != null) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_GIA_HAN_CAI_TRANG) {
                    ItemOption expiredDate = null;
                    boolean canBeExtend = true;
                    for (ItemOption io : caitrang.itemOptions) {
                        if (io.optionTemplate.id == 93) {
                            expiredDate = io;
                        }
                        if (io.optionTemplate.id == 199) {
                            canBeExtend = false;
                        }
                    }
                    if (canBeExtend) {
                        if (expiredDate.param > 0) {
                            player.inventory.subGold(COST_GIA_HAN_CAI_TRANG);
                            sendEffectSuccessCombine(player);
                            expiredDate.param++;
                            InventoryService.gI().subQuantityItemsBag(player, vegiahan, 1);
                            InventoryService.gI().sendItemBags(player);
                            Service.getInstance().sendMoney(player);
                            reOpenItemCombine(player);
                        }
                    }
                }
            }
        }
    }

    public void openDTS(Player player) {
        if (player.combineNew.itemsCombine.size() != 4) {
            Service.getInstance().sendThongBao(player, "Thiếu đồ");
            return;
        }
        if (player.inventory.gold < COST_NANG_DO_TS) {
            Service.getInstance().sendThongBao(player, "Bạn thiếu 1 tỷ vàng...");
            return;
        }
        if (player.inventory.gem < COST_RUBY_NANG_DO_TS) {
            Service.getInstance().sendThongBao(player, "Bạn thiếu ngọc xanh...");
            return;
        }
        if (InventoryService.gI().getCountEmptyBag(player) < 1) {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 4) {
            Item manhts = null;
            Item danc = null;
            Item damayman = null;
            Item congthuc = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.isManhTS()) {
                        manhts = item;
                    } else if (item.isdanangcapDoTs()) {
                        danc = item;
                    } else if (item.isDamayman()) {
                        damayman = item;
                    } else if (item.isCongthucNomal() || item.isCongthucVip()) {
                        congthuc = item;
                    }
                }
            }
            if (manhts == null || danc == null || damayman == null || congthuc == null) {
                Service.getInstance().sendThongBao(player, "Thiếu đồ!!");
                return;
            }
            if (manhts.quantity < 999) {
                Service.getInstance().sendThongBao(player, "Cần x999 mảnh thiên sứ!");
                return;
            }
            if (manhts != null && danc != null && damayman != null && congthuc != null && manhts.quantity >= 999) {
                int tile = get_Tile_nang_Do_TS(danc, congthuc);
                int perLucky = 20;// chi
                perLucky += perLucky * (damayman.template.id - 1078) * 10 / 100;
                int perSuccesslucky = Util.nextInt(0, 100);
                if (Util.isTrue(tile, 100)) {
                    short[][] itemIds = {
                            {1048, 1051, 1054, 1057, 1060},
                            {1049, 1052, 1055, 1058, 1061},
                            {1050, 1053, 1056, 1059, 1062}};
                    Item itemTS = ItemService.gI().DoThienSu(itemIds[congthuc.template.gender][manhts.typeIdManh()], congthuc.template.gender, perSuccesslucky, perLucky);
                    sendEffectSuccessCombineDoTS(player, itemTS.template.iconID);
                    InventoryService.gI().addItemBag(player, itemTS, 0);
                    InventoryService.gI().subQuantityItemsBag(player, manhts, 999);
                } else {
                    sendEffectFailCombineDoTS(player);
                    InventoryService.gI().subQuantityItemsBag(player, manhts, 99);
                }
                player.inventory.gold -= COST_NANG_DO_TS;
                player.inventory.addGem(-COST_RUBY_NANG_DO_TS);
                InventoryService.gI().subQuantityItemsBag(player, danc, 1);
                InventoryService.gI().subQuantityItemsBag(player, damayman, 1);
                InventoryService.gI().subQuantityItemsBag(player, congthuc, 1);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                player.combineNew.itemsCombine.clear();
                reOpenItemCombine(player);
            }
        }
    }

    private void dapDoKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item manhTL = null;
            Item dokh = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id >= 2032 && item.template.id <= 2036) {
                        manhTL = item;
                    } else if (item.isSKHThuong()) {
                        dokh = item;
                    }
                }
            }
            if (dokh != null && manhTL != null && manhTL.quantity >= 2) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0 && player.inventory.ruby >= 20_000) {
                    int oldRuby = player.inventory.ruby;
                    player.inventory.addRuby(-20_000);
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-dapDoKichHoat");
                    sendEffectSuccessCombine(player);
                    Item item = ArrietyDrop.randomCS_DKH(dokh.template.id, (byte) 1, dokh.template.gender == 3 ? player.gender : dokh.template.gender);
                    InventoryService.gI().addItemBag(player, item, 0);
                    InventoryService.gI().subQuantityItemsBag(player, dokh, 1);
                    InventoryService.gI().subQuantityItemsBag(player, manhTL, 2);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Thieu tien roi cu em");
                }
            }
        }
    }

    private int getTine(Item tl) {// tile thanh cong
        int tile = 10;
        if (tl != null) {
            if (tl.template.id >= 555 && tl.template.id <= 567) {
                tile = 30;
            }
        }
        return tile;
    }

    private void petWhis(Player player) {
        if (player.combineNew.itemsCombine.size() >= 1) {
            Item thang = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id == 2031) {
                        thang = item;
                    }
                }
            }
            if (player.inventory.ruby < 20_000) {
                Service.getInstance().sendThongBao(player, "bạn thiếu 20k ruby");
                return;
            }
            if (thang.quantity < 19) {
                Service.getInstance().sendThongBao(player, "cần 99 Thăng Tinh Thạch");
                return;
            }
            if (player.pet.typePet != ConstPet.CELL) {
                Service.getInstance().sendThongBao(player, "Ngươi cần có đệ tử Cell");
                return;
            }
            if (player.pet.getLever() < 3) {
                Service.getInstance().sendThongBao(player, "Đệ Cell của bạn phải ở lever 3");
                return;
            }
            if (thang != null && thang.quantity > 99) {
                InventoryService.gI().subQuantityItemsBag(player, thang, 99);
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-20_000);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-petWhis");
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                if (Util.isTrue(50, 100)) {
                    if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
                        player.pet.unFusion();
                    }
                    MapService.gI().exitMap(player.pet);
                    player.pet.dispose();
                    player.pet = null;
                    PetService.gI().createWhisPet(player, player.gender);
                    Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
                } else {
                    Service.getInstance().sendThongBao(player, "Hii hi bạn đỏ như màu lồn nên xịt rồi");
                }
            }
        }
    }

    private void tradePet(Player player) {
        if (player.combineNew.itemsCombine.size() >= 1) {
            Item GAYTD = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id == 1231) {
                        GAYTD = item;
                    }
                }
            }
            if (GAYTD.quantity < 1) {
                Service.getInstance().sendThongBao(player, "cần x10 Đá nguyền cc");
                return;
            }
            if (player.pet.typePet != ConstPet.SUPER) {
                Service.getInstance().sendThongBao(player, "Ngươi cần có đệ tử Black gâu ku");
                return;
            }
            if (GAYTD != null && GAYTD.quantity > 10) {
                InventoryService.gI().subQuantityItemsBag(player, GAYTD, 10);
                InventoryService.gI().sendItemBags(player);
                if (Util.isTrue(10, 100)) {
                    PetService.gI().createWhisPet(player, player.gender);
                    Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
                } else {
                    Service.getInstance().sendThongBao(player, "Xịt =)))))");
                }
            }
        }
    }

    private void upgradePet(Player player) {
        if (player.combineNew.itemsCombine.size() >= 1) {
            Item da = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id == 2040) {
                        da = item;
                    }
                }
            }
            if (da.quantity < 1) {
                Service.getInstance().sendThongBao(player, "cần x10 Đá nguyền cc");
                return;
            }
            if (player.pet.getLever() >= 7) {
                Service.getInstance().sendThongBao(player, "Max Lever rồi thằng nhót");
                return;
            }
            if (da != null && da.quantity > 10) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0 && player.inventory.ruby >= COST_LEVER) {
                    int oldRuby = player.inventory.ruby;
                    player.inventory.addRuby(-5_000);
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-upgradePet");
                    Service.getInstance().sendMoney(player);
                    InventoryService.gI().subQuantityItemsBag(player, da, 10);
                    if (Util.isTrue(50, 100)) {
                        player.pet.setLever(player.pet.getLever() + 1);
                        Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
                    } else {
                        Service.getInstance().sendThongBao(player, "Xịt =)))))");
                    }
                } else {
                    Service.getInstance().sendThongBao(player, "Thieu tien roi cu em");
                }
            }
        }
    }

    private void upgradeCell(Player player) {
        if (player.combineNew.itemsCombine.size() >= 1) {
            Item auTrungCelXank = null;
            Item auTrungCelVanK = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    switch (item.template.id) {
                        case 1992:
                            auTrungCelXank = item;
                            break;
                        case 1993:
                            auTrungCelVanK = item;
                            break;
                    }
                }
            }
            switch (player.pet.getLever()) {
                case 1:
                    if (!(player.pet.getLever() == 1 && auTrungCelXank != null && auTrungCelXank.quantity >= 999)) {
                        Service.getInstance().sendThongBao(player, "Cần x999 Ấu trùng xên xanh để nâng lên cấp 2");
                        return;
                    }
                    break;
                case 2:
                    if (!(player.pet.getLever() == 2 && auTrungCelVanK != null && auTrungCelVanK.quantity >= 999)) {
                        Service.getInstance().sendThongBao(player, "Cần x999 Ấu trùng xên vàng để nâng lên cấp 3");
                        return;
                    }
                    break;
            }

            if (InventoryService.gI().getCountEmptyBag(player) > 0 && player.inventory.ruby >= 30_000) {
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-30_000);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-upgradeCell");
                switch (player.pet.getLever()) {
                    case 1:
                        InventoryService.gI().subQuantityItemsBag(player, auTrungCelXank, 999);
                        break;
                    case 2:
                        InventoryService.gI().subQuantityItemsBag(player, auTrungCelVanK, 999);
                        break;
                }
                player.pet.setLever(player.pet.getLever() + 1);
                Service.getInstance().sendMoney(player);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
                reOpenItemCombine(player);
            } else {
                Service.getInstance().sendThongBao(player, "Thieu tien roi cu em");
            }
        }
    }

    private void dapDoHuydiet(Player player) {
        if (!player.combineNew.itemsCombine.isEmpty() && player.combineNew.itemsCombine.size() <= 2) {
            Item dokh = null;
            Item pi = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.isSKHVip() && item.template.id >= 555 && item.template.id <= 567) {
                        dokh = item;
                    } else if (item.template.id == ConstItem.THOI_VANG_VIP) {
                        pi = item;
                    }
                }
            }
            if (dokh != null) {
                int gem = 30000;
                if (player.inventory.gem < gem) {
                    Service.getInstance().sendThongBao(player, "Không đủ ngọc để thực hiện");
                    return;
                }
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    int tile = 0;
                    if (pi != null && pi.quantity >= 10000) {
                        tile = 10;
                        InventoryService.gI().subQuantityItemsBag(player, pi, 10000);
                    } else {
                        tile = 5;
                    }
                    if (Util.isTrue(tile, 100)) {
                        int typeOption = 0;
                        int check = dokh.checkSet(dokh);
                        for (ItemOption io : dokh.itemOptions) {
                            switch (io.optionTemplate.id) {
                                case 129:
                                    typeOption = 2;//Set Songoku
                                    break;
                                case 128:
                                    typeOption = 1;  //Set Krillin
                                    break;
                                case 127:
                                    typeOption = 0;  //Set Tenshinhan
                                    break;
                                case 130:
                                    typeOption = 3; //Set Piccolo
                                    break;
                                case 131:
                                    typeOption = 4; //Set Dende
                                    break;
                                case 132:
                                    typeOption = 5; ///Set Pikkoro Daimao
                                    break;
                                case 133:
                                    typeOption = 6;//Set Kakarot
                                    break;
                                case 134:
                                    typeOption = 7; //Set Vegeta
                                    break;
                                case 135:
                                    typeOption = 8; //Set Nappa
                                    break;
                            }
                        }
                        sendEffectSuccessCombine(player);
                        Item item = ArrietyDrop.randomCS_DKHHD(check + 95, typeOption,
                                (dokh).template.gender == 3 ? player.gender : dokh.template.gender);
                        InventoryService.gI().addItemBag(player, item, 0);
                        InventoryService.gI().subQuantityItemsBag(player, dokh, 1);
                        InventoryService.gI().sendItemBags(player);
                        player.inventory.addGem(-30_000);
                    }
                    if (pi != null && pi.quantity >= 10000) {
                        InventoryService.gI().subQuantityItemsBag(player, pi, 10000);
                    }
                    Service.getInstance().sendThongBao(player, "Xịt nhót");
                    player.inventory.addGem(-30_000);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                Service.getInstance().sendThongBao(player, "Hành trang full");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Thiếu đồ");
        }
    }

    private void dapDoThanLinh(Player player) {
        if (player.combineNew.itemsCombine.size() >= 1) {
            Item manhTL = null;
            Item dokh = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id >= 555 && item.template.id <= 567) {
                        manhTL = item;
                    } else if (item.isSKHVip()) {
                        dokh = item;
                    }
                }
            }
            if (dokh != null) {
                Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                if (pi == null || pi.quantity < 10_000) {
                    Service.getInstance().sendThongBao(player, "Bạn thiếu 10k " + ConstItemName.MONEY_UNIT);
                    return;
                }
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    int tile = getTine(manhTL);
                    if (Util.isTrue(tile, 100)) {
                        int typeOption = 0;
                        int check = dokh.checkSet(dokh);
                        for (ItemOption io : dokh.itemOptions) {
                            switch (io.optionTemplate.id) {
                                case 129:
                                    typeOption = 2;//Set Songoku
                                    break;
                                case 128:
                                    typeOption = 1;  //Set Krillin
                                    break;
                                case 127:
                                    typeOption = 0;  //Set Tenshinhan
                                    break;
                                case 130:
                                    typeOption = 3; //Set Piccolo
                                    break;
                                case 131:
                                    typeOption = 4; //Set Dende
                                    break;
                                case 132:
                                    typeOption = 5; ///Set Pikkoro Daimao
                                    break;
                                case 133:
                                    typeOption = 6;//Set Kakarot  
                                    break;
                                case 134:
                                    typeOption = 7; //Set Vegeta
                                    break;
                                case 135:
                                    typeOption = 8; //Set Nappa
                                    break;
                            }
                        }
                        sendEffectSuccessCombine(player);
                        Item item = ArrietyDrop.randomCS_DKHTL(check, typeOption,
                                dokh.template.gender == 3 ? player.gender : dokh.template.gender);
                        InventoryService.gI().addItemBag(player, item, 0);
                        InventoryService.gI().subQuantityItemsBag(player, dokh, 1);
                    }
                    Service.getInstance().sendThongBao(player, "Xịt nhót");
//                    player.inventory.ruby -= 10_000;
                    InventoryService.gI().subQuantityItemsBag(player, pi, 10_000);
                    if (manhTL != null) {
                        InventoryService.gI().subQuantityItemsBag(player, manhTL, 1);
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Hành trang full");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Thiếu đồ");
            }
        }
    }

    private void upgradeDisguise(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            Item dis = null;
            Item task = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id == 711) {
                        dis = item;
                    } else if (item.template.id == 1260) {
                        task = item;
                    }
                }
            }
            if (player.inventory.ruby < 10_000) {
                Service.getInstance().sendThongBao(player, "khong du tien");
                reOpenItemCombine(player);
                return;
            }
            if (dis != null && task != null && task.quantity >= 99) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                    int oldRuby = player.inventory.ruby;
                    player.inventory.addRuby(-10_000);
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-upgradeDisguise");
                    int tile = 10;
                    if (Util.isTrue(tile, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI().Disguise();
                        InventoryService.gI().addItemBag(player, item, 0);
                        InventoryService.gI().subQuantityItemsBag(player, dis, 1);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryService.gI().subQuantityItemsBag(player, task, 99);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
                }
            }
        } else {
            Service.getInstance().sendThongBao(player, "default");
        }
    }

    private void tradeDoThanLinh(Player player) {
        if (player.combineNew.itemsCombine.size() != 1) {
            Service.getInstance().sendThongBao(player, "Thiếu đồ");
            return;
        }
        if (player.inventory.ruby < COST_RUBY_TRADE_TL) {
            Service.getInstance().sendThongBao(player, "Nạp tiền vào donate cho taooo!");
            return;
        }
        if (InventoryService.gI().getCountEmptyBag(player) < 1) {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
            return;
        }
        if (player.combineNew.itemsCombine.size() == 1) {
            Item doTL = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    if (item.template.id >= 555 && item.template.id <= 567) {
                        doTL = item;
                    }
                }
            }
            if (doTL == null) {
                Service.getInstance().sendThongBao(player, "Thiếu đồ!!");
                return;
            }
            if (doTL.quantity < 1) {
                Service.getInstance().sendThongBao(player, "Cần x1 món đồ Thần Linh bất kì!");
                return;
            }
            if (doTL != null && doTL.quantity >= 1) {
                int tile = 50;
                Item itemMTL = ItemService.gI().createNewItem((short) Util.nextInt(2032, 2036));
                sendEffectSuccessCombineDoTS(player, itemMTL.template.iconID);
                InventoryService.gI().addItemBag(player, itemMTL, 0);
                InventoryService.gI().subQuantityItemsBag(player, doTL, 1);
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-COST_RUBY_TRADE_TL);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-tradeDoThanLinh");
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                player.combineNew.itemsCombine.clear();
                reOpenItemCombine(player);
            }
        }

    }

    private void doiManhKichHoat(Player player) {
        if (player.combineNew.itemsCombine.size() == 2 || player.combineNew.itemsCombine.size() == 3) {
            Item nr1s = null, doThan = null, buaBaoVe = null;
            for (Item it : player.combineNew.itemsCombine) {
                if (it.template.id == 14) {
                    nr1s = it;
                } else if (it.template.id == 2010) {
                    buaBaoVe = it;
                } else if (it.template.id >= 555 && it.template.id <= 567) {
                    doThan = it;
                }
            }
            if (nr1s != null && doThan != null) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0 && player.inventory.gold >= COST_DOI_MANH_KICH_HOAT) {
                    player.inventory.gold -= COST_DOI_MANH_KICH_HOAT;
                    int tiLe = buaBaoVe != null ? 100 : 50;
                    if (Util.isTrue(tiLe, 100)) {
                        sendEffectSuccessCombine(player);
                        Item item = ItemService.gI().createNewItem((short) 2009);
                        item.itemOptions.add(new ItemOption(30, 0));
                        InventoryService.gI().addItemBag(player, item, 0);
                    } else {
                        sendEffectFailCombine(player);
                    }
                    InventoryService.gI().subQuantityItemsBag(player, nr1s, 1);
                    InventoryService.gI().subQuantityItemsBag(player, doThan, 1);
                    if (buaBaoVe != null) {
                        InventoryService.gI().subQuantityItemsBag(player, buaBaoVe, 1);
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hãy chọn 1 trang bị thần linh và 1 viên ngọc rồng 1 sao", "Đóng");
            }
        }
    }

    private void doiVeHuyDiet(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            Item item = player.combineNew.itemsCombine.get(0);
            if (item.isNotNullItem() && item.template.id >= 555 && item.template.id <= 567) {
                if (InventoryService.gI().getCountEmptyBag(player) > 0
                        && player.inventory.gold >= COST_DOI_VE_DOI_DO_HUY_DIET) {
                    player.inventory.gold -= COST_DOI_VE_DOI_DO_HUY_DIET;
                    Item ticket = ItemService.gI().createNewItem((short) (2001 + item.template.type));
                    ticket.itemOptions.add(new ItemOption(30, 0));
                    InventoryService.gI().subQuantityItemsBag(player, item, 1);
                    InventoryService.gI().addItemBag(player, ticket, 99);
                    sendEffectOpenItem(player, item.template.iconID, ticket.template.iconID);

                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private void epSaoTrangBi(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int gem = player.combineNew.gemCombine;
            if (player.inventory.gem < gem) {
                Service.getInstance().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item trangBi = null;
            Item daPhaLe = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (isTrangBiEpSaoPhale(item)) {
                    trangBi = item;
                } else if (isDaPhaLe(item)) {
                    daPhaLe = item;
                }
            }
            int star = 0; // sao pha lê đã ép
            int starEmpty = 0; // lỗ sao pha lê
            if (trangBi != null && daPhaLe != null) {
                ItemOption optionStar = null;
                for (ItemOption io : trangBi.itemOptions) {
                    if (io.optionTemplate.id == 102) {
                        star = io.param;
                        optionStar = io;
                    } else if (io.optionTemplate.id == 107) {
                        starEmpty = io.param;
                    }
                }
                if (star < starEmpty) {
                    player.inventory.subGem(gem);
                    int optionId = getOptionDaPhaLe(daPhaLe);
                    int param = getParamDaPhaLe(daPhaLe);
                    ItemOption option = null;
                    for (ItemOption io : trangBi.itemOptions) {
                        if (io.optionTemplate.id == optionId) {
                            option = io;
                            break;
                        }
                    }
                    if (option != null) {
                        option.param += param;
                    } else {
                        trangBi.itemOptions.add(new ItemOption(optionId, param));
                    }
                    if (optionStar != null) {
                        optionStar.param++;
                    } else {
                        trangBi.itemOptions.add(new ItemOption(102, 1));
                    }

                    InventoryService.gI().subQuantityItemsBag(player, daPhaLe, 1);
                    sendEffectSuccessCombine(player);
                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
    }

    private boolean phaLeHoaTrangBi(Player player) {
        boolean flag = false;
        if (!player.combineNew.itemsCombine.isEmpty()) {
            int gem = player.combineNew.gemCombine;
            int gold = player.combineNew.goldCombine;
            if (player.inventory.gem < gem) {
                Service.getInstance().sendThongBao(player, "Không đủ ngọc để thực hiện");
                flag = false;
                return false;
            }
            if (player.inventory.gold < gold) {
                Service.getInstance().sendThongBao(player, "Không đủ vàng để thực hiện");
                flag = false;
                return false;
            }
            Item item = player.combineNew.itemsCombine.get(0);
            if (isTrangBiPhaLeHoa(item)) {
                int star = 0;
                ItemOption optionStar = null;
                for (ItemOption io : item.itemOptions) {
                    if (io.optionTemplate.id == 107) {
                        star = io.param;
                        optionStar = io;
                        break;
                    }
                }
                if (star < MAX_STAR_ITEM) {
                    player.inventory.subGem(gem);
                    player.inventory.subGold(gold);
                    if (Util.isTrue(player.combineNew.ratioCombine, 230)) {
                        if (optionStar == null) {
                            item.itemOptions.add(new ItemOption(107, 1));
                        } else {
                            optionStar.param++;
                        }
                        if (star == 4) {
                            item.itemOptions.add(new ItemOption(30, 1));
                        }
                        sendEffectSuccessCombine(player);
                        if (optionStar != null && optionStar.param >= 7) {
                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa pha lê hóa " + "thành công " + item.template.name + " lên " + optionStar.param + " sao pha lê");
                            ServerLog.logCombine(player.name, item.template.name, optionStar.param);
                        }
                        flag = true;
                    } else {
                        sendEffectFailCombine(player);
                    }
                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                reOpenItemCombine(player);
            }
        }
        return flag;
    }

    public boolean isItemPhaLeHoa(Item it) {
        return it.template.id == 2053;
    }

    public boolean isItemCaiTrang(Item it) {
        return it.template.type == 5;
    }

    private boolean phaLeHoaCaiTrang(Player player) {
        boolean flag = false;
        if (!player.combineNew.itemsCombine.isEmpty()) {
            Item item = player.combineNew.itemsCombine.get(0);
//            int ruby = 10_000;
            if (isItemCaiTrang(item)) {
                int star = 0;
                ItemOption optionStar = null;
                Item tvv = null;
                for (Item it : player.combineNew.itemsCombine) {
                    if (it.isNotNullItem()) {
                        switch (it.template.id) {
                            case ConstItem.THOI_VANG_VIP:
                                tvv = it;
                                break;
                        }
                    }
                }
                for (ItemOption io : item.itemOptions) {
                    if (io.optionTemplate.id == 107) {
                        star = io.param;
                        optionStar = io;
                        break;
                    }
                }
                if (tvv != null && tvv.quantity >= 10_000) {
                    if (star < MAX_STAR_ITEM) {
                        InventoryService.gI().subQuantityItemsBag(player, tvv, 10_000);
                        if (Util.isTrue(20, 100)) {
                            if (optionStar == null) {
                                item.itemOptions.add(new ItemOption(107, 1));
                            } else {
                                optionStar.param++;
                            }
                            sendEffectSuccessCombine(player);
                            if (optionStar != null && optionStar.param >= 7) {
                                ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa pha lê hóa " + "thành công " + item.template.name + " lên " + optionStar.param + " sao pha lê");
                                ServerLog.logCombine(player.name, item.template.name, optionStar.param);
                            }
                            flag = true;
                        } else {
                            sendEffectFailCombine(player);
                        }
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Không đủ Thỏi vàng VIP để thực hiện");
                    return false;
                }
            }
        }
        return flag;
    }

    private boolean phalehoaDisguise(Player player) {
        boolean flag = false;
        if (!player.combineNew.itemsCombine.isEmpty()) {
            Item item = player.combineNew.itemsCombine.get(0);
            int ruby = 10_000;
            if (isItemPhaLeHoa(item)) {
                int star = 0;
                ItemOption optionStar = null;
                Item capsule = null;
                for (Item it : player.combineNew.itemsCombine) {
                    if (it.isNotNullItem()) {
                        switch (it.template.id) {
                            case 869:
                                capsule = it;
                                break;
                        }
                    }
                }
                for (ItemOption io : item.itemOptions) {
                    if (io.optionTemplate.id == 107) {
                        star = io.param;
                        optionStar = io;
                        break;
                    }
                }
                if (capsule != null || capsule.quantity < 9) {
                    if (player.inventory.ruby < ruby) {
                        Service.getInstance().sendThongBao(player, "Không đủ ruby để thực hiện");
                        return false;
                    }
                    if (star < MAX_SAO_FLAG_BAG) {
                        int oldRuby = player.inventory.ruby;
                        player.inventory.addRuby(-ruby);
                        // ekko ghi log add ruby
                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-phalehoaDisguise");
                        InventoryService.gI().subQuantityItemsBag(player, capsule, 9);
                        if (Util.isTrue(89, 100)) {
                            if (optionStar == null) {
                                item.itemOptions.add(new ItemOption(107, 1));
                            } else {
                                optionStar.param++;
                            }
                            flag = true;
                        } else {
                            sendEffectFailCombine(player);
                        }
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
        return flag;
    }

    private void nhapNgocRong(Player player) {
        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
            if (!player.combineNew.itemsCombine.isEmpty()) {
                Item item = player.combineNew.itemsCombine.get(0);
                if (item != null && item.isNotNullItem()) {
                    if ((item.template.id > 14 && item.template.id <= 20) && item.quantity >= 7) {
                        Item nr = ItemService.gI().createNewItem((short) (item.template.id - 1));
                        InventoryService.gI().addItemBag(player, nr, 0);
                        InventoryService.gI().subQuantityItemsBag(player, item, 7);
                        InventoryService.gI().sendItemBags(player);
                        reOpenItemCombine(player);
                        sendEffectCombineDB(player, item.template.iconID);
                        return;
                    }
                    if (player.inventory.gold >= 500000000) {
                        if (item.template.id == 14 && item.quantity >= 7) {
                            Item nr = ItemService.gI().createNewItem((short) (1015));
                            InventoryService.gI().addItemBag(player, nr, 0);
                            sendEffectCombineDB(player, (short) 9650);
                        } else if (item.template.id == 926 && item.quantity >= 7) {
                            Item nr = ItemService.gI().createNewItem((short) (925));
                            nr.itemOptions.add(new ItemOption(93, 70));
                            InventoryService.gI().addItemBag(player, nr, 0);
                            sendEffectCombineDB(player, item.template.iconID);
                        }
                        InventoryService.gI().subQuantityItemsBag(player, item, 7);
                        player.inventory.gold -= 500000000;
                        Service.getInstance().sendMoney(player);
                        InventoryService.gI().sendItemBags(player);
                        reOpenItemCombine(player);
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ vàng, còn thiếu " + Util.numberToMoney(500000000 - player.inventory.gold) + " vàng");
                    }
                }
            }
        }
    }

    private void epNgocHacAm(Player player) {
        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
            if (!player.combineNew.itemsCombine.isEmpty()) {
                Item item = player.combineNew.itemsCombine.get(0);
                if (item != null && item.isNotNullItem()) {
                    if ((item.template.id > ConstItem.NGOC_HAC_AM_1_SAO && item.template.id <= ConstItem.NGOC_HAC_AM_7_SAO) && item.quantity >= 7) {
                        Item nr = ItemService.gI().createNewItem((short) (item.template.id - 1));
                        InventoryService.gI().addItemBag(player, nr, 0);
                        InventoryService.gI().subQuantityItemsBag(player, item, 7);
                        InventoryService.gI().sendItemBags(player);
                        reOpenItemCombine(player);
                        sendEffectCombineDB(player, item.template.iconID);
                        return;
                    }
                }
            }
        }
    }

    private void epNgocRongBang(Player player) {
//        System.out.println("loggggggggggg");
        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
            if (!player.combineNew.itemsCombine.isEmpty()) {
                Item item = player.combineNew.itemsCombine.get(0);
                if (item != null && item.isNotNullItem()) {
                    if ((item.template.id > 925 && item.template.id <= 931) && item.quantity >= 7) {
                        Item nr = ItemService.gI().createNewItem((short) (item.template.id - 1));
                        InventoryService.gI().addItemBag(player, nr, 0);
                        InventoryService.gI().subQuantityItemsBag(player, item, 7);
                        InventoryService.gI().sendItemBags(player);
                        reOpenItemCombine(player);
                        sendEffectCombineDB(player, item.template.iconID);
                        return;
                    }
                    if (player.inventory.ruby >= 1000) {
                        if (item.template.id == 2009 && item.quantity >= 7) {
                            Item nr = ItemService.gI().createNewItem((short) (931));
                            InventoryService.gI().addItemBag(player, nr, 0);
                            sendEffectCombineDB(player, (short) 8585);
                        }
                        InventoryService.gI().subQuantityItemsBag(player, item, 7);
                        int oldRuby = player.inventory.ruby;
                        player.inventory.addRuby(-1000);
                        // ekko ghi log add ruby
                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-epNgocRongBang");
                        Service.getInstance().sendMoney(player);
                        InventoryService.gI().sendItemBags(player);
                        reOpenItemCombine(player);
                    } else {
                        Service.getInstance().sendThongBao(player, "Không đủ ruby, còn thiếu " + Util.numberToMoney(1000 - player.inventory.ruby) + " ruby");
                    }
                }
            }
        }
    }

    private boolean checkStone(Item linhthu, Item stone) {
        if (linhthu != null && stone != null) {
            if (linhthu.template.id == 2014 && stone.template.id == 1977) {//Aether 
                return true;
            } else if (linhthu.template.id == 2015 && stone.template.id == 1978) {//Aurora 
                return true;
            } else if (linhthu.template.id == 2016 && stone.template.id == 1979) {//Power 
                return true;
            } else if (linhthu.template.id == 2017 && stone.template.id == 1980) {//Mind 
                return true;
            } else if (linhthu.template.id == 2018 && stone.template.id == 1981) {//Space 
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isStone(Item item) {// da
        return item.template.id >= 1977 && item.template.id <= 1981;
    }

    private boolean isLinhThu(Item item) {// xac dinh item linh thu
        return item.template.id >= 2014 && item.template.id <= 2018;
    }

    private int getTile(Item ttt) {// tile thanh cong
        int tile = 0;
        if (ttt.template.id == 2031) {
            tile = 20;
        } else if (ttt.quantity >= 10) {
            tile = Util.nextInt(20, 40);
        }
        return tile;
    }

    private void upgradeLinhThu(Player player) {
        if (player.combineNew.itemsCombine.size() == 4) {
            Item itemLinhThu = null;
            Item stone = null;
            Item HLT = null;// hon linh thu
            Item TTT = null;// thang tinh thach
            for (Item item : player.combineNew.itemsCombine) {
                if (item.isNotNullItem()) {
                    switch (item.template.id) {
                        case 2029:
                            HLT = item;
                            break;
                        case 2031:
                            TTT = item;
                            break;
                    }
                    if (isLinhThu(item)) {
                        itemLinhThu = item;
                    } else if (isStone(item)) {
                        stone = item;
                    }
                }
            }
            if (checkStone(itemLinhThu, stone) && HLT != null) {
                if (player.inventory.ruby < 10_000) {
                    Service.getInstance().sendThongBao(player, "Không đủ ruby để thực hiện");
                    reOpenItemCombine(player);
                    return;
                }
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-10_000);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "CombineServiceNew-upgradeLinhThu");
                int tile = getTile(TTT);
                if (Util.isTrue(tile, 100)) {
                    itemLinhThu.itemOptions.clear();
                    switch (itemLinhThu.template.id) {
                        case 2014:// hoa
                            itemLinhThu.itemOptions.add(new ItemOption(50, Util.nextInt(7, 45)));
                            // itemLinhThu.itemOptions.add(new ItemOption(168, 0));
                            break;
                        case 2015:
                            itemLinhThu.itemOptions.add(new ItemOption(94, Util.nextInt(7, 45)));
                            itemLinhThu.itemOptions.add(new ItemOption(192, 0));
                            break;
                        case 2016:
                            itemLinhThu.itemOptions.add(new ItemOption(77, Util.nextInt(7, 45)));
                            itemLinhThu.itemOptions.add(new ItemOption(80, Util.nextInt(30, 70)));
                            break;
                        case 2017:
                            itemLinhThu.itemOptions.add(new ItemOption(108, Util.nextInt(7, 45)));
                            itemLinhThu.itemOptions.add(new ItemOption(111, 0));
                            break;
                        case 2018:// 13 23
                            itemLinhThu.itemOptions.add(new ItemOption(103, Util.nextInt(7, 45)));
                            itemLinhThu.itemOptions.add(new ItemOption(173, Util.nextInt(30, 70)));
                            break;
                    }
                    sendEffectSuccessCombine(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Xịt nhót");
                    sendEffectFailCombine(player);
                }
                InventoryService.gI().subQuantityItemsBag(player, HLT, 99);
                InventoryService.gI().subQuantityItemsBag(player, stone, 1);
                if (TTT.quantity < 10) {
                    InventoryService.gI().subQuantityItemsBag(player, TTT, 1);
                } else {
                    InventoryService.gI().subQuantityItemsBag(player, TTT, 10);
                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                reOpenItemCombine(player);
            } else {
                Service.getInstance().sendThongBao(player, "Không đúng Hệ với Linh Thú hoặc thiếu Hồn Linh Thú");
            }
        }
    }

    private void nangCapVatPham(Player player) {
        if (player.combineNew.itemsCombine.size() >= 2 && player.combineNew.itemsCombine.size() < 4) {
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type < 5).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.type == 14).count() != 1) {
                return;
            }
            if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.stream().filter(item -> item.isNotNullItem() && item.template.id == 1143).count() != 1) {
                return;
            }
            Item itemDo = null;
            Item itemDNC = null;
            Item itemDBV = null;
            for (int j = 0; j < player.combineNew.itemsCombine.size(); j++) {
                if (player.combineNew.itemsCombine.get(j).isNotNullItem()) {
                    if (player.combineNew.itemsCombine.size() == 3 && player.combineNew.itemsCombine.get(j).template.id == 1143) {
                        itemDBV = player.combineNew.itemsCombine.get(j);
                        continue;
                    }
                    if (player.combineNew.itemsCombine.get(j).template.type < 5) {
                        itemDo = player.combineNew.itemsCombine.get(j);
                    } else {
                        itemDNC = player.combineNew.itemsCombine.get(j);
                    }
                }
            }
            if (isCoupleItemNangCapCheck(itemDo, itemDNC)) {
                int countDaNangCap = player.combineNew.countDaNangCap;
                int gold = player.combineNew.goldCombine;
                short countDaBaoVe = player.combineNew.countDaBaoVe;
                if (player.inventory.gold < gold) {
                    Service.getInstance().sendThongBao(player, "Không đủ vàng để thực hiện");
                    return;
                }
                if (itemDNC.quantity < countDaNangCap) {
                    return;
                }
                if (player.combineNew.itemsCombine.size() == 3) {
                    if (Objects.isNull(itemDBV)) {
                        return;
                    }
                    if (itemDBV.quantity < countDaBaoVe) {
                        return;
                    }
                }
                int level = 0;
                ItemOption optionLevel = null;
                for (ItemOption io : itemDo.itemOptions) {
                    if (io.optionTemplate.id == 72) {
                        level = io.param;
                        optionLevel = io;
                        break;
                    }
                }
                if (level < MAX_LEVEL_ITEM) {
                    player.inventory.gold -= gold;
                    ItemOption option = null;
                    ItemOption option2 = null;
                    for (ItemOption io : itemDo.itemOptions) {
                        if (io.optionTemplate.id == 47
                                || io.optionTemplate.id == 6
                                || io.optionTemplate.id == 0
                                || io.optionTemplate.id == 7
                                || io.optionTemplate.id == 14
                                || io.optionTemplate.id == 22
                                || io.optionTemplate.id == 23) {
                            option = io;
                        } else if (io.optionTemplate.id == 27 || io.optionTemplate.id == 28) {
                            option2 = io;
                        }
                    }
                    if (Util.isTrue(player.combineNew.ratioCombine, 100)) {
                        if (option.optionTemplate.id == 14) {
                            option.param += 1;
                        } else {
                            option.param += (option.param * 10 / 100);
                        }
                        if (option2 != null) {
                            option2.param += (option2.param * 10 / 100);
                        }
                        if (optionLevel == null) {
                            itemDo.itemOptions.add(new ItemOption(72, 1));
                        } else {
                            optionLevel.param++;
                        }
                        if (optionLevel != null && optionLevel.param >= 5) {
                            ServerNotify.gI().notify("Chúc mừng " + player.name + " vừa nâng cấp "
                                    + "thành công " + itemDo.template.name + " lên +" + optionLevel.param);
                        }
                        sendEffectSuccessCombine(player);
                    } else {
                        if ((level == 2 || level == 4 || level == 6) && (player.combineNew.itemsCombine.size() != 3)) {
                            if (option.optionTemplate.id == 14) {
                                option.param -= 1;
                            } else {
                                option.param -= (option.param * 15 / 100);
                            }
                            if (option2 != null) {
                                option2.param -= (option2.param * 15 / 100);
                            }
                            optionLevel.param--;
                        }
                        sendEffectFailCombine(player);
                    }
                    if (player.combineNew.itemsCombine.size() == 3) {
                        InventoryService.gI().subQuantityItemsBag(player, itemDBV, countDaBaoVe);
                    }
                    InventoryService.gI().subQuantityItemsBag(player, itemDNC, player.combineNew.countDaNangCap);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    reOpenItemCombine(player);
                }
            }
        }
    }

    private boolean isCoupleItemNangCapCheck(Item trangBi, Item daNangCap) {
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // --------------------------------------------------------------------------

    /**
     * Hiệu ứng mở item
     *
     * @param player
     */
    public void sendEffectOpenItem(Player player, short icon1, short icon2) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(OPEN_ITEM);
            msg.writer().writeShort(icon1);
            msg.writer().writeShort(icon2);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng đập đồ thành công
     *
     * @param player
     */
    public void sendEffectSuccessCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_SUCCESS);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    private void sendEffectCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(8);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Hiệu ứng đập đồ thất bại
     *
     * @param player
     */
    public void sendEffectFailCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_FAIL);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendEffectFailCombineDoTS(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(8);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gửi lại danh sách đồ trong tab combine
     *
     * @param player
     */
    public void reOpenItemCombine(Player player) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(REOPEN_TAB_COMBINE);
            msg.writer().writeByte(player.combineNew.itemsCombine.size());
            for (Item it : player.combineNew.itemsCombine) {
                for (int j = 0; j < player.inventory.itemsBag.size(); j++) {
                    if (it == player.inventory.itemsBag.get(j)) {
                        msg.writer().writeByte(j);
                    }
                }
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    /**
     * Hiệu ứng ghép ngọc rồng
     *
     * @param player
     * @param icon
     */
    private void sendEffectCombineDB(Player player, short icon) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(COMBINE_DRAGON_BALL);
            msg.writer().writeShort(icon);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    private int getCountDaBaoVe(int level) {
        return level + 1;
    }

    // --------------------------------------------------------------------------Ratio,
    // cost combine
    private int getRatioDaMayMan(int id) {
        switch (id) {
            case 1079:
                return 10;
            case 1080:
                return 20;
            case 1081:
                return 30;
            case 1082:
                return 40;
            case 1083:
                return 50;
        }
        return 0;
    }

    private int getRatioDaNangCap(int id) {
        switch (id) {
            case 1074:
                return 10;
            case 1075:
                return 20;
            case 1076:
                return 30;
            case 1077:
                return 40;
            case 1078:
                return 50;
        }
        return 0;
    }

    private int getGoldPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 5000000;
            case 1:
                return 10000000;
            case 2:
                return 20000000;
            case 3:
                return 40000000;
            case 4:
                return 60000000;
            case 5:
                return 90000000;
            case 6:
                return 120000000;
            case 7:
                return 120000000;
            case 8:
                return 120000000;
        }
        return 0;
    }

    private float getRatioPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 50f;
            case 1:
                return 30f;
            case 2:
                return 20f;
            case 3:
                return 20f;
            case 4:
                return 5f;
            case 5:
                return 3f;
            case 6:
                return 1f;
            case 7:
                return 0.5f;
            case 8:
                return 0.05f;
            case 9:
            case 10:
            case 11:
                return 0.01f;
        }
        return 0;
    }

    private float getTileNangHonHoan(int level) {
        switch (level) {
            case 1319:
            case 1320:
            case 1321:
            case 1322:
            case 1323:
            case 1324:
            case 1325:
            case 1326:
                return 20f;
        }
        return 0;
    }

    private int getGemPhaLeHoa(int star) {
        switch (star) {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 20;
            case 3:
                return 50;
            case 4:
                return 50;
            case 5:
                return 100;
            case 6:
                return 200;
            case 7:
                return 1000;
            case 8:
                return 1000;
            case 9:
                return 5000;
            case 10:
                return 10000;
            case 11:
                return 20000;
        }
        return 0;
    }

    private int getGemEpSao(int star) {
        switch (star) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 5;
            case 3:
                return 10;
            case 4:
                return 25;
            case 5:
                return 50;
            case 6:
                return 100;
            case 7:
                return 100;

        }
        return 0;
    }

    private int getTileNangCapDo(int level) {
        switch (level) {
            case 0:
                return 80;
            case 1:
                return 50;
            case 2:
                return 20;
            case 3:
                return 10;
            case 4:
                return 7;
            case 5:
                return 5;
            case 6:
                return 1;
        }
        return 0;
    }

    private int getCountDaNangCapDo(int level) {
        switch (level) {
            case 0:
                return 3;
            case 1:
                return 7;
            case 2:
                return 11;
            case 3:
                return 17;
            case 4:
                return 23;
            case 5:
                return 35;
            case 6:
                return 50;
        }
        return 0;
    }

    private int lvbt(Item bongtai) {
        switch (bongtai.template.id) {
            case 454:
                return 1;
            case 921:
                return 2;
            case 1995:
                return 3;
        }
        return 0;

    }

    private int getGoldNangCapDo(int level) {
        switch (level) {
            case 0:
                return 10000;
            case 1:
                return 70000;
            case 2:
                return 300000;
            case 3:
                return 1500000;
            case 4:
                return 7000000;
            case 5:
                return 23000000;
            case 6:
                return 100000000;
        }
        return 0;
    }

    // --------------------------------------------------------------------------check
    public boolean isAngelClothes(int id) {
        if (id >= 1048 && id <= 1062) {
            return true;
        }
        return false;
    }

    public boolean isDestroyClothes(int id) {
        if (id >= 650 && id <= 662) {
            return true;
        }
        return false;
    }

    private String getTypeTrangBi(int type) {
        switch (type) {
            case 0:
                return "Áo";
            case 1:
                return "Quần";
            case 2:
                return "Găng";
            case 3:
                return "Giày";
            case 4:
                return "Nhẫn";
        }
        return "";
    }

    public boolean isManhTrangBi(Item it) {
        switch (it.template.id) {
            case 1066:
            case 1067:
            case 1068:
            case 1069:
            case 1070:
                return true;
        }
        return false;
    }

    public boolean isCraftingRecipe(int id) {
        switch (id) {
            case 1071:
            case 1072:
            case 1073:
            case 1084:
            case 1085:
            case 1086:
                return true;
        }
        return false;
    }

    public int getRatioCraftingRecipe(int id) {
        switch (id) {
            case 1071:
                return 0;
            case 1072:
                return 0;
            case 1073:
                return 0;
            case 1084:
                return 10;
            case 1085:
                return 10;
            case 1086:
                return 10;
        }
        return 0;
    }

    public boolean isUpgradeStone(int id) {
        switch (id) {
            case 1074:
            case 1075:
            case 1076:
            case 1077:
            case 1078:
                return true;
        }
        return false;
    }

    public int getRatioUpgradeStone(int id) {
        switch (id) {
            case 1074:
                return 10;
            case 1075:
                return 20;
            case 1076:
                return 30;
            case 1077:
                return 40;
            case 1078:
                return 50;
        }
        return 0;
    }

    public boolean isLuckyStone(int id) {
        switch (id) {
            case 1079:
            case 1080:
            case 1081:
            case 1082:
            case 1083:
                return true;
        }
        return false;
    }

    private int getGoldnangbt(int lvbt) {
        return GOLD_BONG_TAI2;
    }

    private int getRubyUpgradeBT(int lvbt) {
        return RUBY_BONG_TAI2;
    }

    private int getcountmvbtnangbt(int lvbt) {
        return 99;
    }

    private boolean checkbongtai(Item item) {
        if (item.template.id == 454 || item.template.id == 921) {
            return true;
        }
        return false;
    }

    public int getRatioLuckyStone(int id) {
        switch (id) {
            case 1079:
                return 10;
            case 1080:
                return 20;
            case 1081:
                return 30;
            case 1082:
                return 40;
            case 1083:
                return 50;
        }
        return 0;
    }

    private boolean isCoupleItemNangCap(Item item1, Item item2) {
        Item trangBi = null;
        Item daNangCap = null;
        if (item1 != null && item1.isNotNullItem()) {
            if (item1.template.type < 5) {
                trangBi = item1;
            } else if (item1.template.type == 14) {
                daNangCap = item1;
            }
        }
        if (item2 != null && item2.isNotNullItem()) {
            if (item2.template.type < 5) {
                trangBi = item2;
            } else if (item2.template.type == 14) {
                daNangCap = item2;
            }
        }
        if (trangBi != null && daNangCap != null) {
            if (trangBi.template.type == 0 && daNangCap.template.id == 223) {
                return true;
            } else if (trangBi.template.type == 1 && daNangCap.template.id == 222) {
                return true;
            } else if (trangBi.template.type == 2 && daNangCap.template.id == 224) {
                return true;
            } else if (trangBi.template.type == 3 && daNangCap.template.id == 221) {
                return true;
            } else if (trangBi.template.type == 4 && daNangCap.template.id == 220) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isDaPhaLe(Item item) {
        if (item != null && item.isNotNullItem()) {
            return item.template.type == 30 || (item.template.id >= 14 && item.template.id <= 20);
        }
        return false;
    }

    private boolean isTrangBiPhaLeHoa(Item item) {
        if (item != null && item.isNotNullItem()) {
            if (item.template.type < 5 || item.template.type == 32) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean isTrangBiEpSaoPhale(Item item) {
        if (item != null && item.isNotNullItem()) {
            if (item.template.type <= 5 || item.template.type == 32 || item.template.id == 2053) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private int getParamDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).param;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 5; // +5%hp
            case 19:
                return 5; // +5%ki
            case 18:
                return 3; // +5%hp/30s
            case 17:
                return 5; // +5%ki/30s
            case 16:
                return 5; // +3%sđ
            case 15:
                return 2; // +2%giáp
            case 14:
                return 2; // +2%né đòn
            default:
                return -1;
        }
    }

    private int getOptionDaPhaLe(Item daPhaLe) {
        if (daPhaLe.template.type == 30) {
            return daPhaLe.itemOptions.get(0).optionTemplate.id;
        }
        switch (daPhaLe.template.id) {
            case 20:
                return 77;
            case 19:
                return 103;
            case 18:
                return 50;
            case 17:
                return 81;
            case 16:
                return 80;
            case 15:
                return 94;
            case 14:
                return 108;
            default:
                return -1;
        }
    }

    /**
     * Trả về id item c0
     *
     * @param gender
     * @param type
     * @return
     */
    private int getTempIdItemC0(int gender, int type) {
        if (type == 4) {
            return 12;
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return 0;
                    case 1:
                        return 6;
                    case 2:
                        return 21;
                    case 3:
                        return 27;
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return 1;
                    case 1:
                        return 7;
                    case 2:
                        return 22;
                    case 3:
                        return 28;
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return 2;
                    case 1:
                        return 8;
                    case 2:
                        return 23;
                    case 3:
                        return 29;
                }
                break;
        }
        return -1;
    }

    // Trả về tên đồ c0
    private String getNameItemC0(int gender, int type) {
        if (type == 4) {
            return "Rada cấp 1";
        }
        switch (gender) {
            case 0:
                switch (type) {
                    case 0:
                        return "Áo vải 3 lỗ";
                    case 1:
                        return "Quần vải đen";
                    case 2:
                        return "Găng thun đen";
                    case 3:
                        return "Giầy nhựa";
                }
                break;
            case 1:
                switch (type) {
                    case 0:
                        return "Áo sợi len";
                    case 1:
                        return "Quần sợi len";
                    case 2:
                        return "Găng sợi len";
                    case 3:
                        return "Giầy sợi len";
                }
                break;
            case 2:
                switch (type) {
                    case 0:
                        return "Áo vải thô";
                    case 1:
                        return "Quần vải thô";
                    case 2:
                        return "Găng vải thô";
                    case 3:
                        return "Giầy vải thô";
                }
                break;
        }
        return "";
    }

    public void endCombine(Player player) {
        sendEffectSuccessCombine(player);
        reOpenItemCombine(player);
    }

    public int get_Tile_nang_Do_TS(Item danc, Item congthuc) {
        int tile = 0;
        if (congthuc.isCongthucVip()) {
            tile = 35;
        } else if (congthuc.isCongthucNomal()) {
            tile = 20;
        }
        if (danc != null && danc.isdanangcapDoTs()) {
            tile += (danc.template.id - 1073) * 10;
        }
        return tile;
    }

    // --------------------------------------------------------------------------Text
    // tab combine
    private String getTextTopTabCombine(int type) {
        switch (type) {
            case NANG_CAP_NHAN:
                return "Ta sẽ giúp ngươi\n nâng cấp nhẫn thành\n sức mạnh tối thượnng";
            case GIAM_DINH:
                return "Ta sẽ giám định\n Sách của ngươi ";
            case TAY_SACH:
                return "Ta sẽ Tẩy Sách\ncủa ngươi về trạng thái ban đầu ";
            case NANG_SACH:
                return "Ta sẽ Nâng cấp Sách\ncủa ngươi lên bậc 2 ";
            case PHUC_HOI:
                return "Ta sẽ Phục hồi sách\ncủa ngươi ";
            case PHAN_RA:
                return "Ta sẽ Phân rã sách\ncủa ngươi ";
            case UPDATE_TRAIN_ARMOR:
                return "Ta sẽ giúp ngươi làm điều đó =)))";
            case PHA_LE_HOA_CAI_TRANG:
                return "Ta sẽ giúp ngươi làm điều đó =)))";
            case PHA_LE_HOA_DISGUISE:
                return "Ta sẽ giúp ngươi làm điều đó =)))";
            case TRADE_PET:
                return "Ta sẽ giúp ngươi làm điều đó =)))";
            case NANG_CAP_DE_WHIS:
                return "Ta sẽ giúp con nâng cấp đệ Whís";
            case UPGRADE_PET:
                return "Ta sẽ giúp ngươi làm điều đó =)))";
            case UPGRADE_PET_CELL:
                return "Ta sẽ giúp con Nâng cấp đệ tử";
            case UPGRADE_POWER_STONE_COINT:
            case UPGRADE_POWER_STONE:
                return "Ta sẽ giúp ngươi nâng cấp viên đá sức mạnh\nHãy đưa cho ta viên đá nào";
            case UPGRADE_THAN_LINH:
                return "Ta sẽ giúp ngươi\n làm điều đó";
            case UPGRADE_HUY_DIET:
                return "Ta sẽ giúp ngươi\n làm điều đó";
            case UPGRADE_LINHTHU:
                return "Ta sẽ giúp ngươi\n làm điều đó";
            case UPGRADE_CAITRANG:
                return "Ta sẽ giúp ngươi\n làm điều đó";
            case NANG_CAP_DO_KICH_HOAT:
                return "Ta sẽ giúp ngươi\n làm điều đó";
            case NANG_CAP_DO_TS:
                return "Chế tạo trang bị thiên sứ!";
            case TRADE_DO_THAN_LINH:
                return "Ta sẽ giúp ngươi trao đổi đồ Thần Linh này\nsang những mảnh Thần Linh";
            case EP_SAO_TRANG_BI:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở lên mạnh mẽ";
            case PHA_LE_HOA_TRANG_BI:
            case PHA_LE_HOA_TRANG_BI_X10:
                return "Ta sẽ phù phép\ncho trang bị của ngươi\ntrở thành trang bị pha lê";
            case NHAP_NGOC_RONG:
                return "Ta sẽ phù phép\ncho 7 viên Ngọc Rồng\nthành 1 viên Ngọc Rồng cấp cao";
            case EP_NGOC_HAC_AM:
                return "Ta sẽ phù phép\ncho 7 viên Ngọc Rồng\nthành 1 viên Ngọc Rồng Hắc Ám cấp cao";
            case EP_NGOC_RONG_BANG:
                return "Ta sẽ phù phép\ncho 7 viên Ngọc Rồng Băng\nthành 1 viên Ngọc Rồng cấp cao";
            case NANG_CAP_CAI_TRANG:
                return "Ta sẽ giúp ngươi \nlàm điều đó";
            case REMOVE_OPTION:
                return "Ta sẽ giúp ngươi làm điều đó";
            case NANG_CHAN_MENH:
                return "Ta sẽ phù phép cho ngươi chân mệnh thành hồn hoàn cao cấp!";
            case NANG_CAP_VAT_PHAM:
                return "Ta sẽ phù phép cho trang bị của ngươi trở lên mạnh mẽ";
            case DOI_VE_HUY_DIET:
                return "Ta sẽ đưa ngươi 1 vé đổi đồ\nhủy diệt, đổi lại ngươi phải đưa ta\n 1 món đồ thần linh tương ứng";
            case DAP_SET_KICH_HOAT:
                return "Ta sẽ giúp ngươi chuyển hóa\n1 món đồ hủy diệt\nthành 1 món đồ kích hoạt";
            // case DOI_MANH_KICH_HOAT:
            // return "Ta sẽ giúp ngươi biến hóa\nviên ngọc 1 sao và 1 món đồ\nthần linh
            // thành mảnh kích hoạt";
            case DAP_SET_KICH_HOAT_CAO_CAP:
                return "Ta sẽ giúp ngươi chuyển hóa\n3 món đồ hủy diệt\nthành 1 món đồ kích hoạt cao cấp";
            case GIA_HAN_CAI_TRANG:
                return "Ta sẽ phù phép\n cho trang bị của mi\n thêm hạn sử dụng";
            case NANG_CAP_DO_THIEN_SU:
                return "Nâng cấp\n trang bị thiên sứ";
            case NANG_CAP_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata của ngươi\nthành cấp 2";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Ta sẽ phù phép\ncho bông tai Porata của ngươi\nthành cấp 3";
            case MO_CHI_SO_BONG_TAI:
                return "Ta sẽ phù phép\ncho bông tai Porata cấp 2 của ngươi\ncó 1 chỉ số ngẫu nhiên";
            case PHAN_RA_SKH_VIP:
                return "Ta sẽ giúp ngươi\nphân ra SKH VIP\n";
            case CHUYEN_HOA_SKH_HUY_DIET:
                return "Ta sẽ giúp ngươi\nchuyển hóa SKH Hủy Diệt\n";
            case KHAM_BUA:
                return "Ta sẽ giúp ngươi\nkhảm bùa ma thuật\n";
            case PHA_LE_HOA_VONG_THIEN_SU:
                return "Ta sẽ giúp ngươi\npha lê hóa vòng thiên sứ\n";
            case CHE_TAO_BANH_QUY:
                return "Ta sẽ giúp ngươi\nchế tạo bánh quy\n";
            case CHE_TAO_KEO_GIANG_SINH:
                return "Ta sẽ giúp ngươi\nchế tạo kẹo giáng sinh\n";
            case NANG_CAP_CAI_TRANG_HOA_XUONG:
                return "Ta sẽ giúp ngươi\nnâng cấp cải trang hóa xương\n";
            case NANG_CAP_SKH:
                return "Ta sẽ giúp ngươi\nnâng cấp SKH\n";
            case NANG_CAP_PET:
                return "Ta sẽ giúp ngươi\nnâng cấp hoặc\ntẩy cấp Pet";
            case DOI_DA_THAN_LINH:
                return "Ta sẽ giúp ngươi\nđổi đá thần linh\n";
            default:
                return "";
        }
    }

    private String getTextInfoTabCombine(int type) {
        switch (type) {
            case UPGRADE_POWER_STONE_COINT:
            case UPGRADE_POWER_STONE:
                return "Vào hành trang\nChọn viên đá sức mạnh\nva lựa chọn nguyên liệu nâng cấp phú hợp\n10k " + ConstItemName.MONEY_UNIT + " hay là 30k ruby?";
            case GIAM_DINH:
                return "vào hành trang\nChọn Sách Tuyệt Kỹ\nChọn Bùa giám định\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case TAY_SACH:
                return "vào hành trang\nChọn Sách Tuyệt Kỹ\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case NANG_SACH:
                return "vào hành trang\nChọn Sách Tuyệt Kỹ Bậc 1\n Chọn 10 Kìm bấm giấy"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHUC_HOI:
                return "vào hành trang\nChọn Sách Tuyệt Kỹ\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHAN_RA:
                return "vào hành trang\nChọn Sách Tuyệt Kỹ\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case UPDATE_TRAIN_ARMOR:
                return "Hãy đưa ta x99 Thăng Tinh Thạch\nĐể làm Giáp Luyện Tập C1";
            case NANG_CAP_DE_WHIS:
                return "Bản thân con phải sở hữu đệ tử Cell cấp 3\nx99 Thăng Tinh Thạch và 50k ruby\nTa sẽ giúp con sở hữu đệ tử Whis";
            case PHA_LE_HOA_CAI_TRANG:
                return "Con hãy đưa cho ta 1 Cải trang bất kì\nVà 10k Thỏi vàng VIP";
            case PHA_LE_HOA_DISGUISE:
                return "Con hãy đưa ta x1 Kiếm Hasakiiii mà muốn pha lê hóa\nVà x9 capsule 1 Sao";
            case TRADE_PET:
                return "Con hãy đưa cho ta x10 Gậy thượng đế\nTa sẽ giúp con chuyển đệ Black gâu ku\nThanh đệ tử Whis";
            case UPGRADE_PET:
                return "Vào hành trang chọn x10 Đá Nguyền Ấn\nvà 5k ruby";
            case UPGRADE_PET_CELL:
                return "Vào hành trang chọn x999 Ấu trùng Cell\nvà 30k ruby";
            case UPGRADE_HUY_DIET:
                return "Vào hành trang\nChọn 1 món Thần Linh Kích Hoạt bất kì\n "
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case UPGRADE_THAN_LINH:
                return "Vào hành trang\nChọn 1 món Thần Linh bất kì\n "
                        + " và 1 món SKH bất kỳ\n"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case UPGRADE_LINHTHU:
                return "Vào hàng trang\nChọn x1 Linh thú bất kì, x1 Stone cùng hệ Linh Thú\nx1 Thăng Tinh Thạch\nx10 Thăng Tinh Thạch để tỉ lệ Thành Công cao hơn\nx99 Hồn Linh Thú\nXong rồi ấn Nâng Cấp";
            case UPGRADE_CAITRANG:
                return "Vào hành trang\nChọn x1 cải trang jaki chun\nvà  Lọ nước hồi sinh";
            case NANG_CAP_DO_KICH_HOAT:
                return "Vào hành trang\nChọn 5 mảnh Thần Linh bất kì\n "
                        + " và 1 món SKH thường bất kỳ\n"
                        + "Chỉ cần chọn 'Nâng Cấp'";
            case EP_SAO_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa) có ô đặt sao pha lê\nChọn loại sao pha lê\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case TRADE_DO_THAN_LINH:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc nhẫn)\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case PHA_LE_HOA_TRANG_BI:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nSau đó chọn 'Nâng cấp'";
            case PHA_LE_HOA_TRANG_BI_X10:
                return "Chọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nSau đó chọn 'Nâng cấp'\n Khi nâng cấp thành công hoặc đủ 100 lần thì sẽ dừng lại";
            case NHAP_NGOC_RONG:
                return "Vào hành trang\nChọn 7 viên ngọc cùng sao\nSau đó chọn 'Làm phép'";
            case EP_NGOC_HAC_AM:
                return "Vào hành trang\nChọn 7 viên ngọc hắc ám cùng sao\nSau đó chọn 'Làm phép'";
            case EP_NGOC_RONG_BANG:
                return "Vào hành trang\nChọn 7 viên ngọc cùng sao\nSau đó chọn 'Làm phép'";
            case NANG_CAP_CAI_TRANG:
                return "Ta sẽ giúp ngươi làm điều đó";
            case NANG_SKH_NEW:
                return "Hãy bỏ trang bị trang bị sét kích hoạt\n và 5k " + ConstItemName.MONEY_UNIT + " vào sau đó ấn \nNÂNG CẤP\n nếu bổ sung thêm đồ thần linh\n tỉ lệ sẽ nhân đôi";
            case NANG_SKH_NEW_RUBY:
                return "Hãy bỏ trang bị trang bị sét kích hoạt\n và 20k Hồng ngọc vào sau đó ấn \nNÂNG CẤP\n nếu bổ sung thêm đồ thần linh\n tỉ lệ sẽ nhân đôi";
            case REMOVE_OPTION:
                return "Hãy bỏ trang bị \ncần tẩy sao vào\n sau đó ấn nâng cấp";
            case NANG_CAP_NHAN:
                return "Hãy bỏ nhẫn thời không\n và 10k đồng coin vào\n đó chọn nâng cấp\n Từ cấp 1 đến cấp 5 tỉ lệ 50% mỗi cấp tăng 3% chỉ số\n Từ cấp 5 đến 7 tỉ lệ 10% mỗi cấp tăng 10% chỉ số  ";
            case NANG_CHAN_MENH:
                return "Ta sẽ giúp ngươi làm điều đó";
            case NANG_CAP_VAT_PHAM:
                return "vào hành trang\nChọn trang bị\n(Áo, quần, găng, giày hoặc rađa)\nChọn loại đá để nâng cấp\n"
                        + "Sau đó chọn 'Nâng cấp'";
            case DOI_VE_HUY_DIET:
                return "Vào hành trang\nChọn món đồ thần linh tương ứng\n(Áo, quần, găng, giày hoặc nhẫn)\nSau đó chọn 'Đổi'";
            case DAP_SET_KICH_HOAT:
                return "Vào hành trang\nChọn món đồ hủy diệt tương ứng\n(Áo, quần, găng, giày hoặc nhẫn)\n(Có thể thêm 1 món đồ thần linh bất kỳ để tăng tỉ lệ)\nSau đó chọn 'Đập'";
            // case DOI_MANH_KICH_HOAT:
            // return "Vào hành trang\nChọn món đồ thần linh tương ứng\n(Áo, quần, găng,
            // giày hoặc nhẫn)\nSau đó chọn 'Đổi'";
            case DAP_SET_KICH_HOAT_CAO_CAP:
                return "Vào hành trang\nChọn 3 món đồ hủy diệt khác nhau\n(Áo, quần, găng, giày hoặc nhẫn)\nSau đó chọn 'Đập'";
            case GIA_HAN_CAI_TRANG:
                return "Vào hành trang \n Chọn cải trang có hạn sử dụng \n Chọn thẻ gia hạn \n Sau đó chọn gia hạn";
            case NANG_CAP_DO_THIEN_SU:
                return "Cần 1 công thức\nTrang bị thiên sứ\nĐá nâng cấp (tùy chọn)\nĐá may mắn (tùy chọn)";
            case NANG_CAP_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh bông tai để nâng cấp, số lượng\n99 cái\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_BONG_TAI_CAP3:
                return "Vào hành trang"
                        + "\nChọn bông tai Porata cấp 2"
                        + "\nx9999 " + ConstItemName.BINH_NUOC
                        + "\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_DO_TS:
                return "Cần 1 công thức\n "
                        + "Mảnh trang bị tương ứng"
                        + "1 đá nâng cấp (tùy chọn)\n"
                        + "1 đá may mắn (tùy chọn)\n";
            case MO_CHI_SO_BONG_TAI:
                return "Vào hành trang\nChọn bông tai Porata\nChọn mảnh hồn bông tai số lượng 99 cái\nvà đá xanh lam để nâng cấp\nSau đó chọn 'Nâng cấp'";
            case PHAN_RA_SKH_VIP:
                return "Chọn trang bị\n(SKH VIP)\nSau đó chọn 'Nâng cấp'";
            case CHUYEN_HOA_SKH_HUY_DIET:
                return "Chọn trang bị\n(SKH Thần)\nChọn Thỏi vàng VIP số lượng 10k để nâng cấp\nSau đó chọn 'Nâng cấp'";
            case KHAM_BUA:
                return "Chọn trang bị\n(Áo, quần, giày, găng, rada)\nChọn x10 bùa ma thuật để nâng cấp\nSau đó chọn 'Nâng cấp'";
            case PHA_LE_HOA_VONG_THIEN_SU:
                return "Chọn trang bị\n(Vòng thiên sứ)\nChọn 50k Thỏi Vàng VIP để pha lê hóa\nSau đó chọn 'Nâng cấp'";
            case CHE_TAO_BANH_QUY:
                return "Chọn \nKẹo giáng sinh\nChọn bột mì để chế tạo\nSau đó chọn 'Nâng cấp'";
            case CHE_TAO_KEO_GIANG_SINH:
                return "Chọn \nGậy gỗ\nChọn kẹo đường để chế tạo\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_CAI_TRANG_HOA_XUONG:
                return "Chọn trang bị\nCải trang Ngộ Không hoặc Đường Tăng\nChọn 200k Thỏi Vàng VIP để nâng cấp\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_SKH:
                return "Chọn trang bị\n2 món thần linh\nHoặc 2 món SKH\n và x10 đá thần linh\nSau đó chọn 'Nâng cấp'";
            case NANG_CAP_PET:
                return "Chọn Pet\nx3 Đá Nguyệt Tử\n1k TVV\nđể nâng cấp\nHoặc Pet\nx1 Đá Thanh Tẩy\n1k TVV\nSau đó chọn 'Nâng cấp'";
            case DOI_DA_THAN_LINH:
                return "Chọn \n1 món thần linh\nSau đó chọn 'Nâng cấp'";
            default:
                return "";
        }
    }

    public void sendEffectSuccessCombineDoTS(Player player, short icon) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(7);
            msg.writer().writeShort(icon);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffectSuccessCombineDoTS(Player player, short icon, short x, short y, short npcId) {
        Message msg;
        try {
            msg = new Message(-81);
            msg.writer().writeByte(7);
            msg.writer().writeShort(icon);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            msg.writer().writeShort(npcId);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ekko kiểm tra đồ có nằm trong danh sách SKH nâng cấp không
    public static int getNextIdInList(int id) {
        for (int[][] itemType : ConstItem.LIST_ITEM_SKH_MINI) { // Lặp qua từng loại trang bị (áo, quần, ...)
            for (int[] upgradeLevel : itemType) { // Lặp qua từng cấp độ nâng cấp (td, nm, xd)
                for (int i = 0; i < upgradeLevel.length - 1; i++) { // Duyệt qua các phần tử, trừ phần tử cuối
                    if (upgradeLevel[i] == id) { // Nếu tìm thấy ID cần kiểm tra
                        return upgradeLevel[i + 1]; // Trả về phần tử tiếp theo liền kề
                    }
                }
            }
        }
        return -1; // Không tìm thấy ID
    }

    public static int getFirstItemByPlanetAndType(int planetIndex, int itemTypeIndex) {
        // Kiểm tra hành tinh hợp lệ
        if (planetIndex < 0 || planetIndex >= ConstItem.LIST_ITEM_SKH_MINI.length) {
//            throw new IllegalArgumentException("Hành tinh không hợp lệ (planetIndex không nằm trong phạm vi).");
            return -1;
        }

        // Kiểm tra loại đồ hợp lệ
        if (itemTypeIndex < 0 || itemTypeIndex >= ConstItem.LIST_ITEM_SKH_MINI[planetIndex].length) {
//            throw new IllegalArgumentException("Loại đồ không hợp lệ (itemTypeIndex không nằm trong phạm vi).");\
            return -1;
        }

        // Lấy danh sách đồ của loại đồ được chỉ định
        int[] upgradeLevel = ConstItem.LIST_ITEM_SKH_MINI[planetIndex][itemTypeIndex];

        // Trả về phần tử đầu tiên nếu danh sách không rỗng
        return upgradeLevel.length > 0 ? upgradeLevel[0] : -1;
    }

    // ekko
    private void nangCapPet(Player player) {
        if (player.combineNew.itemsCombine.size() == 3) {
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                Item pet = null;
                Item daNguyetTu = null;
                Item tvv = null;
                Item daThanhTay = null;
                boolean isNangCap = false;
                boolean isTay = false;
                int maxLevel = 8;
                int slTVV = 1000;
                int slDaNguyetTu = 3;
                int slDaThanhTay = 1;
                int chiSoCongThem = 2;
                int currentLevel = -1;
                int sdNguyetTu = -1;
                int kiNguyetTu = -1;
                int hpNguyetTu = -1;
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem()) {
                        // là pet
                        if(item.itemIsPet()) {
                            // kiểm tra cấp của pet
                            for (ItemOption option : item.itemOptions) {
                                if(option.optionTemplate.id == ConstOption.CAP) {
                                    currentLevel = option.param;
                                } else if(option.optionTemplate.id == ConstOption.SUC_DANH_NGUYET_TU_TANG_PHAN_TRAM) {
                                    sdNguyetTu = option.param;
                                } else if(option.optionTemplate.id == ConstOption.HP_NGUYET_TU_TANG_PHAN_TRAM) {
                                    hpNguyetTu = option.param;
                                } else if(option.optionTemplate.id == ConstOption.KI_NGUYET_TU_TANG_PHAN_TRAM) {
                                    kiNguyetTu = option.param;
                                }
                            }
                            pet = item;
                        } else if(item.template.id == ConstItem.DA_THANH_TAY) {
                            daThanhTay = item;
                        } else if(item.template.id == ConstItem.DA_NGUYET_TU) {
                            daNguyetTu = item;
                        } else if(item.template.id == ConstItem.THOI_VANG_VIP) {
                            tvv = item;
                        }
                    }
                }
                // nâng cấp
                if(daNguyetTu != null) {
                    isNangCap = true;
                    isTay = false;
                }
                // tẩy
                else if(daThanhTay != null) {
                    isNangCap = false;
                    isTay = true;
                }
                // mặc định là nâng cấp
                else {
                    isNangCap = true;
                    isTay = false;
                }
                if (currentLevel >= maxLevel && isNangCap) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Pet đã đạt cấp độ tối đa", "Đóng");
                }
                else if (pet == null) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần có 1 pet", "Đóng");
                }
                else if (tvv == null || tvv.quantity < slTVV) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần có 1k TVV", "Đóng");
                } else if(isNangCap && (daNguyetTu == null || daNguyetTu.quantity < slDaNguyetTu)) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần có x3 Đá Nguyệt Tử", "Đóng");
                } else if(isTay && (daThanhTay == null || daThanhTay.quantity < slDaThanhTay)) {
                    this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                            "Con cần có x1 Đá Thanh Tẩy", "Đóng");
                } else {
                    // nâng cấp thì trừ đá nguyệt tử
                    if(isNangCap) {
                        InventoryService.gI().subQuantityItemsBag(player, daNguyetTu, slDaNguyetTu);
                    } else if (isTay) {
                        InventoryService.gI().subQuantityItemsBag(player, daThanhTay, slDaThanhTay);
                    }
                    // trừ 1k TVV
                    InventoryService.gI().subQuantityItemsBag(player, tvv, slTVV);
                    // 20% thành công
                    if (Util.isTrue(20, 100)) {
                        InventoryService.gI().subQuantityItemsBag(player, pet, 1);
                        // random chỉ số tăng
                        int rdOption = Util.nextInt(ConstOption.SUC_DANH_NGUYET_TU_TANG_PHAN_TRAM, ConstOption.KI_NGUYET_TU_TANG_PHAN_TRAM);
                        ItemOption newIo = null;
                        // pet chưa có chỉ số thì thêm mới
                        if((rdOption == ConstOption.SUC_DANH_NGUYET_TU_TANG_PHAN_TRAM && sdNguyetTu == -1) || (rdOption == ConstOption.HP_NGUYET_TU_TANG_PHAN_TRAM && hpNguyetTu == -1) || (rdOption == ConstOption.KI_NGUYET_TU_TANG_PHAN_TRAM && kiNguyetTu == -1)) {
                            newIo = new ItemOption(rdOption, chiSoCongThem);
                        }
                        // nâng cấp
                        if(isNangCap) {
                            // nếu chưa có cấp thì thêm option cấp
                            if(currentLevel == -1) {
                                pet.itemOptions.add(new ItemOption(ConstOption.CAP, 0));
                            }
                            if(newIo != null) {
                                pet.itemOptions.add(newIo);
                                // cộng cấp
                                for (ItemOption io : pet.itemOptions) {
                                    if(io.optionTemplate.id == ConstOption.CAP) {
                                        io.param += 1;
                                    }
                                }
                            }
                            // đã có chỉ số trước đó thì cộng thêm chỉ số
                            else {
                                for (ItemOption io : pet.itemOptions) {
                                    if(io.optionTemplate.id == rdOption) {
                                        io.param += chiSoCongThem;
                                    } else if(io.optionTemplate.id == ConstOption.CAP) {
                                        io.param += 1;
                                    }
                                }
                            }
                        } else if(isTay) {
                            if (currentLevel > -1) {
                                for (ItemOption io : pet.itemOptions) {
                                    if(io.optionTemplate.id == ConstOption.CAP) {
                                        io.param = 0;
                                    }
                                }
                                // xóa hết option nguyệt tử
                                pet.itemOptions.removeIf(item -> item.optionTemplate.id == ConstOption.SUC_DANH_NGUYET_TU_TANG_PHAN_TRAM || item.optionTemplate.id == ConstOption.HP_NGUYET_TU_TANG_PHAN_TRAM || item.optionTemplate.id == ConstOption.KI_NGUYET_TU_TANG_PHAN_TRAM);
                            }
                        }
                        InventoryService.gI().addItemBag(player, pet, 1);
                        sendEffectSuccessCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    } else {
                        sendEffectFailCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                }
            } else {
                this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.baHatMit.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn 1 pet, x3 Đá Nguyệt Tử, 1k TVV để nâng cấp\nHoặc 1 pet, x1 Đá Thanh Tẩy, 1k TVV để tẩy cấp", "Đóng");
        }
    }

    // ekko
    private void doiDaThanLinh(Player player) {
        if (player.combineNew.itemsCombine.size() == 1){
            if (InventoryService.gI().getCountEmptyBag(player) > 0) {
                for (Item item : player.combineNew.itemsCombine) {
                    if (item.isNotNullItem() && item.isItemThanLinh()) {
                        Item daThanLinh = ItemService.gI().createNewItem((short) ConstItem.DA_THAN_LINH, 50);
                        InventoryService.gI().addItemBag(player, daThanLinh, 0);
                        InventoryService.gI().subQuantityItemsBag(player, item, 1);
                        sendEffectSuccessCombine(player);
                        InventoryService.gI().sendItemBags(player);
                        Service.getInstance().sendMoney(player);
                        reOpenItemCombine(player);
                    }
                }
            } else {
                this.lyTieuNuong.createOtherMenu(player, ConstNpc.IGNORE_MENU,
                        "Hàng trang đã đầy", "Đóng");
            }
        } else {
            this.lyTieuNuong.createOtherMenu(player, ConstNpc.IGNORE_MENU, "Hãy chọn một đồ thần linh", "Đóng");
        }
    }

    // init option cho đồ skh
    public static List<ItemOption> initOptionSKH(int itemID) {
        List<ItemOption> lstOption = new ArrayList<>();
        double percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
        int optionOriginal = 0;
        int optionVal = 0;
        switch (itemID) {
            // áo trái đất
            case ConstItem.AO_VAI_3_LO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 2));
                break;
            case ConstItem.AO_THUN_3_LO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 4));
                break;
            case ConstItem.AO_VAI_DAY:
                lstOption.add(new ItemOption(ConstOption.GIAP, 8));
                break;
            case ConstItem.AO_THUN_DAY:
                lstOption.add(new ItemOption(ConstOption.GIAP, 16));
                break;
            case ConstItem.AO_VAI_KAME:
                lstOption.add(new ItemOption(ConstOption.GIAP, 24));
                break;
            case ConstItem.AO_THUN_KAME:
                lstOption.add(new ItemOption(ConstOption.GIAP, 40));
                break;
            case ConstItem.AO_VO_KAME:
                lstOption.add(new ItemOption(ConstOption.GIAP, 60));
                break;
            case ConstItem.AO_VO_GOKU:
                lstOption.add(new ItemOption(ConstOption.GIAP, 90));
                break;
            case ConstItem.AO_BAC_GOKU:
                lstOption.add(new ItemOption(ConstOption.GIAP, 200));
                break;
            case ConstItem.AO_VANG_GOKU:
                lstOption.add(new ItemOption(ConstOption.GIAP, 250));
                break;
            case ConstItem.AO_DA_CALIC:
                lstOption.add(new ItemOption(ConstOption.GIAP, 300));
                break;
            case ConstItem.AO_JEAN_CALIC:
                lstOption.add(new ItemOption(ConstOption.GIAP, 400));
                break;
            // quần trái đất
            case ConstItem.QUAN_VAI_DEN:
                lstOption.add(new ItemOption(ConstOption.HP, 30));
                break;
            case ConstItem.QUAN_THUN_DEN:
                lstOption.add(new ItemOption(ConstOption.HP, 150));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 12));
                break;
            case ConstItem.QUAN_VAI_DAY:
                lstOption.add(new ItemOption(ConstOption.HP, 300));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 40));
                break;
            case ConstItem.QUAN_THUN_DAY:
                lstOption.add(new ItemOption(ConstOption.HP, 600));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 120));
                break;
            case ConstItem.QUAN_VAI_KAME:
                lstOption.add(new ItemOption(ConstOption.HP, 1400));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 280));
                break;
            case ConstItem.QUAN_THUN_KAME:
                lstOption.add(new ItemOption(ConstOption.HP, 3000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 600));
                break;
            case ConstItem.QUAN_VO_KAME:
                lstOption.add(new ItemOption(ConstOption.HP, 6000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 1200));
                break;
            case ConstItem.QUAN_VO_GOKU:
                lstOption.add(new ItemOption(ConstOption.HP, 10000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2000));
                break;
            case ConstItem.QUAN_BAC_GOKU:
                lstOption.add(new ItemOption(ConstOption.HP, 14000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2500));
                break;
            case ConstItem.QUAN_VANG_GOKU:
                lstOption.add(new ItemOption(ConstOption.HP, 18000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 3000));
                break;
            case ConstItem.QUAN_DA_CALIC:
                lstOption.add(new ItemOption(ConstOption.HP, 22000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 3500));
                break;
            case ConstItem.QUAN_JEAN_CALIC:
                lstOption.add(new ItemOption(ConstOption.HP, 26000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 4000));
                break;
            // găng trái đất
            case ConstItem.GANG_VAI_DEN:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 4));
                break;
            case ConstItem.GANG_THUN_DEN:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 7));
                break;
            case ConstItem.GANG_VAI_DAY:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 14));
                break;
            case ConstItem.GANG_THUN_DAY:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 28));
                break;
            case ConstItem.GANG_VAI_KAME:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 55));
                break;
            case ConstItem.GANG_THUN_KAME:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 110));
                break;
            case ConstItem.GANG_VO_KAME:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 220));
                break;
            case ConstItem.GANG_VO_GOKU:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 530));
                break;
            case ConstItem.GANG_BAC_GOKU:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 680));
                break;
            case ConstItem.GANG_VANG_GOKU:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 1000));
                break;
            case ConstItem.GANG_DA_CALIC:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 1500));
                break;
            case ConstItem.GANG_JEAN_CALIC:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 2200));
                break;
            // giày trái đất
            case ConstItem.GIAY_NHUA:
                lstOption.add(new ItemOption(ConstOption.KI, 10));
                break;
            case ConstItem.GIAY_CAO_SU:
                lstOption.add(new ItemOption(ConstOption.KI, 25));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 5));
                break;
            case ConstItem.GIAY_NHUA_DE_DAY:
                lstOption.add(new ItemOption(ConstOption.KI, 120));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 24));
                break;
            case ConstItem.GIAY_CAO_SU_DE_DAY:
                lstOption.add(new ItemOption(ConstOption.KI, 250));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 50));
                break;
            case ConstItem.GIAY_NHUA_KAME:
                lstOption.add(new ItemOption(ConstOption.KI, 500));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 100));
                break;
            case ConstItem.GIAY_CAO_SU_KAME:
                lstOption.add(new ItemOption(ConstOption.KI, 1200));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 240));
                break;
            case ConstItem.GIAY_VO_KAME:
                lstOption.add(new ItemOption(ConstOption.KI, 2400));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 480));
                break;
            case ConstItem.GIAY_VO_GOKU:
                lstOption.add(new ItemOption(ConstOption.KI, 5000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1000));
                break;
            case ConstItem.GIAY_BAC_GOKU:
                lstOption.add(new ItemOption(ConstOption.KI, 9000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1500));
                break;
            case ConstItem.GIAY_VANG_GOKU:
                lstOption.add(new ItemOption(ConstOption.KI, 14000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2000));
                break;
            case ConstItem.GIAY_DA_CALIC:
                lstOption.add(new ItemOption(ConstOption.KI, 19000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2500));
                break;
            case ConstItem.GIAY_JEAN_CALIC:
                lstOption.add(new ItemOption(ConstOption.KI, 24000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 3000));
                break;
            // ra đa trái đất
            case ConstItem.RADA_CAP_1:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 1));
                break;
            case ConstItem.RADA_CAP_2:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 2));
                break;
            case ConstItem.RADA_CAP_3:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 3));
                break;
            case ConstItem.RADA_CAP_4:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 4));
                break;
            case ConstItem.RADA_CAP_5:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 5));
                break;
            case ConstItem.RADA_CAP_6:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 6));
                break;
            case ConstItem.RADA_CAP_7:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 7));
                break;
            case ConstItem.RADA_CAP_8:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 8));
                break;
            case ConstItem.RADA_CAP_9:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 9));
                break;
            case ConstItem.RADA_CAP_10:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 10));
                break;
            case ConstItem.RADA_CAP_11:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 11));
                break;
            case ConstItem.RADA_CAP_12:
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, 12));
                break;
            // áo namec
            case ConstItem.AO_SOI_LEN:
                lstOption.add(new ItemOption(ConstOption.GIAP, 2));
                break;
            case ConstItem.AO_SOI_GAI:
                lstOption.add(new ItemOption(ConstOption.GIAP, 4));
                break;
            case ConstItem.AO_LEN_PICO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 8));
                break;
            case ConstItem.AO_THUN_PICO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 16));
                break;
            case ConstItem.AO_CHOANG_LEN:
                lstOption.add(new ItemOption(ConstOption.GIAP, 24));
                break;
            case ConstItem.AO_CHOANG_THUN:
                lstOption.add(new ItemOption(ConstOption.GIAP, 40));
                break;
            case ConstItem.AO_VAI_PICO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 60));
                break;
            case ConstItem.AO_DA_PICO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 90));
                break;
            case ConstItem.AO_SAT_TRON:
                lstOption.add(new ItemOption(ConstOption.GIAP, 200));
                break;
            case ConstItem.AO_DONG_TRON:
                lstOption.add(new ItemOption(ConstOption.GIAP, 250));
                break;
            case ConstItem.AO_BAC_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.GIAP, 300));
                break;
            case ConstItem.AO_VANG_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.GIAP, 400));
                break;
            // quần namec
            case ConstItem.QUAN_SOI_LEN:
                lstOption.add(new ItemOption(ConstOption.HP, 20));
                break;
            case ConstItem.QUAN_SOI_GAI:
                lstOption.add(new ItemOption(ConstOption.HP, 25));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 10));
                break;
            case ConstItem.QUAN_VAI_THO_PICO:
                lstOption.add(new ItemOption(ConstOption.HP, 120));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 28));
                break;
            case ConstItem.QUAN_THUN_PICO:
                lstOption.add(new ItemOption(ConstOption.HP, 250));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 100));
                break;
            case ConstItem.QUAN_LEN_CUNG:
                lstOption.add(new ItemOption(ConstOption.HP, 600));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 240));
                break;
            case ConstItem.QUAN_THUN_CUNG:
                lstOption.add(new ItemOption(ConstOption.HP, 1200));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 480));
                break;
            case ConstItem.QUAN_VAI_CUNG_PICO:
                lstOption.add(new ItemOption(ConstOption.HP, 2400));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 960));
                break;
            case ConstItem.QUAN_VAI_MEM_PICO:
                lstOption.add(new ItemOption(ConstOption.HP, 4800));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 1800));
                break;
            case ConstItem.QUAN_SAT_TRON:
                lstOption.add(new ItemOption(ConstOption.HP, 13000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2200));
                break;
            case ConstItem.QUAN_DONG_TRON:
                lstOption.add(new ItemOption(ConstOption.HP, 17000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2700));
                break;
            case ConstItem.QUAN_BAC_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.HP, 21000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 32000));
                break;
            case ConstItem.QUAN_VANG_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.HP, 25000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 37000));
                break;
            // găng namec
            case ConstItem.GANG_SOI_LEN:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 3));
                break;
            case ConstItem.GANG_SOI_GAI:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 6));
                break;
            case ConstItem.GANG_LEN_PICO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 12));
                break;
            case ConstItem.GANG_THUN_PICO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 24));
                break;
            case ConstItem.GANG_LEN_CUNG:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 50));
                break;
            case ConstItem.GANG_THUN_CUNG:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 100));
                break;
            case ConstItem.GANG_VAI_PICO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 200));
                break;
            case ConstItem.GANG_DA_PICO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 500));
                break;
            case ConstItem.GANG_SAT_TRON:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 630));
                break;
            case ConstItem.GANG_DONG_TRON:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 950));
                break;
            case ConstItem.GANG_BAC_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 1450));
                break;
            case ConstItem.GANG_VANG_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 2150));
                break;
            // giày namec
            case ConstItem.GIAY_SOI_LEN:
                lstOption.add(new ItemOption(ConstOption.KI, 15));
                break;
            case ConstItem.GIAY_SOI_GAI:
                lstOption.add(new ItemOption(ConstOption.KI, 30));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 6));
                break;
            case ConstItem.GIAY_NHUA_PICO:
                lstOption.add(new ItemOption(ConstOption.KI, 150));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 30));
                break;
            case ConstItem.GIAY_CAO_SU_PICO:
                lstOption.add(new ItemOption(ConstOption.KI, 300));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 60));
                break;
            case ConstItem.GIAY_NHUA_CUNG:
                lstOption.add(new ItemOption(ConstOption.KI, 600));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 120));
                break;
            case ConstItem.GIAY_CAO_SU_CUNG:
                lstOption.add(new ItemOption(ConstOption.KI, 1500));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 300));
                break;
            case ConstItem.GIAY_DA_PICO:
                lstOption.add(new ItemOption(ConstOption.KI, 3000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 600));
                break;
            case ConstItem.GIAY_SAT_PICO:
                lstOption.add(new ItemOption(ConstOption.KI, 6000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1200));
                break;
            case ConstItem.GIAY_SAT_TRON:
                lstOption.add(new ItemOption(ConstOption.KI, 10000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1700));
                break;
            case ConstItem.GIAY_DONG_TRON:
                lstOption.add(new ItemOption(ConstOption.KI, 15000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2200));
                break;
            case ConstItem.GIAY_BAC_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.KI, 20000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2700));
                break;
            case ConstItem.GIAY_VANG_ZEALOT:
                lstOption.add(new ItemOption(ConstOption.KI, 25000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 3200));
                break;
                // áo xayda
            case ConstItem.AO_VAI_THO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 3));
                break;
            case ConstItem.AO_THUN_THO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 5));
                break;
            case ConstItem.AO_GIAP_SAT:
                lstOption.add(new ItemOption(ConstOption.GIAP, 10));
                break;
            case ConstItem.AO_GIAP_DONG:
                lstOption.add(new ItemOption(ConstOption.GIAP, 20));
                break;
            case ConstItem.AO_GIAP_BAC:
                lstOption.add(new ItemOption(ConstOption.GIAP, 30));
                break;
            case ConstItem.AO_GIAP_VANG:
                lstOption.add(new ItemOption(ConstOption.GIAP, 50));
                break;
            case ConstItem.AO_LONG_XAYDA:
                lstOption.add(new ItemOption(ConstOption.GIAP, 70));
                break;
            case ConstItem.AO_KHOAC_XAYDA:
                lstOption.add(new ItemOption(ConstOption.GIAP, 100));
                break;
            case ConstItem.AO_LONG_DO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 230));
                break;
            case ConstItem.AO_SIEU_XAYDA:
                lstOption.add(new ItemOption(ConstOption.GIAP, 280));
                break;
            case ConstItem.AO_KAIO:
                lstOption.add(new ItemOption(ConstOption.GIAP, 330));
                break;
            case ConstItem.AO_LUONG_LONG:
                lstOption.add(new ItemOption(ConstOption.GIAP, 450));
                break;
                // quần xayda
            case ConstItem.QUAN_VAI_THO:
                lstOption.add(new ItemOption(ConstOption.HP, 20));
                break;
            case ConstItem.QUAN_THUN_THO:
                lstOption.add(new ItemOption(ConstOption.HP, 20));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 8));
                break;
            case ConstItem.QUAN_GIAP_SAT:
                lstOption.add(new ItemOption(ConstOption.HP, 100));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 20));
                break;
            case ConstItem.QUAN_GIAP_DONG:
                lstOption.add(new ItemOption(ConstOption.HP, 200));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 80));
                break;
            case ConstItem.QUAN_GIAP_BAC:
                lstOption.add(new ItemOption(ConstOption.HP, 500));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 200));
                break;
            case ConstItem.QUAN_GIAP_VANG:
                lstOption.add(new ItemOption(ConstOption.HP, 1000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 400));
                break;
            case ConstItem.QUAN_LONG_XAYDA:
                lstOption.add(new ItemOption(ConstOption.HP, 2000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 800));
                break;
            case ConstItem.QUAN_DA_XAYDA:
                lstOption.add(new ItemOption(ConstOption.HP, 4000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 1600));
                break;
            case ConstItem.QUAN_LONG_DO:
                lstOption.add(new ItemOption(ConstOption.HP, 12000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2100));
                break;
            case ConstItem.QUAN_SIEU_XAYDA:
                lstOption.add(new ItemOption(ConstOption.HP, 16000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 2600));
                break;
            case ConstItem.QUAN_KAIO:
                lstOption.add(new ItemOption(ConstOption.HP, 20000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 3100));
                break;
            case ConstItem.QUAN_LUONG_LONG:
                lstOption.add(new ItemOption(ConstOption.HP, 24000));
                lstOption.add(new ItemOption(ConstOption.CONG_HP_TREN_30_GIAY, 3600));
                break;
                // găng xayda
            case ConstItem.GANG_VAI_THO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 5));
                break;
            case ConstItem.GANG_THUN_THO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 8));
                break;
            case ConstItem.GANG_SAT:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 16));
                break;
            case ConstItem.GANG_DONG:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 32));
                break;
            case ConstItem.GANG_BAC:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 60));
                break;
            case ConstItem.GANG_VANG:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 120));
                break;
            case ConstItem.GANG_LONG_XAYDA:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 240));
                break;
            case ConstItem.GANG_DA_XAYDA:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 560));
                break;
            case ConstItem.GANG_LONG_DO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 700));
                break;
            case ConstItem.GANG_SIEU_XAYDA:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 1050));
                break;
            case ConstItem.GANG_KAIO:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 1550));
                break;
            case ConstItem.GANG_LUONG_LONG:
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, 2250));
                break;
                // giày xayda
            case ConstItem.GIAY_VAI_THO:
                lstOption.add(new ItemOption(ConstOption.KI, 10));
                break;
            case ConstItem.GIAY_CAO_SU_THO:
                lstOption.add(new ItemOption(ConstOption.KI, 20));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 4));
                break;
            case ConstItem.GIAY_SAT:
                lstOption.add(new ItemOption(ConstOption.KI, 100));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 20));
                break;
            case ConstItem.GIAY_DONG:
                lstOption.add(new ItemOption(ConstOption.KI, 200));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 40));
                break;
            case ConstItem.GIAY_BAC:
                lstOption.add(new ItemOption(ConstOption.KI, 400));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 80));
                break;
            case ConstItem.GIAY_VANG:
                lstOption.add(new ItemOption(ConstOption.KI, 1000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 200));
                break;
            case ConstItem.GIAY_LONG_XAYDA:
                lstOption.add(new ItemOption(ConstOption.KI, 2000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 400));
                break;
            case ConstItem.GIAY_DA_XAYDA:
                lstOption.add(new ItemOption(ConstOption.KI, 4000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 800));
                break;
            case ConstItem.GIAY_LONG_DO:
                lstOption.add(new ItemOption(ConstOption.KI, 8000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1300));
                break;
            case ConstItem.GIAY_SIEU_XAYDA:
                lstOption.add(new ItemOption(ConstOption.KI, 13000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 1800));
                break;
            case ConstItem.GIAY_KAIO:
                lstOption.add(new ItemOption(ConstOption.KI, 18000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2300));
                break;
            case ConstItem.GIAY_LUONG_LONG:
                lstOption.add(new ItemOption(ConstOption.KI, 23000));
                lstOption.add(new ItemOption(ConstOption.CONG_KI_TREN_30_GIAY, 2800));
                break;
            case ConstItem.AO_THAN_LINH:
            case ConstItem.AO_THAN_NAMEC:
            case ConstItem.AO_THAN_XAYDA:
                // random 1 -> 15% chỉ số 250 giáp
                percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
                optionOriginal = 250;
                optionVal = (int) (optionOriginal * percentage);
                lstOption.add(new ItemOption(ConstOption.GIAP, optionVal));
                break;
            case ConstItem.QUAN_THAN_LINH:
            case ConstItem.QUAN_THAN_NAMEC:
            case ConstItem.QUAN_THAN_XAYDA:
                // random 1 -> 15% chỉ số 50k hp
                percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
                optionOriginal = 50_000;
                optionVal = (int) (optionOriginal * percentage);
                lstOption.add(new ItemOption(ConstOption.GIAP, optionVal));
                break;
            case ConstItem.GANG_THAN_LINH:
            case ConstItem.GANG_THAN_NAMEC:
            case ConstItem.GANG_THAN_XAYDA:
                // random 1 -> 15% chỉ số tấn công 5k
                percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
                optionOriginal = 5000;
                optionVal = (int) (optionOriginal * percentage);
                lstOption.add(new ItemOption(ConstOption.TAN_CONG, optionVal));
                break;
            case ConstItem.GIAY_THAN_LINH:
            case ConstItem.GIAY_THAN_NAMEC:
            case ConstItem.GIAY_THAN_XAYDA:
                // random 1 -> 15% chỉ số 50k ki
                percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
                optionOriginal = 50_000;
                optionVal = (int) (optionOriginal * percentage);
                lstOption.add(new ItemOption(ConstOption.KI, optionVal));
                break;
            case ConstItem.NHAN_THAN_LINH:
                // random 1 -> 15% chỉ số 12% chí mạng
                percentage = Util.nextInt(1, 15) / 100.0; // Từ 1% đến 15%
                optionOriginal = 12;
                optionVal = (int) (optionOriginal * percentage);
                lstOption.add(new ItemOption(ConstOption.CHI_MANG_PHAN_TRAM, optionVal));
                break;
            default:
                break;
        }
        return lstOption;
    }

}
