package nro.services;

import nro.consts.ConstNpc;
import nro.models.item.Item;
import nro.models.player.NPoint;
import nro.models.player.Pet;
import nro.models.player.Player;

/**
 * @Build by Arriety
 */
public class OpenPowerService {

    public static final int COST_SPEED_OPEN_LIMIT_POWER = 1000000000;

    private static OpenPowerService i;

    private OpenPowerService() {

    }

    public static OpenPowerService gI() {
        if (i == null) {
            i = new OpenPowerService();
        }
        return i;
    }

    public boolean openPowerBasic(Player player) {
        byte curLimit = player.nPoint.limitPower;
        if (curLimit < NPoint.MAX_LIMIT) {
            if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
                if (player.nPoint.limitPower == 10) {
                    if (player.nPoint.power <= 150000000000L) {
                        // Nếu sức mạnh dưới 150 tỷ, gửi thông báo và dừng thực hiện lệnh tiếp theo
                        if (!player.isPet) {

                            Service.getInstance().sendThongBao(player, "Con cần trên 150 tỷ sức mạnh");
                        } else {
                            Service.getInstance().sendThongBao(((Pet) player).master, "Đệ tử của con cần trên 150 tỷ sức mạnh");
                        }
                        return false;
                    }

//                    Item pi = InventoryService.gI().findItemBagByTemp(player, 674);
//                    if (pi == null || pi.quantity < 999) {
//                        // Nếu không đủ x999 đá ngũ sắc, gửi thông báo và dừng thực hiện lệnh tiếp theo
//                        if (!player.isPet) {
//
//                            Service.getInstance().sendThongBao(player, "Cần trên 150 tỷ và 999 đá ngũ sắc");
//                        } else {
//                            Service.getInstance().sendThongBao(((Pet) player).master, "Cần trên 150 tỷ và 999 đá ngũ sắc");
//                        }
//                        return false;
//                    }

                    // Điều kiện `pi != null && pi.quantity >= 999` đã được xử lý ở trên, tiếp tục logic nếu cần
                }
            }
            if (player.nPoint.limitPower == 9 && !player.inventory.itemsBody.stream().limit(1).allMatch(it -> it.isNotNullItem()
                    && it.template.name.contains("Hủy Diệt"))) {
                if (!player.isPet) {
                    Service.getInstance().sendThongBao(player, "Yêu cầu 5 đồ Hủy Diệt");
                } else {
                    Service.getInstance().sendThongBao(((Pet) player).master, "Yêu cầu 5 đồ Hủy Diệt");
                }
                return false;
            }
            if (player.nPoint.limitPower == 10) {
//                Item pi = InventoryService.gI().findItemBagByTemp(player, 674);
//                InventoryService.gI().subQuantityItemsBag(player, pi, 999);
//                InventoryService.gI().sendItemBags(player);
                player.nPoint.tiemNang = 0;

                Service.getInstance().player(player);
                player.zone.load_Me_To_Another(player);
                player.zone.loadAnotherToMe(player);
                Service.getInstance().Send_Caitrang(player);
                Service.getInstance().point(player);
            }
            if (!player.itemTime.isOpenPower && player.nPoint.canOpenPower()) {
                player.itemTime.isOpenPower = true;
                player.itemTime.lastTimeOpenPower = System.currentTimeMillis();
                ItemTimeService.gI().sendAllItemTime(player);
//                return true;
            } else {
                Service.getInstance().sendThongBao(player, "Sức mạnh của bạn không đủ để thực hiện");
                return false;
            }

            player.nPoint.limitPower++;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;

            }
            player.nPoint.initPowerLimit();
            if (!player.isPet) {

                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {

                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;

        } else {
            Service.getInstance().sendThongBao(player, "Sức mạnh của bạn đã đạt tới mức tối đa");
            return false;
        }
    }

