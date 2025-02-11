package nro.services;

import nro.consts.ConstItem;
import nro.consts.ConstOption;
import nro.consts.ConstOptionSelect;
import nro.models.item.ItemOptionTemplate;
import nro.models.item.ItemTemplate;
import nro.models.kygui.ConsignmentItem;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.mob.ArrietyDrop;
import nro.models.shop.ItemShop;
import nro.server.Manager;
import nro.utils.TimeUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import nro.models.player.Player;
import nro.utils.Util;

/**
 * @Build by Arriety
 */
public class ItemService {

    private static ItemService i;

    public static ItemService gI() {
        if (i == null) {
            i = new ItemService();
        }
        return i;
    }

    public Item createItemNull() {
        Item item = new Item();
        return item;
    }

    public Item createItemFromItemShop(ItemShop itemShop) {
        Item item = new Item();
        item.template = itemShop.temp;
        item.quantity = 1;
        item.content = item.getContent();
        item.info = item.getInfo();
        for (ItemOption io : itemShop.options) {
            item.itemOptions.add(new ItemOption(io));
        }
        return item;
    }

    public Item copyItem(Item item) {
        Item it = new Item();
        it.itemOptions = new ArrayList<>();
        it.template = item.template;
        it.info = item.info;
        it.content = item.content;
        it.quantity = item.quantity;
        it.createTime = item.createTime;
        for (ItemOption io : item.itemOptions) {
            it.itemOptions.add(new ItemOption(io));
        }
        return it;
    }

    public Item otpKH(short tempId) {
        return otpKH(tempId, 1);
    }

    public Item otpKHHD(short tempId) {
        return otpKHHD(tempId, 1);
    }

    public Item otpKH(short tempId, int start, int end) {
        return otpKH(tempId, 1, start, end);
    }

    public Item Disguise() {
        Item it = ItemService.gI().createNewItem((short) 742);
        it.itemOptions.add(new ItemOption(50, 80));
        it.itemOptions.add(new ItemOption(77, 80));
        it.itemOptions.add(new ItemOption(103, 80));
        it.itemOptions.add(new ItemOption(228, 0));
        it.itemOptions.add(new ItemOption(230, 0));
        return it;
    }

