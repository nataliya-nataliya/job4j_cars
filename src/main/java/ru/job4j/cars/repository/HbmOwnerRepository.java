package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Owner;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmOwnerRepository implements OwnerRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<Owner> save(Owner owner) {
        Optional<Owner> optionalOwner;
        try {
            crudRepository.run(session -> session.persist(owner));
            optionalOwner = Optional.of(owner);
        } catch (PersistenceException e) {
            optionalOwner = Optional.empty();
        }
        return optionalOwner;
    }

    @Override
    public Optional<Owner> findById(int id) {
        return crudRepository.optional(
                "from Owner f where f.id = :fId", Owner.class,
                Map.of("fId", id)
        );
    }
}
