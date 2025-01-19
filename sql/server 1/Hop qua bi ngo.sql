DELETE
  FROM item_template
  WHERE id = 2198;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2198, 27, 3, 'Túi Quà Bí Ngô', 'Giấu bên trong nhiều vật phẩm quý giá', 25015, -1, 0, 0);

INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (2198, 1, 1, 1, 1, 1);

DELETE
  FROM open_box
  WHERE box_id = 2198;

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (13, 2198, 2197, 2197, 2, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (14, 2198, 2180, 2180, 17, 10, 10);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (15, 2198, 2181, 2181, 17, 10, 10);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (16, 2198, 2182, 2182, 17, 10, 10);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (17, 2198, 2119, 2119, 26, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (18, 2198, 2120, 2120, 5, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (19, 2198, 2118, 2118, 15, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (20, 2198, 2199, 2199, 1, 1, 1);

delete FROM open_box_option
WHERE open_box_id IN (13, 14, 17, 18, 19, 20);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (13, 50, 40, 60);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (13, 77, 40, 60);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (13, 103, 40, 60);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (17, 103, 8, 8);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (18, 50, 4, 4);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (19, 77, 8, 8);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (20, 50, 50, 50);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (20, 77, 50, 50);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (20, 103, 50, 50);