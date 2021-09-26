create or replace function generateSubjects()
    RETURNS int
    LANGUAGE plpgsql as
$$
DECLARE
    skills      TEXT[];
    subjects_count INTEGER;
    skill_size  INTEGER;
    skill_count INTEGER := 1;

begin
    skills := array ['math','english', 'physics', 'biology'::text];
    skill_size := 4;
    for i in 1..1000
        loop
            insert into subjects ("name", tutor)
            values (skills[skill_count], concat('Tutor', i));
            if skill_count = skill_size then
                skill_count := 0;
            end if;
            skill_count := skill_count + 1;
            users_count := i;
        end loop;
    return users_count;
end
$$;