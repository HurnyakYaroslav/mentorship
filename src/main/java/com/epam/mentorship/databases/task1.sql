create table if not exists students
(
    id            serial primary key,
    name          VARCHAR(20),
    surname       varchar(20),
    date_of_birth date,
    phone_number  varchar(11),
    primary_skill varchar(11),
    created_datetime date,
    updated_datetime date
);

create table if not exists subjects
(
    id            serial primary key,
    name          VARCHAR(20),
    tutor         varchar(20)
);

create table if not exists exam_result
(
    id            serial primary key,
    student_id    int,
    subject_id    int,
    mark          int,

    constraint fk_student
        foreign key (student_id)
            references students (id),

    constraint fk_subject
        foreign key (subject_id)
            references subjects (id)
);

