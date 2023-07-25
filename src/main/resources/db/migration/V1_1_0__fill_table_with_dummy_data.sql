INSERT INTO clients (first_name, last_name, birth_date, address, phone, email, password)
VALUES
    ('John', 'Doe', '1985-07-15'::timestamp, '123 Main St', '555-1234', 'john.doe@example.com', '$2a$12$IJicbQ4ddlq0j1mekOJ6/u6gWQJSp.B5fetcRRLDh.QJarg5lslDy'), -- (первый пользователь с паролем "password1")
    ('Jane', 'Smith', '1990-03-25'::timestamp, '456 Elm St', '555-5678', 'jane.smith@example.com', '$2a$12$uakADbmS7hZvK10TrJYKRu086bELReEyBv5SMRkxW8dx.NF29ABNO'), -- (второй пользователь с паролем "password2")
    ('Michael', 'Johnson', '1988-11-10'::timestamp, '789 Oak St', '555-9876', 'michael.johnson@example.com', '$2a$12$NrZinc69foV5Tg/r8s7ztuZjtHAi2B5YFkZPms6gm9CA35dJoSKcm'), -- (третий пользователь с паролем "password3")
    ('Sarah', 'Williams', '1992-06-05'::timestamp, '321 Pine St', '555-4321', 'sarah.williams@example.com', '$2a$12$VeSRw9o9sFomVkHXk7DwC./VPfIpwaeumgJjcKAsCAuIpE6dQ1UMm'); -- (четвертый пользователь с паролем "password4")

INSERT INTO clients (first_name, last_name, birth_date, address, phone, email, password, role)
VALUES
    ('Admin', 'Admin', '1985-07-15'::timestamp, 'Admin', '555-5555', 'admin@gmail.com',
     '$2a$12$5IP0xTnsKy6jF4ojVIE5TeOEuf6dhnuXUAy/N6QJcU2My0yAEs1u6', 'ADMIN'); -- admin (пароль для администратора)

INSERT INTO countries (name) VALUES
                                 ('United States'),
                                 ('Canada'),
                                 ('United Kingdom'),
                                 ('Germany'),
                                 ('France'),
                                 ('Australia'),
                                 ('Japan'),
                                 ('China'),
                                 ('Brazil'),
                                 ('India');

INSERT INTO payment_systems (name, description, logo_url, transaction_fee_percentage)
VALUES
    ('Mastercard', 'Credit card payment system', 'https://example.com/mastercard-logo.png', 2.5),
    ('Visa', 'Credit card payment system', 'https://example.com/visa-logo.png', 2.0),
    ('PayPal', 'Online payment system', 'https://example.com/paypal-logo.png', 3.0);

INSERT INTO limits (daily_limit, monthly_limit, transaction_limit)
VALUES
    (10000, 100000, 50000),
    (50000, 500000, 100000),
    (100000, 1000000, 300000);

INSERT INTO currencies (code, name) VALUES
                                        ('USD', 'United States Dollar'),
                                        ('EUR', 'Euro'),
                                        ('GBP', 'British Pound Sterling'),
                                        ('JPY', 'Japanese Yen'),
                                        ('CAD', 'Canadian Dollar');


INSERT INTO currency_rates (base_currency_id, target_currency_id, rate, date)
VALUES
    (1, 2, 0.8765, '2023-07-08'), -- USD to EUR
    (1, 3, 0.7654, '2023-07-08'), -- USD to GBP
    (1, 4, 110.1234, '2023-07-08'), -- USD to JPY
    (1, 5, 1.2987, '2023-07-08'), -- USD to CAD
    (2, 1, 1.1396, '2023-07-08'), -- EUR to USD
    (2, 3, 0.8754, '2023-07-08'), -- EUR to GBP
    (2, 4, 128.7634, '2023-07-08'), -- EUR to JPY
    (2, 5, 1.5189, '2023-07-08'), -- EUR to CAD
    (3, 1, 1.3054, '2023-07-08'), -- GBP to USD
    (3, 2, 1.1421, '2023-07-08'), -- GBP to EUR
    (3, 4, 147.9087, '2023-07-08'), -- GBP to JPY
    (3, 5, 1.7403, '2023-07-08'), -- GBP to CAD
    (4, 1, 0.0091, '2023-07-08'), -- JPY to USD
    (4, 2, 0.0078, '2023-07-08'), -- JPY to EUR
    (4, 3, 0.0068, '2023-07-08'), -- JPY to GBP
    (4, 5, 0.0117, '2023-07-08'), -- JPY to CAD
    (5, 1, 0.7692, '2023-07-08'), -- CAD to USD
    (5, 2, 0.6581, '2023-07-08'), -- CAD to EUR
    (5, 3, 0.5745, '2023-07-08'), -- CAD to GBP
    (5, 4, 85.2069, '2023-07-08'); -- CAD to JPY
