create or replace function generateStudents()
    RETURNS int
    LANGUAGE plpgsql as
$$
DECLARE
    skills      TEXT[];
    users_count INTEGER;
    skill_size  INTEGER;
    skill_count INTEGER := 1;

begin
    skills := array ['math','english', 'physics', 'biology'::text];
    skill_size := 4;
    for i in 1..100000
        loop
            insert into students ("name", surname, date_of_birth, primary_skill, created_datetime, updated_datetime)
            values (concat('Yaroslav', i), concat('Hurniak', i),
                    concat('0', (RANDOM() * 9)::INT, (RANDOM() * 9)::INT,
                           (RANDOM() * 9)::INT, (RANDOM() * 9)::INT,
                           (RANDOM() * 9)::INT, (RANDOM() * 9)::INT,
                           (RANDOM() * 9)::INT, (RANDOM() * 9)::INT,
                           (RANDOM() * 9)::INT),
                    '2000-06-11', skills[skill_count], current_timestamp,
                    current_timestamp);
            if skill_count = skill_size then
                skill_count := 0;
            end if;
            skill_count := skill_count + 1;
            users_count := i;
        end loop;
    return users_count;
end
$$;