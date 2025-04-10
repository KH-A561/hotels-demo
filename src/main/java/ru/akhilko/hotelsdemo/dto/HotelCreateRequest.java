package ru.akhilko.hotelsdemo.dto;

import jakarta.validation.constraints.NotBlank;

public record HotelCreateRequest(@NotBlank String title,
                                 @NotBlank Long addressId,
                                 String rating,
                                 String notes) {
}