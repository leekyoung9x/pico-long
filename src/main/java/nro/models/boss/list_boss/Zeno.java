package nro.models.boss.list_boss;

import nro.consts.ConstItem;
import nro.models.boss.Boss;
import nro.models.boss.BossData;
import nro.models.boss.BossFactory;
import nro.models.map.ItemMap;
import nro.models.player.Player;
import nro.services.Service;
import nro.utils.SkillUtil;
import nro.utils.Util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Zeno extends Boss {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    private boolean canAttack = true;

    public Zeno() {
        super(BossFactory.ZENO, BossData.ZENO);

        SpcecialNoiTai();
    }

    private void SpcecialNoiTai() {
        // Lập lịch cho trạng thái bất tử
        scheduler.scheduleAtFixedRate(() -> {
            canAttack = false; // Boss bất tử
            Service.getInstance().chat(this, "Boss bất tử trong 4 giây.");
        }, 0, 8, TimeUnit.SECONDS); // Bắt đầu ngay lập tức

        // Lập lịch cho khả năng tấn công
        scheduler.scheduleAtFixedRate(() -> {
            canAttack = true; // Boss có thể bị tấn công
            Service.getInstance().chat(this, "Boss có thể bị tấn công trong 2 giây.");
        }, 4, 8, TimeUnit.SECONDS); // Bắt đầu sau 4 giây
    }

    protected Zeno(byte id, BossData bossData) {
        super(id, bossData);
        SpcecialNoiTai();
    }

    @Override
    protected boolean useSpecialSkill() {
        return false;
    }

    @Override
    public void rewards(Player pl) {
        if (pl != null) {
            // tỉ lệ 5% rớt item
            if(Util.isTrue(5, 100)) {
                ItemMap itemMap = new ItemMap(this.zone, ConstItem.DE_TU_ZENO, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
                Service.getInstance().dropItemMap(this.zone, itemMap);
            }
            // rơi 1 viên nro 7 sao
            else {
                ItemMap itemMap = new ItemMap(this.zone, ConstItem.NGOC_RONG_7_SAO, 1, pl.location.x, this.zone.map.yPhysicInTop(pl.location.x, pl.location.y - 24), pl.id);
                Service.getInstance().dropItemMap(this.zone, itemMap);
            }
        }
    }

    @Override
    public void initTalk() {
        this.textTalkBefore = new String[]{};
        this.textTalkMidle = new String[]{"Các ngươi không dễ mà làm ta bị thương đâu !"};
        this.textTalkAfter = new String[]{};
    }

    @Override
    public void idle() {
        if (this.countIdle >= this.maxIdle) {
            this.maxIdle = Util.nextInt(0, 3);
            this.countIdle = 0;
            this.changeAttack();
        } else {
            this.countIdle++;
        }
    }

    @Override
    public void checkPlayerDie(Player pl) {
        if (pl.isDie()) {
            Service.getInstance().chat(this, "Chừa nha " + plAttack.name + " động vào ta chỉ có chết.");
            this.plAttack = null;
        }
    }

    @Override
    public long injured(Player plAtt, long damage, boolean piercing, boolean isMobAttack) {
        if (!canAttack) {
            return 0; // Không gây sát thương
        }
        if (plAtt == null) {
            return 0;
        }
        if (this.isDie()) {
            return 0;
        } else {
            return super.injured(plAtt, damage, piercing, isMobAttack);
        }
    }
}
