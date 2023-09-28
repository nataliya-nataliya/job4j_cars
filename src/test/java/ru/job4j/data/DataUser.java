package ru.job4j.data;

import ru.job4j.cars.model.User;

public class DataUser {
    public static User createUser1() {
        User user = new User();
        user.setLogin("test1@email.com");
        user.setPassword("qwerty");
        return user;
    }

    public static User createUser2() {
        User user = new User();
        user.setLogin("test2@email.com");
        user.setPassword("qwerty");
        return user;
    }
}
