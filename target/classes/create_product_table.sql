CREATE TABLE inventory.product (
                                   product_id uuid NOT NULL,
                                   controlled bool NOT NULL,
                                   minimum_quantity int4 NULL,
                                   product_name varchar(255) NULL,
                                   weight varchar(255) NULL,
                                   category_id uuid NULL,
                                   provider_id uuid NULL,
                                   CONSTRAINT product_pkey PRIMARY KEY (product_id)
);

ALTER TABLE inventory.product ADD CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES inventory.category(category_id);
ALTER TABLE inventory.product ADD CONSTRAINT fk_provider FOREIGN KEY (provider_id) REFERENCES inventory.provider(provider_id);