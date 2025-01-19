DELETE
  FROM item_template
  WHERE id = 2169;

INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES
  (2169, 32, 3, 'Giáp tập luyện cấp 4', 'Khi mặc vào sẽ đánh yếu hơn bình thường 40%, khi cởi ra sẽ tăng sức đánh 40%', 30783, -1, 0, 15000000);