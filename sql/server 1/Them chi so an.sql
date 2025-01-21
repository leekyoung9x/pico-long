UPDATE item_template
SET icon_id = 13960, NAME = 'Set kích hoạt VIP', description = 'Mở hộp này để có cơ hội nhận được set kích hoạt chỉ số ẩn VIP'
WHERE id = 2013;

DELETE FROM item_option_template
WHERE ID IN (249, 250, 251);

INSERT INTO item_option_template (ID, NAME, TYPE)
  VALUES (249, 'Chỉ số kích hoạt ẩn tăng 15% SĐ', 0);

INSERT INTO item_option_template (ID, NAME, TYPE)
  VALUES (250, 'Chỉ số kích hoạt ẩn tăng 15% HP', 0);

INSERT INTO item_option_template (ID, NAME, TYPE)
  VALUES (251, 'Chỉ số kích hoạt ẩn tăng 15% KI', 0);