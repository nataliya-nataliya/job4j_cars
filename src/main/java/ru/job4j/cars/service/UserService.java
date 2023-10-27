package ru.job4j.cars.service;


import ru.job4j.cars.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.TimeZone;

public interface UserService {
    Optional<User> save(User user);

    Optional<User> findByLoginAndPassword(String login, String password);

    Optional<User> findById(int id);

    Collection<TimeZone> getAllTimeZone();

}
