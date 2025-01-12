/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nro.models.npc.NpcForge;

import nro.consts.ConstNpc;
import nro.consts.ConstPet;
import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.npc.Npc;
import nro.models.player.Player;
import nro.models.skill.Skill;
import nro.server.Manager;
import nro.server.io.Message;
import nro.services.InventoryService;
import nro.services.ItemService;
import nro.services.PlayerService;
import nro.services.Service;
import nro.services.func.ChangeMapService;
import nro.services.func.CombineServiceNew;
import nro.services.func.ShopService;
import nro.utils.SkillUtil;
import nro.utils.Util;

/**
 *
 * @author Arriety
 */
public class Whis extends Npc {

    public Whis(int mapId, int status, int cx, int cy, int tempId, int avartar) {
        super(mapId, status, cx, cy, tempId, avartar);
    }

    @Override
    public void openBaseMenu(Player player) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 48:
                    this.createOtherMenu(player, ConstNpc.BASE_MENU, "Chưa tới giờ thi đấu, xem hướng dẫn để biết thêm chi tiết",
                            "Hướng\ndẫn\nthêm", "Shop đồ thiên sứ VIP", "Từ chối");
                    break;
                case 154:
//                    int level = TopWhis.GetLevel(player.id);

                    Item[] items = new Item[1];
                    items[0] = InventoryService.gI().findItem(player.inventory.itemsBag, 1229);//bikieptk
                    int quantity1 = (items[0] != null) ? items[0].quantity : 0;

                    Skill curSkill1 = SkillUtil.getSkillbyId(player, Skill.SUPER_KAME);
                    Skill curSkill2 = SkillUtil.getSkillbyId(player, Skill.MAFUBA);
                    Skill curSkill3 = SkillUtil.getSkillbyId(player, Skill.SUPER_ANTOMIC);

