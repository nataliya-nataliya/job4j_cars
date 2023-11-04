package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.cars.dto.PostDto;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.repository.PostRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimplePostService implements PostService {
    private final PostRepository postRepository;

    @Override
    public Optional<Post> save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public boolean deleteById(int id) {
        return postRepository.deleteById(id);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.update(post);
    }

    @Override
    public boolean updateStatus(Post post) {
        return postRepository.updateStatus(post);
    }

    @Override
    public Optional<PostDto> findById(int id, String userCurrentTimeZone) {
        return convertCreatedOfPost(postRepository.findById(id), userCurrentTimeZone);
    }

    @Override
    public Collection<PostDto> findAllOrderByCreatedDesc(String userCurrentTimeZone) {
        return convertCreatedOfPostsList(postRepository.findAllOrderByCreatedDesc(),
                userCurrentTimeZone);
    }

    @Override
    public Collection<PostDto> findByActiveStatusOrderById(boolean status,
                                                           String userCurrentTimeZone) {
        return convertCreatedOfPostsList(postRepository
                .findAllWhereFindByStatusOrderByCreated(status), userCurrentTimeZone);
    }

    @Override
    public Collection<PostDto> findAllWhereCreatedIsDateOrderByCreated(LocalDate date,
                                                                       String userCurrentTimeZone) {
        return convertCreatedOfPostsList(postRepository
                .findAllWhereCreatedIsDateOrderByCreated(date), userCurrentTimeZone);
    }

    @Override
    public Collection<PostDto> findAllWhereFileIsNotNullOrderByCreated(String userCurrentTimeZone) {
        return convertCreatedOfPostsList(postRepository
                .findAllWhereFileIsNotNullOrderByCreated(), userCurrentTimeZone);
    }

    @Override
    public Collection<PostDto> findPostByCarBrandOrderByCreated(int id, String userCurrentTimeZone) {
        return convertCreatedOfPostsList(postRepository
                .findPostByCarBrandOrderByCreated(id), userCurrentTimeZone);
    }

    private Collection<PostDto> convertCreatedOfPostsList(Collection<Post> postsList,
                                                          String userCurrentTimeZone) {
        List<PostDto> postsDtoList = new ArrayList<>();
        for (Post post : postsList) {
            var userCreatedTaskTimeZone = post.getUser().getTimezone();
            LocalDateTime created = post.getCreated();
            if (!userCurrentTimeZone.equals(userCreatedTaskTimeZone)) {
                ZonedDateTime zonedCreated = created.atZone(ZoneId.of(userCreatedTaskTimeZone));
                ZonedDateTime convertedTime = zonedCreated
                        .withZoneSameInstant(ZoneId.of(userCurrentTimeZone));
                post.setCreated(convertedTime.toLocalDateTime());
            }
            postsDtoList.add(new PostDto(post.getDescription(), created, post.getCreated(),
                    post.getUser(),
                    post.getPriceList().get(post.getPriceList().size() - 1),
                    post.getFileList(), post.getCar().getBrand(), post.getCar().getEngine(),
                    post.getCar().getCarModel(), post.isStatus()));
        }
        return postsDtoList;
    }

    private Optional<PostDto> convertCreatedOfPost(Optional<Post> optionalPost,
                                                   String userCurrentTimeZone) {
        var post = optionalPost.get();
        var userCreatedPostTimeZone = post.getUser().getTimezone();
        LocalDateTime created = post.getCreated();
        if (!userCurrentTimeZone.equals(userCreatedPostTimeZone)) {
            ZonedDateTime zonedCreated = created.atZone(ZoneId.of(userCreatedPostTimeZone));
            ZonedDateTime convertedTime = zonedCreated
                    .withZoneSameInstant(ZoneId.of(userCurrentTimeZone));
            post.setCreated(convertedTime.toLocalDateTime());
        }
        return Optional.of(new PostDto(post.getDescription(), created, post.getCreated(),
                post.getUser(),
                post.getPriceList().get(post.getPriceList().size() - 1),
                post.getFileList(), post.getCar().getBrand(), post.getCar().getEngine(),
                post.getCar().getCarModel(), post.isStatus()));
    }
}
