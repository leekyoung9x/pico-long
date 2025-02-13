/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import java.util.List;

import nro.consts.ConstAction;
import nro.consts.ConstItem;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.manager.EventTurnManager;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.boss.BossManager;
import nro.models.item.Item;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.services.InventoryService;
import nro.services.PlayerService;
import nro.services.Service;

/**
 *
 * @author Arriety
 */
public class Potage extends Npc {

    private final int COST_RUBY = 20_000;

    public Potage(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            if (this.mapId == 140) {
                int turn = EventTurnManager.ManageHumanityChallenge(ConstAction.GET_BY_ID, player.id);

                this.createOtherMenu(player, ConstNpc.BASE_MENU,
                        "Chào mừng người đến với thế giới Tôn Hành Giả gặp Giả Hành Tôn"
                        , "Gọi Boss\nNhân bản\n 5k TVV", "Từ chối");
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            if (this.mapId == 140) {
                int turn = EventTurnManager.ManageHumanityChallenge(ConstAction.GET_BY_ID, player.id);

                if (player.iDMark.isBaseMenu()) {
                    switch (select) {
                        case 0:
//                            if (player.inventory.gem < COST_RUBY) {
//                                this.npcChat(player, "Nhà ngươi không đủ 20k ngọc");
//                                return;
//                            }
                            Item tvv = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
                            if (tvv == null || tvv.quantity < 5_000) {
                                this.npcChat(player, "Nhà ngươi không đủ 1k TVV");
                                return;
                            }
                            MakeCopy(player);
                            this.npcChat(player, "Hắn đã xuất hiện");
                            InventoryService.gI().subQuantityItemsBag(player, tvv, 5_000);
                            InventoryService.gI().sendItemBags(player);
                            Service.getInstance().sendMoney(player);
                            break;
//                        case 1:
//                            Item pi = InventoryService.gI().findItemBagByTemp(player, ConstItem.THOI_VANG_VIP);
//                            if (pi == null || pi.quantity < 5_000) {
//                                this.npcChat(player, "Nhà ngươi không đủ 5k TVV");
//                                return;
//                            }
//                            MakeCopy(player);
//                            this.npcChat(player, "Hắn đã xuất hiện");
//                            InventoryService.gI().subQuantityItemsBag(player, pi, 5_000);
//                            InventoryService.gI().sendItemBags(player);
//                            Service.getInstance().sendMoney(player);
//                            break;

                    }
                }
            }
        }
    }

    private void MakeCopy(Player player) {
//        if (!player.getSession().actived) {
//            Service.getInstance().sendThongBao(player, "Bạn chưa phải là thành viên của Ngọc rồng KIMKO");
//            return;
//        }
        Boss idBoss = BossManager.gI().getBossById(BossFactory.CLONE_NHAN_BAN);
        if (idBoss != null && idBoss.zone != null) {
            this.npcChat(player, "Nhà ngươi hãy tiêu diệt Boss lúc trước ngươi vừa gọi ra ở khu vực :" + idBoss.zone.zoneId);
            return;
        }
        BossData data = getDataBoss(player);
        Boss nhaban = BossFactory.createBossNhanBan(player, data);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                nhaban.typePk = ConstPlayer.PK_ALL;
                PlayerService.gI().sendTypePk(nhaban);
            } catch (Exception e) {
                e.printStackTrace();
            }
            nhaban.setStatus((byte) 3);
        }).start();

    }

    public static BossData getDataBoss(Player pl) {
        List<Skill> skills = pl.playerSkill.skills.stream().filter(s -> s != null && s.point > 0).toList();
        int[][] skillTemp = new int[skills.size()][3];
        for (byte i = 0; i < skills.size(); i++) {
            Skill skill = skills.get(i);
            skillTemp[i][0] = skill.template.id;
            skillTemp[i][1] = skill.point;
            skillTemp[i][2] = skill.coolDown;
        }
        BossData boss = new BossData("Nhân Bản " + pl.name, pl.gender, Boss.DAME_NORMAL, Boss.HP_NORMAL, pl.nPoint.dame,
                new long[][]{{pl.nPoint.hpMax}}, new short[]{pl.getHead(), pl.getBody(), pl.getLeg()}, new short[]{139}, skillTemp, 0
        );
        return boss;
    }
}
