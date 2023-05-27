CREATE TABLE IF NOT EXISTS cards (
     id UUID DEFAULT uuid_generate_v4(),
     account_id UUID,
     card_number VARCHAR NOT NULL,
     expiration_date DATE,
     holder_name VARCHAR,
     cvv VARCHAR,
     status VARCHAR,
     PRIMARY KEY (id),
     FOREIGN KEY (account_id) REFERENCES accounts (id)
);