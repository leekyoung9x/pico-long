UPDATE player a LEFT JOIN (SELECT
      i.id,
      i.NAME,
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
    ORDER BY j.vnd DESC) b ON a.id = b.id
SET data_inventory = JSON_SET(data_inventory, '$[2]', 1500000)
WHERE JSON_EXTRACT(data_inventory, '$[2]') > 2500000
AND b.id is NULL;