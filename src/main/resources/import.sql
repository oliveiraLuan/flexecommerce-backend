INSERT INTO tb_category(name, created_At) VALUES ('Pneus', NOW());
INSERT INTO tb_category(name, created_At) VALUES ('Correia dentada', NOW());
INSERT INTO tb_category(name, created_At) VALUES ('Embreagens', NOW());

INSERT INTO tb_product(date, name, description, img_url, price) VALUES (NOW(), 'Kit correia dentada', 'Kit contendo a correia dentada e o tensor', '', 200.0)

INSERT INTO tb_user(first_name, last_name, email, password) VALUES ('Luan', 'de Oliveira', 'luan@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG')
INSERT INTO tb_user(first_name, last_name, email, password) VALUES ('Fabio', 'Cafe', 'maria@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_OPERATOR');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
