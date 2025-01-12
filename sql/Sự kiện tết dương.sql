DELETE FROM item_shop WHERE ID = 9941;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9941, 50, 805, 0, 0, 1, 1, 1192, 2000, '2024-11-19 02:07:29');

DELETE FROM item_shop_option WHERE item_shop_id = 9941;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9941, 169, 10);

-- c?p nh?t tên item
UPDATE item_template it SET it.NAME = 'Ð? Black Goku', it.icon_id = 5141 WHERE it.id = 2206;





-- d?u th?n c?p 11
UPDATE item_template SET TYPE = 27, gender = 3, NAME = 'Ð?u th?n c?p 11', description = 'V?t ph?m s? ki?n, tác d?ng trong 30 phút tang 20% SÐ, HP, KI', icon_id = 29155, part = -1, is_up_to_up = 0, power_require = 0 WHERE id = 1944;



-- update map mabu 6h
UPDATE map_template mt SET mt.zones = 1, mt.max_player = 30 WHERE mt.id = 114;
UPDATE map_template mt SET mt.zones = 1, mt.max_player = 30 WHERE mt.id = 128;





-- test


INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2207, 99, 3, 'Danh hi?u Ð?i gia', 'Dùng d? tang d? giàu có', 11803, 1006, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2206, 27, 3, 'Ð? t? Goku', 'Ð? t? vip siêu c?p th? gi?i ng?c r?ng', 12273, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2205, 27, 3, 'Nang lu?ng Bill', 'V?t ph?m s? ki?n, tác d?ng trong 10 phút', 4847, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2204, 5, 3, 'C?i trang N? y tá cu?ng lo?n', 'C?i trang N? y tá cu?ng lo?n', 29212, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2203, 11, 3, 'Cánh Thiên Th?n', 'V?t ph?m s? ki?n', 29162, 86, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2202, 23, 3, 'Ván bay "Túi Vàng Th?n Tài"', 'V?t ph?m s? ki?n', 29155, 27, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2201, 21, 3, 'Pet Búp bê th?i ti?t', 'V?t ph?m s? ki?n', 29174, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2200, 27, 3, 'Ð? t? Whis', 'Yêu c?u: S? h?u d? Bill Level 7', 7679, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2199, 23, 3, 'Ván bay "R?ng thiên"', 'V?t ph?m s? ki?n', 29154, 46, 0, 0);









SELECT * FROM item_template it ORDER BY it.id DESC;

SELECT * FROM item_template it WHERE it.NAME LIKE '%th?i vàng%';

SELECT * FROM map_template mt WHERE mt.NAME LIKE '%vách núi%';

SELECT * FROM item_template it WHERE type = 23;

SELECT * FROM item_template it WHERE it.id IN (734, 2085, 1172, 897, 2133);

SELECT * FROM item_option_template iot WHERE iot.id = 72;

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%t?n công%';

SELECT * FROM map_template mt WHERE mt.id = 200;
SELECT * FROM map_template mt WHERE mt.NAME like '%bulon%';



SELECT * FROM item_option_template iot WHERE iot.id = 169;

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%s?c dánh chí m?ng%';

SELECT * FROM item_option_template iot WHERE iot.id = 72;

