create table if not exists tests (
    id BIGINT not null,
    set_id BIGINT,
    user_id BIGINT,
    answers varchar(255),
    cards_number INT,
    wrong_answers INT,
    correct_answers INT,
    start_date timestamp,
    end_date timestamp,
    completion_time INT,
    primary key (id)
);