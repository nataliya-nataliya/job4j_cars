package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.Post;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmPostRepository implements PostRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmPostRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<Post> save(Post post) {
        Optional<Post> optionalPost = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(post));
            optionalPost = Optional.of(post);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", post.getClass().getSimpleName());
        }
        return optionalPost;
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete Post where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    Post.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }

    @Override
    public boolean update(Post post) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run(session -> session.merge(post));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when updating a {} with ID {} from the database",
                    Post.class.getSimpleName(), post.getId());
        }
        return isCompletedTransaction;
    }

    @Override
    public Optional<Post> findById(int id) {
        return crudRepository.optional("from Post f where f.id = :fId",
                Post.class, Map.of("fId", id));
    }

    @Override
    public Collection<Post> findAllOrderByCreatedDesc() {
        return crudRepository.query("from Post p order by p.created desc", Post.class);
    }

    @Override
    public Collection<Post> findAllWhereCreatedIsDateOrderByCreated(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atStartOfDay().plusDays(1);
        return crudRepository.query("from Post p "
                        + "where p.created >= :startDate and p.created < :endDate order by created",
                Post.class,
                Map.of("startDate", startOfDay, "endDate", endOfDay));
    }

    @Override
    public Collection<Post> findAllWhereFileIsNotNullOrderByCreated() {
        return crudRepository.query("from Post p join fetch p.fileList order by created",
                Post.class);
    }

    @Override
    public Collection<Post> findPostByCarBrandOrderByCreated(int id) {
        return crudRepository.query("select p from Post p join p.car c join c.brand b "
                        + "where b.id = :bId order by created",
                Post.class, Map.of("bId", id));
    }
}
