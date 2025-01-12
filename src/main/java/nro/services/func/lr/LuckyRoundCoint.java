/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.services.func.lr;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import nro.consts.Cmd;
import nro.consts.ConstItem;
import nro.consts.ConstItemName;
import nro.models.item.Item;
import nro.models.player.Player;
import nro.server.io.Message;
import nro.services.InventoryService;
import nro.services.RewardService;
import nro.services.Service;
import nro.services.func.LuckyRoundService;

/**
 *
 * @author Arriety
 */
public class LuckyRoundCoint {

    private static LuckyRoundCoint i;

    public static LuckyRoundCoint gI() {
        if (i == null) {
            i = new LuckyRoundCoint();
        }
        return i;
    }

    public void payAndGetStarted(Player player, byte quantity) {
        Item coint = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
        if (coint == null) {
            Service.getInstance().sendThongBao(player, "Bạn không " + ConstItemName.MONEY_UNIT);
            LuckyRoundService.gI().openCrackBallUI(player, LuckyRoundService.USING_GOLD);
            return;
        }
        int x = 1000 * quantity;
        if (coint.quantity < x) {
            Service.getInstance().sendThongBao(player, "Bạn không đủ 1k " + ConstItemName.MONEY_UNIT);
            LuckyRoundService.gI().openCrackBallUI(player, LuckyRoundService.USING_GOLD);
            return;
        }
        if (quantity > InventoryService.gI().getCountEmptyListItem(player.inventory.itemsBoxCrackBall)) {
            LuckyRoundService.gI().openCrackBallUI(player, LuckyRoundService.USING_GOLD);
            Service.getInstance().sendThongBao(player, "Rương phụ đã đầy");
            return;
        }
        InventoryService.gI().subQuantityItemsBag(player, coint, x);
        InventoryService.gI().sendItemBags(player);
        List<Item> list = reward(player, quantity);
        result(player, list);
    }

    public List<Item> reward(Player player, byte quantity) {
        List<Item> list = RewardService.gI().getListItemLuckyRound(player, quantity);
        addItemToBox(player, list);
        return list;
    }

    private void result(Player player, List<Item> items) {
        try {
            Message ms = new Message(Cmd.LUCKY_ROUND);
            DataOutputStream ds = ms.writer();
            ds.writeByte(1);
            ds.writeByte(items.size());
            for (Item item : items) {
                ds.writeShort(item.template.iconID);
            }
            player.sendMessage(ms);
            ms.cleanup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void addItemToBox(Player player, List<Item> items) {
        for (Item item : items) {
            InventoryService.gI().addItemNotUpToUpQuantity(player.inventory.itemsBoxCrackBall, item);
        }
    }
}
