package ru.job4j.cars.repository;

import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarRepository {
    Collection<Car> findAllOrderById();

    Optional<Car> findById(int id);
}
