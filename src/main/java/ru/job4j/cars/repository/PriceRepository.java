package ru.job4j.cars.repository;

import ru.job4j.cars.model.Price;

import java.util.Optional;

public interface PriceRepository {

    Optional<Price> save(Price price);

    Optional<Price> findById(int id);

    boolean deleteById(int id);

}
