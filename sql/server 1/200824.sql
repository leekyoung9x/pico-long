-- 200824
-- Đóng Npc Popo ở làng aru
-- ban đầu:  ["[7,228,432,562]","[6,492,432,0]","[80,428,432,6578]","[67,524,432,2132]","[49,597,432,4544]"]
-- xóa npc popo id 67:  ["[7,228,432,562]","[6,492,432,0]","[80,428,432,6578]","[49,597,432,4544]"]
UPDATE map_template mt SET mt.npcs = '["[7,228,432,562]","[6,492,432,0]","[80,428,432,6578]","[49,597,432,4544]"]' WHERE mt.id = 0;
-- xóa npc daishinkan ở làng aru, làng kakarot
-- ban đầu ["[7,228,432,562]","[6,492,432,0]","[80,428,432,6578]","[49,597,432,4544]"]
UPDATE map_template mt SET mt.npcs = '["[7,228,432,562]","[6,492,432,0]","[49,597,432,4544]"]' WHERE mt.id = 0;
-- ban đầu ["[9,396,408,565]","[6,252,408,0]","[80,628,408,6578]"]
UPDATE map_template mt SET mt.npcs = '["[9,396,408,565]","[6,252,408,0]"]' WHERE mt.id = 14;

-- Tạo bảng lưu lần nhận quà tặng của người chơi

DROP TABLE IF EXISTS player_reward;
CREATE TABLE player_reward(
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  player_id int(11) NOT NULL,
  items text NULL,
  INDEX `player_id_index` (player_id)
);

-- Xóa NPC ông già ở làng Mori
-- ban đầu: ["[6,564,432,0]","[8,300,432,350]","[82,566,432,0]"]
UPDATE map_template mt SET mt.npcs = '["[6,564,432,0]","[8,300,432,350]"]' WHERE mt.id = 7;