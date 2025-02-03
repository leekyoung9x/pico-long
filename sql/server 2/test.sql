UPDATE item_option_template iot SET iot.NAME = 'Chỉ số kích hoạt ẩn tăng 10% SĐ và 15% HP, KI' WHERE iot.id = 249;
-- đóng shop hallowin và noel
DELETE FROM tab_shop WHERE ID = 48;
DELETE FROM tab_shop WHERE ID = 49;
-- santa - cửa hàng xóa đồ
-- == 2083 bardock
DELETE FROM item_shop WHERE ID = 9883;
-- == cải trang
-- == noel
DELETE FROM item_shop where ID = 9917;
-- == hit
Delete FROM item_shop where ID = 945;
-- == super xayda blue
DELETE from item_shop WHERE id = 9865;
-- == cải trang 2061
DELETE from item_shop WHERE id = 9857;
-- == cải trang 2054
DELETE from item_shop WHERE id = 9882;
-- == cải trang 2056
DELETE from item_shop WHERE id = 9881;

-- giảm giá thẻ nội tại max 15k tvv
UPDATE item_shop `is` SET `is`.quantity_exchange = 15000 WHERE ID = 9848;
-- update icon đệ fide nhí
UPDATE item_template it set it.icon_id = 13302 WHERE it.id = 1969;
-- đóng cây nêu
UPDATE map_template mt set mt.npcs = '["[7,228,432,562]","[6,492,432,0]","[49,597,432,4544]","[54,428,432,9077]"]' WHERE mt.id = 0;
UPDATE map_template mt set mt.npcs = '["[6,564,432,0]","[8,300,432,350]","[54,600,432,9077]"]' WHERE mt.id = 7;
UPDATE map_template mt set mt.npcs = '["[9,396,408,565]","[6,252,408,0]","[54,628,408,9077]"]' WHERE mt.id = 14;

-- thay option cho sao pha lê
DELETE FROM item_shop WHERE id = 9856;

-- spl 441, 442, 443 thay option
UPDATE item_shop_option iso SET iso.option_id = 95, iso.param = 5 WHERE iso.item_shop_id = 9853 AND iso.option_id = 50;
UPDATE item_shop_option iso SET iso.option_id = 96, iso.param = 5 WHERE iso.item_shop_id = 9854 AND iso.option_id = 77;
UPDATE item_shop_option iso SET iso.option_id = 97, iso.param = 5 WHERE iso.item_shop_id = 9855 AND iso.option_id = 103;

-- thêm option cho set daimao
DELETE from item_option_template where ID = 255;
INSERT INTO item_option_template (id, NAME, TYPE) VALUES
(255, '$(5 món x2 thời gian đẻ trứng)', 0);

-- update option 100% sát thương đẻ trứng
UPDATE item_option_template iot SET iot.NAME = '5 món +#% sát thương Đẻ Trứng' WHERE iot.id = 144;


-- map 161: 80, 81
DELETE FROM mob_reward WHERE ID = 46;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(46, 80, 1229, 1, 200, True);

DELETE FROM mob_reward WHERE ID = 47;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(47, 81, 1229, 1, 200, True);


delete FROM mob_reward_map WHERE ID = 110;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(110, 46, 161);

delete FROM mob_reward_map WHERE ID = 111;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(111, 47, 161);


-- map 162: 80, 81
delete FROM mob_reward_map WHERE ID = 112;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(112, 46, 162);

delete FROM mob_reward_map WHERE ID = 113;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(113, 47, 162);
-- map 163: 80, 81
delete FROM mob_reward_map WHERE ID = 114;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(114, 46, 163);

delete FROM mob_reward_map WHERE ID = 115;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(115, 47, 163);


-- đá thần linh

UPDATE item_template SET TYPE = 27, gender = 3, NAME = 'Đá thần linh', description = 'Dùng để nâng cấp SKH', icon_id = 29994, part = -1, is_up_to_up = 0, power_require = 0 WHERE id = 1911;


