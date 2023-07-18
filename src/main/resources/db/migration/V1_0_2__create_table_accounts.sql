CREATE TABLE IF NOT EXISTS accounts (
    id UUID DEFAULT uuid_generate_v4(),
    client_id UUID,
    account_number VARCHAR NOT NULL,
    account_type VARCHAR NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id)
);