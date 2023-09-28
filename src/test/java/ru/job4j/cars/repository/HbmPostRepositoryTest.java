package ru.job4j.cars.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.Provider;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.File;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.data.DataCar;
import ru.job4j.data.DataFile;
import ru.job4j.data.DataPost;
import ru.job4j.data.DataUser;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class HbmPostRepositoryTest {

    private HbmPostRepository postRepository;
    private User user1;
    private User user2;
    private File file1;
    private HbmUserRepository userRepository;
    private HbmCarRepository carRepository;
    private HbmPriceRepository priceRepository;
    private HbmFileRepository fileRepository;
    private Car car;
    private Post post1;
    private Post post2;
    private Post savePost1;
    private Post savePost2;

    @BeforeEach
    public void open() {
        CrudRepository crudRepository = Provider.open();
        postRepository = new HbmPostRepository(crudRepository);
        userRepository = new HbmUserRepository(crudRepository);
        priceRepository = new HbmPriceRepository(crudRepository);
        carRepository = new HbmCarRepository(crudRepository);
        fileRepository = new HbmFileRepository(crudRepository);
        user1 = DataUser.createUser1();
        file1 = DataFile.createFile1();
        carRepository = new HbmCarRepository(crudRepository);
        car = carRepository.findById(DataCar.createCar1().getId()).get();
        userRepository.save(user1);
        post1 = DataPost.createPost1(car, user1);
        savePost1 = postRepository.save(post1).get();
        user2 = DataUser.createUser2();
        userRepository.save(user2);
        post2 = DataPost.createPost2(car, user2);
        savePost2 = postRepository.save(post2).get();
    }

    @Test
    public void whenSavePostThenGetPost() {
        Assertions.assertEquals(post1, savePost1);

    }

    @Test
    public void whenUpdatePostThenGetUpdatedPost() {
        var beforeDesc = savePost1.getDescription();
        savePost1.setDescription("Desc 2");
        postRepository.update(post1);
        Assertions.assertNotEquals(beforeDesc, savePost1.getDescription());
    }

    @Test
    public void whenFindByIdPostThenGetPost() {
        Assertions.assertEquals(savePost1, postRepository.findById(savePost1.getId()).get());
    }

    @Test
    public void whenFindAllOrderByCreatedDescPosts() {

        List<Post> expectedPosts = Stream.of(post1, post2)
                .sorted(Comparator.comparing(Post::getCreated).reversed())
                .toList();
        Collection<Post> actualPosts = postRepository.findAllOrderByCreatedDesc();
        Assertions.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void whenFindAllWhereCreatedIsDateThenGetAllWhereCreatedIsDate() {
        LocalDate today = LocalDate.now();
        List<Post> expectedPosts = Stream.of(post1, post2)
                .sorted(Comparator.comparing(Post::getCreated))
                .toList();
        Collection<Post> actualPosts = postRepository
                .findAllWhereCreatedIsDateOrderByCreated(today);
        Assertions.assertEquals(expectedPosts, actualPosts);
    }

    @Test
    public void whenFindAllWhereFileIsNotNullThenGetPostsWithFilesOrderByCreated() {
        fileRepository.save(file1);
        post1.getFileList().add(file1);
        postRepository.update(post1);
        Assertions.assertEquals(1, postRepository.findAllWhereFileIsNotNullOrderByCreated().size());
        fileRepository.deleteById(file1.getId());
    }

    @Test
    public void whenFindPostByCarBrandOrderByCreatedThenGetPostList() {
        List<Post> expectedPosts = Stream.of(post1, post2)
                .sorted(Comparator.comparing(Post::getCreated))
                .toList();
        int idBrand = car.getBrand().getId();
        Assertions.assertEquals(expectedPosts, postRepository
                .findPostByCarBrandOrderByCreated(idBrand));
    }

    @AfterEach
    public void close() {
        postRepository.deleteById(post1.getId());
        userRepository.deleteById(user1.getId());
        postRepository.deleteById(post2.getId());
        userRepository.deleteById(user2.getId());
        Provider.close();
    }
}
