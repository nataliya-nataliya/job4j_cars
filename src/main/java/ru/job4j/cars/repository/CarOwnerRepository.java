package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarOwner;

import java.util.Optional;

public interface CarOwnerRepository {
    Optional<CarOwner> save(CarOwner carOwner);

    boolean update(CarOwner carOwner);

    Optional<CarOwner> findById(int id);

    boolean deleteById(int id);
}
