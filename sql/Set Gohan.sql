-- option x# TNSM khi đủ 5 món

DELETE FROM item_option_template WHERE ID = 250;
INSERT INTO item_option_template (id, NAME, TYPE)
  VALUES (250, 'x# TNSM khi đủ 5 món', 0);

DELETE FROM item_option_template WHERE ID = 251;
INSERT INTO item_option_template (id, NAME, TYPE)
  VALUES (251, 'x# TNSM khi đủ 5 món', 0);

DELETE FROM item_option_template WHERE ID = 252;
INSERT INTO item_option_template (id, NAME, TYPE)
  VALUES (252, 'x# TNSM khi đủ 5 món', 0);

-- set kakarot
UPDATE item_option_template iot SET iot.NAME = 'x2 sát thương đấm Galick khi đủ 5 món' WHERE iot.id = 136;
-- set daimao
UPDATE item_option_template iot SET iot.NAME = 'Set Daimao' WHERE iot.id = 132; 
-- set cadic
UPDATE item_option_template iot SET iot.NAME = 'Set Cadic' WHERE iot.id = 134; 





