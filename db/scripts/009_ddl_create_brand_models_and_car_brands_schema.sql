create table car_brands
(
    id         serial  primary key,
    name       varchar not null
);

create table car_models
(
    id         serial primary key,
    name       varchar not null
);

create table files
(
    id   serial primary key,
    name varchar not null,
    path varchar not null unique
);
