delete FROM item_option_template
WHERE id IN (250, 251);

UPDATE item_option_template
SET NAME = 'Chỉ số kích hoạt ẩn tăng 15% SĐ, HP, KI'
WHERE id = 249;