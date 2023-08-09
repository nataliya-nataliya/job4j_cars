create table participates
(
    id         serial primary key,
    user_id    int references auto_users (id) not null,
    post_id    int references auto_posts (id) not null
);
