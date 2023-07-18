CREATE TABLE IF NOT EXISTS limits (
    id UUID DEFAULT uuid_generate_v4(),
    daily_limit NUMERIC,
    monthly_limit NUMERIC,
    transaction_limit NUMERIC,
    PRIMARY KEY (id)
    );