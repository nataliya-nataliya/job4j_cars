package ru.job4j.data;


import ru.job4j.cars.model.File;

public class DataFile {
    public static File createFile1() {
        File file = new File();
        file.setName("File 1.jpg");
        file.setPath("folder" + java.io.File.separator + "File 1.jpg");
        return file;
    }
}
