package nro.utils;

import nro.models.box.OpenBox;
import nro.models.box.OpenBoxOption;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.item_reward.Reward;
import nro.models.player.Player;
import nro.server.Manager;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.Service;

import java.util.*;
import java.util.stream.Collectors;

public class BoxUtil {
    // Ekko Mở Hộp quà Noel
    public static void OpenBoxItem(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            StringBuilder noti = new StringBuilder();

            List<Reward> rewards = new ArrayList<>();
            List<ItemOption> itemOptions = new ArrayList<>();

            List<OpenBox> result = Manager.OPEN_BOX.stream()
                    .filter(box -> box.getBox_id() == item.template.id)
                    .sorted(Comparator.comparing(OpenBox::getRate).reversed())
                    .collect(Collectors.toList()); // Thu thập kết quả thành danh sách

            if (result.isEmpty()) {
                Service.getInstance().sendThongBao(pl, "Hộp quà không tồn tại");
                return;
            }

            OpenBox rateDefault = result.get(0);

            for (OpenBox box : result) {
                itemOptions = new ArrayList<>();

                for (int i = 0; i < box.getOpenBoxOptions().size(); i++) {
                    OpenBoxOption option = box.getOpenBoxOptions().get(i);
                    itemOptions.add(new ItemOption(option.getOption_id(), Util.nextInt(option.getOption_value_from(), option.getOption_value_to())));
                }

                List<ItemOption> finalItemOptions = itemOptions;

                if (box.getTemplate_id_from() == -1 && box.getTemplate_id_to() == -1) {
                    pl.diem_skien++;
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được 1 điểm sự kiện");
                } else {
                    rewards.add(new Reward(box.getRate(), () -> createItem(box.getTemplate_id_from(), box.getTemplate_id_to(), Util.nextInt(box.getQuantity_from(), box.getQuantity_to()), finalItemOptions)));
                }
            }

            // Thực hiện kiểm tra tỷ lệ và thêm phần thưởng vào túi
            for (Reward reward : rewards) {
                if (Util.isTrue(reward.chance, 100)) {
                    Item itemReceived = reward.createItem.get();
                    noti.append(itemReceived.template.name).append(" số lượng ").append(itemReceived.quantity).append(", ");
                    InventoryService.gI().addItemBag(pl, itemReceived, 0);
                    break;
                }
            }

            // Xóa 1 item từ túi và cập nhật
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);

            // Thông báo
            String notiResult = noti.length() > 0 ? noti.substring(0, noti.length() - 2) : "";
            if (notiResult.isEmpty()) {
                Item itemReceived = createItem(Util.nextInt(rateDefault.getTemplate_id_from(), rateDefault.getTemplate_id_to()), Util.nextInt(rateDefault.getQuantity_from(), rateDefault.getQuantity_to()));

                itemOptions = new ArrayList<>();

                for (int i = 0; i < rateDefault.getOpenBoxOptions().size(); i++) {
                    OpenBoxOption option = rateDefault.getOpenBoxOptions().get(i);
                    itemOptions.add(new ItemOption(option.getOption_id(), Util.nextInt(option.getOption_value_from(), option.getOption_value_to())));
                }

                itemReceived.itemOptions.addAll(itemOptions);
                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + itemReceived.quantity + " " + itemReceived.template.name);
                InventoryService.gI().addItemBag(pl, itemReceived, 0);
            } else {
                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Phương thức tạo item ngẫu nhiên
    private static Item createItem(int itemId, int quantity) {
        return ItemService.gI().createNewItem((short) itemId, quantity);
    }

    // Phương thức tạo item ngẫu nhiên
    private static Item createItem(int minId, int maxId, int quantity) {
        int itemId = Util.nextInt(minId, maxId);
        return ItemService.gI().createNewItem((short) itemId, quantity);
    }

    // Phương thức tạo item ngẫu nhiên
    private static Item createItem(int minId, int maxId, int quantity, List<ItemOption> options) {
        int itemId = Util.nextInt(minId, maxId);
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        item.itemOptions.addAll(options);
        return item;
    }

    // Phương thức tạo item với một tùy chọn
    private static Item createItemWithOption(int itemId, int quantity, int optionId, int optionValue) {
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        item.itemOptions.add(new ItemOption(optionId, optionValue));
        return item;
    }

    // Phương thức tạo item với nhiều tùy chọn
    private static Item createItemWithMultipleOptions(int itemId, int quantity, ItemOption... options) {
        Item item = ItemService.gI().createNewItem((short) itemId, quantity);
        Collections.addAll(item.itemOptions, options);
        return item;
    }
}
