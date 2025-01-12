-- cập nhật tên item gậy gỗ
UPDATE item_template SET TYPE = 27, gender = 3, NAME = 'Gậy gỗ', description = 'Vật phẩm sự kiện', icon_id = 12176, part = -1, is_up_to_up = 0, power_require = 0 WHERE id = 1932;

-- quái thung lũng đá up ra gậy gỗ
UPDATE mob_reward mr SET mr.item_id = 1932, mr.type_ratio = 500 WHERE ID = 23;
-- nappa rơi ra kẹo đường
DELETE FROM mob_reward WHERE ID = 24;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(24, 25, 1168, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 25;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(25, 39, 1168, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 26;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(26, 40, 1168, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 27;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(27, 41, 1168, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 28;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(28, 42, 1168, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 29;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(29, 43, 1168, 1, 500, True);
-- map 68
DELETE FROM mob_reward_map WHERE ID = 72;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(72, 24, 68);
DELETE FROM mob_reward_map WHERE ID = 73;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(73, 25, 68);

-- map 69
DELETE FROM mob_reward_map WHERE ID = 74;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(74, 24, 69);
DELETE FROM mob_reward_map WHERE ID = 75;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(75, 25, 69);
DELETE FROM mob_reward_map WHERE ID = 76;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(76, 26, 69);

-- map 70
DELETE FROM mob_reward_map WHERE ID = 77;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(77, 25, 70);
DELETE FROM mob_reward_map WHERE ID = 78;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(78, 26, 70);
DELETE FROM mob_reward_map WHERE ID = 79;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(79, 27, 70);

  -- map 71
DELETE FROM mob_reward_map WHERE ID = 80;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(80, 27, 71);
DELETE FROM mob_reward_map WHERE ID = 81;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(81, 28, 71);

-- map 72
DELETE FROM mob_reward_map WHERE ID = 82;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(82, 27, 72);
DELETE FROM mob_reward_map WHERE ID = 83;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(83, 28, 72);
DELETE FROM mob_reward_map WHERE ID = 84;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(84, 29, 72);
-- =========================================
-- ngũ hành sơn rơi bột mì
DELETE FROM mob_reward WHERE ID = 30;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(30, 50, 888, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 31;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(31, 56, 888, 1, 500, True);
DELETE FROM mob_reward WHERE ID = 32;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(32, 57, 888, 1, 500, True);

DELETE FROM mob_reward WHERE ID = 33;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(33, 25, 888, 1, 1000, True);
DELETE FROM mob_reward WHERE ID = 34;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(34, 39, 888, 1, 1000, True);
DELETE FROM mob_reward WHERE ID = 35;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(35, 40, 888, 1, 1000, True);
DELETE FROM mob_reward WHERE ID = 36;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(36, 41, 888, 1, 1000, True);
DELETE FROM mob_reward WHERE ID = 37;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(37, 42, 888, 1, 1000, True);
DELETE FROM mob_reward WHERE ID = 38;
INSERT INTO mob_reward(id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES(38, 43, 888, 1, 1000, True);

DELETE FROM mob_reward_map WHERE ID = 85;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(85, 30, 122);
DELETE FROM mob_reward_map WHERE ID = 86;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(86, 32, 122);
DELETE FROM mob_reward_map WHERE ID = 87;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(87, 32, 123);
DELETE FROM mob_reward_map WHERE ID = 88;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(88, 31, 124);

-- map 68
DELETE FROM mob_reward_map WHERE ID = 89;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(89, 33, 68);
DELETE FROM mob_reward_map WHERE ID = 90;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(90, 34, 68);

-- map 69
DELETE FROM mob_reward_map WHERE ID = 91;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(91, 33, 69);
DELETE FROM mob_reward_map WHERE ID = 92;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(92, 34, 69);
DELETE FROM mob_reward_map WHERE ID = 93;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(93, 35, 69);

-- map 70
DELETE FROM mob_reward_map WHERE ID = 94;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(94, 34, 70);
DELETE FROM mob_reward_map WHERE ID = 95;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(95, 35, 70);
DELETE FROM mob_reward_map WHERE ID = 96;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(96, 36, 70);

  -- map 71
DELETE FROM mob_reward_map WHERE ID = 97;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(97, 36, 71);
DELETE FROM mob_reward_map WHERE ID = 98;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(98, 37, 71);

-- map 72
DELETE FROM mob_reward_map WHERE ID = 99;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(99, 36, 72);
DELETE FROM mob_reward_map WHERE ID = 100;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(100, 37, 72);
DELETE FROM mob_reward_map WHERE ID = 101;
INSERT INTO mob_reward_map(id, mob_reward_id, map_id) VALUES(101, 38, 72);


-- option biến thành gậy như ý
UPDATE item_option_template iot SET iot.NAME = 'Biến kẻ địch thành bộ xương' WHERE iot.id = 174;
UPDATE item_option_template iot SET iot.NAME = 'Không thể bị hóa bộ xương' WHERE iot.id = 168;
  
-- thêm cải trang đường tăng
UPDATE item_template SET TYPE = 5, gender = 3, NAME = 'Cải trang Đường Tăng', description = 'Thiện tai', icon_id = 4521, part = -1, is_up_to_up = 0, power_require = 0 WHERE id = 1931;
DELETE FROM cai_trang WHERE id_temp = 1931;
INSERT INTO cai_trang(id_temp, head, body, leg, bag) VALUES(1931, 467, 468, 469, -1);
DELETE FROM head_avatar WHERE head_id = 467;
INSERT INTO head_avatar (head_id, avatar_id)
VALUES (467, 4544);

-- cải trang ngộ không
UPDATE item_template it SET it.NAME = 'Cải trang Ngộ Không', it.description = 'Tôn hành giả' WHERE ID = 547;

-- thêm cột lưu thời gian hồi effect
ALTER TABLE player
ADD COLUMN time_effect text;

ALTER TABLE player
DROP COLUMN time_effect;





-- test
SELECT * FROM map_template mt WHERE mt.id >= 68 AND mt.id <= 72;

/*
68 => 39, 25
69 => 39, 40, 25
70 => 39, 40, 41
71 => 41, 42
72 => 41, 42, 43
*/



SELECT * FROM mob_reward mr ORDER BY mr.id DESC;
SELECT * FROM item_template it WHERE ID = 547;
SELECT * FROM mob_reward_map mrm ORDER BY mrm.id DESC;
SELECT * FROM mob_template mt WHERE ID IN (25, 39, 40, 41, 42, 43);
SELECT * FROM cai_trang ct ORDER BY ct.id_temp desc;
SELECT * FROM mob_template mt WHERE ID IN (50, 57, 56);
SELECT * FROM item_template it WHERE it.NAME like '%bột mì%';
SELECT time_effect, p.time_may_do, p.* FROM player p WHERE p.name = 'daxanhle';
SELECT * FROM item_template it WHERE it.id IN (533, 1936, 1168, 888);
SELECT * FROM item_template it ORDER BY it.id DESC;
SELECT * FROM player p WHERE p.name LIKE '%bonecon%';
SELECT * FROM account a WHERE a.id = 12108;
SELECT * FROM player p WHERE p.id = 567;
SELECT  time_effect, p.time_may_do, p.*  FROM player p WHERE p.name LIKE '%thợ săn%';
SELECT * FROM account a WHERE a.username = 'td';
SELECT time_effect, p.* FROM player AS p WHERE account_id = 3928;
SELECT * FROM account a WHERE a.id = 3928;
select * from player where account_id = 3928 limit 1;

SELECT * FROM open_box ob;
SELECT * FROM open_box_option obo;
SELECT * FROM map_template mt WHERE mt.NAME LIKE '%trạm tàu vũ trụ%';
SELECT * FROM map_template mt WHERE mt.NAME like '%vách núi%';
SELECT * FROM map_template mt WHERE mt.NAME like '%nappa%';
SELECT * FROM map_template mt WHERE mt.id = 212;
SELECT * FROM mob_template mt WHERE mt.id = 90;
SELECT * FROM mob_reward_map mrm WHERE mrm.map_id = 212;
SELECT * FROM mob_reward mr WHERE mr.id IN (22, 23);
SELECT * FROM mob_template mt WHERE mt.id IN (39, 25);

SELECT * FROM item_template it WHERE it.id IN (674, 2086);
SELECT * FROM mob_reward mr ORDER BY mr.id DESC;
SELECT * FROM mob_reward_map mrm;

SELECT * FROM item_template it WHERE it.id IN (2180, 2181, 2182);



SELECT * FROM item_template it WHERE id IN (1932, 533, 1168, 888);
SELECT * FROM item_template it WHERE it.NAME LIKE '%đá bảo vệ%';
SELECT * FROM map_template mt WHERE mt.id = 44;
SELECT * FROM player p WHERE p.gender = 1 ORDER BY p.power DESC;
SELECT * FROM account a WHERE a.id = 4652;
SELECT * FROM player p WHERE p.name LIKE '%daxanhle%';
UPDATE player p SET p.items_body = '[{"quantity":0,"create_time":1732300195301,"temp_id":652,"option":[[47,1762],[249,0],[21,110],[130,0],[142,300],[103,80],[102,10],[163,15],[72,4],[30,0],[107,10]]},{"quantity":0,"create_time":1732298119008,"temp_id":653,"option":[[22,181],[249,0],[21,110],[130,0],[142,300],[103,80],[102,10],[72,2],[163,15],[30,0],[107,10]]},{"quantity":0,"create_time":1732294287647,"temp_id":659,"option":[[0,10890],[249,0],[21,110],[130,0],[142,300],[103,80],[102,10],[72,2],[163,15],[30,0],[107,10]]},{"quantity":0,"create_time":1732294275458,"temp_id":660,"option":[[23,262],[249,0],[21,110],[130,0],[142,300],[103,80],[102,10],[72,6],[163,15],[30,0],[107,10]]},{"quantity":0,"create_time":1732298085446,"temp_id":656,"option":[[14,19],[249,0],[21,110],[130,0],[142,300],[103,80],[102,10],[72,4],[163,15],[30,0],[107,10]]},{"quantity":1,"create_time":1734964353904,"temp_id":1931,"option":[[73,0],[168,0],[72,2]]},{"quantity":1,"create_time":1730254746046,"temp_id":536,"option":[[9,8222],[103,80],[102,10],[30,0],[107,10]]},{"quantity":1,"create_time":1733543672292,"temp_id":2157,"option":[[50,50],[77,50],[103,50],[42,10]]},{"quantity":1,"create_time":1734856947445,"temp_id":920,"option":[[73,0]]},{"quantity":1,"create_time":1729869865533,"temp_id":2108,"option":[[50,40],[77,40],[103,40]]},{"quantity":1,"create_time":1730546860972,"temp_id":2130,"option":[[50,40],[77,40],[103,40],[14,2],[244,5]]},{"quantity":1,"create_time":1732956505606,"temp_id":2076,"option":[[103,18],[14,7],[21,40],[87,0],[237,5],[238,164],[30,0]]},{"quantity":1,"create_time":1732982548646,"temp_id":805,"option":[[169,10]]},{"quantity":1,"create_time":1734275197361,"temp_id":1327,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1732045931976,"temp_id":2185,"option":[[50,20],[77,20],[103,20]]}]' WHERE p.id = 1891;

SELECT * FROM map_template mt;
SELECT * FROM item_option_template iot WHERE name LIKE '%Sát thương%';
SELECT * FROM item_option_template iot ORDER BY iot.id DESC;

SELECT * FROM item_option_template iot WHERE iot.id = 920;
SELECT * FROM item_template it WHERE it.id = 920;
SELECT * FROM part p WHERE p.id = 10;

SELECT * FROM part p WHERE p.id = 10;


SELECT * FROM item_option_template iot ORDER BY iot.id DESC;
SELECT * FROM item_template it WHERE it.id = 2014;
SELECT * FROM item_template it WHERE it.description like '%ngộ không%';
SELECT * FROM item_template it WHERE it.description like '%tăng%';
SELECT * FROM item_template it WHERE it.name like '%đường tăng%';

SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%đường tăng%';
SELECT * FROM cai_trang ct WHERE ct.head = 467;
SELECT * FROM head_avatar ha WHERE ha.head_id = 467;


SELECT * FROM account a WHERE a.username = 'td';
SELECT * FROM player p WHERE p.account_id = 3928;
SELECT * FROM part p WHERE p.id = 545;



UPDATE player p SET p.items_body = '[{"quantity":0,"create_time":1733107694851,"temp_id":1048,"option":[[47,6950],[249,0],[129,0],[141,150],[21,120],[198,10],[50,44],[102,11],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694863,"temp_id":1051,"option":[[6,173650],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694873,"temp_id":1054,"option":[[0,38075],[249,0],[129,0],[141,150],[21,120],[198,10],[50,44],[102,11],[72,8],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694883,"temp_id":1057,"option":[[7,164450],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694892,"temp_id":1060,"option":[[14,23],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":1,"create_time":1734860647809,"temp_id":547,"option":[[73,0],[174,0]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1734618186184,"temp_id":2060,"option":[[73,0]]},{"quantity":1,"create_time":1733024376388,"temp_id":2202,"option":[[50,66],[77,66],[103,66],[30,0]]},{"quantity":1,"create_time":1733926738750,"temp_id":2197,"option":[[50,59],[77,44],[103,54]]},{"quantity":1,"create_time":1730992207664,"temp_id":2130,"option":[[50,25],[77,55],[103,55],[5,10],[30,0]]},{"quantity":1,"create_time":1729138218844,"temp_id":2075,"option":[[50,23],[21,40],[87,0],[237,5],[238,243],[30,0]]},{"quantity":1,"create_time":1733590097744,"temp_id":805,"option":[[169,55],[72,7]]},{"quantity":1,"create_time":1729132174737,"temp_id":1327,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1733155688710,"temp_id":2207,"option":[[50,60],[77,60],[103,60],[30,0]]}]' WHERE p.id = 1240;

SELECT * FROM item_template it WHERE id = 2135;

SELECT * FROM part p WHERE p.id = 454;

SELECT * FROM item_template it WHERE it.id = 644;
SELECT * FROM cai_trang ct WHERE ct.id_temp = 644;
SELECT p.skills, p.* FROM player p;


SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%cấp%';


UPDATE player p SET p.items_body = '[{"quantity":0,"create_time":1733107694851,"temp_id":1048,"option":[[47,6950],[249,0],[129,0],[141,150],[21,120],[198,10],[50,44],[102,11],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694863,"temp_id":1051,"option":[[6,173650],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694873,"temp_id":1054,"option":[[0,38075],[249,0],[129,0],[141,150],[21,120],[198,10],[50,44],[102,11],[72,8],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694883,"temp_id":1057,"option":[[7,164450],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":0,"create_time":1733107694892,"temp_id":1060,"option":[[14,23],[249,0],[129,0],[141,150],[21,120],[50,44],[102,11],[198,10],[30,0],[107,11]]},{"quantity":1,"create_time":1734860647809,"temp_id":547,"option":[[73,0],[174,0],[72,3]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1734618186184,"temp_id":2060,"option":[[73,0]]},{"quantity":1,"create_time":1733024376388,"temp_id":2202,"option":[[50,66],[77,66],[103,66],[30,0]]},{"quantity":1,"create_time":1733926738750,"temp_id":2197,"option":[[50,59],[77,44],[103,54]]},{"quantity":1,"create_time":1730992207664,"temp_id":2130,"option":[[50,25],[77,55],[103,55],[5,10],[30,0]]},{"quantity":1,"create_time":1729138218844,"temp_id":2075,"option":[[50,23],[21,40],[87,0],[237,5],[238,243],[30,0]]},{"quantity":1,"create_time":1733590097744,"temp_id":805,"option":[[169,55],[72,7]]},{"quantity":1,"create_time":1729132174737,"temp_id":1327,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1733155688710,"temp_id":2207,"option":[[50,60],[77,60],[103,60],[30,0]]}]' WHERE p.account_id = 3928;


SELECT * FROM item_template it ORDER BY it.id DESC;
SELECT * FROM map_template mt WHERE mt.NAME like '%nappa%';



UPDATE player p SET p.diem_skien = 0 WHERE 1 = 1;
  SELECT diem_skien, p.*  FROM player AS p ORDER BY diem_skien DESC;

  