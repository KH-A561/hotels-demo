package ru.akhilko.hotelsdemo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String index;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String building;
}
