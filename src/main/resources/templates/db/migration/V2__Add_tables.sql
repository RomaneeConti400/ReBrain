create table cards (
    id BIGINT not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

create table notes (
    id BIGINT not null,
    description varchar(255),
    text varchar(255),
    title varchar(255),
    primary key (id)
);

create table sets (
    id BIGINT not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

