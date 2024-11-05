CREATE TABLE IF NOT EXISTS customers
(
    customer_id  SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    surname      VARCHAR(100) NOT NULL,
    pesel        VARCHAR(11)  NOT NULL UNIQUE,
    address      VARCHAR(255),
    phone_number VARCHAR(15),
    email        VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users
(
    user_id     SERIAL PRIMARY KEY,
    username    VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(50)  NOT NULL,
    customer_id INTEGER REFERENCES customers (customer_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS accounts
(
    account_id   SERIAL PRIMARY KEY,
    balance      NUMERIC(15, 2) DEFAULT 0.00,
    account_type VARCHAR(50) NOT NULL,
    customer_id  INTEGER     NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE
);