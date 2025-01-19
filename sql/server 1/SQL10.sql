
DROP TABLE IF EXISTS transaction_card;

CREATE TABLE transaction_card (
  id int(11) NOT NULL AUTO_INCREMENT,
  player_id int DEFAULT NULL,
  request_id text DEFAULT NULL,
  status int(11) DEFAULT NULL,
  amount int(11) DEFAULT NULL,
  amount_real int(11) DEFAULT NULL,
  seri text DEFAULT NULL,
  code text DEFAULT NULL,
  card_type text DEFAULT NULL,
  time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (id)
);