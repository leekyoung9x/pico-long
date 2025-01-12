SELECT
  c.id,
  c.username,
  c.password,
  COALESCE(d.value, 0) AS `tong_nap`,
  CONCAT("UPDATE player SET items_bag = REPLACE(REPLACE(REPLACE(REPLACE(items_bag, '102,9', '102,7'), '107,9', '107,7'), '102,8', '102,7'), '107,8', '107,7'), items_body = REPLACE(REPLACE(REPLACE(REPLACE(items_body, '102,9', '102,7'), '107,9', '107,7'), '102,8', '102,7'), '107,8', '107,7'), items_box = REPLACE(REPLACE(REPLACE(REPLACE(items_box, '102,9', '102,7'), '107,9', '107,7'), '102,8', '102,7'), '107,8', '107,7'), pet_body = REPLACE(REPLACE(REPLACE(REPLACE(items_box, '102,9', '102,7'), '107,9', '107,7'), '102,8', '102,7'), '107,8', '107,7') WHERE id = ", c.id, ';') AS query
FROM (SELECT
    a.id,
    b.username,
    b.password,
    --   ,
    SUBSTRING_INDEX (SUBSTRING_INDEX (data_inventory, ',', 2), ',', -1) AS gem,
    SUBSTRING_INDEX (SUBSTRING_INDEX (data_inventory, ',', 3), ',', -1) AS ruby
  --   items_bag
  FROM player a
    INNER JOIN account b
      ON a.account_id = b.id
  WHERE
  --   (SUBSTRING_INDEX (SUBSTRING_INDEX (data_inventory, ',', 2), ',', -1) >= 1000000
  --   OR SUBSTRING_INDEX (SUBSTRING_INDEX (data_inventory, ',', 3), ',', -1) >= 1000000)
  -- OR 
  (
  -- items_bag LIKE '%1172%'
  -- OR items_bag LIKE '%1254%'
  -- OR items_bag LIKE '%2083%'
  items_bag LIKE '%102,8%'
  OR items_bag LIKE '%102,9%'
  OR items_body LIKE '%102,8%'
  OR items_body LIKE '%102,9%'
  OR items_box LIKE '%102,8%'
  OR items_box LIKE '%102,9%'
  OR pet_body LIKE '%102,8%'
  OR pet_body LIKE '%102,9%'
  )) c
  LEFT JOIN (SELECT
      i.id,
      i.name,
      j.vnd AS value
    FROM player i
      INNER JOIN (SELECT
          a.player_id,
          SUM(a.amount) AS vnd
        FROM (SELECT
            player_id,
            amount
          FROM transaction_banking
          WHERE is_recieve = 1
          UNION ALL
          SELECT
            player_id,
            amount_real
          FROM transaction_card
          WHERE status IN (1, 2)) a
        GROUP BY a.player_id
        ORDER BY vnd DESC) j
        ON i.id = j.player_id
    ORDER BY j.vnd DESC) d
    ON c.id = d.id
WHERE COALESCE(d.value, 0) = 0;