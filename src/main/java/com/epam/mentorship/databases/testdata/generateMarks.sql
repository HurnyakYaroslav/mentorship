create or replace function generatemarks() returns integer
    language plpgsql
as
$$
DECLARE
    marks_count   INTEGER;
    students_size INTEGER;
    subjects_size INTEGER;
begin
    students_size = (SELECT COUNT(*) from students);
    subjects_size = (SELECT COUNT(*) from subjects);
    for i in 1..1000000
        loop
            insert into exam_result (mark, student_id, subject_id)
            values (floor(random() * 99 + 1)::int,
                    floor(random() * students_size + 1)::int,
                    floor(random() * subjects_size + 1)::int);
            marks_count := i;
        end loop;
    return marks_count;
end
$$;