CREATE TABLE inventory.entry (
                                 entry_id uuid NOT NULL,
                                 entry_date timestamp(6) NULL,
                                 invoice_number int4 NULL,
                                 request_date timestamp(6) NULL,
                                 shipping float8 NULL,
                                 tax float8 NULL,
                                 total float8 NULL,
                                 shipping_company_id uuid NULL,
                                 CONSTRAINT entry_pkey PRIMARY KEY (entry_id)
);

ALTER TABLE inventory.entry ADD CONSTRAINT fk_shipping_company FOREIGN KEY (shipping_company_id) REFERENCES inventory.shipping_company(shipping_company_id);