delete FROM item_template
WHERE id IN (2154);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2154, 21, 3, 'Pet Gấu hồng', 'Vật phẩm sự kiện', 30676, -1, 0, 0);

delete FROM part
WHERE id IN (1458, 1459, 1460);

INSERT INTO part (id, type, part, data) VALUES
(1458, 0, '[{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1459, 1, '[{"icon":2955,"dx":0,"dy":0},{"icon":30666,"dx":-2,"dy":-15},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1460, 2, '[{"icon":30673,"dx":7,"dy":-10},{"icon":30675,"dx":2,"dy":9},{"icon":30667,"dx":-2,"dy":-21},{"icon":30668,"dx":-1,"dy":-21},{"icon":30670,"dx":-4,"dy":-21},{"icon":30671,"dx":0,"dy":-21},{"icon":30673,"dx":-2,"dy":-19},{"icon":30671,"dx":-6,"dy":-17},{"icon":30668,"dx":-7,"dy":-18},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE FROM mini_pet
WHERE id_temp = 2154;

INSERT INTO mini_pet (id_temp, head, `body`, leg) VALUES
(2154, 1458, 1459, 1460);