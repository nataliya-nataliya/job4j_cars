package ru.job4j.data;

import ru.job4j.cars.model.Owner;
import ru.job4j.cars.model.User;

public class DataOwner {

    public static Owner createOwner1(User user) {
        Owner owner = new Owner();
        owner.setName("Owner 1");
        owner.setUser(user);
        return owner;
    }
}
