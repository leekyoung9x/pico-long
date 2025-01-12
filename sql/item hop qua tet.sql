delete FROM item_template WHERE ID = 2184;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2184, 27, 3, 'Hộp quà tết', 'Giấu bên trong nhiều vật phẩm quý giá', 11006, -1, 0, 0);

SELECT * FROM item_template