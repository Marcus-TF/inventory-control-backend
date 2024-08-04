-- Cria o schema chamado 'inventory'
CREATE SCHEMA inventory;

-- Use o schema 'inventory'
SET search_path TO inventory;

insert into profile
values('c74846b0-ec3d-46ad-9469-0c31d96bad04' , 'ADMIN');

insert into user
values('4feb0353-aa50-493a-9cb5-65ae2c5edc25' , 'USER');

