--
-- PostgreSQL database dump
--

-- Dumped from database version 17.2 (Debian 17.2-1.pgdg120+1)
-- Dumped by pg_dump version 17.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: accounts; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.accounts (
    account_id integer NOT NULL,
    balance numeric(15,2) DEFAULT 0.00,
    account_type character varying(50) NOT NULL,
    customer_id integer NOT NULL
);


ALTER TABLE public.accounts OWNER TO "Kamcio";

--
-- Name: accounts_account_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.accounts_account_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.accounts_account_id_seq OWNER TO "Kamcio";

--
-- Name: accounts_account_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.accounts_account_id_seq OWNED BY public.accounts.account_id;


--
-- Name: branches; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.branches (
    branch_id integer NOT NULL,
    branch_name character varying(100) NOT NULL,
    branch_address character varying(255),
    branch_phone_number character varying(15)
);


ALTER TABLE public.branches OWNER TO "Kamcio";

--
-- Name: branches_branch_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.branches_branch_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.branches_branch_id_seq OWNER TO "Kamcio";

--
-- Name: branches_branch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.branches_branch_id_seq OWNED BY public.branches.branch_id;


--
-- Name: cards; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.cards (
    card_id integer NOT NULL,
    customer_id integer NOT NULL,
    account_id integer NOT NULL,
    card_number character varying(16) NOT NULL,
    card_type character varying(50) NOT NULL,
    expiration_date date NOT NULL,
    cvv character varying(3) NOT NULL,
    limits numeric(15,2) NOT NULL,
    status character varying(50) NOT NULL
);


ALTER TABLE public.cards OWNER TO "Kamcio";

--
-- Name: cards_card_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.cards_card_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cards_card_id_seq OWNER TO "Kamcio";

--
-- Name: cards_card_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.cards_card_id_seq OWNED BY public.cards.card_id;


--
-- Name: customers; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.customers (
    customer_id integer NOT NULL,
    name character varying(100) NOT NULL,
    surname character varying(100) NOT NULL,
    pesel character varying(11) NOT NULL,
    address character varying(255),
    phone_number character varying(15),
    email character varying(100) NOT NULL,
    branch_id integer
);


ALTER TABLE public.customers OWNER TO "Kamcio";

--
-- Name: customers_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.customers_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.customers_customer_id_seq OWNER TO "Kamcio";

--
-- Name: customers_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.customers_customer_id_seq OWNED BY public.customers.customer_id;


--
-- Name: employees; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.employees (
    employee_id integer NOT NULL,
    name character varying(100) NOT NULL,
    "position" character varying(100),
    branch_id integer
);


ALTER TABLE public.employees OWNER TO "Kamcio";

--
-- Name: employees_employee_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.employees_employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.employees_employee_id_seq OWNER TO "Kamcio";

--
-- Name: employees_employee_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.employees_employee_id_seq OWNED BY public.employees.employee_id;


