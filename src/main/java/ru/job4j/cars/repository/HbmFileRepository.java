package ru.job4j.cars.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.cars.model.File;

import javax.persistence.PersistenceException;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbmFileRepository implements FileRepository {
    private final CrudRepository crudRepository;

    @Override
    public Optional<File> save(File file) {
        Optional<File> fileOptional;
        try {
            crudRepository.run(session -> session.persist(file));
            fileOptional = Optional.of(file);
        } catch (PersistenceException e) {
            fileOptional = Optional.empty();
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
        boolean isCompletedTransaction;
        try {
            crudRepository.run("delete File where id = :fId", Map.of("fId", id));
            isCompletedTransaction = true;
        } catch (PersistenceException e) {
            isCompletedTransaction = false;
        }
        return isCompletedTransaction;
    }
}
