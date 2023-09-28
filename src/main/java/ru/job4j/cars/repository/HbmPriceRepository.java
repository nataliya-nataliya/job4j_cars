package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Price;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriceRepository implements PriceRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<Price> save(Price price) {
        Optional<Price> optionalPrice;
        try {
            crudRepository.run(session -> session.persist(price));
            optionalPrice = Optional.of(price);
        } catch (PersistenceException e) {
            optionalPrice = Optional.empty();
        }
        return optionalPrice;
    }

    @Override
    public Optional<Price> findById(int id) {
        return crudRepository.optional(
                "from Price f where f.id = :fId", Price.class,
                Map.of("fId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run("delete Price where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }
}
