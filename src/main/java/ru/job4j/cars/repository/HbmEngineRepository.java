package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Engine;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HbmEngineRepository implements EngineRepository {
    private final CrudRepository crudRepository;

    @Override
    public Collection<Engine> findAllOrderById() {
        return crudRepository.query("from Engine f order by f.id", Engine.class);
    }
}
