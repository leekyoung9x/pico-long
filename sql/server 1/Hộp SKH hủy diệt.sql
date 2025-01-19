DELETE FROM item_template WHERE ID = 2167;
INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2167, 27, 3, 'Hộp set Kích Hoạt Hủy Diệt', 'Chọn tùy thích 1 set kích hoạt mà bạn muốn', 11201, -1, 0, 0);




-- sửa lỗi pet Pet Bí Ma Vương
UPDATE item_template it SET it.TYPE = 21 WHERE it.id = 1107;


