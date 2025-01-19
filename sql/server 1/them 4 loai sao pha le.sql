-- Thêm 4 loại sao pha lê
DELETE FROM item_template WHERE ID IN (2150);
INSERT INTO item_template (id, TYPE, gender, NAME, description, icon_id, part, is_up_to_up, power_require)
  VALUES 
  (2150, 30, 3, 'Sao pha lê TNSM (2150)', '', 12231, 0, 0, 0);
-- cập nhật tên 3 loại spl
UPDATE item_template it SET it.NAME = 'Sao pha lê siêu cấp HP (2118)' WHERE it.id = 2118;
UPDATE item_template it SET it.NAME = 'Sao pha lê siêu cấp KI (2119)' WHERE it.id = 2119;
UPDATE item_template it SET it.NAME = 'Sao pha lê siêu cấp SĐ (2120)' WHERE it.id = 2120;