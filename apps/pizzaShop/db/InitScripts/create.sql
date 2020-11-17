CREATE TABLE `orders` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `order_date` timestamp,
                          `order_sent` datetime,
                          `postal_code` varchar(16),
    `street` varchar(255),
    `number` varchar(255),
    `city` varchar(255),
    `country`varchar(255)
    );

CREATE TABLE `toppings` (
                            `id` int PRIMARY KEY AUTO_INCREMENT,
                            `name` varchar(255)
    );

CREATE TABLE `sizes` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `radius` int,
                         `base_price` decimal(4,4),
    `topping_price` decimal(4,4)
    );

CREATE TABLE `pizzas` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `size_id` int
);

CREATE TABLE `pizza_topping` (
                                 `pizza_id` int,
                                 `topping_id` int,
                                 `amount` int,
                                 PRIMARY KEY (`pizza_id`, `topping_id`)
    );

CREATE TABLE `pizza_order` (
                               `order_id` int,
                               `pizza_id` int,
                               PRIMARY KEY (`order_id`, `pizza_id`)
    );

ALTER TABLE `pizzas` ADD FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`);

ALTER TABLE `pizza_topping` ADD FOREIGN KEY (`pizza_id`) REFERENCES `pizzas` (`id`);

ALTER TABLE `pizza_topping` ADD FOREIGN KEY (`topping_id`) REFERENCES `toppings` (`id`);

ALTER TABLE `pizza_order` ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

ALTER TABLE `pizza_order` ADD FOREIGN KEY (`pizza_id`) REFERENCES `pizzas` (`id`);

