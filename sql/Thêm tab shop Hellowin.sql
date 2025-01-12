DELETE FROM tab_shop WHERE ID = 47;
INSERT INTO tab_shop (id, shop_id, NAME)
  VALUES (47, 9, 'Shop<>Hallowin');




-- Thêm hộp kẹo ma quái
DELETE FROM item_template WHERE ID = 2162;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) 
VALUES(2162, 27, 3, 'Hộp kẹo ma quái', 'Hộp này có thể mở được kẹo ma quái, 1 hộp SKH VIP, Bánh thịt người, Sách phép thuật, Ngọc rồng đen từ 1 -> 7 sao', 11719, -1, 0, 0);

-- Kẹo ma quái
DELETE FROM item_template WHERE ID = 2163;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) 
VALUES(2163, 27, 3, 'Kẹo ma quái', 'Khi sử dụng item kẹo ma quái này trong vòng 10 phút player sẽ có thể gây dame tối đa là -3 lên boss Ma Xương', 11714, -1, 0, 0);

-- Bánh thịt người
DELETE FROM item_template WHERE ID = 2164;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) 
VALUES(2164, 27, 3, 'Bánh thịt người', 'Khi sử dụng item Bánh thịt người trong vòng 10 phút player sẽ được tăng 10% sức đánh,Hp,Ki', 11718, -1, 0, 0);


-- Sách phép thuật
DELETE FROM item_template WHERE ID = 2165;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) 
VALUES(2165, 27, 3, 'Sách phép thuật', 'Hello Win', 11717, -1, 0, 0);




-- test