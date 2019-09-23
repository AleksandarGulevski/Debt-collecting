CREATE TABLE users (
id bigserial not null,
first_name character varying(100) NOT NULL,
last_name character varying(100) NOT NULL,
email character varying(100) NOT NULL,
CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE debtor (
id bigserial not null,
first_name character varying(100) NOT NULL,
last_name character varying(100) NOT NULL,
email character varying(100) NOT NULL,
iban character varying(50) NOT NULL,
ssn character varying(15) NOT NULL,
user_id bigint NOT NULL,
CONSTRAINT debtor_pkey PRIMARY KEY (id),
CONSTRAINT fk_debtor_users
FOREIGN KEY (user_id)
REFERENCES users (id)
ON UPDATE NO ACTION
ON DELETE CASCADE
);

CREATE TABLE invoice (
id bigserial not null,
status character varying(8) NOT NULL,
amount NUMERIC(12, 2) NOT NULL,
duedate timestamp with time zone not null,
debtor_id bigint NOT NULL,
CONSTRAINT invoice_pkey PRIMARY KEY (id),
CONSTRAINT fk_invoice_debtor
FOREIGN KEY (debtor_id)
REFERENCES debtor (id)
ON UPDATE NO ACTION
ON DELETE CASCADE
);