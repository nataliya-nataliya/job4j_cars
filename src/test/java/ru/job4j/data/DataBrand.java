package ru.job4j.data;

import ru.job4j.cars.model.Brand;

public class DataBrand {
    public static Brand createBrand1() {
        Brand brand = new Brand();
        brand.setId(1);
        brand.setName("Brand 1");
        return brand;
    }

    public static Brand createBrand2() {
        Brand brand = new Brand();
        brand.setId(2);
        brand.setName("Brand 2");
        return brand;
    }

    public static Brand createBrand3() {
        Brand brand = new Brand();
        brand.setId(3);
        brand.setName("Brand 3");
        return brand;
    }
}