    public boolean openPowerSpeed(Player player) {
        if (player.nPoint.limitPower < NPoint.MAX_LIMIT) {
            if (player.nPoint.limitPower == 10) {
                if (player.nPoint.power <= 150000000000L) {
                    // Nếu sức mạnh dưới 150 tỷ, gửi thông báo và dừng thực hiện lệnh tiếp theo
                    if (!player.isPet) {
                        Service.getInstance().sendThongBao(player, "Con cần trên 150 tỷ sức mạnh");
                    } else {
                        Service.getInstance().sendThongBao(((Pet) player).master, "Đệ tử của con cần trên 150 tỷ sức mạnh");
                    }
                    return false;
                }

//                Item pi = InventoryService.gI().findItemBagByTemp(!player.isPet ? player : ((Pet) player).master, 674);
//                if (pi == null || pi.quantity < 999) {
//                    // Nếu không đủ x999 đá ngũ sắc, gửi thông báo và dừng thực hiện lệnh tiếp theo
//                    if (!player.isPet) {
//                        Service.getInstance().sendThongBao(player, "Cần trên 150 tỷ và 999 đá ngũ sắc");
//                    } else {
//                        Service.getInstance().sendThongBao(((Pet) player).master, "Cần trên 150 tỷ và 999 đá ngũ sắc");
//                    }
//                    return false;
//                }

                // Điều kiện `pi != null && pi.quantity >= 999` đã được xử lý ở trên, tiếp tục logic nếu cần
            }

            Player master = new Player();

            if (player instanceof Pet) {
                master = ((Pet) player).master;
            } else {
                master = player;
            }

            if (master.inventory.gold < 1_000_000_000) {
                Service.getInstance().sendThongBao(((Pet) player).master, "Con nghèo thế 1 tỷ vàng thôi mà cũng không có");
                return false;
            }

            if (player.nPoint.limitPower == 9
                    && !player.inventory.itemsBody.stream().limit(1).allMatch(it -> it.isNotNullItem()
                    && it.template.name.contains("Hủy Diệt"))) {
                if (!player.isPet) {
                    Service.getInstance().sendThongBao(player, "Yêu cầu 5 đồ Hủy Diệt");
                } else {
                    Service.getInstance().sendThongBao(((Pet) player).master, "Yêu cầu 5 đồ Hủy Diệt");
                }
                return false;
            }
            if (player.nPoint.limitPower == 10) {
//                Item pi = InventoryService.gI().findItemBagByTemp(!player.isPet ? player : ((Pet) player).master, 674);
//                InventoryService.gI().subQuantityItemsBag(!player.isPet ? player : ((Pet) player).master, pi, 999);
//                InventoryService.gI().sendItemBags(!player.isPet ? player : ((Pet) player).master);
                player.nPoint.tiemNang = 0;

//                Service.getInstance().player(!player.isPet ? player : ((Pet) player).master);
//                Service.getInstance().Send_Caitrang(player);
//                player.zone.load_Me_To_Another(!player.isPet ? player : ((Pet) player).master);
//                player.zone.loadAnotherToMe(!player.isPet ? player : ((Pet) player).master);
//                Service.getInstance().point(!player.isPet ? player : ((Pet) player).master);
            }
            player.nPoint.limitPower++;
            if (player.nPoint.limitPower > NPoint.MAX_LIMIT) {
                player.nPoint.limitPower = NPoint.MAX_LIMIT;

            }
            player.nPoint.initPowerLimit();

            master.inventory.addGold(-1_000_000_000);
            Service.getInstance().sendMoney(master);

            if (!player.isPet) {

                Service.getInstance().sendThongBao(player, "Giới hạn sức mạnh của bạn đã được tăng lên 1 bậc");
            } else {

                Service.getInstance().sendThongBao(((Pet) player).master, "Giới hạn sức mạnh của đệ tử đã được tăng lên 1 bậc");
            }
            return true;
        } else {
            if (!player.isPet) {
                Service.getInstance().sendThongBao(player, "Sức mạnh của bạn đã đạt tới mức tối đa");
            } else {
                Service.getInstance().sendThongBao(((Pet) player).master, "Sức mạnh của đệ tử đã đạt tới mức tối đa");
            }
            return false;
        }
    }

}
