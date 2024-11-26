CREATE TABLE IF NOT EXISTS photo (
                                       id int8 PRIMARY KEY,
                                       photo_of_apartment bytea
);

CREATE SEQUENCE photo_sequence START 1 INCREMENT 1;