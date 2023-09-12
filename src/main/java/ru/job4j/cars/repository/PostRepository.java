package ru.job4j.cars.repository;

import ru.job4j.cars.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {
    Optional<Post> save(Post post);

    boolean deleteById(int id);

    boolean update(Post post);

    Optional<Post> findById(int id);

    Collection<Post> findAllOrderByCreated();

    Collection<Post> findAllWhereCreatedIsTodayOrderByCreated();

    Collection<Post> findAllWhereFileIsNotNullOrderByCreated();

    Collection<Post> findPostByCarBrandOrderByCreated(int id);
}
