CREATE TABLE inventory.shipping_company_cities (
                                                   shipping_company_id_fk uuid NOT NULL,
                                                   city_id_fk uuid NOT NULL
);

ALTER TABLE inventory.shipping_company_cities ADD CONSTRAINT fk_city FOREIGN KEY (city_id_fk) REFERENCES inventory.city(city_id);
ALTER TABLE inventory.shipping_company_cities ADD CONSTRAINT fk_shipping_company FOREIGN KEY (shipping_company_id_fk) REFERENCES inventory.shipping_company(shipping_company_id);