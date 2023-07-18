CREATE TABLE IF NOT EXISTS payment_system_countries (
    payment_system_id BIGINT,
    country_id BIGINT,
    PRIMARY KEY (payment_system_id, country_id),
    FOREIGN KEY (payment_system_id) REFERENCES payment_systems (id),
    FOREIGN KEY (country_id) REFERENCES countries (id)
);