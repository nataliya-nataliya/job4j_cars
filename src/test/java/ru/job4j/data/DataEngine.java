package ru.job4j.data;

import ru.job4j.cars.model.Engine;

public class DataEngine {
    public static Engine createEngine1() {
        Engine engine = new Engine();
        engine.setId(1);
        engine.setName("Engine 1");
        return engine;
    }

    public static Engine createEngine2() {
        Engine engine = new Engine();
        engine.setId(2);
        engine.setName("Engine 2");
        return engine;
    }

    public static Engine createEngine3() {
        Engine engine = new Engine();
        engine.setId(3);
        engine.setName("Engine 3");
        return engine;
    }
}
