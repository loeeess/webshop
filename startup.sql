CREATE TABLE user ()

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
 'https://cdn.webshopapp.com/shops/238922/files/311744048/fashion-click-scrunchie-leopard-velvet.jpg?_ga=2.114437378.400751633.1584288349-687456565.1584288349');

INSERT INTO user
VALUES
(DEFAULT, 'admin', 'admin@admin.com', 'admin', 'ADMIN'),
(DEFAULT, 'user', 'user@user.com', 'user', 'USER');