                    if ((player.gender == 0 && curSkill1 != null && curSkill1.point == 0)
                            || (player.gender == 1 && curSkill2 != null && curSkill2.point == 0)
                            || (player.gender == 2 && curSkill3 != null && curSkill3.point == 0)) {
                        // ekko
//                        this.createOtherMenu(player, ConstNpc.MENU_WHIS_200,
//                                "Ngươi muốn gì nào\n"
//                                + "Điều kiện học Tuyệt kỹ \n|2|Bí Kiếp tuyệt kỹ" + quantity1 + "/999\n Hồng ngọc: 20.000",
//                                new String[]{"Nói chuyện",
//                                    "Hành tinh\nBill",
//                                    "Top 100", "[LV:" + level + "]",
//                                    "Học\nTuyệt kỹ"});
                        this.createOtherMenu(player, ConstNpc.MENU_WHIS_200,
                                "Ngươi muốn gì nào\n"
                                        + "Điều kiện học Tuyệt kỹ \n|2|Bí Kiếp tuyệt kỹ" + quantity1 + "/999\n Hồng ngọc: 20.000",
                                new String[]{"Nói chuyện", "Học\nTuyệt kỹ", "Đóng"});
                    } else if((curSkill1 != null && curSkill1.point > 0) || (curSkill2 != null && curSkill2.point > 0) || (curSkill3 != null && curSkill3.point > 0 )) {
                        // ekko
//                        this.createOtherMenu(player, ConstNpc.MENU_WHIS_200,
//                                "Ngươi muốn gì nào\n"
//                                + "Nâng cấp tuyệt kỹ tăng sức mạnh và tầm xa của tuyệt kỹ\nĐiều kiện nâng cấp Tuyệt kỹ\n|2| Hồng ngọc: 35.000",
//                                new String[]{"Nói chuyện",
//                                    "Hành tinh\nBill",
//                                    "Top 100", "[LV:" + level + "]",
//                                    "Nâng cấp\ntuyệt kỹ"});
                        this.createOtherMenu(player, ConstNpc.MENU_WHIS_200,
                                "Ngươi muốn gì nào\n"
                                        + "Nâng cấp tuyệt kỹ tăng sức mạnh và tầm xa của tuyệt kỹ\nĐiều kiện nâng cấp Tuyệt kỹ\n|2| Hồng ngọc: 35.000",
                                new String[]{"Nói chuyện", "Nâng cấp\ntuyệt kỹ", "Đóng"});
                    } else {
                        Service.getInstance().sendThongBao(player, "Có lỗi xảy ra");
                    }
                    break;
                case 200:
                    this.createOtherMenu(player, ConstNpc.MENU_WHIS,
                            "Ngươi muốn gì nào",
                            "Nói chuyện");
                    break;
                default:
                    super.openBaseMenu(player);
                    break;
            }
        }
    }

    @Override
    public void confirmMenu(Player player, int select) {
        if (canOpenNpc(player)) {
            switch (this.mapId) {
                case 48:
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.BASE_MENU:
                            switch (select) {
                                case 0:
                                    break;
                                case 1:
                                    ShopService.gI().openShopNormal(player, this, ConstNpc.SHOP_WHIS_DO_THIEN_SU_VIP, 1, -1);
                                    break;
                            }
                            break;
                    }
                    break;
                case 200:
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_WHIS:
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                            "Ta sẽ giúp ngươi chế tạo trang bị Thiên Sứ!",
                                            "OK", "Đổi gậy\nThiên sứ", "Hành tinh\nWhis", "Đóng");
                                    break;
                                case 1:
                                    if (player.pet == null) {
                                        this.npcChat(player, "Đệ tử con đâu?");
                                        return;
                                    }
                                    if (player.pet.typePet == ConstPet.NORMAL) {
                                        Service.getInstance().sendThongBao(player, "Chúng tôi không nhận đệ tử có dòng máu thấp kém để đổi");
                                        return;
                                    }
                                    if (player.pet.nPoint.power < 100_000_000_000L) {
                                        Service.getInstance().sendThongBao(player, "Đệ tử của bạn chưa đạt tới 100 tỷ sức mạnh");
                                        return;
                                    }
                                    Item it = InventoryService.gI().findItemBagByTemp(player, (short) 1968);
                                    if (it != null) {//2080
                                        if (it.quantity < 10) {
                                            this.npcChat(player, "Bạn thiếu x10 Súp cá nóc");
                                            return;
                                        }
                                        Item i = ItemService.gI().createNewItem((short) 2080, 1);
                                        InventoryService.gI().addItemBag(player, i, 99);
                                        InventoryService.gI().subQuantityItemsBag(player, it, 10);
                                        InventoryService.gI().sendItemBags(player);
                                    } else {
                                        Service.getInstance().sendThongBao(player, "Bạn không có Súp cá nóc");
                                    }
                                    break;
                            }
                            break;
                        case ConstNpc.BASE_MENU:
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DO_TS);
                                    break;
                                case 1:
                                    Item i = InventoryService.gI().findItemBagByTemp(player, 1998);
                                    if (i == null) {
                                        this.npcChat(player, "Bạn thiếu Gậy thiên sứ");
                                        return;
                                    }
                                    if (i.quantity <= 99) {
                                        this.npcChat(player, "Bạn thiếu x99 Gậy thiên sứ");
                                        return;
                                    }
                                    Item ct = ItemService.gI().createNewItem((short) 1276, 1);
                                    ct.itemOptions.add(new ItemOption(50, 55));
                                    ct.itemOptions.add(new ItemOption(77, 55));
                                    ct.itemOptions.add(new ItemOption(103, 55));
                                    ct.itemOptions.add(new ItemOption(101, 200));
                                    ct.itemOptions.add(new ItemOption(106, 1));
                                    if (Util.isTrue(1, 100)) {
                                        ct.itemOptions.add(new ItemOption(74, 1));
                                    } else {
                                        ct.itemOptions.add(new ItemOption(93, 1));
                                    }
                                    InventoryService.gI().subQuantityItemsBag(player, i, 99);
                                    InventoryService.gI().addItemBag(player, ct, 99);
                                    InventoryService.gI().sendItemBags(player);
                                    Service.getInstance().sendThongBao(player, "Bạn đã nhận " + ct.template.name);
