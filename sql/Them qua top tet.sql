DELETE
  FROM item_template
  WHERE id IN (2201);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2201, 21, 3, 'Pet Búp bê thời tiết', 'Vật phẩm sự kiện', 29174, -1, 0, 0);

DELETE
  FROM part
  WHERE id IN (1489, 1490, 1491);

INSERT INTO part (id, type, part, data) VALUES
(1489, 0, '[{"icon":29172,"dx":0,"dy":22},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1490, 1, '[{"icon":29163,"dx":-2,"dy":-16},{"icon":29164,"dx":-5,"dy":-25},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1491, 2, '[{"icon":29173,"dx":10,"dy":-13},{"icon":2955,"dx":0,"dy":0},{"icon":29167,"dx":-22,"dy":-29},{"icon":29168,"dx":-19,"dy":-31},{"icon":29169,"dx":-23,"dy":-29},{"icon":29170,"dx":-19,"dy":-28},{"icon":29171,"dx":-21,"dy":-29},{"icon":29164,"dx":-6,"dy":-20},{"icon":29164,"dx":-9,"dy":-23},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]', NULL);

DELETE
  FROM mini_pet
  WHERE id_temp = 2201;

INSERT INTO mini_pet (id_temp, head, `body`, leg)
  VALUES
  (2201, 1489, 1490, 1491);

-- ---------------------------------------------------------------------------------------------------------------------------------

DELETE
  FROM img_by_name
  WHERE id IN (127, 128);

DELETE
  FROM item_template
  WHERE id IN (2202);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2202, 23, 3, 'Ván bay "Túi Vàng Thần Tài"', 'Vật phẩm sự kiện', 29155, 27, 0, 0);

INSERT INTO img_by_name (id, NAME, n_frame)
  VALUES (127, 'mount_27_0', 4);

INSERT INTO img_by_name (id, NAME, n_frame)
  VALUES (128, 'mount_27_1', 4);

-- --------------------------------------------------------------------------------------------------------------------------------------------------------------------
DELETE
  FROM item_template
  WHERE id IN (2203);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2203, 11, 3, 'Cánh Thiên Thần', 'Vật phẩm sự kiện', 29162, 86, 0, 0);

delete from flag_bag WHERE ID = 86;

INSERT INTO flag_bag (id, icon_data, NAME, gold, gem, icon_id) VALUES
(86, '29156,29157,29158,29159,29160,29161', 'Cánh thiên thần', -1, -1, 29162);

-- --------------------------------------------------------------------------------------------------------------------------------
delete FROM cai_trang
WHERE id_temp = 2204;

delete FROM head_avatar
WHERE head_id IN (1492, 1493, 1494);

delete FROM array_head_2_frames
WHERE id = 18;

DELETE
  FROM item_template
  WHERE id IN (2204);

DELETE
  FROM part
  WHERE id IN (1492, 1493, 1494, 1495, 1496);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2204, 5, 3, 'Cải trang Nữ y tá cuồng loạn', 'Cải trang Nữ y tá cuồng loạn', 29212, -1, 0, 0);

INSERT INTO array_head_2_frames (id, data) VALUES
(18, '[1492,1493,1494]');

INSERT INTO head_avatar (head_id, avatar_id) VALUES
(1492, 29211);

INSERT INTO head_avatar (head_id, avatar_id) VALUES
(1493, 29211);

INSERT INTO head_avatar (head_id, avatar_id) VALUES
(1494, 29211);

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag) VALUES
(2204, 1492, 1495, 1496, -1);

INSERT INTO part (id, type, part, data) VALUES
(1492, 0, '[{"icon":29176,"dx":-2,"dy":-8},{"icon":29179,"dx":-6,"dy":-7},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1493, 0, '[{"icon":29177,"dx":-2,"dy":-10},{"icon":29180,"dx":-9,"dy":-9},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1494, 0, '[{"icon":29178,"dx":-3,"dy":-7},{"icon":29181,"dx":-6,"dy":-6},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1495, 1, '[{"icon":29182,"dx":-4,"dy":-5},{"icon":29183,"dx":-3,"dy":-9},{"icon":29184,"dx":-4,"dy":-8},{"icon":29185,"dx":-2,"dy":-9},{"icon":29186,"dx":-1,"dy":-8},{"icon":29187,"dx":0,"dy":-7},{"icon":29188,"dx":0,"dy":-7},{"icon":29189,"dx":1,"dy":-7},{"icon":29190,"dx":1,"dy":-3},{"icon":29191,"dx":-2,"dy":-4},{"icon":29192,"dx":-4,"dy":-8},{"icon":29193,"dx":-4,"dy":-6},{"icon":29194,"dx":1,"dy":-5},{"icon":29195,"dx":3,"dy":-12},{"icon":29196,"dx":-6,"dy":-8},{"icon":29197,"dx":-6,"dy":-8},{"icon":29175,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1496, 2, '[{"icon":29198,"dx":5,"dy":3},{"icon":29199,"dx":-1,"dy":-5},{"icon":29200,"dx":2,"dy":-2},{"icon":29201,"dx":4,"dy":-2},{"icon":29202,"dx":3,"dy":-3},{"icon":29203,"dx":4,"dy":-2},{"icon":29204,"dx":5,"dy":0},{"icon":29205,"dx":2,"dy":0},{"icon":29206,"dx":1,"dy":-4},{"icon":29207,"dx":1,"dy":-5},{"icon":29208,"dx":4,"dy":-2},{"icon":29209,"dx":2,"dy":2},{"icon":29210,"dx":0,"dy":-2},{"icon":2955,"dx":0,"dy":0}]', NULL);
