package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Price;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriceRepository implements PriceRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmPriceRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<Price> save(Price price) {
        Optional<Price> optionalPrice = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(price));
            optionalPrice = Optional.of(price);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", price.getClass().getSimpleName());
        }
        return optionalPrice;
    }

    @Override
    public Optional<Price> findById(int id) {
        return crudRepository.optional(
                "from Price p where p.id = :pId", Price.class,
                Map.of("pId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete Price where id = :pId", Map.of("pId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    Price.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }
}
