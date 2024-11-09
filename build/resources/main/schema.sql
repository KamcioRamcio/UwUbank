CREATE TABLE IF NOT EXISTS branches
(
    branch_id           SERIAL PRIMARY KEY,
    branch_name         VARCHAR(100) NOT NULL,
    branch_address      VARCHAR(255),
    branch_phone_number VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS customers
(
    customer_id  SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    surname      VARCHAR(100) NOT NULL,
    pesel        VARCHAR(11)  NOT NULL UNIQUE,
    address      VARCHAR(255),
    phone_number VARCHAR(15),
    email        VARCHAR(100) NOT NULL UNIQUE,
    branch_id    INTEGER      REFERENCES branches (branch_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS employees
(
    employee_id SERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    position    VARCHAR(100),
    branch_id   INTEGER      REFERENCES branches (branch_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS users
(
    user_id     SERIAL PRIMARY KEY,
    username    VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    role        VARCHAR(50)  NOT NULL,
    customer_id INTEGER REFERENCES customers (customer_id) ON DELETE CASCADE,
    employee_id INTEGER REFERENCES employees (employee_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS accounts
(
    account_id   SERIAL PRIMARY KEY,
    balance      NUMERIC(15, 2) DEFAULT 0.00,
    account_type VARCHAR(50) NOT NULL,
    customer_id  INTEGER     NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE
);



CREATE TABLE IF NOT EXISTS incomes
(
    income_id   SERIAL PRIMARY KEY,
    customer_id INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    amount      NUMERIC(15, 2) NOT NULL,
    currency    VARCHAR(5)     NOT NULL,
    sender      VARCHAR(100)   NOT NULL,
    account_id  INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    date        DATE           NOT NULL
);

CREATE TABLE IF NOT EXISTS outcomes
(
    outcome_id  SERIAL PRIMARY KEY,
    customer_id INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    amount      NUMERIC(15, 2) NOT NULL,
    currency    VARCHAR(5)     NOT NULL,
    receiver    VARCHAR(100)   NOT NULL,
    account_id  INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    date        DATE           NOT NULL
);

CREATE TABLE IF NOT EXISTS transfers
(
    transfer_id         SERIAL PRIMARY KEY,
    customer_id         INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    amount              NUMERIC(15, 2) NOT NULL,
    sender_account_id   INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    receiver_account_id INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    date                DATE           NOT NULL
);

CREATE TABLE IF NOT EXISTS own_transfers
(
    own_transfer_id SERIAL PRIMARY KEY,
    customer_id     INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    amount          NUMERIC(15, 2) NOT NULL,
    from_account_id INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    to_account_id   INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    date            DATE           NOT NULL
);

CREATE TABLE IF NOT EXISTS loans
(
    loan_id       SERIAL PRIMARY KEY,
    customer_id   INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    amount        NUMERIC(15, 2) NOT NULL,
    currency      VARCHAR(5)     NOT NULL,
    start_date    DATE           NOT NULL,
    end_date      DATE           NOT NULL,
    interest_rate NUMERIC(5, 2)  NOT NULL,
    approved      BOOLEAN        NOT NULL,
    status        VARCHAR(50)    NOT NULL
);

CREATE TABLE IF NOT EXISTS savings
(
    savings_id    SERIAL PRIMARY KEY,
    customer_id   INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    balance       NUMERIC(15, 2) NOT NULL,
    currency      VARCHAR(5)     NOT NULL,
    interest_rate NUMERIC(5, 2)  NOT NULL
);

CREATE TABLE IF NOT EXISTS cards
(
    card_id         SERIAL PRIMARY KEY,
    customer_id     INTEGER        NOT NULL REFERENCES customers (customer_id) ON DELETE CASCADE,
    account_id      INTEGER        NOT NULL REFERENCES accounts (account_id) ON DELETE CASCADE,
    card_number     VARCHAR(16)    NOT NULL,
    card_type       VARCHAR(50)    NOT NULL,
    expiration_date DATE           NOT NULL,
    cvv             VARCHAR(3)     NOT NULL,
    limits          NUMERIC(15, 2) NOT NULL,
    status          VARCHAR(50)    NOT NULL
);