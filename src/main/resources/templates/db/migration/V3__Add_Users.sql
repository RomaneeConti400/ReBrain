create table if not exists users (
    id BIGINT not null,
    first_name varchar(255),
    middle_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    primary key (id)
);
