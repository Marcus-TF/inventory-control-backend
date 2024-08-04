CREATE TABLE inventory.city (
                                city_id uuid NOT NULL,
                                city_name varchar(255) NULL,
                                uf varchar(255) NULL,
                                CONSTRAINT city_pkey PRIMARY KEY (city_id)
);