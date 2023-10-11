package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmOwnerRepository implements OwnerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmOwnerRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<Owner> save(Owner owner) {
        Optional<Owner> optionalOwner = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(owner));
            optionalOwner = Optional.of(owner);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", owner.getClass().getSimpleName());
        }
        return optionalOwner;
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "from Owner o where o.id = :oId", Owner.class,
                Map.of("oId", id)
        );
    }

    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete Owner where id = :oId", Map.of("oId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    Owner.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }

    @Override
    public Collection<Owner> findAllOrderById() {
        return crudRepository.query("from Owner order by id", Owner.class);
    }
}
