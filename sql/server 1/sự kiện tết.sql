-- danh hiệu memori
DELETE FROM item_template WHERE ID = 2185;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2185, 99, 3, 'Danh hiệu Memori', 'Dùng để tăng độ đẹp zai, gây sát thương lên lân con', 11803, 646, 0, 0);

-- Kẻ Hủy Diệt Loài Hoa (x2 sát thương lên boss Hoa Hồng) ->  Sức đánh ma thuật tăng 10%
UPDATE item_option_template it SET it.NAME = 'Sức đánh ma thuật tăng #%' WHERE it.id = 198;
-- Biến mọi người xung quanh thành kẹo mỗi 30 giây- > Hp ma thuật tăng 15%
UPDATE item_option_template it SET it.NAME = 'HP ma thuật tăng #%' WHERE it.id = 186;
-- Biến người xung quanh thành Bí Ngô ->  Ki ma thuật tăng 15%
UPDATE item_option_template it SET it.NAME = 'KI ma thuật tăng #%' WHERE it.id = 163;


-- bùa hp, ki, sd
DELETE FROM item_template WHERE ID = 2180;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2180, 27, 3, 'Bùa pháp sư HP', 'Dùng để tăng chỉ số HP', 1407, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2181;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2181, 27, 3, 'Bùa pháp sư KI', 'Dùng để tăng chỉ số KI', 1408, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2182;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2182, 27, 3, 'Bùa pháp sư Sức đánh', 'Dùng để tăng chỉ số sức đánh', 1409, -1, 0, 0);


-- Ngọc hắc ám
DELETE FROM item_template WHERE ID = 2192;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2192, 12, 3, 'Ngọc rồng hắc ám 7 sao', 'Thu thập để ước Rồng Hắc Ám', 29099, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2191;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2191, 12, 3, 'Ngọc rồng hắc ám 6 sao', 'Thu thập để ước Rồng Hắc Ám', 29098, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2190;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2190, 12, 3, 'Ngọc rồng hắc ám 5 sao', 'Thu thập để ước Rồng Hắc Ám', 29097, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2189;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2189, 12, 3, 'Ngọc rồng hắc ám 4 sao', 'Thu thập để ước Rồng Hắc Ám', 29096, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2188;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2188, 12, 3, 'Ngọc rồng hắc ám 3 sao', 'Thu thập để ước Rồng Hắc Ám', 29095, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2187;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2187, 12, 3, 'Ngọc rồng hắc ám 2 sao', 'Thu thập để ước Rồng Hắc Ám', 29094, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2186;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2186, 12, 3, 'Ngọc rồng hắc ám 1 sao', 'Thu thập để ước Rồng Hắc Ám', 29093, -1, 0, 0);




-- Cơ hội hạ độc đối thủ -> Đẹp trai tăng #% Sức đánh, HP, KI
UPDATE item_option_template it SET it.NAME = 'Đẹp trai tăng #% Sức đánh, HP, KI' WHERE it.id = 169;

-- vòng thiên sứ
UPDATE item_template it SET it.NAME = 'Đá Astone' WHERE it.id = 1956;
UPDATE item_template it SET it.NAME = 'Vòng thiên sứ', it.icon_id = 29092 WHERE it.id = 805;



-- rương ngọc đen
DELETE FROM item_template WHERE ID = 2179;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2179, 27, 3, 'Rương Ngọc Hắc Ám', 'Mở rương này để nhận 1 bộ Ngọc Rồng Hắc Ám.', 5007, -1, 0, 0);




-- Ngọc rồng đen 1 sao, 2 sao chỉnh còn 3 khu
UPDATE map_template mt SET mt.zones = 3 WHERE mt.id = 85;
UPDATE map_template mt SET mt.zones = 3 WHERE mt.id = 86;



-- Đổi bằng # điểm sự kiện -> Tăng 10% mọi chỉ số nếu sỡ hữu đệ Zeno
UPDATE item_option_template iot SET iot.NAME = 'Tăng #% mọi chỉ số khi sỡ hữu đệ Zeno' WHERE iot.id = 200;










-- test
SELECT * FROM item_option_template it WHERE id IN (198, 186, 163, 169);

SELECT * FROM item_template it WHERE it.id IN (1956, 805);
SELECT * FROM item_template it WHERE it.id = 1956;
SELECT * FROM item_template it ORDER BY it.id DESC;
SELECT * FROM item_template it WHERE it.NAME like '%Vòng%';
SELECT * FROM item_option_template iot ORDER BY iot.id DESC;

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%sở hữu%';
SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%KI%';

SELECT * FROM player p WHERE p.name like '%ekko%';


SELECT * FROM item_template it ORDER BY it.id desc;


SELECT * FROM item_template it WHERE it.TYPE IN (0, 1, 2, 3, 4) AND it.gender = 0;

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%loài hoa%';

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%hp%';

SELECT * FROM item_template it WHERE it.NAME LIKE '%stone%';
SELECT * FROM item_template it WHERE it.NAME LIKE '%cờ%';

