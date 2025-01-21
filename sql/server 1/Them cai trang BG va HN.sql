INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2152, 5, 3, 'Cải trang Bát Giới', 'Cải trang thành Bát Giới', 30663, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2153, 5, 3, 'Cải trang Hằng Nga', 'Cải trang thành Hằng Nga', 30665, -1, 0, 0);

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag)
  VALUES (2152, 1452, 1453, 1454, -1);

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag)
  VALUES (2153, 1455, 1456, 1457, -1);


insert INTO head_avatar (head_id, avatar_id)
  VALUES (1452, 30662);

insert INTO head_avatar (head_id, avatar_id)
  VALUES (1455, 30664);

INSERT INTO part (id, type, part, data) VALUES
(1452, 0, '[{"id":30600,"dx":0,"dy":2},{"id":30601,"dx":2,"dy":1},{"id":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1453, 1, '[{"id":30602,"dx":-1,"dy":5},{"id":30603,"dx":-3,"dy":3},{"id":30604,"dx":1,"dy":2},{"id":30605,"dx":2,"dy":2},{"id":30606,"dx":1,"dy":1},{"id":30607,"dx":1,"dy":1},{"id":30608,"dx":1,"dy":2},{"id":30609,"dx":0,"dy":3},{"id":30610,"dx":0,"dy":5},{"id":30611,"dx":0,"dy":6},{"id":30612,"dx":0,"dy":3},{"id":30613,"dx":-2,"dy":3},{"id":30614,"dx":0,"dy":2},{"id":30615,"dx":2,"dy":1},{"id":30616,"dx":0,"dy":3},{"id":30617,"dx":-2,"dy":2},{"id":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1454, 2, '[{"id":30618,"dx":10,"dy":9},{"id":30619,"dx":0,"dy":4},{"id":30620,"dx":4,"dy":4},{"id":30621,"dx":5,"dy":3},{"id":30622,"dx":2,"dy":4},{"id":30623,"dx":3,"dy":3},{"id":30624,"dx":2,"dy":3},{"id":30625,"dx":0,"dy":7},{"id":30626,"dx":-1,"dy":7},{"id":30627,"dx":2,"dy":3},{"id":30628,"dx":2,"dy":6},{"id":30629,"dx":-3,"dy":8},{"id":30630,"dx":3,"dy":8},{"id":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1455, 0, '[{"id":30631,"dx":-3,"dy":-6},{"id":30632,"dx":-3,"dy":-8},{"id":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1456, 1, '[{"id":30633,"dx":-8,"dy":-7},{"id":30634,"dx":-7,"dy":-11},{"id":30635,"dx":-10,"dy":-13},{"id":30636,"dx":-7,"dy":-12},{"id":30637,"dx":-11,"dy":-12},{"id":30638,"dx":-9,"dy":-13},{"id":30639,"dx":-10,"dy":-17},{"id":30640,"dx":-3,"dy":-3},{"id":30641,"dx":-4,"dy":-7},{"id":30642,"dx":-10,"dy":-1},{"id":30643,"dx":-6,"dy":-6},{"id":30644,"dx":-5,"dy":-4},{"id":30645,"dx":-10,"dy":-8},{"id":30646,"dx":-2,"dy":-13},{"id":30647,"dx":-9,"dy":-8},{"id":30648,"dx":-8,"dy":-7},{"id":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1457, 2, '[{"id":30649,"dx":5,"dy":8},{"id":30650,"dx":-2,"dy":-1},{"id":30651,"dx":-2,"dy":0},{"id":30652,"dx":0,"dy":0},{"id":30653,"dx":-5,"dy":2},{"id":30654,"dx":-2,"dy":0},{"id":30655,"dx":-3,"dy":-1},{"id":30656,"dx":0,"dy":4},{"id":30657,"dx":-5,"dy":0},{"id":30658,"dx":-4,"dy":-2},{"id":30659,"dx":0,"dy":-1},{"id":30660,"dx":-5,"dy":1},{"id":30661,"dx":0,"dy":-1},{"id":2955,"dx":0,"dy":0}]', NULL);

UPDATE part
SET part = REPLACE(part, 'id', 'icon')
WHERE id BETWEEN 1452 AND 1457;