delete FROM part WHERE id IN (1480, 1481, 1482);

INSERT INTO part (id, type, part, data) VALUES
(1480, 0, '[{"icon":29100,"dx":0,"dy":-10},{"icon":29101,"dx":0,"dy":-8},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1481, 1, '[{"icon":29102,"dx":0,"dy":-5},{"icon":29103,"dx":-2,"dy":-8},{"icon":29104,"dx":-2,"dy":-11},{"icon":29105,"dx":-2,"dy":-10},{"icon":29106,"dx":0,"dy":-9},{"icon":29107,"dx":0,"dy":-6},{"icon":29108,"dx":0,"dy":-7},{"icon":29109,"dx":-5,"dy":-5},{"icon":29110,"dx":-1,"dy":-6},{"icon":29111,"dx":-4,"dy":-8},{"icon":29112,"dx":-1,"dy":-6},{"icon":29113,"dx":-2,"dy":-7},{"icon":29114,"dx":-1,"dy":-6},{"icon":29115,"dx":1,"dy":-8},{"icon":29116,"dx":-3,"dy":-9},{"icon":29117,"dx":0,"dy":-7},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1482, 2, '[{"icon":29118,"dx":1,"dy":1},{"icon":29119,"dx":-1,"dy":-4},{"icon":29120,"dx":-2,"dy":-2},{"icon":29121,"dx":0,"dy":-3},{"icon":29122,"dx":-2,"dy":-4},{"icon":29123,"dx":-1,"dy":-1},{"icon":29124,"dx":0,"dy":-1},{"icon":29125,"dx":-2,"dy":-2},{"icon":29126,"dx":0,"dy":-2},{"icon":29127,"dx":-2,"dy":-4},{"icon":29128,"dx":-2,"dy":-4},{"icon":29129,"dx":0,"dy":0},{"icon":29130,"dx":0,"dy":-1},{"icon":2955,"dx":0,"dy":0}]', NULL);

delete FROM item_template WHERE id = 2194;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2194, 5, 3, 'Cải trang Vegeta Blue Tết', 'Cải trang thành Vegeta Blue Tết', 29132, -1, 0, 0);

delete FROM cai_trang where id_temp = 2194;

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag)
  VALUES (2194, 1480, 1481, 1482, DEFAULT); 

delete FROM head_avatar where head_id = 1480;

INSERT INTO head_avatar (head_id, avatar_id)
  VALUES (1480, 29131); 

SELECT * FROM part;

SELECT * FROM cai_trang;