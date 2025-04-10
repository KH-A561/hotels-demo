package ru.akhilko.hotelsdemo.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ru.akhilko.hotelsdemo.entity.HotelEntity;

@ApplicationScoped
public class HotelRepository implements PanacheRepository<HotelEntity> {
}
