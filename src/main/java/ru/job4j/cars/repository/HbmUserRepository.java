package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmUserRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        Optional<User> optionalUser = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(user));
            optionalUser = Optional.of(user);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", user.getClass().getSimpleName());
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User u where u.login = :uLogin and "
                        + "u.password = :uPassword", User.class,
                Map.of("uLogin", login, "uPassword", password)
        );
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(
                "from User u where u.id = :uId", User.class,
                Map.of("uId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete User where id = :uId", Map.of("uId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    User.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }

    @Override
    public Collection<User> findAllOrderById() {
        return crudRepository.query("from User order by id", User.class);
    }
}
