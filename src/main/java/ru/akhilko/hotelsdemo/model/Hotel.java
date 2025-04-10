package ru.akhilko.hotelsdemo.model;

public record Hotel(Long id, Address address, String title, HotelRating rating, String notes) {}
