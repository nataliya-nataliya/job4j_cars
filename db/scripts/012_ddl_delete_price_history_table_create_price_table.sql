drop table price_history;

create table prices
(
   id      serial primary key,
   price   bigint not null,
   created timestamp without time zone default now() not null,
   post_id int references auto_posts(id)
);
