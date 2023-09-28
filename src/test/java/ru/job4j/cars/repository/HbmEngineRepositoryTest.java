package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Engine;
import ru.job4j.data.DataEngine;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class HbmEngineRepositoryTest {
    private HbmEngineRepository engineRepository;
    private Engine engine1;
    private Engine engine2;
    private Engine engine3;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        engineRepository = new HbmEngineRepository(crudRepository);
        engine1 = DataEngine.createEngine1();
        engine2 = DataEngine.createEngine2();
        engine3 = DataEngine.createEngine3();
    }

    @Test
    public void whenFindAllOrderByIdEngines() {
        List<Engine> expectedEngines = Stream.of(engine1, engine2, engine3)
                .sorted(Comparator.comparing(Engine::getId))
                .toList();
        Collection<Engine> actualEngines = engineRepository.findAllOrderById();
        Assertions.assertEquals(expectedEngines, actualEngines);
    }

    @AfterEach
    public void close() {
        Provider.close();
    }
}