-- rơi đồ cold
-- 66
DELETE from mob_reward where ID = 48;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(48, 66, 1911, 1, 500, True);
-- 67
DELETE from mob_reward where ID = 49;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(49, 67, 1911, 1, 500, True);
-- 68
DELETE from mob_reward where ID = 50;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(50, 68, 1911, 1, 500, True);
-- 69
DELETE from mob_reward where ID = 51;
INSERT INTO mob_reward (id, mob_id, item_id, ratio, type_ratio, for_all_gender) VALUES
(51, 69, 1911, 1, 500, True);
-- map 105: 66
DELETE FROM mob_reward_map WHERE ID = 116;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(116, 48, 105);

-- map 106: 66, 67
DELETE FROM mob_reward_map WHERE ID = 117;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(117, 48, 106);
DELETE FROM mob_reward_map WHERE ID = 118;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(118, 49, 106);

-- map 107: 67
DELETE FROM mob_reward_map WHERE ID = 119;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(119, 49, 107);

-- map 108: 67, 68
DELETE FROM mob_reward_map WHERE ID = 120;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(120, 49, 108);
DELETE FROM mob_reward_map WHERE ID = 121;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(121, 50, 108);
-- map 109: 68
DELETE FROM mob_reward_map WHERE ID = 122;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(122, 50, 109);
-- map 110: 68, 69
DELETE FROM mob_reward_map WHERE ID = 123;
INSERT INTO mob_reward_map (id, mob_reward_id, map_id) VALUES
(123, 51, 110);





-- test
SELECT * FROM item_template it ORDER BY it.id DESC;
SELECT * FROM map_template mt WHERE ID IN (161, 162, 163);

SELECT * FROM mob_reward mr ORDER BY mr.id DESC;
SELECT * FROM mob_reward_map mrm ORDER BY mrm.id DESC;
SELECT * FROM mob_reward mr WHERE mr.mob_id IN (66, 67, 68, 69);
SELECT * FROM mob_reward_map mrm WHERE mrm.map_id >= 105 AND mrm.map_id <= 110;


SELECT * FROM item_option_template iot WHERE iot.NAME like '%giáp+#%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%HP+#%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%HP/%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%tấn công+#%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%KI+#%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%KI/30s%';
SELECT * FROM item_option_template iot WHERE iot.NAME like '%chí mạng+#%%';

SELECT * from item_option_template iot WHERE iot.id  = 144;

SELECT * from item_option_template iot WHERE iot.id IN (5, 77, 103, 50, 107, 106, 30, 161);


-- map nhs
SELECT * FROM map_template mt WHERE ID >= 122 AND ID <= 124;
-- map cold
SELECT id, mt.mobs, mt.* FROM map_template mt WHERE ID >= 105 AND ID <= 110;

SELECT * FROM player WHERE name like '%thợ săn%';
SELECT * FROM account a WHERE a.id = 3928;


SELECT * FROM player WHERE name like '%daxanhle%';
SELECT * FROM account a WHERE a.id = 4652;


