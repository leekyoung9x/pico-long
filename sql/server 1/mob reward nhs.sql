SELECT * FROM item_template
WHERE NAME LIKE '%mảnh vỡ bông tai%';

SELECT * FROM item_template
WHERE NAME LIKE '%mảnh hồn bông tai%';

SELECT * FROM item_template
WHERE NAME LIKE '%đá xanh lam%';

SELECT * FROM mob_reward
WHERE mob_id IN (50, 56, 57);

DELETE a FROM mob_reward_map a INNER JOIN (SELECT id FROM mob_reward
WHERE mob_id IN (50, 56, 57)) b ON a.mob_reward_id = b.id;

DELETE FROM mob_reward WHERE mob_id IN (50, 56, 57);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (50, 933, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (56, 933, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (57, 933, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (50, 934, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (56, 934, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (57, 934, 1, 100, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (50, 935, 1, 1000, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (56, 935, 1, 1000, 1);

INSERT INTO mob_reward (mob_id, item_id, ratio, type_ratio, for_all_gender)
  VALUES (57, 935, 1, 1000, 1);

INSERT INTO mob_reward_map (mob_reward_id, map_id)
SELECT id, 122 FROM mob_reward
WHERE mob_id IN (50, 56, 57);

INSERT INTO mob_reward_map (mob_reward_id, map_id)
SELECT id, 123 FROM mob_reward
WHERE mob_id IN (50, 56, 57);

INSERT INTO mob_reward_map (mob_reward_id, map_id)
SELECT id, 124 FROM mob_reward
WHERE mob_id IN (50, 56, 57);