    public Item otpHD(short tempId, int quantity) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();
        switch (item.template.type) {
            case 0:// ao
                item.itemOptions.add(new ItemOption(47, Util.highlightsItem(true, new Random().nextInt(1001) + 5000))); // áo từ 1800-2800 giáp
                break;
            case 1://quan
                item.itemOptions.add(new ItemOption(22, Util.highlightsItem(true, new Random().nextInt(16) + 130))); // hp 85-100k
                break;
            case 2:// gang
                item.itemOptions.add(new ItemOption(0, Util.highlightsItem(true, new Random().nextInt(150) + 14000))); // 8500-10000
                break;
            case 3:// giay
                item.itemOptions.add(new ItemOption(23, Util.highlightsItem(true, new Random().nextInt(11) + 130))); // ki 80-90k
                break;
            case 4:
                item.itemOptions.add(new ItemOption(14, new Random().nextInt(3) + 20)); //chí mạng 17-19%
                break;
            default:
                break;
        }
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public Item otpKH(short tempId, int quantity) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();
        switch (item.template.type) {
            case 0:
                item.itemOptions.add(new ItemOption(47, Util.nextInt(1, 4)));
                break;
            case 1:
                item.itemOptions.add(new ItemOption(6, Util.nextInt(150, 200)));
                break;
            case 2:
                item.itemOptions.add(new ItemOption(0, Util.nextInt(7, 29)));
                break;
            case 3:
                item.itemOptions.add(new ItemOption(7, Util.nextInt(2, 12)));
                break;
            case 4:
                item.itemOptions.add(new ItemOption(14, 1));
                break;
            default:
                break;
        }
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public Item otpKHHD(short tempId, int quantity) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();
        switch (item.template.type) {
            case 0:
                item.itemOptions.add(new ItemOption(47, 1800));
                break;
            case 1:
                item.itemOptions.add(new ItemOption(6, 90000));
                break;
            case 2:
                item.itemOptions.add(new ItemOption(0, 9000));
                break;
            case 3:
                item.itemOptions.add(new ItemOption(7, 100000));
                break;
            case 4:
                item.itemOptions.add(new ItemOption(14, 16));
                break;
            default:
                break;
        }
        // không thể GD
        item.itemOptions.add(new ItemOption(30, 0));
        // SKH ẩn
        item.itemOptions.add(new ItemOption(249, 0));
        // 9 SPL chưa ép
        item.itemOptions.add(new ItemOption(107, 9));
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public Item otpKH(short tempId, int quantity, int start, int end) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();
        switch (item.template.type) {
            case 0:
                item.itemOptions.add(new ItemOption(47, Util.nextInt(start, end)));
                break;
            case 1:
                item.itemOptions.add(new ItemOption(6, Util.nextInt(start, end)));
                break;
            case 2:
                item.itemOptions.add(new ItemOption(0, Util.nextInt(start, end)));
                break;
            case 3:
                item.itemOptions.add(new ItemOption(7, Util.nextInt(start, end)));
                break;
            case 4:
                item.itemOptions.add(new ItemOption(14, Util.nextInt(start, end)));
                break;
            default:
                break;
        }
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public void setThanLinh(Player player, int gender) {
        try {
            if (gender <= 2) {
                Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SET_THAN_LINH);

                int[] itemTLs = ArrietyDrop.list_do_than_linh[gender];

                Item ao = ItemService.gI().otpKH((short) itemTLs[0], 250, 350);
                Item quan = ItemService.gI().otpKH((short) itemTLs[1], 45_000, 60_000);
                Item gang = ItemService.gI().otpKH((short) itemTLs[2], 4500, 5500);
                Item giay = ItemService.gI().otpKH((short) itemTLs[3], 45_000, 60_000);
                Item rd = ItemService.gI().otpKH((short) itemTLs[4], 15, 18);
                ao.itemOptions.add(new ItemOption(30, 0));
                quan.itemOptions.add(new ItemOption(30, 0));
                gang.itemOptions.add(new ItemOption(30, 0));
                giay.itemOptions.add(new ItemOption(30, 0));
                rd.itemOptions.add(new ItemOption(30, 0));
                if (InventoryService.gI().getCountEmptyBag(player) > 4) {
                    InventoryService.gI().addItemBag(player, ao, 0);
                    InventoryService.gI().addItemBag(player, quan, 0);
                    InventoryService.gI().addItemBag(player, gang, 0);
                    InventoryService.gI().addItemBag(player, giay, 0);
                    InventoryService.gI().addItemBag(player, rd, 0);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Thần Linh");
                    InventoryService.gI().subQuantityItemsBag(player, hq, 1);
                    InventoryService.gI().sendItemBags(player);
                } else {
                    Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSongoku(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_QUA_THUONG);
        Item ao = ItemService.gI().otpKH((short) 0);
        Item quan = ItemService.gI().otpKH((short) 6);
        Item gang = ItemService.gI().otpKH((short) 21);
        Item giay = ItemService.gI().otpKH((short) 27);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_SONGOKU, 0));//129
        quan.itemOptions.add(new ItemOption(ConstOption.SET_SONGOKU, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_SONGOKU, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_SONGOKU, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_SONGOKU, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KAMEJOKO, 100));
        quan.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KAMEJOKO, 100));
        gang.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KAMEJOKO, 100));
        giay.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KAMEJOKO, 100));
        rd.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KAMEJOKO, 100));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Songoku");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setTNSMTD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_QUA_SET_TNSM);
        Item ao = ItemService.gI().otpKH((short) 0);
        Item quan = ItemService.gI().otpKH((short) 6);
        Item gang = ItemService.gI().otpKH((short) 21);
        Item giay = ItemService.gI().otpKH((short) 27);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));//129
        quan.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt TNSM");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setSongokuHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 650);
        Item quan = ItemService.gI().otpKHHD((short) 651);
        Item gang = ItemService.gI().otpKHHD((short) 657);
        Item giay = ItemService.gI().otpKHHD((short) 658);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        // set sogoku
        ao.itemOptions.add(new ItemOption(129, 0));//129
        quan.itemOptions.add(new ItemOption(129, 0));
        gang.itemOptions.add(new ItemOption(129, 0));
        giay.itemOptions.add(new ItemOption(129, 0));
        nhan.itemOptions.add(new ItemOption(129, 0));
        ao.itemOptions.add(new ItemOption(141, 100));
        quan.itemOptions.add(new ItemOption(141, 100));
        gang.itemOptions.add(new ItemOption(141, 100));
        giay.itemOptions.add(new ItemOption(141, 100));
        nhan.itemOptions.add(new ItemOption(141, 100));

        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Songoku Hủy Diệt");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setKrillin(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 0);
        Item quan = ItemService.gI().otpKH((short) 6);
        Item gang = ItemService.gI().otpKH((short) 21);
        Item giay = ItemService.gI().otpKH((short) 27);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_KRILLIN, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_KRILLIN, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_KRILLIN, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_KRILLIN, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_KRILLIN, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.SET_QCKK, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_QCKK, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_QCKK, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_QCKK, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_QCKK, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Krillin");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setKrillinHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 650);
        Item quan = ItemService.gI().otpKHHD((short) 651);
        Item gang = ItemService.gI().otpKHHD((short) 657);
        Item giay = ItemService.gI().otpKHHD((short) 658);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(128, 0));
        quan.itemOptions.add(new ItemOption(128, 0));
        gang.itemOptions.add(new ItemOption(128, 0));
        giay.itemOptions.add(new ItemOption(128, 0));
        nhan.itemOptions.add(new ItemOption(128, 0));
        ao.itemOptions.add(new ItemOption(140, 0));
        quan.itemOptions.add(new ItemOption(140, 0));
        gang.itemOptions.add(new ItemOption(140, 0));
        giay.itemOptions.add(new ItemOption(140, 0));
        nhan.itemOptions.add(new ItemOption(140, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích hoạt Hủy Diệt Krillin");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setTenshinhan(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 0);
        Item quan = ItemService.gI().otpKH((short) 6);
        Item gang = ItemService.gI().otpKH((short) 21);
        Item giay = ItemService.gI().otpKH((short) 27);
        Item rd = ItemService.gI().otpKH((short) 12);
        // set Tenshinhan
        ao.itemOptions.add(new ItemOption(ConstOption.SET_TENSHINHAN, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_TENSHINHAN, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_TENSHINHAN, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_TENSHINHAN, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_TENSHINHAN, 0));
        // x1.5 sát thương kaioken
        ao.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_KAIOKEN, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_KAIOKEN, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_KAIOKEN, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_KAIOKEN, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_KAIOKEN, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Tenshinhan");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setGohanHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 650);
        Item quan = ItemService.gI().otpKHHD((short) 651);
        Item gang = ItemService.gI().otpKHHD((short) 657);
        Item giay = ItemService.gI().otpKHHD((short) 658);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        // set gohan
        ao.itemOptions.add(new ItemOption(248, 0));
        quan.itemOptions.add(new ItemOption(248, 0));
        gang.itemOptions.add(new ItemOption(248, 0));
        giay.itemOptions.add(new ItemOption(248, 0));
        nhan.itemOptions.add(new ItemOption(248, 0));
        // x3 tnsm
        ao.itemOptions.add(new ItemOption(250, 3));
        quan.itemOptions.add(new ItemOption(250, 3));
        gang.itemOptions.add(new ItemOption(250, 3));
        giay.itemOptions.add(new ItemOption(250, 3));
        nhan.itemOptions.add(new ItemOption(250, 3));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích Hoạt Hủy Diệt Gohan");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setDende(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 1);
        Item quan = ItemService.gI().otpKH((short) 7);
        Item gang = ItemService.gI().otpKH((short) 22);
        Item giay = ItemService.gI().otpKH((short) 28);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_DENDE, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_DENDE, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_DENDE, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_DENDE, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_DENDE, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_LIEN_HOAN, 100));
        quan.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_LIEN_HOAN, 100));
        gang.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_LIEN_HOAN, 100));
        giay.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_LIEN_HOAN, 100));
        rd.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_LIEN_HOAN, 100));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Dende");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setTNSMNM(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_QUA_SET_TNSM);
        Item ao = ItemService.gI().otpKH((short) 1);
        Item quan = ItemService.gI().otpKH((short) 7);
        Item gang = ItemService.gI().otpKH((short) 22);
        Item giay = ItemService.gI().otpKH((short) 28);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt TNSM");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setDendeHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 652);
        Item quan = ItemService.gI().otpKHHD((short) 653);
        Item gang = ItemService.gI().otpKHHD((short) 659);
        Item giay = ItemService.gI().otpKHHD((short) 660);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(131, 0));
        quan.itemOptions.add(new ItemOption(131, 0));
        gang.itemOptions.add(new ItemOption(131, 0));
        giay.itemOptions.add(new ItemOption(131, 0));
        nhan.itemOptions.add(new ItemOption(131, 0));

        ao.itemOptions.add(new ItemOption(143, 100));
        quan.itemOptions.add(new ItemOption(143, 100));
        gang.itemOptions.add(new ItemOption(143, 100));
        giay.itemOptions.add(new ItemOption(143, 100));
        nhan.itemOptions.add(new ItemOption(143, 100));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích hoạt Hủy Diệt Dende");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setPicolo(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 1);
        Item quan = ItemService.gI().otpKH((short) 7);
        Item gang = ItemService.gI().otpKH((short) 22);
        Item giay = ItemService.gI().otpKH((short) 28);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_PICOLO, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_PICOLO, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_PICOLO, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_PICOLO, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_PICOLO, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KI, 100));
        quan.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KI, 100));
        gang.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KI, 100));
        giay.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KI, 100));
        rd.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_KI, 100));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Picolo");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setPicoloHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 652);
        Item quan = ItemService.gI().otpKHHD((short) 653);
        Item gang = ItemService.gI().otpKHHD((short) 659);
        Item giay = ItemService.gI().otpKHHD((short) 660);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(130, 0));
        quan.itemOptions.add(new ItemOption(130, 0));
        gang.itemOptions.add(new ItemOption(130, 0));
        giay.itemOptions.add(new ItemOption(130, 0));
        nhan.itemOptions.add(new ItemOption(130, 0));

        ao.itemOptions.add(new ItemOption(142, 100));
        quan.itemOptions.add(new ItemOption(142, 100));
        gang.itemOptions.add(new ItemOption(142, 100));
        giay.itemOptions.add(new ItemOption(142, 100));
        nhan.itemOptions.add(new ItemOption(142, 100));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích Hoạt Hủy Diệt Picolo");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setDaimao(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 1);
        Item quan = ItemService.gI().otpKH((short) 7);
        Item gang = ItemService.gI().otpKH((short) 22);
        Item giay = ItemService.gI().otpKH((short) 28);
        Item rd = ItemService.gI().otpKH((short) 12);
        // daimao
        ao.itemOptions.add(new ItemOption(ConstOption.SET_DAIMAO, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_DAIMAO, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_DAIMAO, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_DAIMAO, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_DAIMAO, 0));

        // 100% sát thương đẻ trứng
        ao.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_DE_TRUNG, 100));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_DE_TRUNG, 100));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_DE_TRUNG, 100));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_DE_TRUNG, 100));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_SAT_THUONG_DE_TRUNG, 100));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Daimao");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setDaimaoHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 652);
        Item quan = ItemService.gI().otpKHHD((short) 653);
        Item gang = ItemService.gI().otpKHHD((short) 659);
        Item giay = ItemService.gI().otpKHHD((short) 660);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        // daimao
        ao.itemOptions.add(new ItemOption(132, 0));
        quan.itemOptions.add(new ItemOption(132, 0));
        gang.itemOptions.add(new ItemOption(132, 0));
        giay.itemOptions.add(new ItemOption(132, 0));
        nhan.itemOptions.add(new ItemOption(132, 0));

        // x3 TNSM khi danh quai
        ao.itemOptions.add(new ItemOption(252, 3));
        quan.itemOptions.add(new ItemOption(252, 3));
        gang.itemOptions.add(new ItemOption(252, 3));
        giay.itemOptions.add(new ItemOption(252, 3));
        nhan.itemOptions.add(new ItemOption(252, 3));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích Hoạt Hủy Diệt Daimao");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setKakarot(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 2);
        Item quan = ItemService.gI().otpKH((short) 8);
        Item gang = ItemService.gI().otpKH((short) 23);
        Item giay = ItemService.gI().otpKH((short) 29);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_KAKAROT, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_KAKAROT, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_KAKAROT, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_KAKAROT, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_KAKAROT, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.SET_DAM_GALICK, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_DAM_GALICK, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_DAM_GALICK, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_DAM_GALICK, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_DAM_GALICK, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Kakarot");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setTNSMXD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_QUA_SET_TNSM);
        Item ao = ItemService.gI().otpKH((short) 2);
        Item quan = ItemService.gI().otpKH((short) 8);
        Item gang = ItemService.gI().otpKH((short) 23);
        Item giay = ItemService.gI().otpKH((short) 29);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_TNSM, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_X_TNSM_5_MON, 3));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt TNSM");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setKakarotHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 654);
        Item quan = ItemService.gI().otpKHHD((short) 655);
        Item gang = ItemService.gI().otpKHHD((short) 661);
        Item giay = ItemService.gI().otpKHHD((short) 662);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(133, 0));
        quan.itemOptions.add(new ItemOption(133, 0));
        gang.itemOptions.add(new ItemOption(133, 0));
        giay.itemOptions.add(new ItemOption(133, 0));
        nhan.itemOptions.add(new ItemOption(133, 0));

        ao.itemOptions.add(new ItemOption(136, 0));
        quan.itemOptions.add(new ItemOption(136, 0));
        gang.itemOptions.add(new ItemOption(136, 0));
        giay.itemOptions.add(new ItemOption(136, 0));
        nhan.itemOptions.add(new ItemOption(136, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích hoạt Hủy Diệt Kakarot");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setCadic(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 2);
        Item quan = ItemService.gI().otpKH((short) 8);
        Item gang = ItemService.gI().otpKH((short) 23);
        Item giay = ItemService.gI().otpKH((short) 29);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_CADIC, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_CADIC, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_CADIC, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_CADIC, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_CADIC, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.SET_X5_THOI_GIAN_HOA_KHI, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_X5_THOI_GIAN_HOA_KHI, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_X5_THOI_GIAN_HOA_KHI, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_X5_THOI_GIAN_HOA_KHI, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_X5_THOI_GIAN_HOA_KHI, 0));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Cadic");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setCadicHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 654);
        Item quan = ItemService.gI().otpKHHD((short) 655);
        Item gang = ItemService.gI().otpKHHD((short) 661);
        Item giay = ItemService.gI().otpKHHD((short) 662);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(134, 0));
        quan.itemOptions.add(new ItemOption(134, 0));
        gang.itemOptions.add(new ItemOption(134, 0));
        giay.itemOptions.add(new ItemOption(134, 0));
        nhan.itemOptions.add(new ItemOption(134, 0));

        ao.itemOptions.add(new ItemOption(251, 3));
        quan.itemOptions.add(new ItemOption(251, 3));
        gang.itemOptions.add(new ItemOption(251, 3));
        giay.itemOptions.add(new ItemOption(251, 3));
        nhan.itemOptions.add(new ItemOption(251, 3));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích Hoạt Hủy Diệt Cadic");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setNappa(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1227);
        Item ao = ItemService.gI().otpKH((short) 2);
        Item quan = ItemService.gI().otpKH((short) 8);
        Item gang = ItemService.gI().otpKH((short) 23);
        Item giay = ItemService.gI().otpKH((short) 29);
        Item rd = ItemService.gI().otpKH((short) 12);
        ao.itemOptions.add(new ItemOption(ConstOption.SET_NAPPA, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.SET_NAPPA, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.SET_NAPPA, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.SET_NAPPA, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.SET_NAPPA, 0));

        ao.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_HP, 100));
        quan.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_HP, 100));
        gang.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_HP, 100));
        giay.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_HP, 100));
        rd.itemOptions.add(new ItemOption(ConstOption.OPTION_PERCENT_HP, 100));
        ao.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        quan.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        gang.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        giay.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        rd.itemOptions.add(new ItemOption(ConstOption.KHONG_THE_GIAO_DICH, 0));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, rd, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Nappa");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void setNappaHD(Player player) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_SKH_HUY_DIET);
        Item ao = ItemService.gI().otpKHHD((short) 654);
        Item quan = ItemService.gI().otpKHHD((short) 655);
        Item gang = ItemService.gI().otpKHHD((short) 661);
        Item giay = ItemService.gI().otpKHHD((short) 662);
        Item nhan = ItemService.gI().otpKHHD((short) 656);
        ao.itemOptions.add(new ItemOption(135, 0));
        quan.itemOptions.add(new ItemOption(135, 0));
        gang.itemOptions.add(new ItemOption(135, 0));
        giay.itemOptions.add(new ItemOption(135, 0));
        nhan.itemOptions.add(new ItemOption(135, 0));

        ao.itemOptions.add(new ItemOption(138, 100));
        quan.itemOptions.add(new ItemOption(138, 100));
        gang.itemOptions.add(new ItemOption(138, 100));
        giay.itemOptions.add(new ItemOption(138, 100));
        nhan.itemOptions.add(new ItemOption(138, 100));
        if (InventoryService.gI().getCountEmptyBag(player) > 4) {
            InventoryService.gI().addItemBag(player, ao, 0);
            InventoryService.gI().addItemBag(player, quan, 0);
            InventoryService.gI().addItemBag(player, gang, 0);
            InventoryService.gI().addItemBag(player, giay, 0);
            InventoryService.gI().addItemBag(player, nhan, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Set Kích hoạt Hủy Diệt Nappa");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
        }
    }

    public void bongTaiCap3MaxChiSo(Player player, int type) throws Exception {
        Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, ConstItem.HOP_BTC3);
        Item btc3 = createNewItem((short)ConstItem.BONG_TAI_CAP_3, 1);
        switch (type) {
            case ConstOptionSelect.SUC_DANH:
                btc3.itemOptions.add(new ItemOption(50, 35));
                break;
            case ConstOptionSelect.HP:
                btc3.itemOptions.add(new ItemOption(77, 35));
                break;
            case ConstOptionSelect.KI:
                btc3.itemOptions.add(new ItemOption(103, 35));
                break;
            default:
                break;
        }

        btc3.itemOptions.add(new ItemOption(30, 0));

        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
            InventoryService.gI().addItemBag(player, btc3, 0);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được Bông tai cấp 3");
            InventoryService.gI().subQuantityItemsBag(player, hq, 1);
            InventoryService.gI().sendItemBags(player);
        } else {
            Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 1 ô trống hành trang");
        }
    }

    public Item DoThienSu(int itemId, int gender, int perSuccess, int perLucky) {
        Item dots = createItemSetKichHoat(itemId, 1);
        List<Integer> ao = Arrays.asList(1048, 1049, 1050);
        List<Integer> quan = Arrays.asList(1051, 1052, 1053);
        List<Integer> gang = Arrays.asList(1054, 1055, 1056);
        List<Integer> giay = Arrays.asList(1057, 1058, 1059);
        List<Integer> nhan = Arrays.asList(1060, 1061, 1062);
        //áo
        if (ao.contains(itemId)) {
            switch (gender) {
                case 0:
                    dots.itemOptions.add(new ItemOption(47, Util.nextInt(120, 135) * 1_600 / 100)); // áo TD giáp
                    break;
                case 1:
                    dots.itemOptions.add(new ItemOption(47, Util.nextInt(120, 135) * 1_700 / 100)); // áo NM giáp
                    break;
                case 2:
                    dots.itemOptions.add(new ItemOption(47, Util.nextInt(120, 135) * 1_800 / 100)); // áo XD giáp
                    break;
            }
        }
        //quần
        if (Util.isTrue(80, 100)) {
            if (quan.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 130) * 104 / 100)); // quần TD hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 130) * 16_000 / 100)); // quần td hồi hp
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 130) * 100 / 100)); // quần NM hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 130) * 14_000 / 100)); // quần NM hồi hp
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 130) * 96 / 100)); // quần XD hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 130) * 12_000 / 100)); // quần XD hồi hp
                        break;
                }
            }
        } else {
            if (quan.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 135) * 104 / 100)); // quần TD hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 135) * 16_000 / 100)); // quần td hồi hp
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 135) * 100 / 100)); // quần NM hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 135) * 14_000 / 100)); // quần NM hồi hp
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(22, Util.nextInt(120, 135) * 96 / 100)); // quần XD hp
                        dots.itemOptions.add(new ItemOption(27, Util.nextInt(120, 135) * 12_000 / 100)); // quần XD hồi hp
                        break;
                }
            }
        }
        //găng
        if (Util.isTrue(80, 100)) {
            if (gang.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 130) * 10_800 / 100)); // gang TD sd
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 130) * 10_800 / 100)); // gang NM sd
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 130) * 10_800 / 100)); // gang XD sd
                        break;
                }
            }
        } else {
            if (gang.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 135) * 10_800 / 100)); // gang TD sd
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 135) * 10_800 / 100)); // gang NM sd
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(0, Util.nextInt(120, 135) * 10_800 / 100)); // gang XD sd
                        break;
                }
            }
        }
        //giày
        if (Util.isTrue(80, 100)) {
            if (giay.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(23, Util.nextInt(120, 130) * 96 / 100)); // quần TD hp
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 130) * 12_000 / 100)); // quần td hồi hp
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(23, Util.nextInt(120, 130) * 100 / 100)); // quần NM hp
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 130) * 12_800 / 100)); // quần NM hồi hp
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(23, Util.nextInt(120, 130) * 92 / 100)); // quần XD hp
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 130) * 11_200 / 100)); // quần XD hồi hp
                        break;
                }
            }
        } else {
            if (giay.contains(itemId)) {
                switch (gender) {
                    case 0:
                        dots.itemOptions.add(new ItemOption(23, Util.nextInt(120, 135) * 96 / 100)); // quần TD hp
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 135) * 12_000 / 100)); // quần td hồi hp
                        break;
                    case 1:
                        dots.itemOptions.add(new ItemOption(23, Util.nextInt(120, 135) * 100 / 100)); // quần NM hp
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 135) * 12_800 / 100)); // quần NM hồi hp
                        break;
                    case 2:
                        dots.itemOptions.add(new ItemOption(28, Util.nextInt(120, 135) * 11_200 / 100)); // quần XD hồi hp
                        break;
                }
            }
        }
        if (nhan.contains(itemId)) {
            dots.itemOptions.add(new ItemOption(14, Util.nextInt(120, 135) * 16 / 100)); // nhẫn 18-20%
        }
        dots.itemOptions.add(new ItemOption(21, 90));
        dots.itemOptions.add(new ItemOption(30, 0));
        if (perSuccess <= perLucky) {
            if (perSuccess >= (perLucky - 3)) {
                perLucky = 3;
            } else if (perSuccess <= (perLucky - 4) && perSuccess >= (perLucky - 10)) {
                perLucky = 2;
            } else {
                perLucky = 1;
            }
            dots.itemOptions.add(new ItemOption(41, perLucky));
            ArrayList<Integer> listOptionBonus = new ArrayList<>();
            listOptionBonus.add(42);
            listOptionBonus.add(43);
            listOptionBonus.add(44);
            listOptionBonus.add(45);
            listOptionBonus.add(46);
            listOptionBonus.add(197);
            listOptionBonus.add(198);
            listOptionBonus.add(199);
            listOptionBonus.add(200);
            listOptionBonus.add(201);
            listOptionBonus.add(202);
            listOptionBonus.add(203);
            listOptionBonus.add(204);
            for (int i = 0; i < perLucky; i++) {
                perSuccess = Util.nextInt(0, listOptionBonus.size() - 1);
                dots.itemOptions.add(new ItemOption(listOptionBonus.get(perSuccess), Util.nextInt(1, 6)));
                listOptionBonus.remove(perSuccess);
            }
        }
        return dots;
    }

    public ConsignmentItem convertToConsignmentItem(Item item) {
        ConsignmentItem it = new ConsignmentItem();
        it.itemOptions = new ArrayList<>();
        it.template = item.template;
        it.info = item.info;
        it.content = item.content;
        it.quantity = item.quantity;
        it.createTime = item.createTime;
        for (ItemOption io : item.itemOptions) {
            it.itemOptions.add(new ItemOption(io));
        }
        it.setPriceGold(-1);
        it.setPriceGem(-1);
        return it;
    }

    public Item createItemSetKichHoat(int tempId, int quantity) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.itemOptions = createItemNull().itemOptions;
        item.createTime = System.currentTimeMillis();
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public Item createNewItem(short tempId) {
        return createNewItem(tempId, 1);
    }

    public Item createNewItem(short tempId, int quantity) {
        Item item = new Item();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();

        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public ConsignmentItem createNewConsignmentItem(short tempId, int quantity) {
        ConsignmentItem item = new ConsignmentItem();
        item.template = getTemplate(tempId);
        item.quantity = quantity;
        item.createTime = System.currentTimeMillis();
        item.content = item.getContent();
        item.info = item.getInfo();
        return item;
    }

    public Item createItemFromItemMap(ItemMap itemMap) {
        Item item = createNewItem(itemMap.itemTemplate.id, itemMap.quantity);
        item.itemOptions = itemMap.options;
        return item;
    }

    public ItemOptionTemplate getItemOptionTemplate(int id) {
        return Manager.ITEM_OPTION_TEMPLATES.get(id);
    }

    public ItemTemplate getTemplate(int id) {
        return Manager.ITEM_TEMPLATES.get(id);
    }

    public boolean isItemActivation(Item item) {
        return false;
    }

    public int getPercentTrainArmor(Item item) {
        if (item != null) {
            switch (item.template.id) {
                case 529:
                case 534:
                    return 10;
                case 530:
                case 535:
                    return 20;
                case 531:
                case 536:
                    return 30;
                case ConstItem.GIAP_TAP_LUYEN_CAP_4:
                    return 40;
                default:
                    return 0;
            }
        } else {
            return 0;
        }
    }

    public boolean isTrainArmor(Item item) {
        if (item != null) {
            switch (item.template.id) {
                case 529:
                case 534:
                case 530:
                case 535:
                case 531:
                case 536:
                case ConstItem.GIAP_TAP_LUYEN_CAP_4:
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public boolean isOutOfDateTime(Item item) {
        long now = System.currentTimeMillis();
        if (item != null) {
            for (ItemOption io : item.itemOptions) {
                if (io.optionTemplate.id == 93) {
                    int dayPass = (int) TimeUtil.diffDate(new Date(), new Date(item.createTime), TimeUtil.DAY);
                    if (dayPass != 0) {
                        io.param -= dayPass;
                        if (io.param <= 0) {
                            return true;
                        } else {
                            item.createTime = System.currentTimeMillis();
                        }
                    }
                } else if (io.optionTemplate.id == 196) {
                    long e = io.param * 1000L;
                    if (e <= now) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isItemNoLimitQuantity(int id) {
        // item k giới hạn số lượng
        // mảnh trang bị thiên sứ
        return id >= 1066 && id <= 1070;
    }
}
