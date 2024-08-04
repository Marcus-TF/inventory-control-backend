CREATE TABLE inventory.provider_cities (
                                           provider_id_fk uuid NOT NULL,
                                           city_id_fk uuid NOT NULL
);

ALTER TABLE inventory.provider_cities ADD CONSTRAINT fk_provider FOREIGN KEY (provider_id_fk) REFERENCES inventory.provider(provider_id);
ALTER TABLE inventory.provider_cities ADD CONSTRAINT fk_city FOREIGN KEY (city_id_fk) REFERENCES inventory.city(city_id);