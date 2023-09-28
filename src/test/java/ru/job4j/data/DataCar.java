package ru.job4j.data;

import ru.job4j.cars.model.Brand;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.CarModel;
import ru.job4j.cars.model.Engine;

public class DataCar {
    private static final Brand BRAND_1 = DataBrand.createBrand1();
    private static final Brand BRAND_2 = DataBrand.createBrand2();

    private static final Engine ENGINE_1 = DataEngine.createEngine1();
    private static final Engine ENGINE_3 = DataEngine.createEngine3();

    private static final CarModel CAR_MODEL_1 = DataCarModel.createModel1();
    private static final CarModel CAR_MODEL_2 = DataCarModel.createModel2();
    private static final CarModel CAR_MODEL_3 = DataCarModel.createModel3();

    public static Car createCar1() {
        Car car1 = new Car();
        car1.setId(1);
        car1.setEngine(ENGINE_1);
        car1.setBrand(BRAND_2);
        car1.setCarModel(CAR_MODEL_2);
        return car1;
    }

    public static Car createCar2() {
        Car car2 = new Car();
        car2.setId(2);
        car2.setEngine(ENGINE_3);
        car2.setBrand(BRAND_2);
        car2.setCarModel(CAR_MODEL_1);
        return car2;
    }

    public static Car createCar3() {
        Car car3 = new Car();
        car3.setId(3);
        car3.setEngine(ENGINE_1);
        car3.setBrand(BRAND_1);
        car3.setCarModel(CAR_MODEL_3);
        return car3;
    }
}
