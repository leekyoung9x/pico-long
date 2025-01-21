-- ===== map 105
-- Tai tím
DELETE FROM mob_reward WHERE ID = 10;
DELETE FROM mob_reward WHERE ID = 11;
DELETE FROM mob_reward WHERE ID = 12;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(10, 66, 929, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(11, 66, 930, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(12, 66, 931, 1, 10000, True);

DELETE FROM mob_reward_map WHERE ID = 40;
DELETE FROM mob_reward_map WHERE ID = 41;
DELETE FROM mob_reward_map WHERE ID = 42;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (40, 10, 105);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (41, 11, 105);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (42, 12, 105);

-- ====== map 106
-- Abo
DELETE FROM mob_reward WHERE ID = 13;
DELETE FROM mob_reward WHERE ID = 14;
DELETE FROM mob_reward WHERE ID = 15;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(13, 67, 929, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(14, 67, 930, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(15, 67, 931, 1, 10000, True);

DELETE FROM mob_reward_map WHERE ID = 43;
DELETE FROM mob_reward_map WHERE ID = 44;
DELETE FROM mob_reward_map WHERE ID = 45;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (43, 13, 106);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (44, 14, 106);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (45, 15, 106);


-- Tai tím
DELETE FROM mob_reward_map WHERE ID = 46;
DELETE FROM mob_reward_map WHERE ID = 47;
DELETE FROM mob_reward_map WHERE ID = 48;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (46, 10, 106);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (47, 11, 106);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (48, 12, 106);




-- ===== map 107
-- abo
DELETE FROM mob_reward_map WHERE ID = 49;
DELETE FROM mob_reward_map WHERE ID = 50;
DELETE FROM mob_reward_map WHERE ID = 51;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (49, 13, 107);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (50, 14, 107);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (51, 15, 107);


-- ====== map 108
-- Abo
DELETE FROM mob_reward_map WHERE ID = 52;
DELETE FROM mob_reward_map WHERE ID = 53;
DELETE FROM mob_reward_map WHERE ID = 54;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (52, 13, 108);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (53, 14, 108);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (54, 15, 108);

-- kado
DELETE FROM mob_reward WHERE ID = 16;
DELETE FROM mob_reward WHERE ID = 17;
DELETE FROM mob_reward WHERE ID = 18;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(16, 68, 929, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(17, 68, 930, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(18, 68, 931, 1, 10000, True);

DELETE FROM mob_reward_map WHERE ID = 55;
DELETE FROM mob_reward_map WHERE ID = 56;
DELETE FROM mob_reward_map WHERE ID = 57;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (55, 16, 108);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (56, 17, 108);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (57, 18, 108);



-- ====== map 109
-- kaido
DELETE FROM mob_reward_map WHERE ID = 58;
DELETE FROM mob_reward_map WHERE ID = 59;
DELETE FROM mob_reward_map WHERE ID = 60;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (58, 16, 109);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (59, 17, 109);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (60, 18, 109);

-- da xanh
DELETE FROM mob_reward WHERE ID = 19;
DELETE FROM mob_reward WHERE ID = 20;
DELETE FROM mob_reward WHERE ID = 21;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(19, 69, 929, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(20, 69, 930, 1, 10000, True);
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(21, 69, 931, 1, 10000, True);

DELETE FROM mob_reward_map WHERE ID = 61;
DELETE FROM mob_reward_map WHERE ID = 62;
DELETE FROM mob_reward_map WHERE ID = 63;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (61, 19, 109);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (62, 20, 109);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (63, 21, 109);
-- ====== map 110
-- kaido
DELETE FROM mob_reward_map WHERE ID = 64;
DELETE FROM mob_reward_map WHERE ID = 65;
DELETE FROM mob_reward_map WHERE ID = 66;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (64, 16, 110);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (65, 17, 110);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (66, 18, 110);



-- ===== da xanh
DELETE FROM mob_reward_map WHERE ID = 67;
DELETE FROM mob_reward_map WHERE ID = 68;
DELETE FROM mob_reward_map WHERE ID = 69;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (67, 19, 110);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (68, 20, 110);
INSERT INTO mob_reward_map (id, mob_reward_id, map_id)
  VALUES (69, 21, 110);





