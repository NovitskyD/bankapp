CREATE TABLE IF NOT EXISTS countries (
     id BIGINT GENERATED BY DEFAULT AS IDENTITY,
     name VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
);