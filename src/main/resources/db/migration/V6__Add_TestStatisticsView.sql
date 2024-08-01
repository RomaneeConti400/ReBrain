TRUNCATE TABLE test_statistics_view;

INSERT INTO test_statistics_view (user_id, set_id, test_count, average_score)
SELECT
    user_id AS userId,
    set_id AS setId,
    COUNT(*) AS testCount,
    COALESCE(AVG(correct_answers::decimal), 0) AS averageScore
FROM
    tests
GROUP BY
    user_id, set_id;
