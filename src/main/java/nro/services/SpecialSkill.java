package nro.services;

import nro.models.item.Item;
import nro.models.item.ItemOption;
import nro.models.mob.Mob;
import nro.models.player.Player;
import nro.models.skill.Hit;
import nro.models.skill.Skill;
import nro.server.io.Message;
import nro.utils.Util;

import java.io.DataOutputStream;
import java.util.*;

public class SpecialSkill {

    private static SpecialSkill i;

    public static SpecialSkill gI() {
        if (i == null) {
            i = new SpecialSkill();
        }
        return i;
    }

    public boolean executeSpecialSkill(Player player, short skillID, short xPlayer, short yPlayer,
            byte dir, short x, short y) {
        if (player.location.x != xPlayer || player.location.y != yPlayer) {
            return true;
        }
        sendEffStartSkillNotFocus(player, skillID, dir, 5000, (byte) 0);
        player.skillSpecial.setSkillSpecial(dir, xPlayer, yPlayer, x, y);
        SkillService.gI().affterUseSkill(player, skillID);
        SpecialSkill.gI().minusBookReliability(player);
        SpecialSkill.gI().incrementExpert(player);
        return false;
    }

    public void dealDamageSkillNotFocus(Player player, List<Player> players, List<Mob> mobs, Hit hit) {
        List<Integer> hits = hit.getHits();
        final int maxHit = hits.size();
        final int[] damageCount = {0};
        Timer timer = player.playerSkill.timer;
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                int damage = hits.get(damageCount[0]);
                damageCount[0]++;
                for (Player p : players) {
                    p.injured(player, damage, false, false);
                }
                for (Mob mob : mobs) {
                    mob.injured(player, damage, false);
                }
                if (damageCount[0] >= maxHit) {
                    SkillService.gI().affterUseSkill(player, player.playerSkill.skillSelect.template.id);
                    cancel();
                }
            }
        }, 0, 500);
    }

    private void sendEffStartSkillNotFocus(Player player, short skillID, byte dir, int timePre, byte isFly) {
        try {
            int frame = getTypeFrame(player);
            int type = getTypePaintSkill(player, skillID);
            Message m = new Message(-45);
            DataOutputStream ds = m.writer();
            ds.writeByte(20);
            ds.writeInt((int) player.id);
            ds.writeShort(skillID);
            ds.writeByte(frame);//typeFrame
            ds.writeByte(dir);
            ds.writeShort(timePre);
            ds.writeByte(isFly);//isfly
            ds.writeByte(type);//typepaint
            ds.writeByte(0);//typeItem
            ds.flush();
            Service.getInstance().sendMessAllPlayerInMap(player.zone, m);
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendEffEndUseSkillNotFocus(Player player, int time) {
        Message m = new Message(-45);
        DataOutputStream ds = m.writer();
        try {
            byte id = player.skillSpecial.skillSpecial.template.id;
            int typePaint = getTypePaintSkill(player, id);
            ds.writeByte(21);
            ds.writeInt((int) player.id);
            ds.writeShort(id);
            ds.writeShort(player.skillSpecial._xPlayer + ((player.skillSpecial.dir == -1)
                    ? (-player.skillSpecial._xObjTaget) : player.skillSpecial._xObjTaget));
            ds.writeShort(player.skillSpecial._yPlayer);
            ds.writeShort(time);
            ds.writeShort(25);
            ds.writeByte(typePaint);//type paint
            ds.writeByte(0);//
            ds.writeByte(0);//type item
            ds.flush();
            Service.getInstance().sendMessAllPlayerInMap(player.zone, m);
            m.cleanup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getTypePaintSkill(Player player, int skillID) {
        int type = 1;

        Optional<Skill> skill = player.playerSkill.skills.stream().filter(n -> n.template.id == skillID)
                .findFirst();

        if (player.gender == 2) {
            type = 0;
            for (Item item : player.inventory.itemsBody) {
                if (item != null && item.template != null) {
                    if (item.template.id >= 2071 && item.template.id <= 2074) {
                        if (skill.get().point >= 7) {
                            type = 3;
                        }
                    }
                    if (item.template.id >= 2075 && item.template.id <= 2077) {
                        type = 3;
                    }
                }
            }
            return type;
        }
        for (Item item : player.inventory.itemsBody) {
            if (item != null && item.template != null) {
                if (item.template.id >= 2071 && item.template.id <= 2074) {
                    if (skill.get().point >= 7) {
                        type = 3;
                    } else {
                        type = 2;
                    }
                }
                if (item.template.id >= 2075 && item.template.id <= 2077) {
                    type = 3;
                }
            }
        }
        return type;
    }

    private static int getTypeFrame(Player player) {
        int type = 1;
        switch (player.gender) {
            case 0:// td
                type = 1;
                break;
            case 1:// xd
                type = 3;
                break;
            case 2:// nm
                type = 2;
                break;
        }
        return type;
    }

    public void minusBookReliability(Player player) {
        if (player.isPl()) {
            if (player.inventory != null && player.inventory.itemsBody.get(11).isNotNullItem()) {
                Item item = player.inventory.itemsBody.get(11);
                if (item.template.id >= 2071 && item.template.id <= 2073
                        || item.template.id >= 2075 && item.template.id <= 2077) {
                    for (ItemOption io : item.itemOptions) {
                        if (io.optionTemplate.id == 238) {
                            if (io.param <= 0) {
                                io.param = 0;
                            } else if (io.param <= 10) {
                                io.param -= 1;
                            } else {
                                io.param -= Util.nextInt(1, 9);
                            }
                        }
                    }
                    InventoryService.gI().sendItemBody(player);
                }
            }
        }
    }

    public void incrementExpert(Player player) {
        Skill skillSpecial = player.playerSkill.skillSelect;
        if (skillSpecial.currLevel < 1000) {
            skillSpecial.currLevel += 10; // Chỉnh tốc độ luyện Skill 9
            sendCurrLevelSpecial(player, skillSpecial);
        }
    }

    public void sendCurrLevelSpecial(Player player, Skill skill) {
        Message message = null;
        try {
            message = Service.getInstance().messageSubCommand((byte) 62);
            message.writer().writeShort(skill.skillId);
            message.writer().writeByte(0);
            message.writer().writeShort(skill.currLevel);
            player.sendMessage(message);
        } catch (final Exception ex) {
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }
        }
    }

    public void updateNewSkill(Player player, short id, byte typePaint, byte typeItem) {
        Message message = null;
        try {
            message = Service.getInstance().messageSubCommand((byte) 62);
            message.writer().writeShort(id);//skillid
            message.writer().writeByte(1);
            message.writer().writeByte(typePaint);
            message.writer().writeByte(typeItem);
            Service.getInstance().sendMessAllPlayerInMap(player, message);
        } catch (final Exception ex) {
        } finally {
            if (message != null) {
                message.cleanup();
                message = null;
            }
        }
    }

}
