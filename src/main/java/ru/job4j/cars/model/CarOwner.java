package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "cars_owners")
public class CarOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    @ManyToOne

    @JoinColumn(name = "car_id", foreignKey = @ForeignKey(name = "CAR_ID_FK"))
    private Car car;
    @ManyToOne
    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name = "OWNER_ID_FK"))
    private Owner owner;

    @Column(name = "start_own")
    private LocalDateTime startOwn;
    @Column(name = "end_own")
    private LocalDateTime endOwn;
}
