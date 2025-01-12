package nro.models.player;

import nro.models.item_reward.ItemRewardQuantity;

import java.sql.Timestamp;
import java.util.List;

public class PlayerReward {
    public long id;
    // id người chơi
    public long player_id;
    // vật phẩm đã nhận: id vật phẩm + số lượng
    public List<ItemRewardQuantity> items;
    // thời gian nhận
    public Timestamp created_date;

}
