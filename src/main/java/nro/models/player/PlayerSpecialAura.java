package nro.models.player;

import nro.models.item.ItemOption;

import java.util.ArrayList;
import java.util.List;

public class PlayerSpecialAura {
    public long id;
    // id người chơi
    public long player_id;
    // id hào quang
    public long aura_id;
    // trạng thái
    public long status;
    // CHi so danh hieu
    public List<ItemOption> options = new ArrayList<>();
}
