package ru.job4j.data;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.CarOwner;
import ru.job4j.cars.model.Owner;

import java.time.LocalDateTime;

public class DataCarOwner {

    public static CarOwner createCarOwner1(Owner owner, Car car) {
        CarOwner carOwner = new CarOwner();
        carOwner.setCar(car);
        carOwner.setOwner(owner);
        carOwner.setStartOwn(LocalDateTime.now().minusYears(1));
        carOwner.setEndOwn(LocalDateTime.now().minusDays(5));
        return carOwner;
    }

    public static CarOwner createCarOwner2(int id, Owner owner, Car car) {
        CarOwner carOwner = new CarOwner();
        carOwner.setId(id);
        carOwner.setCar(car);
        carOwner.setOwner(owner);
        carOwner.setStartOwn(LocalDateTime.now().minusYears(1));
        carOwner.setEndOwn(LocalDateTime.now().minusDays(5));
        return carOwner;
    }
}
