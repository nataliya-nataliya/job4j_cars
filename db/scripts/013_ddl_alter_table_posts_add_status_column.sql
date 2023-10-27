alter table auto_posts add column status   boolean default false not null;
alter table auto_users add column timezone varchar(50);
alter table auto_users add column name     varchar(50);

