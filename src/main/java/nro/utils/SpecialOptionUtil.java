package nro.utils;

import nro.models.item.Item;
import nro.models.player.Pet;
import nro.models.player.Player;
import nro.services.Service;

public class SpecialOptionUtil {

    public static void MakeOptionThayTro(Player player, Item item, boolean isMaster) {
        if (Util.isSetThayTro(item.getId())) {
            Item itemPet = null;

            if (player.pet != null
                    && player.pet.inventory != null
                    && player.pet.inventory.itemsBody != null) {
                itemPet = player.pet.inventory.itemsBody.get(5);
            }

            if (item != null && itemPet != null && item.template != null && itemPet.template != null) {
                if (item.getId() == 1276 && itemPet.getId() == 1277) {
                    Util.AddOptionToItem(item, 233);
                    Util.AddOptionToItem(itemPet, 232);
                }

                if (item.getId() == 1277 && itemPet.getId() == 1276) {
                    Util.AddOptionToItem(item, 232);
                    Util.AddOptionToItem(itemPet, 233);
                    Service.getInstance().Send_Caitrang(player.pet);
                    Service.getInstance().point(player.pet);
                    Service.getInstance().sendFlagBag(player.pet);
                }
            }
        }
    }

    public static void MakeOptionThayTro(Pet pet, Item item, boolean isMaster) {
        if (Util.isSetThayTro(item.getId())) {
            Item itemPet = null;

            if (pet.master != null
                    && pet.master.inventory != null
                    && pet.master.inventory.itemsBody != null) {
                itemPet = pet.master.inventory.itemsBody.get(5);
            }

            if (item != null && itemPet != null && item.template != null && itemPet.template != null) {
                if (item.getId() == 1276 && itemPet.getId() == 1277) {
                    Util.AddOptionToItem(item, 233);
                    Util.AddOptionToItem(itemPet, 232);
                }

                if (item.getId() == 1277 && itemPet.getId() == 1276) {
                    Util.AddOptionToItem(item, 232);
                    Util.AddOptionToItem(itemPet, 233);
                    Service.getInstance().Send_Caitrang(pet.master);
                    Service.getInstance().point(pet.master);
                    Service.getInstance().sendFlagBag(pet.master);
                }
            }
        }
    }
}
