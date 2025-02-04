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





UPDATE player SET account_id = 18131, name = 'testtt', power = 55679239649676, head = 29, gender = 1, have_tennis_space_ship = 1, clan_id_sv1 = 7236, clan_id_sv2 = -1, data_inventory = '[187720424000,539545,1591620,1000000000000]', data_location = '[1559,96,0]', data_point = '[0,35638,10200,0,9,1000,842551,0,55663217482180,1000,20000,55679239649676,111220]', data_magic_tree = '[0,1738311923672,1,1738312257944,5]', items_body = '[{"quantity":1,"create_time":1738657403730,"temp_id":1,"option":[[175,111111111]]},{"quantity":1,"create_time":1738674883730,"temp_id":1052,"option":[[22,145],[131,0],[143,0],[21,100],[30,0]]},{"quantity":1,"create_time":1738634599238,"temp_id":261,"option":[[50,11111111]]},{"quantity":1,"create_time":1738634682349,"temp_id":28,"option":[[52,111111111]]},{"quantity":84,"create_time":1738329643201,"temp_id":561,"option":[[50,1]]},{"quantity":1,"create_time":1738675408095,"temp_id":1964,"option":[[10,111]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1738672081653,"temp_id":1129,"option":[[50,999]]},{"quantity":1,"create_time":1738674094820,"temp_id":2121,"option":[[50,17],[77,12],[103,19],[101,17],[43,15]]},{"quantity":1,"create_time":1738673557254,"temp_id":1949,"option":[[31,111]]},{"quantity":1,"create_time":1738673873793,"temp_id":1952,"option":[[27,111]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1738672505510,"temp_id":1919,"option":[[50,11]]},{"quantity":1,"create_time":1738672885334,"temp_id":1922,"option":[[40,111]]}]', items_bag = '[{"quantity":1,"create_time":1738311923672,"temp_id":454,"option":[[73,1]]},{"quantity":1,"create_time":1738311923672,"temp_id":194,"option":[[73,1]]},{"quantity":8970000,"create_time":1738312971891,"temp_id":1288,"option":[[0,0]]},{"quantity":997970,"create_time":1738328575590,"temp_id":674,"option":[[73,0]]},{"quantity":1,"create_time":1738595076148,"temp_id":1,"option":[[47,2],[132,0],[144,100]]},{"quantity":1,"create_time":1738595053910,"temp_id":7,"option":[[6,20],[132,0],[144,100]]},{"quantity":1,"create_time":1738634759805,"temp_id":28,"option":[[48,99999999]]},{"quantity":1,"create_time":1738595169643,"temp_id":12,"option":[[14,1],[132,0],[144,100]]},{"quantity":88,"create_time":1738509206597,"temp_id":557,"option":[[73,0]]},{"quantity":90,"create_time":1738509215807,"temp_id":558,"option":[[73,0]]},{"quantity":88,"create_time":1738509230797,"temp_id":564,"option":[[73,0]]},{"quantity":88,"create_time":1738509235039,"temp_id":565,"option":[[73,0]]},{"quantity":92,"create_time":1738509238968,"temp_id":561,"option":[[73,0]]},{"quantity":2,"create_time":1738657023482,"temp_id":16,"option":[[73,0]]},{"quantity":1,"create_time":1738634408273,"temp_id":261,"option":[[50,1111111]]},{"quantity":1,"create_time":1738634341000,"temp_id":1,"option":[[175,111111]]},{"quantity":1,"create_time":1738657480781,"temp_id":195,"option":[[73,0]]},{"quantity":1,"create_time":1738669819104,"temp_id":2177,"option":[[1,1]]},{"quantity":1,"create_time":1738671081812,"temp_id":1925,"option":[[50,1]]},{"quantity":1,"create_time":1738671355993,"temp_id":2200,"option":[[11,1]]},{"quantity":1,"create_time":1738671442553,"temp_id":1179,"option":[[1,1]]},{"quantity":1,"create_time":1738671495738,"temp_id":2090,"option":[[1,1]]},{"quantity":1,"create_time":1738671614717,"temp_id":1180,"option":[[1,1]]},{"quantity":1,"create_time":1738671642329,"temp_id":1191,"option":[[1,1]]},{"quantity":1,"create_time":1738671686281,"temp_id":2036,"option":[[73,0]]},{"quantity":1,"create_time":1738496096138,"temp_id":450,"option":[[106,0]]},{"quantity":1,"create_time":1738672602803,"temp_id":1915,"option":[[50,99]]},{"quantity":1,"create_time":1738672191468,"temp_id":1260,"option":[[48,11]]},{"quantity":1,"create_time":1738672224839,"temp_id":1161,"option":[[47,2222]]},{"quantity":1,"create_time":1738672553581,"temp_id":1916,"option":[[50,11]]},{"quantity":1,"create_time":1738671907036,"temp_id":1235,"option":[[50,999]]},{"quantity":1,"create_time":1738673234149,"temp_id":1945,"option":[[36,111]]},{"quantity":1,"create_time":1738673344556,"temp_id":1947,"option":[[34,111]]},{"quantity":1,"create_time":1738673472153,"temp_id":1948,"option":[[32,111]]},{"quantity":1,"create_time":1738673682481,"temp_id":1950,"option":[[29,111]]},{"quantity":1,"create_time":1738674274889,"temp_id":1954,"option":[[23,111]]},{"quantity":1,"create_time":1738674315425,"temp_id":1955,"option":[[22,111]]},{"quantity":1,"create_time":1738674349591,"temp_id":1956,"option":[[21,111]]},{"quantity":1,"create_time":1738673073225,"temp_id":1941,"option":[[39,111]]},{"quantity":1,"create_time":1738674532854,"temp_id":1958,"option":[[18,111]]},{"quantity":6,"create_time":1738675062863,"temp_id":1961,"option":[[1,1]]},{"quantity":1,"create_time":1738674402367,"temp_id":1957,"option":[[20,111]]},{"quantity":1,"create_time":1738675374415,"temp_id":1963,"option":[[11,111]]},{"quantity":1,"create_time":1738676532833,"temp_id":1966,"option":[[7,111]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]', items_box = '[{"quantity":1,"create_time":1738598832313,"temp_id":47,"option":[[7,30],[28,6],[130,0],[142,100]]},{"quantity":1,"create_time":1738598895387,"temp_id":31,"option":[[7,150],[28,30],[130,0],[142,100]]},{"quantity":1,"create_time":1738598928228,"temp_id":48,"option":[[7,300],[28,60],[130,0],[142,100]]},{"quantity":1,"create_time":1738598972197,"temp_id":164,"option":[[7,600],[28,120],[130,0],[142,100]]},{"quantity":1,"create_time":1738599004145,"temp_id":165,"option":[[7,1500],[28,300],[130,0],[142,100]]},{"quantity":1,"create_time":1738599031889,"temp_id":166,"option":[[7,3000],[28,600],[130,0],[142,100]]},{"quantity":1,"create_time":1738599056362,"temp_id":167,"option":[[7,6000],[28,1200],[130,0],[142,100]]},{"quantity":1,"create_time":1738599079307,"temp_id":270,"option":[[7,10000],[28,1700],[130,0],[142,100]]},{"quantity":1,"create_time":1738599105411,"temp_id":271,"option":[[7,15000],[28,2200],[130,0],[142,100]]},{"quantity":1,"create_time":1738599136005,"temp_id":272,"option":[[7,20000],[28,2700],[130,0],[142,100]]},{"quantity":1,"create_time":1738599163948,"temp_id":273,"option":[[7,25000],[28,3200],[130,0],[142,100]]},{"quantity":1,"create_time":1738599314756,"temp_id":45,"option":[[0,24],[130,0],[142,100]]},{"quantity":1,"create_time":1738599346007,"temp_id":160,"option":[[0,50],[130,0],[142,100]]},{"quantity":1,"create_time":1738599377938,"temp_id":161,"option":[[0,100],[130,0],[142,100]]},{"quantity":1,"create_time":1738599428760,"temp_id":163,"option":[[0,500],[130,0],[142,100]]},{"quantity":1,"create_time":1738599468637,"temp_id":258,"option":[[0,630],[130,0],[142,100]]},{"quantity":1,"create_time":1738599495831,"temp_id":259,"option":[[0,950],[130,0],[142,100]]},{"quantity":1,"create_time":1738599530070,"temp_id":260,"option":[[0,1450],[130,0],[142,100]]},{"quantity":1,"create_time":1738599560412,"temp_id":261,"option":[[0,2150],[130,0],[142,100]]},{"quantity":1,"create_time":1738635066618,"temp_id":557,"option":[[50,99999999]]},{"quantity":1,"create_time":1738595110711,"temp_id":22,"option":[[0,3],[132,0],[144,100]]},{"quantity":83,"create_time":1738329576189,"temp_id":564,"option":[[50,1]]},{"quantity":1,"create_time":1738634736375,"temp_id":28,"option":[[49,99999999]]},{"quantity":83,"create_time":1738329592702,"temp_id":565,"option":[[50,1]]},{"quantity":1,"create_time":1738595139896,"temp_id":28,"option":[[7,15],[132,0],[144,100]]},{"quantity":76,"create_time":1738329382340,"temp_id":557,"option":[[50,1]]},{"quantity":8,"create_time":1738635125891,"temp_id":19,"option":[[73,0]]},{"quantity":18,"create_time":1738635142549,"temp_id":20,"option":[[73,0]]},{"quantity":4,"create_time":1738635197765,"temp_id":665,"option":[[73,0]]},{"quantity":4,"create_time":1738635294520,"temp_id":663,"option":[[73,0]]},{"quantity":10,"create_time":1738635429562,"temp_id":666,"option":[[73,0]]},{"quantity":6,"create_time":1738635706606,"temp_id":664,"option":[[73,0]]},{"quantity":2,"create_time":1738636148036,"temp_id":667,"option":[[73,0]]},{"quantity":1,"create_time":1738636465906,"temp_id":18,"option":[[73,0]]},{"quantity":1,"create_time":1738637024406,"temp_id":1911,"option":[[73,0]]},{"quantity":1,"create_time":1738656335636,"temp_id":570,"option":[[73,0]]},{"quantity":18,"create_time":1738657007212,"temp_id":17,"option":[[73,0]]},{"quantity":1,"create_time":1738657007497,"temp_id":559,"option":[[47,1356],[21,15],[30,0],[107,1]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]', items_box_lucky_round = '[{"quantity":14000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":47000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":220,"option":[]},{"quantity":13000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":222,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":220,"option":[]},{"quantity":46000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":221,"option":[]},{"quantity":14000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":441,"option":[[95,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":443,"option":[[97,5]]},{"quantity":40000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":43000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":13000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":441,"option":[[95,5]]},{"quantity":30000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":222,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":220,"option":[]},{"quantity":35000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":441,"option":[[95,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":2047,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":2051,"option":[]},{"quantity":9000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":32000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":447,"option":[[101,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":37000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":36000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":223,"option":[]},{"quantity":7000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":19000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":8000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":13000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":8000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":7000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":6000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":47000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":5000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":443,"option":[[97,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":223,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":220,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":224,"option":[]},{"quantity":43000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":13000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":42000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":44000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":14000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":36000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":2049,"option":[]},{"quantity":48000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":35000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":22000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":20,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":222,"option":[]},{"quantity":43000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":21000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":1969,"option":[]},{"quantity":15000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":441,"option":[[95,5]]},{"quantity":14000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":37000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":34000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":20,"option":[]},{"quantity":31000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":222,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":1969,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":24000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":31000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":19,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":220,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":446,"option":[[100,3]]},{"quantity":29000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":222,"option":[]},{"quantity":28000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":18,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":2050,"option":[]},{"quantity":42000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":20,"option":[]},{"quantity":35000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":9000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":1,"create_time":1738677501621,"temp_id":442,"option":[[96,5]]},{"quantity":30000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":15000,"create_time":1738677501621,"temp_id":189,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]', friends = '[]', enemies = '[]', data_intrinsic = '[12,56,0,0]', data_item_time = '[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]', data_task = '[0,25,1]', data_mabu_egg = '{}', data_charm = '[1741227096892,1741227095755,1741227098488,0,0,1741227105747,1741227101156,0,0,0,0]', skills = '[[2,1738677592601,1,0],[3,0,0,0],[7,0,0,0],[11,1738634797938,1,0],[12,1738595362976,7,0],[17,1738677402802,1,0],[18,0,0,0],[19,0,0,0],[26,0,0,0]]', skills_shortcut = '[2,-1,-1,-1,-1,-1,-1,-1,-1,-1]', pet_info = '{"gender":1,"is_mabu":8,"level":7,"name":"$Fide Nhí","type_fusion":0,"left_fusion":1100658365,"status":0}', pet_power = 100001500000, pet_point = '{"mp":0,"max_stamina":1000,"mpg":13960,"critg":1,"limit_power":0,"stamina":1000,"hp":0,"damg":667,"power":100001500000,"defg":45,"hpg":9840,"tiem_nang":99970077301}', pet_body = '[{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]}]', pet_skill = '[[4,1],[1,1],[6,1],[12,1],[24,1]]', create_time = '2025-01-31 00:25:23', data_black_ball = '[[0,0],[0,0],[0,0],[0,0],[0,0],[0,0],[0,0]]', thoi_vang = 0, data_side_task = '[0,0,50,-1,0,0]', new_reg = 0, event_point = 0, `1sao` = 0, `2sao` = 0, `3sao` = 2, top = 0, collection_book = '[{"id":1,"amount":0,"level":0,"use":false},{"id":2,"amount":0,"level":0,"use":false},{"id":3,"amount":0,"level":0,"use":false},{"id":4,"amount":0,"level":0,"use":false},{"id":5,"amount":0,"level":0,"use":false},{"id":6,"amount":0,"level":0,"use":false},{"id":7,"amount":0,"level":0,"use":false},{"id":8,"amount":0,"level":0,"use":false},{"id":9,"amount":0,"level":0,"use":false},{"id":10,"amount":0,"level":0,"use":false},{"id":11,"amount":0,"level":0,"use":false},{"id":12,"amount":0,"level":0,"use":false},{"id":13,"amount":0,"level":0,"use":false},{"id":14,"amount":0,"level":0,"use":false},{"id":15,"amount":0,"level":0,"use":false},{"id":16,"amount":0,"level":0,"use":false},{"id":17,"amount":0,"level":0,"use":false},{"id":18,"amount":0,"level":0,"use":false}]', challenge = '[1,0,0]', firstTimeLogin = '2025-02-04 00:05:28', sk_tet = '[0,0,0,0,0]', buy_limit = '[0,0,0,0,0,0,0,0,0,0,0,0,0]', moc_nap = 0, achivements = '[{"receive":0,"count":0,"finish":0,"id":0},{"receive":0,"count":0,"finish":0,"id":1},{"receive":0,"count":0,"finish":0,"id":2},{"receive":0,"count":0,"finish":0,"id":3},{"receive":0,"count":11225,"finish":0,"id":4},{"receive":0,"count":165,"finish":0,"id":5},{"receive":0,"count":0,"finish":0,"id":6},{"receive":0,"count":29,"finish":0,"id":7},{"receive":0,"count":0,"finish":0,"id":8},{"receive":0,"count":0,"finish":0,"id":9},{"receive":0,"count":0,"finish":0,"id":10},{"receive":0,"count":0,"finish":0,"id":11},{"receive":0,"count":0,"finish":0,"id":12},{"receive":0,"count":0,"finish":0,"id":13},{"receive":0,"count":9,"finish":0,"id":14},{"receive":0,"count":0,"finish":0,"id":15}]', reward_limit = '[0,0,0,0,0,0,0,0,0,0]', item_new_time = '[0,0,0,0,120925,0,0,0,0,0,0,0,0,0]', data_bill_egg = '{"create_time":1738634798630,"time_done":0}', data_egg_linhthu = '{}', time_may_do = '[0]', pointShiba = 0, diem_skien = 0, maintain = 0, zm = '[0,0]', lvpet = 7, data_tong_nap = '[0,0,0]', time_effect = '[0]' WHERE id = 13935;
SELECT * FROM player p WHERE p.id = 13935;

SELECT * FROM map_template mt WHERE mt.NAME like '%hành tinh bill%';


SELECT * FROM item_template it WHERE it.id = 638;
SELECT * FROM item_template it WHERE it.TYPE = 27;
select * FROM account a WHERE a.username like '%tester1%';
SELECT p.data_task, p.* FROM player p where p.account_id = 18128;
SELECT * FROM task_sub_template tmt WHERE tmt.NAME LIKE '%sức đánh%';
SELECT * FROM mob_reward_map mrm WHERE mrm.map_id IN (161, 162, 163);
SELECT * FROM mob_reward mr WHERE mr.item_id = 1229;
select * FROM item_option_template iot WHERE iot.id IN (129, 141, 30, 139, 136, 137, 138, 141, 249);
SELECT * FROM item_option_template iot WHERE iot.NAME like '%$(5%';
select * FROM item_template it WHERE it.NAME like '%ngọc rồng%';
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

