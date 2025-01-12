DELETE
  FROM item_template
  WHERE id IN (2197);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2197, 21, 3, 'Pet Rồng xương', 'Vật phẩm sự kiện', 29153, -1, 0, 0);

DELETE
  FROM part
  WHERE id IN (1486, 1487, 1488);

INSERT INTO part (id, TYPE, part, data)
  VALUES
  (1486, 0, '[{"icon":2955,"dx":9,"dy":-1},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, TYPE, part, data)
  VALUES
  (1487, 1, '[{"icon":2955,"dx":0,"dy":0},{"icon":29144,"dx":3,"dy":-25},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, TYPE, part, data)
  VALUES
  (1488, 2, '[{"icon":29148,"dx":-19,"dy":-15},{"icon":29143,"dx":-1,"dy":-23},{"icon":29145,"dx":-29,"dy":-26},{"icon":29147,"dx":-24,"dy":-27},{"icon":29148,"dx":-32,"dy":-25},{"icon":29150,"dx":-24,"dy":-23},{"icon":29151,"dx":-29,"dy":-24},{"icon":29145,"dx":-29,"dy":-19},{"icon":29151,"dx":-30,"dy":-20},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE
  FROM mini_pet
  WHERE id_temp = 2197;

INSERT INTO mini_pet (id_temp, head, `body`, leg)
  VALUES
  (2197, 1486, 1487, 1488);

DELETE
  FROM img_by_name
  WHERE id IN (125, 126);

DELETE
  FROM item_template
  WHERE id IN (2199);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2199, 23, 3, 'Ván bay "Rồng thiên"', 'Vật phẩm sự kiện', 29154, 46, 0, 0);

INSERT INTO img_by_name (id, NAME, n_frame)
  VALUES (125, 'mount_46_0', 6);

INSERT INTO img_by_name (id, NAME, n_frame)
  VALUES (126, 'mount_46_1', 6);