SELECT * FROM item_template it WHERE it.id = 638;
SELECT * FROM item_template it WHERE it.TYPE = 27;
select * FROM account a WHERE a.username like '%tester1%';
SELECT p.data_task, p.* FROM player p where p.account_id = 18128;
SELECT * FROM task_sub_template tmt WHERE tmt.NAME LIKE '%sức đánh%';
SELECT * FROM mob_reward_map mrm WHERE mrm.map_id IN (161, 162, 163);
SELECT * FROM mob_reward mr WHERE mr.item_id = 1229;
select * FROM item_option_template iot WHERE iot.id IN (129, 141, 30, 139, 136, 137, 138, 141, 249);
SELECT * FROM item_option_template iot WHERE iot.NAME like '%$(5%';
select * FROM item_template it WHERE it.NAME like '%bí kíp tuyệt kỹ%';
SELECT * FROM item_template it where ID >= 14 AND ID <= 20;
SELECT * FROM item_template it ORDER BY it.id DESC;
select * from map_template mt where mt.NAME like '%hang động%';
SELECT * FROM item_option_template iot ORDER BY iot.id DESC;
SELECT * FROM player p WHERE name like '%lì em%';
SELECT * FROM account a where a.id = 2615;
select * FROM account a WHERE a.is_admin = 1 ORDER BY a.id asc;
-- UPDATE player SET items_bag = '[{"quantity":1,"create_time":1726484559186,"temp_id":194,"option":[[73,1]]},{"quantity":1202,"create_time":1726523809478,"temp_id":1150,"option":[[73,0]]},{"quantity":1346,"create_time":1727352558020,"temp_id":1152,"option":[[73,0]]},{"quantity":1453,"create_time":1726541657128,"temp_id":1153,"option":[[73,0]]},{"quantity":1,"create_time":1727068078991,"temp_id":241,"option":[[47,450],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1727068081303,"temp_id":253,"option":[[6,24000],[27,3600],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1729102192288,"temp_id":1056,"option":[[0,36972],[72,8],[50,36],[102,9],[30,0],[107,9]]},{"quantity":1,"create_time":1727068086412,"temp_id":277,"option":[[7,23000],[28,2800],[50,24],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1727068088635,"temp_id":281,"option":[[14,12],[50,24],[102,8],[30,0],[107,8]]},{"quantity":16949,"create_time":1729558532728,"temp_id":674,"option":[[73,0]]},{"quantity":75,"create_time":1730920744854,"temp_id":595,"option":[[2,256]]},{"quantity":1,"create_time":1730942044044,"temp_id":2136,"option":[[244,5],[101,1000],[50,22],[77,34],[103,21],[30,0]]},{"quantity":0,"create_time":1729248088109,"temp_id":567,"option":[[23,50],[249,0],[135,0],[138,190],[108,14],[102,7],[163,15],[30,0],[107,7]]},{"quantity":0,"create_time":1729249061126,"temp_id":559,"option":[[47,250],[249,0],[135,0],[138,170],[108,14],[102,7],[163,15],[30,0],[107,7]]},{"quantity":1,"create_time":1729249122538,"temp_id":560,"option":[[22,50],[249,0],[135,0],[138,180],[108,14],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1729248483372,"temp_id":566,"option":[[0,5000],[249,0],[135,0],[138,180],[108,14],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1729247951168,"temp_id":561,"option":[[14,12],[249,0],[135,0],[138,190],[108,14],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1728603247683,"temp_id":567,"option":[[23,50],[249,0],[133,0],[136,0],[102,7],[102,0],[102,7],[50,28],[30,0],[107,7]]},{"quantity":1,"create_time":1728617578155,"temp_id":559,"option":[[47,250],[249,0],[133,0],[136,0],[102,7],[102,0],[102,7],[50,28],[30,0],[107,7]]},{"quantity":1,"create_time":1728874907807,"temp_id":560,"option":[[22,50],[249,0],[133,0],[136,0],[102,7],[102,0],[102,8],[50,32],[30,0],[107,8]]},{"quantity":1,"create_time":1728607518517,"temp_id":566,"option":[[0,10716],[249,0],[133,0],[136,0],[72,8],[50,32],[102,8],[30,0],[107,8]]},{"quantity":1,"create_time":1728879593620,"temp_id":567,"option":[[23,50],[249,0],[133,0],[136,0],[95,35],[102,7],[30,0],[107,7]]},{"quantity":1,"create_time":1728812501457,"temp_id":561,"option":[[14,19],[249,0],[133,0],[136,0],[50,21],[102,7],[72,7],[30,0],[107,7]]},{"quantity":1,"create_time":1732073435434,"temp_id":2185,"option":[[50,20],[77,20],[103,20]]},{"quantity":1,"create_time":1732201559773,"temp_id":2193,"option":[[50,110],[77,190],[103,110],[200,10],[102,10],[107,11]]},{"quantity":1,"create_time":1732698153249,"temp_id":2173,"option":[[77,50],[103,50],[50,50],[244,5]]},{"quantity":1,"create_time":1732626236448,"temp_id":2194,"option":[[5,110],[77,120],[103,120]]},{"quantity":1,"create_time":1729688551652,"temp_id":594,"option":[[47,400],[108,10]]},{"quantity":1,"create_time":1730946876629,"temp_id":2097,"option":[[77,50],[103,50],[50,50]]},{"quantity":1,"create_time":1729868931241,"temp_id":2169,"option":[[50,24],[9,0],[102,8],[30,0],[107,8]]},{"quantity":2090,"create_time":1733785485512,"temp_id":2180,"option":[[73,0]]},{"quantity":1,"create_time":1727238632408,"temp_id":921,"option":[[72,2],[50,23]]},{"quantity":1,"create_time":1729102192302,"temp_id":2017,"option":[[50,50],[77,50],[103,50],[116,0],[30,0]]},{"quantity":1,"create_time":1734100521263,"temp_id":2197,"option":[[50,46],[77,60],[103,58]]},{"quantity":1860,"create_time":1734100522467,"temp_id":2182,"option":[[73,0]]},{"quantity":2200,"create_time":1734233611764,"temp_id":2181,"option":[[73,0]]},{"quantity":2,"create_time":1730860741547,"temp_id":2131,"option":[[77,40],[245,10]]},{"quantity":1,"create_time":1734008490127,"temp_id":576,"option":[[50,31],[77,31],[103,31],[25,0]]},{"quantity":92,"create_time":1733738660056,"temp_id":1944,"option":[[73,0]]},{"quantity":106,"create_time":1733074615347,"temp_id":2013,"option":[[73,0]]},{"quantity":1,"create_time":1734402726150,"temp_id":2205,"option":[[30,0]]},{"quantity":21,"create_time":1735007817965,"temp_id":17,"option":[[73,0]]},{"quantity":5,"create_time":1735008739455,"temp_id":15,"option":[[73,0]]},{"quantity":4,"create_time":1735023606485,"temp_id":16,"option":[[73,0]]},{"quantity":1,"create_time":1735346660545,"temp_id":1934,"option":[[50,64],[77,65],[103,56]]},{"quantity":32,"create_time":1735346679325,"temp_id":2118,"option":[[77,8]]},{"quantity":1,"create_time":1730917860408,"temp_id":2128,"option":[[5,10],[50,25],[77,55],[103,55]]},{"quantity":1,"create_time":1735346736835,"temp_id":1934,"option":[[50,60],[77,57],[103,60]]},{"quantity":1,"create_time":1735346738854,"temp_id":1934,"option":[[50,61],[77,58],[103,60]]},{"quantity":1,"create_time":1735346816665,"temp_id":1934,"option":[[50,60],[77,61],[103,61]]},{"quantity":3035,"create_time":1735353700725,"temp_id":1192,"option":[[73,0]]},{"quantity":1,"create_time":1735346632865,"temp_id":1936,"option":[[50,59],[77,57],[103,54],[243,15]]},{"quantity":1,"create_time":1735346698535,"temp_id":1934,"option":[[50,57],[77,65],[103,62]]},{"quantity":3,"create_time":1735394314839,"temp_id":2177,"option":[[161,0],[30,0]]},{"quantity":30,"create_time":1735400957010,"temp_id":2179,"option":[[73,0]]},{"quantity":14,"create_time":1735479300042,"temp_id":2187,"option":[[73,0]]},{"quantity":11,"create_time":1735479300554,"temp_id":2188,"option":[[73,0]]},{"quantity":1,"create_time":1735565258337,"temp_id":454,"option":[[73,0]]},{"quantity":87,"create_time":1735617018565,"temp_id":1945,"option":[[73,0]]},{"quantity":11,"create_time":1735725617397,"temp_id":2120,"option":[[50,4]]},{"quantity":24,"create_time":1735725980760,"temp_id":2119,"option":[[103,8]]},{"quantity":1,"create_time":1735817916035,"temp_id":1934,"option":[[50,55],[77,62],[103,55]]},{"quantity":1,"create_time":1737684968606,"temp_id":1961,"option":[[73,0]]},{"quantity":1,"create_time":1737685023452,"temp_id":1969,"option":[[73,0]]},{"quantity":9985999,"create_time":1737685918409,"temp_id":1288,"option":[[73,0]]},{"quantity":4,"create_time":1737707064660,"temp_id":555,"option":[[47,833],[86,0],[21,17]]},{"quantity":1,"create_time":1737707098929,"temp_id":555,"option":[[73,0]]},{"quantity":1,"create_time":1737707653987,"temp_id":564,"option":[[0,5382],[86,0],[21,17]]},{"quantity":1,"create_time":1737795002887,"temp_id":170,"option":[[73,0]]},{"quantity":1,"create_time":1737795007318,"temp_id":170,"option":[[73,0],[255,0],[137,0]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]' WHERE ID = 25;
select * FROM item_template it where NAME LIKE '%cải trang bunma%';
SELECT * FROM  item_option_template iot WHERE iot.NAME LIKE '%x2 thời gian%';
SELECT * FROM item_shop_option iso WHERE iso.item_shop_id = 9853;
SELECT * FROM item_shop_option iso WHERE iso.item_shop_id = 9854;
SELECT * FROM item_shop_option iso WHERE iso.item_shop_id = 9855;
SELECT * from item_option_template iot WHERE id IN (50, 30, 252);
SELECT * from item_template it WHERE ID >= 555 AND ID <= 567;
SELECT * FROM item_template it WHERE id in (555, 556, 562, 563, 561);
SELECT * FROM item_template it WHERE id in (557, 558, 564, 565, 561);
SELECT * FROM item_template it WHERE id in (559, 560, 566, 567, 561);

