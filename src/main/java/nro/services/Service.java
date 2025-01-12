package nro.services;

import nro.consts.Cmd;
import nro.consts.ConstNpc;
import nro.consts.ConstPlayer;
import nro.data.DataGame;
import nro.jdbc.daos.AccountDAO;
import nro.manager.TopManager;
import nro.models.Part;
import nro.models.PartManager;
import nro.models.boss.BossFactory;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.map.ItemMap;
import nro.models.map.Zone;
import nro.models.map.dungeon.zones.ZDungeon;
import nro.models.mob.Mob;
import nro.models.player.Pet;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.power.Caption;
import nro.power.CaptionManager;
import nro.server.AutoMaintenance;
import nro.server.Client;
import nro.server.Manager;
import nro.server.ServerManager;
import nro.server.io.Message;
import nro.server.io.Session;
import nro.services.func.Input;
import nro.utils.FileIO;
import nro.utils.Log;
import nro.utils.TimeUtil;
import nro.utils.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import nro.art.ServerLog;
import nro.consts.ConstAction;
import nro.consts.ConstPet;
import nro.consts.ConstTask;
import nro.event.Event;
import nro.jdbc.DBService;
import nro.manager.EventTurnManager;
import nro.manager.TopToTask;
import nro.models.TopPlayer;
import nro.models.boss.Boss;
import nro.models.boss.BossManager;
import nro.models.boss.list_boss.WhisTop;
import nro.models.npc.specialnpc.BillEgg;
import nro.models.npc.specialnpc.EggLinhThu;
import nro.models.npc.specialnpc.MabuEgg;
import nro.services.func.ChangeMapService;

import static nro.manager.TopPlayerManager.GetTopNap;
import static nro.manager.TopPlayerManager.GetTopPower;

import nro.services.func.TransactionService;

/**
 * @author Administrator
 * @Build Arriety
 */
