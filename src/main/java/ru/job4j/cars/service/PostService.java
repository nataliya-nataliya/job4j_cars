package ru.job4j.cars.service;

import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Post;


import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

public interface PostService {
    Optional<Post> save(Post post);

    boolean deleteById(int id);

    boolean update(Post post);

    boolean updateStatus(Post post);

    Optional<PostDto> findById(int id, String userCurrentTimeZone);

    Collection<PostDto> findAllOrderByCreatedDesc(String userCurrentTimeZone);

    Collection<PostDto> findByActiveStatusOrderById(boolean status, String userCurrentTimeZone);

    Collection<PostDto> findAllWhereCreatedIsDateOrderByCreated(LocalDate date,
                                                                String userCurrentTimeZone);

    Collection<PostDto> findAllWhereFileIsNotNullOrderByCreated(String userCurrentTimeZone);

    Collection<PostDto> findPostByCarBrandOrderByCreated(int id, String userCurrentTimeZone);

}
