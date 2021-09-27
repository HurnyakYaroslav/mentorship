create trigger UpdateUpdated
after update on students
execute procedure update_updated_field();


CREATE OR REPLACE FUNCTION update_updated_field() RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_datetime = CURRENT_TIMESTAMP;
    RETURN NEW;
END; $$