public class Service {

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void addEffectChar(Player pl, int id, int layer, int loop, int loopcount, int stand) {
        if (!pl.idEffChar.contains(id)) {
            pl.idEffChar.add(id);
        }
        try {
            Message msg = new Message(-128);
            msg.writer().writeByte(0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(id);
            msg.writer().writeByte(layer);
            msg.writer().writeByte(loop);
            msg.writer().writeShort(loopcount);
            msg.writer().writeByte(stand);
            sendMessAllPlayerInMap(pl.zone, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToRegisterScr(Session session) {
        Message message;
        try {
            message = new Message(42);
            message.writer().writeByte(0);
            session.sendMessage(message);
            message.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTextTime(Player pl, byte id, String name, short time) {
        Message msg;
        try {
            msg = new Message(Cmd.MESSAGE_TIME);
            msg.writer().writeByte(id);
            msg.writer().writeUTF(name);
            msg.writer().writeShort(time);
            sendMessAllPlayerInMap(pl.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendMessAllPlayer(Message msg) {
        msg.transformData();
        PlayerService.gI().sendMessageAllPlayer(msg);
    }

    public void sendMessAllPlayerIgnoreMe(Player player, Message msg) {
        msg.transformData();
        PlayerService.gI().sendMessageIgnore(player, msg);
    }

    public void sendPetFollow(Player player, short smallId, int nFrame, int height) {
        Message msg;
        try {
            if (player != null) {
                msg = new Message(31);
                msg.writer().writeInt((int) player.id);
                if (smallId == 0) {
                    msg.writer().writeByte(0);// type 0
                } else {
                    short width = 75;

                    msg.writer().writeByte(1);// type 1
                    msg.writer().writeShort(smallId);
                    msg.writer().writeByte(1);
                    int[] fr = new int[nFrame];

                    for (int i = 0; i < nFrame; i++) {
                        fr[i] = i;
                    }

                    msg.writer().writeByte(fr.length);
                    for (int i = 0; i < fr.length; i++) {
                        msg.writer().writeByte(fr[i]);
                    }
                    msg.writer().writeShort(width);
                    msg.writer().writeShort(height);
                }
                sendMessAllPlayerInMap(player, msg);
                msg.cleanup();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendPetFollow(Player player, short smallId) {
        Message msg;
        try {
            if (player != null) {
                msg = new Message(31);
                msg.writer().writeInt((int) player.id);
                if (smallId == 0) {
                    msg.writer().writeByte(0);// type 0
                } else {
                    short width = 75;
                    short height = 75;

                    if (smallId == 15067) {
                        width = 65;
                        height = 65;
                    }

                    if (smallId == 29002 || smallId == 29312) {
                        width = 40;
                        height = 40;
                    }

                    if (smallId == 29321) {
                        width = 40;
                        height = 35;
                    }

                    msg.writer().writeByte(1);// type 1
                    msg.writer().writeShort(smallId);
                    msg.writer().writeByte(1);
                    int[] fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};

                    if (smallId == 29002 || smallId == 29312) {
                        fr = new int[]{0, 1};
                    }

                    if (smallId == 29321) {
                        fr = new int[]{0, 1};
                    }

                    msg.writer().writeByte(fr.length);
                    for (int i = 0; i < fr.length; i++) {
                        msg.writer().writeByte(fr[i]);
                    }
                    msg.writer().writeShort(width);
                    msg.writer().writeShort(height);
                }
                sendMessAllPlayerInMap(player, msg);
                msg.cleanup();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LinkService(Player player, int iconId, String text, String p2, String caption) {
        try {
            Message msg;
            msg = new Message(-70);
            msg.writer().writeShort(iconId);
            msg.writer().writeUTF(text);
            msg.writer().writeByte(1);
            msg.writer().writeUTF(p2); // link sex
            msg.writer().writeUTF(caption);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPetFollowToMe(Player me, Player pl) {
        Item linhThu = pl.inventory.itemsBody.get(10);
        if (!linhThu.isNotNullItem()) {
            return;
        }
        short smallId = (short) (linhThu.template.iconID - 1);
        Message msg;
        try {
            msg = new Message(31);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(1);
            msg.writer().writeShort(smallId);
            msg.writer().writeByte(1);
            int[] fr = new int[]{0, 1, 2, 3, 4, 5, 6, 7};
            msg.writer().writeByte(fr.length);
            for (int i = 0; i < fr.length; i++) {
                msg.writer().writeByte(fr[i]);
            }
            msg.writer().writeShort(smallId == 15067 ? 65 : 75);
            msg.writer().writeShort(smallId == 15067 ? 65 : 75);
            me.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Mabu14hAttack(Boss mabu, Player plAttack, int x, int y, byte skillId) {
        mabu.isUseSpeacialSkill = true;
        mabu.lastTimeUseSpeacialSkill = System.currentTimeMillis();
        try {
            Message msg = new Message(51);
            msg.writer().writeInt((int) mabu.id);
            msg.writer().writeByte(skillId);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            if (skillId == 1) {
                msg.writer().writeByte(1);
                long dame = plAttack.injured(mabu, (int) (mabu.nPoint.getDameAttack(false) * (skillId == 1 ? 1.5 : 1)), false, false);
                msg.writer().writeInt((int) plAttack.id);
                msg.writer().writeLong(dame);
            } else if (skillId == 0) {
                List<Player> listAttack = mabu.getListPlayerAttack(70);
                msg.writer().writeByte(listAttack.size());
                for (int i = 0; i < listAttack.size(); i++) {
                    Player pl = listAttack.get(i);
                    long dame = pl.injured(mabu, mabu.nPoint.getDameAttack(false), false, false);
                    msg.writer().writeInt((int) pl.id);
                    msg.writer().writeLong(dame);
                }
                listAttack.clear();
            }
            sendMessAllPlayerInMap(mabu.zone, msg);
            mabu.isUseSpeacialSkill = false;
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void sendMabuEat(Player plHold, short... point) {
        Message msg;
        try {
            msg = new Message(52);
            msg.writer().writeByte(1);
            msg.writer().writeInt((int) plHold.id);
            msg.writer().writeShort(point[0]);
            msg.writer().writeShort(point[1]);
            sendMessAllPlayerInMap(plHold.zone, msg);
            plHold.location.x = point[0];
            plHold.location.y = point[1];
            MapService.gI().sendPlayerMove(plHold);
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void removeMabuEat(Player plHold) {
        PlayerService.gI().changeAndSendTypePK(plHold, ConstPlayer.NON_PK);
        plHold.effectSkill.isHoldMabu = false;
        plHold.effectSkill.isTaskHoldMabu = -1;
        Message msg;
        try {
            msg = new Message(52);
            msg.writer().writeByte(0);
            msg.writer().writeInt((int) plHold.id);
            sendMessAllPlayerInMap(plHold.zone, msg);
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void eatPlayer(Boss mabu, Player plHold) {
        mabu.isUseSpeacialSkill = true;
        mabu.lastTimeUseSpeacialSkill = System.currentTimeMillis();
        plHold.effectSkill.isTaskHoldMabu = 1;
        plHold.effectSkill.lastTimeHoldMabu = System.currentTimeMillis();
        try {
            Message msg = new Message(52);
            msg.writer().writeByte(2);
            msg.writer().writeInt((int) mabu.id);
            msg.writer().writeInt((int) plHold.id);
            sendMessAllPlayerInMap(mabu.zone, msg);
            mabu.isUseSpeacialSkill = false;
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void sendPopUpMultiLine(Player pl, int tempID, int avt, String text) {
        Message msg = null;
        try {
            msg = new Message(-218);
            msg.writer().writeShort(tempID);
            msg.writer().writeUTF(text);
            msg.writer().writeShort(avt);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public void sendMessAllPlayerInMap(Zone zone, Message msg) {
        msg.transformData();
        if (zone != null) {
            List<Player> players = zone.getPlayers();
            synchronized (players) {
                for (Player pl : players) {
                    if (pl != null) {
                        pl.sendMessage(msg);
                    }
                }
            }
            msg.cleanup();
        }
    }

    public void sendMessAllPlayerInMap(Player player, Message msg) {
        msg.transformData();
        if (player.zone != null) {
            if (player.zone.map.isMapOffline) {
                if (player.isPet) {
                    ((Pet) player).master.sendMessage(msg);
                } else {
                    if (player instanceof WhisTop) {
                        List<Player> players = player.zone.getPlayers();
                        synchronized (players) {
                            for (Player pl : players) {
                                try {
                                    if (pl != null && pl.id == (long) Util.GetPropertyByName(player, "player_id")) {
                                        pl.sendMessage(msg);
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else {
                        player.sendMessage(msg);
                    }
                }
            } else {
                List<Player> players = player.zone.getPlayers();
                synchronized (players) {
                    for (Player pl : players) {
                        if (pl != null) {
                            pl.sendMessage(msg);
                        }
                    }
                }
                msg.cleanup();
            }
        }
    }

    public void sendMessAnotherNotMeInMap(Player player, Message msg) {
        if (player.zone != null) {
            List<Player> players = player.zone.getPlayers();
            synchronized (players) {
                for (Player pl : players) {
                    if (pl != null && !pl.equals(player)) {
                        pl.sendMessage(msg);
                    }
                }
            }

            msg.cleanup();
        }
    }

    public void sendMessToPlayer(Player player, Message msg, long id) {
        if (player.zone != null) {
            List<Player> players = player.zone.getPlayers();
            synchronized (players) {
                for (Player pl : players) {
                    if (pl != null && pl.id == id) {
                        pl.sendMessage(msg);
                    }
                }
            }

            msg.cleanup();
        }
    }

    public void Send_Info_NV(Player pl) {
        Message msg;
        try {
            msg = Service.getInstance().messageSubCommand((byte) 14);//Cập nhật máu
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeLong(pl.nPoint.hp);
            msg.writer().writeByte(0);//Hiệu ứng Ăn Đậu
            msg.writer().writeLong(pl.nPoint.hpMax);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public void sendInfoPlayerEatPea(Player pl) {
        Message msg;
        try {
            msg = Service.getInstance().messageSubCommand((byte) 14);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeLong(pl.nPoint.hp);
            msg.writer().writeByte(1);
            msg.writer().writeLong(pl.nPoint.hpMax);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public void loginDe(Session session, short second) {
        Message msg;
        try {
            msg = new Message(122);
            msg.writer().writeShort(second);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void resetPoint(Player player, int x, int y) {
        Message msg;
        try {
            player.location.x = x;
            player.location.y = y;
            msg = new Message(46);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void clearMap(Player player) {
        Message msg;
        try {
            msg = new Message(-22);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    //    int test = 0;
    public void chat(Player player, String text) {

        if (player.getSession() != null && player.isAdmin()) {
            if (text.equals("q")) {
                Service.getInstance().releaseCooldownSkill(player);
                return;
            }
//            if (text.equals("next_game_csmm")) {
//                Service.getInstance().sendThongBao(player, "Kết quả con số may mắn tiếp theo là: " + MiniGame.gI().MiniGame_S1.result_next);
//                return;
//            }
            if (text.equals("action")) {
                EventTurnManager.ManageCallDragon(ConstAction.UPDATE_ALL, 0);
                return;
            }
            if (text.equals("lever")) {
                SpecialSkill.gI().incrementExpert(player);
                return;
            }
            if (text.equals("mob")) {
                Service.getInstance().sendThongBao(player, "mob size" + player.skillSpecial.mobsTaget.size());
                return;
            }
            if (text.equals("mini")) {
                Service.getInstance().sendThongBao(player, "mini size: " + player.zone.getMiniPet().size());
                return;
            }
            if (text.equals("00")) {
                for (Skill skill : player.playerSkill.skills) {
                    skill.lastTimeUseThisSkill = 0;
                }
                Service.getInstance().sendTimeSkill(player);
                return;
            }
            if (text.equals("rp")) {
                player.nPoint.setFullHpMp();
                PlayerService.gI().sendInfoHpMp(player);
                return;
            }

            if (text.equals("skillxd")) {
                SkillService.gI().learSkillSpecial(player, Skill.SUPER_ANTOMIC);
                return;
            }
            if (text.equals("skilltd")) {
                SkillService.gI().learSkillSpecial(player, Skill.SUPER_KAME);
                return;
            }
            if (text.equals("skillnm")) {
                SkillService.gI().learSkillSpecial(player, Skill.MAFUBA);
                return;
            }
            if (text.equals("e")) {
                SkillService.gI().sendUpdateSkill(player, Skill.MAFUBA);
                SkillService.gI().sendUpdateSkill(player, Skill.SUPER_KAME);
                SkillService.gI().sendUpdateSkill(player, Skill.SUPER_ANTOMIC);
                return;
            }
            if (text.equals("tele1")) {
                this.sendThongBao(player, "Thực thi lệnh thành công");
                List<Player> playersMap = Client.gI().getPlayers();
                for (Player pl : playersMap) {
                    if (pl != null && !player.equals(pl)) {
                        if (pl.zone != null) {
                            ChangeMapService.gI().changeMap(pl, player.zone, player.location.x, player.location.y);
                        }
                        Service.getInstance().sendThongBao(pl, "|2|Bạn đã được ADMIN gọi đến đây");
                    }
                }
                return;
            }
            if (text.equals("teodeptrai")) {
                List<Long> blackList = new ArrayList<>();
                blackList.add((long) BossFactory.TIEU_DOI_TRUONG);
                blackList.add((long) BossFactory.KUKU);
                blackList.add((long) BossFactory.RAMBO);
                blackList.add((long) BossFactory.MAP_DAU_DINH);
                blackList.add((long) BossFactory.FIDE_DAI_CA_1);
                blackList.add((long) BossFactory.FIDE_DAI_CA_2);
                blackList.add((long) BossFactory.FIDE_DAI_CA_3);
                blackList.add((long) BossFactory.ANDROID_13);
                blackList.add((long) BossFactory.ANDROID_14);
                blackList.add((long) BossFactory.ANDROID_15);
                blackList.add((long) BossFactory.ANDROID_19);
                blackList.add((long) BossFactory.ANDROID_20);
                blackList.add((long) BossFactory.XEN_CON);
                blackList.add((long) BossFactory.XEN_BO_HUNG);
                blackList.add((long) BossFactory.XEN_BO_HUNG_1);
                blackList.add((long) BossFactory.XEN_BO_HUNG_2);
                blackList.add((long) BossFactory.XEN_BO_HUNG_HOAN_THIEN);
                blackList.add((long) BossFactory.TRUNG_UY_XANH_LO);
                blackList.add((long) BossFactory.TRUNG_UY_TRANG);
                blackList.add((long) BossFactory.TRUNG_UD_THEP);
                blackList.add((long) BossFactory.ROBOT_VE_SI_1);
                blackList.add((long) BossFactory.ROBOT_VE_SI_2);
                blackList.add((long) BossFactory.ROBOT_VE_SI_3);
                blackList.add((long) BossFactory.ROBOT_VE_SI_4);
                blackList.add((long) BossFactory.NINJA_AO_TIM);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_1);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_2);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_3);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_4);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_5);
                blackList.add((long) BossFactory.NINJA_AO_TIM_FAKE_6);
                blackList.add((long) BossFactory.YA_CON);
                blackList.add((long) BossFactory.MABU_MAP);
                blackList.add((long) BossFactory.DRABULA_TANG1);
                blackList.add((long) BossFactory.DRABULA_TANG6);
                blackList.add((long) BossFactory.GOKU_TANG5);
                blackList.add((long) BossFactory.BUIBUI_TANG2);
                blackList.add((long) BossFactory.BUIBUI_TANG3);
                blackList.add((long) BossFactory.YACON_TANG4);
                blackList.add((long) BossFactory.PIC);
                blackList.add((long) BossFactory.POC);
                blackList.add((long) BossFactory.KINGKONG);
                blackList.add((long) BossFactory.CHAN_XU);
                blackList.add((long) BossFactory.CHA_PA);
                blackList.add((long) BossFactory.YACON_TANG4);
                blackList.add((long) BossFactory.PIC);
                blackList.add((long) BossFactory.POC);
                blackList.add((long) BossFactory.KINGKONG);
                blackList.add((long) BossFactory.CHAN_XU);
                blackList.add((long) BossFactory.CHA_PA);
                blackList.add((long) BossFactory.JACKY_CHUN);
                blackList.add((long) BossFactory.LIU_LIU);
                blackList.add((long) BossFactory.O_DO);
                blackList.add((long) BossFactory.PON_PUT);
                blackList.add((long) BossFactory.SOI_HEC_QUYN);
                blackList.add((long) BossFactory.TAU_PAY_PAY);
                blackList.add((long) BossFactory.THIEN_XIN_HANG);
                blackList.add((long) BossFactory.THIEN_XIN_HANG_CLONE);
                blackList.add((long) BossFactory.XINBATO);
                blackList.add((long) BossFactory.YAMCHA);
                blackList.add((long) BossFactory.BILL);
                blackList.add((long) BossFactory.FIDE_DAI_CA_1);
                blackList.add((long) BossFactory.XEN_BO_HUNG_HOAN_THIEN);
                blackList.add((long) BossFactory.WHIS);
//                System.out.println(blackList.toArray().toString());
                List<Boss> bossesToRespawn = BossManager.BOSSES_IN_GAME.stream()
                        .filter(boss -> boss != null && (boss.isDie() || boss.zone == null) && !blackList.contains(boss.id))
                        .collect(Collectors.toList());

                bossesToRespawn.forEach(boss -> {
                    boss.respawnBoss();
                    System.out.println("Boss " + boss.name + " sống dậy");
                });
            }
            if (text.equals("hsboss")) {
                // Danh sách các boss cần hồi sinh (danh sách đen)
                List<Long> blackList = new ArrayList<>();
                blackList.add((long) BossFactory.NGOK);


                // Lọc các boss cần hồi sinh từ danh sách đen
                List<Boss> bossesToRespawn = BossManager.BOSSES_IN_GAME.stream()
                        .filter(boss -> boss != null && (boss.isDie() || boss.zone == null) && blackList.contains(boss.id))
                        .collect(Collectors.toList());

                // Hồi sinh các boss và in thông báo
                bossesToRespawn.forEach(boss -> {
                    boss.respawnBoss();
                    System.out.println("Boss " + boss.name + " sống dậy");
                });
            }
            if (text.startsWith("dm")) {
                try {
                    String[] parts = text.split(" ");
                    if (parts.length >= 3) {
                        int nframe = Integer.parseInt(parts[1]);
                        int height = Integer.parseInt(parts[2]);
                        this.sendPetFollow(player, (short) 29002, nframe, height);
                        return;
                    } else {
                        Service.getInstance().sendThongBao(player, "Lỗi");
                        return;
                    }
                } catch (NumberFormatException e) {
                }
                return;

            }

            if (text.equals("killpl")) {
                this.sendThongBao(player, "Xiên toàn server thành công");
                List<Player> playersMap = Client.gI().getPlayers();
                for (Player pl : playersMap) {
                    if (pl != null && !player.equals(pl)) {
                        pl.isDie();
                        pl.setDie(player);
                        PlayerService.gI().sendInfoHpMpMoney(pl);
                        Service.getInstance().Send_Info_NV(pl);
                        Service.getInstance().sendThongBao(pl, "|2|ADMIN ĐÃ TÀN SÁT CẢ SERVER");
                    }
                }
                return;
            }
            if (text.equals("pet")) {
                PetService.gI().createWhisPet(player, player.gender);
                return;
            }
//            if (text.equals("uplv")) {
//                player.pet.setLever(player.pet.getLever() + 1);
//                this.sendThongBao(player, "Pet của bạn đã lên LV: " + player.pet.getLever());
//                return;
//            }
            if (text.equals("rsdt")) {
                player.clan.doanhTrai.finish();
                return;
            }
            if (text.equals("doanhtraiteam")) {
                DoanhTraiService.gI().openDoanhTrai(player, true);
                return;
            }
            if (text.equals("hsbillcon")) {
                for (Boss boss : BossManager.BOSSES_IN_GAME) {
                    if (boss != null && (boss.isDie() || boss.zone == null) && boss.id == BossFactory.BILLCON) {
                        boss.respawnBoss();
                    }
                }
                return;
            }
            if (text.equals("reloadaura")) {
                Manager.ReloadSpecialAura();
                return;
            }
            if (text.equals("reloadopenbox")) {
                Manager.ReloadOpenBox();
                return;
            }
            if (text.startsWith("hs")) {
                try {
                    // Lấy phần id từ chuỗi text, bỏ đi 2 ký tự đầu là "hs"
                    long id = Long.parseLong(text.substring(2));

                    // Chuyển id thành số âm
                    id = -id;

                    // Duyệt qua danh sách các boss trong game
                    for (Boss boss : BossManager.BOSSES_IN_GAME) {
                        if (boss != null && (boss.isDie() || boss.zone == null) && boss.id == id) {
                            boss.respawnBoss();
                        }
                    }
                } catch (NumberFormatException e) {
                    // Trường hợp người dùng nhập không đúng định dạng số
                    Service.getInstance().sendThongBao(player, "ID không hợp lệ");
                }
                return;
            }
            if (text.equals("logskill")) {
                Service.getInstance().sendThongBao(player, player.playerSkill.skillSelect.coolDown
                        + "");
                return;
            }
            if (text.equals("linhthu")) {
                sendThongBao(player, "Khởi Tạo Linhthu Thành Công: " + (player.egglinhthu != null));
                EggLinhThu.createEggLinhThu(player);
                return;
            }
            if (text.equals("egg")) {
                MabuEgg.createMabuEgg(player);
                return;
            }
            if (text.equals("bill")) {
                sendThongBao(player, "Khởi Tạo cc Thành Công: " + (player.billEgg != null));
                BillEgg.createBillEgg(player);
                return;
            }
            if (text.equals("tb")) {
                PetService.gI().changebilconPet(player, 0);
                return;
            }
            if (text.equals("tt")) {
                PetService.gI().changeNormalPet(player, 0);
                return;
            }
            if (text.equals("doanhtrai")) {
                DoanhTraiService.gI().openDoanhTrai(player);
                return;
            }
            if (text.equals("maintain")) {
                new Thread(() -> new AutoMaintenance().execute()).start();
            }
            if (text.equals("boss")) {
                BossManager.gI().showListBoss(player);
                return;
            }
            if (text.startsWith("i")) {
                String[] parts = text.split(" ");
                if (parts.length >= 3) {
                    short id = Short.parseShort(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    Item item = ItemService.gI().createNewItem(id, quantity);
                    InventoryService.gI().addItemBag(player, item, 99999);
                    InventoryService.gI().sendItemBags(player);
                    Service.getInstance().sendThongBao(player, "Bạn nhận được " + item.template.name + " số lượng: " + quantity);
                    return;
                } else {
                    Service.getInstance().sendThongBao(player, "Lỗi");
                    return;
                }
            }
            if (text.startsWith("buff")) {
                String[] parts = text.split(" ");
                if (parts.length >= 3) {
                    int type = Integer.parseInt(parts[1]);
                    int param = Integer.parseInt(parts[2]);
                    String said = "";
                    switch (type) {
                        case 1:
                            player.nPoint.hpg += param;
                            said = "Hp";
                            break;
                        case 2:
                            player.nPoint.mpg += param;
                            said = "Ki";
                            break;
                        case 3:
                            player.nPoint.dameg += param;
                            said = "Dame";
                            break;
                        case 4:
                            player.nPoint.defg += param;
                            said = "Giap";
                            break;
                        case 5:
                            player.nPoint.critg += param;
                            said = "Chi Mang";
                            break;
                    }
                    Service.getInstance().point(player);
                    Service.getInstance().sendThongBao(player, "Buff " + said + " param:" + Util.numberToMoney(param));
                    return;
                } else {
                    Service.getInstance().sendThongBao(player, "Lỗi");
                    return;
                }
            }
            if (text.startsWith("task")) {
                String[] parts = text.split(" ");
                if (parts.length >= 2) {
                    short id = Short.parseShort(parts[1]);
                    player.playerTask.taskMain.id = id;
                    TaskService.gI().sendNextTaskMain(player);
                    return;
                } else {
                    Service.getInstance().sendThongBao(player, "Lỗi");
                    return;
                }
            }
            if (text.equals("client")) {
                Client.gI().show(player);
                return;
            }
            if (text.equals("put")) {
                Input.gI().createFormSenditem1(player);
                return;
            }
            if (text.equals("beodz")) {
                Manager.reloadShop();
                this.sendThongBao(player, "Load Shop Success");
                return;
            }
            if (text.equals("admin")) {
                NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_ADMIN, -1,
                        "Quản trị admin Kimkan \n"
                                + "|1|Online: " + Client.gI().getPlayers().size() + "\n"
                                + "|4|Thread: " + Thread.activeCount() + "\n",
                        "Ngọc rồng", "Log check", "Bảo trì", "Tìm kiếm\nngười chơi", "Call\nBoss", "Đóng");
                return;
            } else if (text.equals("toado")) {
                NpcService.gI().createMenuConMeo(player, ConstNpc.COORDINATES, 11525, "Tọa độ: " + player.location.x + " - " + player.location.y + "\nMap: " + player.zone.map.mapId + " - " + player.zone.zoneId);
                return;
            } else if (text.equals("tn")) {
                Input.gI().createFormTangGem(player);
                return;
            } else if (text.equals("it")) {
                Input.gI().createFormAddItem(player);
                return;
            } else if (text.startsWith("upp")) {
                try {
                    long power = Long.parseLong(text.replaceAll("upp", ""));
                    addSMTN(player.pet, (byte) 2, power, false);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.startsWith("up")) {
                try {
                    long power = Long.parseLong(text.replaceAll("up", ""));
                    addSMTN(player, (byte) 2, power, false);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (text.startsWith("m ")) {
                try {
                    int mapId = Integer.parseInt(text.replace("m ", ""));
                    Zone zone = MapService.gI().getZoneJoinByMapIdAndZoneId(player, mapId, 0);
                    if (zone != null) {
                        player.location.x = 500;
                        player.location.y = zone.map.yPhysicInTop(500, 100);
                        MapService.gI().goToMap(player, zone);
                        Service.getInstance().clearMap(player);
                        zone.mapInfo(player);
                        player.zone.loadAnotherToMe(player);
                        player.zone.load_Me_To_Another(player);
                    }
                    return;
                } catch (Exception e) {
//                    e.printStackTrace();;
                }
            }
        }
        if (text.startsWith("ten con la ")) {
            PetService.gI().changeNamePet(player, text.replaceAll("ten con la ", ""));
        }
        if (text.equals("thoatket")) {

            ChangeMapService.gI().changeMapBySpaceShip(player, 5 + player.gender, -1, -1);

        }
        if (text.equals("fixlag")) {
            Service.getInstance().player(player);
            Service.getInstance().Send_Caitrang(player);
        }
        if (player.pet != null) {

            if (text.equals("di theo") || text.equals("follow")) {
                player.pet.changeStatus(Pet.FOLLOW);
            } else if (text.equals("bao ve") || text.equals("protect")) {
                player.pet.changeStatus(Pet.PROTECT);
            } else if (text.equals("tan cong") || text.equals("attack")) {
                player.pet.changeStatus(Pet.ATTACK);
            } else if (text.equals("ve nha") || text.equals("go home")) {
                player.pet.changeStatus(Pet.GOHOME);
            } else if (text.equals("bien hinh")) {
                player.pet.transform();
            } else if (text.equals("tele")) {
                Item nhan = player.pet.inventory.itemsBody.get(7);
                if (nhan.isNotNullItem() && nhan.template.id == 2110) {
                    ChangeMapService.gI().changeMapBySpaceShip(player, 160, -1, Util.nextInt(60, 200));
                } else {
                    Service.getInstance().sendThongBao(player, "|2|Cần mặc nhẫn thời không sai lệch cho đệ ");
                }
            }
        }

        if (text.length() > 100) {
            text = text.substring(0, 100);
        }
        chatMap(player, text);
    }

    public static Service gI() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void showYourNumber(Player player, String Number, String result, String finish, int type) {
        Message msg = null;
        try {
            msg = new Message(-126);
            msg.writer().writeByte(type); // 1 = RESET GAME | 0 = SHOW CON SỐ CỦA PLAYER
            if (type == 0) {
                msg.writer().writeUTF(Number);
            } else if (type == 1) {
                msg.writer().writeByte(type);
                msg.writer().writeUTF(result); //
                msg.writer().writeUTF(finish);
            }
            player.sendMessage(msg);
        } catch (Exception e) {
        } finally {
            if (msg != null) {
                msg.cleanup();
                msg = null;
            }
        }
    }

    public void removeTitle(Player player) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(2);
            me.writer().writeInt((int) player.id);
            player.getSession().sendMessage(me);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();
            if (player.titleitem) {
                Service.getInstance().sendTitle(player, player.partDanhHieu);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTitle(Player playerReveice, Player playerInfo) {
        if (playerReveice == null || playerInfo == null) {
            return;
        }
        try {
            if (playerInfo.inventory.itemsBody.size() > 14) {
                Item item = playerInfo.inventory.itemsBody.get(14);
                if (item.isNotNullItem()) {
                    short part = item.template.part;
                    Message me;
                    try {
                        me = new Message(-128);
                        me.writer().writeByte(0);
                        me.writer().writeInt((int) playerInfo.id);
                        if (playerInfo.titleitem) {
                            me.writer().writeShort(part);
                        }
                        me.writer().writeShort(part);
                        me.writer().writeByte(1);
                        me.writer().writeByte(-1);
                        me.writer().writeShort(50);
                        me.writer().writeByte(-1);
                        me.writer().writeByte(-1);
                        playerReveice.sendMessage(me);
                        me.cleanup();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTitle(Player player) {
        if (player.inventory.itemsBody.size() > 14) {
            Item item = player.inventory.itemsBody.get(14);
            if (item.isNotNullItem()) {
                sendTitle(player, item.template.part);
            }
        }
    }

    public void sendTitle(Player player, int part) {
        Message me;
        try {
            me = new Message(-128);
            me.writer().writeByte(0);
            me.writer().writeInt((int) player.id);
            if (player.titleitem) {
                me.writer().writeShort(part);
            }
            me.writer().writeShort(part);
            me.writer().writeByte(1);
            me.writer().writeByte(-1);
            me.writer().writeShort(50);
            me.writer().writeByte(-1);
            me.writer().writeByte(-1);
            this.sendMessAllPlayerInMap(player, me);
            me.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chatMap(Player player, String text) {
        Message msg;
        try {
            msg = new Message(44);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeUTF(text);
            sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void regisAccount(Session session, Message _msg) {
        try {
            PreparedStatement ps = null;
            int key = -1;
            int sl = 0;
            String day = _msg.reader().readUTF();
            String month = _msg.reader().readUTF();
            String year = _msg.reader().readUTF();
            String address = _msg.reader().readUTF();
            String cmnd = _msg.reader().readUTF();
            String dayCmnd = _msg.reader().readUTF();
            String noiCapCmnd = _msg.reader().readUTF();
            String user = _msg.reader().readUTF();
            String pass = _msg.reader().readUTF();
            if (!(user.length() >= 4 && user.length() <= 18)) {
                sendThongBaoOK(session, "Tài khoản phải có độ dài 4-18 ký tự");
                return;
            }
            if (!(pass.length() >= 6 && pass.length() <= 18)) {
                sendThongBaoOK(session, "Mật khẩu phải có độ dài 6-18 ký tự");
                return;
            }
            try (Connection con = DBService.gI().getConnectionForGetPlayer();) {
                ps = con.prepareStatement("SELECT COUNT(1) AS sl FROM account WHERE ip_address = ?");
                ps.setString(1, session.ipAddress);
                ResultSet rset = ps.executeQuery();
                rset.next();
                sl = rset.getInt("sl");
                if (sl > 5) {
                    sendThongBaoOK(session, "Số lượng account tối đa có thể đăng ký cho 1 Ip là 5");
                } else {
                    ps = con.prepareStatement("select * from account where username = ?");
                    ps.setString(1, user);
                    if (ps.executeQuery().next()) {
                        sendThongBaoOK(session, "Tạo thất bại do tài khoản đã tồn tại");
                    } else {
                        ps = con.prepareStatement("insert into account(username,password) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, user);
                        ps.setString(2, pass);
                        ps.executeUpdate();
                        ResultSet rs = ps.getGeneratedKeys();
                        rs.next();
                        key = rs.getInt(1);
                        sendThongBaoOK(session, "Tạo tài khoản thành công!");
                    }
                }
            } catch (Exception e) {
                Log.error(AccountDAO.class, e);
            } finally {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            sendThongBaoOK(session, "Tạo tài khoản thất bại");
        }
    }

    public void chatJustForMe(Player me, Player plChat, String text) {
        Message msg;
        try {
            msg = new Message(44);
            msg.writer().writeInt((int) plChat.id);
            msg.writer().writeUTF(text);
            me.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void point(Player player) {
        player.nPoint.calPoint();
        Send_Info_NV(player);
        if (!player.isPet && !player.isBoss) {
            Message msg;
            try {
                msg = new Message(-42);

                msg.writer().writeInt((int) player.nPoint.hpg);
                msg.writer().writeInt((int) player.nPoint.mpg);
                msg.writer().writeInt((int) player.nPoint.dameg);
                msg.writer().writeLong(player.nPoint.hpMax);// hp full
                msg.writer().writeLong(player.nPoint.mpMax);// mp full
                msg.writer().writeLong(player.nPoint.hp);// hp
                msg.writer().writeLong(player.nPoint.mp);// mp
                msg.writer().writeByte(player.nPoint.speed);// speed
                msg.writer().writeByte(20);
                msg.writer().writeByte(20);
                msg.writer().writeByte(1);
                msg.writer().writeLong(player.nPoint.dame);// dam base
                msg.writer().writeLong(player.nPoint.def);// def full
                msg.writer().writeByte(player.nPoint.crit);// crit full
                msg.writer().writeLong(player.nPoint.tiemNang);
                msg.writer().writeShort(100);
                msg.writer().writeInt((int)player.nPoint.defg);
                msg.writer().writeByte(player.nPoint.critg);
                player.sendMessage(msg);
                msg.cleanup();
            } catch (Exception e) {
                Log.error(Service.class, e);
            }
        }
    }

    public void point2(Player player) {
        player.nPoint.calPoint();
//        Send_Info_NV(player);
        if (!player.isPet && !player.isBoss) {
            Message msg;
            try {
                msg = new Message(-97);

                msg.writer().writeInt(player.nPoint.nangdong);

                player.sendMessage(msg);
                msg.cleanup();
            } catch (Exception e) {
                Log.error(Service.class, e);
            }
        }
    }

    public void player(Player pl) {
        if (pl == null) {
            return;
        }
        Message msg;
        try {
            msg = messageSubCommand((byte) 0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(pl.playerTask.taskMain.id);
            msg.writer().writeByte(pl.gender);
            msg.writer().writeShort(pl.head);
            msg.writer().writeUTF(pl.name /*+ "[" + pl.id + "]"*/);
            msg.writer().writeByte(0); //cPK
            msg.writer().writeByte(pl.typePk);
            msg.writer().writeLong(pl.nPoint.power);
            msg.writer().writeShort(0);
            msg.writer().writeShort(0);
            msg.writer().writeByte(pl.gender);
            //--------skill---------

            ArrayList<Skill> skills = (ArrayList<Skill>) pl.playerSkill.skills;

            msg.writer().writeByte(pl.playerSkill.getSizeSkill());

            for (Skill skill : skills) {
                if (skill.skillId != -1) {
                    msg.writer().writeShort(skill.skillId);
                }
            }

            //---vang---luong--luongKhoa
            long gold = pl.inventory.getGoldDisplay();
            if (pl.isVersionAbove(214)) {
                msg.writer().writeLong(gold);
            } else {
                msg.writer().writeInt((int) gold);
            }
            msg.writer().writeInt(pl.inventory.ruby);
            msg.writer().writeInt(pl.inventory.gem);

            //--------itemBody---------
            ArrayList<Item> itemsBody = (ArrayList<Item>) pl.inventory.itemsBody;
            msg.writer().writeByte(itemsBody.size());
            for (Item item : itemsBody) {
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.getDisplayOptions();
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeShort(itemOption.optionTemplate.id);
                        msg.writer().writeInt(itemOption.param);
                    }
                }

            }

            //--------itemBag---------
            ArrayList<Item> itemsBag = (ArrayList<Item>) pl.inventory.itemsBag;
            msg.writer().writeByte(itemsBag.size());
            for (int i = 0; i < itemsBag.size(); i++) {
                Item item = itemsBag.get(i);
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.getDisplayOptions();
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeShort(itemOption.optionTemplate.id);
                        msg.writer().writeInt(itemOption.param);
                    }
                }

            }

            //--------itemBox---------
            ArrayList<Item> itemsBox = (ArrayList<Item>) pl.inventory.itemsBox;
            msg.writer().writeByte(itemsBox.size());
            for (int i = 0; i < itemsBox.size(); i++) {
                Item item = itemsBox.get(i);
                if (!item.isNotNullItem()) {
                    msg.writer().writeShort(-1);
                } else {
                    msg.writer().writeShort(item.template.id);
                    msg.writer().writeInt(item.quantity);
                    msg.writer().writeUTF(item.getInfo());
                    msg.writer().writeUTF(item.getContent());
                    List<ItemOption> itemOptions = item.getDisplayOptions();
                    msg.writer().writeByte(itemOptions.size());
                    for (ItemOption itemOption : itemOptions) {
                        msg.writer().writeShort(itemOption.optionTemplate.id);
                        msg.writer().writeInt(itemOption.param);
                    }
                }
            }
            //-----------------
            DataGame.sendHeadAvatar(msg);
            //-----------------
            msg.writer().writeShort(514); //char info id - con chim thông báo
            msg.writer().writeShort(515); //char info id
            msg.writer().writeShort(537); //char info id
            msg.writer().writeByte(pl.fusion.typeFusion != ConstPlayer.NON_FUSION ? 1 : 0); //nhập thể
            msg.writer().writeInt(333); //deltatime
            msg.writer().writeByte(pl.isNewMember ? 1 : 0); //is new member

            msg.writer().writeShort(pl.getAura()); //idauraeff
            msg.writer().writeByte(pl.getEffFront());
            msg.writer().writeShort(1); // idHat
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message messageNotLogin(byte command) throws IOException {
        Message ms = new Message(-29);
        ms.writer().writeByte(command);
        return ms;
    }

    public Message messageNotMap(byte command) throws IOException {
        Message ms = new Message(-28);
        ms.writer().writeByte(command);
        return ms;
    }

    public Message messageSubCommand(byte command) throws IOException {
        Message ms = new Message(-30);
        ms.writer().writeByte(command);
        return ms;
    }

    public void addSMTN(Player player, byte type, long param, boolean isOri) {
        if (player.nPoint.power > player.nPoint.getPowerLimit()) {
            return;
        }

        if (player.isPet) {
            player.nPoint.powerUp(param);
            player.nPoint.tiemNangUp(param);
            Player master = ((Pet) player).master;
            param = master.nPoint.calSubTNSM(param);
            master.nPoint.tiemNangUp(param);
            addSMTN(master, type, param, true);
        } else {
            switch (type) {
                case 1:
                    player.nPoint.tiemNangUp(param);
                    break;
                case 2:
                    player.nPoint.powerUp(param);
                    player.nPoint.tiemNangUp(param);
                    break;
                default:
                    player.nPoint.powerUp(param);
                    break;
            }
            PlayerService.gI().sendTNSM(player, type, param);
            if (isOri && player.clan != null) {
                player.clan.addSMTNClan(player, param);
            }
        }
    }

    public String get_HanhTinh(int hanhtinh) {
        switch (hanhtinh) {
            case 0:
                return "Trái Đất";
            case 1:
                return "Xayda";
            case 2:
                return "Namec";
            default:
                return "";
        }
    }

    public String getCurrStrLevel(Player pl) {
        long sucmanh = pl.nPoint.power;
        if (sucmanh < 3000) {
            return "Tân thủ";
        } else if (sucmanh < 15000) {
            return "Tập sự sơ cấp";
        } else if (sucmanh < 40000) {
            return "Tập sự trung cấp";
        } else if (sucmanh < 90000) {
            return "Tập sự cao cấp";
        } else if (sucmanh < 170000) {
            return "Tân binh";
        } else if (sucmanh < 340000) {
            return "Chiến binh";
        } else if (sucmanh < 700000) {
            return "Chiến binh cao cấp";
        } else if (sucmanh < 1500000) {
            return "Vệ binh";
        } else if (sucmanh < 15000000) {
            return "Vệ binh hoàng gia";
        } else if (sucmanh < 150000000) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 1";
        } else if (sucmanh < 1500000000) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 2";
        } else if (sucmanh < 5000000000L) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 3";
        } else if (sucmanh < 10000000000L) {
            return "Siêu " + get_HanhTinh(pl.gender) + " cấp 4";
        } else if (sucmanh < 40000000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 1";
        } else if (sucmanh < 50010000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 2";
        } else if (sucmanh < 60010000000L) {
            return "Thần " + get_HanhTinh(pl.gender) + " cấp 3";
        } else if (sucmanh < 70010000000L) {
            return "Giới Vương Thần cấp 11";
        } else if (sucmanh < 80010000000L) {
            return "Giới Vương Thần cấp 2";
        } else if (sucmanh < 100010000000L) {
            return "Giới Vương Thần cấp 3";
        } else if (sucmanh < 11100010000000L) {
            return "Thần Huỷ Diệt cấp 1";
        }
        return "Thần Huỷ Diệt cấp 2";
    }

    public void hsChar(Player pl, long hp, long mp) {
        Message msg;
        try {
            pl.setJustRevivaled();
            pl.nPoint.setHp(hp);
            pl.nPoint.setMp(mp);
            if (!pl.isPet) {
                msg = new Message(-16);
                pl.sendMessage(msg);
                msg.cleanup();
                PlayerService.gI().sendInfoHpMpMoney(pl);
            }
            msg = messageSubCommand((byte) 15);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeLong(hp);
            msg.writer().writeLong(mp);
            msg.writer().writeShort(pl.location.x);
            msg.writer().writeShort(pl.location.y);
            sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();
            Send_Info_NV(pl);
            PlayerService.gI().sendInfoHpMp(pl);
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void charDie(Player pl) {
        Message msg;
        try {
            if (!pl.isPet) {
                msg = new Message(-17);
                msg.writer().writeByte((int) pl.id);
                msg.writer().writeShort(pl.location.x);
                msg.writer().writeShort(pl.location.y);
                pl.sendMessage(msg);
                msg.cleanup();
            } else {
                ((Pet) pl).lastTimeDie = System.currentTimeMillis();
            }

            msg = new Message(-8);
            msg.writer().writeShort((int) pl.id);
            msg.writer().writeByte(0); //cpk
            msg.writer().writeShort(pl.location.x);
            msg.writer().writeShort(pl.location.y);
            sendMessAnotherNotMeInMap(pl, msg);
            msg.cleanup();

//            Send_Info_NV(pl);
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void attackMob(Player pl, int mobId) {
        if (pl != null && pl.zone != null) {
            for (Mob mob : pl.zone.mobs) {
                if (mob.id == mobId) {
                    SkillService.gI().useSkill(pl, null, mob);
                    break;
                }
            }
        }
    }

    public void sendEffectHideNPC(Player pl, byte npcID, byte status) {
        Message msg;
        try {
            msg = new Message(-73);
            msg.writer().writeByte(npcID);
            msg.writer().writeByte(status); // 0 = hide
            Service.getInstance().sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffectHideNPCPlayer(Player pl, byte npcID, byte status) {
        Message msg;
        try {
            msg = new Message(-73);
            msg.writer().writeByte(npcID);
            msg.writer().writeByte(status); // 0 = hide
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Send_Caitrang(Player player) {
        if (player != null) {
            if (player.pet != null) {
                this.sendFlagBag(player.pet);
            }
            Message msg;
            try {
                msg = new Message(-90);
                msg.writer().writeByte(1);// check type
                msg.writer().writeInt((int) player.id); //id player
                short head = player.getHead();
                short body = player.getBody();
                short leg = player.getLeg();

                msg.writer().writeShort(head);//set head
                msg.writer().writeShort(body);//setbody
                msg.writer().writeShort(leg);//set leg
                msg.writer().writeByte(player.effectSkill.isMonkey ? 1 : 0);//set khỉ
                sendMessAllPlayerInMap(player, msg);
                msg.cleanup();
            } catch (Exception e) {
                Log.error(Service.class, e);
            }
        }
    }

    public void setNotMonkey(Player player) {
        Message msg;
        try {
            msg = new Message(-90);
            msg.writer().writeByte(-1);
            msg.writer().writeInt((int) player.id);
            Service.getInstance().sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void sendFlagBag(Player pl) {
        Message msg;
        try {
            msg = new Message(-64);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(pl.getFlagBag());
            sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendThongBaoOK(Player pl, String text) {
        if (pl.isPet) {
            return;
        }
        Message msg;
        try {
            msg = new Message(-26);
            msg.writer().writeUTF(text);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendThongBaoOK(Session session, String text) {
        Message msg;
        try {
            msg = new Message(-26);
            msg.writer().writeUTF(text);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendThongBaoAllPlayer(String thongBao) {
        Message msg;
        try {
            msg = new Message(-25);
            msg.writer().writeUTF(thongBao);
            this.sendMessAllPlayer(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendBigMessage(Player player, int iconId, String text) {
        try {
            Message msg;
            msg = new Message(-70);
            msg.writer().writeShort(iconId);
            msg.writer().writeUTF(text);
            msg.writer().writeByte(0);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void sendThongBaoFromAdmin(Player player, String text) {
        sendBigMessage(player, 1139, text);
    }

    public void sendBigMessAllPlayer(int iconId, String text) {
        try {
            Message msg;
            msg = new Message(-70);
            msg.writer().writeShort(iconId);
            msg.writer().writeUTF(text);
            msg.writer().writeByte(0);
            this.sendMessAllPlayer(msg);
            msg.cleanup();
        } catch (IOException e) {
        }
    }

    public void sendThongBao(Player pl, String thongBao) {
        Message msg;
        try {
            msg = new Message(-25);
            msg.writer().writeUTF(thongBao);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendThongBaoWeb(Player pl, String thongBao) {
        Message msg;
        try {
            msg = new Message(99);
            msg.writer().writeUTF(thongBao);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendMoney(Player pl) {
        Message msg;
        try {
            msg = new Message(6);
            long gold = pl.inventory.getGoldDisplay();
            if (pl.isVersionAbove(214)) {
                msg.writer().writeLong(gold);
            } else {
                msg.writer().writeInt((int) gold);
            }
            msg.writer().writeInt(pl.inventory.gem);
            msg.writer().writeInt(pl.inventory.ruby);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }


    public void sendToAntherMePickItem(Player player, int itemMapId) {
        Message msg;
        try {
            msg = new Message(-19);
            msg.writer().writeShort(itemMapId);
            msg.writer().writeInt((int) player.id);
            sendMessAnotherNotMeInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {

        }
    }

    public boolean isItemMoney(int type) {
        return type == 9 || type == 10 || type == 34;
    }

    public void useSkillNotFocus(Player pl, Message m) throws IOException {
        byte status = m.reader().readByte();
        if (status == 20) {
            byte SkillID = m.reader().readByte();
            short xPlayer = m.reader().readShort();
            short yPlayer = m.reader().readShort();
            byte dir = m.reader().readByte();
            short x = m.reader().readShort();
            short y = m.reader().readShort();
            SkillService.gI().useSKillNotFocus(pl, SkillID, xPlayer, yPlayer, dir, x, y);
        } else {
            SkillService.gI().useSkill(pl, null, null);
        }
    }

    public void chatGlobal(Player pl, String text) {
        if (pl.inventory.getGem() >= 5) {
            if (pl.isAdmin() || Util.canDoWithTime(pl.lastTimeChatGlobal, 180000)) {
                if (pl.isAdmin() || pl.nPoint.power > 2000000000) {
                    pl.inventory.subGem(5);
                    sendMoney(pl);
                    pl.lastTimeChatGlobal = System.currentTimeMillis();
                    Message msg;
                    try {
                        msg = new Message(92);
                        msg.writer().writeUTF(pl.name);
                        msg.writer().writeUTF("|5|" + text);
                        msg.writer().writeInt((int) pl.id);
                        msg.writer().writeShort(pl.getHead());
                        msg.writer().writeShort(pl.getBody());
                        msg.writer().writeShort(pl.getFlagBag()); //bag
                        msg.writer().writeShort(pl.getLeg());
                        msg.writer().writeByte(0);
                        sendMessAllPlayer(msg);
                        msg.cleanup();
                    } catch (Exception e) {
                    }
                } else {
                    sendThongBao(pl, "Sức mạnh phải ít nhất 2tỷ mới có thể chat thế giới");
                }
            } else {
                sendThongBao(pl, "Không thể chat thế giới lúc này, vui lòng đợi " + TimeUtil.getTimeLeft(pl.lastTimeChatGlobal, 120));
            }
        } else {
            sendThongBao(pl, "Không đủ ngọc chat thế giới");
        }
    }

    private int tiLeXanhDo = 3;

    public int xanhToDo(int n) {
        return n * tiLeXanhDo;
    }

    public int doToXanh(int n) {
        return (int) n / tiLeXanhDo;
    }

    public static final int[] flagTempId = {363, 364, 365, 366, 367, 368, 369, 370, 371, 519, 520, 747};
    public static final int[] flagIconId = {2761, 2330, 2323, 2327, 2326, 2324, 2329, 2328, 2331, 4386, 4385, 2325};

    public void openFlagUI(Player pl) {
        Message msg;
        try {
            msg = new Message(-103);
            msg.writer().writeByte(0);
            msg.writer().writeByte(flagTempId.length);
            for (int i = 0; i < flagTempId.length; i++) {
                msg.writer().writeShort(flagTempId[i]);
                msg.writer().writeByte(1);
                switch (flagTempId[i]) {
                    case 363:
                        msg.writer().writeByte(73);
                        msg.writer().writeShort(0);
                        break;
                    case 371:
                        msg.writer().writeByte(88);
                        msg.writer().writeShort(10);
                        break;
                    default:
                        msg.writer().writeByte(88);
                        msg.writer().writeShort(5);
                        break;
                }
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void changeFlag(Player pl, int index) {

        Message msg;
        try {
            pl.cFlag = (byte) index;
            msg = new Message(-103);
            msg.writer().writeByte(1);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(index);
            Service.getInstance().sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();

            msg = new Message(-103);
            msg.writer().writeByte(2);
            msg.writer().writeByte(index);
            msg.writer().writeShort(flagIconId[index]);
            Service.getInstance().sendMessAllPlayerInMap(pl, msg);
            msg.cleanup();

            if (pl.pet != null) {
                pl.pet.cFlag = (byte) index;
                msg = new Message(-103);
                msg.writer().writeByte(1);
                msg.writer().writeInt((int) pl.pet.id);
                msg.writer().writeByte(index);
                Service.getInstance().sendMessAllPlayerInMap(pl.pet, msg);
                msg.cleanup();

                msg = new Message(-103);
                msg.writer().writeByte(2);
                msg.writer().writeByte(index);
                msg.writer().writeShort(flagIconId[index]);
                Service.getInstance().sendMessAllPlayerInMap(pl.pet, msg);
                msg.cleanup();
            }
            pl.lastTimeChangeFlag = System.currentTimeMillis();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void sendFlagPlayerToMe(Player me, Player pl) {
        Message msg;
        try {
            msg = new Message(-103);
            msg.writer().writeByte(2);
            msg.writer().writeByte(pl.cFlag);
            msg.writer().writeShort(flagIconId[pl.cFlag]);
            me.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseFlag(Player pl, int index) {
        if (Util.canDoWithTime(pl.lastTimeChangeFlag, 60000)) {
            if (!MapService.gI().isMapBlackBallWar(pl.zone.map.mapId) && !MapService.gI().isMapMabuWar(pl.zone.map.mapId) && !pl.isHoldBlackBall) {
                if (index < 0 || index >= 12) {
                    Service.getInstance().sendThongBao(pl, "Không thể thao tác");
                    return;
                }
                changeFlag(pl, index);
            } else {
                sendThongBao(pl, "Không thể đổi cờ ở khu vực này");
            }
        } else {
            sendThongBao(pl, "Không thể đổi cờ lúc này! Vui lòng đợi " + TimeUtil.getTimeLeft(pl.lastTimeChangeFlag, 60) + " nữa!");
        }
    }

    public void attackPlayer(Player pl, int idPlAnPem) {
        SkillService.gI().useSkill(pl, pl.zone.getPlayerInMap(idPlAnPem), null);
    }

    public void openZoneUI(Player pl) {
        if (pl.zone == null || pl.zone.map.isMapOffline) {
            sendThongBaoOK(pl, "Không thể đổi khu vực trong map này");
            return;
        }
        int mapid = pl.zone.map.mapId;
        if (!pl.isAdmin() && (MapService.gI().isMapDoanhTrai(mapid) || MapService.gI().isMapBanDoKhoBau(mapid) || mapid == 120 || MapService.gI().isMapVS(mapid) || mapid == 126 || pl.zone instanceof ZDungeon)) {
            sendThongBaoOK(pl, "Không thể đổi khu vực trong map này");
            return;
        }
        Message msg;
        try {
            msg = new Message(29);
            msg.writer().writeByte(pl.zone.map.zones.size());
            for (Zone zone : pl.zone.map.zones) {
                msg.writer().writeByte(zone.zoneId);
                int numPlayers = zone.getNumOfPlayers();
                msg.writer().writeByte((numPlayers < 5 ? 0 : (numPlayers < 8 ? 1 : 2)));
                msg.writer().writeByte(numPlayers);
                msg.writer().writeByte(zone.maxPlayer);
                msg.writer().writeByte(0);
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void releaseCooldownSkill(Player pl) {
        Message msg;
        try {
            msg = new Message(-94);
            for (Skill skill : pl.playerSkill.skills) {
                skill.coolDown = 0;
                msg.writer().writeShort(skill.skillId);
                int leftTime = (int) (skill.lastTimeUseThisSkill + skill.coolDown - System.currentTimeMillis());
                if (leftTime < 0) {
                    leftTime = 0;
                }
                msg.writer().writeInt(leftTime);
            }
            pl.sendMessage(msg);
            pl.nPoint.setMp(pl.nPoint.mpMax);
            PlayerService.gI().sendInfoHpMpMoney(pl);
            msg.cleanup();

        } catch (Exception e) {
        }
    }

    public void sendTimeSkill(Player pl) {
        Message msg;
        try {
            msg = new Message(-94);
            for (Skill skill : pl.playerSkill.skills) {
                msg.writer().writeShort(skill.skillId);

                int timeLeft = (int) (skill.lastTimeUseThisSkill + skill.coolDown - System.currentTimeMillis());
                if (timeLeft < 0) {
                    timeLeft = 0;
                }
                msg.writer().writeInt(timeLeft);
            }
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void dropItemMap(Zone zone, ItemMap item) {
        Message msg;
        try {
            msg = new Message(68);
            msg.writer().writeShort(item.itemMapId);
            msg.writer().writeShort(item.itemTemplate.id);
            msg.writer().writeShort(item.x);
            msg.writer().writeShort(item.y);
            msg.writer().writeInt((int) item.playerId);//
            if (item.playerId == -2) {
                msg.writer().writeShort(item.range);
            }
            sendMessAllPlayerInMap(zone, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropItemMapForMe(Player player, ItemMap item) {
        Message msg;
        try {
            msg = new Message(68);
            msg.writer().writeShort(item.itemMapId);
            msg.writer().writeShort(item.itemTemplate.id);
            msg.writer().writeShort(item.x);
            msg.writer().writeShort(item.y);
            msg.writer().writeInt((int) item.playerId);//
            if (item.playerId == -2) {
                msg.writer().writeShort(item.range);
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void showInfoPet(Player pl) {
        if (pl != null && pl.pet != null) {
            Message msg;
            try {
                msg = new Message(-107);
                msg.writer().writeByte(2);
                msg.writer().writeShort(pl.pet.getAvatar());
                msg.writer().writeByte(pl.pet.inventory.itemsBody.size());

                for (Item item : pl.pet.inventory.itemsBody) {
                    if (!item.isNotNullItem()) {
                        msg.writer().writeShort(-1);
                    } else {
                        msg.writer().writeShort(item.template.id);
                        msg.writer().writeInt(item.quantity);
                        msg.writer().writeUTF(item.getInfo());
                        msg.writer().writeUTF(item.getContent());

                        List<ItemOption> itemOptions = item.getDisplayOptions();
                        int countOption = itemOptions.size();
                        msg.writer().writeByte(countOption);
                        for (ItemOption iop : itemOptions) {
                            msg.writer().writeByte(iop.optionTemplate.id);
                            msg.writer().writeShort(iop.param);
                        }
                    }
                }

                msg.writer().writeLong(pl.pet.nPoint.hp); //hp
                msg.writer().writeLong(pl.pet.nPoint.hpMax); //hpfull
                msg.writer().writeLong(pl.pet.nPoint.mp); //mp
                msg.writer().writeLong(pl.pet.nPoint.mpMax); //mpfull
                msg.writer().writeLong(pl.pet.nPoint.dame); //damefull
                msg.writer().writeUTF(pl.pet.name); //name
                msg.writer().writeUTF(getCurrStrLevel(pl.pet)); //curr level
                msg.writer().writeLong(pl.pet.nPoint.power); //power
                msg.writer().writeLong(pl.pet.nPoint.tiemNang); //tiềm năng
                msg.writer().writeByte(pl.pet.getStatus()); //status
                msg.writer().writeShort(pl.pet.nPoint.stamina); //stamina
                msg.writer().writeShort(pl.pet.nPoint.maxStamina); //stamina full
                msg.writer().writeByte(pl.pet.nPoint.crit); //crit
                msg.writer().writeLong(pl.pet.nPoint.def); //def
//                if (pl.pet.typePet == ConstPet.WHIS) {
                msg.writer().writeByte(5); //counnt pet skill
//                } else {
//                    msg.writer().writeByte(4); //counnt pet skill
//                }
                for (int i = 0; i < pl.pet.playerSkill.skills.size(); i++) {
                    if (pl.pet.playerSkill.skills.get(i).skillId != -1) {
                        msg.writer().writeShort(pl.pet.playerSkill.skills.get(i).skillId);
                    } else {
                        switch (i) {
                            case 1://skil 2
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 150tr để mở");
                                break;
                            case 2://s kil 3
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 1tỷ5 để mở");
                                break;
                            case 3://skil 4
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("Cần đạt sức mạnh 30 tỷ để mở");
                                break;
                            case 4:
                                msg.writer().writeShort(-1);
                                msg.writer().writeUTF("ần đạt sức mạnh 105 tỷ để mở");
                                break;
                        }

                    }
                }
                pl.sendMessage(msg);
                msg.cleanup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendSpeedPlayer(Player pl, int speed) {
        Message msg;
        try {
            msg = Service.getInstance().messageSubCommand((byte) 8);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeByte(speed != -1 ? speed : pl.nPoint.speed);
            pl.sendMessage(msg);
//            Service.getInstance().sendMessAllPlayerInMap(pl.map, msg);
            msg.cleanup();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void setPos(Player player, int x, int y) {
        player.location.x = x;
        player.location.y = y;
        Message msg;
        try {
            msg = new Message(123);
            msg.writer().writeInt((int) player.id);
            msg.writer().writeShort(x);
            msg.writer().writeShort(y);
            msg.writer().writeByte(1);
            sendMessAllPlayerInMap(player, msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void getPlayerMenu(Player player, int playerId) {
        Message msg;
        try {
            msg = new Message(-79);
            Player pl = player.zone.getPlayerInMap(playerId);
            if (pl != null) {
                msg.writer().writeInt(playerId);
                msg.writer().writeLong(pl.nPoint.power);
                msg.writer().writeUTF(Service.getInstance().getCurrStrLevel(pl));
                Service.getInstance().sendThongBao(pl, player.name + " vừa dòm bạn!");
                player.sendMessage(msg);
            }
            msg.cleanup();
            if (player.isAdmin()) {
                SubMenuService.gI().showMenuForAdmin(player);
            }
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void subMenuPlayer(Player player) {
        Message msg;
        try {
            msg = messageSubCommand((byte) 63);
            msg.writer().writeByte(1);
            msg.writer().writeUTF("String 1");
            msg.writer().writeUTF("String 2");
            msg.writer().writeShort(550);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void hideWaitDialog(Player pl) {
        Message msg;
        try {
            msg = new Message(-99);
            msg.writer().writeByte(-1);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void chatPrivate(Player plChat, Player plReceive, String text) {
        Message msg;
        try {
            msg = new Message(92);
            msg.writer().writeUTF(plChat.name);
            msg.writer().writeUTF("|5|" + text);
            msg.writer().writeInt((int) plChat.id);
            msg.writer().writeShort(plChat.getHead());
            msg.writer().writeShort(plChat.getBody());
            msg.writer().writeShort(plChat.getFlagBag()); //bag
            msg.writer().writeShort(plChat.getLeg());
            msg.writer().writeByte(1);
            plChat.sendMessage(msg);
            plReceive.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void changePassword(Player player, String oldPass, String newPass, String rePass) {
        if (player.getSession().pp.equals(oldPass)) {
            if (newPass.length() >= 6) {
                if (newPass.equals(rePass)) {
                    player.getSession().pp = newPass;
                    AccountDAO.updateAccount(player.getSession());
                    Service.getInstance().sendThongBao(player, "Đổi mật khẩu thành công!");
                } else {
                    Service.getInstance().sendThongBao(player, "Mật khẩu nhập lại không đúng!");
                }
            } else {
                Service.getInstance().sendThongBao(player, "Mật khẩu ít nhất 6 ký tự!");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Mật khẩu cũ không đúng!");
        }
    }

    public void switchToCreateChar(Session session) {
        Message msg;
        try {
            msg = new Message(2);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendCaption(Session session, byte gender) {
        Message msg;
        try {
            List<Caption> captions = CaptionManager.getInstance().getCaptions();
            msg = new Message(-41);
            msg.writer().writeByte(captions.size());
            for (Caption caption : captions) {
                msg.writer().writeUTF(caption.getCaption(gender));
            }
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendHavePet(Player player) {
        Message msg;
        try {
            msg = new Message(-107);
            msg.writer().writeByte(player.pet == null ? 0 : 1);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendWaitToLogin(Session session, int secondsWait) {
        Message msg;
        try {
            msg = new Message(122);
            msg.writer().writeShort(secondsWait);
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            Log.error(Service.class, e);
        }
    }

    public void sendMessage(Session session, int cmd, String path) {
        Message msg;
        try {
            msg = new Message(cmd);
            msg.writer().write(FileIO.readFile(path));
            session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendTopRank(Player pl) {
        Message msg;
        try {
            msg = new Message(Cmd.THELUC);
            msg.writer().writeInt(1);
            pl.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void createItemMap(Player player, int tempId) {
        ItemMap itemMap = new ItemMap(player.zone, tempId, 1, player.location.x, player.location.y, player.id);
        dropItemMap(player.zone, itemMap);
    }

    public void sendNangDong(Player player) {
        Message msg;
        try {
            msg = new Message(-97);
            msg.writer().writeInt(100);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendPowerInfo(Player pl, String info, short point) {
        Message m = null;
        try {
            m = new Message(-115);
            m.writer().writeUTF(info);
            m.writer().writeShort(point);
            m.writer().writeShort(20);
            m.writer().writeShort(10);
            m.writer().flush();
            if (pl != null && pl.getSession() != null) {
                pl.sendMessage(m);
            }
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void setMabuHold(Player pl, byte type) {
        Message m = null;
        try {
            m = new Message(52);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (m != null) {
                m.cleanup();
            }
        }
    }

    public void sendPercentMabuEgg(Player player, byte percent) {
        try {
            Message msg = new Message(-117);
            msg.writer().writeByte(percent);
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void sendPlayerInfo(Player player) {
        try {
            Message msg = messageSubCommand((byte) 7);
            msg.writer().writeInt((int) player.id);
            if (player.clan != null) {
                msg.writer().writeInt(player.clan.id);
            } else {
                msg.writer().writeInt(-1);
            }
            int level = CaptionManager.getInstance().getLevel(player);
            level = player.isInvisible ? 0 : level;
            msg.writer().writeByte(level);
            msg.writer().writeBoolean(player.isInvisible);
            msg.writer().writeByte(player.typePk);
            msg.writer().writeByte(player.gender);
            msg.writer().writeByte(player.gender);
            msg.writer().writeShort(player.getHead());
            msg.writer().writeUTF(player.name);
            msg.writer().writeLong(player.nPoint.hp);
            msg.writer().writeLong(player.nPoint.hpMax);
            msg.writer().writeShort(player.getBody());
            msg.writer().writeShort(player.getLeg());
            msg.writer().writeByte(player.getFlagBag());
            msg.writer().writeByte(-1);
            msg.writer().writeShort(player.location.x);
            msg.writer().writeShort(player.location.y);
            msg.writer().writeShort(0);
            msg.writer().writeShort(0);
            msg.writer().writeByte(0);

//            msg.writer().writeShort(0);
//            msg.writer().writeByte(0);
//            msg.writer().writeShort(0);
            sendMessAllPlayer(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getCurrLevel(Player pl) {

    }

    public int getWidthHeightImgPetFollow(int id) {
        if (id == 15067) {
            return 65;
        }
        return 75;
    }

    public void showTopPower(Player player) {
        List<Player> list = TopManager.getInstance().getList();
        Message msg = new Message(Cmd.TOP);
        try {
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Top Sức Mạnh");
            msg.writer().writeByte(list.size());
            for (int i = 0; i < list.size(); i++) {
                Player pl = list.get(i);
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeShort(pl.getHead());
                if (player.isVersionAbove(220)) {
                    Part part = PartManager.getInstance().find(pl.getHead());
                    msg.writer().writeShort(part.getIcon(0));
                }
                msg.writer().writeShort(pl.getBody());
                msg.writer().writeShort(pl.getLeg());
                msg.writer().writeUTF(pl.name);
                msg.writer().writeUTF(Client.gI().getPlayer(pl.id) != null ? "Online" : "");
                msg.writer().writeUTF("Sức mạnh: " + Util.numberToMoney(pl.nPoint.power));
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffAllPlayerMapToMe(Player pl) {
        try {
            for (Player plM : pl.zone.getPlayers()) {
                if (plM.isPl() && plM.inventory.itemsBody.size() >= 12) {

                    Item chanmenh = plM.inventory.itemsBody.get(13);
                    if (chanmenh.isNotNullItem()) {
                        Service.getInstance().sendEffPlayer(plM, pl, chanmenh.template.part, 0, -1, 1);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public void sendEffPlayer(Player pl, Player plReceive, int idEff, int layer, int loop, int loopCount) {
        Message msg = null;
        try {
            msg = new Message(-128);
            msg.writer().writeByte(0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(idEff);
            msg.writer().writeByte(layer);
            msg.writer().writeByte(loop);
            msg.writer().writeShort(loopCount);
            msg.writer().writeByte(0);
            plReceive.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffPlayer(Player pl) {
        if (pl.isPl()) {
            if (pl.inventory.itemsBody.size() > 13) {
                Item chanMenh = pl.inventory.itemsBody.get(13);
                if (chanMenh.isNotNullItem()) {
                    Service.getInstance().sendEffAllPlayer(pl, chanMenh.template.part, 0, -1, 1);
                }
                Item vongThienSu = pl.inventory.itemsBody.get(12);
                if (vongThienSu.isNotNullItem()) {
                    Service.getInstance().sendEffAllPlayer(pl, vongThienSu.template.part, 0, -1, 1);
                }
            } else {
//            System.out.println("itemsBody does not have enough elements.");
            }
        }

    }

    public void removeEffPlayer(Player pl, int idEff) {
        Message msg = null;
        try {
            msg = new Message(-128);
            msg.writer().writeByte(1);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(idEff);
            sendMessAllPlayerInMap(pl.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffAllPlayer(Player pl, int idEff, int layer, int loop, int loopCount) {
        Message msg = null;
        try {
            msg = new Message(-128);
            msg.writer().writeByte(0);
            msg.writer().writeInt((int) pl.id);
            msg.writer().writeShort(idEff);
            msg.writer().writeByte(layer);
            msg.writer().writeByte(loop);
            msg.writer().writeShort(loopCount);
            msg.writer().writeByte(0);
            sendMessAllPlayerInMap(pl.zone, msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showTopTask(Player player) {
        List<Player> list = TopToTask.getInstance().load();
        Message msg = new Message(Cmd.TOP);
        try {
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Top Nhiệm Vụ");
            msg.writer().writeByte(list.size());
            for (int i = 0; i < list.size(); i++) {
                Player pl = list.get(i);
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt((int) pl.id);
                msg.writer().writeShort(pl.getHead());
                if (player.isVersionAbove(220)) {
                    Part part = PartManager.getInstance().find(pl.getHead());
                    msg.writer().writeShort(part.getIcon(0));
                }
                msg.writer().writeShort(pl.getBody());
                msg.writer().writeShort(pl.getLeg());
                msg.writer().writeUTF(pl.name);
                msg.writer().writeUTF(Client.gI().getPlayer(pl.id) != null ? "Online" : "");
//                msg.writer().writeUTF("Sức mạnh: " + Util.numberToMoney(pl.nPoint.power));
                msg.writer().writeUTF(" ặ ặ");
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowTopNap(Player player) {
        List<TopPlayer> list = GetTopNap();
        Message msg = new Message(Cmd.TOP);
        try {
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Bảng xếp hạng Nạp");
            msg.writer().writeByte(list.size());
            for (int i = 0; i < list.size(); i++) {
                TopPlayer top = list.get(i);
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt(Math.toIntExact(top.id));
                msg.writer().writeShort(player.getHead());
                if (player.isVersionAbove(220)) {
                    Part part = PartManager.getInstance().find(player.getHead());
                    msg.writer().writeShort(part.getIcon(0));
                }
                msg.writer().writeShort(player.getBody());
                msg.writer().writeShort(player.getLeg());
                msg.writer().writeUTF(top.name);

                msg.writer().writeUTF(Util.formatCurrency(top.amount) + " VNĐ");

                msg.writer().writeUTF("");
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ShowTopPower(Player player) {
        List<TopPlayer> list = GetTopPower();
        Message msg = new Message(Cmd.TOP);
        try {
            msg.writer().writeByte(0);
            msg.writer().writeUTF("Bảng xếp hạng Sức mạnh");
            msg.writer().writeByte(list.size());
            for (int i = 0; i < list.size(); i++) {
                TopPlayer top = list.get(i);
                msg.writer().writeInt(i + 1);
                msg.writer().writeInt(Math.toIntExact(top.id));
                msg.writer().writeShort(player.getHead());
                if (player.isVersionAbove(220)) {
                    Part part = PartManager.getInstance().find(player.getHead());
                    msg.writer().writeShort(part.getIcon(0));
                }
                msg.writer().writeShort(player.getBody());
                msg.writer().writeShort(player.getLeg());
                msg.writer().writeUTF(top.name);

                msg.writer().writeUTF(Util.formatCurrency(top.amount));

                msg.writer().writeUTF("");
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
