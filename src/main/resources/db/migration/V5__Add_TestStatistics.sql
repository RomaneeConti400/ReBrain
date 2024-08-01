create table if not exists test_statistics_view (
    id BIGINT not null,
    user_id BIGINT,
    set_id BIGINT,
    test_count BIGINT,
    average_score DECIMAL(5, 2),
    primary key (id)
);