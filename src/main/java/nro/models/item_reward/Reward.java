package nro.models.item_reward;

import lombok.Getter;
import nro.models.item.Item;

import java.util.function.Supplier;

@Getter
public class Reward {
    public int chance;
    public Supplier<Item> createItem;

    public Reward(int chance, Supplier<Item> createItem) {
        this.chance = chance;
        this.createItem = createItem;
    }
}
