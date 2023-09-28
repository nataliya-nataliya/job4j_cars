package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.CarModel;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HbmCarModelRepository implements CarModelRepository {
    private final CrudRepository crudRepository;

    @Override
    public Collection<CarModel> findAllOrderById() {
        return crudRepository.query("from CarModel order by id", CarModel.class);
    }
}
