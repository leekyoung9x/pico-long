delete FROM item_template
WHERE id IN (2156);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2156, 21, 3, 'Pet Thỏ cam trung thu', 'Vật phẩm sự kiện', 30699, -1, 0, 0);

delete FROM part
WHERE id IN (1463, 1464, 1465);

INSERT INTO part (id, type, part, data) VALUES
(1463, 0, '[{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1464, 1, '[{"icon":2955,"dx":0,"dy":0},{"icon":30690,"dx":-5,"dy":-12},{"icon":30690,"dx":-8,"dy":-14},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1465, 2, '[{"icon":30695,"dx":-1,"dy":-7},{"icon":30688,"dx":-6,"dy":1},{"icon":30688,"dx":-6,"dy":1},{"icon":30692,"dx":-5,"dy":-19},{"icon":30694,"dx":-8,"dy":-17},{"icon":30695,"dx":-6,"dy":-23},{"icon":30697,"dx":-7,"dy":-17},{"icon":30692,"dx":-10,"dy":-13},{"icon":30697,"dx":-13,"dy":-14},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE FROM mini_pet
WHERE id_temp = 2156;

INSERT INTO mini_pet (id_temp, head, `body`, leg) VALUES
(2156, 1463, 1464, 1465);