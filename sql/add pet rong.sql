delete FROM item_template
WHERE id IN (2195);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2195, 21, 3, 'Pet Rồng thần tài', 'Vật phẩm sự kiện', 29142, -1, 0, 0);

DELETE FROM part WHERE ID IN (1483, 1484, 1485);

INSERT INTO part (id, type, part, data) VALUES
(1483, 0, '[{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1484, 1, '[{"icon":2955,"dx":0,"dy":0},{"icon":29133,"dx":-12,"dy":-22},{"icon":29135,"dx":-11,"dy":-35},{"icon":29137,"dx":-7,"dy":-34},{"icon":29139,"dx":-12,"dy":-34},{"icon":29140,"dx":-9,"dy":-32},{"icon":29135,"dx":-11,"dy":-35},{"icon":29140,"dx":-4,"dy":-27},{"icon":29137,"dx":-2,"dy":-24},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1485, 2, '[{"icon":2955,"dx":0,"dy":0},{"icon":29134,"dx":-10,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE FROM mini_pet
WHERE id_temp = 2195;

INSERT INTO mini_pet (id_temp, head, `body`, leg) VALUES
(2195, 1483, 1484, 1485);