//                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DE_WHIS);
                                    break;
                                case 2:
                                    ChangeMapService.gI().changeMapBySpaceShip(player, 154, -1, 336);
                                    break;
                            }
                            break;
                        case ConstNpc.MENU_NANG_CAP_DO_TS:
                            switch (select) {
                                case 0:
                                case 1:
                                    CombineServiceNew.gI().startCombine(player);
                                    break;

                            }
                            break;
                    }
                    break;
                case 154:
                    switch (player.iDMark.getIndexMenu()) {
                        case ConstNpc.MENU_WHIS_200:
                            switch (select) {
                                case 0:
                                    this.createOtherMenu(player, ConstNpc.BASE_MENU,
                                            "Ta sẽ giúp ngươi chế tạo trang bị Thiên Sứ!",
                                            "OK", "Đóng");
                                    break;
                                // ekko
//                                case 1:
//                                    ChangeMapService.gI().changeMapBySpaceShip(player, 200, -1, 336);
//                                    break;
//                                case 2:
//                                    TopWhisService.ShowTop(player);
//                                    break;
//                                case 3:
//                                    // Đây là gọi boss whis cũ
//                                    if (player.inventory.gem < 10_000) {
//                                        this.npcChat(player, "Bạn không đủ 10k ngọc xanh");
//                                        return;
//                                    }
//                                    int level = TopWhis.GetLevel(player.id);
//                                    int whisId = TopWhis.GetMaxPlayerId();
//                                    player.inventory.subGem(10_000);
////                                        InventoryService.gI().subQuantityItemsBag(player, eat, 10);
////                                    InventoryService.gI().sendItemBags(player);
//                                    Service.getInstance().sendMoney(player);
//                                    TopWhis.SwitchToWhisBoss(player, whisId, level);
//                                    break;
                                // nâng cấp tuyệt kỹ
                                case 1: {
                                    Message msg;
                                    try {
                                        if (player.gender == 0) {
                                            Skill curSkill = SkillUtil.getSkillbyId(player, Skill.SUPER_KAME);
                                            // học skill 9 không cần tốn bí kiếp tuyệt kỹ
                                            //Chỉ trừ 60 tỷ tiềm năng của player
                                            if (curSkill == null || curSkill.point == 0) {
                                                long powYC = 80_000_000_000L;
                                                long tnsub = 60_000_000_000L;
                                                if (player.nPoint != null && player.nPoint.power >= powYC && player.nPoint.tiemNang >= tnsub) {
                                                        if (player.inventory != null && player.inventory.ruby > 20_000) {
                                                        int oldRuby = player.inventory.ruby;
                                                        player.inventory.addRuby(-20_000);
                                                        // ekko ghi log add ruby
                                                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                        player.nPoint.tiemNang -= tnsub;
                                                        PlayerService.gI().sendTNSM(player, (byte) 1, -tnsub);
                                                        Service.getInstance().point(player);
                                                        Service.getInstance().player(player);
                                                        Service.getInstance().sendMoney(player);
                                                        InventoryService.gI().sendItemBags(player);
                                                        curSkill = SkillUtil.createSkill(Skill.SUPER_KAME, 1);
                                                        SkillUtil.setSkill(player, curSkill);
                                                        msg = Service.getInstance().messageSubCommand((byte) 23);
                                                        msg.writer().writeShort(curSkill.skillId);
                                                        player.sendMessage(msg);
                                                        msg.cleanup();
                                                    } else {
                                                        Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                    }
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Yêu cầu sức mạnh trên 80 Tỷ và 60 tỷ Tnsm");
                                                }
                                            } else if (curSkill != null && curSkill.point > 0 && curSkill.point < 9) {
                                                Item bikieptuyetky = null;
                                                try {
                                                    bikieptuyetky = InventoryService.gI().findItem(player.inventory.itemsBag, 1229);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                if (curSkill.currLevel == 1000 && player.inventory != null && player.inventory.ruby < 35_000) {
                                                    Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                } else if (bikieptuyetky == null || bikieptuyetky.quantity < 999) {
                                                    this.npcChat(player, "Bạn không đủ 999 bí kíp tuyệt kĩ");
                                                } else if (curSkill.currLevel == 1000 && player.inventory != null && player.inventory.ruby > 35_000) {
                                                    int oldRuby = player.inventory.ruby;
                                                    player.inventory.addRuby(-35_000);
                                                    // ekko ghi log add ruby
                                                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                    InventoryService.gI().subQuantityItemsBag(player, bikieptuyetky, 999);
                                                    InventoryService.gI().sendItemBags(player);
                                                    Service.getInstance().sendMoney(player);
                                                    curSkill = SkillUtil.createSkill(Skill.SUPER_KAME, curSkill.point + 1);
                                                    SkillUtil.setSkill(player, curSkill);
                                                    msg = Service.getInstance().messageSubCommand((byte) 62);
                                                    msg.writer().writeShort(curSkill.skillId);
                                                    player.sendMessage(msg);
                                                    msg.cleanup();
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Thông thạo của bạn chưa đủ 100%");
                                                }
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Tuyệt kĩ của bạn đã đạt cấp tối đa");
                                            }
                                        }
                                        if (player.gender == 1) {
                                            Skill curSkill = SkillUtil.getSkillbyId(player, Skill.MAFUBA);
                                            if (curSkill != null && curSkill.point == 0) {
                                                long powYC = 80_000_000_000L;
                                                long tnsub = 60_000_000_000L;
                                                if (player.nPoint != null && player.nPoint.power >= powYC && player.nPoint.tiemNang >= tnsub) {
                                                    if (player.inventory != null && player.inventory.ruby > 20_000) {
                                                        int oldRuby = player.inventory.ruby;
                                                        player.inventory.addRuby(-20_000);
                                                        // ekko ghi log add ruby
                                                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                        player.nPoint.tiemNang -= tnsub;
                                                        PlayerService.gI().sendTNSM(player, (byte) 1, -tnsub);
                                                        Service.getInstance().point(player);
                                                        Service.getInstance().player(player);
                                                        InventoryService.gI().sendItemBags(player);
                                                        Service.getInstance().sendMoney(player);
                                                        curSkill = SkillUtil.createSkill(Skill.MAFUBA, 1);
                                                        SkillUtil.setSkill(player, curSkill);
                                                        msg = Service.getInstance().messageSubCommand((byte) 23);
                                                        msg.writer().writeShort(curSkill.skillId);
                                                        player.sendMessage(msg);
                                                        msg.cleanup();
                                                    } else {
                                                        Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                    }
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Yêu cầu sức mạnh trên 80 Tỷ và 60 tỷ Tnsm");
                                                }
                                            } else if (curSkill != null && curSkill.point > 0 && curSkill.point < 9) {
                                                Item bikieptuyetky = null;
                                                try {
                                                    bikieptuyetky = InventoryService.gI().findItem(player.inventory.itemsBag, 1229);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                if (curSkill.currLevel == 1000 && player.inventory.ruby < 35_000) {
                                                    Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                } else if (bikieptuyetky == null || bikieptuyetky.quantity < 999) {
                                                    this.npcChat(player, "Bạn không đủ 999 bí kíp tuyệt kĩ");
                                                } else if (curSkill.currLevel == 1000 && player.inventory != null && player.inventory.ruby > 35_000) {
                                                    int oldRuby = player.inventory.ruby;
                                                    player.inventory.addRuby(-35_000);
                                                    // ekko ghi log add ruby
                                                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                    InventoryService.gI().subQuantityItemsBag(player, bikieptuyetky, 999);
                                                    InventoryService.gI().sendItemBags(player);
                                                    Service.getInstance().sendMoney(player);
                                                    curSkill = SkillUtil.createSkill(Skill.MAFUBA, curSkill.point + 1);
                                                    SkillUtil.setSkill(player, curSkill);
                                                    msg = Service.getInstance().messageSubCommand((byte) 62);
                                                    msg.writer().writeShort(curSkill.skillId);
                                                    player.sendMessage(msg);
                                                    msg.cleanup();
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Thông thạo của bạn chưa đủ 100%");
                                                }
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Tuyệt kĩ của bạn đã đạt cấp tối đa");
                                            }
                                        }
                                        if (player.gender == 2) {
                                            Skill curSkill = SkillUtil.getSkillbyId(player, Skill.SUPER_ANTOMIC);
                                            if (curSkill != null && curSkill.point == 0) {
                                                long powYC = 80_000_000_000L;
                                                long tnsub = 60_000_000_000L;
                                                if (player.nPoint != null && player.nPoint.power >= powYC && player.nPoint.tiemNang >= tnsub) {
                                                    if (player.inventory != null && player.inventory.ruby > 20_000) {
                                                        int oldRuby = player.inventory.ruby;
                                                        player.inventory.addRuby(-20_000);
                                                        // ekko ghi log add ruby
                                                        Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                        player.nPoint.tiemNang -= tnsub;
                                                        PlayerService.gI().sendTNSM(player, (byte) 1, -tnsub);
                                                        Service.getInstance().point(player);
                                                        Service.getInstance().player(player);
                                                        Service.getInstance().sendMoney(player);
                                                        InventoryService.gI().sendItemBags(player);
                                                        curSkill = SkillUtil.createSkill(Skill.SUPER_ANTOMIC, 1);
                                                        SkillUtil.setSkill(player, curSkill);
                                                        msg = Service.getInstance().messageSubCommand((byte) 23);
                                                        msg.writer().writeShort(curSkill.skillId);
                                                        player.sendMessage(msg);
                                                        msg.cleanup();
                                                    } else {
                                                        Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                    }
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Yêu cầu sức mạnh trên 80 Tỷ và 60 tỷ Tnsm");
                                                }
                                            } else if (curSkill != null && curSkill.point > 0 && curSkill.point < 9) {
                                                Item bikieptuyetky = null;
                                                try {
                                                    bikieptuyetky = InventoryService.gI().findItem(player.inventory.itemsBag, 1229);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                if (curSkill.currLevel == 1000 && player.inventory != null && player.inventory.ruby < 35_000) {
                                                    Service.getInstance().sendThongBao(player, "Không đủ Hồng ngọc");
                                                } else if (bikieptuyetky == null || bikieptuyetky.quantity < 999) {
                                                    this.npcChat(player, "Bạn không đủ 999 bí kíp tuyệt kĩ");
                                                } else if (curSkill.currLevel == 1000 && player.inventory != null && player.inventory.ruby > 35_000) {
                                                    int oldRuby = player.inventory.ruby;
                                                    player.inventory.addRuby(-35_000);
                                                    // ekko ghi log add ruby
                                                    Manager.addPlayerRubyHistory(player.id, oldRuby, player.inventory.ruby, "Whis-confirmMenu");
                                                    InventoryService.gI().subQuantityItemsBag(player, bikieptuyetky, 999);
                                                    InventoryService.gI().sendItemBags(player);
                                                    Service.getInstance().sendMoney(player);
                                                    curSkill = SkillUtil.createSkill(Skill.SUPER_ANTOMIC,
                                                            curSkill.point + 1);
                                                    SkillUtil.setSkill(player, curSkill);
                                                    msg = Service.getInstance().messageSubCommand((byte) 62);
                                                    msg.writer().writeShort(curSkill.skillId);
                                                    player.sendMessage(msg);
                                                    msg.cleanup();
                                                } else {
                                                    Service.getInstance().sendThongBao(player, "Thông thạo của bạn chưa đủ 100%");
                                                }
                                            } else {
                                                Service.getInstance().sendThongBao(player, "Tuyệt kĩ của bạn đã đạt cấp tối đa");
                                            }
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                        System.out.println("loi ne   4534     ClassCastException ");
                                    }
                                }
                            }
                            break;
                        case ConstNpc.BASE_MENU:
                            switch (select) {
                                case 0:
                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DO_TS);
                                    break;
                                    // ekko tạm ẩn nâng cấp đệ whis
//                                case 1:
//                                    CombineServiceNew.gI().openTabCombine(player, CombineServiceNew.NANG_CAP_DE_WHIS);
//                                    break;
                            }
                            break;
                        case ConstNpc.MENU_NANG_CAP_DO_TS:
                            switch (select) {
                                case 0:
                                case 1:
                                    CombineServiceNew.gI().startCombine(player);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }
}
