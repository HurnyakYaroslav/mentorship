explain analyse select students.name as name, surname as surname, date_of_birth
                                     as date_of_birth, phone_number, er.mark as mark
                from students
                         join exam_result er on students.id = er.student_id
                         where surname like ('%333');