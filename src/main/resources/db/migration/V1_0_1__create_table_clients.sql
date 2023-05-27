create extension if not exists "uuid-ossp";

CREATE TABLE IF NOT EXISTS clients (
    id UUID DEFAULT uuid_generate_v4(),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    birth_date TIMESTAMP WITH TIME ZONE,
    address VARCHAR NOT NULL,
    phone VARCHAR NOT NULL,
    email VARCHAR,
    password VARCHAR NOT NULL,
    PRIMARY KEY (id)
);