DELETE FROM item_template WHERE ID = 2166;
INSERT INTO item_template(id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES(2166, 27, 3, 'Hộp quà tháng 10', 'Hộp này có thể mở được Ván bay phượng hoàng lửa, Pet thỏ cam trung thu, Ngọc rồng xương, Ngọc xanh, Đá bảo vệ, Bình nước', 10852, -1, 0, 0);

-- reset point nạp
UPDATE account a SET a.pointNap = 0 WHERE 1 = 1;


