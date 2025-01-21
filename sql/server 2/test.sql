UPDATE item_option_template iot SET iot.NAME = 'Chỉ số kích hoạt ẩn tăng 10% SĐ và 15% HP, KI' WHERE iot.id = 249;
-- đóng shop hallowin và noel
DELETE FROM tab_shop WHERE ID = 48;
DELETE FROM tab_shop WHERE ID = 49;






-- test
SELECT * FROM item_template it WHERE it.NAME like '%hoa cúc%';
SELECT * FROM item_template it WHERE it.NAME like '%tuyệt kỹ%';
SELECT * FROM map_template mt WHERE mt.NAME like '%Potaufeu%';
SELECT * FROM open_box ob WHERE ob.box_id = 2149;
SELECT * FROM item_template it WHERE id = 751;
SELECT * FROM item_template it WHERE id = 570;
SELECT * FROM item_option_template iot WHERE iot.id = 141;
SELECT * FROM item_option_template iot WHERE iot.NAME like '%Chỉ số kích hoạt ẩn tăng 15% SĐ, HP, KI%';
SELECT * FROM item_option_template iot WHERE id >= 127 AND id <= 135;
SELECT * FROM player p WHERE name like '%daxanhle%';
SELECT * FROM account a WHERE a.id = 4652;
SELECT * from player p where p.name like '%zengaming%';
SELECT * FROM npc_template nt WHERE nt.NAME like '%thượng đế%';
select * from map_template mt WHERE mt.NAME like '%thần điện%';
SELECT * FROM tab_shop ts where ts.NAME like '%noel%';
SELECT * FROM tab_shop ts ORDER BY ts.id DESC;

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
SELECT * FROM item_option_template iot WHERE iot.id IN (132, 144);

