delete FROM part
WHERE ID IN (1477, 1478, 1479);

INSERT INTO part (id, type, part, data) VALUES
(1477, 0, '[{"icon":29059,"dx":1,"dy":-10},{"icon":29060,"dx":1,"dy":-9},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1478, 1, '[{"icon":29061,"dx":0,"dy":-2},{"icon":29062,"dx":-2,"dy":-7},{"icon":29063,"dx":-2,"dy":-8},{"icon":29064,"dx":0,"dy":-9},{"icon":29065,"dx":0,"dy":-6},{"icon":29066,"dx":2,"dy":-6},{"icon":29067,"dx":0,"dy":-6},{"icon":29068,"dx":0,"dy":-6},{"icon":29069,"dx":0,"dy":-4},{"icon":29070,"dx":-3,"dy":-5},{"icon":29071,"dx":-1,"dy":-5},{"icon":29072,"dx":-2,"dy":-5},{"icon":29073,"dx":-5,"dy":-3},{"icon":29074,"dx":1,"dy":-6},{"icon":29075,"dx":-3,"dy":-7},{"icon":29076,"dx":-1,"dy":-5},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1479, 2, '[{"icon":29077,"dx":2,"dy":-4},{"icon":29078,"dx":-2,"dy":-4},{"icon":29079,"dx":-2,"dy":-2},{"icon":29080,"dx":-2,"dy":-4},{"icon":29081,"dx":-1,"dy":-3},{"icon":29082,"dx":0,"dy":-4},{"icon":29083,"dx":0,"dy":-2},{"icon":29084,"dx":-1,"dy":-1},{"icon":29085,"dx":-4,"dy":-2},{"icon":29086,"dx":-3,"dy":-3},{"icon":29087,"dx":-2,"dy":-4},{"icon":29088,"dx":-3,"dy":0},{"icon":29089,"dx":2,"dy":-4},{"icon":2955,"dx":0,"dy":0}]', NULL);

delete FROM head_avatar WHERE head_id = 1477;

INSERT INTO head_avatar (head_id, avatar_id)
  VALUES (1477, 29090);

SET @id = 2193;

delete FROM cai_trang WHERE id_temp = @id;

delete FROM item_template WHERE id = @id;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(@id, 5, 3, 'Cải trang Black Goku Tết', 'Cải trang thành Black Goku Tết', 29091, -1, 0, 0);

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag)
  VALUES (@id, 1477, 1478, 1479, DEFAULT);