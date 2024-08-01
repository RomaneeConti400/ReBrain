-- 1. Создание функции для обновления представления
CREATE OR REPLACE FUNCTION update_test_statistics_view()
    RETURNS TRIGGER AS $$
BEGIN
    TRUNCATE TABLE test_statistics_view;

    INSERT INTO test_statistics_view (user_id, set_id, test_count, average_score)
    SELECT
        user_id AS userId,
        set_id AS setId,
        COUNT(*) AS testCount,
        COALESCE(AVG(correct_answers::decimal) * 100 / NULLIF(AVG(cards_number::decimal), 0), 0) AS averageScore
    FROM
        tests
    GROUP BY
        user_id, set_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- 2. Создание триггера, который будет запускать обновление представления
CREATE TRIGGER trigger_update_test_statistics_view
    AFTER INSERT OR UPDATE OR DELETE ON tests
    FOR EACH STATEMENT
EXECUTE FUNCTION update_test_statistics_view();

