package nro.services.func;

import nro.consts.*;
import nro.dialog.MenuDialog;
import nro.dialog.MenuRunable;
import nro.event.Event;
import nro.lib.RandomCollection;
import nro.manager.EventTurnManager;
import nro.manager.NamekBallManager;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.item_reward.Reward;
import nro.models.map.*;
import nro.models.map.Map;
import nro.models.map.dungeon.zones.ZSnakeRoad;
import nro.models.map.war.NamekBallWar;
import nro.models.player.Inventory;
import nro.models.player.MiniPet;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.server.Manager;
import nro.server.io.Message;
import nro.server.io.Session;
import nro.services.*;
import nro.utils.*;
import org.apache.log4j.Logger;

import java.util.*;

import nro.models.boss.BossManager;
import nro.models.mob.ArrietyDrop;
import nro.models.npc.specialnpc.EggLinhThu;

/**
 * @Build Arriety
 */
public class UseItem {

    private static final int ITEM_BOX_TO_BODY_OR_BAG = 0;
    private static final int ITEM_BAG_TO_BOX = 1;
    private static final int ITEM_BODY_TO_BOX = 3;
    private static final int ITEM_BAG_TO_BODY = 4;
    private static final int ITEM_BODY_TO_BAG = 5;
    private static final int ITEM_BAG_TO_PET_BODY = 6;
    private static final int ITEM_BODY_PET_TO_BAG = 7;

    private static final byte DO_USE_ITEM = 0;
    private static final byte DO_THROW_ITEM = 1;
    private static final byte ACCEPT_THROW_ITEM = 2;
    private static final byte ACCEPT_USE_ITEM = 3;

    private static UseItem instance;
    private static final Logger logger = Logger.getLogger(UseItem.class);

    private UseItem() {

    }

    public static UseItem gI() {
        if (instance == null) {
            instance = new UseItem();
        }
        return instance;
    }

    public void getItem(Session session, Message msg) {
        Player player = session.player;
        TransactionService.gI().cancelTrade(player);
        try {
            int type = msg.reader().readByte();
            int index = msg.reader().readByte();
            switch (type) {
                case ITEM_BOX_TO_BODY_OR_BAG:
                    InventoryService.gI().itemBoxToBodyOrBag(player, index);
                    TaskService.gI().checkDoneTaskGetItemBox(player);
                    break;
                case ITEM_BAG_TO_BOX:
                    InventoryService.gI().itemBagToBox(player, index);
                    break;
                case ITEM_BODY_TO_BOX:
                    InventoryService.gI().itemBodyToBox(player, index);
                    break;
                case ITEM_BAG_TO_BODY:
                    InventoryService.gI().itemBagToBody(player, index);
                    break;
                case ITEM_BODY_TO_BAG:
                    InventoryService.gI().itemBodyToBag(player, index);
                    break;
                case ITEM_BAG_TO_PET_BODY:
                    InventoryService.gI().itemBagToPetBody(player, index);
                    break;
                case ITEM_BODY_PET_TO_BAG:
                    InventoryService.gI().itemPetBodyToBag(player, index);
                    break;
            }
            player.setClothes.setup();
            if (player.pet != null) {
                player.pet.setClothes.setup();
            }

            Util.SendAuraRefresh(player);

            player.setClanMember();
            Service.getInstance().point(player);
        } catch (Exception e) {
            Log.error(UseItem.class, e);

        }
    }

    public void doItem(Player player, Message _msg) {
        TransactionService.gI().cancelTrade(player);
        Message msg;
        try {
            byte type = _msg.reader().readByte();
            int where = _msg.reader().readByte();
            int index = _msg.reader().readByte();
            switch (type) {
                case DO_USE_ITEM:
                    if (player != null && player.inventory != null) {
                        if (index != -1) {
                            if (index >= 0 && index < player.inventory.itemsBag.size()) {
                                Item item = player.inventory.itemsBag.get(index);
                                if (item.isNotNullItem()) {
                                    if (item.template.type == 21) {
                                        MiniPet.callMiniPet(player, item.template.id);
                                        InventoryService.gI().itemBagToBody(player, index);
                                        break;
                                    }
                                    if (item.template.type == 22) {
                                        msg = new Message(-43);
                                        msg.writer().writeByte(type);
                                        msg.writer().writeByte(where);
                                        msg.writer().writeByte(index);
                                        msg.writer().writeUTF("Bạn có muốn dùng " + player.inventory.itemsBag.get(index).template.name + "?");
                                        player.sendMessage(msg);
                                        msg.cleanup();
                                    } else if (item.template.type == 7) {
                                        msg = new Message(-43);
                                        msg.writer().writeByte(type);
                                        msg.writer().writeByte(where);
                                        msg.writer().writeByte(index);
                                        msg.writer().writeUTF("Bạn chắc chắn học " + player.inventory.itemsBag.get(index).template.name + "?");
                                        player.sendMessage(msg);
                                    } else if (player.isVersionAbove(220) && item.template.type == 23
                                            || item.template.type == 24
                                            || item.template.type == 11
                                            || item.template.type == 76
                                            || item.template.type == 69
                                            || item.template.type == 97
                                            || item.template.type == 99) {
                                        InventoryService.gI().itemBagToBody(player, index);
                                    } else if (item.template.id == 401) {
                                        msg = new Message(-43);
                                        msg.writer().writeByte(type);
                                        msg.writer().writeByte(where);
                                        msg.writer().writeByte(index);
                                        msg.writer().writeUTF("Sau khi đổi đệ sẽ mất toàn bộ trang bị trên người đệ tử nếu chưa tháo");
                                        player.sendMessage(msg);
                                    } else if (item.template.id == 2080) {
                                        msg = new Message(-43);
                                        msg.writer().writeByte(type);
                                        msg.writer().writeByte(where);
                                        msg.writer().writeByte(index);
                                        msg.writer().writeUTF("Sau khi đổi đệ sẽ mất toàn bộ trang bị trên người đệ tử nếu chưa tháo");
                                        player.sendMessage(msg);
                                    } else if (item.template.id == 1969) {
                                        msg = new Message(-43);
                                        msg.writer().writeByte(type);
                                        msg.writer().writeByte(where);
                                        msg.writer().writeByte(index);
                                        msg.writer().writeUTF("Sau khi đổi đệ sẽ mất toàn bộ trang bị trên người đệ tử nếu chưa tháo");
                                        player.sendMessage(msg);
                                    } else {
                                        useItem(player, item, index);
                                    }
                                }
                            }
                        } else {
                            InventoryService.gI().eatPea(player);
                        }
                    }
                    break;
                case DO_THROW_ITEM:
                    if (!(player.zone.map.mapId == 21 || player.zone.map.mapId == 22 || player.zone.map.mapId == 23)) {
                        Item item = null;
                        if (where == 0) {
                            if (index >= 0 && index < player.inventory.itemsBody.size()) {
                                item = player.inventory.itemsBody.get(index);
                            }
                        } else {
                            if (index >= 0 && index < player.inventory.itemsBag.size()) {
                                item = player.inventory.itemsBag.get(index);
                            }
                        }
                        if (item != null && item.isNotNullItem()) {
                            msg = new Message(-43);
                            msg.writer().writeByte(type);
                            msg.writer().writeByte(where);
                            msg.writer().writeByte(index);
                            msg.writer().writeUTF("Bạn chắc chắn muốn vứt " + item.template.name + "?");
                            player.sendMessage(msg);
                        }
                    } else {
                        Service.getInstance().sendThongBao(player, "Không thể thực hiện");
                    }
                    break;
                case ACCEPT_THROW_ITEM:
                    Service.getInstance().point(player);
                    InventoryService.gI().throwItem(player, where, index);
                    break;
                case ACCEPT_USE_ITEM:
                    if (index >= 0 && index < player.inventory.itemsBag.size()) {
                        Item item = player.inventory.itemsBag.get(index);
                        if (item.isNotNullItem()) {
                            useItem(player, item, index);
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            Log.error(UseItem.class, e);
        }
    }

    public void useSatellite(Player player, Item item) {
        Satellite satellite = null;
        if (player.zone != null) {
            int count = player.zone.getSatellites().size();
            if (count < 3) {
                switch (item.template.id) {
                    case ConstItem.VE_TINH_TRI_LUC:
                        satellite = new SatelliteMP(player.zone, ConstItem.VE_TINH_TRI_LUC, player.location.x, player.location.y, player);
                        break;
                    case ConstItem.VE_TINH_TRI_TUE:
                        satellite = new SatelliteExp(player.zone, ConstItem.VE_TINH_TRI_TUE, player.location.x, player.location.y, player);
                        break;

                    case ConstItem.VE_TINH_PHONG_THU:
                        satellite = new SatelliteDefense(player.zone, ConstItem.VE_TINH_PHONG_THU, player.location.x, player.location.y, player);
                        break;

                    case ConstItem.VE_TINH_SINH_LUC:
                        satellite = new SatelliteHP(player.zone, ConstItem.VE_TINH_SINH_LUC, player.location.x, player.location.y, player);
                        break;
                }
                if (satellite != null) {
                    InventoryService.gI().subQuantityItemsBag(player, item, 1);
                    Service.getInstance().dropItemMapForMe(player, satellite);
                    Service.getInstance().dropItemMap(player.zone, satellite);
                }
            } else {
                Service.getInstance().sendThongBaoOK(player, "Số lượng vệ tinh có thể đặt trong khu vực đã đạt mức tối đa.");
            }
        }
    }

    private void useItem(Player pl, Item item, int indexBag) {
        if (Event.isEvent() && Event.getInstance().useItem(pl, item)) {
            return;
        }
        if (item.template.strRequire <= pl.nPoint.power) {
            int type = item.getType();
            switch (type) {
                case 6:
                    InventoryService.gI().eatPea(pl);
                    break;
                case 33:
                    RadaService.getInstance().useItemCard(pl, item);
                    break;
                case 22:
                    useSatellite(pl, item);
                    break;
                case 97:
                case 99:
                    InventoryService.gI().itemBagToBody(pl, indexBag);
//                    Service.getInstance().sendEffPlayer(pl);

                    break;
                default:
                    switch (item.template.id) {
                        case 1948:
                            this.renameByItem(pl);
                            break;
                        case 1286:
                            openboxsukien(pl, item, 17);
                            break;
                        case 1283:
                            openboxsukien(pl, item, 16);
                            break;
                        case 457:
                            Input.gI().createFormBanThoiVang(pl);
                            break;
                        case 737:
                            openboxsukien(pl, item, 5);
                            break;
                        case 1967:
                            openQuaVip(pl, item);
                            break;
                        case 2013:
                            if (pl.nPoint.power >= 80_000_000_000L || pl.getSession().actived >= 1) {
                                openRandomSKH(pl, pl.gender, item);
                            } else {
                                Service.getInstance().sendThongBao(pl, "Cần 80 tỷ sm mới được sử dụng");
                            }
                            break;
                        case 2138:
                        case 2139:
                        case 2140:
                        case 2141:
                        case 2142:
                        case 2143:
                        case 2144:
                        case 2145:
                        case 2146:
                        case 2147:
                        case 2148: {
                            OpenRuongCapDHVT(pl, item);
                            break;
                        }
                        case 2149: {
                            OpenRuongCap12(pl, item);
                            break;
                        }
                        case ConstItem.BON_TAM_GO:
                        case ConstItem.BON_TAM_VANG:
                            useBonTam(pl, item);
                            break;
                        case 1954:
                            if (pl.pet != null && pl.pet.typePet == ConstPet.BILL_CON) {
                                upgradebillcon(pl, item);
                            }

                            if (pl.pet != null && pl.pet.typePet == ConstPet.MABU_NHI) {
                                upgradeMabuNhi(pl, item);
                            }

                            break;
                        case ConstItem.GOI_10_RADA_DO_NGOC:
                            findNamekBall(pl, item);
                            break;
                        case 2001:
                            capsule8thang3(pl, item);
                            break;
                        case ConstItem.CAPSULE_THOI_TRANG_30_NGAY:
                            capsuleThoiTrang(pl, item);
                            break;
                        case 570:
                            openWoodChest(pl, item);
                            break;
//                        case 648:
//                            openboxsukien(pl, item, 3);
//                            break;
                        case 2024:
                            hopQuaTanThu(pl, item);
                            break;
                        case 992:
//                            ChangeMapService.gI().goToPrimaryForest(pl);
                            break;
                        case 2078:
                            Input.gI().createFormTangGem(pl);
                            break;
                        case 2079:
                            Input.gI().createFormTangRuby(pl);
                            break;
                        case 2052:
                            UseItem.gI().blackGoku(pl);
                            break;
                        case 2020: //phiếu cải trang 20/10
                            openbox2010(pl, item);
                            break;

                        case 2037:
//                            openboxsukien(pl, item, 6);
                            Service.getInstance().sendThongBao(pl, "Su kien da ket thuc");
                            break;
                        case 2043:
                            openboxsukien(pl, item, 7);
                            break;
                        case 736:
                            openboxsukien(pl, item, 8);
                            break;
                        case 397:
                            openboxsukien(pl, item, 9);
                            break;
                        case 398:
                            openboxsukien(pl, item, 10);
                            break;
                        case 211: //nho tím
                        case 212: //nho xanh
                            eatGrapes(pl, item);
                            break;
                        case ConstItem.CAPSULE_VANG://574
                            openboxsukien(pl, item, 11);
                            break;
                        case 2065:// li xi
                            openboxsukien(pl, item, 12);
                            break;
                        case 2066:
                            openboxsukien(pl, item, 13);
                            break;
                        case 1990:
                            openboxsukien(pl, item, 14);
                            break;
                        case 2067:
                            openboxsukien(pl, item, 15);
                            break;
                        case 380: //cskb
                            openCSKB(pl, item);
                            break;
                        case 2116:
                            ruongquaticknap(pl, item);
                            break;
                        case 381: //cuồng nộ
                        case 382: //bổ huyết
                        case 383: //bổ khí
                        case 384: //giáp xên
                        case 385: //ẩn danh
                        case 379: //máy dò
                        case 663: //bánh pudding
                        case 664: //xúc xíc
                        case 665: //kem dâu
                        case 666: //mì ly
                        case 667: //sushi
                        case 1150:
                        case 1152:
                        case 1153:
                        case 1151:
                        case 752:
                        case 753:
                        case 2044:
                        case 638:
                        case 2134:
                        case 2205:
                        case 1944:
//                        case 694:
                            useItemTime(pl, item);
                            break;
                        case 1989:
                            BossManager.gI().showListBoss(pl);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 1965:
                            if (pl.pet == null) {
                                PetService.gI().createPetMabuHan(pl, pl.gender);
                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                                return;
                            }
                            if (pl.fusion.typeFusion != ConstPlayer.NON_FUSION) {
                                pl.pet.unFusion();
                            }
                            pl.pet.dispose();
                            MapService.gI().exitMap(pl.pet);
                            pl.pet = null;
                            PetService.gI().createPetMabuHan(pl, pl.gender);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 1955:
                            openMabuNhi(pl, item);
                            break;
                        case 2102:
                            openbillcon(pl, item);
                            break;
                        case 1953:
                            addItemVip2(pl, item);
                            break;
                        case 2124:
                            qua2cu(pl, item);
                            break;
                        case 2125:
                            qua5cu(pl, item);
                            break;
                        case 2126:
                            qua10cu(pl, item);
                            break;

                        case 1969:
                            changePetfide(pl, item);
//                            if (pl.pet == null) {
//                                PetService.gI().createPetFide(pl, pl.gender);
//                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
//                                return;
//                            }
//                            if (pl.fusion.typeFusion != ConstPlayer.NON_FUSION) {
//                                pl.pet.unFusion();
//                            }
//                            pl.pet.dispose();
//                            MapService.gI().exitMap(pl.pet);
//                            pl.pet = null;
//                            PetService.gI().createPetFide(pl, pl.gender);
//                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 2080:
                            changePetcell(pl, item);
//                            if (pl.pet == null) {
//                                PetService.gI().createWhisPet(pl, pl.gender);
//                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
//                                return;
//                            }
//                            if (pl.fusion.typeFusion != ConstPlayer.NON_FUSION) {
//                                pl.pet.unFusion();
//                            }
//                            MapService.gI().exitMap(pl.pet);
//                            pl.pet.dispose();
//                            pl.pet = null;
//                            PetService.gI().createWhisPet(pl, pl.gender);
//                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 521: //tdlt
                            useTDLT(pl, item);
                            break;
                        case 454: //bông tai
                            usePorata(pl);
                            break;
                        case 921: //bông tai c2
                            UseItem.gI().usePorata2(pl);
                            break;
                        case 2131: //bông tai c2
                            UseItem.gI().usePorata4(pl);
                            break;
                        case 1995:// bong tai
                            usePorata3(pl);
                            break;
                        case 193: //gói 10 viên capsule
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        case 194: //capsule đặc biệt
                            openCapsuleUI(pl);
                            break;
                        case 1991:
                            int oldRuby = pl.inventory.ruby;
                            pl.inventory.addRuby(Util.nextInt(1, 1000));
                            // ekko ghi log add ruby
                            Manager.addPlayerRubyHistory(pl.id, oldRuby, pl.inventory.ruby, "UseItem-useItem");
                            Service.getInstance().sendMoney(pl);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 401: //đổi đệ tử
                            changePet(pl, item);
                            break;
                        case 402: //sách nâng chiêu 1 đệ tử
                        case 403: //sách nâng chiêu 2 đệ tử
                        case 404: //sách nâng chiêu 3 đệ tử
                        case 759: //sách nâng chiêu 4 đệ tử
                        case 2038:
                            upSkillPet(pl, item);
                            break;
                        case 2042:
                            if (pl.egglinhthu == null) {
                                UseItem.gI().openEggLinhThu(pl, item);
                            } else {
                                Service.getInstance().sendThongBao(pl, "Vui lòng mở trứng trước!");
                            }
                            break;
                        case ConstItem.HOP_QUA_THUONG:
                            UseItem.gI().hopQuaKichHoat(pl, item);
                            break;
                        case ConstItem.HOP_QUA_SET_TNSM:
                            UseItem.gI().hopQuaSetTNSM(pl, item);
                            break;
                        case ConstItem.HOP_SKH_HUY_DIET:
                            UseItem.gI().hopQuaKichHoatHuyDiet(pl, item);
                            break;
                        case ConstItem.HOP_BTC3:
                            UseItem.gI().hopQuaBTC3(pl, item);
                            break;
                        case ConstItem.HOP_SET_THAN_LINH:
                            UseItem.gI().hopQuaThanLinh(pl, item);
                            break;
                        case 1228:
                            if (pl.pet == null) {
                                PetService.gI().createNormalPet(pl, pl.gender);
                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);

                            } else {
                                Service.getInstance().sendThongBao(pl, "bạn có đệ tử rồi");
                            }
//                           
                            break;
                        case ConstItem.CAPSULE_TET:
                            openCapsuleTet2022(pl, item);
                            break;
                        case 2004:
                            if (pl.pet == null) {
                                Service.getInstance().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }
                            if (pl.pet.playerSkill.skills.get(1).skillId != -1
                                    && pl.pet.playerSkill.skills.get(2).skillId != -1) {
                                pl.pet.openSkill2();
                                pl.pet.openSkill3();
                                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryService.gI().sendItemBags(pl);
                                Service.getInstance().sendThongBao(pl, "Đã đổi thành công chiêu 2 3 đệ tử");
                            } else {
                                Service.getInstance().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 3 chứ!");
                            }
                            break;
                        case 2039:
                            if (pl.pet == null) {
                                Service.getInstance().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }
                            if (pl.pet.playerSkill.skills.get(1).skillId != -1
                                    && pl.pet.playerSkill.skills.get(2).skillId != -1
                                    && pl.pet.playerSkill.skills.get(3).skillId != -1) {
                                pl.pet.openSkill5();
                                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryService.gI().sendItemBags(pl);
                                Service.getInstance().sendThongBao(pl, "Đã đổi thành công chiêu 5 đệ tử");
                            } else {
                                Service.getInstance().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 4 chứ!");
                            }
                            break;
//                        case 1167:
////                            Service.getInstance().sendThongBao(pl, "Bắn cái cc");
//                            activeBanhQuy(pl, item);
//                            break;
                        case 2107:
                            capsulehe(pl, item);
                            break;
                        case 2137: {
                            // Nhảy đến nv nappa
                            if (pl.playerTask.taskMain.id >= 18) {
                                Service.getInstance().sendThongBao(pl, "Vật phẩm này không có tác dụng với bạn!");
                            } else {
                                MenuDialog menu = new MenuDialog("Bạn có muốn đến nhiệm vụ Nappa và bỏ qua các nhiệm vụ trước đó không ?", new String[]{"Có", "Không"}, new MenuRunable() {
                                    @Override
                                    public void run() {
                                        switch (getIndexSelected()) {
                                            case 0:
                                                TaskService.gI().sendNextTaskMain(pl, 18);
                                                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                                InventoryService.gI().sendItemBags(pl);
                                                break;
                                            case 1:
                                                break;
                                        }
                                    }
                                });
                                menu.show(pl);
                            }
                            break;
                        }
                        case 2109:
                            if (pl.nPoint.power < 10_000_000_000L) {
                                Service.getInstance().sendThongBao(pl, "Cần 10 tỷ sm mới được sửa dụng");
                                return;
                            }
                            IntrinsicService.gI().changeIntrinsic2(pl);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 2106:
                            hopquahe(pl, item);
                            break;
                        case 2092:
                            hopquaticknap(pl, item);
                            break;
                        case 2122:
                            ruongquaticknap7(pl, item);
                            break;
                        case 2132:
                            ruongquaticknap8(pl, item);
                            break;
                        case 2005:
                            if (pl.pet == null) {
                                Service.getInstance().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }
                            if (pl.pet.playerSkill.skills.get(1).skillId != -1 && pl.pet.playerSkill.skills.get(3).skillId != -1) {
                                pl.pet.openSkill3();
                                pl.pet.openSkill4();
                                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryService.gI().sendItemBags(pl);
                                Service.getInstance().sendThongBao(pl, "Đã đổi thành công chiêu 2 3 4 đệ tử");
                            } else {
                                Service.getInstance().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 4 chứ!");
                            }
                            break;
                        case 1961:
                            hopQuaThienSu(pl);
                            break;
                        case 2010:
                            if (pl.pet == null) {
                                Service.getInstance().sendThongBao(pl, "Ngươi làm gì có đệ tử?");
                                break;
                            }
                            if (pl.pet.playerSkill.skills.get(1).skillId != -1 && pl.pet.playerSkill.skills.get(4).skillId != -1) {
                                pl.pet.openSkill4();
                                pl.pet.openSkill5();
                                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                                InventoryService.gI().sendItemBags(pl);
                                Service.getInstance().sendThongBao(pl, "Đã đổi thành công chiêu 4, 5 đệ tử");
                            } else {
                                Service.getInstance().sendThongBao(pl, "Ít nhất đệ tử ngươi phải có chiêu 5 chứ!");
                            }
                            break;
                        case 2095:
                            if (pl.nPoint.power < 80_000_000_000L) {
                                Service.getInstance().sendThongBao(pl, "Trên 80 tỷ mới dùng được");
                                return;
                            }
                            Service.getInstance().addSMTN(pl, (byte) 2, 30_000_000, false);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 2096:
                            if (pl.pet.nPoint.power < 80_000_000_000L) {
                                Service.getInstance().sendThongBao(pl, "Đệ trên 80 tỷ mới dùng được");
                                return;
                            }
//                            Service.getInstance().addSMTN(pl, (byte) 2, 20_000_000, false);
                            Service.getInstance().addSMTN(pl.pet, (byte) 2, 100_000_000, false);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 2011:
                            if (pl.nPoint.power > 80_000_000_000L || pl.pet.nPoint.power > 80_000_000_000L) {
                                Service.getInstance().sendThongBao(pl, "Bạn hoặc đệ tử đã trên 80 tỏi sức mạnh ko thể sửa dụng vật phẩm này");
                                return;
                            }
                            Service.getInstance().addSMTN(pl, (byte) 2, 200_000_000, false);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case 195:
                            if (pl.nPoint.power > 80_000_000_000L || pl.pet.nPoint.power > 80_000_000_000L) {
                                Service.getInstance().sendThongBao(pl, "Bạn hoặc đệ tử đã trên 80 tỏi sức mạnh ko thể sửa dụng vật phẩm này");
                                return;
                            }
//                            Service.getInstance().addSMTN(pl, (byte) 2, 20_000_000, false);
                            Service.getInstance().addSMTN(pl.pet, (byte) 2, 20_000_000, false);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            break;
                        case ConstItem.HOP_QUA_TICH_LUY:
                            OpenHopQuaTichLuy(pl, item);
                            break;
                        case ConstItem.HOP_QUA_MA_QUAI:
                            OpenHopQuaMaQuai(pl, item);
                        case ConstItem.HOA_DANG:
                            openHoaDang(pl, item);
                            break;
                        case ConstItem.HOP_KEO_MA_QUAI:
                            OpenHopKeoMaQuai(pl, item);
                            break;
                        case ConstItem.KEO_MA_QUAI:
                            UseKeoMaQuai(pl, item);
                            break;
                        case ConstItem.NAO_CHO:
                            UseNaoCho(pl, item);
                            break;
                        case ConstItem.HOP_QUA_THANG_10:
                            OpenHopQuaThang10(pl, item);
                            break;
                        case ConstItem.RUONG_NGOC_BANG:
                            OpenRuongNgocBang(pl, item);
                            break;
                        case ConstItem.HOP_QUA_GIANG_SINH:
                            OpenHopQuaGiangSinh(pl, item);
                            break;
                        case ConstItem.DE_TU_ZENO:
                            OpenDeZeno(pl, item);
                            break;
                        case ConstItem.HOP_QUA_NOEL:
                            OpenHopQuaNoel(pl, item);
                            break;
                        case ConstItem.RUONG_NGOC_HAC_AM:
                            OpenRuongNgocHacAm(pl, item);
                            break;
                        case ConstItem.DE_TU_WHIS:
                            OpenDeWhis(pl, item);
                            break;
//                        case ConstItem.DE_TU_GOKU:
//                            OpenDeGoku(pl, item);
//                            break;
                        case ConstItem.DE_TU_BLACK_GOKU:
                            OpenDeBlackGoku(pl, item);
                            break;
                        default:
                            switch (item.template.type) {
                                case 7: //sách học, nâng skill
                                    learnSkill(pl, item);
                                    break;
                                case 12: //ngọc rồng các loại
//                                    Service.getInstance().sendThongBaoOK(pl, "Bảo trì tính năng.");
                                    controllerCallRongThan(pl, item);
                                    break;
                                case 11: //item flag bag
                                    useItemChangeFlagBag(pl, item);
                                    break;
                                case 72: {
                                    InventoryService.gI().itemBagToBody(pl, indexBag);
                                    Service.getInstance().sendPetFollow(pl, (short) (item.template.iconID - 1));
                                    break;
                                }
                                case 27: {
                                    BoxUtil.OpenBoxItem(pl, item);
                                    break;
                                }
                            }
                    }
                    break;
            }
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBaoOK(pl, "Sức mạnh không đủ yêu cầu");
        }
    }

    private static void openHoaDang(Player pl, Item item) {
        Service.gI().addEffectChar(pl, 67, 1, 1, 1, 1);
        Service.gI().addEffectChar(pl, 67, 1, 1, 1, 0);
        Service.gI().addEffectChar(pl, 67, 1, 1, 1, -1);
        InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
//        pl.diem_skien++;

        InventoryService.gI().sendItemBags(pl);
    }

    private static void OpenRuongCapDHVT(Player pl, Item item) {
        // Cắt bỏ 14 ký tự đầu tiên
        int quantity = Integer.parseInt(item.template.name.substring(10)) * 10000;
        String name = "";

        Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantity);
        InventoryService.gI().addItemBag(pl, ngoc, 0);
        name += ngoc.getName();

        ngoc = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, quantity);
        InventoryService.gI().addItemBag(pl, ngoc, 0);
        name += ", " + ngoc.getName();

        InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);

