create table if not exists schedules (
    id BIGINT not null,
    set_id BIGINT not null,
    user_id BIGINT not null,
    start_date date not null,
    primary key (id)
    );

create table if not exists schedule_repeats (
    schedule_id BIGINT not null,
    repeats varchar(7) not null, /* 31 1200 - date and time */
    foreign key (schedule_id) references schedules(id)
    );