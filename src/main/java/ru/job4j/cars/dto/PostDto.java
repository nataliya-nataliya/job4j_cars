package ru.job4j.cars.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.cars.model.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private String description;
    private LocalDateTime created;
    private LocalDateTime convertedCreated;
    private User user;
    private Price price;
    private List<File> fileList;
    private Brand brand;
    private Engine engine;
    private CarModel carModel;
    private boolean status;
}
