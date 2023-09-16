alter table auto_posts add column file_id int references files(id);
alter table auto_posts add column car_id  int not null references cars(id);

