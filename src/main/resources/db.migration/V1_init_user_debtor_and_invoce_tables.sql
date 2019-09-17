CREATE TABLE user (
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
CONSTRAINT debtor_pkey PRIMARY KEY (id),
CONSTRAINT fk_user_debtor
FOREIGN KEY (user_id)
REFERENCES user (id)
ON UPDATE NO ACTION
ON DELETE CASCADE
);

CREATE TABLE invoice (
id bigserial
status character varying(8) NOT NULL,
amount NUMERIC(precision, scale) NOT NULL,
duedate timestamp with time zone not null,
CONSTRAINT invoice_pkey PRIMARY KEY (id),
CONSTRAINT fk_debtor_invoice
FOREIGN KEY (debtor_id)
REFERENCES debtor (id)
ON UPDATE NO ACTION
ON DELETE CASCADE
);