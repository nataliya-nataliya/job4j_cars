package ru.job4j.data;

import ru.job4j.cars.model.CarModel;

public class DataCarModel {

    public static CarModel createModel1() {
        CarModel model = new CarModel();
        model.setId(1);
        model.setName("Model 1");
        return model;
    }

    public static CarModel createModel2() {
        CarModel model = new CarModel();
        model.setId(2);
        model.setName("Model 2");
        return model;
    }

    public static CarModel createModel3() {
        CarModel model = new CarModel();
        model.setId(3);
        model.setName("Model 3");
        return model;
    }
}
