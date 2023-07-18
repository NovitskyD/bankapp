CREATE TABLE IF NOT EXISTS cards (
     id UUID DEFAULT uuid_generate_v4(),
     account_id UUID,
     card_number VARCHAR NOT NULL,
     expiration_date DATE,
     holder_name VARCHAR,
     cvv VARCHAR,
     status VARCHAR,
     limit_id UUID,
     payment_system_id UUID,
     PRIMARY KEY (id),
     FOREIGN KEY (account_id) REFERENCES accounts (id),
     FOREIGN KEY (limit_id) REFERENCES limits (id),
     FOREIGN KEY (payment_system_id) REFERENCES payment_systems (id)
);