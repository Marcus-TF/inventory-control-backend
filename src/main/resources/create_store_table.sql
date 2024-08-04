CREATE TABLE inventory.store (
                                 store_id uuid NOT NULL,
                                 address varchar(255) NULL,
                                 cnpj varchar(255) NULL,
                                 district varchar(255) NULL,
                                 "name" varchar(255) NULL,
                                 "number" int4 NULL,
                                 "subscription" varchar(255) NULL,
                                 telephone varchar(255) NULL,
                                 zip_code varchar(255) NULL,
                                 city_id uuid NULL,
                                 CONSTRAINT store_pkey PRIMARY KEY (store_id)
);

ALTER TABLE inventory.store ADD CONSTRAINT fk_city FOREIGN KEY (city_id) REFERENCES inventory.city(city_id);