SELECT * FROM open_box;

SELECT * FROM open_box_option;

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (1, 2179, 1185, 1185, 3, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (2, 2179, 1186, 1186, 3, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (3, 2179, 753, 753, 30, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (4, 2179, 1192, 1192, 62, 1, 1);

INSERT INTO open_box (id, box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (5, 2179, 2129, 2129, 2, 1, 1);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (1, 50, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (1, 77, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (1, 103, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (2, 50, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (2, 77, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (2, 103, 1, 40);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (5, 50, 110, 110);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (5, 77, 110, 110);

INSERT INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (5, 103, 110, 110);