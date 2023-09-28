package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Brand;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HbmBrandRepository implements BrandRepository {
    private final CrudRepository crudRepository;

    @Override
    public Collection<Brand> findAllOrderById() {
        return crudRepository.query("from Brand order by id", Brand.class);
    }
}
