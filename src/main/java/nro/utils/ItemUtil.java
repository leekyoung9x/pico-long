package nro.utils;

import nro.consts.ConstOption;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.player.NPoint;
import nro.models.player.Player;
import nro.services.Service;

import java.util.List;
import java.util.Objects;

public class ItemUtil {
    public static boolean isNewSKHAndCanUpgrade(Player player, Item item) {
        if (Objects.isNull(item) || !item.isNotNullItem()) {
            return false;
        }
        List<ItemOption> itemOptionList = item.itemOptions;
        for (ItemOption itemOption : itemOptionList) {
            switch (itemOption.optionTemplate.id) {
                case ConstOption.OPTION_PERCENT_HP:
                case ConstOption.OPTION_PERCENT_KI:
                    return itemOption.param < 200;
                case ConstOption.OPTION_PERCENT_KAMEJOKO:
                case ConstOption.OPTION_PERCENT_LIEN_HOAN:
                    return itemOption.param < 150;
            }

        }
        return false;
    }


    public static boolean isNewSKH(Player player, Item item) {
        if (Objects.isNull(item) || !item.isNotNullItem()) {
            return false;
        }
        List<ItemOption> itemOptionList = item.itemOptions;
        for (ItemOption itemOption : itemOptionList) {
            switch (itemOption.optionTemplate.id) {
                case ConstOption.OPTION_PERCENT_HP:
                case ConstOption.OPTION_PERCENT_KI:
                    return true;
                case ConstOption.OPTION_PERCENT_KAMEJOKO:
                case ConstOption.OPTION_PERCENT_LIEN_HOAN:
                    return true;
            }

        }
        return false;
    }
}
