package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Car;
import ru.job4j.data.DataCar;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class HbmCarRepositoryTest {
    private HbmCarRepository carRepository;
    private Car car1;
    private Car car2;
    private Car car3;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        carRepository = new HbmCarRepository(crudRepository);
        car1 = DataCar.createCar1();
        car2 = DataCar.createCar2();
        car3 = DataCar.createCar3();
    }

    @Test
    public void whenFindAllOrderByIdCars() {
        List<Car> expectedCars = Stream.of(car1, car2, car3)
                .sorted(Comparator.comparing(Car::getId))
                .toList();
        Collection<Car> actualCars = carRepository.findAllOrderById();
        Assertions.assertEquals(expectedCars, actualCars);
    }

    @Test
    public void whenFindByIdCarThenGetCar() {
        Assertions.assertEquals(car2, carRepository.findById(2).get());
    }

    @Test
    public void whenFindByNonExistentIdCarThenGetCar() {
        Assertions.assertFalse(carRepository.findById(car3.getId() + 1).isPresent());
    }

    @AfterEach
    public void close() {
        Provider.close();
    }
}
