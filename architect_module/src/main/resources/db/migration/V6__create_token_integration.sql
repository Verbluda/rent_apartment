CREATE TABLE IF NOT EXISTS integration_info (
                                     service_name_id varchar PRIMARY KEY,
                                     path_value varchar,
                                     token_value varchar
);

INSERT INTO integration_info (service_name_id, path_value, token_value)
VALUES ('GEO', 'https://api.opencagedata.com/geocode/v1/json?q=%s+%s&key=%s&language=ru', 'N2Y1ZGQ1Yzk3NDY1NDU0M2E3MjFlNGI0MzE0MDBkNjI='),
       ('WEATHER', 'https://api.weatherapi.com/v1/current.json?key=%s&q=%s,%s&lang=ru', 'ZDYyM2FkNjk3YmJiNDRiMTkwYjE0MjYzNzI0MjgxMA==');