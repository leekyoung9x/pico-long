/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.item;

import java.util.Random;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class RewardItem {

    private static RewardItem i;

    public static RewardItem gI() {
        if (i == null) {
            i = new RewardItem();
        }
        return i;
    }


    public Item otpKH(short tempId) {
        return ItemService.gI().otpHD(tempId, 1);
    }

//  
    public void setSongoku(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1048);
            Item quan = this.otpKH((short) 1051);
            Item gang = this.otpKH((short) 1054);
            Item giay = this.otpKH((short) 1057);
            Item rd = this.otpKH((short) 1060);
            ao.itemOptions.add(new ItemOption(129, 0));//129
            quan.itemOptions.add(new ItemOption(129, 0));
            gang.itemOptions.add(new ItemOption(129, 0));
            giay.itemOptions.add(new ItemOption(129, 0));
            rd.itemOptions.add(new ItemOption(129, 0));
            ao.itemOptions.add(new ItemOption(141, 100));
            quan.itemOptions.add(new ItemOption(141, 100));
            gang.itemOptions.add(new ItemOption(141, 100));
            giay.itemOptions.add(new ItemOption(141, 100));
            rd.itemOptions.add(new ItemOption(141, 100));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
            if (InventoryService.gI().getCountEmptyBag(player) > 4) {
                InventoryService.gI().addItemBag(player, ao, 0);
                InventoryService.gI().addItemBag(player, quan, 0);
                InventoryService.gI().addItemBag(player, gang, 0);
                InventoryService.gI().addItemBag(player, giay, 0);
                InventoryService.gI().addItemBag(player, rd, 0);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Kame");
                InventoryService.gI().subQuantityItemsBag(player, hq, 1);
                InventoryService.gI().sendItemBags(player);
            } else {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setThenXinHang(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1048);
            Item quan = this.otpKH((short) 1051);
            Item gang = this.otpKH((short) 1054);
            Item giay = this.otpKH((short) 1057);
            Item rd = this.otpKH((short) 1060);
            ao.itemOptions.add(new ItemOption(127, 0));
            quan.itemOptions.add(new ItemOption(127, 0));
            gang.itemOptions.add(new ItemOption(127, 0));
            giay.itemOptions.add(new ItemOption(127, 0));
            rd.itemOptions.add(new ItemOption(127, 0));
            ao.itemOptions.add(new ItemOption(139, 0));
            quan.itemOptions.add(new ItemOption(139, 0));
            gang.itemOptions.add(new ItemOption(139, 0));
            giay.itemOptions.add(new ItemOption(139, 0));
            rd.itemOptions.add(new ItemOption(139, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
            if (InventoryService.gI().getCountEmptyBag(player) > 4) {
                InventoryService.gI().addItemBag(player, ao, 0);
                InventoryService.gI().addItemBag(player, quan, 0);
                InventoryService.gI().addItemBag(player, gang, 0);
                InventoryService.gI().addItemBag(player, giay, 0);
                InventoryService.gI().addItemBag(player, rd, 0);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Thên Xin Hăng");
                InventoryService.gI().subQuantityItemsBag(player, hq, 1);
                InventoryService.gI().sendItemBags(player);
            } else {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setKaioKen(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1048);
            Item quan = this.otpKH((short) 1051);
            Item gang = this.otpKH((short) 1054);
            Item giay = this.otpKH((short) 1057);
            Item rd = this.otpKH((short) 1060);
            ao.itemOptions.add(new ItemOption(128, 0));
            quan.itemOptions.add(new ItemOption(128, 0));
            gang.itemOptions.add(new ItemOption(128, 0));
            giay.itemOptions.add(new ItemOption(128, 0));
            rd.itemOptions.add(new ItemOption(128, 0));
            ao.itemOptions.add(new ItemOption(140, 0));
            quan.itemOptions.add(new ItemOption(140, 0));
            gang.itemOptions.add(new ItemOption(140, 0));
            giay.itemOptions.add(new ItemOption(140, 0));
            rd.itemOptions.add(new ItemOption(140, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
            if (InventoryService.gI().getCountEmptyBag(player) > 4) {
                InventoryService.gI().addItemBag(player, ao, 0);
                InventoryService.gI().addItemBag(player, quan, 0);
                InventoryService.gI().addItemBag(player, gang, 0);
                InventoryService.gI().addItemBag(player, giay, 0);
                InventoryService.gI().addItemBag(player, rd, 0);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Kaioken");
                InventoryService.gI().subQuantityItemsBag(player, hq, 1);
                InventoryService.gI().sendItemBags(player);
            } else {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLienHoan(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1049);
            Item quan = this.otpKH((short) 1052);
            Item gang = this.otpKH((short) 1055);
            Item giay = this.otpKH((short) 1058);
            Item rd = this.otpKH((short) 1061);
            ao.itemOptions.add(new ItemOption(131, 0));
            quan.itemOptions.add(new ItemOption(131, 0));
            gang.itemOptions.add(new ItemOption(131, 0));
            giay.itemOptions.add(new ItemOption(131, 0));
            rd.itemOptions.add(new ItemOption(131, 0));

            ao.itemOptions.add(new ItemOption(143, 0));
            quan.itemOptions.add(new ItemOption(143, 0));
            gang.itemOptions.add(new ItemOption(143, 0));
            giay.itemOptions.add(new ItemOption(143, 0));
            rd.itemOptions.add(new ItemOption(143, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
            if (InventoryService.gI().getCountEmptyBag(player) > 4) {
                InventoryService.gI().addItemBag(player, ao, 0);
                InventoryService.gI().addItemBag(player, quan, 0);
                InventoryService.gI().addItemBag(player, gang, 0);
                InventoryService.gI().addItemBag(player, giay, 0);
                InventoryService.gI().addItemBag(player, rd, 0);
                InventoryService.gI().sendItemBags(player);
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được set Kích hoạt Liên hoàn");
                InventoryService.gI().subQuantityItemsBag(player, hq, 1);
                InventoryService.gI().sendItemBags(player);
            } else {
                Service.getInstance().sendThongBao(player, "Bạn phải có ít nhất 5 ô trống hành trang");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPicolo(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1049);
            Item quan = this.otpKH((short) 1052);
            Item gang = this.otpKH((short) 1055);
            Item giay = this.otpKH((short) 1058);
            Item rd = this.otpKH((short) 1061);
            ao.itemOptions.add(new ItemOption(130, 0));
            quan.itemOptions.add(new ItemOption(130, 0));
            gang.itemOptions.add(new ItemOption(130, 0));
            giay.itemOptions.add(new ItemOption(130, 0));
            rd.itemOptions.add(new ItemOption(130, 0));

            ao.itemOptions.add(new ItemOption(142, 0));
            quan.itemOptions.add(new ItemOption(142, 0));
            gang.itemOptions.add(new ItemOption(142, 0));
            giay.itemOptions.add(new ItemOption(142, 0));
            rd.itemOptions.add(new ItemOption(142, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPikkoroDaimao(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1049);
            Item quan = this.otpKH((short) 1052);
            Item gang = this.otpKH((short) 1055);
            Item giay = this.otpKH((short) 1058);
            Item rd = this.otpKH((short) 1061);
            ao.itemOptions.add(new ItemOption(132, 0));
            quan.itemOptions.add(new ItemOption(132, 0));
            gang.itemOptions.add(new ItemOption(132, 0));
            giay.itemOptions.add(new ItemOption(132, 0));
            rd.itemOptions.add(new ItemOption(132, 0));

            ao.itemOptions.add(new ItemOption(144, 0));
            quan.itemOptions.add(new ItemOption(144, 0));
            gang.itemOptions.add(new ItemOption(144, 0));
            giay.itemOptions.add(new ItemOption(144, 0));
            rd.itemOptions.add(new ItemOption(144, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setKakarot(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1050);
            Item quan = this.otpKH((short) 1053);
            Item gang = this.otpKH((short) 1056);
            Item giay = this.otpKH((short) 1059);
            Item rd = this.otpKH((short) 1062);
            ao.itemOptions.add(new ItemOption(133, 0));
            quan.itemOptions.add(new ItemOption(133, 0));
            gang.itemOptions.add(new ItemOption(133, 0));
            giay.itemOptions.add(new ItemOption(133, 0));
            rd.itemOptions.add(new ItemOption(133, 0));

            ao.itemOptions.add(new ItemOption(136, 0));
            quan.itemOptions.add(new ItemOption(136, 0));
            gang.itemOptions.add(new ItemOption(136, 0));
            giay.itemOptions.add(new ItemOption(136, 0));
            rd.itemOptions.add(new ItemOption(136, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setCadic(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1050);
            Item quan = this.otpKH((short) 1053);
            Item gang = this.otpKH((short) 1056);
            Item giay = this.otpKH((short) 1059);
            Item rd = this.otpKH((short) 1062);
            ao.itemOptions.add(new ItemOption(134, 0));
            quan.itemOptions.add(new ItemOption(134, 0));
            gang.itemOptions.add(new ItemOption(134, 0));
            giay.itemOptions.add(new ItemOption(134, 0));
            rd.itemOptions.add(new ItemOption(134, 0));

            ao.itemOptions.add(new ItemOption(137, 0));
            quan.itemOptions.add(new ItemOption(137, 0));
            gang.itemOptions.add(new ItemOption(137, 0));
            giay.itemOptions.add(new ItemOption(137, 0));
            rd.itemOptions.add(new ItemOption(137, 0));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setNappa(Player player) {
        try {
            Item hq = InventoryService.gI().findItem(player.inventory.itemsBag, 1961);
            Item ao = this.otpKH((short) 1050);
            Item quan = this.otpKH((short) 1053);
            Item gang = this.otpKH((short) 1056);
            Item giay = this.otpKH((short) 1059);
            Item rd = this.otpKH((short) 1062);
            ao.itemOptions.add(new ItemOption(135, 0));
            quan.itemOptions.add(new ItemOption(135, 0));
            gang.itemOptions.add(new ItemOption(135, 0));
            giay.itemOptions.add(new ItemOption(135, 0));
            rd.itemOptions.add(new ItemOption(135, 0));

            ao.itemOptions.add(new ItemOption(138, 100));
            quan.itemOptions.add(new ItemOption(138, 100));
            gang.itemOptions.add(new ItemOption(138, 100));
            giay.itemOptions.add(new ItemOption(138, 100));
            rd.itemOptions.add(new ItemOption(138, 100));
            ao.itemOptions.add(new ItemOption(30, 0));
            quan.itemOptions.add(new ItemOption(30, 0));
            gang.itemOptions.add(new ItemOption(30, 0));
            giay.itemOptions.add(new ItemOption(30, 0));
            rd.itemOptions.add(new ItemOption(30, 0));
            ao.itemOptions.add(new ItemOption(21, 100));
            quan.itemOptions.add(new ItemOption(21, 100));
            gang.itemOptions.add(new ItemOption(21, 100));
            giay.itemOptions.add(new ItemOption(21, 100));
            rd.itemOptions.add(new ItemOption(21, 100));
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public Item otpKH(short tempId, int quantity) {
//        Item item = new Item();
//        item.template = ItemService.gI().getTemplate(tempId);
//        item.quantity = quantity;
//        item.createTime = System.currentTimeMillis();
//        switch (item.template.type) {
//            case 0:// ao
//                item.itemOptions.add(new ItemOption(47, Util.highlightsItem(true, new Random().nextInt(1001) + 1800))); // áo từ 1800-2800 giáp
//                break;
//            case 1://quan
//                item.itemOptions.add(new ItemOption(22, Util.highlightsItem(true, new Random().nextInt(16) + 85))); // hp 85-100k
//                break;
//            case 2:// gang
//                item.itemOptions.add(new ItemOption(0, Util.highlightsItem(true, new Random().nextInt(150) + 8500))); // 8500-10000
//                break;
//            case 3:// giay
//                item.itemOptions.add(new ItemOption(23, Util.highlightsItem(true, new Random().nextInt(11) + 80))); // ki 80-90k
//                break;
//            case 4:
//                item.itemOptions.add(new ItemOption(14, new Random().nextInt(3) + 17)); //chí mạng 17-19%
//                break;
//            default:
//                break;
//        }
//        item.content = item.getContent();
//        item.info = item.getInfo();
//        return item;
//    }
}
