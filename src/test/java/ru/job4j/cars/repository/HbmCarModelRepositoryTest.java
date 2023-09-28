package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.CarModel;
import ru.job4j.data.DataCarModel;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class HbmCarModelRepositoryTest {
    private HbmCarModelRepository carModelRepository;
    private CarModel carModel1;
    private CarModel carModel2;
    private CarModel carModel3;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        carModelRepository = new HbmCarModelRepository(crudRepository);
        carModel1 = DataCarModel.createModel1();
        carModel2 = DataCarModel.createModel2();
        carModel3 = DataCarModel.createModel3();
    }

    @Test
    public void whenFindAllOrderByIdEngines() {
        List<CarModel> expectedCarModels = Stream.of(carModel1, carModel2, carModel3)
                .sorted(Comparator.comparing(CarModel::getId))
                .toList();
        Collection<CarModel> actualCarModels = carModelRepository.findAllOrderById();
        Assertions.assertEquals(expectedCarModels, actualCarModels);
    }

    @AfterEach
    public void close() {
        Provider.close();
    }
}
