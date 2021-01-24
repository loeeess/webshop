CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	account_username VARCHAR(255),
	account_email VARCHAR(255),
	account_password VARCHAR(255),
	account_role VARCHAR(255)
);

CREATE TABLE product (
	id SERIAL PRIMARY KEY,
    product_name VARCHAR(255),
    product_price NUMERIC(10,2),
    product_description VARCHAR(1024),
    product_image VARCHAR(1024)
);

INSERT INTO product
VALUES
(DEFAULT, 'Fjällräven Kånken Backpack', 69.99, 'Iconic Kanken backpack by Fjallraven is a Scandinavian design classic dating back to 1978.', 'https://www.duifhuizen.nl/media/catalog/product/cache/0/image/a4b764e5482f62fb51dae89ed3917009/2/3/23510-kanken-black_1_.jpg'),
(DEFAULT, 'Dopper bottle', 
 12.50, 
 'Dopper is empowering people to choose reusable over single-use water bottles, to protect our world''s waters.', 
 'https://waterfleswinkel.nl/image/cache/data/Productafbeeldingen/Dopper/Dopper%20Pink%20Paradise%20kopie-750x750.jpg'),
(DEFAULT, 'Scrunchie', 
 4.95, 
 'An elegant hair tie made with a lustrous satin fabric.', 
 'https://cdn.webshopapp.com/shops/238922/files/311744048/fashion-click-scrunchie-leopard-velvet.jpg?_ga=2.114437378.400751633.1584288349-687456565.1584288349'),
 (DEFAULT, 'Fujifilm Instax Mini 9', 
 89.90, 
 'Snap instant, retro-inspired photos and selfies with the Fujifilm Instax Mini 9 instant camera.', 
 'https://www.partydeco.nl/media/cache/catalog/product/0/9/700x700_co_ar_tr_fr_bc_100/094-011_instax_mini_9_ice_blue.jpg'),
 (DEFAULT, 'Mario Badescu Facial Spray', 
 10.00, 
 'Mario Badescu Facial Spray with Aloe, Cucumber, and Green Tea rehydrates, invigorates, and tones.', 
 'https://www.themakeupspot.nl/media/image/d9/07/9d/Mario-Badescu-Rosewater-Spray-118ml_600x600.jpg'),
 (DEFAULT, 'Vans Old Skool Sneakers', 
 79.99, 
 'They say true style never goes out of fashion, and the Vans Old Skool is a staple trainer for any collection.', 
 'https://images.vans.com/is/image/VansEU/VN000D3HY28-HERO?$PDP-FULL-IMAGE$'),
 (DEFAULT, 'Art Socks: The Great Wave', 13.95, 'Show your love for art and the ocean with these fun and cool socks.', 
 'https://cdn.shopify.com/s/files/1/0028/8410/8401/products/The_Great_Wave_off_Kanagawa_1200x1200.jpg?v=1575401141'),
 (DEFAULT, 'Burt''s Bees Lip Balm', 4.95, 'Burt''s Bees Moisturizing Lip Balm, rejuvenates and beautifies lips naturally with a sweet flavor.',
 'https://media.douglas-shop.com/sgf/nl/523942/900_0/Burt_s_Bees-Lippen-Beeswax_Lip_Balm.jpg?imwidth=720'),
 (DEFAULT, 'Tony Moly Aloe Face Mask', 6.95, 'I''m Real Aloe Mask Sheet Water type essence mask provide quick moisture to skin.',
 'https://images.ulta.com/is/image/Ulta/2292433?op_sharpen=1&resMode=bilin&qlt=85&wid=800&hei=800&fmt=jpg'),
 (DEFAULT, 'Tyler, The Creator - Flower Boy CD', 13.95, 'Flower Boy is the fourth studio album by American rapper Tyler, the Creator.',
 'https://upload.wikimedia.org/wikipedia/en/c/c3/Tyler%2C_the_Creator_-_Flower_Boy.png'),
 (DEFAULT, 'Wildflower Blue Phone Case', 22.50, 'Don''t you know, plaid goes with EVERYTHING, especially on phones.', 
'https://di2ponv0v5otw.cloudfront.net/posts/2018/07/23/5b567bf645c8b3e7d778d6a1/m_5b567c7a800deeef736db59e.jpg'),
(DEFAULT, 'AirPods Case Pink Silicone', 9.95, 'Ultra Lightweight Protective Case For Your Apple AirPods.', 
'https://ae01.alicdn.com/kf/HTB1fXDTbEKF3KVjSZFEq6xExFXaK.jpg');

/*
Please note that these accounts are made for testing purposes. These username/password combinations are not recommended due to a lack of security.
*/
INSERT INTO account
VALUES
(DEFAULT, 'admin', 'admin@admin.com', 'admin', 'ADMIN'),
(DEFAULT, 'user', 'user@user.com', 'user', 'USER');

