CREATE TABLE invoice_item (
id bigserial not null,
item_name character varying(100) NOT NULL,
item_type character varying(100) NOT NULL,
item_price_without_vat  NUMERIC(12, 2) NOT NULL,
item_description character varying(400),
item_date  timestamp with time zone not null,
item_vat_percentage bigint NOT NULL,
invoice_id bigint NOT NULL,
CONSTRAINT invoice_item_pkey PRIMARY KEY (id),
CONSTRAINT fk_invoice_item_invoice
FOREIGN KEY (invoice_id)
REFERENCES invoice (id)
ON UPDATE NO ACTION
ON DELETE CASCADE
)