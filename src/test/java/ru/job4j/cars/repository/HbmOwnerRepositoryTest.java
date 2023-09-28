package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;
import ru.job4j.data.DataOwner;
import ru.job4j.data.DataUser;

class HbmOwnerRepositoryTest {

    private HbmOwnerRepository ownerRepository;
    private Owner owner;
    private HbmUserRepository userRepository;
    private Owner saveOwner;
    private User saveUser;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        userRepository = new HbmUserRepository(crudRepository);
        ownerRepository = new HbmOwnerRepository(crudRepository);
        User user = DataUser.createUser1();
        saveUser = userRepository.save(user).get();
        owner = DataOwner.createOwner1(saveUser);
        saveOwner = ownerRepository.save(owner).get();
    }

    @Test
    public void whenSaveOwnerThenGetOwner() {
        Assertions.assertEquals(owner, saveOwner);
    }

    @Test
    public void whenFindByIdOwnerThenGetOwner() {
        Assertions.assertEquals(owner, ownerRepository.findById(saveOwner.getId()).get());
    }

    @AfterEach
    public void close() {
        ownerRepository.deleteById(saveOwner.getId());
        userRepository.deleteById(saveUser.getId());
        Provider.close();
    }
}
