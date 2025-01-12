INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (2184, 2174, 2174, 50, 1, 1);

INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
  VALUES (2184, 1288, 1288, 50, 1000, 1000);

-- INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
--   VALUES (2184, DEFAULT, DEFAULT, 5, 1, 1);
-- 
-- INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
--   VALUES (2184, DEFAULT, DEFAULT, 5, 1, 1);
-- 
-- INSERT INTO open_box (box_id, template_id_from, template_id_to, rate, quantity_from, quantity_to)
--   VALUES (2184, DEFAULT, DEFAULT, 5, 1, 1);

SELECT * FROM open_box;
SELECT * FROM open_box_option;

insert INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (6, 50, 50, 50);

insert INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (6, 77, 50, 50);

insert INTO open_box_option (open_box_id, option_id, option_value_from, option_value_to)
  VALUES (6, 103, 50, 50);