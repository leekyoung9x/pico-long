DELETE
  FROM item_template
  WHERE id = 2172;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2172, 27, 3, 'Đá ngũ sắc VIP', 'Loại đá quý hiếm nhất trong thế giới Ngọc Rồng. Dùng để đục lỗ để gắn Sao Pha Lê cho mọi loại trang bị kể cả cải trang. Dùng để mua những cải trang cực hiếm. Có thể nhặt được khi đánh quái, với tỉ lệ rất thấp. Hãy gặp Thần Hủy Diệt để sử dụng loại đá này.', 27021, -1, 0, 0);