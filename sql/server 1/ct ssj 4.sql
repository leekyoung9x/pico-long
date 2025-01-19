delete FROM part
WHERE ID IN (1466, 1467, 1468, 1469, 1470);

DELETE FROM cai_trang
WHERE id_temp = 2158;

DELETE FROM head_avatar
WHERE head_id IN (1466, 1467,1468);

DELETE FROM item_template
WHERE ID = 2158;

INSERT INTO part (id, type, part, data) VALUES
(1466, 0, '[{"icon":30745,"dx":-3,"dy":-20},{"icon":30748,"dx":-2,"dy":-19},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1467, 0, '[{"icon":30746,"dx":-3,"dy":-20},{"icon":30749,"dx":-2,"dy":-19},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1468, 0, '[{"icon":30747,"dx":-3,"dy":-20},{"icon":30750,"dx":-2,"dy":-19},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1469, 1, '[{"icon":30751,"dx":-11,"dy":-6},{"icon":30752,"dx":-8,"dy":-12},{"icon":30753,"dx":-3,"dy":-14},{"icon":30754,"dx":2,"dy":-13},{"icon":30755,"dx":-2,"dy":-12},{"icon":30756,"dx":0,"dy":-12},{"icon":30757,"dx":0,"dy":-11},{"icon":30758,"dx":-5,"dy":-13},{"icon":30759,"dx":-1,"dy":-10},{"icon":30760,"dx":-10,"dy":-16},{"icon":30761,"dx":-7,"dy":-9},{"icon":30762,"dx":-7,"dy":-11},{"icon":30763,"dx":-4,"dy":-12},{"icon":30764,"dx":-1,"dy":-11},{"icon":30765,"dx":-7,"dy":-15},{"icon":30766,"dx":-13,"dy":-10},{"icon":2955,"dx":0,"dy":0}]', NULL);

INSERT INTO part (id, type, part, data) VALUES
(1470, 2, '[{"icon":30767,"dx":4,"dy":0},{"icon":30768,"dx":-2,"dy":-7},{"icon":30769,"dx":1,"dy":-7},{"icon":30770,"dx":-1,"dy":-7},{"icon":30771,"dx":-7,"dy":-7},{"icon":30772,"dx":-5,"dy":-8},{"icon":30773,"dx":-6,"dy":-7},{"icon":30774,"dx":0,"dy":-4},{"icon":30775,"dx":0,"dy":-5},{"icon":30776,"dx":-7,"dy":-8},{"icon":30777,"dx":-9,"dy":-7},{"icon":30778,"dx":-10,"dy":-5},{"icon":30779,"dx":-2,"dy":-7},{"icon":2955,"dx":0,"dy":0}]', NULL);

insert INTO head_avatar (head_id, avatar_id)
  VALUES (1466, 30780);

insert INTO head_avatar (head_id, avatar_id)
  VALUES (1467, 30780);

insert INTO head_avatar (head_id, avatar_id)
  VALUES (1468, 30780);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2158, 5, 3, 'Cải trang Gogeta SSJ 4', 'Cải trang thành Gogeta SSJ 4', 30781, -1, 0, 0);

INSERT INTO cai_trang (id_temp, head, `body`, leg, bag)
  VALUES (2158, 1466, 1469, 1470, -1);

DELETE FROM array_head_2_frames
WHERE ID = 17;

INSERT INTO array_head_2_frames (ID, DATA)
  VALUES (17, '[1466,1467,1468]');


DELETE FROM item_template
WHERE id = 2157;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2157, 11, 3, 'Phương thiên họa kích', '', 30700, 80, 0, 0);

DELETE FROM flag_bag
WHERE id = 80;

INSERT INTO flag_bag (ID, icon_data, NAME, gold, gem, icon_id)
  VALUES (80, '30701,30702,30703,30704,30705,30706,30707,30708,30709,30710,30711,30712,30713,30714,30715,30716,30717,30718,30719,30720,30721,30722,30723,30724,30725,30726,30727,30728,30729,30730,30731,30732,30733,30734,30735,30736,30737,30738,30739,30740,30741,30742,30743,30744,30745', 'Phương thiên họa kích', -1, -1, 30700);