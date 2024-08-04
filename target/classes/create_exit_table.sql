CREATE TABLE inventory."exit" (
	exit_id uuid NOT NULL,
	shipping float8 NULL,
	tax float8 NULL,
	total float8 NULL,
	shipping_company_id uuid NULL,
	store_id uuid NULL,
	CONSTRAINT exit_pkey PRIMARY KEY (exit_id)
);

ALTER TABLE inventory."exit" ADD CONSTRAINT fk_store FOREIGN KEY (store_id) REFERENCES inventory.store(store_id);
ALTER TABLE inventory."exit" ADD CONSTRAINT fk_shipping_company FOREIGN KEY (shipping_company_id) REFERENCES inventory.shipping_company(shipping_company_id);