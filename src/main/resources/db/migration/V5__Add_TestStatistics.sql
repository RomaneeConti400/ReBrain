create table if not exists test_statistics_view (
    user_id BIGINT,
    set_id BIGINT,
    test_count BIGINT,
    average_score DECIMAL(5, 2)
);