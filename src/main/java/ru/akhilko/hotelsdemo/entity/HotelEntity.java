package ru.akhilko.hotelsdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.akhilko.hotelsdemo.model.HotelRating;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    private HotelRating rating;

    private String notes;
}
