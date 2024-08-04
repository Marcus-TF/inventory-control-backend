CREATE TABLE inventory.category (
                                    category_id uuid NOT NULL,
                                    category_name varchar(255) NULL,
                                    CONSTRAINT category_pkey PRIMARY KEY (category_id)
);