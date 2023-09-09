package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Car;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCarRepository implements CarRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<Car> save(Car car) {
        Optional<Car> optionalCar;
        try {
            crudRepository.run(session -> session.persist(car));
            optionalCar = Optional.of(car);
        } catch (PersistenceException e) {
            optionalCar = Optional.empty();
        }
        return optionalCar;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run(
                    "delete Car where id = :fId",
                    Map.of("fId", id)
            );
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public boolean update(Car car) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run(session -> session.merge(car));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public Optional<Car> findById(int id) {
        return crudRepository.optional(
                "from Car f where f.id = :fId", Car.class,
                Map.of("fId", id)
        );
    }
}
