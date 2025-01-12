package nro.services;

import nro.consts.ConstPlayer;
import nro.models.player.Pet;
import nro.models.player.Player;
import nro.utils.SkillUtil;
import nro.utils.Util;
import nro.consts.ConstPet;

/**
 *
 * @Build by Arriety
 *
 */
public class PetService {

    private static PetService i;

    public static PetService gI() {
        if (i == null) {
            i = new PetService();
        }
        return i;
    }

    public void createNormalPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.NORMAL, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                    player.pet.nPoint.initPowerLimit();
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Xin hãy thu nhận làm đệ tử");
            } catch (Exception e) {
            }
        }).start();
    }

//    public void createNormalPet(Player player, byte... limitPower) {
//        new Thread(() -> {
//            try {
//                createNewPet(player, ConstPet.NORMAL);
//                if (limitPower != null && limitPower.length == 1) {
//                    player.pet.nPoint.limitPower = limitPower[0];
//                }
//                Thread.sleep(1000);
//                Service.getInstance().chatJustForMe(player, player.pet, "Xin hãy thu nhận làm đệ tử");
//            } catch (Exception e) {
//            }
//        }).start();
//    }
//    public void createMabuPet(Player player, byte... limitPower) {
//        new Thread(() -> {
//            try {
//                createNewPet(player, ConstPet.MABU);
//                if (limitPower != null && limitPower.length == 1) {
//                    player.pet.nPoint.limitPower = limitPower[0];
//                    player.pet.nPoint.initPowerLimit();
//                }
//                Thread.sleep(1000);
//                Service.getInstance().chatJustForMe(player, player.pet, "Oa oa oa...");
//            } catch (Exception e) {
//            }
//        }).start();
//    }

    public void createMabuPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.MABU, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                    player.pet.nPoint.initPowerLimit();
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Oa oa oa...");
            } catch (Exception e) {
            }
        }).start();
    }

    public void createBerusPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.BILL, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Thần hủy diệt hiện thân tất cả quỳ xuống...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createPetFide(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.FIDE_NHI, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
//                Service.getInstance().chatJustForMe(player, player.pet, "Bruuuuuuuu");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createPetMabuHan(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.MABU_HAN, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
//                Service.getInstance().chatJustForMe(player, player.pet, "Bruuuuuuuu");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createBlackPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.SUPER, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "huuuuuuuuuuu...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createMabuNhi(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.MABU_NHI, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createBILLCON(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.BILL_CON, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createCell(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.CELL, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createVidelPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.VIDEL, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Ta sẽ đem hạnh phúc đến Noel này...");
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được Đệ Tử Videl");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void createWhisPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.WHIS, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Djt nhau au au...");
                Service.getInstance().sendThongBao(player, "Djt nhau au au");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // ekko tạo pet whis theo level
    public void createWhisPetByLevel(Player player, int gender, int level, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPetByLevel(player, ConstPet.WHIS, level, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được Đệ Tử Whis");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // ekko tạo pet goku theo level
    public void createGokuPetByLevel(Player player, int gender, int level, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPetByLevel(player, ConstPet.BLACK_GOKU, level, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được Đệ Tử Black Goku");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // ekko tạo pet goku theo level
    public void createBlackGokuPet(Player player, int gender, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPet(player, ConstPet.BLACK_GOKU, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được Đệ Tử Black Goku");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    // ekko tạo pet Zeno theo level
    public void createZenoPetByLevel(Player player, int gender, int level, byte... limitPower) {
        new Thread(() -> {
            try {
                createNewPetByLevel(player, ConstPet.ZENO, level, (byte) gender);
                if (limitPower != null && limitPower.length == 1) {
                    player.pet.nPoint.limitPower = limitPower[0];
                }
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Hiazzzz...");
                Service.getInstance().sendThongBao(player, "Bạn đã nhận được Đệ Tử Zeno");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void changeNormalPet(Player player, int gender) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createNormalPet(player, gender, limitPower);
    }

    public void changeMabuPet(Player player, int gender) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createMabuPet(player, gender, limitPower);
    }

    public void changeBillPet(Player player, int gender) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createBerusPet(player, gender, limitPower);
    }

    public void changeCellPet(Player player, int gender) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createCell(player, gender, limitPower);
    }

    public void changeVidelPet(Player player, int gender) {
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createVidelPet(player, gender);
    }

    public void changebilconPet(Player player, int gender) {
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createBILLCON(player, gender);
    }

    public void changeSuperPet(Player player, int gender) {
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createBlackPet(player, gender);
    }

    // ekko đổi whis pet theo level
    public void changeWhisPetByLevel(Player player, int gender, int level) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createWhisPetByLevel(player, gender, level, limitPower);
    }

    // ekko đổi zeno pet theo level
    public void changeZenoPetByLevel(Player player, int gender, int level) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createZenoPetByLevel(player, gender, level, limitPower);
    }

    // ekko đổi goku pet theo level
    public void changeGokuPetByLevel(Player player, int gender, int level) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createGokuPetByLevel(player, gender, level, limitPower);
    }

    // ekko đổi goku pet theo level
    public void changeBlackGokuPet(Player player, int gender) {
        byte limitPower = player.pet.nPoint.limitPower;
        if (player.fusion.typeFusion != ConstPlayer.NON_FUSION) {
            player.pet.unFusion();
        }
        MapService.gI().exitMap(player.pet);
        player.pet.dispose();
        player.pet = null;
        createBlackGokuPet(player, gender, limitPower);
    }

    public void changeNamePet(Player player, String name) {
        if (!InventoryService.gI().existItemBag(player, 400)) {
            Service.getInstance().sendThongBao(player, "Bạn cần thẻ đặt tên đệ tử, mua tại Santa");
            return;
        } else if (Util.haveSpecialCharacter(name)) {
            Service.getInstance().sendThongBao(player, "Tên không được chứa ký tự đặc biệt");
            return;
        } else if (name.length() > 10) {
            Service.getInstance().sendThongBao(player, "Tên quá dài");
            return;
        }
        MapService.gI().exitMap(player.pet);
        player.pet.name = "$" + name.toLowerCase().trim();
        InventoryService.gI().subQuantityItemsBag(player, InventoryService.gI().findItemBagByTemp(player, 400), 1);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                Service.getInstance().chatJustForMe(player, player.pet, "Cảm ơn sư phụ đã đặt cho con tên " + name);
            } catch (Exception e) {
            }
        }).start();
    }

    private int[] getDataPetNormal() {
        int[] petData = new int[5];
        petData[0] = Util.nextInt(40, 105) * 20; //hp
        petData[1] = Util.nextInt(40, 105) * 20; //mp
        petData[2] = Util.nextInt(20, 45); //dame
        petData[3] = Util.nextInt(9, 50); //def
        petData[4] = Util.nextInt(0, 2); //crit
        return petData;
    }

    private int[] getDataPetMabu() {
        int[] petData = new int[5];
        petData[0] = Util.nextInt(40, 105) * 20; //hp
        petData[1] = Util.nextInt(40, 105) * 20; //mp
        petData[2] = Util.nextInt(50, 120); //dame
        petData[3] = Util.nextInt(9, 50); //def
        petData[4] = Util.nextInt(0, 2); //crit
        return petData;
    }

    private int[] getDataPetBerus() {
        int[] petData = new int[5];
        petData[0] = Util.nextInt(40, 115) * 20; //hp
        petData[1] = Util.nextInt(40, 115) * 20; //mp
        petData[2] = Util.nextInt(70, 140); //dame
        petData[3] = Util.nextInt(9, 50); //def
        petData[4] = Util.nextInt(0, 2); //crit
        return petData;
    }

    private int[] getDataPetVidel() {
        int[] petData = new int[5];
        petData[0] = Util.nextInt(40, 120) * 20; //hp
        petData[1] = Util.nextInt(40, 120) * 20; //mp
        petData[2] = Util.nextInt(20, 150); //dame
        petData[3] = Util.nextInt(9, 50); //def
        petData[4] = Util.nextInt(0, 2); //crit
        return petData;
    }

    private void createNewPet(Player player, byte typePet, byte... gender) {
        int[] data = new int[0];
        int petLevel = 0;
        Pet pet = new Pet(player);
        pet.nPoint.power = 1_500_000;

        switch (typePet) {
            case ConstPet.NORMAL: {
                data = getDataPetNormal();
                pet.name = "$Đệ tử";
                pet.nPoint.power = 1_500_000;
                pet.typePet = ConstPet.NORMAL;
                break;
            }
            case ConstPet.MABU: {
                data = getDataPetMabu();
                pet.name = "$Mabư mập";
                pet.typePet = ConstPet.MABU;
                break;
            }
            case ConstPet.FIDE_NHI: {
                data = getDataPetMabu();
                pet.name = "$Fide Nhí";
                pet.typePet = ConstPet.FIDE_NHI;
                break;
            }
            case ConstPet.MABU_HAN: {
                data = getDataPetMabu();
                pet.name = "$Mabư Han";
                pet.typePet = ConstPet.MABU_HAN;
                break;
            }
            case ConstPet.BILL: {
                data = getDataPetBerus();
                pet.name = "$Berus";
                pet.typePet = ConstPet.BILL;
                break;
            }
            case ConstPet.SUPER: {
                data = getDataPetBerus();
                pet.name = "$Black Goku";
                pet.typePet = ConstPet.SUPER;
                break;
            }
            case ConstPet.VIDEL: {
                data = getDataPetVidel();
                pet.name = "$Videl";
                pet.typePet = ConstPet.VIDEL;
                break;
            }
            case ConstPet.WHIS: {
                data = getDataPetVidel();
                pet.name = "$Whis";
                pet.typePet = ConstPet.WHIS;
                break;
            }
            case ConstPet.CELL: {
                data = getDataPetVidel();
                pet.name = "$Cell";
                pet.typePet = ConstPet.CELL;
                petLevel = 1;
                break;
            }
            case ConstPet.MABU_NHI: {
                data = getDataPetVidel();
                pet.name = "$Mabư nhí";
                pet.typePet = ConstPet.MABU_NHI;
                break;
            }
            case ConstPet.BILL_CON: {
                data = getDataPetVidel();
                pet.name = "$Mini Berus";
                pet.typePet = ConstPet.BILL_CON;
                if (player.pet != null && player.pet.typePet == ConstPet.MABU_NHI) {
                    petLevel = player.pet.getLever();
                } else {
                    petLevel = 1;
                }
                break;
            }
            case ConstPet.BLACK_GOKU: {
                data = getDataPetVidel();
                pet.name = "$Black Goku";
                pet.typePet = ConstPet.BLACK_GOKU;
                break;
            }
        }
        pet.gender = (gender != null && gender.length != 0) ? gender[0] : (byte) Util.nextInt(0, 2);
        pet.id = -player.id;
        pet.nPoint.stamina = 1000;
        pet.nPoint.maxStamina = 1000;
        pet.nPoint.hpg = data[0];
        pet.nPoint.mpg = data[1];
        pet.nPoint.dameg = data[2];
        pet.nPoint.defg = data[3];
        pet.nPoint.critg = data[4];
        pet.lever = player.lvpet;

        for (int i = 0; i < 7; i++) {
            pet.inventory.itemsBody.add(ItemService.gI().createItemNull());
        }
//        pet.playerSkill.skills.add(SkillUtil.createSkill(Util.nextInt(Util.nextInt(0, 2) * 2, 17), 1));
        // ekko nếu là mabu thì skill mặc định là đấm liên hoàn
        if(typePet == ConstPet.MABU) {
            pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
        }
        else if (Util.isTrue(80, 100)) {
            pet.playerSkill.skills.add(SkillUtil.createSkill(Util.nextInt(0, 2) * 2, 1));
        } else {
            pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
        }
//        pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
//        if (petLevel != 0) {
//            pet.playerSkill.skills.add(SkillUtil.createSkill(1, 1));
//        }
        if (petLevel != 0) {
            pet.setLever(petLevel);
        }
        for (int i = 0; i < 4; i++) {
            pet.playerSkill.skills.add(SkillUtil.createEmptySkill());
        }
        pet.nPoint.calPoint();
        pet.nPoint.setFullHpMp();

        player.pet = pet;
    }

    // ekko tạo pet theo level
    private void createNewPetByLevel(Player player, byte typePet, int level, byte... gender) {
        int[] data = new int[0];
        int petLevel = level;
        Pet pet = new Pet(player);
        pet.nPoint.power = 1_500_000;

        switch (typePet) {
            case ConstPet.NORMAL: {
                data = getDataPetNormal();
                pet.name = "$Đệ tử";
                pet.nPoint.power = 1_500_000;
                pet.typePet = ConstPet.NORMAL;
                break;
            }
            case ConstPet.MABU: {
                data = getDataPetMabu();
                pet.name = "$Mabư mập";
                pet.typePet = ConstPet.MABU;
                break;
            }
            case ConstPet.FIDE_NHI: {
                data = getDataPetMabu();
                pet.name = "$Fide Nhí";
                pet.typePet = ConstPet.FIDE_NHI;
                break;
            }
            case ConstPet.MABU_HAN: {
                data = getDataPetMabu();
                pet.name = "$Mabư Han";
                pet.typePet = ConstPet.MABU_HAN;
                break;
            }
            case ConstPet.BILL: {
                data = getDataPetBerus();
                pet.name = "$Berus";
                pet.typePet = ConstPet.BILL;
                break;
            }
            case ConstPet.SUPER: {
                data = getDataPetBerus();
                pet.name = "$Black Goku";
                pet.typePet = ConstPet.SUPER;
                break;
            }
            case ConstPet.VIDEL: {
                data = getDataPetVidel();
                pet.name = "$Videl";
                pet.typePet = ConstPet.VIDEL;
                break;
            }
            case ConstPet.WHIS: {
                data = getDataPetVidel();
                pet.name = "$Whis";
                pet.typePet = ConstPet.WHIS;
                break;
            }
            case ConstPet.CELL: {
                data = getDataPetVidel();
                pet.name = "$Cell";
                pet.typePet = ConstPet.CELL;
                break;
            }
            case ConstPet.MABU_NHI: {
                data = getDataPetVidel();
                pet.name = "$Mabư nhí";
                pet.typePet = ConstPet.MABU_NHI;
                break;
            }
            case ConstPet.BILL_CON: {
                data = getDataPetVidel();
                pet.name = "$Mini Berus";
                pet.typePet = ConstPet.BILL_CON;
                break;
            }
            case ConstPet.ZENO: {
                data = getDataPetVidel();
                pet.name = "$Zeno";
                pet.typePet = ConstPet.ZENO;
                break;
            }
            case ConstPet.BLACK_GOKU: {
                data = getDataPetVidel();
                pet.name = "$Black Goku";
                pet.typePet = ConstPet.BLACK_GOKU;
                break;
            }
        }
        pet.gender = (gender != null && gender.length != 0) ? gender[0] : (byte) Util.nextInt(0, 2);
        pet.id = -player.id;
        pet.nPoint.stamina = 1000;
        pet.nPoint.maxStamina = 1000;
        pet.nPoint.hpg = data[0];
        pet.nPoint.mpg = data[1];
        pet.nPoint.dameg = data[2];
        pet.nPoint.defg = data[3];
        pet.nPoint.critg = data[4];
        pet.lever = player.lvpet;

        for (int i = 0; i < 7; i++) {
            pet.inventory.itemsBody.add(ItemService.gI().createItemNull());
        }
//        pet.playerSkill.skills.add(SkillUtil.createSkill(Util.nextInt(Util.nextInt(0, 2) * 2, 17), 1));
        // ekko nếu là mabu thì skill mặc định là đấm liên hoàn
        if(typePet == ConstPet.MABU) {
            pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
        }
        else if (Util.isTrue(80, 100)) {
            pet.playerSkill.skills.add(SkillUtil.createSkill(Util.nextInt(0, 2) * 2, 1));
        } else {
            pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
        }
//        pet.playerSkill.skills.add(SkillUtil.createSkill(17, 1));
//        if (petLevel != 0) {
//            pet.playerSkill.skills.add(SkillUtil.createSkill(1, 1));
//        }
        if (petLevel != 0) {
            pet.setLever(petLevel);
        }
        for (int i = 0; i < 4; i++) {
            pet.playerSkill.skills.add(SkillUtil.createEmptySkill());
        }
        pet.nPoint.calPoint();
        pet.nPoint.setFullHpMp();

        player.pet = pet;
    }

    //--------------------------------------------------------------------------
}
