CREATE TABLE IF NOT EXISTS transactions (
    id UUID DEFAULT uuid_generate_v4(),
    sender_account_id UUID,
    recipient_account_id UUID,
    type VARCHAR NOT NULL,
    amount NUMERIC NOT NULL,
    currency VARCHAR NOT NULL,
    description VARCHAR,
    timestamp VARCHAR NOT NULL,
    status VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (sender_account_id) REFERENCES accounts (id),
    FOREIGN KEY (recipient_account_id) REFERENCES accounts (id)
);