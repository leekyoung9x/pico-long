package nro.models.box;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenBox {
    private Integer id;
    private Integer box_id;
    private Integer template_id_from;
    private Integer template_id_to;
    private Integer rate;
    private Integer quantity_from;
    private Integer quantity_to;
    private List<OpenBoxOption> openBoxOptions;

    // Constructors
    public OpenBox() {
    }

    public OpenBox(Integer id, Integer box_id, Integer template_id_from, Integer template_id_to, Integer rate, Integer quantity_from, Integer quantity_to, List<OpenBoxOption> openBoxOptions) {
        this.id = id;
        this.box_id = box_id;
        this.template_id_from = template_id_from;
        this.template_id_to = template_id_to;
        this.rate = rate;
        this.quantity_from = quantity_from;
        this.quantity_to = quantity_to;
        this.openBoxOptions = openBoxOptions;
    }
}