package ru.akhilko.hotelsdemo.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.akhilko.hotelsdemo.entity.AddressEntity;

@ApplicationScoped
public class AddressRepository implements PanacheRepository<AddressEntity> {
}
