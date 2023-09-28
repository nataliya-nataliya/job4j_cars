package ru.job4j.data;

import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;

import java.time.LocalDateTime;

public class DataPost {

    public static Post createPost1(Car car, User user) {
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setCar(car);
        post.setDescription("Desc 1");
        post.setUser(user);
        return post;
    }

    public static Post createPost2(Car car, User user) {
        Post post = new Post();
        post.setCreated(LocalDateTime.now());
        post.setCar(car);
        post.setDescription("Desc 2");
        post.setUser(user);
        return post;
    }
}
