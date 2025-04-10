package ru.akhilko.hotelsdemo.mapper;

import org.mapstruct.*;
import ru.akhilko.hotelsdemo.dto.HotelCreateRequest;
import ru.akhilko.hotelsdemo.dto.HotelResponse;
import ru.akhilko.hotelsdemo.entity.HotelEntity;
import ru.akhilko.hotelsdemo.model.Hotel;

import java.util.List;

@Named("HotelMapper")
@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface HotelMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", expression = "java(HotelRating.fromString(dto.rating()))")
    @Mapping(target = "address", ignore = true)
    Hotel dtoToModel(HotelCreateRequest dto);

    @Named("modelToDto")
    @Mapping(target = "rating", expression = "java(model.rating().toString())")
    HotelResponse modelToDto(Hotel model);

    HotelEntity modelToDao(Hotel model);

    @Named("daoToModel")
    Hotel daoToModel(HotelEntity dao);

    @IterableMapping(qualifiedByName = "daoToModel")
    List<Hotel> daoToModel(List<HotelEntity> hotelEntity);

    @IterableMapping(qualifiedByName = "modelToDto")
    List<HotelResponse> modelToDto(List<Hotel> model);
}
