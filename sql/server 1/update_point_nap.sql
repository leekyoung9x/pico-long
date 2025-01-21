DELETE FROM item_template WHERE ID = 2200;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2200, 27, 3, 'Đệ tử Whis', 'Yêu cầu: Sở hữu đệ Bill Level 7', 7679, -1, 0, 0);


DELETE FROM item_template WHERE ID = 2201;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2201, 27, 3, 'Năng lượng Bill rác', 'Vật phẩm sự kiện, tác dụng trong 10 phút', 4847, -1, 0, 0);

DELETE FROM item_template WHERE ID = 2202;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2202, 27, 3, 'Năng lượng Bill rác', 'Vật phẩm sự kiện, tác dụng trong 10 phút', 4847, -1, 0, 0);
DELETE FROM item_template WHERE ID = 2203;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2203, 27, 3, 'Năng lượng Bill rác', 'Vật phẩm sự kiện, tác dụng trong 10 phút', 4847, -1, 0, 0);
DELETE FROM item_template WHERE ID = 2204;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2204, 27, 3, 'Năng lượng Bill rác', 'Vật phẩm sự kiện, tác dụng trong 10 phút', 4847, -1, 0, 0);


DELETE FROM item_template WHERE ID = 2205;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2205, 27, 3, 'Năng lượng Bill', 'Vật phẩm sự kiện, tác dụng trong 10 phút', 4847, -1, 0, 0);



-- Giảm # giây thời gian bị mù ->  kháng nóng
UPDATE item_option_template iot SET iot.NAME = 'Kháng nóng' WHERE ID = 187;



-- Giảm #% thời gian bị mù ->  x# TNSM
UPDATE item_option_template iot SET iot.NAME = 'x# TNSM' WHERE ID = 175;


UPDATE item_option_template iot SET iot.NAME = 'Gái xink' WHERE iot.id = 183;




DELETE FROM item_template WHERE ID = 2206;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2206, 27, 3, 'Đệ tử Goku', 'Đệ tử vip siêu cấp thế giới ngọc rồng', 12273, -1, 0, 0);






-- test
SELECT * FROM item_template it ORDER BY it.id DESC;

SELECT * FROM item_template it WHERE it.id = 20;
SELECT * FROM item_template it WHERE it.NAME LIKE '%bánh chưng%';
SELECT * FROM item_template it WHERE it.NAME LIKE '%bill%';
SELECT * FROM item_template it WHERE it.TYPE = 27;

USE pico;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2199, 23, 3, 'Ván bay "Rồng thiên"', 'Vật phẩm sự kiện', 29154, 46, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2198, 27, 3, 'Túi Quà Bí Ngô', 'Giấu bên trong nhiều vật phẩm quý giá', 25015, -1, 0, 0);

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2197, 21, 3, 'Pet Rồng xương', 'Vật phẩm sự kiện', 29153, -1, 0, 0);


SELECT * FROM player p WHERE p.name LIKE '%thợ săn zeno%';
SELECT * FROM account a WHERE a.id = 3928;


SELECT * FROM item_option_template iot WHERE iot.id = 106;
SELECT * FROM item_option_template iot ORDER BY id DESC;
SELECT * FROM item_option_template iot WHERE iot.NAME LIKE '%zeno%';
SELECT * FROM item_template it ORDER BY it.id DESC;

SELECT * FROM player p;

SELECT * FROM account a WHERE a.id = 2606;

SELECT * FROM item_option_template iot ORDER BY iot.id DESC;

SELECT * FROM item_template WHERE NAME LIKE '%đệ tử whis%';

UPDATE player p SET p.items_body = '[{"quantity":1,"create_time":1729240952656,"temp_id":650,"option":[[47,292],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10],[187,0]]},{"quantity":1,"create_time":1729240952656,"temp_id":651,"option":[[6,175382],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":657,"option":[[0,19289],[249,0],[129,0],[141,150],[50,40],[102,10],[72,8],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":658,"option":[[7,194871],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1729240952656,"temp_id":656,"option":[[14,23],[249,0],[129,0],[141,150],[50,40],[102,10],[72,7],[30,0],[107,10]]},{"quantity":1,"create_time":1730274652916,"temp_id":2123,"option":[[5,125],[77,115],[103,115],[106,0],[50,40],[102,10],[107,10]]},{"quantity":0,"create_time":0,"temp_id":-1,"option":[]},{"quantity":1,"create_time":1732155020092,"temp_id":2196,"option":[[14,15],[50,55],[77,55],[103,55]]},{"quantity":1,"create_time":1730270665120,"temp_id":1148,"option":[[50,30],[77,30],[103,30],[244,5]]},{"quantity":1,"create_time":1729102545892,"temp_id":1107,"option":[[44,10],[50,50],[77,50],[103,50],[30,0]]},{"quantity":1,"create_time":1730992207664,"temp_id":2130,"option":[[50,25],[77,55],[103,55],[5,10],[30,0]]},{"quantity":1,"create_time":1729138218844,"temp_id":2075,"option":[[50,23],[21,40],[87,0],[237,5],[238,362],[30,0]]},{"quantity":1,"create_time":1731950295695,"temp_id":805,"option":[[169,10]]},{"quantity":1,"create_time":1729132174737,"temp_id":1327,"option":[[50,50],[77,50],[103,50]]},{"quantity":1,"create_time":1729101576441,"temp_id":2097,"option":[[43,20],[50,30],[77,30],[103,30],[30,0]]}]' WHERE p.id = 1240;

SELECT * FROM part p WHERE p.id = 1471;

SELECT * FROM part p WHERE p.part LIKE '%12273%';
SELECT * FROM part p WHERE p.id > 1325;