        InventoryService.gI().sendItemBags(pl);
        Service.getInstance().sendThongBao(pl, "Bạn nhận được " + name + " số lượng: " + quantity);
    }

    // Ekko Mở Hộp quà tích lũy
    private static void OpenHopQuaTichLuy(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            String noti = "";
            // 50% Tỷ lệ nhận được random 1->3k Thỏi vàng VIP
            if (Util.isTrue(50, 100)) {
                int quantityTVV = Util.nextInt(1, 3000);
                Item tvv = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, quantityTVV);
                InventoryService.gI().addItemBag(pl, tvv, 0);
                noti += tvv.template.name + " số lượng " + quantityTVV + ", ";
            }
            // 20% Tỷ lệ nhận được random 1 món thần linh
//            else if (Util.isTrue(20, 100)) {
//                Item doTL = ArrietyDrop.GetRandomDoTL(pl);
//                InventoryService.gI().addItemBag(pl, doTL, 0);
//                noti += doTL.template.name + " số lượng " + 1 + ", ";
//            }
            // 20% Tỷ lệ nhận được 1 hộp SKH VIP
//            else if (Util.isTrue(20, 100)) {
//                Item skhVip = ItemService.gI().createNewItem((short) 2013, 1);
//                InventoryService.gI().addItemBag(pl, skhVip, 0);
//                noti += skhVip.template.name + " số lượng " + 1 + ", ";
//            }
            // 5% Tỷ lệ nhận được random 1-> 10 đá bảo vệ
            else if (Util.isTrue(5, 100)) {
                int quantity = Util.nextInt(1, 10);
                Item daBaoVe = ItemService.gI().createNewItem((short) 1143, quantity);
                InventoryService.gI().addItemBag(pl, daBaoVe, 0);
                noti += daBaoVe.template.name + " số lượng " + quantity + ", ";
            }

            // 5% Tỷ lệ nhận được Ván bay hoa đăng : 20% sức đánh,Hp,Ki
            else if (Util.isTrue(5, 100)) {
                Item vanBayVeSau = ItemService.gI().createNewItem((short) 2133, 1);
                vanBayVeSau.itemOptions.add(new ItemOption(50, 10));
                vanBayVeSau.itemOptions.add(new ItemOption(77, 10));
                vanBayVeSau.itemOptions.add(new ItemOption(103, 10));
                InventoryService.gI().addItemBag(pl, vanBayVeSau, 0); // 1 là số ngày hạn sử dụng
                noti += vanBayVeSau.template.name + " số lượng " + 1 + ", ";
            }
            // xịt thì ra TVV
            else {
                int quantityTVV = Util.nextInt(1, 3000);
                Item tvv = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, quantityTVV);
                InventoryService.gI().addItemBag(pl, tvv, 0);
                noti += tvv.template.name + " số lượng " + quantityTVV + ", ";
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            if (noti.isEmpty()) {
                Service.getInstance().sendThongBao(pl, "Đen quá con không nhận được gì rồi !");
            } else {
                String notiResult = noti.substring(0, noti.length() - 2);
                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
            }
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Ekko Mở Hộp quà ma quái
    private static void OpenHopQuaMaQuai(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            String noti = "";
            // 30% Tỷ lệ nhận random 1->10k ngọc xanh
            if (Util.isTrue(30, 100)) {
                int quantityNgoc = Util.nextInt(1, 10_000);
                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantityNgoc);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                noti += ngoc.template.name + " số lượng " + quantityNgoc + ", ";
            }
            // 30% tỷ lệ nhận random 1->10k hồng ngọc
            else if (Util.isTrue(30, 100)) {
                int quantityHN = Util.nextInt(1, 10_000);
                Item hongNgoc = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, quantityHN);
                InventoryService.gI().addItemBag(pl, hongNgoc, 0);
                noti += hongNgoc.template.name + " số lượng " + quantityHN + ", ";
            }
            // 15% Tỷ lệ nhận được random 1->1000 bình nước
            else if (Util.isTrue(15, 100)) {
                int quantityBN = Util.nextInt(1, 1000);
                Item binhNuoc = ItemService.gI().createNewItem((short) ConstItem.BINH_NUOC, quantityBN);
                InventoryService.gI().addItemBag(pl, binhNuoc, 0);
                noti += binhNuoc.template.name + " số lượng " + quantityBN + ", ";
            }
            // 10% Tỷ lệ nhận được radom 1->10 đá bảo vệ
            else if (Util.isTrue(10, 100)) {
                int quantityDBV = Util.nextInt(1, 10);
                Item daBaoVe = ItemService.gI().createNewItem((short) ConstItem.DA_BAO_VE, quantityDBV);
                InventoryService.gI().addItemBag(pl, daBaoVe, 0);
                noti += daBaoVe.template.name + " số lượng " + quantityDBV + ", ";
            }
            //   10% Tỷ lệ nhận được cải trang Gogeta ssj4 70% sđ,hp,ki
            else if (Util.isTrue(10, 100)) {
                // cải trang Gogeta ssj4 70% sđ,hp,ki vĩnh viễn
                Item ctGS = ItemService.gI().createNewItem((short) ConstItem.CAI_TRANG_GOGETA_SSJ4, 1);
                ctGS.itemOptions.add(new ItemOption(50, 70)); // Sức đánh 70%
                ctGS.itemOptions.add(new ItemOption(77, 70)); // HP 70%
                ctGS.itemOptions.add(new ItemOption(103, 70)); // Ki 70%
                InventoryService.gI().addItemBag(pl, ctGS, 0);
                noti += ctGS.template.name + ", ";
            }
            // 5% Tỷ lệ nhận Pet gà chín cựa 30% sức đánh,Hp,Ki
            else if (Util.isTrue(5, 100)) {
                Item petGCC = ItemService.gI().createNewItem((short) ConstItem.PET_GA_CHIN_CUA, 1);
                petGCC.itemOptions.add(new ItemOption(50, 30)); // Sức đánh 30%
                petGCC.itemOptions.add(new ItemOption(77, 30)); // HP 30%
                petGCC.itemOptions.add(new ItemOption(103, 30)); // Ki 30%
                InventoryService.gI().addItemBag(pl, petGCC, 0);
                noti += petGCC.template.name + ", ";
            }
            // xịt thì ra ngọc xanh
            else {
                int quantityNgoc = Util.nextInt(1, 10_000);
                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantityNgoc);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                noti += ngoc.template.name + " số lượng " + quantityNgoc + ", ";
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            // thông báo
            String notiResult = noti.substring(0, noti.length() - 2);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Ekko Mở Hộp kẹo ma quái
    private static void OpenHopKeoMaQuai(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            String noti = "";
            //   10% Tỷ lệ nhận được item "Kẹo ma quái"
            if (Util.isTrue(10, 100)) {
                Item keo = ItemService.gI().createNewItem((short) ConstItem.KEO_MA_QUAI, 1);
                InventoryService.gI().addItemBag(pl, keo, 0);
                noti += keo.template.name + " số lượng " + 1 + ", ";
            }
            // 20% Tỷ lệ nhận được 1 Hộp SKH VIP
            else if (Util.isTrue(20, 100)) {
//                Item skhVip = ItemService.gI().createNewItem((short) ConstItem.SKH_VIP, 1);
//                InventoryService.gI().addItemBag(pl, skhVip, 0);
//                noti += skhVip.template.name + " số lượng " + 1 + ", ";
            }
            // 10% Tỷ lệ nhận được item "Não chó"
            else if (Util.isTrue(10, 100)) {
                Item naoCho = ItemService.gI().createNewItem((short) ConstItem.NAO_CHO, 1);
                InventoryService.gI().addItemBag(pl, naoCho, 0);
                noti += naoCho.template.name + " số lượng " + 1 + ", ";
            }
            // 20% Tỷ lệ nhận được random 1-> 100 item "Sách bí ngô"
            else if (Util.isTrue(20, 100)) {
                int quantitySBN = Util.nextInt(1, 100);
                Item sachBiNgo = ItemService.gI().createNewItem((short) ConstItem.SACH_BI_NGO, quantitySBN);
                InventoryService.gI().addItemBag(pl, sachBiNgo, 0);
                noti += sachBiNgo.template.name + " số lượng " + quantitySBN + ", ";
            }
            // 40% Tỷ lệ nhận được random viên ngọc rồng đen từ 1->7 sao
            else {
                int ngocDenID = Util.nextInt(ConstItem.NGOC_DEN_1_SAO, ConstItem.NGOC_DEN_7_SAO);
                Item ngocDen = ItemService.gI().createNewItem((short) ngocDenID, 1);
                InventoryService.gI().addItemBag(pl, ngocDen, 0);
                noti += ngocDen.template.name + " số lượng " + 1 + ", ";
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            // thông báo
            String notiResult = noti.substring(0, noti.length() - 2);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Ekko dùng kẹo ma quái
    private static void UseKeoMaQuai(Player pl, Item item) {
        //  trong vòng 10 phút player sẽ có thể gây dame tối đa là -3 lên boss "Ma Xương"
        if (pl != null) {
            if (pl.itemTime != null) {
                if (pl.itemTime.rateDameMaXuong) {
                    Service.getInstance().sendThongBao(pl, "Kẹo ma quái vẫn đang còn hiệu lực");
                    return;
                }
                pl.itemTime.lastTimeDameMaXuong = System.currentTimeMillis();
                pl.itemTime.rateDameMaXuong = true;
                ItemTimeService.gI().sendAllItemTime(pl);
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
        } else {
            Service.getInstance().sendThongBao(pl, "Đã có lỗi xảy ra");
        }
    }

    // Ekko dùng não chó
    private static void UseNaoCho(Player pl, Item item) {
        // Khi sử dụng item não chó trong vòng 10 phút player sẽ được tăng 10% sức đánh,Hp,Ki
        if (pl != null) {
            if (pl.itemTime != null) {
                if (pl.itemTime.rateNaoCho) {
                    Service.getInstance().sendThongBao(pl, "Não chó vẫn đang còn hiệu lực");
                    return;
                }
                pl.itemTime.lastTimeNaoCho = System.currentTimeMillis();
                pl.itemTime.rateNaoCho = true;
                ItemTimeService.gI().sendAllItemTime(pl);
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
        } else {
            Service.getInstance().sendThongBao(pl, "Đã có lỗi xảy ra");
        }
    }

    // Ekko Mở Hộp quà Noel
    private static void OpenHopQuaNoel(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            StringBuilder noti = new StringBuilder();

            // Danh sách phần thưởng với tỷ lệ và cấu hình
            List<Reward> rewards = Arrays.asList(
                    new Reward(50, () -> createItem(ConstItem.NGOC_RONG_BANG_1_SAO, ConstItem.NGOC_RONG_BANG_7_SAO, 1)),
                    new Reward(24, () -> createItem(ConstItem.HOP_QUA_NOEL, 1)),
                    new Reward(10, () -> createItemWithOption(ConstItem.SAO_PHA_LE_SIEU_CAP_KI, 1, ConstOption.KI_CONG_PHAN_TRAM, 8)),
                    new Reward(5, () -> createItemWithOption(ConstItem.SAO_PHA_LE_SIEU_CAP_HP, 1, ConstOption.HP_CONG_PHAN_TRAM, 8)),
                    new Reward(5, () -> createItemWithOption(ConstItem.SAO_PHA_LE_SIEU_CAP_SUC_DANH, 1, ConstOption.SUC_DANH_CONG_PHAN_TRAM, 4)),
                    new Reward(5, () -> createItemWithMultipleOptions(ConstItem.LINH_THU_TUAN_LOC, 1,
                            new ItemOption(ConstOption.HP_CONG_PHAN_TRAM, 50),
                            new ItemOption(ConstOption.KI_CONG_PHAN_TRAM, 50),
                            new ItemOption(ConstOption.SUC_DANH_CONG_PHAN_TRAM, 50),
                            new ItemOption(ConstOption.PHAN_TRAM_SAT_THUONG_LEN_BOSS, 5))),
                    new Reward(1, () -> createItemWithMultipleOptions(ConstItem.CAI_TRANG_ZENO, 1,
                            new ItemOption(ConstOption.HP_CONG_PHAN_TRAM, 120),
                            new ItemOption(ConstOption.KI_CONG_PHAN_TRAM, 120),
                            new ItemOption(ConstOption.PHAN_TRAM_SAT_THUONG_LEN_BOSS, 10),
                            new ItemOption(ConstOption.PHAN_TRAM_SUC_DANH_CHI_MANG, 105)))
            );

            // Thực hiện kiểm tra tỷ lệ và thêm phần thưởng vào túi
            for (Reward reward : rewards) {
                if (Util.isTrue(reward.chance, 100)) {
                    Item itemReceived = reward.createItem.get();
                    InventoryService.gI().addItemBag(pl, itemReceived, 0);
                    noti.append(itemReceived.template.name).append(" số lượng ").append(1).append(", ");
                    break;
                }
            }

            // Xóa 1 item từ túi và cập nhật
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);

            // Thông báo
            String notiResult = noti.length() > 0 ? noti.substring(0, noti.length() - 2) : "";
            if (notiResult.isEmpty()) {
                Item itemReceived = createItem(ConstItem.HOP_QUA_NOEL, 1);
                InventoryService.gI().addItemBag(pl, itemReceived, 0);
                Service.getInstance().sendThongBao(pl, "Bạn nhận được 1 " + itemReceived.template.name);
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

    // Ekko Mở Hộp quà tháng 10
    private static void OpenHopQuaThang10(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            String noti = "";
            // 35% tỷ lệ nhận random 1->10k ngọc xanh
            if (Util.isTrue(35, 100)) {
                int quantityNgoc = Util.nextInt(1, 10_000);
                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantityNgoc);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                noti += ngoc.template.name + " số lượng " + quantityNgoc + ", ";
            }
            // 20% Tỷ lệ nhận được radom 1->10 đá bảo vệ
            else if (Util.isTrue(20, 100)) {
                int quantityDBV = Util.nextInt(1, 10);
                Item daBaoVe = ItemService.gI().createNewItem((short) ConstItem.DA_BAO_VE, quantityDBV);
                InventoryService.gI().addItemBag(pl, daBaoVe, 0);
                noti += daBaoVe.template.name + " số lượng " + quantityDBV + ", ";
            }
            // 15% Tỷ lệ nhận được random 1->1000 bình nước nâng btc3
            else if (Util.isTrue(15, 100)) {
                int quantityBN = Util.nextInt(1, 1000);
                Item binhNuoc = ItemService.gI().createNewItem((short) ConstItem.BINH_NUOC, quantityBN);
                InventoryService.gI().addItemBag(pl, binhNuoc, 0);
                noti += binhNuoc.template.name + " số lượng " + quantityBN + ", ";
            }
            // 15% Tỷ lệ nhận random 1 viên ngọc rồng đen từ 1->7 sao
            else if (Util.isTrue(15, 100)) {
                int ngocDenID = Util.nextInt(ConstItem.NGOC_DEN_1_SAO, ConstItem.NGOC_DEN_7_SAO);
                Item ngocDen = ItemService.gI().createNewItem((short) ngocDenID, 1);
                InventoryService.gI().addItemBag(pl, ngocDen, 0);
                noti += ngocDen.template.name + " số lượng " + 1 + ", ";
            }
            // 5% Pet Thỏ cam trung thu (special option tăng 30% sđ,hp,ki - x3 lượng ngọc nhận được khi up quái)
            else if (Util.isTrue(5, 100)) {
                Item petThoCam = ItemService.gI().createNewItem((short) ConstItem.PET_THO_CAM_TRUNG_THU, 1);
                petThoCam.itemOptions.add(new ItemOption(40, 30)); // special option tăng 30% sđ,hp,ki
                petThoCam.itemOptions.add(new ItemOption(234, 0)); // x3 lượng ngọc nhận được khi up quái
                InventoryService.gI().addItemBag(pl, petThoCam, 0);
                noti += petThoCam.template.name + ", ";
            }
            // 5% Tỷ lệ nhận Ván bay phượng hoàn lửa (30% Hp,sđ,ki - 5% sát thương lên boss)
            else if (Util.isTrue(5, 100)) {
                Item vanBay = ItemService.gI().createNewItem((short) ConstItem.PHUONG_HOANG_LUA, 1);
                vanBay.itemOptions.add(new ItemOption(50, 30)); // Sức đánh 30%
                vanBay.itemOptions.add(new ItemOption(77, 30)); // HP 30%
                vanBay.itemOptions.add(new ItemOption(103, 30)); // Ki 30%
                vanBay.itemOptions.add(new ItemOption(244, 5)); // 5% sát thương lên boss
                InventoryService.gI().addItemBag(pl, vanBay, 0);
                noti += vanBay.template.name + ", ";
            }
            // xịt thì ra ngọc xanh
            else {
                int quantityNgoc = Util.nextInt(1, 10_000);
                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantityNgoc);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                noti += ngoc.template.name + " số lượng " + quantityNgoc + ", ";
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            // thông báo
            String notiResult = noti.substring(0, noti.length() - 2);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Ekko Mở Hộp quà tháng 10
    private static void OpenRuongNgocBang(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) >= 6) {
            int slItem = 1;
            for (int ngocID = ConstItem.NGOC_RONG_BANG_1_SAO; ngocID <= ConstItem.NGOC_RONG_BANG_7_SAO; ngocID++) {
                Item ngoc = ItemService.gI().createNewItem((short) ngocID, slItem);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được 1 bộ Ngọc Rồng Băng");
        } else {
            Service.getInstance().sendThongBao(pl, "Bạn cần 6 ô hành trang trống");
        }
    }
    // Ekko
    private static void OpenRuongNgocHacAm(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) >= 6) {
            int slItem = 1;
            for (int ngocID = ConstItem.NGOC_HAC_AM_1_SAO; ngocID <= ConstItem.NGOC_HAC_AM_7_SAO; ngocID++) {
                Item ngoc = ItemService.gI().createNewItem((short) ngocID, slItem);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được 1 bộ Ngọc Rồng Hắc Ám");
        } else {
            Service.getInstance().sendThongBao(pl, "Bạn cần 6 ô hành trang trống");
        }
    }

    // Ekko Mở Hộp quà giáng sinh
    private static void OpenHopQuaGiangSinh(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            String noti = "";
            int slItem = 1;
            Item itemReward = null;
            // 10% Tỷ lệ Random 1 viên ngọc rồng băng từ 1->7 sao
            if (Util.isTrue(10, 100)) {
                int ngocID = Util.nextInt(ConstItem.NGOC_RONG_BANG_1_SAO, ConstItem.NGOC_RONG_BANG_7_SAO);
                itemReward = ItemService.gI().createNewItem((short) ngocID, slItem);
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            // 10% Tỷ lệ nhận được random 1 viên ngọc rồng thường từ 1->7 sao
            else if (Util.isTrue(10, 100)) {
                int ngocID = Util.nextInt(ConstItem.NGOC_RONG_1_SAO, ConstItem.NGOC_RONG_7_SAO);
                itemReward = ItemService.gI().createNewItem((short) ngocID, slItem);
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            // 40% Tỷ lệ nhận được x1 Kẹo tuyết
            else if (Util.isTrue(40, 100)) {
                itemReward = ItemService.gI().createNewItem((short) ConstItem.KEO_NGUOI_TUYET, slItem);
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            // 10% Tỷ lệ nhận được túi quà noel (id 823) - random 1->35% sức đánh,Hp,Ki
            else if (Util.isTrue(10, 100)) {
                itemReward = ItemService.gI().createNewItem((short) ConstItem.TUI_QUA, slItem);
                int randomSD = Util.nextInt(1, 35);
                int randomHP = Util.nextInt(1, 35);
                int randomKI = Util.nextInt(1, 35);
                itemReward.itemOptions.add(new ItemOption(50, randomSD)); // Sức đánh
                itemReward.itemOptions.add(new ItemOption(77, randomHP)); // HP
                itemReward.itemOptions.add(new ItemOption(103, randomKI)); // Ki
                itemReward.itemOptions.add(new ItemOption(30, 0)); // Không thể giao dịch
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            // 30% Tỷ lệ nhận được Pet tuần lộc nhí - random 1->40% sức đánh,Hp,KI
            else if (Util.isTrue(30, 100)) {
                itemReward = ItemService.gI().createNewItem((short) ConstItem.TUAN_LOC_NHI, slItem);
                int randomSD = Util.nextInt(1, 40);
                int randomHP = Util.nextInt(1, 40);
                int randomKI = Util.nextInt(1, 40);
                itemReward.itemOptions.add(new ItemOption(50, randomSD)); // Sức đánh
                itemReward.itemOptions.add(new ItemOption(77, randomHP)); // HP
                itemReward.itemOptions.add(new ItemOption(103, randomKI)); // Ki
                itemReward.itemOptions.add(new ItemOption(30, 0)); // Không thể giao dịch
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            // xịt thì ra kẹo người tuyết
            else {
                itemReward = ItemService.gI().createNewItem((short) ConstItem.KEO_NGUOI_TUYET, slItem);
                InventoryService.gI().addItemBag(pl, itemReward, 0);
                noti += itemReward.template.name + " số lượng " + slItem + ", ";
            }
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            // thông báo
            String notiResult = noti.substring(0, noti.length() - 2);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + notiResult);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    // Ekko Mở nhận đệ tử Zeno
    private static void OpenDeZeno(Player pl, Item item) {
        NpcService.gI().createMenuConMeo(pl,
                ConstNpc.DE_TU_ZENO, -1, "Hãy tháo đồ hành trang kẻo mất nha con! \n Con có muốn nhận đệ tử Zeno không?",
                "Đồng ý", "Từ chối");
    }

    // Ekko Mở nhận đệ tử Whis
    private static void OpenDeWhis(Player pl, Item item) {
        NpcService.gI().createMenuConMeo(pl,
                ConstNpc.DE_TU_WHIS, -1, "Hãy tháo đồ hành trang kẻo mất nha con! \n Con có muốn nhận đệ tử Whis không?",
                "Đồng ý", "Từ chối");
    }

    // Ekko Mở nhận đệ tử Goku
//    private static void OpenDeGoku(Player pl, Item item) {
//        NpcService.gI().createMenuConMeo(pl,
//                ConstNpc.DE_TU_GOKU, -1, "Hãy tháo đồ hành trang kẻo mất nha con! \n Con có muốn nhận đệ tử Goku không?",
//                "Đồng ý", "Từ chối");
//    }

    // Ekko Mở nhận đệ tử Black Goku
    private static void OpenDeBlackGoku(Player pl, Item item) {
        NpcService.gI().createMenuConMeo(pl,
                ConstNpc.DE_TU_BLACK_GOKU, -1, "Hãy tháo đồ hành trang kẻo mất nha con! \n Con có muốn nhận đệ tử Black Goku không?",
                "Đồng ý", "Từ chối");
    }

    private static void OpenRuongCap12(Player pl, Item item) {
//        int[] splSuper = new int[]{2118, 2119, 2120};
//        int optionId = 0, optionValue = 0;
//
//        // Tạo đối tượng Random
//        Random random = new Random();
//
//        // Sinh chỉ số ngẫu nhiên từ 0 đến (array.length - 1)
//        int randomIndex = random.nextInt(splSuper.length);
//
//        // Lấy phần tử ngẫu nhiên từ mảng
//        int randomElement = splSuper[randomIndex];
//
//        switch (randomElement) {
//            case 2118: {
//                optionId = 77;
//                optionValue = 8;
//                break;
//            }
//            case 2119: {
//                optionId = 103;
//                optionValue = 8;
//                break;
//            }
//            case 2120: {
//                optionId = 50;
//                optionValue = 4;
//                break;
//            }
//        }
//
//        int quantity = 150000;
//        String name = "";
//
//        Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantity);
//        InventoryService.gI().addItemBag(pl, ngoc, 0);
//        name += ngoc.getName();
//
//        ngoc = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, quantity);
//        InventoryService.gI().addItemBag(pl, ngoc, 0);
//        name += ", " + ngoc.getName();
//
//        ngoc = ItemService.gI().createNewItem((short) randomElement, 1);
//        ngoc.itemOptions.add(new ItemOption(optionId, optionValue));
//        InventoryService.gI().addItemBag(pl, ngoc, 999);
//
//        InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
//
//        InventoryService.gI().sendItemBags(pl);
//
//        Service.getInstance().sendThongBao(pl, "Bạn nhận được " + name + " số lượng: " + quantity + " và 1 " + ngoc.getName());
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int itemIDRandom = Util.nextInt(ConstItem.NGOC_HAC_AM_4_SAO, ConstItem.NGOC_HAC_AM_7_SAO);
            int quantityRandom = Util.nextInt(1, 10);
            Item itemRandom = ItemService.gI().createNewItem((short) itemIDRandom, quantityRandom);
            InventoryService.gI().addItemBag(pl, itemRandom, 0);
            InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
            InventoryService.gI().sendItemBags(pl);
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + itemRandom.template.name + " số lượng " + quantityRandom);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void upgradeMabuNhi(Player player, Item item) {
        if (player.pet == null) {
            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Mabư Nhí");
            return;
        }
        if (player.pet.typePet != ConstPet.MABU_NHI) {
            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Mabư Nhí");
            return;
        }
        if (player.pet.getLever() >= 5) {
            Service.getInstance().sendThongBaoOK(player, "Viên đá chỉ giúp bạn nâng lên lever 5\nNếu bạn muốn nâng cấp nữa hãy bay đến\ngặp Tổ Sư Kaio để nâng cấp tiếp");
            return;
        }
        player.pet.setLever(player.pet.getLever() + 1);
        player.lvpet += 1;
        InventoryService.gI().sendItemBags(player);
        Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
        Service.getInstance().player(player);
        player.zone.load_Me_To_Another(player);
        player.zone.loadAnotherToMe(player);
        Service.getInstance().point(player);
        Service.getInstance().Send_Caitrang(player);
    }

    private void upgradebillcon(Player player, Item item) {
        if (player.pet == null) {
            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Bill Con");
            return;
        }
        if (player.pet.typePet != ConstPet.BILL_CON) {
            Service.getInstance().sendThongBao(player, "Yêu cầu bạn phải có đệ tử Bill Con");
            return;
        }
        if (player.pet.getLever() >= 7) {
            Service.getInstance().sendThongBaoOK(player, "Tối đa 7 cấp");
            return;
        }
        player.pet.setLever(player.pet.getLever() + 1);
        player.lvpet += 1;
        InventoryService.gI().sendItemBags(player);
        Service.getInstance().sendThongBao(player, "Chúc mừng bạn đã nâng cấp Thành Công");
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
        Service.getInstance().player(player);
        player.zone.load_Me_To_Another(player);
        player.zone.loadAnotherToMe(player);
        Service.getInstance().point(player);
        Service.getInstance().Send_Caitrang(player);
    }

    private int getRandomItem6() {
        int[] weights = {5, 10, 30, 20, 25, 15};
        int totalWeight = 100;
        int random = Util.nextInt(1, totalWeight + 1);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (random <= sum) {
                return i + 1;
            }
        }
        return 1; // Mặc định trả về case 1 nếu có lỗi xảy ra
    }

    private void ruongquaticknap(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int randomItem = getRandomItem6();

            // Tạo item tương ứng với số ngẫu nhiên đã chọn
            switch (randomItem) {
                case 1:
                    //bóng cá nóc
                    Item gokuVoCuc = ItemService.gI().createNewItem((short) 1285, 1);
                    gokuVoCuc.itemOptions.add(new ItemOption(50, 25));
                    gokuVoCuc.itemOptions.add(new ItemOption(77, 25));
                    gokuVoCuc.itemOptions.add(new ItemOption(103, 25));
                    InventoryService.gI().addItemBag(pl, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận đuợc bóng cá nóc vàng cute");
                    break;
                case 2:
                    // pet shiba
                    Item gokuVoCucForever = ItemService.gI().createNewItem((short) 2117, 1);
                    gokuVoCucForever.itemOptions.add(new ItemOption(50, Util.nextInt(20, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(77, Util.nextInt(20, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(103, Util.nextInt(20, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(234, 1));
                    InventoryService.gI().addItemBag(pl, gokuVoCucForever, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Pet shiba may mắn");
                    break;

                case 3:
                    // ngọc xanh
                    Item vegetaBlue2 = ItemService.gI().createNewItem((short) 77);
                    int quantity = Util.nextInt(1, 1000);
                    vegetaBlue2.quantity = quantity;
                    InventoryService.gI().addItemBag(pl, vegetaBlue2, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được  x" + quantity + " " + vegetaBlue2.template.name);
                    break;
                case 4:
                    // súp
                    Item da1 = ItemService.gI().createNewItem((short) 1968, 1);

                    InventoryService.gI().addItemBag(pl, da1, 0); // 1 là số ngày hạn sử dụng

                    Service.getInstance().sendThongBao(pl, "Bạn nhận x1 súp cá nóc");
                    break;
                case 5:
                    // hồng ngọc
                    Item hongngoc = ItemService.gI().createNewItem((short) 861);
                    int quantity3 = Util.nextInt(1, 1000);
                    hongngoc.quantity = quantity3;
                    // ekko ghi log add ruby
                    Manager.addPlayerRubyHistory(pl.id, pl.inventory.ruby, pl.inventory.ruby + quantity3, "UseItem-ruongquaticknap");
                    InventoryService.gI().addItemBag(pl, hongngoc, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được  x" + quantity3 + " " + hongngoc.template.name);
                    break;

                case 6:
                    // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)

                    Item dalinhhon = ItemService.gI().createNewItem((short) 1994);
                    int quantity2 = Util.nextInt(1, 10);
                    dalinhhon.quantity = quantity2;
                    InventoryService.gI().addItemBag(pl, dalinhhon, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được  x" + quantity2 + " " + dalinhhon.template.name);
                    break;
            }

            // Sau khi thêm item, cập nhật túi đồ của người chơi
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private int getRandomItem7() {
        int[] weights = {40, 20, 20, 20};
        int totalWeight = 100;
        int random = Util.nextInt(1, totalWeight + 1);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (random <= sum) {
                return i + 1;
            }
        }
        return 1; // Mặc định trả về case 1 nếu có lỗi xảy ra
    }

    private void ruongquaticknap7(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int randomItem = getRandomItem7();

            // Tạo item tương ứng với số ngẫu nhiên đã chọn
            switch (randomItem) {
                case 1:
                    Random random = new Random();

                    // Tạo một mã ngẫu nhiên từ 2045 đến 2051
                    int randomItemCode = 2045 + random.nextInt(7);

                    // Tạo item mới với mã ngẫu nhiên này
                    Item cc = ItemService.gI().createNewItem((short) randomItemCode);

                    // Thêm item vào túi đồ của người chơi
                    InventoryService.gI().addItemBag(pl, cc, 99999);
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được " + cc.template.name);

                    break;
                case 2:
                    // pet shiba
                    Item gokuVoCucForever = ItemService.gI().createNewItem((short) 1288, 1);

                    InventoryService.gI().addItemBag(pl, gokuVoCucForever, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được thỏi vàng vip");
                    break;

                case 3:
                    // Đá bảo vệ (Khóa)
                    Item vegetaBlue2 = ItemService.gI().createNewItem((short) 1143);

                    InventoryService.gI().addItemBag(pl, vegetaBlue2, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được" + vegetaBlue2.template.name);
                    break;
                case 4:
                    // cải trang Sói Basil
//                    Item da1 = ItemService.gI().createNewItem((short) 730, 1);
//                    da1.itemOptions.add(new ItemOption(50, 1));
//                    da1.itemOptions.add(new ItemOption(77, 1));
//                    da1.itemOptions.add(new ItemOption(103, 1));
//                    da1.itemOptions.add(new ItemOption(14, 1));
//                    da1.itemOptions.add(new ItemOption(5, Util.nextInt(1, 100)));
//                    InventoryService.gI().addItemBag(pl, da1, 0); // 1 là số ngày hạn sử dụng
//
//                    Service.getInstance().sendThongBao(pl, "Bạn nhận " + da1.template.name);
                    break;
            }

            // Sau khi thêm item, cập nhật túi đồ của người chơi
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private int getRandomItem8() {
        int[] weights = {5, 5, 5, 5, 40, 40};
        int totalWeight = 100;
        int random = Util.nextInt(1, totalWeight + 1);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (random <= sum) {
                return i + 1;
            }
        }
        return 1; // Mặc định trả về case 1 nếu có lỗi xảy ra
    }

    private void ruongquaticknap8(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int randomItem = getRandomItem8();

            // Tạo item tương ứng với số ngẫu nhiên đã chọn
            switch (randomItem) {
                case 1:
                    Item vanbayhoadang = ItemService.gI().createNewItem((short) 2133, 1);
                    vanbayhoadang.itemOptions.add(new ItemOption(50, 30));
                    vanbayhoadang.itemOptions.add(new ItemOption(77, 30));
                    vanbayhoadang.itemOptions.add(new ItemOption(103, 30));
                    InventoryService.gI().addItemBag(pl, vanbayhoadang, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được " + vanbayhoadang.template.name);
                    break;
                case 2:

                    Item splsd = ItemService.gI().createNewItem((short) 2120, 1);
                    splsd.itemOptions.add(new ItemOption(50, 4));
                    InventoryService.gI().addItemBag(pl, splsd, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được sao pha lê sức đánh siêu cấp");
                    break;

                case 3:
                    // ngọc xanh
                    Item splki = ItemService.gI().createNewItem((short) 2119);
                    splki.itemOptions.add(new ItemOption(103, 10));
                    InventoryService.gI().addItemBag(pl, splki, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được" + splki.template.name);
                    break;
                case 4:
                    // súp
                    Item splhp = ItemService.gI().createNewItem((short) 2118);
                    splhp.itemOptions.add(new ItemOption(77, 8));
                    InventoryService.gI().addItemBag(pl, splhp, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được" + splhp.template.name);
                    break;
                case 5:
                    // súp
                    Item tvvip = ItemService.gI().createNewItem((short) 1288, Util.nextInt(1, 5));

                    InventoryService.gI().addItemBag(pl, tvvip, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được" + tvvip.quantity + " thỏi vàng vip ");
                    break;
                case 6:
                    // súp
                    Item coin = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, Util.nextInt(1, 1000));

                    InventoryService.gI().addItemBag(pl, coin, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được " + coin.quantity + " coin ");
                    break;
            }

            // Sau khi thêm item, cập nhật túi đồ của người chơi
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void qua2cu(Player player, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 1) {
            player.inventory.addGem(1_000_000);
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(1_000_000);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "UseItem-qua2cu");
            Item chimlua = ItemService.gI().createNewItem((short) 739);
            chimlua.itemOptions.add(new ItemOption(50, 100));
            chimlua.itemOptions.add(new ItemOption(77, 120));
            chimlua.itemOptions.add(new ItemOption(103, 120));
            chimlua.itemOptions.add(new ItemOption(5, 10));
            chimlua.itemOptions.add(new ItemOption(14, 10));
            chimlua.itemOptions.add(new ItemOption(39, 1));
            InventoryService.gI().addItemBag(player, chimlua, 99);

            Service.getInstance().sendMoney(player);
            InventoryService.gI().sendItemBags(player);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
        }
    }

    private void qua5cu(Player player, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 2) {
            player.inventory.addGem(2_000_000);
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(2_000_000);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "UseItem-qua5cu");
            Item chimlua = ItemService.gI().createNewItem((short) 2129);
            chimlua.itemOptions.add(new ItemOption(50, 100));
            chimlua.itemOptions.add(new ItemOption(77, 120));
            chimlua.itemOptions.add(new ItemOption(103, 120));
            chimlua.itemOptions.add(new ItemOption(5, 10));
            chimlua.itemOptions.add(new ItemOption(14, 10));

            InventoryService.gI().addItemBag(player, chimlua, 99);
            Item vitngu = ItemService.gI().createNewItem((short) 2112);
            vitngu.itemOptions.add(new ItemOption(50, 35));
            vitngu.itemOptions.add(new ItemOption(77, 35));
            vitngu.itemOptions.add(new ItemOption(103, 35));
            vitngu.itemOptions.add(new ItemOption(42, 15));

            InventoryService.gI().addItemBag(player, vitngu, 99);
            Service.getInstance().sendMoney(player);
            InventoryService.gI().sendItemBags(player);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Cần 2 ô hành trang");
        }
    }

    private void qua10cu(Player player, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 6) {
            player.inventory.addGem(5_000_000);
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(5_000_000);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "UseItem-qua10cu");

            Item coin = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, 1000000);
            InventoryService.gI().addItemBag(player, coin, 9999999);
            Item thuylong = ItemService.gI().createNewItem((short) 2130);
            thuylong.itemOptions.add(new ItemOption(50, 60));
            thuylong.itemOptions.add(new ItemOption(77, 60));
            thuylong.itemOptions.add(new ItemOption(103, 60));
            thuylong.itemOptions.add(new ItemOption(14, 10));

            InventoryService.gI().addItemBag(player, thuylong, 99);

            Item hopskh = ItemService.gI().createNewItem((short) 1961);
            InventoryService.gI().addItemBag(player, hopskh, 99);

            Item spl1 = ItemService.gI().createNewItem((short) 2118, 50);
            spl1.itemOptions.add(new ItemOption(77, 8));
            InventoryService.gI().addItemBag(player, spl1, 9900);

            Item spl2 = ItemService.gI().createNewItem((short) 2119, 50);
            spl2.itemOptions.add(new ItemOption(103, 10));
            InventoryService.gI().addItemBag(player, spl2, 9900);

            Item spl3 = ItemService.gI().createNewItem((short) 2120, 50);
            spl3.itemOptions.add(new ItemOption(50, 4));
            InventoryService.gI().addItemBag(player, spl3, 9900);

            Service.getInstance().sendMoney(player);
            InventoryService.gI().sendItemBags(player);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Cần 6 ô hành trang");
        }
    }

    private void addItemVip2(Player player, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 7) {
            player.inventory.addGem(150_000);
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(150_000);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "UseItem-addItemVip2");
            Item chimlua = ItemService.gI().createNewItem((short) 2121);
            chimlua.itemOptions.add(new ItemOption(50, Util.nextInt(10, 20)));
            chimlua.itemOptions.add(new ItemOption(77, Util.nextInt(10, 20)));
            chimlua.itemOptions.add(new ItemOption(103, Util.nextInt(10, 20)));
            chimlua.itemOptions.add(new ItemOption(101, Util.nextInt(10, 20)));
            chimlua.itemOptions.add(new ItemOption(43, 15));
            InventoryService.gI().addItemBag(player, chimlua, 99);
            for (int i = 2045; i <= 2051; i++) {
                Item cc = ItemService.gI().createNewItem((short) i);
                InventoryService.gI().addItemBag(player, cc, 99999);
            }
            Service.getInstance().sendMoney(player);
            InventoryService.gI().sendItemBags(player);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
        }
    }

    private void openbillcon(Player pl, Item item) {

        if (pl.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            pl.pet.unFusion();
        }
        pl.pet.dispose();
        MapService.gI().exitMap(pl.pet);
        pl.pet = null;
        PetService.gI().createBILLCON(pl, pl.gender);
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
    }

    private void openMabuNhi(Player pl, Item item) {
        if (pl.pet == null) {
            Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải có đệ tử Cell nhí");
            return;
        }
        if (pl.pet.typePet != ConstPet.WHIS) {
            Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải có đệ tử Cell nhí");
            return;
        }
        if (pl.pet.nPoint.power < 100_000_000_000L) {
            Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải có đệ tử Cell nhí và 100 tỷ sức mạnh");
            return;
        }
        if (pl.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            pl.pet.unFusion();
        }
        pl.pet.dispose();
        MapService.gI().exitMap(pl.pet);
        pl.pet = null;
        PetService.gI().createMabuNhi(pl, pl.gender);
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
    }

    private void UseThoiVang(Player player) {
        Item tv = null;
        for (Item item : player.inventory.itemsBag) {
            if (item.isNotNullItem() && item.template.id == 457) {
                tv = item;
                break;
            }
        }
        if (tv != null /*&& tv.isNotNullItem()*/) {
            if (player.inventory.gold <= player.inventory.getGoldLimit()) {
                InventoryService.gI().subQuantityItemsBag(player, tv, 1);
                player.inventory.gold += 500_000_000;
                PlayerService.gI().sendInfoHpMpMoney(player);
                InventoryService.gI().sendItemBags(player);
            } else {
                Service.getInstance().sendThongBao(player, "Bạn đã sửa dụng quá giới hạn vàng");
            }
        } else {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
        }
    }

    private void findNamekBall(Player pl, Item item) {
        List<NamekBall> balls = NamekBallManager.gI().getList();
        StringBuffer sb = new StringBuffer();
        for (NamekBall namekBall : balls) {
            if (namekBall.zone != null) {
                Map m = namekBall.zone.map;
                sb.append(namekBall.getIndex() + 1).append(" Sao: ").append(m.mapName).append(namekBall.getHolderName() == null ? "" : " - " + namekBall.getHolderName()).append("\n");
            } else {
                Service.getInstance().sendThongBao(pl, "Đã xảy ra lỗi");
            }
        }
        final int star = Util.nextInt(0, 6);
        final NamekBall ball = NamekBallManager.gI().findByIndex(star);
        if (ball == null) {
            Service.getInstance().sendThongBao(pl, "Dit nhau au au");
            return;
        }
        final Inventory inventory = pl.inventory;
        MenuDialog menu = new MenuDialog(sb.toString(), new String[]{"Đến ngay\nViên " + (star + 1) + " Sao\n 50tr Vàng", "Đến ngay\nViên " + (star + 1) + " Sao\n 5 Hồng ngọc"}, new MenuRunable() {
            @Override
            public void run() {
                switch (getIndexSelected()) {
                    case 0:
                        if (inventory.gold < 50000000) {
                            Service.getInstance().sendThongBao(pl, "Không đủ tiền");
                            return;
                        }
                        inventory.subGold(50000000);
                        ChangeMapService.gI().changeMap(pl, ball.zone, ball.x, ball.y);
                        break;
                    case 1:
                        if (inventory.ruby < 5) {
                            Service.getInstance().sendThongBao(pl, "Không đủ tiền");
                            return;
                        }
                        inventory.subRuby(5);
                        ChangeMapService.gI().changeMap(pl, ball.zone, ball.x, ball.y);
                        break;
                }
                if (pl.isHoldNamecBall) {
                    NamekBallWar.gI().dropBall(pl);
                }
                Service.getInstance().sendMoney(pl);
            }
        });
        menu.show(pl);
        InventoryService.gI().sendItemBags(pl);
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
    }

    private void capsuleThoiTrang(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            Item it = ItemService.gI().createNewItem((short) Util.nextInt(ConstItem.CAI_TRANG_GOKU_THOI_TRANG, ConstItem.CAI_TRANG_CA_DIC_THOI_TRANG));
            it.itemOptions.add(new ItemOption(50, 30));
            it.itemOptions.add(new ItemOption(77, 30));
            it.itemOptions.add(new ItemOption(103, 30));
            it.itemOptions.add(new ItemOption(106, 0));
            InventoryService.gI().addItemBag(pl, it, 0);
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
            short icon1 = item.template.iconID;
            short icon2 = it.template.iconID;
            CombineServiceNew.gI().sendEffectOpenItem(pl, icon1, icon2);
        } else {
            Service.getInstance().sendThongBao(pl, "Hãy chừa 1 ô trống để mở.");
        }

    }

    private void useBonTam(Player player, Item item) {
        if (!player.zone.map.isMapLang()) {
            Service.getInstance().sendThongBaoOK(player, "Chỉ có thể sự dụng ở các làng");
            return;
        }
        if (player.event.isUseBonTam()) {
            Service.getInstance().sendThongBaoOK(player, "Không thể sử dụng khi đang tắm");
            return;
        }
        int itemID = item.template.id;
        RandomCollection<Integer> rd = new RandomCollection<>();
        rd.add(1, ConstItem.QUAT_BA_TIEU);
        rd.add(1, ConstItem.CAY_KEM);
        rd.add(1, ConstItem.CA_HEO);
        rd.add(1, ConstItem.DIEU_RONG);

        if (itemID == ConstItem.BON_TAM_GO) {
            rd.add(1, ConstItem.CON_DIEU);
        } else {//bồn tắm vàng
            rd.add(1, ConstItem.XIEN_CA);
            rd.add(1, ConstItem.PHONG_LON);
            rd.add(1, ConstItem.CAI_TRANG_POC_BIKINI_2023);
            rd.add(1, ConstItem.CAI_TRANG_PIC_THO_LAN_2023);
            rd.add(1, ConstItem.CAI_TRANG_KING_KONG_SANH_DIEU_2023);
        }

        int rwID = rd.next();
        Item rw = ItemService.gI().createNewItem((short) rwID);
        if (rw.template.type == 11) {// đồ đeo lưng
            //option
            rw.itemOptions.add(new ItemOption(50, Util.nextInt(5, 15)));
            rw.itemOptions.add(new ItemOption(77, Util.nextInt(5, 15)));
            rw.itemOptions.add(new ItemOption(103, Util.nextInt(5, 15)));
            rw.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
        } else {// cải trang
            //optionr
            rw.itemOptions.add(new ItemOption(50, Util.nextInt(20, 40)));
            rw.itemOptions.add(new ItemOption(77, Util.nextInt(20, 40)));
            rw.itemOptions.add(new ItemOption(103, Util.nextInt(20, 40)));
            rw.itemOptions.add(new ItemOption(199, 0));
            rw.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
        }

        if (rwID == ConstItem.QUAT_BA_TIEU || rwID == ConstItem.VO_OC || rwID == ConstItem.CAY_KEM || rwID == ConstItem.CA_HEO) {
            //hsd
            rw.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));

        } else if (rwID == ConstItem.XIEN_CA || rwID == ConstItem.PHONG_LON || rwID == ConstItem.CAI_TRANG_POC_BIKINI_2023
                || rwID == ConstItem.CAI_TRANG_PIC_THO_LAN_2023 || rwID == ConstItem.CAI_TRANG_KING_KONG_SANH_DIEU_2023) {
            // hsd - vinh vien
            if (Util.isTrue(1, 30)) {
                rw.itemOptions.add(new ItemOption(174, 2023));
            } else {
                rw.itemOptions.add(new ItemOption(174, 2023));
                rw.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
            }

        }

        int delay = itemID == ConstItem.BON_TAM_GO ? 3 : 1;
        ItemTimeService.gI().sendItemTime(player, 3779, 60 * delay);
        EffectSkillService.gI().startStun(player, System.currentTimeMillis(), 60000 * delay);
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
        InventoryService.gI().sendItemBags(player);
        player.event.setUseBonTam(true);
        Util.setTimeout(() -> {
            InventoryService.gI().addItemBag(player, rw, 99);
            InventoryService.gI().sendItemBags(player);
            player.event.setUseBonTam(false);
        }, 60000 * delay);
    }

    private void openCapsuleTet2022(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) == 0) {
            Service.getInstance().sendThongBao(pl, "Hãy chừa 1 ô trống để mở.");
            return;
        }
        RandomCollection<Integer> rdItemID = new RandomCollection<>();
        rdItemID.add(1, ConstItem.PHAO_HOA);
        rdItemID.add(1, ConstItem.CAY_TRUC);
        rdItemID.add(1, ConstItem.NON_HO_VANG);
        switch (pl.gender) {
            case 0:
                rdItemID.add(1, ConstItem.NON_TRAU_MAY_MAN);
                rdItemID.add(1, ConstItem.NON_CHUOT_MAY_MAN);
                break;
            case 1:
                rdItemID.add(1, ConstItem.NON_TRAU_MAY_MAN_847);
                rdItemID.add(1, ConstItem.NON_CHUOT_MAY_MAN_755);
                break;
            default:
                rdItemID.add(1, ConstItem.NON_TRAU_MAY_MAN_848);
                rdItemID.add(1, ConstItem.NON_CHUOT_MAY_MAN_756);
                break;
        }
        rdItemID.add(1, ConstItem.CAI_TRANG_HO_VANG);
        rdItemID.add(1, ConstItem.HO_MAP_VANG);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_442);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_443);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_444);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_445);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_446);
//        rdItemID.add(2, ConstItem.SAO_PHA_LE_447);
        rdItemID.add(2, ConstItem.DA_LUC_BAO);
        rdItemID.add(2, ConstItem.DA_SAPHIA);
        rdItemID.add(2, ConstItem.DA_TITAN);
        rdItemID.add(2, ConstItem.DA_THACH_ANH_TIM);
        rdItemID.add(2, ConstItem.DA_RUBY);
        rdItemID.add(3, ConstItem.VANG_190);
        int itemID = rdItemID.next();
        Item newItem = ItemService.gI().createNewItem((short) itemID);
        switch (newItem.template.type) {
            case 9:
                newItem.quantity = Util.nextInt(10, 50) * 1000000;
                break;
            case 14:
            case 30:
                newItem.quantity = 10;
                break;
            default:
                switch (itemID) {
                    case ConstItem.CAY_TRUC: {
                        RandomCollection<ItemOption> rdOption = new RandomCollection<>();
                        rdOption.add(2, new ItemOption(77, 15));//%hp
                        rdOption.add(2, new ItemOption(103, 15));//%hp
                        rdOption.add(1, new ItemOption(50, 15));//%hp
                        newItem.itemOptions.add(rdOption.next());
                    }
                    break;

                    case ConstItem.HO_MAP_VANG: {
                        newItem.itemOptions.add(new ItemOption(77, Util.nextInt(10, 20)));
                        newItem.itemOptions.add(new ItemOption(103, Util.nextInt(10, 20)));
                        newItem.itemOptions.add(new ItemOption(50, Util.nextInt(10, 20)));
                    }
                    break;

                    case ConstItem.NON_HO_VANG:
                    case ConstItem.CAI_TRANG_HO_VANG:
                    case ConstItem.NON_TRAU_MAY_MAN:
                    case ConstItem.NON_TRAU_MAY_MAN_847:
                    case ConstItem.NON_TRAU_MAY_MAN_848:
                    case ConstItem.NON_CHUOT_MAY_MAN:
                    case ConstItem.NON_CHUOT_MAY_MAN_755:
                    case ConstItem.NON_CHUOT_MAY_MAN_756:
                        newItem.itemOptions.add(new ItemOption(77, 30));
                        newItem.itemOptions.add(new ItemOption(103, 30));
                        newItem.itemOptions.add(new ItemOption(50, 30));
                        break;
                }
                RandomCollection<Integer> rdDay = new RandomCollection<>();
                rdDay.add(6, 3);
                rdDay.add(3, 7);
                rdDay.add(1, 15);
                int day = rdDay.next();
                newItem.itemOptions.add(new ItemOption(93, day));
                break;
        }
        short icon1 = item.template.iconID;
        short icon2 = newItem.template.iconID;
        if (newItem.template.type == 9) {
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + Util.numberToMoney(newItem.quantity) + " " + newItem.template.name);
        } else if (newItem.quantity == 1) {
            Service.getInstance().sendThongBao(pl, "Bạn nhận được " + newItem.template.name);
        } else {
            Service.getInstance().sendThongBao(pl, "Bạn nhận được x" + newItem.quantity + " " + newItem.template.name);
        }
        CombineServiceNew.gI().sendEffectOpenItem(pl, icon1, icon2);
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
        InventoryService.gI().addItemBag(pl, newItem, 99);
        InventoryService.gI().sendItemBags(pl);
    }

    private int randClothes(int level) {
        return ConstItem.LIST_ITEM_CLOTHES[Util.nextInt(0, 2)][Util.nextInt(0, 4)][level - 1];
    }

    //    private void openWoodChest(Player pl, Item item) {
//        int time = (int) TimeUtil.diffDate(new Date(), new Date(item.createTime), TimeUtil.DAY);
//        if (time != 0) {
//            Item itemReward = null;
//            int param = item.itemOptions.get(0).param;
//            int gold = 0;
//            int[] listItem = {441, 442, 443, 444, 445, 446, 447, 457, 457, 457, 223, 224, 225};
//            int[] listClothesReward;
//            int[] listItemReward;
//            String text = "Bạn nhận được\n";
//            if (param < 8) {
//                gold = 100000000 * param;
//                listClothesReward = new int[]{randClothes(param)};
//                listItemReward = Util.pickNRandInArr(listItem, 3);
//            } else if (param < 10) {
//                gold = 120000000 * param;
//                listClothesReward = new int[]{randClothes(param), randClothes(param)};
//                listItemReward = Util.pickNRandInArr(listItem, 4);
//            } else {
//                gold = 150000000 * param;
//                listClothesReward = new int[]{randClothes(param), randClothes(param), randClothes(param)};
//                listItemReward = Util.pickNRandInArr(listItem, 5);
//                int ruby = Util.nextInt(1, 5);
//                pl.inventory.ruby += ruby;
//                pl.textRuongGo.add(text + "|1| " + ruby + " Hồng Ngọc");
//            }
//            for (var i : listClothesReward) {
//                itemReward = ItemService.gI().createNewItem((short) i);
//                RewardService.gI().initBaseOptionClothes(itemReward.template.id, itemReward.template.type, itemReward.itemOptions);
//                RewardService.gI().initStarOption(itemReward, new RewardService.RatioStar[]{new RewardService.RatioStar((byte) 1, 1, 2), new RewardService.RatioStar((byte) 2, 1, 3), new RewardService.RatioStar((byte) 3, 1, 4), new RewardService.RatioStar((byte) 4, 1, 5),});
//                InventoryService.gI().addItemBag(pl, itemReward, 0);
//                pl.textRuongGo.add(text + itemReward.getInfoItem());
//            }
//            for (var i : listItemReward) {
//                itemReward = ItemService.gI().createNewItem((short) i);
//                RewardService.gI().initBaseOptionSaoPhaLe(itemReward);
//                itemReward.quantity = Util.nextInt(1, 5);
//                InventoryService.gI().addItemBag(pl, itemReward, 0);
//                pl.textRuongGo.add(text + itemReward.getInfoItem());
//            }
//            if (param == 11) {
//                Item ngocrong = ItemService.gI().createNewItem((short) Util.nextInt(16, 20));
//                Item itemcap2 = ItemService.gI().createNewItem((short) Util.nextInt(1150, 1154));
//                Item da = ItemService.gI().createNewItem((short) Util.getOne(2011, 2012));
//                pl.inventory.ruby += 3000;
//                InventoryService.gI().addItemBag(pl, da, 999);
//                InventoryService.gI().addItemBag(pl, ngocrong, 999);
//                InventoryService.gI().addItemBag(pl, itemcap2, 999);
////                pl.textRuongGo.add(text + itemReward.getInfoItem());
//            }
//            NpcService.gI().createMenuConMeo(pl, ConstNpc.RUONG_GO, -1,
//                    "Bạn nhận được\n|1|+" + Util.numberToMoney(gold) + " vàng", "OK [" + pl.textRuongGo.size() + "]");
//            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
//            pl.inventory.addGold(gold);
//            InventoryService.gI().sendItemBags(pl);
//            PlayerService.gI().sendInfoHpMpMoney(pl);
//        } else {
//            Service.getInstance().sendThongBao(pl, "Vì bạn quên không lấy chìa nên cần đợi 24h để bẻ khóa");
//        }
//    }
    private void openWoodChest(Player pl, Item item) {
        int time = (int) TimeUtil.diffDate(new Date(), new Date(item.createTime), TimeUtil.DAY);
        if (time != 0 || pl.isAdmin()) {
            int param = item.itemOptions.get(0).param;
            int gem = 0;
            if (param < 12) {
                int quantity = param * 10000;
                String name = "";

                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantity);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                name += ngoc.getName();

                ngoc = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, quantity);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                name += ", " + ngoc.getName();

                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);

                InventoryService.gI().sendItemBags(pl);
//                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + name + " số lượng: " + quantity);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.RUONG_GO, -1,
                        "Bạn nhận được " + name + " số lượng: " + Util.formatCurrency(quantity), "OK [" + pl.textRuongGo.size() + "]");
            }
            if (param == 12) {
//                int[] splSuper = new int[]{2118, 2119, 2120};
//                int optionId = 0, optionValue = 0;
//
//                // Tạo đối tượng Random
//                Random random = new Random();
//
//                // Sinh chỉ số ngẫu nhiên từ 0 đến (array.length - 1)
//                int randomIndex = random.nextInt(splSuper.length);
//
//                // Lấy phần tử ngẫu nhiên từ mảng
//                int randomElement = splSuper[randomIndex];
//
//                switch (randomElement) {
//                    case 2118: {
//                        optionId = 77;
//                        optionValue = 8;
//                        break;
//                    }
//                    case 2119: {
//                        optionId = 103;
//                        optionValue = 8;
//                        break;
//                    }
//                    case 2120: {
//                        optionId = 50;
//                        optionValue = 4;
//                        break;
//                    }
//                }

                int quantity = 150000;
                String name = "";

                Item ngoc = ItemService.gI().createNewItem((short) ConstItem.NGOC, quantity);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                name += ngoc.getName();

                ngoc = ItemService.gI().createNewItem((short) ConstItem.HONG_NGOC, quantity);
                InventoryService.gI().addItemBag(pl, ngoc, 0);
                name += ", " + ngoc.getName();

//                ngoc = ItemService.gI().createNewItem((short) randomElement, 1);
//                ngoc.itemOptions.add(new ItemOption(optionId, optionValue));
//                ngoc.itemOptions.add(new ItemOption(30, 0));
//                InventoryService.gI().addItemBag(pl, ngoc, 999);

                InventoryService.gI().subQuantityItem(pl.inventory.itemsBag, item, 1);
                // random 1-> 10 viên nro hắc ám 4 -> 7 sao
//                short randomNr = (short) Util.nextInt(SummonDragon.NGOC_RONG_NOEL[3], SummonDragon.NGOC_RONG_NOEL[6]);
//                int rdSl = Util.nextInt(1, 10);
//                Item nr = ItemService.gI().createNewItem(randomNr, rdSl);
//                InventoryService.gI().addItemBag(pl, nr, 999);

                InventoryService.gI().sendItemBags(pl);

//                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + name + " số lượng: " + quantity + " và 1 " + ngoc.getName());
//                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + nr.template.name);
                NpcService.gI().createMenuConMeo(pl, ConstNpc.RUONG_GO, -1,
                        "Bạn nhận được " + name + " số lượng: " + Util.formatCurrency(quantity) + " ngọc xanh , " + Util.formatCurrency(quantity) + " hồng ngọc" , "OK [" + pl.textRuongGo.size() + "]");
            }
//            NpcService.gI().createMenuConMeo(pl, ConstNpc.RUONG_GO, -1,
//                    "Bạn nhận được\n|1|+" + Util.numberToMoney(gem) + " vàng", "OK [" + pl.textRuongGo.size() + "]");
//            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
            PlayerService.gI().sendInfoHpMpMoney(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Vì bạn quên không lấy chìa nên cần đợi 24h để bẻ khóa");
        }
    }

    private void useItemChangeFlagBag(Player player, Item item) {
        switch (item.template.id) {
            case 805:// vong thien than
                break;
            case 865: //kiem Z
                if (!player.effectFlagBag.useKiemz) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useKiemz = !player.effectFlagBag.useKiemz;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 994: //vỏ ốc
                break;
            case 995: //cây kem
                break;
            case 996: //cá heo
                break;
            case 997: //con diều
                break;
            case 998: //diều rồng
                if (!player.effectFlagBag.useDieuRong) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useDieuRong = !player.effectFlagBag.useDieuRong;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 999: //mèo mun
                if (!player.effectFlagBag.useMeoMun) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useMeoMun = !player.effectFlagBag.useMeoMun;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 1000: //xiên cá
                if (!player.effectFlagBag.useXienCa) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useXienCa = !player.effectFlagBag.useXienCa;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 1001: //phóng heo
                if (!player.effectFlagBag.usePhongHeo) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.usePhongHeo = !player.effectFlagBag.usePhongHeo;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 954:
                if (!player.effectFlagBag.useHoaVang) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useHoaVang = !player.effectFlagBag.useHoaVang;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 955:
                if (!player.effectFlagBag.useHoaHong) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useHoaHong = !player.effectFlagBag.useHoaHong;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
            case 852:
                if (!player.effectFlagBag.useGayTre) {
                    player.effectFlagBag.reset();
                    player.effectFlagBag.useGayTre = !player.effectFlagBag.useGayTre;
                } else {
                    player.effectFlagBag.reset();
                }
                break;
        }
        Service.getInstance().point(player);
        Service.getInstance().sendFlagBag(player);
    }

    private void changePetcell(Player player, Item item) {
        if (player.pet == null) {
            PetService.gI().createWhisPet(player, player.gender);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
            return;
        }
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        PetService.gI().createWhisPet(player, player.gender);
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
    }

    private void changePetfide(Player player, Item item) {
        if (player.pet == null) {
            PetService.gI().createPetFide(player, player.gender);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
            return;
        }
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        player.pet.dispose();
        MapService.gI().exitMap(player.pet);
        player.pet = null;
        PetService.gI().createPetFide(player, player.gender);
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
    }

    private void changePet(Player player, Item item) {
        if (player.pet != null) {
            int gender = player.pet.gender + 1;
            if (gender > 2) {
                gender = 0;
            }
            PetService.gI().changeNormalPet(player, gender);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Không thể thực hiện");
        }
    }

    public void hopQuaTanThu(Player pl, Item it) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 14) {
            int gender = pl.gender;
            int[] id = {gender, 6 + gender, 21 + gender, 27 + gender, 12, 194, 441, 442, 443, 444, 445, 446, 447};
            int[] soluong = {1, 1, 1, 1, 1, 1, 10, 10, 10, 10, 10, 10, 10};
            int[] option = {0, 0, 0, 0, 0, 73, 95, 96, 97, 98, 99, 100, 101};
            int[] param = {0, 0, 0, 0, 0, 0, 5, 5, 5, 3, 3, 5, 5};
            int arrLength = id.length - 1;

            for (int i = 0; i < arrLength; i++) {
                if (i < 5) {
                    Item item = ItemService.gI().createNewItem((short) id[i]);
                    RewardService.gI().initBaseOptionClothes(item.template.id, item.template.type, item.itemOptions);
                    item.itemOptions.add(new ItemOption(107, 3));
                    InventoryService.gI().addItemBag(pl, item, 0);
                } else {
                    Item item = ItemService.gI().createNewItem((short) id[i]);
                    item.quantity = soluong[i];
                    item.itemOptions.add(new ItemOption(option[i], param[i]));
                    InventoryService.gI().addItemBag(pl, item, 0);
                }
            }

            int[] idpet = {916, 917, 918, 942, 943, 944, 1046, 1039, 1040};

            Item item = ItemService.gI().createNewItem((short) idpet[Util.nextInt(0, idpet.length - 1)]);
            item.itemOptions.add(new ItemOption(50, Util.nextInt(5, 10)));
            item.itemOptions.add(new ItemOption(77, Util.nextInt(5, 10)));
            item.itemOptions.add(new ItemOption(103, Util.nextInt(5, 10)));
            item.itemOptions.add(new ItemOption(93, 3));
            InventoryService.gI().addItemBag(pl, item, 0);

            InventoryService.gI().subQuantityItemsBag(pl, it, 1);
            InventoryService.gI().sendItemBags(pl);
            Service.getInstance().sendThongBao(pl, "Chúc bạn chơi game vui vẻ");
        } else {
            Service.getInstance().sendThongBao(pl, "Cần tối thiểu 14 ô trống để nhận thưởng");
        }
    }

    private void openbox2010(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = {17, 16, 15, 675, 676, 677, 678, 679, 680, 681, 580, 581, 582};
            int[][] gold = {{5000, 20000}};
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;

            Item it = ItemService.gI().createNewItem(temp[index]);

            if (temp[index] >= 15 && temp[index] <= 17) {
                it.itemOptions.add(new ItemOption(73, 0));

            } else if (temp[index] >= 580 && temp[index] <= 582 || temp[index] >= 675 && temp[index] <= 681) { // cải trang

                it.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                it.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                it.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                it.itemOptions.add(new ItemOption(95, Util.nextInt(5, 15)));
                it.itemOptions.add(new ItemOption(96, Util.nextInt(5, 15)));

                if (Util.isTrue(1, 200)) {
                    it.itemOptions.add(new ItemOption(74, 0));
                } else {
                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                }

            } else {
                it.itemOptions.add(new ItemOption(73, 0));
            }
            InventoryService.gI().addItemBag(pl, it, 0);
            icon[1] = it.template.iconID;

            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void capsule8thang3(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = {17, 16, 675, 676, 677, 678, 679, 680, 681, 580, 581, 582, 1154, 1155, 1156, 860, 1041, 1042, 1043, 954, 955};
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            Item it = ItemService.gI().createNewItem(temp[index]);
            if (Util.isTrue(15, 100)) {
                int ruby = Util.nextInt(1, 5);
                int oldRuby = pl.inventory.ruby;
                pl.inventory.addRuby(ruby);
                // ekko ghi log add ruby
                Manager.addPlayerRubyHistory(pl.id, oldRuby, pl.inventory.ruby, "UseItem-capsule8thang3");
                CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, (short) 7743);
                PlayerService.gI().sendInfoHpMpMoney(pl);
                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                InventoryService.gI().sendItemBags(pl);
                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + ruby + " Hồng Ngọc");
                return;
            }
            if (Util.isTrue(17, 100)) {
                Item mewmew = ItemService.gI().createNewItem((short) 457);
                int thoivang = Util.nextInt(1, 5);
                InventoryService.gI().addItemBag(pl, mewmew, thoivang);
                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                InventoryService.gI().sendItemBags(pl);
                Service.getInstance().sendThongBao(pl, "Bạn nhận được thỏi vàng");
                CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, (short) 4028);
                return;
            }
            if (it.template.type == 5) { // cải trang
                it.itemOptions.add(new ItemOption(50, Util.nextInt(17, 25)));
                it.itemOptions.add(new ItemOption(77, Util.nextInt(17, 38)));
                it.itemOptions.add(new ItemOption(103, Util.nextInt(17, 38)));
                it.itemOptions.add(new ItemOption(117, Util.nextInt(6, 15)));
            } else if (it.template.id == 954 || it.template.id == 955) {
                it.itemOptions.add(new ItemOption(50, Util.nextInt(5, 12)));
                it.itemOptions.add(new ItemOption(77, Util.nextInt(5, 12)));
                it.itemOptions.add(new ItemOption(103, Util.nextInt(5, 12)));
            }
            if (it.template.type == 5 || it.template.id == 954 || it.template.id == 955) {
                if (Util.isTrue(1, 10)) {
                    it.itemOptions.add(new ItemOption(74, 0));
                } else {
                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 3)));
                }
            }
            InventoryService.gI().addItemBag(pl, it, 0);
            icon[1] = it.template.iconID;
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    public void openRandomSKH(Player player, byte gender, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 0) {
            Item itemkichhoat = ArrietyDrop.randomCS_DKH(ArrietyDrop.list_item_SKH[player.gender][Util.nextInt(0, 4)],
                    (byte) 1, gender);

            // ekko tăng tỉ lệ cho toàn bộ player nhận SKH ẩn lên 50% (cũ 5%)
            if (Util.isTrue(5, 10)) {
                itemkichhoat.itemOptions.add(new ItemOption(249, 15));
            }

            InventoryService.gI().subQuantityItemsBag(player, item, 1);
            InventoryService.gI().addItemBag(player, itemkichhoat, 1);
            InventoryService.gI().sendItemBags(player);
            Service.getInstance().sendThongBao(player, "Bạn đã nhận được " + itemkichhoat.template.name);
        } else {
            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
        }

    }

    public void openQuaVip(Player player, Item item) {
        if (InventoryService.gI().getCountEmptyBag(player) > 3) {
            Item d = ItemService.gI().createNewItem((short) 1969, 1);// dt
            Item it = ItemService.gI().createNewItem((short) 2018, 1);// pet
            Item coint = ItemService.gI().createNewItem((short) ConstItem.THOI_VANG_VIP, 30_000);// coint
            it.itemOptions.add(new ItemOption(50, 20));
            it.itemOptions.add(new ItemOption(77, 20));
            it.itemOptions.add(new ItemOption(103, 20));
            it.itemOptions.add(new ItemOption(14, 15));
            it.itemOptions.add(new ItemOption(116, 0));
            InventoryService.gI().addItemBag(player, it, 99);
            InventoryService.gI().addItemBag(player, coint, 1_500_000);
            InventoryService.gI().addItemBag(player, d, 9999);
            int i = 200_000;
            player.inventory.addGem(i);
            int oldRuby = player.inventory.ruby;
            player.inventory.addRuby(i);
            // ekko ghi log add ruby
            Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "UseItem-openQuaVip");
            Service.getInstance().sendMoney(player);
            InventoryService.gI().sendItemBags(player);
            InventoryService.gI().subQuantityItemsBag(player, item, 1);
        } else {
            Service.getInstance().sendThongBao(player, "Hàng trang đã đầy");
        }

    }

    private void capsulehe(Player pl, Item item) {
        if (true) {
            Service.gI().sendThongBao(pl, "Vật phẩm bảo trì");
            return;
        }
//        if (InventoryService.gI().getCountEmptyBag(pl) == 0) {
//            Service.getInstance().sendThongBao(pl, "Hành trang của bạn không đủ chỗ trống");
//            return;
//        }
//
//        for (int i = 0; i < 10; i++) {
//            EffectMapService.gI().sendEffectMapToAllInMap(pl.zone, 64, 2, 1, pl.location.x - Util.nextInt(-24, 24), pl.location.y, 1);
//        }
//        int randomPoints = Util.nextInt(1, 10);  // Tạo số ngẫu nhiên từ 1 đến 10
//        pl.diem_skien += randomPoints;  // Cộng số ngẫu nhiên vào điểm sự kiện
//        // Tạo item tương ứng với số ngẫu nhiên đã chọn
//        Service.getInstance().sendThongBao(pl, "Điểm sự kiện của bạn hiện tại là: " + pl.diem_skien);
//        // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)
//        Item gokuVoCuc = ItemService.gI().createNewItem((short) 2008, Util.nextInt(1, 1000));
//
//        InventoryService.gI().addItemBag(pl, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
//        Service.getInstance().sendThongBao(pl, "Bạn nhận được xxx coin");
//
//        // Sau khi thêm item, cập nhật túi đồ của người chơi
//        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
//        InventoryService.gI().sendItemBags(pl);

    }

    private int getRandomItem2() {
        int[] weights = {5, 10, 40, 50, 5};
        int totalWeight = 100;
        int random = Util.nextInt(1, totalWeight + 1);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (random <= sum) {
                return i + 1;
            }
        }
        return 1; // Mặc định trả về case 1 nếu có lỗi xảy ra
    }

    private void hopquahe(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int randomItem = getRandomItem2();

            // Tạo item tương ứng với số ngẫu nhiên đã chọn
            switch (randomItem) {
                case 1:
                    // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)
                    Item gokuVoCuc = ItemService.gI().createNewItem((short) 2105);
                    gokuVoCuc.itemOptions.add(new ItemOption(50, Util.nextInt(1, 80)));
                    gokuVoCuc.itemOptions.add(new ItemOption(77, Util.nextInt(1, 80)));
                    gokuVoCuc.itemOptions.add(new ItemOption(103, Util.nextInt(1, 80)));

                    gokuVoCuc.itemOptions.add(new ItemOption(244, Util.nextInt(1, 10)));
                    gokuVoCuc.itemOptions.add(new ItemOption(246, Util.nextInt(1, 500)));
                    gokuVoCuc.itemOptions.add(new ItemOption(245, 1));
                    InventoryService.gI().addItemBag(pl, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận cải trang hè");
                    break;
                case 2:
                    // Tạo cải trang Goku Vô cực (Vĩnh viễn)
                    Item gokuVoCucForever = ItemService.gI().createNewItem((short) 2108);
                    gokuVoCucForever.itemOptions.add(new ItemOption(50, Util.nextInt(1, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(77, Util.nextInt(1, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(103, Util.nextInt(1, 35)));
                    gokuVoCucForever.itemOptions.add(new ItemOption(245, 1));
                    InventoryService.gI().addItemBag(pl, gokuVoCucForever, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận pet cá mập");
                    break;
                case 3:
                    // Tạo cải trang Vegeta Blue
                    Item vegetaBlue = ItemService.gI().createNewItem((short) 694);

                    InventoryService.gI().addItemBag(pl, vegetaBlue, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Nhận được quả dừa");
                    break;
                case 4:
                    // Tạo cải trang Vegeta Blue
                    Item vegetaBlue2 = ItemService.gI().createNewItem((short) 2107);
                    vegetaBlue2.itemOptions.add(new ItemOption(30, 1));
                    InventoryService.gI().addItemBag(pl, vegetaBlue2, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được capsun hè");
                    break;
                case 5:
                    // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)
                    Item da1 = ItemService.gI().createNewItem((short) 2109);

                    InventoryService.gI().addItemBag(pl, da1, 0); // 1 là số ngày hạn sử dụng

                    Service.getInstance().sendThongBao(pl, "Bạn nhận random đá nâng cấp");
                    break;
            }

            // Sau khi thêm item, cập nhật túi đồ của người chơi
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private int getRandomItem() {
        int[] weights = {20, 20, 20, 10, 30};
        int totalWeight = 100;
        int random = Util.nextInt(1, totalWeight + 1);
        int sum = 0;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (random <= sum) {
                return i + 1;
            }
        }
        return 1; // Mặc định trả về case 1 nếu có lỗi xảy ra
    }

    private void hopquaticknap(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            int randomItem = getRandomItem();

            // Tạo item tương ứng với số ngẫu nhiên đã chọn
            switch (randomItem) {
                case 1:
                    // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)
                    Item gokuVoCuc = ItemService.gI().createNewItem((short) 2090, Util.nextInt(1, 5));
                    gokuVoCuc.itemOptions.add(new ItemOption(30, 1));
                    InventoryService.gI().addItemBag(pl, gokuVoCuc, 0); // 1 là số ngày hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận đá nguyên thủy");
                    break;
                case 2:
                    // Tạo cải trang Goku Vô cực (Vĩnh viễn)
                    Item gokuVoCucForever = ItemService.gI().createNewItem((short) 2088, Util.nextInt(1, 5));
                    gokuVoCucForever.itemOptions.add(new ItemOption(30, 1));
                    InventoryService.gI().addItemBag(pl, gokuVoCucForever, 0); // 0 là không có hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được linh hồn cải trang");
                    break;
                case 3:
                    // Tạo cải trang Vegeta Blue
                    Item vegetaBlue = ItemService.gI().createNewItem((short) 2093);
                    vegetaBlue.itemOptions.add(new ItemOption(50, Util.nextInt(1, 90)));
                    vegetaBlue.itemOptions.add(new ItemOption(77, Util.nextInt(60, 90)));
                    vegetaBlue.itemOptions.add(new ItemOption(103, Util.nextInt(60, 90)));
                    vegetaBlue.itemOptions.add(new ItemOption(243, Util.nextInt(1, 10)));
                    vegetaBlue.itemOptions.add(new ItemOption(116, 1));
                    InventoryService.gI().addItemBag(pl, vegetaBlue, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Nhận được Vegeta God");
                    break;
                case 4:
                    // Tạo cải trang Vegeta Blue
                    Item vegetaBlue2 = ItemService.gI().createNewItem((short) 2094);
                    vegetaBlue2.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                    vegetaBlue2.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                    vegetaBlue2.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                    vegetaBlue2.itemOptions.add(new ItemOption(116, 1));
                    InventoryService.gI().addItemBag(pl, vegetaBlue2, 0); // Không đặt hạn sử dụng
                    Service.getInstance().sendThongBao(pl, "Bạn nhận được linh thú Vogo");
                    break;
                case 5:
                    // Tạo cải trang Goku Vô cực (Hạn sử dụng 1 ngày)
                    Item da1 = ItemService.gI().createNewItem((short) 220, Util.nextInt(1, 5));
                    Item da2 = ItemService.gI().createNewItem((short) 221, Util.nextInt(1, 5));
                    Item da3 = ItemService.gI().createNewItem((short) 222, Util.nextInt(1, 5));
                    Item da4 = ItemService.gI().createNewItem((short) 223, Util.nextInt(1, 5));
                    Item da5 = ItemService.gI().createNewItem((short) 224, Util.nextInt(1, 5));
                    InventoryService.gI().addItemBag(pl, da1, 0); // 1 là số ngày hạn sử dụng
                    InventoryService.gI().addItemBag(pl, da2, 0);
                    InventoryService.gI().addItemBag(pl, da3, 0);
                    InventoryService.gI().addItemBag(pl, da4, 0);
                    InventoryService.gI().addItemBag(pl, da5, 0);
                    Service.getInstance().sendThongBao(pl, "Bạn nhận random đá nâng cấp");
                    break;
            }

            // Sau khi thêm item, cập nhật túi đồ của người chơi
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void renameByItem(Player plChanged) {
        Input.gI().createFormRename(plChanged);
    }

    public void openboxsukien(Player pl, Item item, int idsukien) {
        try {
            switch (idsukien) {
                case 1:
                    if (Manager.EVENT_SEVER == idsukien) {
                        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                            short[] temp = {16, 15, 865, 999, 1000, 1001, 739, 742, 743};
                            int[][] gold = {{5000, 20000}};
                            byte index = (byte) Util.nextInt(0, temp.length - 1);
                            short[] icon = new short[2];
                            icon[0] = item.template.iconID;
                            Item it = ItemService.gI().createNewItem(temp[index]);
                            if (temp[index] >= 15 && temp[index] <= 16) {
                                it.itemOptions.add(new ItemOption(73, 0));
                            } else if (temp[index] == 865) {

                                it.itemOptions.add(new ItemOption(30, 0));

                                if (Util.isTrue(1, 30)) {
                                    it.itemOptions.add(new ItemOption(93, 365));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 999) { // mèo mun
                                it.itemOptions.add(new ItemOption(77, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 1000) { // xiên cá
                                it.itemOptions.add(new ItemOption(103, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 1001) { // Phóng heo
                                it.itemOptions.add(new ItemOption(50, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }

                            } else if (temp[index] == 739) { // cải trang Billes

                                it.itemOptions.add(new ItemOption(77, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(30, 45)));

                                if (Util.isTrue(1, 100)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }

                            } else if (temp[index] == 742) { // cải trang Caufila

                                it.itemOptions.add(new ItemOption(77, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(30, 45)));

                                if (Util.isTrue(1, 100)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 743) { // chổi bay
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }

                            } else {
                                it.itemOptions.add(new ItemOption(73, 0));
                            }
                            InventoryService.gI().addItemBag(pl, it, 0);
                            icon[1] = it.template.iconID;

                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            InventoryService.gI().sendItemBags(pl);

                            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        } else {
                            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                        }
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Sự kiện đã kết thúc");
                    }
                case ConstEvent.SU_KIEN_20_11:
                    if (Manager.EVENT_SEVER == idsukien) {
                        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                            short[] temp = {16, 15, 1039, 954, 955, 710, 711, 1040, 2023, 999, 1000, 1001};
                            byte index = (byte) Util.nextInt(0, temp.length - 1);
                            short[] icon = new short[2];
                            icon[0] = item.template.iconID;
                            Item it = ItemService.gI().createNewItem(temp[index]);
                            if (temp[index] >= 15 && temp[index] <= 16) {
                                it.itemOptions.add(new ItemOption(73, 0));
                            } else if (temp[index] == 1039) {
                                it.itemOptions.add(new ItemOption(50, 10));
                                it.itemOptions.add(new ItemOption(77, 10));
                                it.itemOptions.add(new ItemOption(103, 10));
                                it.itemOptions.add(new ItemOption(30, 0));
                                it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                            } else if (temp[index] == 954) {
                                it.itemOptions.add(new ItemOption(50, 15));
                                it.itemOptions.add(new ItemOption(77, 15));
                                it.itemOptions.add(new ItemOption(103, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(79, 80)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 955) {
                                it.itemOptions.add(new ItemOption(50, 20));
                                it.itemOptions.add(new ItemOption(77, 20));
                                it.itemOptions.add(new ItemOption(103, 20));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(79, 80)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 710) {//cải trang quy lão kame
                                it.itemOptions.add(new ItemOption(50, 22));
                                it.itemOptions.add(new ItemOption(77, 20));
                                it.itemOptions.add(new ItemOption(103, 20));
                                it.itemOptions.add(new ItemOption(194, 0));
                                it.itemOptions.add(new ItemOption(160, 35));
                                if (Util.isTrue(99, 100)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 711) { // cải trang jacky chun
                                it.itemOptions.add(new ItemOption(50, 23));
                                it.itemOptions.add(new ItemOption(77, 21));
                                it.itemOptions.add(new ItemOption(103, 21));
                                it.itemOptions.add(new ItemOption(195, 0));
                                it.itemOptions.add(new ItemOption(160, 50));
                                if (Util.isTrue(99, 100)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 1040) {
                                it.itemOptions.add(new ItemOption(50, 10));
                                it.itemOptions.add(new ItemOption(77, 10));
                                it.itemOptions.add(new ItemOption(103, 10));
                                it.itemOptions.add(new ItemOption(30, 0));
                                it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                            } else if (temp[index] == 2023) {
                                it.itemOptions.add(new ItemOption(30, 0));
                            } else if (temp[index] == 999) { // mèo mun
                                it.itemOptions.add(new ItemOption(77, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 1000) { // xiên cá
                                it.itemOptions.add(new ItemOption(103, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else if (temp[index] == 1001) { // Phóng heo
                                it.itemOptions.add(new ItemOption(50, 15));
                                it.itemOptions.add(new ItemOption(30, 0));
                                if (Util.isTrue(1, 50)) {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                            } else {
                                it.itemOptions.add(new ItemOption(73, 0));
                            }
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            icon[1] = it.template.iconID;
                            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                            InventoryService.gI().addItemBag(pl, it, 0);
                            int ruby = Util.nextInt(1, 5);
                            int oldRuby = pl.inventory.ruby;
                            pl.inventory.addRuby(ruby);
                            // ekko ghi log add ruby
                            Manager.addPlayerRubyHistory(pl.id, oldRuby, pl.inventory.ruby, "UseItem-openboxsukien");
                            InventoryService.gI().sendItemBags(pl);
                            PlayerService.gI().sendInfoHpMpMoney(pl);
                            Service.getInstance().sendThongBao(pl, "Bạn được tặng kèm " + ruby + " Hồng Ngọc");
                        } else {
                            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                        }
                    } else {
                        Service.getInstance().sendThongBao(pl, "Sự kiện đã kết thúc");
                    }
                    break;
                case ConstEvent.SU_KIEN_NOEL:
                    if (Manager.EVENT_SEVER == idsukien) {
                        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
//                            int spl = Util.nextInt(441, 445);
                            int dnc = Util.nextInt(220, 224);
                            int nr = Util.nextInt(16, 18);
                            int nrBang = Util.nextInt(926, 931);

                            if (Util.isTrue(5, 90)) {
                                int ruby = Util.nextInt(1, 3);
                                int oldRuby = pl.inventory.ruby;
                                pl.inventory.addRuby(ruby);
                                // ekko ghi log add ruby
                                Manager.addPlayerRubyHistory(pl.id, oldRuby, pl.inventory.ruby, "UseItem-openboxsukien");
                                CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, (short) 7743);
                                PlayerService.gI().sendInfoHpMpMoney(pl);
                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                                InventoryService.gI().sendItemBags(pl);
                                Service.getInstance().sendThongBao(pl, "Bạn nhận được " + ruby + " Hồng Ngọc");
                            } else {
                                int[] temp = {dnc, nr, nrBang, 387, 390, 393, 821, 822, 746, 380, 999, 1000, 1001, 936, 2022};
                                byte index = (byte) Util.nextInt(0, temp.length - 1);
                                short[] icon = new short[2];
                                icon[0] = item.template.iconID;
                                Item it = ItemService.gI().createNewItem((short) temp[index]);

//                                if (temp[index] >= 441 && temp[index] <= 443) {// sao pha le
//                                    it.itemOptions.add(new ItemOption(temp[index] - 346, 5));
//                                    it.quantity = 10;
//                                } else if (temp[index] >= 444 && temp[index] <= 445) {
//                                    it.itemOptions.add(new ItemOption(temp[index] - 346, 3));
//                                    it.quantity = 10;
//                                } else
                                if (temp[index] >= 220 && temp[index] <= 224) { // da nang cap
                                    it.quantity = 10;
                                } else if (temp[index] >= 387 && temp[index] <= 393) { // mu noel do
                                    it.itemOptions.add(new ItemOption(50, Util.nextInt(30, 40)));
                                    it.itemOptions.add(new ItemOption(77, Util.nextInt(30, 40)));
                                    it.itemOptions.add(new ItemOption(103, Util.nextInt(30, 40)));
                                    it.itemOptions.add(new ItemOption(80, Util.nextInt(10, 20)));
                                    it.itemOptions.add(new ItemOption(106, 0));
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 3)));
                                    it.itemOptions.add(new ItemOption(199, 0));
                                } else if (temp[index] == 936) { // tuan loc
                                    it.itemOptions.add(new ItemOption(50, Util.nextInt(5, 10)));
                                    it.itemOptions.add(new ItemOption(77, Util.nextInt(5, 10)));
                                    it.itemOptions.add(new ItemOption(103, Util.nextInt(5, 10)));
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(3, 30)));
                                } else if (temp[index] == 822) { //cay thong noel
                                    it.itemOptions.add(new ItemOption(50, Util.nextInt(10, 20)));
                                    it.itemOptions.add(new ItemOption(77, Util.nextInt(10, 20)));
                                    it.itemOptions.add(new ItemOption(103, Util.nextInt(10, 20)));
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(3, 30)));
                                    it.itemOptions.add(new ItemOption(30, 0));
                                    it.itemOptions.add(new ItemOption(74, 0));
                                } else if (temp[index] == 746) { // xe truot tuyet
                                    it.itemOptions.add(new ItemOption(74, 0));
                                    it.itemOptions.add(new ItemOption(30, 0));
                                    if (Util.isTrue(99, 100)) {
                                        it.itemOptions.add(new ItemOption(93, Util.nextInt(30, 360)));
                                    }
                                } else if (temp[index] == 999) { // mèo mun
                                    it.itemOptions.add(new ItemOption(77, 15));
                                    it.itemOptions.add(new ItemOption(74, 0));
                                    it.itemOptions.add(new ItemOption(30, 0));
                                    if (Util.isTrue(99, 100)) {
                                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                    }
                                } else if (temp[index] == 1000) { // xiên cá
                                    it.itemOptions.add(new ItemOption(103, 15));
                                    it.itemOptions.add(new ItemOption(74, 0));
                                    it.itemOptions.add(new ItemOption(30, 0));
                                    if (Util.isTrue(99, 100)) {
                                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                    }
                                } else if (temp[index] == 1001) { // Phóng heo
                                    it.itemOptions.add(new ItemOption(50, 15));
                                    it.itemOptions.add(new ItemOption(74, 0));
                                    it.itemOptions.add(new ItemOption(30, 0));
                                    if (Util.isTrue(99, 100)) {
                                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                    }
                                } else if (temp[index] == 2022 || temp[index] == 821) {
                                    it.itemOptions.add(new ItemOption(30, 0));
                                } else {
                                    it.itemOptions.add(new ItemOption(73, 0));
                                }
                                InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                                icon[1] = it.template.iconID;
                                CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                                InventoryService.gI().addItemBag(pl, it, 0);
                                InventoryService.gI().sendItemBags(pl);
                            }
                        } else {
                            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                        }
                    } else {
                        Service.getInstance().sendThongBao(pl, "Sự kiện đã kết thúc");
                    }
                    break;
                case ConstEvent.SU_KIEN_TET:
                    if (Manager.EVENT_SEVER == idsukien) {
                        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                            short[] icon = new short[2];
                            icon[0] = item.template.iconID;
                            RandomCollection<Integer> rd = Manager.HOP_QUA_TET;
                            int tempID = rd.next();
                            Item it = ItemService.gI().createNewItem((short) tempID);
                            if (it.template.type == 11) {//FLAGBAG
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(5, 20)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(5, 20)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(5, 20)));
                            } else if (tempID >= 1159 && tempID <= 1161) {
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(106, 0));
                            } else if (tempID == ConstItem.CAI_TRANG_SSJ_3_WHITE) {
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(30, 40)));
                                it.itemOptions.add(new ItemOption(5, Util.nextInt(10, 25)));
                                it.itemOptions.add(new ItemOption(104, Util.nextInt(5, 15)));
                            }
                            int type = it.template.type;
                            if (type == 5 || type == 11) {// cải trang & flagbag
                                if (Util.isTrue(199, 200)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                                }
                                it.itemOptions.add(new ItemOption(199, 0));//KHÔNG THỂ GIA HẠN
                            } else if (type == 23) {// thú cưỡi
                                if (Util.isTrue(199, 200)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 5)));
                                }
                            }
                            if (tempID >= ConstItem.MANH_AO && tempID <= ConstItem.MANH_GANG_TAY) {
                                it.quantity = Util.nextInt(5, 15);
                            } else {
                                it.itemOptions.add(new ItemOption(74, 0));
                            }
                            icon[1] = it.template.iconID;
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                            InventoryService.gI().addItemBag(pl, it, 0);
                            InventoryService.gI().sendItemBags(pl);
                            break;
                        } else {
                            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                        }
                    } else {
                        Service.getInstance().sendThongBao(pl, "Sự kiện đã kết thúc");
                    }
                    break;
                case 5:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(30, 100)) {
                            byte getRandom = (byte) Util.getOne(0, 1);
                            if (getRandom == 0) {
                                tempID = 584;
                            } else {
                                tempID = 861;
                            }
                        } else if (Util.isTrue(20, 100)) {
                            byte getRandom = (byte) Util.getOne(0, 1);
                            if (getRandom == 0) {
                                tempID = Util.getOne(733, 734);
                            } else {
                                tempID = 861;
                            }
                        } else if (Util.isTrue(15, 100)) {
                            tempID = 457;
                        } else {
                            tempID = 190;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 457:
                                it.quantity = Util.nextInt(1, 5);
                                break;
                            case 861:
                                it.quantity = Util.nextInt(100, 500);
                                break;
                            case 584:
                                it.itemOptions.add(new ItemOption(77, 45));
                                it.itemOptions.add(new ItemOption(103, 45));
                                it.itemOptions.add(new ItemOption(50, 45));
                                it.itemOptions.add(new ItemOption(116, 0));
                                if (!Util.isTrue(10, 150)) {
                                    it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 3)));
                                }
                                it.itemOptions.add(new ItemOption(74, 0));
                                break;
                            case 733:
                            case 734:
                                it.itemOptions.add(new ItemOption(77, 5));
                                it.itemOptions.add(new ItemOption(103, 5));
                                it.itemOptions.add(new ItemOption(50, 5));
                                it.itemOptions.add(new ItemOption(74, 0));
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 6:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(30, 100)) {
                            byte getRandom = (byte) Util.getOne(0, 1);
                            if (getRandom == 0) {
                                tempID = 1213;
                            } else {
                                tempID = 861;
                            }
                        } else if (Util.isTrue(20, 100)) {
                            byte getRandom = (byte) Util.getOne(0, 1);
                            if (getRandom == 0) {
                                tempID = Util.getOne(1230, 1254);
                            } else {
                                tempID = 1111;
                            }
                        } else if (Util.isTrue(5, 100)) {
                            tempID = 457;
                        } else {
                            tempID = 861;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 457:
                                it.quantity = Util.nextInt(1, 10);
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1000, 5000);
                                break;
                            case 1111:
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(15, 22)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(15, 22)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(15, 22)));
                                it.itemOptions.add(new ItemOption(74, 0));
                                break;
                            case 1213:
                            case 1230:
                            case 1254:
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(20, 30)));
                                it.itemOptions.add(new ItemOption(106, 0));
                                it.itemOptions.add(new ItemOption(74, 0));
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 7:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(5, 100)) {
                            tempID = Util.getOne(2032, 2036);
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 1260;
                        } else if (Util.isTrue(26, 100)) {
                            byte getRandom = (byte) Util.getOne(0, 1);
                            if (getRandom == 0) {
                                tempID = Util.getOne(702, 708);
                            } else {
                                tempID = 1143;
                            }
                        } else {
                            tempID = 861;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 861:
                                it.quantity = Util.nextInt(100, 1000);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 8:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        tempID = 1265;
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 1265:
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(1, 5)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(1, 30)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(1, 30)));
                                it.itemOptions.add(new ItemOption(5, Util.nextInt(50, 125)));
                                if (!Util.isTrue(1, 100)) {
                                    it.itemOptions.add(new ItemOption(93, 1));
                                }
                                it.itemOptions.add(new ItemOption(74, 0));
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 9:// hop con mew
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(10, 100)) {
                            tempID = 1253;
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 861;
                        } else if (Util.isTrue(26, 100)) {
                            tempID = 869;
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 2011;
                        } else {
                            tempID = 2012;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 1253:
                                it.itemOptions.add(new ItemOption(50, 20));
                                it.itemOptions.add(new ItemOption(77, 20));
                                it.itemOptions.add(new ItemOption(103, 20));
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1, 5000);
                                break;
                            case 869:
                                it.quantity = Util.nextInt(1, 4);
                                break;
                            case 2011:
                            case 2012:
                                it.quantity = Util.nextInt(1, 2);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);

                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 10:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(5, 120)) {
                            tempID = 1201;
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 2040;
                        } else if (Util.isTrue(26, 100)) {
                            tempID = Util.getOne(1231, 638);
                        } else if (Util.isTrue(30, 100)) {
                            tempID = 861;
                        } else if (Util.isTrue(30, 70)) {
                            tempID = 457;
                        } else {
                            tempID = 16;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 1201:
                                it.itemOptions.add(new ItemOption(50, 20));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(39, 69)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(39, 69)));
                                it.itemOptions.add(new ItemOption(5, Util.nextInt(20, 69)));
                                it.itemOptions.add(new ItemOption(14, Util.nextInt(10, 30)));
                                break;
                            case 457:
                                it.quantity = Util.nextInt(1, 10);
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1, 5000);
                                break;
                            case 2040:
                                it.quantity = Util.nextInt(1, 3);
                                break;
                            case 1231:
                                it.quantity = Util.nextInt(1, 2);
                                break;
                            case 638:
                                it.quantity = Util.nextInt(1, 2);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);

                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 11:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(10, 100)) {
                            tempID = 2061;// cai trang
                        } else if (Util.isTrue(2, 90)) {
                            tempID = 2058;// mew
                        } else if (Util.isTrue(26, 100)) {
                            tempID = 2053;// kiem
                        } else if (Util.isTrue(30, 100)) {
                            tempID = 861;
                        } else if (Util.isTrue(3, 70)) {
                            tempID = 869;
                        } else {
                            tempID = Util.nextInt(1150, 1153);
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 2053:
                                it.itemOptions.add(new ItemOption(50, 5));
                                it.itemOptions.add(new ItemOption(21, 110));
                                break;
                            case 2061:
                                it.itemOptions.add(new ItemOption(50, 50));
                                it.itemOptions.add(new ItemOption(77, 50));
                                it.itemOptions.add(new ItemOption(103, 50));
                                it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
                                it.itemOptions.add(new ItemOption(231, 0));
                                break;
                            case 2058:
                                it.itemOptions.add(new ItemOption(50, 20));
                                it.itemOptions.add(new ItemOption(77, 20));
                                it.itemOptions.add(new ItemOption(103, 20));
                                break;
                            case 869:
                                it.quantity = Util.nextInt(1, 4);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 12:// li xi
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(10, 100)) {
                            tempID = 2061;// cai trang
                        } else if (Util.isTrue(5, 100)) {// banh chung banh tet
                            tempID = Util.getOne(752, 753);
                        } else if (Util.isTrue(30, 100)) {// ngoc rong
                            tempID = Util.nextInt(18, 20);
                        } else if (Util.isTrue(3, 100)) {// van bay
                            tempID = 1278;
                        } else {
                            tempID = 861;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 1278://Xe ma quái
                                it.itemOptions.add(new ItemOption(50, 5));
                                it.itemOptions.add(new ItemOption(77, 5));
                                it.itemOptions.add(new ItemOption(103, 5));
                                break;
                            case 2061://Gâuku đỏ
                                it.itemOptions.add(new ItemOption(50, 50));
                                it.itemOptions.add(new ItemOption(77, 50));
                                it.itemOptions.add(new ItemOption(103, 50));
                                if (Util.isTrue(90, 100)) {
                                    it.itemOptions.add(new ItemOption(93, 1));
                                } else {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                }
                                it.itemOptions.add(new ItemOption(231, 0));
                                break;
                            case 861:// ruby
                                it.quantity = Util.nextInt(1, 100);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 13:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(10, 100)) {
                            tempID = 2064;// cai trang goku
                        } else if (Util.isTrue(27, 90)) {
                            tempID = 869;// capsule 1 
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 2063;// hoa cuc
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 1143;// da bao ve
                        } else if (Util.isTrue(45, 70)) {
                            tempID = Util.nextInt(15, 20);
                        } else {
                            tempID = Util.getOne(2011, 2012);
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 2064:
                                it.itemOptions.add(new ItemOption(50, 72));
                                it.itemOptions.add(new ItemOption(77, 72));
                                it.itemOptions.add(new ItemOption(103, 72));
                                it.itemOptions.add(new ItemOption(108, 30));
                                if (Util.isTrue(40, 100)) {
                                    it.itemOptions.add(new ItemOption(21, 150));
                                } else {
                                    it.itemOptions.add(new ItemOption(74, 0));
                                }
                                break;
                            case 869:
                                it.quantity = Util.nextInt(1, 2);
                                break;
                            case 2063:
                                it.quantity = Util.nextInt(1, 3);
                                break;
                            case 1143:
                                it.quantity = Util.nextInt(1, 3);
                                break;
                            case 2011:
                                it.quantity = Util.nextInt(1, 3);
                                break;
                            case 2012:
                                it.quantity = Util.nextInt(1, 3);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 14://1990
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(5, 100)) {
                            tempID = 1172;// cai trang whis
                        } else if (Util.isTrue(5, 100)) {
                            tempID = 2021;// linh thu
                        } else if (Util.isTrue(20, 100)) {
                            tempID = Util.nextInt(663, 667);// thuc an
                        } else if (Util.isTrue(10, 100)) {
                            tempID = 1143;// da bao ve
                        } else if (Util.isTrue(25, 70)) {// ngoc rong namec
                            tempID = Util.nextInt(1982, 1988);
                        } else if (Util.isTrue(20, 100)) {
                            tempID = 459;//phieu mua sam
                        } else {
                            tempID = 861;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);

                        switch (tempID) {
                            case 2021:
                                it.itemOptions.add(new ItemOption(50, 16));
                                it.itemOptions.add(new ItemOption(77, 16));
                                it.itemOptions.add(new ItemOption(103, 16));
                                break;
                            case 663:
                            case 664:
                            case 665:
                            case 666:
                            case 667:
                                it.quantity = 10;
                                break;
                            case 1172:
                                it.itemOptions.add(new ItemOption(50, Util.nextInt(1, 15)));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(1, 15)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(1, 15)));
