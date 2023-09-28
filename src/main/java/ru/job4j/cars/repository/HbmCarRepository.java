package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {
    private final CrudRepository crudRepository;

    @Override
    public Collection<Car> findAllOrderById() {
        return crudRepository.query("from Car order by id", Car.class);
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                "from Car f where f.id = :fId", Car.class,
                Map.of("fId", id)
        );
    }
}
