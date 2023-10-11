package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;
import ru.job4j.cars.model.Post;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmFileRepository implements FileRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(HbmFileRepository.class);
    private final CrudRepository crudRepository;

    @Override
    public Optional<File> save(File file) {
        Optional<File> fileOptional = Optional.empty();
        try {
            crudRepository.run(session -> session.persist(file));
            fileOptional = Optional.of(file);
        } catch (PersistenceException e) {
            LOGGER.error("Error when saving {} to database", file.getClass().getSimpleName());
        }
        return fileOptional;
    }

    @Override
    public Optional<File> findById(int id) {
        return crudRepository.optional(
                "from File f where f.id = :fId", File.class,
                Map.of("fId", id)
        );
    }

    @Override
    public boolean deleteById(int id) {
        boolean isCompletedTransaction = false;
        try {
            crudRepository.run("delete File where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            LOGGER.error("Error when deleting a {} with ID {} from the database",
                    Post.class.getSimpleName(), id);
        }
        return isCompletedTransaction;
    }
}