SELECT * FROM map_template mt WHERE mt.NAME LIKE '%vách núi%';

SELECT * FROM item_template it WHERE it.id >= 14 AND it.id <= 20;

SELECT * FROM item_template it WHERE it.NAME LIKE '%hắc ám%';
SELECT * FROM item_template it WHERE it.id = 926;

SELECT * FROM item_option_template iot WHERE iot.id = 93;

SELECT * FROM part p WHERE p.data LIKE '%7072%';
SELECT * FROM part p WHERE id IN (763, 764, 765);
SELECT * FROM part p WHERE id IN (545, 548, 549);


SELECT * FROM item_template it WHERE it.NAME LIKE '%lân%';


SELECT * FROM item_template it WHERE it.id >= 1000;


SELECT a.pointNap, a.* FROM account a WHERE a.username LIKE 'td';
SELECT a.pointNap, a.* FROM account a WHERE a.username LIKE 'k';
SELECT * FROM player p WHERE p.account_id = 3928;

UPDATE player p SET p.items_body = '[{"quantity":1,"create_time":1727621377876,"temp_id":0,"option":[[47,3],[129,0],[141,100],[249,15],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1727620439905,"temp_id":6,"option":[[6,50],[129,0],[141,100],[249,15],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1727621370654,"temp_id":21,"option":[[0,7],[129,0],[141,100],[249,15],[50,21],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727621378632,"temp_id":27,"option":[[7,24],[129,0],[141,100],[249,15],[50,21],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727621378264,"temp_id":12,"option":[[14,1],[129,0],[141,100],[249,15],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1727013684997,"temp_id":730,"option":[[50,1],[77,1],[103,1],[14,1],[5,93]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1726489111867,"temp_id":1197,"option":[[50,22],[77,22],[103,22]]},{"quantity":1,"create_time":1726831348861,"temp_id":2127,"option":[[50,15],[77,15],[103,15],[101,100],[21,1]]},{"quantity":1,"create_time":1727697359004,"temp_id":1960,"option":[[40,18],[244,5]]},{"quantity":1,"create_time":1726486309337,"temp_id":2006,"option":[[50,22],[77,22],[103,22]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' AND p.items_bag = '[{"quantity":1,"create_time":1726484547218,"temp_id":921,"option":[[72,2],[50,22]]},{"quantity":1,"create_time":1726484547218,"temp_id":194,"option":[[73,1]]},{"quantity":114,"create_time":1726503016181,"temp_id":19,"option":[[73,0]]},{"quantity":129,"create_time":1726503120453,"temp_id":20,"option":[[73,0]]},{"quantity":48,"create_time":1726845775460,"temp_id":595,"option":[[2,256]]},{"quantity":16,"create_time":1726845785517,"temp_id":595,"option":[[2,256]]},{"quantity":1,"create_time":1726970625796,"temp_id":578,"option":[[50,35],[77,35],[103,35],[29,0]]},{"quantity":1,"create_time":1726845254451,"temp_id":449,"option":[[105,0],[160,30]]},{"quantity":1,"create_time":1726932295329,"temp_id":448,"option":[[95,20],[96,20],[50,10],[101,30]]},{"quantity":1,"create_time":1726970400775,"temp_id":0,"option":[[47,4],[128,0],[140,0],[50,18],[102,6],[30,0],[107,6]]},{"quantity":1,"create_time":1726970400775,"temp_id":6,"option":[[6,170],[128,0],[140,0],[50,18],[102,6],[30,0],[107,6]]},{"quantity":1,"create_time":1726970400775,"temp_id":21,"option":[[0,26],[128,0],[140,0],[50,18],[102,6],[30,0],[107,6]]},{"quantity":1,"create_time":1726970400775,"temp_id":27,"option":[[7,8],[128,0],[140,0],[50,18],[102,6],[30,0],[107,6]]},{"quantity":1,"create_time":1726970400775,"temp_id":12,"option":[[14,1],[128,0],[140,0],[50,18],[102,6],[30,0],[107,6]]},{"quantity":1,"create_time":1726659774197,"temp_id":448,"option":[[95,20],[96,20],[50,10],[101,30]]},{"quantity":1,"create_time":1726513491877,"temp_id":2083,"option":[[50,10],[77,49],[103,55],[14,15],[5,65]]},{"quantity":97,"create_time":1726513419713,"temp_id":443,"option":[[97,5]]},{"quantity":46,"create_time":1726591483817,"temp_id":1151,"option":[[73,0]]},{"quantity":8,"create_time":1727596173652,"temp_id":444,"option":[[73,5]]},{"quantity":11,"create_time":1727596173652,"temp_id":223,"option":[[73,0]]},{"quantity":7,"create_time":1727596173652,"temp_id":447,"option":[[101,5]]},{"quantity":17,"create_time":1727596174144,"temp_id":1153,"option":[[73,0]]},{"quantity":8,"create_time":1727596174144,"temp_id":1152,"option":[[73,0]]},{"quantity":7,"create_time":1727596174645,"temp_id":2051,"option":[[73,0]]},{"quantity":19,"create_time":1727596174645,"temp_id":2045,"option":[[73,0]]},{"quantity":4,"create_time":1727596174645,"temp_id":442,"option":[[96,5]]},{"quantity":7,"create_time":1727596174645,"temp_id":224,"option":[[73,0]]},{"quantity":156,"create_time":1727596175127,"temp_id":221,"option":[[73,0]]},{"quantity":17,"create_time":1727596175634,"temp_id":220,"option":[[73,0]]},{"quantity":5,"create_time":1727596176149,"temp_id":222,"option":[[73,0]]},{"quantity":1,"create_time":1727737566403,"temp_id":807,"option":[[73,0]]},{"quantity":1,"create_time":1726491661974,"temp_id":463,"option":[[50,1],[77,50],[103,45],[5,50]]},{"quantity":9999803,"create_time":1726510148299,"temp_id":933,"option":[[30,0]]},{"quantity":34,"create_time":1726634720955,"temp_id":379,"option":[[73,0]]},{"quantity":1,"create_time":1727621373354,"temp_id":12,"option":[[14,1],[129,0],[141,100],[249,15],[30,0],[107,7]]},{"quantity":21,"create_time":1726659629333,"temp_id":2049,"option":[[73,0]]},{"quantity":11,"create_time":1726831145661,"temp_id":2046,"option":[[73,0]]},{"quantity":16,"create_time":1726831145954,"temp_id":2047,"option":[[73,0]]},{"quantity":10,"create_time":1726831149550,"temp_id":2048,"option":[[73,0]]},{"quantity":4,"create_time":1727651531972,"temp_id":2050,"option":[[73,0]]},{"quantity":5,"create_time":1727651532514,"temp_id":1150,"option":[[73,0]]},{"quantity":3,"create_time":1727651535519,"temp_id":16,"option":[[73,0]]},{"quantity":2,"create_time":1727651536004,"temp_id":17,"option":[[73,0]]},{"quantity":2,"create_time":1727651552811,"temp_id":18,"option":[[73,0]]},{"quantity":1,"create_time":1726510765847,"temp_id":2154,"option":[[50,15],[77,15],[103,15]]},{"quantity":1,"create_time":1727737566403,"temp_id":2119,"option":[[103,8],[30,0]]},{"quantity":1,"create_time":1726485191244,"temp_id":531,"option":[[9,2],[101,70],[102,7],[30,0],[107,7]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' WHERE p.account_id = 3928;

