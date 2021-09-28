
create or replace function getAverageMarksBySubjectName(input_subject varchar)
    RETURNS int LANGUAGE plpgsql as
$$
begin
    return sum(exam_result.mark) / count(exam_result.mark) as average_mark
    from students
    join exam_result on exam_result.student_id = students.id
        join subjects on exam_result.subject_id = subjects.id
                 where subjects.name = input_subject;
end
$$;



select getAverageMarksBySubjectName('physics')