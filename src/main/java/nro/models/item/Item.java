package nro.models.item;

import nro.consts.ConstCombine;
import nro.consts.ConstItem;
import nro.models.player.NPoint;
import nro.models.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item {

    private static final ItemOption OPTION_NULL = new ItemOption(73, 0);

    public ItemTemplate template;

    public String info;

    public String content;

    public int quantity;

    public List<ItemOption> itemOptions;

    public long createTime;

    public boolean isNotNullItem() {
        return this.template != null;
    }

    public Item() {
        this.itemOptions = new ArrayList<>();
        this.createTime = System.currentTimeMillis();
    }

    public String getInfo() {
        String strInfo = "";
        for (ItemOption itemOption : itemOptions) {
            strInfo += itemOption.getOptionString();
        }
        return strInfo;
    }

    public String getInfoItem() {
        String strInfo = "|1|" + template.name + "\n|0|";
        for (ItemOption itemOption : itemOptions) {
            strInfo += itemOption.getOptionString() + "\n";
        }
        strInfo += "|2|" + template.description;
        return strInfo;
    }

    public boolean isSKHThuong() {
        for (ItemOption itemOption : itemOptions) {
            if (itemOption.optionTemplate.id >= 210 && itemOption.optionTemplate.id <= 218) {
                return true;
            }
        }
        return false;
    }

    public boolean isParamSKH() {
        for (ItemOption itemOption : itemOptions) {
            if (itemOption.optionTemplate.id == 138
                    || itemOption.optionTemplate.id == 141
                    || itemOption.optionTemplate.id == 142 || itemOption.optionTemplate.id == 143) {
                return true;
            }
        }
        return false;
    }

    public boolean isItemKyGui() {
        for (ItemOption o : itemOptions) {
            int optionId = o.optionTemplate.id;
            if (optionId == 86 || optionId == 87) {
                return true;
            }
        }
        return false;
    }

    public int checkSet(Item it) {
        int result = -1;
        int[] doTD = new int[]{555, 556, 562, 563};

        int[] doNM = new int[]{557, 558, 564, 565};

        int[] doXD = new int[]{559, 560, 566, 567};
        switch (it.template.type) {
            case 0:// ao
                switch (it.template.gender) {
                    case 0:
                        result = doTD[0];
                        break;
                    case 1:
                        result = doNM[0];
                        break;
                    case 2:
                        result = doXD[0];
                        break;
                }
                break;
            case 1:// quan
                switch (it.template.gender) {
                    case 0:
                        result = doTD[1];
                        break;
                    case 1:
                        result = doNM[1];
                        break;
                    case 2:
                        result = doXD[1];
                        break;
                }
                break;
            case 2:// gang
                switch (it.template.gender) {
                    case 0:
                        result = doTD[2];
                        break;
                    case 1:
                        result = doNM[2];
                        break;
                    case 2:
                        result = doXD[2];
                        break;
                }
                break;
            case 3://giay
                switch (it.template.gender) {
                    case 0:
                        result = doTD[3];
                        break;
                    case 1:
                        result = doNM[3];
                        break;
                    case 2:
                        result = doXD[3];
                        break;
                }
                break;
            case 4:// rada
                result = 561;
                break;
        }
        return result;
    }

    public boolean isSKHVip() {
        for (ItemOption itemOption : itemOptions) {
            if (itemOption.optionTemplate.id >= 127 && itemOption.optionTemplate.id <= 144) {
                return true;
            }
        }
        return false;
    }

    public List<ItemOption> getDisplayOptions() {
        List<ItemOption> list = new ArrayList<>();
        if (itemOptions.isEmpty()) {
            list.add(OPTION_NULL);
        } else {
            for (ItemOption o : itemOptions) {
                list.add(o.format());
            }
        }
        return list;
    }

    public String getContent() {
        return "Yêu cầu sức mạnh " + this.template.strRequire + " trở lên";
    }

    public boolean canConsign() {
        for (ItemOption o : itemOptions) {
            int optionId = o.optionTemplate.id;
            if (optionId == 86 || optionId == 87) {
                return true;
            }
        }
        return false;
    }

    public boolean isItemFlagBagPet() {
        return this.template.id == 1111 || this.template.id == 2053 || this.template.id == 1230;
    }

    public boolean isItemThanLinh() {
        return this.template.id >= 555 && this.template.id <= 567;
    }

    public boolean isItemMountPet() {
        return this.template.id == 734 || this.template.id == 2085 || this.template.id == 1172 || this.template.id == 897 || this.template.id == 2133 || this.template.id == 2199;
    }

    public boolean isDHD() {
        return this.template.id >= 650 && this.template.id <= 662;
    }


    public boolean isDTL() {
        if (this.template.id >= 555 && this.template.id <= 567) {
            return true;
        }
        return false;
    }

    public void dispose() {
        this.template = null;
        this.info = null;
        this.content = null;
        if (this.itemOptions != null) {
            for (ItemOption io : this.itemOptions) {
                io.dispose();
            }
            this.itemOptions.clear();
        }
        this.itemOptions = null;
    }

    public short getId() {
        return template.id;
    }

    public byte getType() {
        return template.type;
    }

    public String getName() {
        return template.name;
    }

    public boolean isManhTS() {
        if (this.template.id >= 1066 && this.template.id <= 1070) {
            return true;
        }
        return false;
    }

    public boolean isdanangcapDoTs() {
        if (this.template.id >= 1074 && this.template.id <= 1078) {
            return true;
        }
        return false;
    }

    public boolean isItemThucAn() {
        return this.template.id >= 663 && this.template.id <= 667;
    }

    public boolean isItemSkien() {
        return this.template.id >= 694 && this.template.id <= 698;
    }

    public boolean isNgocRongNaec() {
        return this.template.id >= 1982 && this.template.id <= 1988;
    }

    public boolean isNgocRong() {
        return this.template.id >= 14 && this.template.id <= 20;
    }

    public boolean isDamayman() {
        if (this.template.id >= 1079 && this.template.id <= 1083) {
            return true;
        }
        return false;
    }

    public boolean isCongthucVip() {
        if (this.template.id >= 1084 && this.template.id <= 1086) {
            return true;
        }
        return false;
    }

    public boolean isCongthucNomal() {
        if (this.template.id >= 1071 && this.template.id <= 1073) {
            return true;
        }
        return false;
    }

    public boolean isSpl() {
        return this.template.id >= 441 && this.template.id <= 447;
    }

    public byte typeIdManh() {
        if (!isManhTS()) {
            return -1;
        }
        switch (this.template.id) {
            case 1066:
                return 0;
            case 1067:
                return 1;
            case 1070:
                return 2;
            case 1068:
                return 3;
            case 1069:
                return 4;
            default:
                return -1;
        }
    }

    public int getLevel() {
        if (!this.isNotNullItem()) {
            return 0;
        }
        for (ItemOption itemOption : this.itemOptions) {
            if (itemOption.optionTemplate.id == 72) {
                return itemOption.param;
            }
        }
        return 0;
    }

    public int getSaoDen() {
        if (!this.isNotNullItem()) {
            return 0;
        }
        for (ItemOption itemOption : this.itemOptions) {
            if (itemOption.optionTemplate.id == 107) {
                return itemOption.param;
            }
        }
        return 0;
    }

    public int getQuantityStar() {
        if (!this.isNotNullItem()) {
            return 0;
        }
        return (int) this.itemOptions.stream()
                .filter(itemOption -> itemOption.optionTemplate.id == 107 || itemOption.optionTemplate.id == 102)
                .count();
    }

    public boolean itemCanRemoveOption() {
        if (this.template.type >= 5) {
            return false;
        }
        List<ItemOption> options = this.itemOptions;
        return options.stream().anyMatch(itemOption
                -> ConstCombine.itemOptionsCanRemove.contains(itemOption.optionTemplate.id)
        );
    }


    public int getParams(int paramId) {
        if (! this.isNotNullItem()) {
            return 0;
        }
        for (ItemOption itemOption : this.itemOptions) {
            if (itemOption.optionTemplate.id == paramId) {
                return itemOption.param;
            }
        }
        return 0;
    }

    // ekko Chuyển từ đồ thần linh về đồ hủy diệt
    public int thanLinhToHuyDiet() {
        int hdID = 0;
        switch (this.template.id) {
            // trái đất
            case ConstItem.AO_THAN_LINH:
                hdID = ConstItem.AO_HUY_DIET;
                break;
            case ConstItem.QUAN_THAN_LINH:
                hdID = ConstItem.QUAN_HUY_DIET;
                break;
            case ConstItem.GANG_THAN_LINH:
                hdID = ConstItem.GANG_HUY_DIET;
                break;
            case ConstItem.GIAY_THAN_LINH:
                hdID = ConstItem.GIAY_HUY_DIET;
                break;
            // namec
            case ConstItem.AO_THAN_NAMEC:
                hdID = ConstItem.AO_HUY_DIET_NAMEC;
                break;
            case ConstItem.QUAN_THAN_NAMEC:
                hdID = ConstItem.QUAN_HUY_DIET_NAMEC;
                break;
            case ConstItem.GANG_THAN_NAMEC:
                hdID = ConstItem.GANG_HUY_DIET_NAMEC;
                break;
            case ConstItem.GIAY_THAN_NAMEC:
                hdID = ConstItem.GIAY_HUY_DIET_NAMEC;
                break;
            // Xayda
            case ConstItem.AO_THAN_XAYDA:
                hdID = ConstItem.AO_HUY_DIET_XAYDA;
                break;
            case ConstItem.QUAN_THAN_XAYDA:
                hdID = ConstItem.QUAN_HUY_DIET_XAYDA;
                break;
            case ConstItem.GANG_THAN_XAYDA:
                hdID = ConstItem.GANG_HUY_DIET_XAYDA;
                break;
            case ConstItem.GIAY_THAN_XAYDA:
                hdID = ConstItem.GIAY_HUY_DIET_XAYDA;
                break;
            // nhẫn
            case ConstItem.NHAN_THAN_LINH:
                hdID = ConstItem.NHAN_HUY_DIET;
                break;

            default:
                break;
        }
        return hdID;
    }
    // Ekko check xem item có phải là áo, quần, găng, giày, rada hay không
    public boolean itemIsClothes() {
        boolean result = false;
        switch (this.template.type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                result = true;
                break;
            default:
                break;
        }
        return  result;
    }
    // Ekko check xem item có phải pet không
    public boolean itemIsPet() {
        boolean result = false;
        switch (this.template.type) {
            case 21:
                result = true;
                break;
            default:
                break;
        }
        return  result;
    }
}
