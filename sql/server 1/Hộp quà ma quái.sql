DELETE FROM item_template WHERE ID = 2161;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2161, 27, 3, 'Hộp quà ma quái', 'Hộp này có thể mở được Cải trang Gogeta SSJ4 HSD 1 ngày hoặc Vĩnh viễn, ngọc xanh, hồng ngọc, đá bảo vệ, bình nước', 11006, -1, 0, 0);





-- thêm shop whis shop đồ thiên sứ VIP
DELETE FROM shop WHERE ID = 26;
INSERT INTO shop (id, npc_id, shop_order)
  VALUES (26, 56, 1);

-- thêm tab shop
DELETE FROM tab_shop WHERE id IN (44, 45, 46);
INSERT INTO tab_shop(id, shop_id, NAME) VALUES(44, 26, 'Trái Đất');
INSERT INTO tab_shop(id, shop_id, NAME) VALUES(45, 26, 'Namếc');
INSERT INTO tab_shop(id, shop_id, NAME) VALUES(46, 26, 'Xayda');

-- thêm item vào shop
-- ===============Trái đất
DELETE FROM item_shop WHERE ID IN (9885, 9886, 9887, 9888, 9889);
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9885, 44, 1048, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:48:08');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9886, 44, 1051, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:49:06');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9887, 44, 1054, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:49:52');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9888, 44, 1057, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:50:29');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9889, 44, 1060, 0, 500000, 0, 1, -1, 0, '2023-09-14 21:50:58');

DELETE FROM item_shop_option WHERE item_shop_id IN (9885, 9886, 9887, 9888, 9889);
-- thêm option
-- áo thiên sứ - 2500 giáp
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9885, 47, 2500);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9885, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9885, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9885, 30, 0);
-- quần thiên sứ - 150k HP
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9886, 22, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9886, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9886, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9886, 30, 0);
-- Găng thiên sứ - 15000 tấn công
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9887, 0, 15000);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9887, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9887, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9887, 30, 0);
-- giày thiên sứ - 150k KI
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9888, 23, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9888, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9888, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9888, 30, 0);
-- Nhẫn thiên sứ - 22% chí mạng
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9889, 14, 22);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9889, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9889, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9889, 30, 0);

-- ================Namec
DELETE FROM item_shop WHERE ID IN (9890, 9891, 9892, 9893, 9894);
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9890, 45, 1049, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:48:08');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9891, 45, 1052, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:49:06');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9892, 45, 1055, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:49:52');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9893, 45, 1058, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:50:29');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9894, 45, 1061, 0, 500000, 0, 1, -1, 0, '2023-09-14 21:50:58');


DELETE FROM item_shop_option WHERE item_shop_id IN (9890, 9891, 9892, 9893, 9894);
-- thêm option
-- áo thiên sứ - 2500 giáp
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9890, 47, 2500);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9890, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9890, 142, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9890, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9890, 30, 0);
-- quần thiên sứ - 150k HP
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9891, 22, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9891, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9891, 142, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9891, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9891, 30, 0);
-- Găng thiên sứ - 15000 tấn công
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9892, 0, 15000);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9892, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9892, 142, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9892, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9892, 30, 0);
-- giày thiên sứ - 150k KI
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9893, 23, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9893, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9893, 142, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9893, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9893, 30, 0);
-- Nhẫn thiên sứ - 22% chí mạng
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9894, 14, 22);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9894, 253, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9894, 142, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9894, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9894, 30, 0);


-- ================Xayda
DELETE FROM item_shop WHERE ID IN (9895, 9896, 9897, 9898, 9899);
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9895, 46, 1050, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:48:08');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9896, 46, 1053, 0, 2000000, 0, 1, -1, 0, '2023-09-14 21:49:06');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9897, 46, 1056, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:49:52');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9898, 46, 1059, 0, 1000000, 0, 1, -1, 0, '2023-09-14 21:50:29');
INSERT INTO item_shop(id, tab_id, temp_id, gold, gem, is_new, is_sell, item_exchange, quantity_exchange, create_time) VALUES(9899, 46, 1062, 0, 500000, 0, 1, -1, 0, '2023-09-14 21:50:58');

DELETE FROM item_shop_option WHERE item_shop_id IN (9895, 9896, 9897, 9898, 9899);
-- thêm option
-- áo thiên sứ - 2500 giáp
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9895, 47, 2500);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9895, 138, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9895, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9895, 30, 0);
-- quần thiên sứ - 150k HP
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9896, 22, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9896, 138, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9896, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9896, 30, 0);
-- Găng thiên sứ - 15000 tấn công
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9897, 0, 15000);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9897, 138, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9897, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9897, 30, 0);
-- giày thiên sứ - 150k KI
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9898, 23, 150);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9898, 138, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9898, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9898, 30, 0);
-- Nhẫn thiên sứ - 22% chí mạng
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9899, 14, 22);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9899, 138, 15);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9899, 21, 101);
INSERT INTO item_shop_option(item_shop_id, option_id, param) VALUES(9899, 30, 0);





-- thêm 3 option cho đồ TL VIP
DELETE FROM item_option_template WHERE ID IN (253);
INSERT INTO item_option_template(id, NAME, TYPE) VALUES(253, '(5 món +#% sức đánh)', 0);






-- Cập nhật lại điểm tích lũy về 0
UPDATE account a SET a.pointNap = 0 WHERE 1 = 1;
