CREATE TABLE IF NOT EXISTS user_info (
    id int8 PRIMARY KEY,
    user_name varchar,
    email varchar,
    password varchar,
    token varchar,
    date_registration timestamp
);

CREATE SEQUENCE user_info_sequence START 2 INCREMENT 1;

INSERT INTO user_info (id, user_name, email, password, token, date_registration)
VALUES
    (1, 'test_name', 'violentvioletlucy@yandex.ru', 'dGVzdF9wYXNzd29yZA==', '2b1c8337-bd48-47d6-8845-297fde070ff1|2034-09-05T16:20:58.320015900', null);