package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;

import java.util.Collection;

public interface BrandRepository {
    Collection<Brand> findAllOrderById();
}
