INSERT INTO engines (name) VALUES ('Engine 1');
INSERT INTO engines (name) VALUES ('Engine 2');
INSERT INTO engines (name) VALUES ('Engine 3');

INSERT INTO car_brands (name) VALUES ('Brand 1');
INSERT INTO car_brands (name) VALUES ('Brand 2');
INSERT INTO car_brands (name) VALUES ('Brand 3');

INSERT INTO car_models (name) VALUES ('Model 1');
INSERT INTO car_models (name) VALUES ('Model 2');
INSERT INTO car_models (name) VALUES ('Model 3');

INSERT INTO cars (engine_id, brand_id, model_id) VALUES (1, 2, 2);
INSERT INTO cars (engine_id, brand_id, model_id) VALUES (3, 2, 1);
INSERT INTO cars (engine_id, brand_id, model_id) VALUES (1, 1, 3);
