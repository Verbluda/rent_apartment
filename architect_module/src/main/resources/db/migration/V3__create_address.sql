CREATE TABLE IF NOT EXISTS address (
                                         id int8 PRIMARY KEY,
                                         city varchar,
                                         street varchar,
                                         number_of_house varchar,
                                         number_of_apartment varchar,
                                         apartment_id int8 REFERENCES apartment(id)
);

CREATE SEQUENCE address_sequence START 2 INCREMENT 1;

INSERT INTO address (id, city, street, number_of_house, number_of_apartment, apartment_id)
VALUES
    (1, 'Москва', 'Садовая', '6', '56', 2);