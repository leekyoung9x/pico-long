/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.models.npc.Npc;
import nro.models.player.Player;

/**
 *
 * @author Administrator
 */
public class Mr_Popo extends Npc {

    public Mr_Popo(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            this.createOtherMenu(player, ConstNpc.BASE_MENU,
                    "Thượng đế vừa phát hiện 1 loại khí đang âm thầm\n"
                    + "hủy diệt mọi mầm sống trên Trái Đất,\n"
                    + "nó được gọi là Destron Gas.\n"
                    + "Ta sẽ đưa các cậu đến nơi ấy, các cậu sẵn sàng chưa?",
                    "Thông tin\nChi tiết", "Top 100\nBang hội",
                    "Thành tích\nBang", "OK", "Từ chối");
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (player.iDMark.isBaseMenu()) {
                switch (select) {

                }
            }
        }
    }
}
