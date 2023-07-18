CREATE TABLE IF NOT EXISTS loans (
     id UUID DEFAULT uuid_generate_v4(),
     card_id UUID,
     loan_amount NUMERIC NOT NULL,
     interest_rate NUMERIC NOT NULL,
     term_months INTEGER NOT NULL,
     start_date DATE NOT NULL,
     end_date DATE,
     status VARCHAR,
     PRIMARY KEY (id)
);