DELIMITER //
DROP PROCEDURE IF EXISTS UpdateJsonPetBag;
CREATE PROCEDURE UpdateJsonPetBag ($is_update bit)
BEGIN
  DECLARE is_check bit DEFAULT 0;
  DECLARE i int DEFAULT 0;
  DECLARE $id int DEFAULT 0;
  DECLARE json_length int;
  DECLARE json_value json;

  -- Lặp qua tất cả các hàng trong bảng
  DECLARE done int DEFAULT 0;
  DECLARE cur CURSOR FOR
  SELECT
    id,
    pet_body
  FROM player FOR UPDATE;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

  DROP TEMPORARY TABLE IF EXISTS tbTop;
  CREATE TEMPORARY TABLE tbTop
  SELECT
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
  ORDER BY j.vnd DESC;

  DROP TEMPORARY TABLE IF EXISTS tbResult;
  CREATE TEMPORARY TABLE tbResult (
    id int,
    items_bag text
  );

  OPEN cur;

read_loop:
  LOOP
    FETCH cur INTO $id, json_value;
    IF done THEN
      LEAVE read_loop;
    END IF;

    -- Lấy độ dài của mảng JSON
    SET json_length = json_length(json_value);

    -- Lặp qua từng phần tử trong mảng
    WHILE i < json_length DO
      -- Truy xuất giá trị quantity và temp_id cho phần tử hiện tại
      IF JSON_EXTRACT(json_value, CONCAT('$[', i, '].temp_id')) = 14
        AND JSON_EXTRACT(json_value, CONCAT('$[', i, '].quantity')) >= 1 THEN
        -- Cập nhật temp_id thành -1 nếu temp_id = 457 và quantity > 1
        SET json_value = JSON_SET(json_value, CONCAT('$[', i, '].temp_id'), 2159);
        SET is_check = 1;
      END IF;

      IF JSON_EXTRACT(json_value, CONCAT('$[', i, '].temp_id')) = 15
        AND JSON_EXTRACT(json_value, CONCAT('$[', i, '].quantity')) >= 2 THEN
        -- Cập nhật temp_id thành -1 nếu temp_id = 457 và quantity > 1
        SET json_value = JSON_SET(json_value, CONCAT('$[', i, '].temp_id'), 2159);
        SET is_check = 1;
      END IF;

      IF JSON_EXTRACT(json_value, CONCAT('$[', i, '].temp_id')) = 16
        AND JSON_EXTRACT(json_value, CONCAT('$[', i, '].quantity')) >= 10 THEN
        -- Cập nhật temp_id thành -1 nếu temp_id = 457 và quantity > 1
        SET json_value = JSON_SET(json_value, CONCAT('$[', i, '].temp_id'), 2159);
        SET is_check = 1;
      END IF;

      IF JSON_EXTRACT(json_value, CONCAT('$[', i, '].temp_id')) IN (2053, 1007, 1254, 1172) THEN
        -- Cập nhật temp_id thành -1 nếu temp_id = 457 và quantity > 1
        SET json_value = JSON_SET(json_value, CONCAT('$[', i, '].temp_id'), 2159);
        SET is_check = 1;
      END IF;

      SET i = i + 1;
    END WHILE;

    IF (is_check = 1) THEN
      INSERT INTO tbResult (id, items_bag)
        VALUES ($id, json_value);
    END IF;

    -- Reset biến i cho lần lặp tiếp theo
    SET i = 0;

    IF $is_update = 1 AND is_check = 1 THEN
      -- Cập nhật lại cột JSON trong bảng
      UPDATE player a
      LEFT JOIN tbTop b
        ON a.id = b.id
      SET items_bag = json_value
      WHERE a.id = $id
      AND b.id IS NULL;
    END IF;

    SET is_check = 0;
  END LOOP;

  CLOSE cur;
  SELECT
    *
  FROM tbResult a
    LEFT JOIN tbTop b
      ON a.id = b.id
  WHERE b.id IS NULL;
  DROP TEMPORARY TABLE IF EXISTS tbResult;
  DROP TEMPORARY TABLE IF EXISTS tbTop;
END//

DELIMITER ;

CALL UpdateJsonPetBag(0);