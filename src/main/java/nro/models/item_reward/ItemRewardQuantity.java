package nro.models.item_reward;

import java.sql.Time;
import java.sql.Timestamp;

// Vật phẩm và số lượng người chơi nhận được
public class ItemRewardQuantity {
    private int id;
    private int quantity;
    private Timestamp date_reward;

    public ItemRewardQuantity(int id, int quantity, long date_reward_long) {
        this.id = id;
        this.quantity = quantity;
        this.date_reward = new Timestamp(date_reward_long);
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Timestamp getDateReward() {
        return date_reward;
    }

    public long getLongDateReward() {
        return date_reward.getTime();
    }
}
