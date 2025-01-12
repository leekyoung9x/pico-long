package nro.services;

import nro.consts.*;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.func.CombineServiceNew;
import nro.utils.Util;

import java.util.ArrayList;

public class ExcellentBook {

    private static ExcellentBook i;

    public static ExcellentBook gI() {
        if (i == null) {
            i = new ExcellentBook();
        }
        return i;
    }

    public static void handleExcellentBook(Player player, int select) {
        try {
            switch (select) {
                // Đóng thành sách cũ
                case 0: {
                    if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                        Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                        return;
                    }

                    int quantityOldBook = 0, quantityPaperStapler = 0;

                    Item oldBook = InventoryService.gI().findItem(player, 2082, 1);
                    Item book = InventoryService.gI().findItem(player, 2068, 1);

                    if (oldBook != null) {
                        quantityOldBook = oldBook.quantity;
                    }

                    if (book != null) {
                        quantityPaperStapler = book.quantity;
                    }

                    NpcService.gI().createOtherMenu(player, ConstNpc.BA_HAT_MIT, ConstNpc.INDEX_MENU_CHANGE_OLD_BOOK,
                            "|" + ConstTextColor.GREEN_BOLD + "| Chế tạo cuốn sách cũ\n|" + ConstTextColor.BLUE + "| Trang sách cũ " + quantityOldBook
                            + "/1000\n|" + ConstTextColor.BLUE + "|Bìa sách " + quantityPaperStapler + "/1\n|" + ConstTextColor.BLUE + "|Hồng ngọc: "
                            + 5000,
                            "Đồng ý", "Từ chối");
                }
                break;
                // Đổi sách tuyệt kỹ
                case 1: {
                    if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                        Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                        return;
                    }
                    if (player.inventory.gem < 10_000) {
                        Service.getInstance().sendThongBao(player, "Bạn thiếu 10k ngọc xanh okerr");
                        return;
                    }
                    int quantityOldBook = 0, quantityPaperStapler = 0;
                    Item oldBook = InventoryService.gI().findItem(player, 2081, 1);
                    Item paperStapler = InventoryService.gI().findItem(player, 2069, 1);

                    if (oldBook != null) {
                        quantityOldBook = oldBook.quantity;
                    }

                    if (paperStapler != null) {
                        quantityPaperStapler = paperStapler.quantity;
                    }

                    NpcService.gI().createOtherMenu(player, ConstNpc.BA_HAT_MIT, ConstNpc.INDEX_MENU_CLOSE_OLD_BOOK,
                            "|" + ConstTextColor.GREEN_BOLD + "|Đổi sách Tuyệt Kỹ 1\n|" + ConstTextColor.BLUE + "|Cuốn sách cũ " + quantityOldBook + "/10"
                            + "\n|" + ConstTextColor.BLUE + "|Kím bấm giấy " + quantityPaperStapler + "/1"
                            + "\n|" + ConstTextColor.BLUE + "|10k ngọc xanh"
                            + "\n|" + ConstTextColor.BLUE + "|Tỉ lệ thành công: 20%",
                            "Đồng ý", "Từ chối");
                }
                break;
                // Giám định sách
                case 2:
                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.GIAM_DINH);
                    break;
                // Tẩy sách
                case 3:
                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.TAY_SACH);
                    break;
                // Nâng cấp sách tuyệt kỹ
                case 4:
                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_SACH);
                    break;
                // Hồi phục sách
                case 5:
                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHUC_HOI);
                    break;
                // Phân rã sách
                case 6:
                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.PHAN_RA);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeOldBook(Player player, int select) {
        try {
            switch (select) {
                // Đồng ý
                case 0:
                    int quantityBook = 10,
                     quantityPaper = 1;

                    Item oldBook = InventoryService.gI().findItem(player, 2081, quantityBook);
                    Item paperStapler = InventoryService.gI().findItem(player, 2069, quantityPaper);

                    if (oldBook == null) {
                        Service.getInstance().sendThongBao(player, "Cháu không có đủ " + quantityBook + " Cuốn sách cũ");
                        return;
                    }

                    if (paperStapler == null) {
                        Service.getInstance().sendThongBao(player, "Cháu không có đủ " + quantityPaper + " Kìm bấm giấy");
                        return;
                    }

                    if (player.inventory.getGem() < 10_000) {
                        Service.getInstance().sendThongBao(player, "Cháu không có đủ 10k ngọc xanh");
                        return;
                    }

                    InventoryService.gI().subQuantityItemsBag(player, oldBook, 10);
                    InventoryService.gI().subQuantityItemsBag(player, paperStapler, 1);
                    player.inventory.subGem(10_000);

                    if (Util.isTrue(20, 100)) {
                        createEffectFusion(player, true);
                        short id = 0;

                        switch (player.gender) {
                            case ConstPlayer.TRAI_DAT ->
                                id = 2071;
                            case ConstPlayer.NAMEC ->
                                id = 2072;
                            case ConstPlayer.XAYDA ->
                                id = 2073;
                            default ->
                                id = 0;
                        }
                        Item it = ItemService.gI().createNewItem(id);

                        it.itemOptions.add(new ItemOption(236, 0));
                        it.itemOptions.add(new ItemOption(21, 40));
                        it.itemOptions.add(new ItemOption(30, 0));
                        it.itemOptions.add(new ItemOption(87, 0));
                        it.itemOptions.add(new ItemOption(237, 5));
                        it.itemOptions.add(new ItemOption(238, 1000));

                        InventoryService.gI().addItemBag(player, it, 1);
                        Service.getInstance().sendThongBao(player, "Bạn vừa nhận được " + it.template.name);
                    } else {
                        createEffectFusion(player, false);
                    }
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void healBook(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            int ruby = player.combineNew.gemCombine;
            if (player.inventory.ruby < ruby) {
                Service.getInstance().sendThongBao(player, "Không đủ hồng ngọc để thực hiện");
                return;
            }

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
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-ruby);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "ExcellentBook-healBook");
                for (ItemOption io : sachtk.itemOptions) {
                    if (io.optionTemplate.id == 238) {
                        level_238 = io.param;
                        optionLevel_238 = io;
                        break;
                    }
                }

                if (optionLevel_238 != null && level_238 < 1000) {
                    optionLevel_238.param += 1000 - level_238;
                }
                CombineServiceNew.gI().sendEffectSuccessCombine(player);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                CombineServiceNew.gI().reOpenItemCombine(player);
            }
        }
    }

    public void easerBook(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            if (player.inventory.ruby < 5000) {
                Service.getInstance().sendThongBao(player, "Không đủ hồng ngọc để thực hiện");
                return;
            }
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
            ItemOption option = null;
            ItemOption option2 = null;
            ItemOption option3 = null;
            ItemOption option4 = null;
            ItemOption option5 = null;
            ItemOption option6 = null;
            if (sachtk != null) {
                player.inventory.addGold(-5000);
                for (ItemOption io : sachtk.itemOptions) {
                    if (io.optionTemplate.id == 237) {
                        level_237 = io.param;
                        optionLevel_237 = io;
                        break;
                    }
                }
                for (ItemOption io : sachtk.itemOptions) {
                    if (io.optionTemplate.id == 236) {
                        level_236 = io.param;
                        optionLevel_236 = io;
                        break;
                    }
                }
                for (ItemOption io : sachtk.itemOptions) {
                    if (io.optionTemplate.id == 50) {
                        option = io;
                    }
                    if (io.optionTemplate.id == 77) {
                        option2 = io;
                    }
                    if (io.optionTemplate.id == 103) {
                        option3 = io;
                    }
                    if (io.optionTemplate.id == 108) {
                        option4 = io;
                    }
                    if (io.optionTemplate.id == 94) {
                        option5 = io;
                    }
                    if (io.optionTemplate.id == 14) {
                        option6 = io;
                    }
                }

                if (optionLevel_236 == null && optionLevel_237 != null && level_237 > 0) {
                    optionLevel_237.param -= 1;
                    sachtk.itemOptions.add(0, new ItemOption(236, 0));
                    sachtk.itemOptions.remove(option);
                    sachtk.itemOptions.remove(option2);
                    sachtk.itemOptions.remove(option3);
                    sachtk.itemOptions.remove(option4);
                    sachtk.itemOptions.remove(option5);
                    sachtk.itemOptions.remove(option6);
                }
                CombineServiceNew.gI().sendEffectSuccessCombine(player);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                CombineServiceNew.gI().reOpenItemCombine(player);
            }
        }
    }

    public static void changeOldBook(Player player, int select) {
        try {
            switch (select) {
                case 0:
                    int giavang = 5000;

                    Item oldPaperBook = InventoryService.gI().findItem(player, 2082, 1000);
                    Item book = InventoryService.gI().findItem(player, 2068, 1);

                    if (InventoryService.gI().getCountEmptyBag(player) == 0) {
                        Service.getInstance().sendThongBao(player, "Hành trang của bạn không đủ chỗ trống");
                    } else if (book != null && book.quantity >= 1
                            && oldPaperBook != null && oldPaperBook.quantity >= 1000
                            && player.inventory.ruby >= giavang) {
                        int oldRuby = player.inventory.ruby;
                        player.inventory.addRuby(-giavang);
                        // ekko ghi log add ruby
                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "ExcellentBook-changeOldBook");
                        Item oldBook = ItemService.gI().createNewItem((short) 2081);
                        oldBook.itemOptions.add(new ItemOption(30, 0));
                        InventoryService.gI().addItemBag(player, oldBook, 9999);
                        InventoryService.gI().subQuantityItemsBag(player, oldPaperBook, 1000);
                        InventoryService.gI().subQuantityItemsBag(player, book, 1);

                        Service.getInstance().sendThongBao(player, "Bạn vừa nhận được " + oldBook.template.name);
                    } else {
                        Service.getInstance().sendThongBao(player, "Không Đủ điều kiện để đổi vật phẩm");
                    }

                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upgradeBook(Player player) {
        if (player.combineNew.itemsCombine.size() == 2) {
            int ruby = player.combineNew.gemCombine;
            if (player.inventory.gem < ruby) {
                Service.getInstance().sendThongBao(player, "Không đủ ngọc để thực hiện");
                return;
            }
            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
            if (pi == null || pi.quantity < 10_000) {
                Service.getInstance().sendThongBao(player, "Không đủ 10k " + ConstItemName.MONEY_UNIT + " để thực hiện");
                return;
            }
            Item sachtk = null;
            Item kimkep = null;
            for (Item item : player.combineNew.itemsCombine) {
                if (item.template.id >= 2071 && item.template.id <= 2073) {
                    sachtk = item;
                } else if (item.template.id == 2069) {
                    kimkep = item;
                }
            }
            if (sachtk != null && kimkep != null && kimkep.quantity >= 10) {
                player.inventory.addGem(-ruby);
                ItemOption option = null;
                ItemOption option2 = null;
                ItemOption option3 = null;
                ItemOption option4 = null;
                ItemOption option5 = null;
                ItemOption option6 = null;
                ItemOption optionLevel_238 = null;
                InventoryService.gI().subQuantityItemsBag(player, kimkep, 10);
                InventoryService.gI().subQuantityItemsBag(player, pi, 10_000);
                if (Util.isTrue(20, 100)) {
                    for (ItemOption io : sachtk.itemOptions) {
                        if (io.optionTemplate.id == 238) {
                            optionLevel_238 = io;
                            break;
                        }
                    }
                    for (ItemOption io : sachtk.itemOptions) {
                        if (io.optionTemplate.id == 50) {
                            option = io;
                        }
                        if (io.optionTemplate.id == 77) {
                            option2 = io;
                        }
                        if (io.optionTemplate.id == 103) {
                            option3 = io;
                        }
                        if (io.optionTemplate.id == 108) {
                            option4 = io;
                        }
                        if (io.optionTemplate.id == 94) {
                            option5 = io;
                        }
                        if (io.optionTemplate.id == 14) {
                            option6 = io;
                        }
                    }
                    if (optionLevel_238 != null) {
                        optionLevel_238.param += 1;
                    }
                    if (option != null) {
                        option.param += 3;
                    }
                    if (option2 != null) {
                        option2.param += 5;
                    }
                    if (option3 != null) {
                        option3.param += 5;
                    }
                    if (option4 != null) {
                        option4.param += 5;
                    }
                    if (option5 != null) {
                        option5.param += 3;
                    }
                    if (option6 != null) {
                        option6.param += 5;
                    }
                    sachtk.template = ItemService.gI().getTemplate(2075 + player.gender);
                    CombineServiceNew.gI().sendEffectSuccessCombine(player);
                } else {
                    CombineServiceNew.gI().sendEffectFailCombine(player);
                }
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                CombineServiceNew.gI().reOpenItemCombine(player);
            }
        }
    }

    public void appraisal(Player player) {
        try {
            if (player.combineNew.itemsCombine.size() == 2) {
                int ruby = 10_000;
                if (player.inventory.ruby < ruby) {
                    Service.getInstance().sendThongBao(player, "Không đủ hồng ngọc để thực hiện");
                    return;
                }
                Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                if (pi == null || pi.quantity < 10_000) {
                    Service.getInstance().sendThongBao(player, "Không đủ 10k " + ConstItemName.MONEY_UNIT + " để thực hiện");
                    return;
                }
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
                int level = 0;
                ItemOption optionLevel = null;
                if (sachtk != null && buagiamdinh != null && buagiamdinh.quantity >= 1) {
                    int oldRuby = player.inventory.ruby;
                    player.inventory.addRuby(-ruby);
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "ExcellentBook-appraisal");
                    InventoryService.gI().subQuantityItemsBag(player, buagiamdinh, 1);
                    InventoryService.gI().subQuantityItemsBag(player, pi, 10_000);
                    for (ItemOption io : sachtk.itemOptions) {
                        if (io.optionTemplate.id == 236) {
                            level = io.param;
                            optionLevel = io;
                            break;
                        }
                        if (io.optionTemplate.id == 237) {
                            io.param++;
                            break;
                        }
                    }
                    if (optionLevel != null) {
                        sachtk.itemOptions.remove(0);
                        if (Util.isTrue(100, 100)) {
                            int rdUp = Util.nextInt(0, 5);
                            int rdUp2 = Util.nextInt(0, 5);
                            int rdUp3 = Util.nextInt(0, 5);
                            switch (rdUp) {
                                case 0:
                                    sachtk.itemOptions.add(0, new ItemOption(50, Util.nextInt(1, 20)));
                                    break;
                                case 1:
                                    sachtk.itemOptions.add(0, new ItemOption(77, Util.nextInt(1, 20)));
                                    break;
                                case 2:
                                    sachtk.itemOptions.add(0, new ItemOption(103, Util.nextInt(1, 20)));
                                    break;
                                case 3:
                                    sachtk.itemOptions.add(0, new ItemOption(108, Util.nextInt(1, 20)));
                                    break;
                                case 4:
                                    sachtk.itemOptions.add(0, new ItemOption(94, Util.nextInt(1, 20)));
                                    break;
                                case 5:
                                    sachtk.itemOptions.add(0, new ItemOption(14, Util.nextInt(1, 20)));
                                    break;
                                default:
                                    break;
                            }
                            if (Util.isTrue(50, 100) && rdUp != rdUp2) {
                                switch (rdUp2) {
                                    case 0:
                                        sachtk.itemOptions.add(0, new ItemOption(50, Util.nextInt(1, 20)));
                                        break;
                                    case 1:
                                        sachtk.itemOptions.add(0, new ItemOption(77, Util.nextInt(1, 20)));
                                        break;
                                    case 2:
                                        sachtk.itemOptions.add(0, new ItemOption(103, Util.nextInt(1, 20)));
                                        break;
                                    case 3:
                                        sachtk.itemOptions.add(0, new ItemOption(108, Util.nextInt(1, 20)));
                                        break;
                                    case 4:
                                        sachtk.itemOptions.add(0, new ItemOption(94, Util.nextInt(1, 20)));
                                        break;
                                    case 5:
                                        sachtk.itemOptions.add(0, new ItemOption(14, Util.nextInt(1, 20)));
                                        break;
                                    default:
                                        break;
                                }

                            }
                            if (Util.isTrue(50, 100) && rdUp != rdUp2 && rdUp2 != rdUp3 && rdUp != rdUp3) {
                                switch (rdUp3) {
                                    case 0:
                                        sachtk.itemOptions.add(0, new ItemOption(50, Util.nextInt(1, 20)));
                                        break;
                                    case 1:
                                        sachtk.itemOptions.add(0, new ItemOption(77, Util.nextInt(1, 20)));
                                        break;
                                    case 2:
                                        sachtk.itemOptions.add(0, new ItemOption(103, Util.nextInt(1, 20)));
                                        break;
                                    case 3:
                                        sachtk.itemOptions.add(0, new ItemOption(108, Util.nextInt(1, 20)));
                                        break;
                                    case 4:
                                        sachtk.itemOptions.add(0, new ItemOption(94, Util.nextInt(1, 20)));
                                        break;
                                    case 5:
                                        sachtk.itemOptions.add(0, new ItemOption(14, Util.nextInt(1, 20)));
                                        break;
                                    default:
                                        break;
                                }

                            }
                        }
                    }
                    CombineServiceNew.gI().sendEffectSuccessCombine(player);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendMoney(player);
                    CombineServiceNew.gI().reOpenItemCombine(player);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void distributeBook(Player player) {
        if (player.combineNew.itemsCombine.size() == 1) {
            int ruby = player.combineNew.gemCombine;
            if (player.inventory.ruby < ruby) {
                Service.getInstance().sendThongBao(player, "Không đủ hồng ngọc để thực hiện");
                return;
            }
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
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-ruby);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "ExcellentBook-distributeBook");
                sachtk.template = ItemService.gI().getTemplate(2081);
                sachtk.quantity = 5;
                sachtk.itemOptions.clear();
                sachtk.itemOptions.add(new ItemOption(73, 0));
                CombineServiceNew.gI().sendEffectSuccessCombine(player);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                CombineServiceNew.gI().reOpenItemCombine(player);

            } else if (sachtk2 != null && sachtk == null) {
                int oldRuby = player.inventory.ruby;
                player.inventory.addRuby(-ruby);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "ExcellentBook-distributeBook");
                sachtk2.template = ItemService.gI().getTemplate(2081);
                sachtk2.quantity = 10;
                sachtk2.itemOptions.clear();
                sachtk2.itemOptions.add(new ItemOption(73, 0));
                CombineServiceNew.gI().sendEffectSuccessCombine(player);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendMoney(player);
                CombineServiceNew.gI().reOpenItemCombine(player);
            }
        }
    }

    private static void createEffectFusion(Player player, boolean isSuccess) {
        try {
            Item oldBook = InventoryService.gI().findItem(player, 2081, 10);
            Item paperStapler = InventoryService.gI().findItem(player, 2069, 1);
            player.combineNew.itemsCombine = null;
            player.combineNew.itemsCombine = new ArrayList<>();
            player.combineNew.itemsCombine.add(oldBook);
            player.combineNew.itemsCombine.add(paperStapler);
            CombineServiceNew.gI().reOpenItemCombine(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.CHE_TAO_DO_THIEN_SU);
        if (isSuccess) {
            CombineServiceNew.gI().sendEffectSuccessCombineDoTS(player, (short) 1103);
        } else {
            CombineServiceNew.gI().sendEffectFailCombineDoTS(player);
        }
    }
}
