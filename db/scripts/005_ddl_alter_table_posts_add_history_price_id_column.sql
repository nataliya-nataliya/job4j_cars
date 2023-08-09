alter table auto_posts
add column price_history_id int not null references price_history(id);
