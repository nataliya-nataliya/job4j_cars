package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarModel;

import java.util.Collection;

public interface CarModelRepository {
    Collection<CarModel> findAllOrderById();
}
