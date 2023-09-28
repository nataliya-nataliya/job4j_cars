package ru.job4j.cars.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.File;
import ru.job4j.data.DataFile;

class HbmFileRepositoryTest {
    private HbmFileRepository fileRepository;
    private File file;
    private File saveFile;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        fileRepository = new HbmFileRepository(crudRepository);
        file = DataFile.createFile1();
        saveFile = fileRepository.save(file).get();
    }

    @Test
    public void whenSaveFileThenGetFile() {
        Assertions.assertEquals(file, saveFile);
    }

    @Test
    public void whenFindByIdFileThenGetFile() {
        Assertions.assertEquals(file, fileRepository.findById(saveFile.getId()).get());
    }

    @AfterEach
    public void close() {
        fileRepository.deleteById(saveFile.getId());
        Provider.close();
    }
}