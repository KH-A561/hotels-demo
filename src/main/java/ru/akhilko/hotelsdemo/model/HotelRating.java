package ru.akhilko.hotelsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum HotelRating {
    FIVE_STARS("*****"),
    FOUR_STARTS("****"),
    THREE_STARS("***"),
    TWO_STARS("**"),
    ONE_STAR("*");

    private final String stars;

    @Override
    public String toString() {
        return stars;
    }

    public static HotelRating fromString(String s) throws IllegalArgumentException {
        return Arrays.stream(HotelRating.values())
                .filter(v -> v.stars.equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("unknown value: " + s));
    }
}
