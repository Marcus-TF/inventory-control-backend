CREATE TABLE inventory.provider (
                                    provider_id uuid NOT NULL,
                                    address varchar(255) NULL,
                                    cep varchar(255) NULL,
                                    cnpj varchar(255) NULL,
                                    contact varchar(255) NULL,
                                    district varchar(255) NULL,
                                    insc varchar(255) NULL,
                                    num varchar(255) NULL,
                                    provider_name varchar(255) NULL,
                                    telephone varchar(255) NULL,
                                    CONSTRAINT provider_pkey PRIMARY KEY (provider_id)
);