SELECT * FROM account a WHERE a.username = 'td';
SELECT * FROM player p WHERE p.account_id = 3928;
UPDATE player p SET p.items_bag = '[{"quantity":1,"create_time":1726634918760,"temp_id":194,"option":[[73,1]]},{"quantity":1,"create_time":1728912463212,"temp_id":521,"option":[[1,1147]]},{"quantity":1,"create_time":1727509935947,"temp_id":2085,"option":[[50,1],[101,300],[21,80]]},{"quantity":423,"create_time":1728912767329,"temp_id":1150,"option":[[73,0]]},{"quantity":998,"create_time":1729276661216,"temp_id":2088,"option":[[73,0]]},{"quantity":162,"create_time":1729737187558,"temp_id":2029,"option":[[73,0]]},{"quantity":11901,"create_time":1729872771623,"temp_id":674,"option":[[73,0]]},{"quantity":104,"create_time":1726992713893,"temp_id":1153,"option":[[73,0]]},{"quantity":366,"create_time":1730190842260,"temp_id":2170,"option":[[73,0]]},{"quantity":1,"create_time":1727695184327,"temp_id":1960,"option":[[40,18],[244,5]]},{"quantity":1,"create_time":1729102729735,"temp_id":2128,"option":[[50,50],[77,50],[103,50],[106,0],[116,0],[30,0]]},{"quantity":1,"create_time":1731549431967,"temp_id":2175,"option":[[50,30],[77,35],[103,30]]},{"quantity":1,"create_time":1727636036439,"temp_id":0,"option":[[47,2],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":6,"option":[[6,192],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":21,"option":[[0,10],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":27,"option":[[7,11],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":12,"option":[[14,1],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":156,"create_time":1730662693269,"temp_id":666,"option":[[73,0]]},{"quantity":1,"create_time":1727607197215,"temp_id":739,"option":[[50,55],[77,55],[103,55],[21,80],[101,1111]]},{"quantity":1,"create_time":1727446578891,"temp_id":730,"option":[[50,25],[77,1],[103,1],[14,1],[5,95],[102,6],[107,6]]},{"quantity":1,"create_time":1729524605771,"temp_id":448,"option":[[95,20],[96,20],[50,10],[101,30]]},{"quantity":92,"create_time":1731944337959,"temp_id":2180,"option":[[73,0]]},{"quantity":80,"create_time":1731944339000,"temp_id":2181,"option":[[73,0]]},{"quantity":12,"create_time":1731950210565,"temp_id":2188,"option":[[73,0]]},{"quantity":8,"create_time":1732030524126,"temp_id":2172,"option":[[73,0]]},{"quantity":11,"create_time":1732262490755,"temp_id":2189,"option":[[73,0]]},{"quantity":2,"create_time":1732202056427,"temp_id":2131,"option":[[0,1000],[50,40],[245,15]]},{"quantity":1,"create_time":1728912560837,"temp_id":536,"option":[[9,0],[95,10],[102,7],[96,25],[30,0],[107,7]]},{"quantity":11,"create_time":1732458071042,"temp_id":2191,"option":[[73,0]]},{"quantity":1,"create_time":1732403602614,"temp_id":1185,"option":[[50,39],[77,7],[103,5]]},{"quantity":3,"create_time":1732403604513,"temp_id":753,"option":[[73,0]]},{"quantity":1,"create_time":1728460423332,"temp_id":555,"option":[[47,250],[249,0],[248,0],[250,3],[30,0]]},{"quantity":1,"create_time":1728460644026,"temp_id":556,"option":[[22,50],[249,0],[128,0],[140,0],[30,0]]},{"quantity":1,"create_time":1728460657029,"temp_id":562,"option":[[0,5000],[249,0],[248,0],[250,3],[30,0]]},{"quantity":14,"create_time":1731950210565,"temp_id":2187,"option":[[73,0]]},{"quantity":1,"create_time":1731947020536,"temp_id":2195,"option":[[50,50],[77,50],[103,50]]},{"quantity":358,"create_time":1732466464483,"temp_id":1192,"option":[[73,0]]},{"quantity":1,"create_time":1729136242631,"temp_id":536,"option":[[9,0],[50,32],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1732586504716,"temp_id":564,"option":[[0,3829],[21,15],[30,0],[107,1]]},{"quantity":1,"create_time":1732619798355,"temp_id":898,"option":[[50,75],[77,75],[103,75],[101,3000],[42,25]]},{"quantity":1,"create_time":1732641288432,"temp_id":566,"option":[[0,4907],[21,15],[30,0],[107,6]]},{"quantity":1,"create_time":1732677804017,"temp_id":1277,"option":[[50,120],[77,115],[103,125],[101,3000],[42,22]]},{"quantity":1,"create_time":1732511227944,"temp_id":592,"option":[[47,400],[108,10]]},{"quantity":1,"create_time":1732704205889,"temp_id":567,"option":[[7,44335],[21,15],[30,0],[107,6]]},{"quantity":1,"create_time":1732728398995,"temp_id":564,"option":[[0,3705],[21,15],[30,0],[107,4]]},{"quantity":57,"create_time":1732763023901,"temp_id":595,"option":[[2,256]]},{"quantity":1,"create_time":1730992207650,"temp_id":1172,"option":[[50,40],[77,40],[103,40],[30,0]]},{"quantity":1,"create_time":1732805839501,"temp_id":1989,"option":[[30,0]]},{"quantity":4,"create_time":1732863644391,"temp_id":2182,"option":[[73,0]]},{"quantity":1,"create_time":1732863658366,"temp_id":2174,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1732863950578,"temp_id":561,"option":[[14,16],[21,15],[107,3]]},{"quantity":1,"create_time":1732864437095,"temp_id":559,"option":[[47,1259],[21,15],[107,0]]},{"quantity":1,"create_time":1732864543244,"temp_id":2048,"option":[[73,0]]},{"quantity":12,"create_time":1732878336821,"temp_id":2013,"option":[[30,0]]},{"quantity":99,"create_time":1732989163899,"temp_id":2201,"option":[[73,0]]},{"quantity":1,"create_time":1729101576441,"temp_id":2097,"option":[[43,20],[50,30],[77,30],[103,30],[30,0]]},{"quantity":1,"create_time":1730270665120,"temp_id":1148,"option":[[50,30],[77,30],[103,30],[244,5]]},{"quantity":90,"create_time":1733153233429,"temp_id":2179,"option":[[73,0]]},{"quantity":1,"create_time":1733152449794,"temp_id":2199,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2186,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2190,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2192,"option":[[73,0]]},{"quantity":1,"create_time":1732791851003,"temp_id":2085,"option":[[50,35],[77,35],[103,35]]},{"quantity":93,"create_time":1733234151760,"temp_id":14,"option":[[73,0]]},{"quantity":93,"create_time":1733234156022,"temp_id":15,"option":[[73,0]]},{"quantity":93,"create_time":1733234158872,"temp_id":16,"option":[[73,0]]},{"quantity":93,"create_time":1733234162864,"temp_id":17,"option":[[73,0]]},{"quantity":93,"create_time":1733234167360,"temp_id":18,"option":[[73,0]]},{"quantity":93,"create_time":1733234171206,"temp_id":19,"option":[[73,0]]},{"quantity":93,"create_time":1733234173899,"temp_id":20,"option":[[73,0]]},{"quantity":1,"create_time":1733238113932,"temp_id":2200,"option":[[73,0]]},{"quantity":-7,"create_time":1731950295695,"temp_id":805,"option":[[169,15]]},{"quantity":399999,"create_time":1733327818963,"temp_id":1288,"option":[[73,0]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' WHERE p.id = 1240;


SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%cây nêu%';
SELECT * FROM shop WHERE npc_id = 84;
SELECT * FROM tab_shop ts WHERE ts.shop_id = 28;
SELECT * FROM item_shop `is` WHERE `is`.tab_id = 50;


SELECT * FROM item_shop `is` ORDER BY `is`.id DESC;
SELECT * FROM item_shop_option iso ORDER BY iso.item_shop_id DESC;

SELECT * FROM item_template it WHERE it.id IN (1192, 2172, 1192);

SELECT * FROM item_template it ORDER BY it.id DESC;


SELECT * FROM item_template it WHERE it.NAME like '%black%';

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%Ch? s? kích ho?t ?n tang 15% SÐ, HP, KI%';

SELECT * FROM item_template it WHERE it.id = 557;

SELECT * FROM item_template it WHERE it.NAME LIKE '%black goku%';

SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%s?c dánh chí m?ng%';

SELECT * FROM part p WHERE p.id IN (550, 551, 552);

  SELECT * FROM part p WHERE p.id IN (1326, 1327, 1328);

SELECT * FROM part p WHERE p.part LIKE '%29059%';
SELECT * FROM part p WHERE p.id IN (1477, 1478, 1479);

SELECT * FROM item_template it ORDER BY it.id DESC;

SELECT * FROM map_template mt WHERE mt.id IN (114, 128);



USE pico;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2211, 99, 3, 'Danh hi?u Ð? t?', 'Danh hi?u Vip m?i có du?c', 11803, 2204, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2210, 27, 3, 'Túi Quà C?i trang Goku', 'Gi?u bên trong nhi?u v?t ph?m quý giá', 25015, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2209, 5, 3, 'C?i trang Super Black Goku', 'C?i trang thành Super Black Goku', 29254, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2208, 5, 3, 'C?i trang Black Goku', 'C?i trang thành Black Goku', 29252, -1, 0, 0);


DELETE FROM item_template WHERE id > 2207;



USE pico;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2211, 99, 3, 'Danh hi?u Ð? t?', 'Danh hi?u Vip m?i có du?c', 11803, 2204, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2210, 27, 3, 'Túi Quà C?i trang Goku', 'Gi?u bên trong nhi?u v?t ph?m quý giá', 25015, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2209, 5, 3, 'C?i trang Super Black Goku', 'C?i trang thành Super Black Goku', 29254, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2208, 5, 3, 'C?i trang Black Goku', 'C?i trang thành Black Goku', 29252, -1, 0, 0);



