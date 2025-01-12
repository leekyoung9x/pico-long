DROP TABLE IF EXISTS mob_reward;
DROP TABLE IF EXISTS mob_reward_map;

CREATE TABLE mob_reward (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mob_id int,
  item_id int,
  ratio int,
  type_ratio int,
  for_all_gender bit(1)
);

CREATE TABLE mob_reward_map (
  id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mob_reward_id int,
  map_id int
);

-- INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
--   VALUES (0, 2013, 1, 2, 1);
-- 
-- INSERT INTO mob_reward_map (mob_reward_id, map_id)
--   VALUES (1, 1);
-- 
-- INSERT INTO mob_reward_map (mob_reward_id, map_id)
--   VALUES (1, 2);
-- 
-- INSERT INTO mob_reward_map (mob_reward_id, map_id)
--   VALUES (1, 3);
-- 
-- INSERT INTO mob_reward_map (mob_reward_id, map_id)
--   VALUES (1, 4);
-- 
-- SELECT * FROM mob_reward_map;
-- 
-- SELECT
--   a.mob_id,
--   a.item_id,
--   a.ratio,
--   a.type_ratio,
--   a.for_all_gender,
--   b.map
-- FROM mob_reward a
--   INNER JOIN (SELECT
--       mob_reward_id,
--       GROUP_CONCAT(map_id SEPARATOR ',') AS map
--     FROM mob_reward_map
--     GROUP BY mob_reward_id) b
--     ON a.id = b.mob_reward_id;
-- 
-- SELECT mob_id FROM mob_reward GROUP BY mob_id;
-- 
-- SELECT * FROM item_template
-- WHERE NAME LIKE '%ngọc rồng%';