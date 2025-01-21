delete FROM item_template
WHERE id IN (2155);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2155, 21, 3, 'Pet Gấu ong hồng', 'Vật phẩm sự kiện', 30687, -1, 0, 0);

delete FROM part
WHERE id IN (1460, 1461, 1462);

INSERT INTO part (id, type, part, data) VALUES
(1460, 0, '[{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1461, 1, '[{"icon":2955,"dx":0,"dy":0},{"icon":30678,"dx":-2,"dy":-17},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1462, 2, '[{"icon":30679,"dx":6,"dy":-13},{"icon":30677,"dx":2,"dy":9},{"icon":30679,"dx":-2,"dy":-23},{"icon":30681,"dx":-1,"dy":-23},{"icon":30683,"dx":-4,"dy":-23},{"icon":30684,"dx":0,"dy":-23},{"icon":30686,"dx":-2,"dy":-21},{"icon":30684,"dx":-6,"dy":-17},{"icon":30681,"dx":-8,"dy":-20},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE FROM mini_pet
WHERE id_temp = 2155;

INSERT INTO mini_pet (id_temp, head, `body`, leg) VALUES
(2155, 1460, 1461, 1462);