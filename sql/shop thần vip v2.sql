-- thêm shop cho bill
DELETE FROM shop WHERE ID = 29;
INSERT INTO shop(id, npc_id, shop_order) VALUES(29, 55, 1);


-- thêm tab shop hủy diệt VIP
DELETE FROM tab_shop WHERE id = 51;
INSERT INTO tab_shop(id, shop_id, NAME) VALUES(51, 29, 'Shop Hủy<>Diệt VIP');


-- thêm item cho shop hủy diệt vip
DELETE FROM item_shop WHERE ID = 9924;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9924, 51, 650, 0, 0, 0, 1, 861, 1000000, '2024-10-08 02:40:05');

DELETE FROM item_shop WHERE ID = 9925;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9925, 51, 651, 0, 0, 0, 1, 861, 1000000, '2024-10-08 02:41:02');

DELETE FROM item_shop WHERE ID = 9926;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9926, 51, 657, 0, 0, 0, 1, 861, 1000000, '2024-10-08 02:41:36');

DELETE FROM item_shop WHERE ID = 9927;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9927, 51, 658, 0, 0, 0, 1, 861, 1000000, '2024-10-08 02:42:10');

DELETE FROM item_shop WHERE ID = 9928;
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9928, 51, 656, 0, 0, 0, 1, 861, 1000000, '2024-10-08 02:42:50');


-- thêm option cho item
DELETE FROM item_shop_option WHERE item_shop_id = 9924;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9924, 47, 1800);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9924, 30, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9924, 249, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9924, 21, 110);

DELETE FROM item_shop_option WHERE item_shop_id = 9925;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9925, 22, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9925, 30, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9925, 249, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9925, 21, 110);

DELETE FROM item_shop_option WHERE item_shop_id = 9926;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9926, 0, 9000);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9926, 30, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9926, 249, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9926, 21, 110);

DELETE FROM item_shop_option WHERE item_shop_id = 9927;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9927, 23, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9927, 30, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9927, 249, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9927, 21, 110);

DELETE FROM item_shop_option WHERE item_shop_id = 9928;
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9928, 14, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9928, 30, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9928, 249, 0);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9928, 21, 110);




-- update vòng thiên sứ
UPDATE item_template it SET it.TYPE = 76, part = 58 WHERE id = 805;







-- test

SELECT * FROM item_option_template iot WHERE iot.id IN (47, 22, 0, 23, 14);

SELECT * FROM item_option_template iot WHERE iot.NAME like '%yêu cầu%';




SELECT * FROM item_template ORDER BY id DESC;

SELECT * FROM item_template WHERE NAME like '%hồng ngọc%';

USE pico;
SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%bunma%';
SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%lý tiểu nương%';
SELECT * FROM shop s WHERE s.npc_id = 7;
SELECT * FROM shop s ORDER BY s.id DESC;
SELECT * FROM tab_shop ts WHERE ts.shop_id IN (14, 27, 1);
SELECT * FROM item_shop `is` WHERE `is`.tab_id = 47;


SELECT * FROM tab_shop ts ORDER BY ts.id DESC;
SELECT * FROM item_shop `is` ORDER BY `is`.id DESC;

SELECT * FROM item_shop_option iso WHERE iso.item_shop_id IN (9902, 9903, 9904, 9905, 9906);

SELECT * FROM item_shop_option iso ORDER BY iso.item_shop_id DESC;
SELECT * FROM item_template it WHERE it.NAME LIKE '%đá ngũ sắc%';

SELECT * FROM map_template mt WHERE mt.npcs like '%7%';

SELECT * FROM shop s WHERE s.npc_id = 54;


SELECT * FROM item_option_template iot WHERE iot.id IN (129, 141)



DELETE FROM shop WHERE ID = 28;
INSERT INTO shop (id, npc_id, shop_order) VALUES
(28, 84, 0);



DELETE FROM tab_shop WHERE ID = 50;
INSERT INTO tab_shop (id, shop_id, NAME) VALUES
(50, 28, 'Cửa hàng<>Tết');


SELECT * FROM npc_template nt WHERE nt.NAME LIKE '%bill%';
SELECT * FROM shop WHERE npc_id = 55;
SELECT * FROM map_template mt WHERE name LIKE '%kaio%';
SELECT * FROM item_template it WHERE it.name LIKE '%hủy diệt%' AND (it.gender = 0 OR it.gender = 3) AND it.TYPE >= 0 AND Type <= 4;


