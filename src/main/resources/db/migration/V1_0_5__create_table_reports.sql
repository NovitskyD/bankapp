CREATE TABLE IF NOT EXISTS reports (
    id UUID DEFAULT uuid_generate_v4(),
    account_id UUID,
    title VARCHAR,
    description VARCHAR NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);