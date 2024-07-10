create sequence hibernate_sequence start 1 increment 1;

create table cards (
    id int8 not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

create table notes (
    id int8 not null,
    description varchar(255),
    text varchar(255),
    title varchar(255),
    primary key (id)
);

create table sets (
    id int8 not null,
    description varchar(255),
    title varchar(255),
    primary key (id)
);

/*
alter table if exists message
    add constraint message_user_fk
    foreign key (user_id) references usr;

alter table if exists user_role
    add constraint user_role_user_fk
    foreign key (user_id) references usr;
*/