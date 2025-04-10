package ru.akhilko.hotelsdemo.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.akhilko.hotelsdemo.model.HotelRating;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<HotelRating, String> {
    @Override
    public String convertToDatabaseColumn(HotelRating rating) {
        return rating.toString();
    }

    @Override
    public HotelRating convertToEntityAttribute(String s) {
        return HotelRating.fromString(s);
    }
}
