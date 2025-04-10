package ru.akhilko.hotelsdemo.service;

import ru.akhilko.hotelsdemo.dto.HotelCreateRequest;
import ru.akhilko.hotelsdemo.dto.HotelResponse;
import ru.akhilko.hotelsdemo.exception.ResponseStatusException;

import java.util.List;

public interface HotelService {
    HotelResponse create(HotelCreateRequest request) throws ResponseStatusException;

    void delete(Long id);

    List<HotelResponse> list(Integer offset, Integer limit, Boolean isSortedByTitle);
}
