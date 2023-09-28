package ru.job4j;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.repository.CrudRepository;

public class Provider {
    private static StandardServiceRegistry registry;

    public static CrudRepository open() {
        registry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        return new CrudRepository(sf);
    }

    public static void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
