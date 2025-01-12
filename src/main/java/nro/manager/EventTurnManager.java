package nro.manager;

import nro.consts.ConstAction;
import nro.consts.ConstItem;
import nro.jdbc.DBService;
import nro.models.item.Item;
import nro.models.player.Player;
import nro.server.Client;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.utils.Util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author Arriety
 */
public class EventTurnManager {

    public static void Update() {
        LocalTime currentTime = LocalTime.now();
        //update 12h trao qua + reset top
        if (currentTime.getHour() == 0 && currentTime.getMinute() == 0 && currentTime.getSecond() == 0) {
            ManageEventShiba(ConstAction.UPDATE_ALL, 0);
            ManageCallDragon(ConstAction.UPDATE_ALL, 0);
            ManageHumanityChallenge(ConstAction.UPDATE_ALL, 0);
        }
    }

    public static void AutoRuby() {
        List<Player> players = Client.gI().getPlayers();
        if (players != null && players.size() > 0) {
            for (Player player : players) {
                if (player != null && player.getSession() != null) {
                    int oldRuby = player.inventory.ruby;
                    player.inventory.addRuby(250);
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "EventTurnManager-AutoRuby");
                    Service.getInstance().sendMoney(player);
                    Service.getInstance().sendThongBao(player, "Phần thưởng của bạn khi online 1h là "
                            + Util.numberToMoney(250) + " hồng ngọc");
                }
            }
        }
    }

    public static void AutoRubyAdmin() {
        List<Player> players = Client.gI().getPlayers();
        if (players != null && players.size() > 0) {
            for (Player player : players) {
                if (player != null && player.getSession() != null && player.isAdmin()) {
                    player.inventory.addGem(50);
                    Service.getInstance().sendMoney(player);
                    Service.getInstance().sendThongBao(player, "Phần thưởng của bạn khi online 1h là " + Util.numberToMoney(50) + " ngọc xanh");
                }
            }
        }
    }

    public static int ManageEventShiba(int action, long player_id) {
        int result = 0;
        Connection con = null;
        CallableStatement ps = null;
        try {
//            Player player = Client.gI().getPlayer(player_id);
//
//            PlayerService.gI().banPlayer(player);

//            con = DBService.gI().getConnection();
//            String sql = "{CALL Proc_EventShiba(?, ?)}";
//            ps = con.prepareCall(sql);
//            ps.setDouble(1, action);
//            ps.setDouble(2, player_id);
//
//            ResultSet rs = ps.executeQuery();
//
//            if (action == ConstAction.GET_BY_ID) {
//                while (rs.next()) {
//                    result = rs.getInt("event_shiba");
//                }
//            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int ManageCallDragon(int action, long player_id) {
        int result = 0;
        Connection con = null;
        CallableStatement ps = null;
        try {
            con = DBService.gI().getConnection();
            String sql = "{CALL Proc_CallDragon(?, ?)}";
            ps = con.prepareCall(sql);
            ps.setDouble(1, action);
            ps.setDouble(2, player_id);

            ResultSet rs = ps.executeQuery();
            if (action == ConstAction.GET_BY_ID) {
                while (rs.next()) {
                    result = rs.getInt("turn");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int ManagerOpenNormal(int action, long player_id) {
        int result = 0;
        Connection con = null;
        CallableStatement ps = null;
        try {
            con = DBService.gI().getConnection();
            String sql = "{CALL Proc_EventShiba(?, ?)}";
            ps = con.prepareCall(sql);
            ps.setDouble(1, action);
            ps.setDouble(2, player_id);

            ResultSet rs = ps.executeQuery();

            if (action == ConstAction.GET_BY_ID) {
                while (rs.next()) {
                    result = rs.getInt("open_normal");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static int ManageHumanityChallenge(int action, long player_id) {
        int result = 0;
        Connection con = null;
        CallableStatement ps = null;
        try {
            con = DBService.gI().getConnection();
            String sql = "{CALL Proc_HumanityChallenge(?, ?)}";
            ps = con.prepareCall(sql);
            ps.setDouble(1, action);
            ps.setDouble(2, player_id);

            ResultSet rs = ps.executeQuery();

            if (action == ConstAction.GET_BY_ID) {
                while (rs.next()) {
                    result = rs.getInt("turn");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
