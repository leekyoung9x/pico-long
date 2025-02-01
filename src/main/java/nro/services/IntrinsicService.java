package nro.services;

import nro.consts.ConstNpc;
import nro.models.intrinsic.Intrinsic;
import nro.models.player.Player;
import nro.server.Manager;
import nro.server.io.Message;
import nro.utils.Util;

import java.util.List;

/**
 *
 * @Build by Arriety
 *
 */
public class IntrinsicService {

    private static IntrinsicService i;
    private static final int[] COST_OPEN = {10, 20, 40, 80, 160, 320, 640, 1280};

    public static IntrinsicService gI() {
        if (i == null) {
            i = new IntrinsicService();
        }
        return i;
    }

    public List<Intrinsic> getIntrinsics(byte playerGender) {
        switch (playerGender) {
            case 0:
                return Manager.INTRINSIC_TD;
            case 1:
                return Manager.INTRINSIC_NM;
            default:
                return Manager.INTRINSIC_XD;
        }
    }

    public Intrinsic getIntrinsicById(int id) {
        for (Intrinsic intrinsic : Manager.INTRINSICS) {
            if (intrinsic.id == id) {
                return new Intrinsic(intrinsic);
            }
        }
        return null;
    }

    public void set_TD(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_TD, -1,
                "Chọn đi ku", "Set\nKamejoko", "Set\nKrilin", "Set\nKaioken", "Từ chối");
    }

    public void set_NM(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_NM, -1,
                "Chọn đi ku", "Set\nLiên hoàn", "Set\nPicolo", "Set\nPikkoro Daimao", "Từ chối");
    }

    public void set_XD(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_XD, -1,
                "Chọn đi ku", "Set\nKakarot", "Set\nCadic", "Set\nNappa", "Từ chối");
    }

    public void sattd(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.menutd, -1,
                "Chọn đi ku", "Set\nSongoku", "Set\nTenshinhan", "Set\nKrillin", "Từ chối");
    }

    public void satnm(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.menunm, -1,
                "Chọn đi ku", "Set\nDende", "Set\nPicolo", "Set\nDaimao", "Từ chối");
    }

    public void setxd(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.menuxd, -1,
                "Chọn đi ku", "Set\nKakarot", "Set\nCadic", "Set\nNappa", "Từ chối");
    }

    // ekko set hủy diệt kích hoạt
    public void setTDHD(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_TD_HD, -1,
                "Chọn đi ku", "Set\nSongoku", "Set\nGohan", "Set\nKrillin", "Từ chối");
    }

    public void setNMHD(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_NM_HD, -1,
                "Chọn đi ku", "Set\nDende", "Set\nPicolo", "Set\nDaimao", "Từ chối");
    }

    public void setXDHD(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.MENU_XD_HD, -1,
                "Chọn đi ku", "Set\nKakarot", "Set\nCadic", "Set\nNappa", "Từ chối");
    }

    public void sendInfoIntrinsic(Player player) {
        Message msg;
        try {
            msg = new Message(112);
            msg.writer().writeByte(0);
            msg.writer().writeShort(player.playerIntrinsic.intrinsic.icon);
            msg.writer().writeUTF(player.playerIntrinsic.intrinsic.getName());
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }
    public void changeIntrinsic2(Player player) {

        List<Intrinsic> listIntrinsic = getIntrinsics(player.gender);
        player.playerIntrinsic.intrinsic = new Intrinsic(listIntrinsic.get(Util.nextInt(1, listIntrinsic.size() - 1)));
        player.playerIntrinsic.intrinsic.param1 = player.playerIntrinsic.intrinsic.paramTo1;
        player.playerIntrinsic.intrinsic.param2 =  player.playerIntrinsic.intrinsic.paramTo2;
        Service.getInstance().sendThongBao(player, "Bạn nhận được Nội tại:\n" + player.playerIntrinsic.intrinsic.getName().substring(0, player.playerIntrinsic.intrinsic.getName().indexOf(" [")));
        sendInfoIntrinsic(player);
    }

    public void showAllIntrinsic(Player player) {
        List<Intrinsic> listIntrinsic = getIntrinsics(player.gender);
        Message msg;
        try {
            msg = new Message(112);
            msg.writer().writeByte(1);
            msg.writer().writeByte(1); //count tab
            msg.writer().writeUTF("Nội tại");
            msg.writer().writeByte(listIntrinsic.size() - 1);
            for (int i = 1; i < listIntrinsic.size(); i++) {
                msg.writer().writeShort(listIntrinsic.get(i).icon);
                msg.writer().writeUTF(listIntrinsic.get(i).getDescription());
            }
            player.sendMessage(msg);
            msg.cleanup();
        } catch (Exception e) {
        }
    }

    public void showMenu(Player player) {
//        NpcService.gI().createMenuConMeo(player, ConstNpc.INTRINSIC, -1,
//                "Nội tại là một kỹ năng bị động hỗ trợ đặc biệt\nBạn có muốn mở hoặc thay đổi nội tại không?",
//                "Xem\ntất cả\nNội Tại", "Mở\nNội Tại", "Mở VIP", "Từ chối");
        NpcService.gI().createMenuConMeo(player, ConstNpc.INTRINSIC, -1,
                "Nội tại là một kỹ năng bị động hỗ trợ đặc biệt\nBạn có muốn mở hoặc thay đổi nội tại không?",
                "Xem\ntất cả\nNội Tại", "Mở\nNội Tại", "Từ chối");
    }

    public void showConfirmOpen(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_OPEN_INTRINSIC, -1,
                "Bạn có muốn mở Nội Tại\nvới giá là 200 triệu tiềm năng", "Mở\nNội tại", "Từ chối");

    }

    public void showConfirmOpenVip(Player player) {
        NpcService.gI().createMenuConMeo(player, ConstNpc.CONFIRM_OPEN_INTRINSIC_VIP, -1,
                "Bạn có muốn mở Nội Tại\nvới giá là 2000 ngọc xanh", "Mở\nNội VIP", "Từ chối");
    }

    private void changeIntrinsic(Player player) {
        List<Intrinsic> listIntrinsic = getIntrinsics(player.gender);
        player.playerIntrinsic.intrinsic = new Intrinsic(listIntrinsic.get(Util.nextInt(1, listIntrinsic.size() - 1)));
        player.playerIntrinsic.intrinsic.param1 = (short) Util.nextInt(player.playerIntrinsic.intrinsic.paramFrom1, player.playerIntrinsic.intrinsic.paramTo1);
        player.playerIntrinsic.intrinsic.param2 = (short) Util.nextInt(player.playerIntrinsic.intrinsic.paramFrom2, player.playerIntrinsic.intrinsic.paramTo2);
        Service.getInstance().sendThongBao(player, "Bạn nhận được Nội tại:\n" + player.playerIntrinsic.intrinsic.getName().substring(0, player.playerIntrinsic.intrinsic.getName().indexOf(" [")));
        sendInfoIntrinsic(player);
    }

    public void open(Player player) {
        if (player.nPoint.power >= 10000000000L) {
            // ekko
//            int gem = 2000;
//            if (player.inventory.getRuby() >= 2000) {
//                player.inventory.subRuby(gem);
//                PlayerService.gI().sendInfoHpMpMoney(player);
//                changeIntrinsic(player);
//                player.playerIntrinsic.countOpen = 0;
//            } else {
//                Service.getInstance().sendThongBao(player, "Bạn không có đủ ngọc, còn thiếu "
//                        + (gem - player.inventory.getRuby()) + " ruby");
//            }
            // tốn 200tr tiềm năng mỗi lần mở
            int gem = 200000000;
            if (player.nPoint.tiemNang >= gem) {
                player.nPoint.tiemNang -= gem;
                PlayerService.gI().sendTNSM(player, (byte) 1, -gem);
                changeIntrinsic(player);
                player.playerIntrinsic.countOpen = 0;
            } else {
                Service.getInstance().sendThongBao(player, "Bạn không có đủ TNSM, còn thiếu "
                        + (gem - player.nPoint.tiemNang) + " TNSM");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Yêu cầu sức mạnh tối thiểu 10 tỷ");
        }
    }

    public void openVip(Player player) {
        if (player.nPoint.power >= 10000000000L) {
            int gem = 2000;
            if (player.inventory.getGem() >= gem) {
                player.inventory.subGem(gem);
                PlayerService.gI().sendInfoHpMpMoney(player);
                changeIntrinsic(player);
                player.playerIntrinsic.countOpen = 0;
            } else {
                Service.getInstance().sendThongBao(player, "Bạn không có đủ ngọc, còn thiếu "
                        + (gem - player.inventory.getGem()) + " ngọc xanh");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Yêu cầu sức mạnh tối thiểu 10 tỷ");
        }
    }

}