SELECT * FROM item_template it WHERE it.name LIKE '%hủy diệt%' AND it.TYPE = 3;


SELECT * FROM player p WHERE p.gender = 2;
SELECT * FROM account a WHERE a.id = 2648;
SELECT * FROM account a WHERE a.id = 2628;


SELECT * FROM item_template it WHERE it.NAME LIKE '%stone%';

SELECT * FROM player WHERE name LIKE '%cucarot%';
SELECT * FROM account a WHERE a.id =3523;

SELECT * FROM map_template mt WHERE mt.NAME like '%trạm tàu vũ trụ%';



SELECT * FROM item_option_template iot WHERE iot.id IN (248, 250, 134, 137, 130, 142, 251);

SELECT * FROM item_option_template iot WHERE iot.id IN (128, 140, 133, 136, 132, 144);

SELECT * FROM item_option_template iot WHERE iot.id IN (129, 141, 135, 138, 131, 143);


SELECT * FROM item_option_template iot WHERE iot.id IN (250, 251, 142, 140, 136, 251);


SELECT * FROM item_template it WHERE it.NAME LIKE '%đá ngũ%';

SELECT * FROM item_template it WHERE it.NAME like '%thiên sứ%';

SELECT * FROM item_template it WHERE id = 1327;

SELECT * FROM part p WHERE p.id = 1018;

SELECT * FROM item_template it WHERE it.name LIKE '%hào quang%';
SELECT * FROM item_template it WHERE it.part = 58;

SELECT * FROM item_template it WHERE it.id = 805;
UPDATE item_template it SET it.TYPE = 76, part = 58 WHERE id = 805;


INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(805, 11, 3, 'Vòng thiên sứ', 'Vật phẩm sự kiện', 29092, 45, 0, 0);



SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%sao pha lê%';

SELECT * FROM player p WHERE p.name LIKE '%thợ săn zeno%';

SELECT * FROM item_template it WHERE it.id IN (555, 557, 559, 562, 564, 566, 563, 565, 567, 561);
SELECT * FROM item_template it WHERE it.id IN (1991, 16, 2013);
SELECT * FROM item_template it WHERE id >= 2045 AND id <=  2051;
SELECT * FROM item_template it WHERE it.id IN (2095, 2096, 1992, 1993);




SELECT * FROM item_template it WHERE it.NAME LIKE '%rương gỗ%';


SELECT * FROM item_template it WHERE it.id IN (2186, 2187, 2188, 2189, 2190, 2191, 2192);





