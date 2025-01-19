SELECT * FROM item_template
WHERE id = 871;

delete FROM item_template WHERE ID = 2183;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2183, 27, 3, 'Capsule Tết', 'Giấu bên trong nhiều vật phẩm quý giá', 7854, -1, 0, 0);
