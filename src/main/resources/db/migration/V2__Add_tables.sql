create table if not exists cards (
    id BIGINT not null,
    description varchar(255) not null,
    title varchar(255) not null,
    user_id BIGINT not null,
    primary key (id)
);

create table if not exists notes (
    id BIGINT not null,
    description varchar(255),
    text varchar(255) not null,
    title varchar(255) not null,
    user_id BIGINT not null,
    primary key (id)
);

create table if not exists sets (
    id BIGINT not null,
    description varchar(255),
    title varchar(255) not null,
    user_id BIGINT not null,
    primary key (id)
);