SELECT * FROM player p;


SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%omega%';

SELECT * FROM map_template mt WHERE mt.npcs LIKE '%29%';

SELECT * FROM account a WHERE a.id = 2613;

SELECT * FROM map_template mt WHERE mt.NAME LIKE '%trạm tàu vũ trụ%';


SELECT * FROM map_template mt WHERE mt.id = 86;


SELECT * FROM item_template it WHERE it.id > 370;


SELECT * FROM item_template it ORDER BY it.id DESC;


SELECT * FROM item_template it WHERE it.id = 805;



SELECT * FROM player p WHERE p.name LIKE '%Trùm%';

SELECT * FROM account a WHERE a.id = 3928;
SELECT * FROM player p WHERE p.account_id = 3928;

UPDATE player p SET p.items_body = '[{"quantity":1,"create_time":1731751301975,"temp_id":0,"option":[[73,0],[200,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":651,"option":[[6,175382],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":657,"option":[[0,19289],[249,0],[129,0],[141,150],[50,40],[102,10],[72,8],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":658,"option":[[7,194871],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":656,"option":[[14,23],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1729524605771,"temp_id":448,"option":[[95,20],[96,20],[50,10],[101,30]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1728776636181,"temp_id":2157,"option":[[77,35],[103,35],[50,35],[44,5]]},{"quantity":1,"create_time":1730270665120,"temp_id":1148,"option":[[50,30],[77,30],[103,30],[244,5]]},{"quantity":1,"create_time":1729102545892,"temp_id":1107,"option":[[44,10],[50,50],[77,50],[103,50],[30,0]]},{"quantity":1,"create_time":1730992207664,"temp_id":2130,"option":[[50,25],[77,55],[103,55],[5,10],[30,0]]},{"quantity":1,"create_time":1729138218844,"temp_id":2075,"option":[[50,23],[21,40],[87,0],[237,5],[238,461],[30,0]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1729132174737,"temp_id":1327,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1731745233490,"temp_id":2185,"option":[[73,0]]}]' WHERE p.account_id = 3928;


SELECT * FROM item_template it WHERE it.NAME LIKE '%vòng sáng thiên thần%';
SELECT * FROM item_template it WHERE name LIKE '%cấp 3%';

SELECT * FROM item_option_template iot ORDER BY id DESC;



UPDATE item_template SET id = 2185, TYPE = 99, gender = 3, NAME = 'Danh hiệu Memori', description = 'Dùng để tăng độ đẹp zai, gây sát thương lên lân con', icon_id = 11803, part = 2203, is_up_to_up = 0, power_require = 0 WHERE id = 2185;
