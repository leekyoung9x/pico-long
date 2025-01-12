UPDATE item_option_template iot SET iot.NAME = 'Sức mạnh Rồng Thiêng tăng #% Sức đánh, HP, KI' WHERE iot.id = 245;

-- them tam Tung sua sau
DELETE FROM item_template WHERE ID = 2170;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2170, 27, 3, 'Rương Quan Que', 'Mở rương này để nhận 1 bộ Ngọc Rồng Băng.', 5007, -1, 0, 0);


DELETE FROM item_template WHERE ID = 2171;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2171, 27, 3, 'Rương Ngọc Băng', 'Mở rương này để nhận 1 bộ Ngọc Rồng Băng.', 5007, -1, 0, 0);








-- test
SELECT * FROM item_option_template iot ORDER BY iot.id DESC;

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%sức đánh%';

SELECT * FROM item_template it WHERE it.NAME LIKE '%băng%';
SELECT * FROM item_template it WHERE it.NAME LIKE '%ngọc rồng%';

SELECT * FROM item_template it WHERE it.NAME LIKE '%bông tai%';

SELECT * FROM item_template it WHERE it.NAME LIKE '%rương%';


SELECT * FROM map_template mt WHERE mt.id >= 105 && mt.id <= 110;

SELECT * FROM part p WHERE p.id = 320;

SELECT * FROM item_template it ORDER BY it.id DESC;
                                        



SELECT p.data_task, p.data_side_task, p.* FROM player p WHERE p.name LIKE '%hungpyy%';
SELECT a.vnd, a.* FROM account a WHERE a.id = 3928;

SELECT * FROM task_main_template tmt;
SELECT * FROM task_sub_template tst;

SELECT * FROM mob_reward mr;
SELECT * FROM mob_reward_map mrm;
SELECT * FROM mob_template mt;


SELECT * FROM part p WHERE p.id = 610;


SELECT * FROM item_template it WHERE it.NAME LIKE '%tuần lộc%';


SELECT * FROM map_template mt WHERE mt.NAME LIKE '%nappa%';

SELECT * FROM map_template mt WHERE mt.id NOT IN (68, 105, 106, 107, 108, 109, 110, 92, 93, 94, 96, 97, 98, 99, 100, 102, 103);


SELECT GROUP_CONCAT(id SEPARATOR ', ') AS id_list
FROM map_template AS mt
WHERE mt.id NOT IN (68, 105, 106, 107, 108, 109, 110, 92, 93, 94, 96, 97, 98, 99, 100, 102, 103)
ORDER BY id;


SELECT * FROM mini_pet mp WHERE mp.id_temp = 936;


SELECT * FROM part p WHERE p.id IN (718, 719, 720);


SELECT * FROM item_template it WHERE it.NAME LIKE '%ngọc đen%';

SELECT * FROM item_template it WHERE it.icon_id = 9850;


SELECT * FROM task_main_template tmt WHERE tmt.NAME LIKE '%tiểu đội%';
SELECT * FROM task_sub_template tst WHERE tst.task_main_id = 20;



SELECT * FROM player p WHERE p.name = 'lhpem';