SELECT * FROM part p ORDER BY p.id desc;


USE pico;

INSERT INTO part (id, type, part) VALUES
(1502, 2, '[{"icon":29238,"dx":5,"dy":1},{"icon":29239,"dx":-1,"dy":-4},{"icon":29240,"dx":-1,"dy":-4},{"icon":29241,"dx":-2,"dy":-4},{"icon":29242,"dx":-1,"dy":-6},{"icon":29243,"dx":0,"dy":-6},{"icon":29244,"dx":-1,"dy":-5},{"icon":29245,"dx":-3,"dy":-3},{"icon":29246,"dx":1,"dy":-2},{"icon":29247,"dx":-3,"dy":-7},{"icon":29248,"dx":4,"dy":-7},{"icon":29249,"dx":-2,"dy":-1},{"icon":29250,"dx":-5,"dy":-7},{"icon":29213,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1501, 1, '[{"icon":29222,"dx":0,"dy":-8},{"icon":29223,"dx":-2,"dy":-10},{"icon":29224,"dx":-3,"dy":-10},{"icon":29225,"dx":-1,"dy":-12},{"icon":29226,"dx":-1,"dy":-10},{"icon":29227,"dx":2,"dy":-10},{"icon":29228,"dx":1,"dy":-10},{"icon":29229,"dx":-20,"dy":-15},{"icon":29230,"dx":0,"dy":-11},{"icon":29231,"dx":-4,"dy":-22},{"icon":29232,"dx":-8,"dy":-18},{"icon":29233,"dx":-3,"dy":-14},{"icon":29234,"dx":0,"dy":-13},{"icon":29235,"dx":0,"dy":-12},{"icon":29236,"dx":-3,"dy":-10},{"icon":29237,"dx":-2,"dy":-10},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1500, 0, '[{"icon":29214,"dx":-2,"dy":-14},{"icon":29215,"dx":-1,"dy":-15},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1499, 0, '[{"icon":29218,"dx":-1,"dy":-21},{"icon":29221,"dx":-1,"dy":-21},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1498, 0, '[{"icon":29217,"dx":-1,"dy":-22},{"icon":29220,"dx":-1,"dy":-23},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1497, 0, '[{"icon":29216,"dx":-1,"dy":-21},{"icon":29219,"dx":-1,"dy":-22},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1496, 2, '[{"icon":29198,"dx":5,"dy":3},{"icon":29199,"dx":-1,"dy":-5},{"icon":29200,"dx":2,"dy":-2},{"icon":29201,"dx":4,"dy":-2},{"icon":29202,"dx":3,"dy":-3},{"icon":29203,"dx":4,"dy":-2},{"icon":29204,"dx":5,"dy":0},{"icon":29205,"dx":2,"dy":0},{"icon":29206,"dx":1,"dy":-4},{"icon":29207,"dx":1,"dy":-5},{"icon":29208,"dx":4,"dy":-2},{"icon":29209,"dx":2,"dy":2},{"icon":29210,"dx":0,"dy":-2},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1495, 1, '[{"icon":29182,"dx":-4,"dy":-5},{"icon":29183,"dx":-3,"dy":-9},{"icon":29184,"dx":-4,"dy":-8},{"icon":29185,"dx":-2,"dy":-9},{"icon":29186,"dx":-1,"dy":-8},{"icon":29187,"dx":0,"dy":-7},{"icon":29188,"dx":0,"dy":-7},{"icon":29189,"dx":1,"dy":-7},{"icon":29190,"dx":1,"dy":-3},{"icon":29191,"dx":-2,"dy":-4},{"icon":29192,"dx":-4,"dy":-8},{"icon":29193,"dx":-4,"dy":-6},{"icon":29194,"dx":1,"dy":-5},{"icon":29195,"dx":3,"dy":-12},{"icon":29196,"dx":-6,"dy":-8},{"icon":29197,"dx":-6,"dy":-8},{"icon":29175,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1494, 0, '[{"icon":29178,"dx":-3,"dy":-7},{"icon":29181,"dx":-6,"dy":-6},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1493, 0, '[{"icon":29177,"dx":-2,"dy":-10},{"icon":29180,"dx":-9,"dy":-9},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1492, 0, '[{"icon":29176,"dx":-2,"dy":-8},{"icon":29179,"dx":-6,"dy":-7},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1491, 2, '[{"icon":29173,"dx":10,"dy":-13},{"icon":2955,"dx":0,"dy":0},{"icon":29167,"dx":-22,"dy":-29},{"icon":29168,"dx":-19,"dy":-31},{"icon":29169,"dx":-23,"dy":-29},{"icon":29170,"dx":-19,"dy":-28},{"icon":29171,"dx":-21,"dy":-29},{"icon":29164,"dx":-6,"dy":-20},{"icon":29164,"dx":-9,"dy":-23},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1490, 1, '[{"icon":29163,"dx":-2,"dy":-16},{"icon":29164,"dx":-5,"dy":-25},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]');

INSERT INTO part (id, type, part) VALUES
(1489, 0, '[{"icon":29172,"dx":0,"dy":22},{"icon":2955,"dx":0,"dy":0},{"icon":2955,"dx":0,"dy":0}]');




SELECT * FROM account a WHERE a.username = 'td';
SELECT * FROM player p WHERE p.account_id = 3928;

SELECT * FROM item_template it WHERE it.NAME like '%c?p 11%'

UPDATE player p SET p.items_bag = '[{"quantity":1,"create_time":1726634918760,"temp_id":194,"option":[[73,1]]},{"quantity":1,"create_time":1728912463212,"temp_id":521,"option":[[1,1147]]},{"quantity":1,"create_time":1727509935947,"temp_id":2085,"option":[[50,1],[101,300],[21,80]]},{"quantity":423,"create_time":1728912767329,"temp_id":1150,"option":[[73,0]]},{"quantity":998,"create_time":1729276661216,"temp_id":2088,"option":[[73,0]]},{"quantity":162,"create_time":1729737187558,"temp_id":2029,"option":[[73,0]]},{"quantity":11901,"create_time":1729872771623,"temp_id":674,"option":[[73,0]]},{"quantity":104,"create_time":1726992713893,"temp_id":1153,"option":[[73,0]]},{"quantity":366,"create_time":1730190842260,"temp_id":2170,"option":[[73,0]]},{"quantity":1,"create_time":1727695184327,"temp_id":1960,"option":[[40,18],[244,5]]},{"quantity":1,"create_time":1729102729735,"temp_id":2128,"option":[[50,50],[77,50],[103,50],[106,0],[116,0],[30,0]]},{"quantity":1,"create_time":1731549431967,"temp_id":2175,"option":[[50,30],[77,35],[103,30]]},{"quantity":1,"create_time":1727636036439,"temp_id":0,"option":[[47,2],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":6,"option":[[6,192],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":21,"option":[[0,10],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":27,"option":[[7,11],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":12,"option":[[14,1],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":156,"create_time":1730662693269,"temp_id":666,"option":[[73,0]]},{"quantity":1,"create_time":1727607197215,"temp_id":739,"option":[[50,55],[77,55],[103,55],[21,80],[101,1111]]},{"quantity":1,"create_time":1727446578891,"temp_id":730,"option":[[50,25],[77,1],[103,1],[14,1],[5,95],[102,6],[107,6]]},{"quantity":1,"create_time":1729524605771,"temp_id":448,"option":[[95,20],[96,20],[50,10],[101,30]]},{"quantity":92,"create_time":1731944337959,"temp_id":2180,"option":[[73,0]]},{"quantity":80,"create_time":1731944339000,"temp_id":2181,"option":[[73,0]]},{"quantity":12,"create_time":1731950210565,"temp_id":2188,"option":[[73,0]]},{"quantity":8,"create_time":1732030524126,"temp_id":2172,"option":[[73,0]]},{"quantity":11,"create_time":1732262490755,"temp_id":2189,"option":[[73,0]]},{"quantity":2,"create_time":1732202056427,"temp_id":2131,"option":[[0,1000],[50,40],[245,15]]},{"quantity":1,"create_time":1728912560837,"temp_id":536,"option":[[9,0],[95,10],[102,7],[96,25],[30,0],[107,7]]},{"quantity":11,"create_time":1732458071042,"temp_id":2191,"option":[[73,0]]},{"quantity":1,"create_time":1732403602614,"temp_id":1185,"option":[[50,39],[77,7],[103,5]]},{"quantity":3,"create_time":1732403604513,"temp_id":753,"option":[[73,0]]},{"quantity":1,"create_time":1728460423332,"temp_id":555,"option":[[47,250],[249,0],[248,0],[250,3],[30,0]]},{"quantity":1,"create_time":1728460644026,"temp_id":556,"option":[[22,50],[249,0],[128,0],[140,0],[30,0]]},{"quantity":1,"create_time":1728460657029,"temp_id":562,"option":[[0,5000],[249,0],[248,0],[250,3],[30,0]]},{"quantity":14,"create_time":1731950210565,"temp_id":2187,"option":[[73,0]]},{"quantity":1,"create_time":1731947020536,"temp_id":2195,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1729136242631,"temp_id":536,"option":[[9,0],[50,32],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1732586504716,"temp_id":564,"option":[[0,3829],[21,15],[30,0],[107,1]]},{"quantity":1,"create_time":1732619798355,"temp_id":898,"option":[[50,75],[77,75],[103,75],[101,3000],[42,25]]},{"quantity":1,"create_time":1732641288432,"temp_id":566,"option":[[0,4907],[21,15],[30,0],[107,6]]},{"quantity":1,"create_time":1732677804017,"temp_id":1277,"option":[[50,120],[77,115],[103,125],[101,3000],[42,22]]},{"quantity":1,"create_time":1732511227944,"temp_id":592,"option":[[47,400],[108,10]]},{"quantity":1,"create_time":1732704205889,"temp_id":567,"option":[[7,44335],[21,15],[30,0],[107,6]]},{"quantity":1,"create_time":1732728398995,"temp_id":564,"option":[[0,3705],[21,15],[30,0],[107,4]]},{"quantity":57,"create_time":1732763023901,"temp_id":595,"option":[[2,256]]},{"quantity":1,"create_time":1730992207650,"temp_id":1172,"option":[[50,40],[77,40],[103,40],[30,0]]},{"quantity":1,"create_time":1732805839501,"temp_id":1989,"option":[[30,0]]},{"quantity":4,"create_time":1732863644391,"temp_id":2182,"option":[[73,0]]},{"quantity":1,"create_time":1732863658366,"temp_id":2174,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1732863950578,"temp_id":561,"option":[[14,16],[21,15],[107,3]]},{"quantity":1,"create_time":1732864437095,"temp_id":559,"option":[[47,1259],[21,15],[107,0]]},{"quantity":1,"create_time":1732864543244,"temp_id":2048,"option":[[73,0]]},{"quantity":15,"create_time":1732878336821,"temp_id":2013,"option":[[30,0]]},{"quantity":99,"create_time":1732989163899,"temp_id":2201,"option":[[73,0]]},{"quantity":1,"create_time":1729101576441,"temp_id":2097,"option":[[43,20],[50,30],[77,30],[103,30],[30,0]]},{"quantity":1,"create_time":1730270665120,"temp_id":1148,"option":[[50,30],[77,30],[103,30],[244,5]]},{"quantity":90,"create_time":1733153233429,"temp_id":2179,"option":[[73,0]]},{"quantity":1,"create_time":1733152449794,"temp_id":2199,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2186,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2190,"option":[[73,0]]},{"quantity":2,"create_time":1733153683309,"temp_id":2192,"option":[[73,0]]},{"quantity":1,"create_time":1732791851003,"temp_id":2085,"option":[[50,35],[77,35],[103,35]]},{"quantity":93,"create_time":1733234151760,"temp_id":14,"option":[[73,0]]},{"quantity":93,"create_time":1733234156022,"temp_id":15,"option":[[73,0]]},{"quantity":93,"create_time":1733234158872,"temp_id":16,"option":[[73,0]]},{"quantity":93,"create_time":1733234162864,"temp_id":17,"option":[[73,0]]},{"quantity":93,"create_time":1733234167360,"temp_id":18,"option":[[73,0]]},{"quantity":93,"create_time":1733234171206,"temp_id":19,"option":[[73,0]]},{"quantity":93,"create_time":1733234173899,"temp_id":20,"option":[[73,0]]},{"quantity":1,"create_time":1733238113932,"temp_id":2200,"option":[[73,0]]},{"quantity":1,"create_time":1733330267297,"temp_id":805,"option":[[169,10]]},{"quantity":949999,"create_time":1733329755648,"temp_id":1288,"option":[[73,0]]},{"quantity":997999,"create_time":1733330261173,"temp_id":1192,"option":[[73,0]]},{"quantity":1,"create_time":1733541048622,"temp_id":2118,"option":[[77,8]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' WHERE p.id = 1240;

SELECT * FROM item_template it ORDER BY it.id DESC;
