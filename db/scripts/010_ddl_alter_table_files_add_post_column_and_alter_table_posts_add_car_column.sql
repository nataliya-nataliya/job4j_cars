alter table files add column post_id int references auto_posts(id);
alter table auto_posts add column car_id  int not null references cars(id);
