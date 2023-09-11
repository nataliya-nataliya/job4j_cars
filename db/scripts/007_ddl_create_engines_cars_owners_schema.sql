create table engines
(
    id       serial primary key,
    name     varchar(50)
);

create table cars
(
    id        serial primary key,
    name      varchar(50),
    engine_id int not null unique references engines (id) not null
);

create table owners
(
    id        serial primary key,
    name      varchar(50),
    user_id   int not null unique references auto_users (id)
);

create table cars_owners
(
    id        serial primary key,
    car_id    int references cars (id) not null,
    owner_id  int references owners (id) not null,
    start_own timestamp without time zone default now(),
    end_own   timestamp without time zone default now(),
    unique (car_id, start_own)
);
