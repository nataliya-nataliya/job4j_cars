package ru.job4j.cars.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.User;
import ru.job4j.data.DataUser;

class HbmUserRepositoryTest {
    private HbmUserRepository userRepository;
    private User user;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        userRepository = new HbmUserRepository(crudRepository);
        user = DataUser.createUser1();
    }

    @Test
    public void whenSaveUserThenGetUser() {
        User saveUser = userRepository.save(user).get();
        User expectedUser = userRepository.findByLoginAndPassword(user.getLogin(),
                user.getPassword()).get();
        Assertions.assertEquals(expectedUser, saveUser);
    }

    @Test
    public void whenSaveUserWithSameLoginsThenSaveOneUser() {
        int sizeBeforeSave = userRepository.findAllOrderById().size();
        var user1 = userRepository.save(user).get();
        var sameUser1 = userRepository.save(user);
        int sizeAfterSave = userRepository.findAllOrderById().size();
        Assertions.assertFalse(sameUser1.isPresent());
        Assertions.assertEquals(sizeBeforeSave + 1, sizeAfterSave);
    }

    @AfterEach
    public void close() {
        userRepository.deleteById(user.getId());
        Provider.close();
    }
}
