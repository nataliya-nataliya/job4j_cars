package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<Post> save(Post post) {
        Optional<Post> optionalPost;
        try {
            crudRepository.run(session -> session.persist(post));
            optionalPost = Optional.of(post);
        } catch (PersistenceException e) {
            optionalPost = Optional.empty();
        }
        return optionalPost;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run("delete Post where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public boolean update(Post post) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run(session -> session.merge(post));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post f where f.id = :fId",
                Post.class, Map.of("fId", id));
    }

    @Override
    public Collection<Post> findAllOrderByCreated() {
        return crudRepository.query("from Post f order by f.id", Post.class);
    }

    @Override
    public Collection<Post> findAllWhereCreatedIsTodayOrderByCreated() {
        return crudRepository.query("from Post f where f.created :: date = "
                        + "current_date order by f.created",
                Post.class);
    }

    @Override
    public Collection<Post> findAllWhereFileIsNotNullOrderByCreated() {
        return crudRepository.query("from Post f where f.file is not null order by f.created",
                Post.class);
    }

    @Override
    public Collection<Post> findPostByCarBrandOrderByCreated(int id) {
        return crudRepository.query("from Post f join f.car c join c.brand b where b.id = :fId",
                Post.class, Map.of("bId", id));
    }
}
