create table cards (
    id BIGINT not null,
    description varchar(255),
    title varchar(255),
    user_id varchar(255),
    primary key (id)
);

create table notes (
    id BIGINT not null,
    description varchar(255),
    text varchar(255),
    title varchar(255),
    user_id varchar(255),
    primary key (id)
);

create table sets (
    id BIGINT not null,
    description varchar(255),
    title varchar(255),
    user_id varchar(255),
    primary key (id)
);

create table users (
    id BIGINT not null,
    first_name varchar(255),
    middle_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    primary key (id)
);
