create table if not exists schedules (
    id BIGINT not null,
    set_id BIGINT not null,
    user_id BIGINT not null,
    cron_expression varchar(100) not null,
    start_date DATE not null,
    primary key (id)
    );