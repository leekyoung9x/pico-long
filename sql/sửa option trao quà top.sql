UPDATE item_option_template iot SET NAME = 'Tăng #% sức đánh chí mạng nếu sở hữu đệ Whis 150 tỷ sức mạnh' WHERE iot.id = 41;
UPDATE item_option_template iot SET NAME = 'Tăng #% Chỉ số nếu sở hữu đệ Whis 120 tỷ sức mạnh' WHERE iot.id = 45;

-- thêm hộp mở BTC 3
DELETE FROM item_template WHERE ID = 2168;
INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require) VALUES
(2168, 27, 3, 'Hộp bông tai cấp 3', 'Chọn tùy thích chỉ số mà bạn muốn', 10852, -1, 0, 0);







