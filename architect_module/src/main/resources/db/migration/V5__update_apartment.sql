ALTER TABLE apartment
    ADD photo_id int8 REFERENCES photo(id);