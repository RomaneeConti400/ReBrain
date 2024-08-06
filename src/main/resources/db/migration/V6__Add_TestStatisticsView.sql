CREATE VIEW test_statistics AS
SELECT
    user_id AS user_id,
    set_id AS set_id,
    COUNT(*) AS test_count,
    CASE
        WHEN AVG(correct_answers::decimal) IS NOT NULL
            AND AVG(cards_number::decimal) IS NOT NULL
            AND AVG(cards_number::decimal) != 0
            THEN AVG(correct_answers::decimal) * 100 / AVG(cards_number::decimal)
        ELSE NULL
        END AS average_score
FROM
    tests
GROUP BY
    user_id, set_id;