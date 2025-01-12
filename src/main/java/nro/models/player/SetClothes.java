package nro.models.player;

import nro.consts.ConstOption;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.utils.Util;

/**
 * @Stole By Arriety
 */
public class SetClothes {

    private Player player;

    public SetClothes(Player player) {
        this.player = player;
    }

    public byte songoku2;
    public byte thienXinHang2;
    public byte kaioken2;

    public byte lienhoan2;
    public byte pikkoroDaimao2;
    public byte picolo2;

    public byte kakarot2;
    public byte cadic2;
    public byte nappa2;

    public byte songoku1;
    public byte thienXinHang1;
    public byte kaioken1;

    public byte lienhoan1;
    public byte pikkoroDaimao1;
    public byte picolo1;

    public byte kakarot1;
    public byte cadic1;
    public byte nappa1;
    public byte setSucDanh;

    public byte SetHuyDiet;

    public boolean godClothes;
    public int ctHaiTac = -1;
    public int ctBunmaXecXi = -1;
    public int setThienSu = 0;
    public byte setGohan;
    public byte setKHA;
    public byte setMaThuat;

    public void setup() {
        setDefault();
        setupSKT();
        this.godClothes = true;
        for (int i = 0; i < 5; i++) {
            Item item = this.player.inventory.itemsBody.get(i);
            if (item.isNotNullItem()) {
                if (item.template.id > 567 || item.template.id < 555) {
                    this.godClothes = false;
                    break;
                }
            } else {
                this.godClothes = false;
                break;
            }
        }

        for (int i = 0; i < this.player.inventory.itemsBody.size(); i++) {
            Item item = this.player.inventory.itemsBody.get(i);
            if (item.isNotNullItem()) {
                if (isSetThienSu(item.getId())) {
                    this.setThienSu++;
                }
            }
        }

        Item ct = this.player.inventory.itemsBody.get(5);
        if (ct.isNotNullItem()) {
            switch (ct.template.id) {
                case 618:
                case 619:
                case 620:
                case 621:
                case 622:
                case 623:
                case 624:
                case 626:
                case 627:
                    this.ctHaiTac = ct.template.id;
                    break;
                case 464:
                    this.ctBunmaXecXi = ct.template.id;
            }
        }
    }

