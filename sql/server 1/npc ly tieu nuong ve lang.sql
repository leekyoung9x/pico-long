﻿-- làng aru
-- cũ ["[7,228,432,562]","[6,492,432,0]","[49,597,432,4544]"]
UPDATE map_template mt SET mt.npcs = '["[7,228,432,562]","[6,492,432,0]","[49,597,432,4544]","[54,428,432,6578]"]' WHERE id = 0;

-- làng mori
-- cũ ["[6,564,432,0]","[8,300,432,350]"]
UPDATE map_template mt SET mt.npcs = '["[6,564,432,0]","[8,300,432,350]","[54,428,432,6578]"]' WHERE id = 7;


-- làng kakarot
-- cũ ["[9,396,408,565]","[6,252,408,0]"]
UPDATE map_template mt SET mt.npcs = '["[9,396,408,565]","[6,252,408,0]","[54,628,408,6578]"]' WHERE id = 14;
