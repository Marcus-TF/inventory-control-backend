CREATE TABLE inventory.entry_item (
	entry_item_id uuid NOT NULL,
	amount int4 NULL,
	batch varchar(255) NULL,
	value float8 NULL,
	entry_id uuid NULL,
	product_id uuid NULL,
	CONSTRAINT entry_item_pkey PRIMARY KEY (entry_item_id)
);

ALTER TABLE inventory.entry_item ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES inventory.product(product_id);
ALTER TABLE inventory.entry_item ADD CONSTRAINT fk_entry FOREIGN KEY (entry_id) REFERENCES inventory.entry(entry_id);