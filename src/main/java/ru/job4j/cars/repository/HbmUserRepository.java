package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.User;

import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmUserRepository implements UserRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<User> save(User user) {
        Optional<User> optionalUser;
        try {
            crudRepository.run(session -> session.persist(user));
            optionalUser = Optional.of(user);
        } catch (PersistenceException e) {
            optionalUser = Optional.empty();
        }
        return optionalUser;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                "from User as u where u.login = :fLogin and "
                        + "u.password = :fPassword", User.class,
                Map.of("fLogin", login, "fPassword", password)
        );
    }

    @Override
    public Optional<User> findById(int id) {
        return crudRepository.optional(
                "from User as u where u.id = :fId", User.class,
                Map.of("fId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction;
        try {
            crudRepository.run("delete User where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }

    @Override
    public Collection<User> findAllOrderById() {
        return crudRepository.query("from User order by id", User.class);
    }
}
