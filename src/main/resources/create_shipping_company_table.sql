CREATE TABLE inventory.shipping_company (
                                            shipping_company_id uuid NOT NULL,
                                            address varchar(255) NULL,
                                            cnpj varchar(255) NULL,
                                            contact varchar(255) NULL,
                                            district varchar(255) NULL,
                                            "name" varchar(255) NULL,
                                            "number" int4 NULL,
                                            "subscription" varchar(255) NULL,
                                            telephone varchar(255) NULL,
                                            zip_code varchar(255) NULL,
                                            CONSTRAINT shipping_company_pkey PRIMARY KEY (shipping_company_id)
);