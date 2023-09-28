package ru.job4j.cars.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Price;
import ru.job4j.data.DataPrice;

class HbmPriceRepositoryTest {
    private HbmPriceRepository priceRepository;
    private Price price;
    private Price savePrice;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        priceRepository = new HbmPriceRepository(crudRepository);
        price = DataPrice.createPrice1();
        savePrice = priceRepository.save(price).get();
    }

    @Test
    public void whenSavePriceThenGetFile() {
        Assertions.assertEquals(price, savePrice);
    }

    @Test
    public void whenFindByIdPriceThenGetFile() {
        Assertions.assertEquals(price, priceRepository.findById(savePrice.getId()).get());
    }

    @AfterEach
    public void close() {
        priceRepository.deleteById(savePrice.getId());
        Provider.close();
    }
}