    public static boolean isSetThanLinh(int[] array, int itemId) {
        for (int i : array) {
            if (i == itemId) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSetHD(int[] array, int itemId) {
        for (int i : array) {
            if (i == itemId) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSetThienSu(int itemId) {
        boolean result = false;

        switch (itemId) {
            case 1048:
            case 1049:
            case 1050:
            case 1051:
            case 1052:
            case 1053:
            case 1054:
            case 1055:
            case 1056:
            case 1057:
            case 1058:
            case 1059:
            case 1060:
            case 1061:
            case 1062:
                result = true;
                break;
            default:
                break;
        }

        return result;
    }

    private void setupSKT() {
        int[] doTD = new int[]{555, 556, 562, 563, 561};

        int[] doNM = new int[]{557, 558, 564, 565, 561};

        int[] doXD = new int[]{559, 560, 566, 567, 561};

        int[] doTD2 = new int[]{650, 651, 657, 658, 656};

        int[] doNM2 = new int[]{652, 653, 659, 660, 656};

        int[] doXD2 = new int[]{654, 655, 661, 662, 656};

        int isFullSetTL = 0;

        int isFullSetHUYDIET = 0;
        int isFullSetMaThuat = 0;

        for (int i = 0; i < 5; i++) {
            Item item = this.player.inventory.itemsBody.get(i);
            if (item.isNotNullItem()) {
                boolean isActSet = false;
                if (item.isDHD()) {
//                    isActSet = true;
                    SetHuyDiet++;
                }

                if (isSetThanLinh(doTD, item.getId()) || isSetThanLinh(doNM, item.getId()) || isSetThanLinh(doXD, item.getId())) {
                    isFullSetTL++;
                }
                if (isSetHD(doTD2, item.getId()) || isSetHD(doNM2, item.getId()) || isSetHD(doXD2, item.getId())) {
                    isFullSetHUYDIET++;
                }

                for (ItemOption io : item.itemOptions) {
                    switch (io.optionTemplate.id) {
                        // Set KH ẩn
                        case 249: {
                            setKHA++;
                            break;
                        }
                        case ConstOption.KI_MA_THUAT_TANG_PHAN_TRAM:
                        case ConstOption.HP_MA_THUAT_TANG_PHAN_TRAM:
                        case ConstOption.SUC_DANH_MA_THUAT_TANG_PHAN_TRAM:
                        {
                            setMaThuat++;
                            break;
                        }
                    }
                }

                for (ItemOption io : item.itemOptions) {
                    switch (io.optionTemplate.id) {
                        case 129:
                        case 141:
                            isActSet = true;
                            songoku2++;
                            break;

                        case 127:
                        case 139:
                            isActSet = true;
                            thienXinHang2++;
                            break;
                        case 128:
                        case 140:
                            isActSet = true;
                            kaioken2++;
                            break;
                        case 131:
                        case 143:
                            isActSet = true;
                            lienhoan2++;
                            break;
                        case 132:
                        case 252:
                            isActSet = true;
                            pikkoroDaimao2++;
                            break;
                        case 130:
                        case 142:
                            isActSet = true;
                            picolo2++;
                            break;
                        case 135:
                        case 138:
                            isActSet = true;
                            nappa2++;
                            break;
                        case 133:
                        case 136:
                            isActSet = true;
                            kakarot2++;
                            break;
                        case 134:
                        case 251:
                            isActSet = true;
                            cadic2++;
                            break;
                            // ekko set gohan
                        case 248:
                        case 250:
                            isActSet = true;
                            setGohan++;
                            break;
                        case 253:
                            setSucDanh++;
                            break;
                    }

                    if (isActSet) {
                        break;
                    }
                }
            } else {
                break;
            }
        }

        songoku2 = validateSKH(songoku2, isFullSetTL);
        thienXinHang2 = validateSKH(thienXinHang2, isFullSetTL);
        kaioken2 = validateSKH(kaioken2, isFullSetTL);
        lienhoan2 = validateSKH(lienhoan2, isFullSetTL);
        pikkoroDaimao2 = validateSKH(pikkoroDaimao2, isFullSetTL);
        picolo2 = validateSKH(picolo2, isFullSetTL);
        nappa2 = validateSKH(nappa2, isFullSetTL);
        kakarot2 = validateSKH(kakarot2, isFullSetTL);
        cadic2 = validateSKH(cadic2, isFullSetTL);
        setGohan = validateSKH(setGohan, isFullSetTL);

        songoku2 = validateSKH(songoku2, isFullSetHUYDIET);
        thienXinHang2 = validateSKH(thienXinHang2, isFullSetHUYDIET);
        kaioken2 = validateSKH(kaioken2, isFullSetHUYDIET);
        lienhoan2 = validateSKH(lienhoan2, isFullSetHUYDIET);
        pikkoroDaimao2 = validateSKH(pikkoroDaimao2, isFullSetHUYDIET);
        picolo2 = validateSKH(picolo2, isFullSetHUYDIET);
        nappa2 = validateSKH(nappa2, isFullSetHUYDIET);
        kakarot2 = validateSKH(kakarot2, isFullSetHUYDIET);
        cadic2 = validateSKH(cadic2, isFullSetHUYDIET);
        setGohan = validateSKH(setGohan, isFullSetHUYDIET);

        setKHA = validateSKH(setKHA, isFullSetHUYDIET);
        setMaThuat = validateSetMaThuat(setMaThuat, isFullSetMaThuat);
    }

    private byte validateSKH(byte option, int isFullSetTL) {
        if (option == 5 && isFullSetTL != 0 && isFullSetTL != 5) {
            option = 0;
        }

        return option;
    }

    private byte validateSetMaThuat(byte option, int isFullSetTL) {
        if (option == 5 && isFullSetTL != 0 && isFullSetTL != 5) {
            option = 0;
        }

        return option;
    }

    private void setDefault() {
        this.songoku1 = 0;
        this.thienXinHang1 = 0;
        this.kaioken1 = 0;
        this.lienhoan1 = 0;
        this.pikkoroDaimao1 = 0;
        this.picolo1 = 0;
        this.kakarot1 = 0;
        this.cadic1 = 0;
        this.nappa1 = 0;
        this.songoku2 = 0;
        this.SetHuyDiet = 0;
        this.thienXinHang2 = 0;
        this.kaioken2 = 0;
        this.lienhoan2 = 0;
        this.pikkoroDaimao2 = 0;
        this.picolo2 = 0;
        this.kakarot2 = 0;
        this.cadic2 = 0;
        this.nappa2 = 0;
        this.godClothes = false;
        this.ctHaiTac = -1;
        this.ctBunmaXecXi = -1;
        this.setThienSu = 0;
        this.setGohan = 0;
        this.setSucDanh = 0;
        this.setKHA = 0;
        this.setMaThuat = 0;
    }

    public void dispose() {
        this.player = null;
    }


//    135 138 (5 món +#% HP)
//    129 141 (5 món +#% sát thương Kamejoko)
//    130 142 (5 món +#% KI)
//    131 143 (5 món +#% sát thương Liên hoàn)

}
