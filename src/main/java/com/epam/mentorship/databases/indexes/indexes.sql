create index surname_b_tree on students using btree(surname);
create index name_b_tree on students using btree(name);
create index phone_b_tree on students using btree(phone_number);


create index surname_hash on students using hash(surname);
create index name_hash on students using hash(name);
create index phone_hash on students using hash(phone_number);
