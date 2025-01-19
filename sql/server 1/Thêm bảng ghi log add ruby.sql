DROP TABLE IF EXISTS player_ruby_history;
CREATE TABLE player_ruby_history(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  player_id int(11) NULL,
  old_ruby int(11) NULL,
  new_ruby int(11) NULL,
  function_name varchar(100) NULL,
  created_date datetime NULL
);
