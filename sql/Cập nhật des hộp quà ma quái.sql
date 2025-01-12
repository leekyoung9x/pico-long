-- cập nhật des
UPDATE item_template it SET it.description = 'Hộp này có thể mở được Pet gà chín cựa, Cải trang Gogeta SSJ4, ngọc xanh, hồng ngọc, đá bảo vệ, bình nước' WHERE it.id = 2161;


DELETE FROM head_avatar WHERE head_id = 1469;
INSERT INTO head_avatar (head_id, avatar_id)
  VALUES (1469, 5073);





-- test