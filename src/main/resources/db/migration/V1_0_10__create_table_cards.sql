CREATE TABLE IF NOT EXISTS cards (
     id UUID DEFAULT uuid_generate_v4(),
     account_id UUID,
     loan_id UUID,
     card_number VARCHAR,
     expiration_date DATE,
     holder_name VARCHAR,
     cvv VARCHAR,
     balance DECIMAL(12, 2),
     currency_id BIGINT NOT NULL,
     status VARCHAR,
     limit_id UUID,
     payment_system_id BIGINT,
     PRIMARY KEY (id),
     FOREIGN KEY (account_id) REFERENCES accounts (id),
     FOREIGN KEY (limit_id) REFERENCES limits (id),
     FOREIGN KEY (loan_id) REFERENCES loans (id),
     FOREIGN KEY (payment_system_id) REFERENCES payment_systems (id)
);

ALTER TABLE transactions ADD FOREIGN KEY (sender_card_id) REFERENCES cards (id);
ALTER TABLE transactions ADD FOREIGN KEY (recipient_card_id) REFERENCES cards (id);
ALTER TABLE loans ADD FOREIGN KEY (card_id) REFERENCES cards (id);
