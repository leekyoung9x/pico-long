package nro.models.player;

import nro.models.item_reward.ItemRewardQuantity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class PlayerRubyHistory {
    public long id;
    // id người chơi
    public long player_id;
    // số ruby cũ
    public int old_ruby;
    // số ruby mới
    public int new_ruby;
    // tên hàm thực hiện add ruby
    public String function_name;
    // ngày tạo
    public Timestamp created_date;

    // Constructor
    public PlayerRubyHistory(long player_id, int old_ruby, int new_ruby, String function_name) {
        this.player_id = player_id;
        this.old_ruby = old_ruby;
        this.new_ruby = new_ruby;
        this.function_name = function_name;
        this.created_date = new Timestamp(System.currentTimeMillis());
    }
}
