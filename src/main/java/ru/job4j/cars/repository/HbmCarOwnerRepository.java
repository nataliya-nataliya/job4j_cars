package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarOwner;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCarOwnerRepository implements CarOwnerRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<CarOwner> save(CarOwner carOwner) {
        Optional<CarOwner> optionalCarOwner;
        try {
            crudRepository.run(session -> session.persist(carOwner));
            optionalCarOwner = Optional.of(carOwner);
        } catch (PersistenceException e) {
            optionalCarOwner = Optional.empty();
        }
        return optionalCarOwner;
    }

    @Override
    public boolean update(CarOwner carOwner) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run(session -> session.merge(carOwner));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public Optional<CarOwner> findById(int id) {
        return crudRepository.optional(
                "from CarOwner f where f.id = :fId", CarOwner.class,
                Map.of("fId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run("delete CarOwner where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }
}
