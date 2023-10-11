package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarOwner;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmCarOwnerRepository implements CarOwnerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmCarOwnerRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<CarOwner> save(CarOwner carOwner) {
        Optional<CarOwner> optionalCarOwner = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(carOwner));
            optionalCarOwner = Optional.of(carOwner);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", carOwner.getClass().getSimpleName());
        }
        return optionalCarOwner;
    }

    @Override
    public boolean update(CarOwner carOwner) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run(session -> session.merge(carOwner));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when updating a {} with ID {} from the database",
                    CarOwner.class.getSimpleName(), carOwner.getId());
        }
        return isCompletedTransaction;
    }

    @Override
    public Optional<CarOwner> findById(int id) {
        return crudRepository.optional(
                "from CarOwner c where c.id = :cId", CarOwner.class,
                Map.of("cId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete CarOwner where id = :cId", Map.of("cId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    CarOwner.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }
}
