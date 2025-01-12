DROP TABLE IF EXISTS open_box;

CREATE TABLE open_box (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  box_id int,
  template_id_from int,
  template_id_to int,
  rate int,
  quantity_from int,
  quantity_to int
);

DROP TABLE IF EXISTS open_box_option;

CREATE TABLE open_box_option (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  open_box_id int,
  option_id int,
  option_value_from int,
  option_value_to int
);