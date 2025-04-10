package ru.akhilko.hotelsdemo.dto;

public record HotelResponse(Long id,
                            String title,
                            Address address,
                            String rating,
                            String notes) {
    public record Address(Long id, String index, String city, String street, String building) {
    }
}
