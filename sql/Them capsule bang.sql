DELETE
  FROM item_template
  WHERE id = 2170;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2170, 27, 3, 'Capsule bang hội', 'Vật phẩm sự kiện', 6782, -1, 0, 0);
