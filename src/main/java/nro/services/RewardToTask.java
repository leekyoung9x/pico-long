/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.services;

import nro.consts.ConstItem;
import nro.consts.ConstTask;
import nro.models.item.Item;
import nro.models.player.Player;
import nro.services.func.SummonDragon;
import nro.utils.Util;

/**
 * @author Arriety
 */
public class RewardToTask {

    private static RewardToTask instance;

    public static RewardToTask getInstance() {
        if (instance == null) {
            instance = new RewardToTask();
        }
        return instance;
    }

    public void rewardToTask(Player player) {
//        int goldReward = 0;
        int rubyReward = 0;
        int point = 0;
        switch (player.playerTask.sideTask.level) {
            // ekko
            // xóa điểm sự kiện chỉ để lại điểm năng động
            case ConstTask.EASY:
//                rubyReward = ConstTask.RUBY_EASY;
                player.nPoint.nangdong++;
//                player.diem_skien++;
                point = 1;
                break;
            case ConstTask.NORMAL:
//                rubyReward = ConstTask.RUBY_NORMAL;
                player.nPoint.nangdong += 2;
//                player.diem_skien+= 2;
                point = 2;
                break;
            case ConstTask.HARD:
//                rubyReward = ConstTask.RUBY_HARD;
                player.nPoint.nangdong += 3;
//                player.diem_skien +=3;
                point = 3;
                break;
            case ConstTask.VERY_HARD:
//                rubyReward = ConstTask.RUBY_VERY_HARD;
                player.nPoint.nangdong += 4;
                sendItemToPlayer(player, (short) ConstItem.PET_MEO_DEN_DUOI_VANG, 1);
//                player.diem_skien+=4;
                point = 4;
                break;
            case ConstTask.HELL:
//                rubyReward = ConstTask.RUBY_HELL;
                player.nPoint.nangdong += 5;
                sendItemToPlayer(player, (short) ConstItem.PET_MEO_DEN_DUOI_VANG, 2);
//                player.diem_skien+=5;
                point = 5;
                break;
        }
//        player.inventory.addGem(rubyReward);
//        player.inventory.addRuby(rubyReward);
//        Service.getInstance().sendMoney(player);

//        int dragonBall = Util.nextInt(SummonDragon.NGOC_RONG_NOEL[2], SummonDragon.NGOC_RONG_NOEL[6]);
//        sendItemToPlayer(player, (short) dragonBall, 1);
        sendItemToPlayer(player, (short) ConstItem.KEO_NGUOI_TUYET, point);

        Service.getInstance().point2(player);
        // ekko
//        Service.getInstance().sendThongBao(player, "Bạn đã có "
//                + Util.numberToMoney( player.nPoint.nangdong) + "  năng động và"+ Util.numberToMoney( player.diem_skien) + "Điểm sự kiện ngũ hành sơn") ;
        Service.getInstance().sendThongBao(player, "Bạn đã có "
                + Util.numberToMoney(player.nPoint.nangdong) + "  năng động");
    }

    private static void sendItemToPlayer(Player player, short itemID, int quantity) {
        Item item = ItemService.gI().createNewItem(itemID, quantity);
        InventoryService.gI().addItemBag(player, item, 999);
        InventoryService.gI().sendItemBags(player);
        Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name);
    }

//    public void rewardHopQuaActive(Player player) {
//        Item hq = ItemService.gI().createNewItem((short) 1227, 1);
//        InventoryService.gI().addItemBag(player, hq, 9999);
//        Service.getInstance().sendThongBao(player, "Bạn đã nhận được Hộp quà Kích Hoạt");
//    }
}