SELECT * FROM item_option_template iot WHERE iot.id IN (95, 96, 97, 100, 101, 73, 98, 99, 50);
SELECT * FROM item_shop `is` WHERE `is`.tab_id = 31 AND `is`.temp_id = 742;
SELECT * FROM item_template it WHERE it.NAME like '%ngọc rồng%';
SELECT * FROM item_template it WHERE it.NAME like '%vip%';
SELECT * FROM map_template mt WHERE mt.NAME like '%Potaufeu%';
SELECT * FROM open_box ob WHERE ob.box_id = 2149;
SELECT * FROM item_template it WHERE id = 1919;
SELECT * FROM item_option_template iot WHERE iot.NAME like '%đẻ trứng%';
SELECT * FROM item_template iot WHERE id >= 1150 AND id <= 1153;
SELECT * FROM player p WHERE name like '%daxanhle%';
SELECT * FROM account a WHERE a.id = 4652;
select * from account a WHERE a.username = 'nm';
SELECT * from player p where p.name like '%zengaming%';
SELECT * FROM npc_template nt WHERE nt.NAME like '%thượng đế%';
select * from map_template mt WHERE mt.NAME like '%đại hội%';
select * FROM npc_template nt where nt.NAME LIKE '%cây nêu%';
SELECT mt.npcs, mt.* FROM map_template mt WHERE mt.npcs like '%[84,%';
SELECT * FROM tab_shop ts where ts.NAME like '%noel%';
SELECT * FROM map_template mt WHERE mt.id = 114;
SELECT * FROM tab_shop ts ORDER BY ts.id DESC;

