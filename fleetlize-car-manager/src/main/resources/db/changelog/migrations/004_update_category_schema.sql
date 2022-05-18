--liquibase formatted sql

--changeset cezar:4

ALTER TABLE car_manager.CATEGORY MODIFY COLUMN DAILY_PRICE NUMERIC(10,2) NOT NULL;

--rollback ALTER TABLE car_manager.CAR_MODEL MODIFY COLUMN DAILY_PRICE int(11) NOT NULL;
