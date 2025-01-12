package nro.utils;

import nro.models.box.OpenBox;
import nro.models.box.OpenBoxOption;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.item_reward.Reward;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;

import java.util.*;
import java.util.stream.Collectors;

public class BossRewardUtil {
    // Ekko Mở Hộp quà Noel
    public static void DropBossItemOpenBox(Player pl, List<OpenBox> items) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            StringBuilder noti = new StringBuilder();

            List<Reward> rewards = new ArrayList<>();
            List<ItemOption> itemOptions = new ArrayList<>();

            items = items.stream()
                    .sorted(Comparator.comparing(OpenBox::getRate).reversed())
                    .collect(Collectors.toList()); // Thu thập kết quả thành danh sách

            if (items.isEmpty()) {
                Service.getInstance().sendThongBao(pl, "Hộp quà không tồn tại");
                return;
            }

            OpenBox rateDefault = items.get(0);

            for (OpenBox box : items) {
                itemOptions = new ArrayList<>();

                for (int i = 0; i < box.getOpenBoxOptions().size(); i++) {
                    OpenBoxOption option = box.getOpenBoxOptions().get(i);
                    itemOptions.add(new ItemOption(option.getOption_id(), Util.nextInt(option.getOption_value_from(), option.getOption_value_to())));
                }

                List<ItemOption> finalItemOptions = itemOptions;
                rewards.add(new Reward(box.getRate(), () -> createItem(box.getTemplate_id_from(), box.getTemplate_id_to(), Util.nextInt(box.getQuantity_from(), box.getQuantity_to()), finalItemOptions)));
            }

            // Thực hiện kiểm tra tỷ lệ và thêm phần thưởng vào túi
            for (Reward reward : rewards) {
                if (Util.isTrue(reward.chance, 100)) {
                    dropItemWithOption(pl, reward, itemOptions);
                    break;
                }
            }

            // Thông báo
            String notiResult = noti.length() > 0 ? noti.substring(0, noti.length() - 2) : "";
            if (notiResult.isEmpty()) {
                dropItemWithOption(pl, rateDefault, itemOptions);
            } else {
                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    public static void DropBossItem(Player pl, List<Reward> items) {
        List<ItemOption> itemOptions = new ArrayList<>();
        items = items.stream()
                .sorted(Comparator.comparing(Reward::getChance).reversed())
                .collect(Collectors.toList()); // Thu thập kết quả thành danh sách
        if (items.isEmpty()) {
            Service.getInstance().sendThongBao(pl, "Không có đồ để rơi");
            return;
        }
        Reward rateDefault = items.get(0);
        boolean isDefault = true;
        // Thực hiện kiểm tra tỷ lệ và thêm phần thưởng vào túi
        for (Reward reward : items) {
            if (Util.isTrue(reward.chance, 100)) {
                dropItemWithOption(pl, reward, itemOptions);
                isDefault = false;
                break;
            }
        }
        // chưa rơi đồ nào thì rơi đồ mặc định (mặc định là đồ đầu tiên trong danh sách)
        if (isDefault) {
            dropItemWithOption(pl, rateDefault, itemOptions);
        }
    }

    public static void dropItemWithOption(Player pl, Reward reward, List<ItemOption> itemOptions) {
        Item itemReceived = reward.createItem.get();

        ItemMap itemMap = Util.ratiItem(pl.zone, itemReceived.getId(), itemReceived.quantity, pl.location.x, pl.location.y, pl.id);
        itemMap.options.addAll(itemReceived.itemOptions);
        itemMap.options.add(new ItemOption(30, 0)); // Thêm option 30 cho tất cả các item

        Service.getInstance().dropItemMap(pl.zone, itemMap);
    }

    public static void dropItemWithOption(Player pl, OpenBox rateDefault, List<ItemOption> itemOptions) {
        Item itemReceived = createItem(Util.nextInt(rateDefault.getTemplate_id_from(), rateDefault.getTemplate_id_to()), Util.nextInt(rateDefault.getQuantity_from(), rateDefault.getQuantity_to()));

        for (int i = 0; i < rateDefault.getOpenBoxOptions().size(); i++) {
            OpenBoxOption option = rateDefault.getOpenBoxOptions().get(i);
            itemOptions.add(new ItemOption(option.getOption_id(), Util.nextInt(option.getOption_value_from(), option.getOption_value_to())));
        }

        itemReceived.itemOptions.addAll(itemOptions);

        ItemMap itemMap = Util.ratiItem(pl.zone, itemReceived.getId(), 1, pl.location.x, pl.location.y, pl.id);
        itemMap.options.addAll(itemReceived.itemOptions);
        itemMap.options.add(new ItemOption(30, 0)); // Thêm option 30 cho tất cả các item

        Service.getInstance().dropItemMap(pl.zone, itemMap);
    }

    // Phương thức tạo item ngẫu nhiên
    public static Item createItem(int itemId, int quantity) {
        return ItemService.gI().createNewItem((short) itemId, quantity);
    }

    // Phương thức tạo item ngẫu nhiên
    public static Item createItem(int minId, int maxId, int quantity) {
        int itemId = Util.nextInt(minId, maxId);
        return ItemService.gI().createNewItem((short) itemId, quantity);
    }

    // Phương thức tạo item ngẫu nhiên
    public static Item createItem(int minId, int maxId, int quantity, List<ItemOption> options) {
        int itemId = Util.nextInt(minId, maxId);
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        item.itemOptions.addAll(options);
        return item;
    }

    // Phương thức tạo item với một tùy chọn
    public static Item createItemWithOption(int itemId, int quantity, int optionId, int optionValue) {
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        item.itemOptions.add(new ItemOption(optionId, optionValue));
        return item;
    }

    // Phương thức tạo item với nhiều tùy chọn
    public static Item createItemWithMultipleOptions(int itemId, int quantity, List<ItemOption> options) {
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        item.itemOptions.addAll(options);
        return item;
    }
}
