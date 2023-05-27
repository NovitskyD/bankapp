INSERT INTO clients (first_name, last_name, birth_date, address, phone, email, password)
VALUES
    ('John', 'Doe', '1985-07-15'::timestamp, '123 Main St', '555-1234', 'john.doe@example.com', 'password123'),
    ('Jane', 'Smith', '1990-03-25'::timestamp, '456 Elm St', '555-5678', 'jane.smith@example.com', 'password456'),
    ('Michael', 'Johnson', '1988-11-10'::timestamp, '789 Oak St', '555-9876', 'michael.johnson@example.com', 'password789'),
    ('Sarah', 'Williams', '1992-06-05'::timestamp, '321 Pine St', '555-4321', 'sarah.williams@example.com', 'password987');
