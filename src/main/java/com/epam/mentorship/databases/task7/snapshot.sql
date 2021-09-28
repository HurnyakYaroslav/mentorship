create  table students_and_marks_snapshot as
select students.name as name, students.surname, s.name as subject, er.mark
from students
         JOIN exam_result er on students.id = er.student_id
         JOIN subjects s on s.id = er.subject_id;