UPDATE player p SET p.items_bag = '[{"quantity":1,"create_time":1726634918760,"temp_id":194,"option":[[73,1]]},{"quantity":1,"create_time":1728912463212,"temp_id":521,"option":[[1,0]]},{"quantity":1,"create_time":1727509935947,"temp_id":2085,"option":[[50,1],[101,300],[21,80]]},{"quantity":225,"create_time":1728912767329,"temp_id":1150,"option":[[73,0]]},{"quantity":814,"create_time":1729276661216,"temp_id":2088,"option":[[73,0]]},{"quantity":1,"create_time":1730274652916,"temp_id":2123,"option":[[5,125],[77,115],[103,115],[106,0],[50,40],[102,10],[107,10]]},{"quantity":162,"create_time":1729737187558,"temp_id":2029,"option":[[73,0]]},{"quantity":1,"create_time":1729136242631,"temp_id":536,"option":[[9,0],[50,32],[102,8],[30,0],[107,8]]},{"quantity":247,"create_time":1726992713893,"temp_id":1153,"option":[[73,0]]},{"quantity":366,"create_time":1730190842260,"temp_id":2170,"option":[[73,0]]},{"quantity":1,"create_time":1727695184327,"temp_id":1960,"option":[[40,18],[244,5]]},{"quantity":1,"create_time":1729102729735,"temp_id":2128,"option":[[50,50],[77,50],[103,50],[106,0],[116,0],[30,0]]},{"quantity":1,"create_time":1730272403901,"temp_id":2136,"option":[[244,5],[101,1000],[50,35],[77,23],[103,29],[30,0]]},{"quantity":22,"create_time":1726670084385,"temp_id":2050,"option":[[73,0]]},{"quantity":19,"create_time":1726670080876,"temp_id":2045,"option":[[73,0]]},{"quantity":32,"create_time":1729845292804,"temp_id":2049,"option":[[73,0]]},{"quantity":20,"create_time":1726670082928,"temp_id":2048,"option":[[73,0]]},{"quantity":25,"create_time":1726670079373,"temp_id":2051,"option":[[73,0]]},{"quantity":30,"create_time":1726670079873,"temp_id":2047,"option":[[73,0]]},{"quantity":1,"create_time":1731549431967,"temp_id":2175,"option":[[50,30],[77,35],[103,30]]},{"quantity":1,"create_time":1727636036439,"temp_id":0,"option":[[47,2],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":6,"option":[[6,192],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":21,"option":[[0,10],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":27,"option":[[7,11],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1727636036439,"temp_id":12,"option":[[14,1],[248,0],[250,3],[101,70],[102,7],[30,0],[107,7]]},{"quantity":20,"create_time":1726670080377,"temp_id":2046,"option":[[73,0]]},{"quantity":16,"create_time":1730662693269,"temp_id":666,"option":[[73,0]]},{"quantity":212,"create_time":1731215937103,"temp_id":17,"option":[[73,0]]},{"quantity":1,"create_time":1731293983937,"temp_id":561,"option":[[14,14],[87,0],[21,17]]},{"quantity":1,"create_time":1731294101613,"temp_id":561,"option":[[14,14],[87,0],[21,17]]},{"quantity":1,"create_time":1727607197215,"temp_id":739,"option":[[50,55],[77,55],[103,55],[21,80],[101,1111]]},{"quantity":1,"create_time":1727446578891,"temp_id":730,"option":[[50,25],[77,1],[103,1],[14,1],[5,95],[102,6],[107,6]]},{"quantity":1,"create_time":1731065219495,"temp_id":2175,"option":[[50,30],[77,35],[103,30]]},{"quantity":1,"create_time":1731571752000,"temp_id":1989,"option":[[30,0]]},{"quantity":9685,"create_time":1729196630550,"temp_id":1288,"option":[[73,0]]},{"quantity":7,"create_time":1731591300885,"temp_id":926,"option":[[73,0]]},{"quantity":1,"create_time":1731715783904,"temp_id":925,"option":[[73,0]]},{"quantity":1,"create_time":1731598447534,"temp_id":1278,"option":[[50,25],[77,25],[103,25]]},{"quantity":1,"create_time":1728912560837,"temp_id":536,"option":[[9,0],[95,10],[102,7],[96,25],[30,0],[107,7]]},{"quantity":1,"create_time":1731650036638,"temp_id":930,"option":[[73,0]]},{"quantity":13,"create_time":1731650669953,"temp_id":16,"option":[[73,0]]},{"quantity":1,"create_time":1731658140756,"temp_id":566,"option":[[0,4028],[21,15],[107,6]]},{"quantity":1,"create_time":1731663205445,"temp_id":561,"option":[[14,17],[21,15],[107,4]]},{"quantity":18,"create_time":1731663437946,"temp_id":2013,"option":[[73,0]]},{"quantity":1,"create_time":1731665532040,"temp_id":567,"option":[[7,40824],[21,15],[107,1]]},{"quantity":1,"create_time":1731665682265,"temp_id":566,"option":[[0,4414],[21,15],[107,0]]},{"quantity":1,"create_time":1731665854393,"temp_id":555,"option":[[47,738],[86,0],[21,17]]},{"quantity":1,"create_time":1731666287917,"temp_id":564,"option":[[0,4298],[21,15],[107,5]]},{"quantity":6,"create_time":1729859945998,"temp_id":2171,"option":[[73,0]]},{"quantity":1,"create_time":1730998678026,"temp_id":2097,"option":[[40,50]]},{"quantity":1,"create_time":1731745249669,"temp_id":1192,"option":[[50,110],[77,110],[103,110]]},{"quantity":3,"create_time":1731745621209,"temp_id":2183,"option":[[73,0]]},{"quantity":987,"create_time":1731745688555,"temp_id":674,"option":[[73,0]]},{"quantity":0,"create_time":1729240952656,"temp_id":650,"option":[[47,292],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[186,15],[30,0],[107,10]]},{"quantity":60,"create_time":1731745986464,"temp_id":2180,"option":[[73,0]]},{"quantity":97,"create_time":1731746135328,"temp_id":2186,"option":[[73,0]]},{"quantity":90,"create_time":1731746139764,"temp_id":2187,"option":[[73,0]]},{"quantity":89,"create_time":1731746142886,"temp_id":2188,"option":[[73,0]]},{"quantity":96,"create_time":1731746146457,"temp_id":2189,"option":[[73,0]]},{"quantity":96,"create_time":1731746150565,"temp_id":2190,"option":[[73,0]]},{"quantity":96,"create_time":1731746154192,"temp_id":2191,"option":[[73,0]]},{"quantity":96,"create_time":1731746157884,"temp_id":2192,"option":[[73,0]]},{"quantity":95,"create_time":1731749576055,"temp_id":14,"option":[[73,0]]},{"quantity":95,"create_time":1731749762512,"temp_id":18,"option":[[73,0]]},{"quantity":95,"create_time":1731749776219,"temp_id":19,"option":[[73,0]]},{"quantity":95,"create_time":1731749786691,"temp_id":20,"option":[[73,0]]},{"quantity":99,"create_time":1731752053696,"temp_id":15,"option":[[73,0]]},{"quantity":1,"create_time":1731814379617,"temp_id":561,"option":[[14,12],[249,0],[30,0]]},{"quantity":1,"create_time":1731840577648,"temp_id":562,"option":[[0,5000],[249,0],[248,0],[250,3],[30,0]]},{"quantity":1,"create_time":1731751301975,"temp_id":0,"option":[[73,0],[200,10]]},{"quantity":1,"create_time":1731840806295,"temp_id":656,"option":[[14,15],[249,0],[128,0],[140,0],[30,0]]},{"quantity":1,"create_time":1731840810068,"temp_id":658,"option":[[23,150],[249,0],[248,0],[250,3],[30,0]]},{"quantity":1,"create_time":1731840813277,"temp_id":657,"option":[[0,9000],[249,0],[248,0],[250,3],[30,0]]},{"quantity":1,"create_time":1731840586299,"temp_id":555,"option":[[47,250],[249,0],[128,0],[140,0],[30,0]]},{"quantity":1,"create_time":1732115350995,"temp_id":561,"option":[[14,12],[249,0],[30,0]]},{"quantity":1,"create_time":1732205092895,"temp_id":0,"option":[[73,0],[107,10]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' WHERE p.id = 1240;



SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%chỉ số kích hoạt ẩn%';
SELECT * FROM item_option_template iot WHERE iot.id IN (21);



SELECT * FROM item_template it WHERE it.id IN (555, 556, 563, 557, 558, 565, 559, 567, 560);



SELECT * FROM item_template it WHERE it.id >= 1982 AND it.id <= 1985;


SELECT * FROM item_template it WHERE it.id IN (0, 33, 3, 34, 136, 137, 138, 139, 230, 231, 232, 233, 1, 41, 4, 42, 152, 153, 154, 155, 234, 235, 236, 237, 2, 49, 5, 50, 168, 169, 170, 171, 238, 239, 240, 241, 555, 557, 559);


SELECT * FROM map_template mt WHERE mt.NAME LIKE '%hành tinh whis%';
SELECT * FROM map_template mt WHERE mt.NAME LIKE '%thực vật%';
SELECT * FROM map_template mt WHERE mt.NAME LIKE '%Làng plant nguyên thuỷ%';

SELECT * FROM player p WHERE p.name LIKE '%bodungdayy%';
SELECT * FROM account a WHERE a.id = 12786;

SELECT * FROM map_template mt WHERE mt.NAME LIKE '%thung lũng phía bắc%';

SELECT * FROM item_template it WHERE it.NAME LIKE '%memo%';
SELECT * FROM item_template it WHERE it.id = 2183;

SELECT * FROM player p WHERE name LIKE '%taolaking%';
SELECT * FROM account a WHERE a.id = 2586;

SELECT * FROM account a WHERE a.username = 'tnsm';





SELECT * FROM item_template it WHERE it.id IN (2119, 2118, 2120);
SELECT * FROM item_option_template iot WHERE iot.id IN (50, 77, 103, 72, 93, 247, 197, 117, 199, 73, 257, 21, 30);
SELECT * FROM item_template it WHERE ID >= 14 AND it.id <= 20;


SELECT * FROM map_template mt WHERE mt.NAME LIKE '%thần điện%';

SELECT mt.npcs, mt.* FROM map_template mt WHERE id >= 110;

SELECT * FROM item_template it WHERE it.NAME like '%zeno%';
SELECT * FROM item_template it WHERE it.NAME like '%whis%';