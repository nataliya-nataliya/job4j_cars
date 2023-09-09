package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.PriceHistory;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPriceHistoryRepository implements PriceHistoryRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<PriceHistory> findById(int id) {
        return crudRepository.optional(
                "from PriceHistory f where f.id = :fId", PriceHistory.class,
                Map.of("fId", id)
        );
    }
}
