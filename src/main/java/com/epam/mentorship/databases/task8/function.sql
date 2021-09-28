
create or replace function getAverageMarks(input_student_id int)
    RETURNS int LANGUAGE plpgsql as
$$
begin
    return sum(exam_result.mark)/count(exam_result.mark) as average_mark
    from public.students
    join exam_result on exam_result.student_id = students.id
    where (students.id = input_student_id);
end
$$;

--create or replace function getAverageMarks()
--    RETURNS int
--    LANGUAGE plpgsql as
--$$
--begin
--    return sum(exam_result.mark)/count(exam_result.mark) as average_mark
--    from public.students
--    join exam_result on exam_result.student_id = students.id;
--
--end
--$$;

SELECT getAverageMarks();