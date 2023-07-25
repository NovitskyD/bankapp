CREATE TABLE IF NOT EXISTS transactions (
    id UUID DEFAULT uuid_generate_v4(),
    sender_card_id UUID,
    recipient_card_id UUID,
    type VARCHAR NOT NULL,
    amount NUMERIC NOT NULL,
    currency_id BIGINT NOT NULL,
    description VARCHAR,
    timestamp VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    PRIMARY KEY (id)
);