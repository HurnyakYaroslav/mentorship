insert into students (name, surname, date_of_birth, phone_number, primary_skill, created_datetime,
                  updated_datetime)
values
('maksym', 'pavlyshmen', '1960-01-17', '0983432832',
         'TEST', now(), now()),
('taras', 'pavlyshmen', '1960-01-17', '0983416832',
         'TEST', now(), now()),
('nazar', 'pavlyshmen', '1960-01-17', '0983422832',
         'TEST', now(), now());


insert into subjects (name, tutor)
values
('math', 'pavlyshmen'),
('english', 'klyshmen');
