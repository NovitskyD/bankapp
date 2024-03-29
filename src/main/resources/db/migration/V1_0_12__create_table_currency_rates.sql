CREATE TABLE currency_rates(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY,
    base_currency_id BIGINT NOT NULL,
    target_currency_id BIGINT NOT NULL,
    rate DECIMAL(10,4) NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (base_currency_id) REFERENCES currencies (id),
    FOREIGN KEY (target_currency_id) REFERENCES currencies (id),
    UNIQUE (base_currency_id, target_currency_id, date)
);