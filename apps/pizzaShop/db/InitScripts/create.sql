CREATE TABLE `orders`
(
    `id`          int PRIMARY KEY AUTO_INCREMENT,
    `order_date`  timestamp not null ,
    `order_sent`  datetime,
    `postal_code` varchar(16) not null ,
    `street`      varchar(255) not null ,
    `number`      varchar(255) not null ,
    `city`        varchar(255) not null ,
    `country`     varchar(255) not null
);

CREATE TABLE `toppings`
(
    `id`   int PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) not null UNIQUE,
    `base_amount` int not null ,
    `unit` varchar(16) not null
);

CREATE TABLE `sizes`
(
    `id`            int PRIMARY KEY AUTO_INCREMENT,
    `diameter`        int not null UNIQUE,
    `base_price`    DECIMAL(6,2) UNSIGNED not null ,
    `topping_price` DECIMAL(6,2) UNSIGNED not null ,
    `dough_amount` NUMERIC not null ,
    `topping_factor` DECIMAL(6,2) UNSIGNED not null
);

CREATE TABLE `pizzas`
(
    `id`      int PRIMARY KEY AUTO_INCREMENT,
    `order_id` int not null ,
    `size_id` int not null
);

CREATE TABLE `pizza_topping`
(
    `pizza_id`   int,
    `topping_id` int,
    `amount`     int not null ,
    PRIMARY KEY (`pizza_id`, `topping_id`)
);

ALTER TABLE `pizzas`
    ADD FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`);

ALTER TABLE `pizzas`
    ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `pizza_topping`
    ADD FOREIGN KEY (`pizza_id`) REFERENCES `pizzas` (`id`);

ALTER TABLE `pizza_topping`
    ADD FOREIGN KEY (`topping_id`) REFERENCES `toppings` (`id`);

INSERT INTO sizes (diameter, base_price, topping_price, dough_amount, topping_factor) VALUES
(15, 7.5, 1, 100, 1), (18, 9, 1.3, 140, 1.3), (21, 12, 2, 200, 2), (25, 18, 3, 300, 3);

INSERT INTO toppings (name, base_amount, unit) VALUES
('Salami', 3, 'stk.'), ('Bacon', 2, 'stk.'), ('Mais', '15', 'g'), ('Oliven', 20, 'g')
