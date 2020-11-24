CREATE TABLE users
(
    id          int PRIMARY KEY AUTO_INCREMENT,
    username    varchar(255) UNIQUE,
    first_name  varchar(255),
    last_name   varchar(255),
    email       varchar(255) UNIQUE,
    password    text,
    postal_code varchar(255),
    street      varchar(255),
    number      varchar(255),
    city        varchar(255),
    country     varchar(255),
    created_at  timestamp DEFAULT CURRENT_TIMESTAMP
);
