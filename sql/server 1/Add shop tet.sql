SELECT * FROM shop;
SELECT * FROM tab_shop;
SELECT * FROM item_shop;

delete from shop WHERE ID = 28;
INSERT INTO shop (ID, npc_id, shop_order)
  VALUES (28, 84, 0);

delete from tab_shop WHERE ID = 50;
INSERT INTO tab_shop (ID, shop_id, NAME)
  VALUES (50, 28, 'Cửa hàng<>Tết');
