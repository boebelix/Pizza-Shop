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
    `name` varchar(255) not null ,
    `base_amount` int not null ,
    `unit` varchar(16) not null
);

CREATE TABLE `sizes`
(
    `id`            int PRIMARY KEY AUTO_INCREMENT,
    `radius`        int not null ,
    `base_price`    NUMERIC(2) not null ,
    `topping_price` NUMERIC(2) not null ,
    `dough_amount` NUMERIC not null ,
    `topping_factor` NUMERIC(2) not null
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