//                                it.itemOptions.add(new ItemOption(17, 30));
//                                it.itemOptions.add(new ItemOption(233, 0));
//                                if (Util.isTrue(40, 100)) {
//                                    it.itemOptions.add(new ItemOption(21, 150));
//                                } else {
//                                    it.itemOptions.add(new ItemOption(74, 0));
//                                }
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1000, 3000);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 15:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(5, 100)) {
                            tempID = 1279;// dao
                        }/* else if (Util.isTrue(5, 100)) {
                            tempID = 2021;// linh thu
                        } */ else if (Util.isTrue(20, 100)) {
                            tempID = Util.nextInt(663, 667);// thuc an
                        } else if (Util.isTrue(10, 100)) {
                            tempID = 1143;// da bao ve
                        } else if (Util.isTrue(25, 70)) {// cuong no
                            tempID = Util.nextInt(1150, 1153);
                        } else if (Util.isTrue(10, 100)) {
                            tempID = 15;//ngoc rong 2s
                        } else {
                            tempID = 861;// ruby
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);

                        switch (tempID) {
                            case 1279:// dao
                                it.itemOptions.add(new ItemOption(50, 29));
                                it.itemOptions.add(new ItemOption(77, 29));
                                it.itemOptions.add(new ItemOption(103, 29));
                                it.itemOptions.add(new ItemOption(234, 0));
                                break;
                            case 663:
                            case 664:
                            case 665:
                            case 666:
                            case 667:
                                it.quantity = 10;
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1000, 3000);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 16:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(10, 100)) {
                            tempID = 1957;// ct
                        } else if (Util.isTrue(30, 100)) {
                            tempID = Util.nextInt(2045, 2051);// nrsc
                        } else if (Util.isTrue(25, 100)) {
                            tempID = 861;// ruby
                        } else {// ngoc
                            tempID = 77;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);

                        switch (tempID) {
                            case 1957://ct
                                it.itemOptions.add(new ItemOption(50, 1));
                                it.itemOptions.add(new ItemOption(77, Util.nextInt(50, 90)));
                                it.itemOptions.add(new ItemOption(103, Util.nextInt(50, 90)));
                                it.itemOptions.add(new ItemOption(5, Util.nextInt(1, 150)));
                                break;
                            case 77:
                                it.quantity = Util.nextInt(1000, 2000);
                                break;
                            case 861:
                                it.quantity = Util.nextInt(1000, 2000);
                                break;
                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
                case 17:
                    if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
                        short[] icon = new short[2];
                        icon[0] = item.template.iconID;
                        int tempID;
                        if (Util.isTrue(5, 100)) {
                            tempID = 1285;// ca noc vang
                        } else if (Util.isTrue(20, 95)) {
                            tempID = 694;//dua
                        } else if (Util.isTrue(30, 75)) {
                            tempID = Util.nextInt(695, 698);//
                        } else if (Util.isTrue(20, 55)) {
                            tempID = 1968;//sup ca noc
                        } else if (Util.isTrue(15, 35)) {
                            tempID = 590;// bi kiep
                        } else {// ngoc
                            tempID = 77;
                        }
                        Item it = ItemService.gI().createNewItem((short) tempID);
                        switch (tempID) {
                            case 695:
                            case 696:
                            case 697:
                            case 698:
                                it.quantity = Util.nextInt(1, 99);
                                break;
                            case 1285:// ca noc vang
                                it.itemOptions.add(new ItemOption(50, 25));
                                it.itemOptions.add(new ItemOption(77, 25));
                                it.itemOptions.add(new ItemOption(103, 25));
                                break;
                            case 77:
                                it.quantity = Util.nextInt(1, 1000);
                                break;
                            case 590:
                                it.quantity = Util.nextInt(1, 500);
                                break;

                        }
                        icon[1] = it.template.iconID;
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                        CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
                        InventoryService.gI().addItemBag(pl, it, 0);
                        InventoryService.gI().sendItemBags(pl);
                        Service.getInstance().sendThongBao(pl, "Chúc mừng bạn đã nhận được: " + it.template.name + " x" + it.quantity);
                        break;
                    } else {
                        Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
                    }
                    break;
            }
        } catch (Exception e) {
            logger.error("Lỗi mở hộp quà", e);
        }
    }

    private void openboxkichhoat(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = {76, 188, 189, 190, 441, 442, 447, 2010, 2009, 865, 938, 939, 940, 16, 17, 18, 19, 20, 946, 947, 948, 382, 383, 384, 385};
            int[][] gold = {{5000, 20000}};
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            if (index <= 3 && index >= 0) {
                pl.inventory.addGold(Util.nextInt(gold[0][0], gold[0][1]));
                PlayerService.gI().sendInfoHpMpMoney(pl);
                icon[1] = 930;
            } else {

                Item it = ItemService.gI().createNewItem(temp[index]);
//                if (temp[index] == 441) {
//                    it.itemOptions.add(new ItemOption(95, 5));
//                } else if (temp[index] == 442) {
//                    it.itemOptions.add(new ItemOption(96, 5));
//                } else if (temp[index] == 447) {
//                    it.itemOptions.add(new ItemOption(101, 5));
//                } else
                if (temp[index] >= 2009 && temp[index] <= 2010) {
                    it.itemOptions.add(new ItemOption(30, 0));
                } else if (temp[index] == 865) {
                    it.itemOptions.add(new ItemOption(30, 0));
                    if (Util.isTrue(1, 20)) {
                        it.itemOptions.add(new ItemOption(93, 365));
                    } else {
                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                    }
                } else if (temp[index] >= 938 && temp[index] <= 940) {
                    it.itemOptions.add(new ItemOption(77, 35));
                    it.itemOptions.add(new ItemOption(103, 35));
                    it.itemOptions.add(new ItemOption(50, 35));
                    if (Util.isTrue(1, 50)) {
                        it.itemOptions.add(new ItemOption(116, 0));
                    } else {
                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                    }
                } else if (temp[index] >= 946 && temp[index] <= 948) {
                    it.itemOptions.add(new ItemOption(77, 35));
                    it.itemOptions.add(new ItemOption(103, 35));
                    it.itemOptions.add(new ItemOption(50, 35));
                    if (Util.isTrue(1, 20)) {
                        it.itemOptions.add(new ItemOption(93, 365));
                    } else {
                        it.itemOptions.add(new ItemOption(93, Util.nextInt(1, 30)));
                    }
                } else {
                    it.itemOptions.add(new ItemOption(73, 0));
                }
                InventoryService.gI().addItemBag(pl, it, 0);
                icon[1] = it.template.iconID;

            }
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void openPhieuCaiTrangHaiTac(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            Item ct = ItemService.gI().createNewItem((short) Util.nextInt(618, 626));
            ct.itemOptions.add(new ItemOption(147, 3));
            ct.itemOptions.add(new ItemOption(77, 3));
            ct.itemOptions.add(new ItemOption(103, 3));
            ct.itemOptions.add(new ItemOption(149, 0));
            if (item.template.id == 2006) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(1, 7)));
            } else if (item.template.id == 2007) {
                ct.itemOptions.add(new ItemOption(93, Util.nextInt(7, 30)));
            }
            InventoryService.gI().addItemBag(pl, ct, 0);
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
            CombineServiceNew.gI().sendEffectOpenItem(pl, item.template.iconID, ct.template.iconID);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void eatGrapes(Player pl, Item item) {
        int percentCurrentStatima = pl.nPoint.stamina * 100 / pl.nPoint.maxStamina;
        if (percentCurrentStatima > 50) {
            Service.getInstance().sendThongBao(pl, "Thể lực vẫn còn trên 50%");
            return;
        } else if (item.template.id == 211) {
            pl.nPoint.stamina = pl.nPoint.maxStamina;
            Service.getInstance().sendThongBao(pl, "Thể lực của bạn đã được hồi phục 100%");
        } else if (item.template.id == 212) {
            pl.nPoint.stamina += (pl.nPoint.maxStamina * 20 / 100);
            Service.getInstance().sendThongBao(pl, "Thể lực của bạn đã được hồi phục 20%");
        }
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
        InventoryService.gI().sendItemBags(pl);
        PlayerService.gI().sendCurrentStamina(pl);
    }

    private void openEggLinhThu(Player player, Item item) {
        EggLinhThu.createEggLinhThu(player);
        Service.getInstance().sendThongBao(player, "Bạn đã nhận được Trứng Linh Thú ở Sân Sau Siêu Thị\nLưu ý nếu không thấy Trứng vui lòng Thoát Game vào lại!");
        InventoryService.gI().subQuantityItemsBag(player, item, 1);
        InventoryService.gI().sendItemBags(player);
    }

    private void openCSKB(Player pl, Item item) {
        if (InventoryService.gI().getCountEmptyBag(pl) > 0) {
            short[] temp = {76, 188, 189, 190, 381, 382, 383, 384, 385};
            int[][] gold = {{5000, 20000}};
            byte index = (byte) Util.nextInt(0, temp.length - 1);
            short[] icon = new short[2];
            icon[0] = item.template.iconID;
            if (index <= 3) {
                pl.inventory.addGold(Util.nextInt(gold[0][0], gold[0][1]));
                PlayerService.gI().sendInfoHpMpMoney(pl);
                icon[1] = 930;
            } else {
                Item it = ItemService.gI().createNewItem(temp[index]);
                it.itemOptions.add(new ItemOption(73, 0));
                InventoryService.gI().addItemBag(pl, it, 0);
                icon[1] = it.template.iconID;
            }
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);

            CombineServiceNew.gI().sendEffectOpenItem(pl, icon[0], icon[1]);
        } else {
            Service.getInstance().sendThongBao(pl, "Hàng trang đã đầy");
        }
    }

    private void useItemTime(Player pl, Item item) {
        boolean updatePoint = false;
        switch (item.template.id) {
            case 752:// banh trung thu 1 trung
                if (pl.nPoint.power < 100_000_000_000L) {
                    Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải đạt sức mạnh 100 tỷ");
                    return;
                }
                if (pl.itemTime.isBanhTrungThu1Trung) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBanhTrungThu1Trung = System.currentTimeMillis();
                pl.itemTime.isBanhTrungThu1Trung = true;
                updatePoint = true;
                break;
            case 753:// banh trung thu 2 trung
                if (pl.nPoint.power < 100_000_000_000L) {
                    Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải đạt sức mạnh 100 tỷ");
                    return;
                }
                if (pl.itemTime.isBanhTrungThu2Trung) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBanhTrungThu2Trung = System.currentTimeMillis();
                pl.itemTime.isBanhTrungThu2Trung = true;
                updatePoint = true;
                break;
            case 2205:// Năng lượng bill
                if (pl.nPoint.power < 100_000_000_000L) {
                    Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải đạt sức mạnh 100 tỷ");
                    return;
                }
                if (pl.itemTime.isNangLuongBill) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeNangLuongBill = System.currentTimeMillis();
                pl.itemTime.isNangLuongBill = true;
                updatePoint = true;
                break;
            case 1944:// Đậu thần cấp 11
//                if (pl.nPoint.power < 100_000_000_000L) {
//                    Service.getInstance().sendThongBao(pl, "Yêu cầu bạn phải đạt sức mạnh 100 tỷ");
//                    return;
//                }
                if (pl.itemTime.isDauThanCap11) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeDauThanCap11 = System.currentTimeMillis();
                pl.itemTime.isDauThanCap11 = true;
                updatePoint = true;
                break;
            case 638:
                if (pl.itemTime.isDuoiKhi) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeDuoiKhi = System.currentTimeMillis();
                pl.itemTime.isDuoiKhi = true;
                updatePoint = true;
                break;
            case 382: //bổ huyết
                if (pl.itemTime.isUseBoHuyet2) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBoHuyet = System.currentTimeMillis();
                pl.itemTime.isUseBoHuyet = true;
                updatePoint = true;
                break;
            case 383: //bổ khí
                if (pl.itemTime.isUseBoKhi2) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBoKhi = System.currentTimeMillis();
                pl.itemTime.isUseBoKhi = true;
                updatePoint = true;
                break;
            case 384: //giáp xên
                if (pl.itemTime.isUseGiapXen2) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeGiapXen = System.currentTimeMillis();
                pl.itemTime.isUseGiapXen = true;
                updatePoint = true;
                break;
            case 381: //cuồng nộ
                if (pl.itemTime.isUseCuongNo2) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeCuongNo = System.currentTimeMillis();
                pl.itemTime.isUseCuongNo = true;
                updatePoint = true;
                break;
            case 385: //ẩn danh
                pl.itemTime.lastTimeAnDanh = System.currentTimeMillis();
                pl.itemTime.isUseAnDanh = true;
                break;
            case 1152: //bổ huyết 2
                if (pl.itemTime.isUseBoHuyet) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBoHuyet2 = System.currentTimeMillis();
                pl.itemTime.isUseBoHuyet2 = true;
                updatePoint = true;
                break;
            case 1151: //bổ khí 2
                if (pl.itemTime.isUseBoKhi) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeBoKhi2 = System.currentTimeMillis();
                pl.itemTime.isUseBoKhi2 = true;
                updatePoint = true;
                break;
            case 1153: //giáp xên 2
                if (pl.itemTime.isUseGiapXen) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeGiapXen2 = System.currentTimeMillis();
                pl.itemTime.isUseGiapXen2 = true;
                updatePoint = true;
                break;
            case 1150: //cuồng nộ 2
                if (pl.itemTime.isUseCuongNo) {
                    Service.getInstance().sendThongBao(pl, "Chỉ có thể sự dụng cùng lúc 1 vật phẩm bổ trợ cùng loại");
                    return;
                }
                pl.itemTime.lastTimeCuongNo2 = System.currentTimeMillis();
                pl.itemTime.isUseCuongNo2 = true;
                updatePoint = true;
                break;
            case 379: //máy dò
                pl.itemTime.lastTimeUseMayDo = System.currentTimeMillis();
                pl.itemTime.isUseMayDo = true;
                break;
            case 2044:
                pl.itemTime.timeMayDo = System.currentTimeMillis();
                pl.itemTime.isMayDo = true;
                break;
            case 2134:
                pl.itemTime.lastTimebinhtangluc = System.currentTimeMillis();
                pl.itemTime.isUsebinhtangluc = true;
                Service.getInstance().point(pl);
                break;
            case 663: //bánh pudding
            case 664: //xúc xíc
            case 665: //kem dâu
            case 666: //mì ly
            case 667: //sushi
                pl.itemTime.lastTimeEatMeal = System.currentTimeMillis();
                pl.itemTime.isEatMeal = true;
                ItemTimeService.gI().removeItemTime(pl, pl.itemTime.iconMeal);
                pl.itemTime.iconMeal = item.template.iconID;
                updatePoint = true;
                break;
//            case 2134:
//                pl.itemTime.lastTimeitem212x2 = System.currentTimeMillis();
//                pl.itemTime.item212x2 = true;
//                updatePoint = true;
//                break;

        }
        if (updatePoint) {
            Service.getInstance().point(pl);
        }
        ItemTimeService.gI().sendAllItemTime(pl);
        // ekko dùng item năng lượng bill không bị mất
        if (item.template.id != 2205) {
            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
            InventoryService.gI().sendItemBags(pl);
        }
    }

    private void controllerCallRongThan(Player pl, Item item) {
//        int turn = EventTurnManager.ManageCallDragon(ConstAction.GET_BY_ID, pl.id);

        int tempId = item.template.id;
        if (tempId >= SummonDragon.NGOC_RONG_1_SAO && tempId <= SummonDragon.NGOC_RONG_7_SAO) {
            switch (tempId) {
                case SummonDragon.NGOC_RONG_1_SAO:
//                case SummonDragon.NGOC_RONG_2_SAO:
//                case SummonDragon.NGOC_RONG_3_SAO:
                    SummonDragon.gI().openMenuSummonShenron(pl, (byte) (tempId - 13), SummonDragon.DRAGON_SHENRON);
                    break;
                default:
                    NpcService.gI().createMenuConMeo(pl, ConstNpc.TUTORIAL_SUMMON_DRAGON, -1, "Bạn chỉ có thể gọi rồng từ ngọc 3 sao, 2 sao, 1 sao", "Hướng\ndẫn thêm\n(mới)", "OK");
                    break;
            }
        }
//        else if (tempId == SummonDragon.NGOC_RONG_SIEU_CAP) {
//            SummonDragon.gI().openMenuSummonShenron(pl, (byte) 1015, SummonDragon.DRAGON_BLACK_SHENRON);
//        }
        else if (tempId >= SummonDragon.NGOC_RONG_SIEU_CAP[0] && tempId <= SummonDragon.NGOC_RONG_SIEU_CAP[6]) {
            switch (tempId) {
                case 2045:
                    SummonDragon.gI().openMenuSummonShenron(pl, (byte) 2045, SummonDragon.DRAGON_ICE_SHENRON);
                    EventTurnManager.ManageCallDragon(ConstAction.UPDATE_BY_ID, pl.id);
//                    if (turn > 0) {
//                    } else {
//                        Service.getInstance().sendThongBao(pl, "Bạn chỉ có thể gọi rồng 1 lan 1 ngay");
//                    }
                    break;
                default:
                    Service.getInstance().sendThongBao(pl, "Bạn chỉ có thể gọi rồng namec từ ngọc 1 sao");
                    break;
            }
        }
//        else if (tempId >= SummonDragon.NGOC_RONG_NOEL[0] && tempId <= SummonDragon.NGOC_RONG_NOEL[6]) {
//            switch (tempId) {
//                case SummonDragon.NGOC_RONG_NOEL_1_SAO:
//                    SummonDragon.gI().openMenuSummonShenron(pl, (byte) SummonDragon.NGOC_RONG_NOEL[0], SummonDragon.DRAGON_NOEL);
//                    break;
//                default:
//                    Service.getInstance().sendThongBao(pl, "Bạn chỉ có thể gọi Rồng Hắc Ám từ ngọc 1 sao");
//                    break;
//            }
//        }
    }

    private void activeBanhQuy(Player pl, Item item) {
//        for (int i = 0; i < 10; i++) {
//            EffectMapService.gI().sendEffectMapToAllInMap(pl.zone, 64, 2, 1, pl.location.x - Util.nextInt(-24, 24), pl.location.y, 1);
//        }
        pl.diem_skien++;
        Service.getInstance().sendThongBao(pl, "Điểm sự kiện của bạn hiện tại là: " + pl.diem_skien);
        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
    }

    private void learnSkill(Player pl, Item item) {
        Message msg;
        try {
            if (item.template.gender == pl.gender || item.template.gender == 3) {
                String[] subName = item.template.name.split("");
                byte level = Byte.parseByte(subName[subName.length - 1]);
                Skill curSkill = SkillUtil.getSkillByItemID(pl, item.template.id);
                if (curSkill.point == 7) {
                    Service.getInstance().sendThongBao(pl, "Kỹ năng đã đạt tối đa!");
                } else {
                    if (curSkill.point == 0) {
                        if (level == 1) {
                            curSkill = SkillUtil.createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id), level);
                            SkillUtil.setSkill(pl, curSkill);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            msg = Service.getInstance().messageSubCommand((byte) 23);
                            msg.writer().writeShort(curSkill.skillId);
                            pl.sendMessage(msg);
                            msg.cleanup();
                        } else {
                            Skill skillNeed = SkillUtil.createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id), level);
                            Service.getInstance().sendThongBao(pl, "Vui lòng học " + skillNeed.template.name + " cấp " + skillNeed.point + " trước!");
                        }
                    } else {
                        if (curSkill.point + 1 == level) {
                            curSkill = SkillUtil.createSkill(SkillUtil.getTempSkillSkillByItemID(item.template.id), level);
                            //System.out.println(curSkill.template.name + " - " + curSkill.point);
                            SkillUtil.setSkill(pl, curSkill);
                            InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                            msg = Service.getInstance().messageSubCommand((byte) 62);
                            msg.writer().writeShort(curSkill.skillId);
                            pl.sendMessage(msg);
                            msg.cleanup();
                        } else {
                            Service.getInstance().sendThongBao(pl, "Vui lòng học " + curSkill.template.name + " cấp " + (curSkill.point + 1) + " trước!");
                        }
                    }
                    InventoryService.gI().sendItemBags(pl);
                }
            } else {
                Service.getInstance().sendThongBao(pl, "Không thể thực hiện");

            }
        } catch (Exception e) {
            Log.error(UseItem.class,
                    e);
        }
    }

    private void useTDLT(Player pl, Item item) {
        if (pl.itemTime.isUseTDLT) {
            ItemTimeService.gI().turnOffTDLT(pl, item);
        } else {
            ItemTimeService.gI().turnOnTDLT(pl, item);
        }
    }

    private void usePorata(Player pl) {
        if (pl.pet == null || pl.fusion.typeFusion == 4) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion(true);
            } else {
                pl.pet.unFusion();
            }
        }
        SendAuraRefresh(pl);
    }

    private void usePorata2(Player pl) {
        if (pl.pet == null || pl.fusion.typeFusion == 4 || pl.fusion.typeFusion == 6 || pl.fusion.typeFusion == 10 || pl.fusion.typeFusion == 12) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion2(true);
            } else {
                pl.pet.unFusion();
            }
        }
        SendAuraRefresh(pl);
    }

    private void usePorata4(Player pl) {
        if (pl.pet == null || pl.fusion.typeFusion == 4 || pl.fusion.typeFusion == 6 || pl.fusion.typeFusion == 10) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion4(true);
            } else {
                pl.pet.unFusion();
            }
        }
        SendAuraRefresh(pl);
    }

    private void usePorata3(Player pl) {
        if (pl.pet == null || pl.fusion.typeFusion == 4 || pl.fusion.typeFusion == 6 || pl.fusion.typeFusion == 12) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        } else {
            if (pl.fusion.typeFusion == ConstPlayer.NON_FUSION) {
                pl.pet.fusion3(true);
            } else {
                pl.pet.unFusion();
            }
        }
        SendAuraRefresh(pl);
    }

    private static void SendAuraRefresh(Player pl) {
        Service.getInstance().player(pl);
        Service.getInstance().Send_Caitrang(pl);
        pl.zone.load_Me_To_Another(pl);
        pl.zone.loadAnotherToMe(pl);
        ItemTimeService.gI().sendAllItemTime(pl);
    }

    private void openCapsuleUI(Player pl) {
        if (pl.haveShiba) {
            Service.getInstance().sendThongBao(pl, "không thể thực hiện");
            return;
        }
        if (pl.isHoldNamecBall) {
            NamekBallWar.gI().dropBall(pl);
            Service.getInstance().sendFlagBag(pl);
        }
        pl.iDMark.setTypeChangeMap(ConstMap.CHANGE_CAPSULE);
        ChangeMapService.gI().openChangeMapTab(pl);
    }

    public void choseMapCapsule(Player pl, int index) {
        int zoneId = -1;
        if (index < 0 || index >= pl.mapCapsule.size()) {
            return;
        }
        Zone zoneChose = pl.mapCapsule.get(index);
        if (index != 0 || zoneChose.map.mapId == 21 || zoneChose.map.mapId == 22 || zoneChose.map.mapId == 23) {
            if (!(pl.zone != null && pl.zone instanceof ZSnakeRoad)) {
                pl.mapBeforeCapsule = pl.zone;
            } else {
                pl.mapBeforeCapsule = null;
            }
        } else {
            zoneId = pl.mapBeforeCapsule != null ? pl.mapBeforeCapsule.zoneId : -1;
            pl.mapBeforeCapsule = null;
        }
        ChangeMapService.gI().changeMapBySpaceShip(pl, pl.mapCapsule.get(index).map.mapId, zoneId, -1);
    }

    private void upSkillPet(Player pl, Item item) {
        if (pl.pet == null) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
            return;
        }
        try {
            switch (item.template.id) {
                case 402: //skill 1
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 0)) {
                        Service.getInstance().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 403: //skill 2
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 1)) {
                        Service.getInstance().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 404: //skill 3
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 2)) {
                        Service.getInstance().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 759: //skill 4
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 3)) {
                        Service.getInstance().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
                case 2038:
                    if (SkillUtil.upSkillPet(pl.pet.playerSkill.skills, 4)) {
                        Service.getInstance().chatJustForMe(pl, pl.pet, "Cảm ơn sư phụ");
                        InventoryService.gI().subQuantityItemsBag(pl, item, 1);
                    } else {
                        Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
                    }
                    break;
            }
        } catch (Exception e) {
            Service.getInstance().sendThongBao(pl, "Không thể thực hiện");
        }
    }

    private void hopQuaKichHoat(Player player, Item item) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM1105, -1, "Chọn hành tinh của mày đi",
                "Set trái đất",
                "Set namec",
                "Set xayda",
                "Từ chổi");
    }

    private void hopQuaSetTNSM(Player player, Item item) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM1910, -1, "Chọn hành tinh của mày đi",
                "Set trái đất",
                "Set namec",
                "Set xayda",
                "Từ chổi");
    }

    private void hopQuaKichHoatHuyDiet(Player player, Item item) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM2167, -1, "Chọn hành tinh của mày đi",
                "Set trái đất",
                "Set namec",
                "Set xayda",
                "Từ chổi");
    }

    private void hopQuaBTC3(Player player, Item item) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM_HOP_BTC3, -1, "Ngươi chọn bông tai Sức đánh, HP hay KI ?",
                "Sức đánh",
                "HP",
                "KI",
                "Từ chổi");
    }

    private void hopQuaThanLinh(Player player, Item item) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM, -1, "Chọn hành tinh của mày đi",
                "Set trái đất",
                "Set namec",
                "Set xayda",
                "Từ chổi");
    }

    private void blackGoku(Player player) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_USE_ITEM_BLACK_GOKU, -1, "Bạn có chắc chắn muốn sở hữu đệ tử Black Goku không?",
                "Đồng ý");
    }

    private void hopQuaThienSu(Player player) {
        NpcService.gI().createMenuConMeo(player,
                ConstNpc.MENU_OPTION_USE_ITEM1961, -1, "Chọn hành tinh của mày đi",
                "Set trái đất",
                "Set namec",
                "Set xayda",
                "Từ chổi");
    }

}
