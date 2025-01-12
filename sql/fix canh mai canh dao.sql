SELECT * FROM flag_bag;

SELECT * FROM item_template
where id IN (1185, 1186);

delete FROM flag_bag
WHERE id IN (83, 84);

INSERT INTO flag_bag (ID, icon_data, NAME, gold, gem, icon_id)
  VALUES (83, '10899', 'Cành mai', -1, -1, 1185);

INSERT INTO flag_bag (ID, icon_data, NAME, gold, gem, icon_id)
  VALUES (84, '10903', 'Cành đào', -1, -1, 1186);

UPDATE item_template SET part = 83 WHERE id = 1185;
UPDATE item_template SET part = 84 WHERE id = 1186;