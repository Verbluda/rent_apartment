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

-- Функция логирования
CREATE OR REPLACE FUNCTION log_apartment_registration_process()
RETURNS TRIGGER as $$
    BEGIN
    INSERT INTO logs_apartment_registration_info (operation_type, logs_message)
    VALUES ('INSERT', 'зарегистрированы новые апартаменты по адресу: ' || NEW.city);
    RETURN NEW;
    END;
    $$ language plpgsql;

-- Триггер для таблицы Адрес
CREATE TRIGGER log_address_trigger
    AFTER INSERT ON address
    FOR EACH ROW EXECUTE FUNCTION log_apartment_registration_process();