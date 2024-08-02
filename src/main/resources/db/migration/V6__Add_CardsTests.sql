create table if not exists cards_sets (
    card_id BIGINT not null,
    set_id BIGINT not null,
    primary key (card_id, set_id)
);