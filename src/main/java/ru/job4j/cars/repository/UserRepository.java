package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Save in database.
     *
     * @param user user.
     * @return user with id.
     */
    public User create(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return user;
    }

    /**
     * Update in the user base.
     *
     * @param user user.
     */
    public void update(User user) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "update User set login = :fLogin, password = "
                                    + ":fPassword WHERE id = :fId")
                    .setParameter("fLogin", user.getLogin())
                    .setParameter("fPassword", user.getPassword())
                    .setParameter("fId", user.getId())
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Delete user by id.
     *
     * @param userId ID
     */
    public void delete(int userId) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.createQuery(
                            "delete User where id = :fUserId")
                    .setParameter("fUserId", userId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * List user sorted by id.
     *
     * @return list of users.
     */
    public List<User> findAllOrderById() {
        Session session = sf.openSession();
        List<User> userList = null;
        try {
            session.beginTransaction();
            userList = session.createQuery(
                    "from User", User.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return userList;
    }

    /**
     * Find user by ID
     *
     * @return user.
     */
    public Optional<User> findById(int userId) {
        Session session = sf.openSession();
        Query<User> query = null;
        try {
            session.beginTransaction();
            query = session.createQuery(
                            "from User as u where u.id = :fId", User.class)
                    .setParameter("fId", userId);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        assert query != null;
        return query.uniqueResultOptional();
    }

    /**
     * List of users by login LIKE %key%
     *
     * @param key key
     * @return list of users.
     */
    public List<User> findByLikeLogin(String key) {
        Session session = sf.openSession();
        List<User> userList = null;
        try {
            session.beginTransaction();
            userList = session.createQuery(
                            "from User as u where u.login like :fKey", User.class)
                    .setParameter("fKey", "%" + key + "%").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return userList;
    }

    /**
     * Find a user by login.
     *
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        Session session = sf.openSession();
        Query<User> query = null;
        try {
            session.beginTransaction();
            query = session.createQuery(
                            "from User as u where u.login = :fLogin", User.class)
                    .setParameter("fLogin", login);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        assert query != null;
        return query.uniqueResultOptional();
    }
}
