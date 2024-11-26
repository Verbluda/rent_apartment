CREATE TABLE IF NOT EXISTS apartment (
                                         id int8 PRIMARY KEY,
                                         is_available boolean,
                                         number_of_room int4,
                                         price_per_day double precision
);

CREATE SEQUENCE apartment_sequence START 3 INCREMENT 1;

INSERT INTO apartment (id, is_available, number_of_room, price_per_day)
VALUES
    (1, true, 3, 7000.00),
    (2, true, 6, 15000.00);