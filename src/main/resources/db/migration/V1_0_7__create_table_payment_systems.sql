CREATE TABLE IF NOT EXISTS payment_systems (
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    logo_url VARCHAR(255),
    transaction_fee_percentage DECIMAL(5, 2),
    PRIMARY KEY (id)
);