--
-- Name: incomes; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.incomes (
    income_id integer NOT NULL,
    customer_id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    currency character varying(5) NOT NULL,
    sender character varying(100) NOT NULL,
    account_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.incomes OWNER TO "Kamcio";

--
-- Name: incomes_income_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.incomes_income_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.incomes_income_id_seq OWNER TO "Kamcio";

--
-- Name: incomes_income_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.incomes_income_id_seq OWNED BY public.incomes.income_id;


--
-- Name: loans; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.loans (
    loan_id integer NOT NULL,
    customer_id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    currency character varying(5) NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    interest_rate numeric(5,2) NOT NULL,
    approved boolean NOT NULL,
    status character varying(50) NOT NULL
);


ALTER TABLE public.loans OWNER TO "Kamcio";

--
-- Name: loans_loan_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.loans_loan_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.loans_loan_id_seq OWNER TO "Kamcio";

--
-- Name: loans_loan_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.loans_loan_id_seq OWNED BY public.loans.loan_id;


--
-- Name: outcomes; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.outcomes (
    outcome_id integer NOT NULL,
    customer_id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    currency character varying(5) NOT NULL,
    receiver character varying(100) NOT NULL,
    account_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.outcomes OWNER TO "Kamcio";

--
-- Name: outcomes_outcome_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.outcomes_outcome_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.outcomes_outcome_id_seq OWNER TO "Kamcio";

--
-- Name: outcomes_outcome_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.outcomes_outcome_id_seq OWNED BY public.outcomes.outcome_id;


--
-- Name: own_transfers; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.own_transfers (
    own_transfer_id integer NOT NULL,
    customer_id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    from_account_id integer NOT NULL,
    to_account_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.own_transfers OWNER TO "Kamcio";

--
-- Name: own_transfers_own_transfer_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.own_transfers_own_transfer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.own_transfers_own_transfer_id_seq OWNER TO "Kamcio";

--
-- Name: own_transfers_own_transfer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.own_transfers_own_transfer_id_seq OWNED BY public.own_transfers.own_transfer_id;


--
-- Name: savings; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.savings (
    savings_id integer NOT NULL,
    customer_id integer NOT NULL,
    balance numeric(15,2) NOT NULL,
    currency character varying(5) NOT NULL,
    interest_rate numeric(5,2) NOT NULL
);


ALTER TABLE public.savings OWNER TO "Kamcio";

--
-- Name: savings_savings_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.savings_savings_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.savings_savings_id_seq OWNER TO "Kamcio";

--
-- Name: savings_savings_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.savings_savings_id_seq OWNED BY public.savings.savings_id;


--
-- Name: transfers; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.transfers (
    transfer_id integer NOT NULL,
    customer_id integer NOT NULL,
    amount numeric(15,2) NOT NULL,
    sender_account_id integer NOT NULL,
    receiver_account_id integer NOT NULL,
    date date NOT NULL
);


ALTER TABLE public.transfers OWNER TO "Kamcio";

--
-- Name: transfers_transfer_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.transfers_transfer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.transfers_transfer_id_seq OWNER TO "Kamcio";

--
-- Name: transfers_transfer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.transfers_transfer_id_seq OWNED BY public.transfers.transfer_id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: Kamcio
--

CREATE TABLE public.users (
    user_id integer NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(50) NOT NULL,
    customer_id integer,
    employee_id integer
);


ALTER TABLE public.users OWNER TO "Kamcio";

--
-- Name: users_user_id_seq; Type: SEQUENCE; Schema: public; Owner: Kamcio
--

CREATE SEQUENCE public.users_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_user_id_seq OWNER TO "Kamcio";

--
-- Name: users_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: Kamcio
--

ALTER SEQUENCE public.users_user_id_seq OWNED BY public.users.user_id;


--
-- Name: accounts account_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.accounts ALTER COLUMN account_id SET DEFAULT nextval('public.accounts_account_id_seq'::regclass);


--
-- Name: branches branch_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.branches ALTER COLUMN branch_id SET DEFAULT nextval('public.branches_branch_id_seq'::regclass);


--
-- Name: cards card_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.cards ALTER COLUMN card_id SET DEFAULT nextval('public.cards_card_id_seq'::regclass);


--
-- Name: customers customer_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.customers ALTER COLUMN customer_id SET DEFAULT nextval('public.customers_customer_id_seq'::regclass);


--
-- Name: employees employee_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.employees ALTER COLUMN employee_id SET DEFAULT nextval('public.employees_employee_id_seq'::regclass);


--
-- Name: incomes income_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.incomes ALTER COLUMN income_id SET DEFAULT nextval('public.incomes_income_id_seq'::regclass);


--
-- Name: loans loan_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.loans ALTER COLUMN loan_id SET DEFAULT nextval('public.loans_loan_id_seq'::regclass);


--
-- Name: outcomes outcome_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.outcomes ALTER COLUMN outcome_id SET DEFAULT nextval('public.outcomes_outcome_id_seq'::regclass);


--
-- Name: own_transfers own_transfer_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.own_transfers ALTER COLUMN own_transfer_id SET DEFAULT nextval('public.own_transfers_own_transfer_id_seq'::regclass);


--
-- Name: savings savings_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.savings ALTER COLUMN savings_id SET DEFAULT nextval('public.savings_savings_id_seq'::regclass);


--
-- Name: transfers transfer_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.transfers ALTER COLUMN transfer_id SET DEFAULT nextval('public.transfers_transfer_id_seq'::regclass);


--
-- Name: users user_id; Type: DEFAULT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.users ALTER COLUMN user_id SET DEFAULT nextval('public.users_user_id_seq'::regclass);


--
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.accounts (account_id, balance, account_type, customer_id) FROM stdin;
4	1701.00	USD	1
5	3935.00	USD	2
3	2369.00	EURO	1
2	3280.00	PLN	1
\.


--
-- Data for Name: branches; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.branches (branch_id, branch_name, branch_address, branch_phone_number) FROM stdin;
1	Straw Hat Pirates	Thousand Sunny, Grand Line	111-222-3333
2	Akatsuki	Hidden Rain Village, Amegakure	444-555-6666
3	Soul Society	Seireitei, Soul Society	777-888-9999
4	Mugiwara Store	Sabaody Archipelago, Grand Line	123-456-7890
5	Konoha Bank	Hidden Leaf Village, Konoha	234-567-8901
6	Gotei 13	Seireitei, Soul Society	345-678-9012
7	Marine Headquarters	Marineford, Grand Line	456-789-0123
\.


--
-- Data for Name: cards; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.cards (card_id, customer_id, account_id, card_number, card_type, expiration_date, cvv, limits, status) FROM stdin;
\.


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.customers (customer_id, name, surname, pesel, address, phone_number, email, branch_id) FROM stdin;
1	Rav	Borus	420692137	Walkowiakowa 27	122222222	rafalborek@gmail.com	1
2	Pawel	BOOM	4206923237	Walkowiakowa 27	132222222	ppw@gmail.com	1
\.


--
-- Data for Name: employees; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.employees (employee_id, name, "position", branch_id) FROM stdin;
1	John Doe	TELLER	1
2	Kamil	TELLER	1
\.


--
-- Data for Name: incomes; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.incomes (income_id, customer_id, amount, currency, sender, account_id, date) FROM stdin;
2	1	100.00	USD	Allegro	2	2024-12-04
\.


--
-- Data for Name: loans; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.loans (loan_id, customer_id, amount, currency, start_date, end_date, interest_rate, approved, status) FROM stdin;
\.


--
-- Data for Name: outcomes; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.outcomes (outcome_id, customer_id, amount, currency, receiver, account_id, date) FROM stdin;
\.


--
-- Data for Name: own_transfers; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.own_transfers (own_transfer_id, customer_id, amount, from_account_id, to_account_id, date) FROM stdin;
1	1	100.00	2	3	2024-12-04
2	1	200.00	2	3	2024-12-04
3	1	200.00	3	2	2024-12-04
\.


--
-- Data for Name: savings; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.savings (savings_id, customer_id, balance, currency, interest_rate) FROM stdin;
\.


--
-- Data for Name: transfers; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.transfers (transfer_id, customer_id, amount, sender_account_id, receiver_account_id, date) FROM stdin;
1	1	200.00	3	5	2024-12-04
2	1	999.00	4	5	2024-12-04
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: Kamcio
--

COPY public.users (user_id, username, password, role, customer_id, employee_id) FROM stdin;
1	johndoe	securepassword123	EMPLOYEE	\N	1
2	kamil	123	EMPLOYEE	\N	2
3	rav	123	CUSTOMER	1	\N
4	pawel	123	CUSTOMER	2	\N
\.


--
-- Name: accounts_account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.accounts_account_id_seq', 5, true);


--
-- Name: branches_branch_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.branches_branch_id_seq', 1, false);


--
-- Name: cards_card_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.cards_card_id_seq', 1, false);


--
-- Name: customers_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.customers_customer_id_seq', 2, true);


--
-- Name: employees_employee_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.employees_employee_id_seq', 2, true);


--
-- Name: incomes_income_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.incomes_income_id_seq', 2, true);


--
-- Name: loans_loan_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.loans_loan_id_seq', 1, false);


--
-- Name: outcomes_outcome_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.outcomes_outcome_id_seq', 1, false);


--
-- Name: own_transfers_own_transfer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.own_transfers_own_transfer_id_seq', 3, true);


--
-- Name: savings_savings_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.savings_savings_id_seq', 1, false);


--
-- Name: transfers_transfer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.transfers_transfer_id_seq', 2, true);


--
-- Name: users_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: Kamcio
--

SELECT pg_catalog.setval('public.users_user_id_seq', 4, true);


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (account_id);


--
-- Name: branches branches_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.branches
    ADD CONSTRAINT branches_pkey PRIMARY KEY (branch_id);


--
-- Name: cards cards_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_pkey PRIMARY KEY (card_id);


--
-- Name: customers customers_email_key; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_email_key UNIQUE (email);


--
-- Name: customers customers_pesel_key; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pesel_key UNIQUE (pesel);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (customer_id);


--
-- Name: employees employees_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_pkey PRIMARY KEY (employee_id);


--
-- Name: incomes incomes_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.incomes
    ADD CONSTRAINT incomes_pkey PRIMARY KEY (income_id);


--
-- Name: loans loans_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_pkey PRIMARY KEY (loan_id);


--
-- Name: outcomes outcomes_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.outcomes
    ADD CONSTRAINT outcomes_pkey PRIMARY KEY (outcome_id);


--
-- Name: own_transfers own_transfers_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.own_transfers
    ADD CONSTRAINT own_transfers_pkey PRIMARY KEY (own_transfer_id);


--
-- Name: savings savings_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.savings
    ADD CONSTRAINT savings_pkey PRIMARY KEY (savings_id);


--
-- Name: transfers transfers_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.transfers
    ADD CONSTRAINT transfers_pkey PRIMARY KEY (transfer_id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: accounts accounts_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: cards cards_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: cards cards_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.cards
    ADD CONSTRAINT cards_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: customers customers_branch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_branch_id_fkey FOREIGN KEY (branch_id) REFERENCES public.branches(branch_id) ON DELETE SET NULL;


--
-- Name: employees employees_branch_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.employees
    ADD CONSTRAINT employees_branch_id_fkey FOREIGN KEY (branch_id) REFERENCES public.branches(branch_id) ON DELETE SET NULL;


--
-- Name: incomes incomes_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.incomes
    ADD CONSTRAINT incomes_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: incomes incomes_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.incomes
    ADD CONSTRAINT incomes_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: loans loans_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.loans
    ADD CONSTRAINT loans_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: outcomes outcomes_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.outcomes
    ADD CONSTRAINT outcomes_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: outcomes outcomes_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.outcomes
    ADD CONSTRAINT outcomes_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: own_transfers own_transfers_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.own_transfers
    ADD CONSTRAINT own_transfers_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: own_transfers own_transfers_from_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.own_transfers
    ADD CONSTRAINT own_transfers_from_account_id_fkey FOREIGN KEY (from_account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: own_transfers own_transfers_to_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.own_transfers
    ADD CONSTRAINT own_transfers_to_account_id_fkey FOREIGN KEY (to_account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: savings savings_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.savings
    ADD CONSTRAINT savings_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: transfers transfers_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.transfers
    ADD CONSTRAINT transfers_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: transfers transfers_receiver_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.transfers
    ADD CONSTRAINT transfers_receiver_account_id_fkey FOREIGN KEY (receiver_account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: transfers transfers_sender_account_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.transfers
    ADD CONSTRAINT transfers_sender_account_id_fkey FOREIGN KEY (sender_account_id) REFERENCES public.accounts(account_id) ON DELETE CASCADE;


--
-- Name: users users_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customers(customer_id) ON DELETE CASCADE;


--
-- Name: users users_employee_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: Kamcio
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_employee_id_fkey FOREIGN KEY (employee_id) REFERENCES public.employees(employee_id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

