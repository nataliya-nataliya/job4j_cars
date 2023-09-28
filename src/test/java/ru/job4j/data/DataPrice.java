package ru.job4j.data;

import ru.job4j.cars.model.Price;

public class DataPrice {
    public static Price createPrice1() {
        Price price = new Price();
        price.setPrice(500);
        return price;
    }
}
