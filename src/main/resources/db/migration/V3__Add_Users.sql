create table if not exists users (
    id BIGINT not null,
    first_name varchar(255) not null,
    middle_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null,
    primary key (id)
);
