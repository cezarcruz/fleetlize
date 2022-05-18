--liquibase formatted sql

--changeset cezar:3

ALTER TABLE car_manager.CAR_MODEL MODIFY COLUMN CATEGORY_ID int(11) NOT NULL;

--rollback ALTER TABLE car_manager.CAR_MODEL MODIFY COLUMN CATEGORY_ID int(11) DEFAULT NULL;