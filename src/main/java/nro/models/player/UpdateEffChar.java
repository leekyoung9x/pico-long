/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.player;

import nro.consts.ConstPet;
import nro.manager.TopWhis;
import nro.models.item.Item;
import nro.server.io.Message;
import nro.services.Service;

/**
 *
 * @author Arrriety
 */
public class UpdateEffChar {

    private static UpdateEffChar i;

    public static UpdateEffChar getInstance() {
        if (i == null) {
            i = new UpdateEffChar();
        }
        return i;
    }

    public void updateEff(Player player) {

        if (player.pet != null && player.pet.typePet == ConstPet.WHIS) {
            Service.getInstance().addEffectChar(player.pet, 58, 1, -1, -1, -1);
        }
        if (player.isPet && player.pet != null) {
            if (player.pet.inventory.itemsBody.size() >= 7) {
                Item nhan = player.pet.inventory.itemsBody.get(7);
                if (nhan != null && nhan.isNotNullItem()  && nhan.template.id == 2110) {
                    Service.getInstance().addEffectChar(player.pet, 57, 1, -1, -1, -1);
                }
            }
        }

        try {
            if (player.isPl()) {
                this.sendTitle(player);
                int playerIdInt = (int) player.id;
                if (player.nPoint.isStone) {
                    Service.getInstance().addEffectChar(player, 58, 1, -1, -1, -1);
                }
                if (TopWhis.TOP_ONE == player.id) {
                    Service.getInstance().addEffectChar(player, 58, 1, -1, -1, -1);
                }
                if (TopWhis.TOP_THREE == player.id) {
                    Service.getInstance().addEffectChar(player, 57, 1, -1, -1, -1);
                }
                if (TopWhis.TOP_TWO == player.id) {
                    Service.getInstance().addEffectChar(player, 56, 1, -1, -1, -1);
                }
                switch (playerIdInt) {
//                    case 38110:
//                        Service.getInstance().addEffectChar(player, 67, 1, -1, -1, 1);
//                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendTitle(Player player) {
        if (player.inventory.itemsBody.size() > 14) {
            Item item = player.inventory.itemsBody.get(14);
            if (item.isNotNullItem()) {
                short part = item.template.part;
                Service.getInstance().sendTitle(player, part);
            }
        }
    }

//    public void sendTitle(Player playerReveice, Player playerInfo) {
//        if (playerReveice == null || playerInfo == null) {
//            return;
//        }
//        try {
//            if (playerInfo.inventory.itemsBody.size() > 14) {
//                Item item = playerInfo.inventory.itemsBody.get(14);
//                if (item.isNotNullItem()) {
//                    short part = item.template.part;
//                    Message me;
//                    try {
//                        me = new Message(-128);
//                        me.writer().writeByte(0);
//                        me.writer().writeInt((int) playerInfo.id);
//                        if (playerInfo.titleitem) {
//                            me.writer().writeShort(part);
//                        }
//                        me.writer().writeShort(part);
//                        me.writer().writeByte(1);
//                        me.writer().writeByte(-1);
//                        me.writer().writeShort(50);
//                        me.writer().writeByte(-1);
//                        me.writer().writeByte(-1);
//                        playerReveice.sendMessage(me);
//                        me.cleanup();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
