package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.CarOwner;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.data.DataCarOwner;
import ru.job4j.data.DataOwner;

class HbmCarOwnerRepositoryTest {
    private HbmCarOwnerRepository carOwnerRepository;
    private HbmOwnerRepository ownerRepository;
    private CarOwner carOwner;
    private Owner owner;
    private Car updateCar;
    private CarOwner actualCarOwner;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        HbmUserRepository userRepository = new HbmUserRepository(crudRepository);
        ownerRepository = new HbmOwnerRepository(crudRepository);
        HbmCarRepository carRepository = new HbmCarRepository(crudRepository);
        User user = userRepository.findById(1).get();
        Car car = carRepository.findById(1).get();
        updateCar = carRepository.findById(2).get();
        owner = ownerRepository.save(DataOwner.createOwner1(user)).get();
        carOwnerRepository = new HbmCarOwnerRepository(crudRepository);
        carOwner = DataCarOwner.createCarOwner1(owner, car);
        actualCarOwner = carOwnerRepository.save(carOwner).get();
    }

    @Test
    public void whenSaveCarOwnerThenSaveCarOwner() {
        Assertions.assertEquals(carOwner, actualCarOwner);
    }

    @Test
    public void whenFindByIdCarOwnerThenGetCarOwner() {
        Assertions.assertEquals(carOwner, carOwnerRepository.
                findById(actualCarOwner.getId()).get());
    }

    @Test
    public void whenFindByIdCarOwnerNonExistentThenGetEmptyCarOwner() {
        Assertions.assertFalse(carOwnerRepository.findById(actualCarOwner.getId() + 1).isPresent());
    }

    @Test
    public void whenCarOwnerUpdateThenUpdateCarOwner() {
        Car beforeCar = actualCarOwner.getCar();
        carOwnerRepository.update(DataCarOwner.createCarOwner2(
                actualCarOwner.getId(), owner, updateCar));
        Assertions.assertNotEquals(beforeCar, carOwnerRepository.
                findById(actualCarOwner.getId()).get().getCar());
    }

    @AfterEach
    public void close() {
        carOwnerRepository.deleteById(carOwner.getId());
        ownerRepository.deleteById(owner.getId());
        Provider.close();
    }
}
