package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Brand;
import ru.job4j.data.DataBrand;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class HbmBrandRepositoryTest {
    private HbmBrandRepository brandRepository;
    private Brand brand1;
    private Brand brand2;
    private Brand brand3;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        brandRepository = new HbmBrandRepository(crudRepository);
        brand1 = DataBrand.createBrand1();
        brand2 = DataBrand.createBrand2();
        brand3 = DataBrand.createBrand3();
    }

    @Test
    public void whenFindAllOrderByIdBrands() {
        List<Brand> expectedBrands = Stream.of(brand1, brand2, brand3)
                .sorted(Comparator.comparing(Brand::getId))
                .toList();
        Collection<Brand> actualBrands = brandRepository.findAllOrderById();
        Assertions.assertEquals(expectedBrands, actualBrands);
    }

    @AfterEach
    public void close() {
        Provider.close();
    }
}
