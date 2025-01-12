package nro.models.box;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenBoxOption {
    private Integer id;
    private Integer open_box_id;
    private Integer option_id;
    private Integer option_value_from;
    private Integer option_value_to;

    // Constructors
    public OpenBoxOption() {
    }

    public OpenBoxOption(Integer id, Integer open_box_id, Integer option_id, Integer option_value_from, Integer option_value_to) {
        this.id = id;
        this.open_box_id = open_box_id;
        this.option_id = option_id;
        this.option_value_from = option_value_from;
        this.option_value_to = option_value_to;
    }
}
