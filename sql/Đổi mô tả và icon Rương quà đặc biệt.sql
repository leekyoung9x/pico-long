UPDATE item_template it SET it.description = 'Hộp này có thể mở được cải trang Sói Basil 100% sức đánh chí mạng đếy!', it.icon_id = 24010 WHERE it.id = 2122;

-- Thêm hộp quà tích lũy
DELETE FROM item_template WHERE ID = 2160;
INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES (2160, 27, 3, 'Hộp quà tích lũy', 'Hộp này có thể mở được Thỏi vàng VIP, đồ thần linh, SKH VIP, đá bảo vệ, Ván bay hoa đăng', 11202, -1, 0, 0);

-- cập nhật description của ván bay hoa đăng
UPDATE item_template it SET it.description = 'Tâm phải tịnh' WHERE it.id = 2133;

