alter table cars add column brand_id int references car_brands(id);
alter table cars add column model_id int references car_models(id);