SELECT * FROM npc_template nt WHERE name LIKE '%santa%';
SELECT * FROM shop s WHERE s.npc_id = 39;
SELECT * FROM tab_shop ts WHERE ts.shop_id = 20;
SELECT * FROM item_shop `is` WHERE `is`.tab_id = 35;
SELECT * FROM part p WHERE p.id = 1369;
SELECT * FROM head_avatar ha WHERE ha.head_id = 1369;
SELECT * FROM mob_reward mr WHERE (mr.item_id >= 441 AND mr.item_id <= 447) OR mr.item_id = 964 OR mr.item_id = 965;
select * FROM open_box ob WHERE ob.template_id_from >= 441 AND ob.template_id_to <= 447;
select * FROM open_box ob WHERE ob.template_id_from >= 964 AND ob.template_id_to <= 965;

select * FROM item_template it WHERE id IN (0, 6, 21, 27, 12);
select * FROM item_template it WHERE id IN (1150, 1151, 1152, 1153);


-- ====== SKH trái đất
-- kame
SELECT * FROM item_option_template iot WHERE iot.id IN (129, 141);
-- tenshinhan
SELECT * FROM item_option_template iot WHERE iot.id IN (127, 139);
-- krillin
SELECT * FROM item_option_template iot WHERE iot.id IN (128, 140);

-- =====SKH xayda
-- nappa
SELECT * FROM item_option_template iot WHERE iot.id IN (135, 138);
-- cadic
SELECT * FROM item_option_template iot WHERE iot.id IN (134, 137);
-- kakarot
SELECT * FROM item_option_template iot WHERE iot.id IN (133, 136);

-- =====SKH namec
-- picolo
SELECT * FROM item_option_template iot WHERE iot.id IN (130, 142);
-- Dende
SELECT * FROM item_option_template iot WHERE iot.id IN (131, 143);
-- Daimao
SELECT * FROM item_option_template iot WHERE iot.id IN (132, 255);

