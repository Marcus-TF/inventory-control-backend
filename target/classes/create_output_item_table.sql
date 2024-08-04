CREATE TABLE inventory.output_item (
	output_item_id uuid NOT NULL,
	amount int4 NULL,
	batch varchar(255) NULL,
	value float8 NULL,
	exit_id uuid NULL,
	product_id uuid NULL,
	CONSTRAINT output_item_pkey PRIMARY KEY (output_item_id)
);

ALTER TABLE inventory.output_item ADD CONSTRAINT fk_exit FOREIGN KEY (exit_id) REFERENCES inventory."exit"(exit_id);
ALTER TABLE inventory.output_item ADD CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES inventory.product(product_id);