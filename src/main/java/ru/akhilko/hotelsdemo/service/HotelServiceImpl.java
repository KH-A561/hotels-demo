package ru.akhilko.hotelsdemo.service;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ru.akhilko.hotelsdemo.dto.HotelCreateRequest;
import ru.akhilko.hotelsdemo.dto.HotelResponse;
import ru.akhilko.hotelsdemo.entity.AddressEntity;
import ru.akhilko.hotelsdemo.entity.HotelEntity;
import ru.akhilko.hotelsdemo.exception.Exceptions;
import ru.akhilko.hotelsdemo.exception.ResponseStatusException;
import ru.akhilko.hotelsdemo.mapper.HotelMapper;
import ru.akhilko.hotelsdemo.model.Hotel;
import ru.akhilko.hotelsdemo.repo.AddressRepository;
import ru.akhilko.hotelsdemo.repo.HotelRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final HotelMapper hotelMapper;

    @Override
    @Transactional
    public HotelResponse create(HotelCreateRequest request) throws ResponseStatusException {
        Hotel hotel = hotelMapper.dtoToModel(request);
        Optional<AddressEntity> addressOpt = addressRepository.findByIdOptional(request.addressId());

        if (addressOpt.isEmpty()) {
            throw new ResponseStatusException(Exceptions.ADDRESS_NOT_FOUND);
        }
        HotelEntity entity = hotelMapper.modelToDao(hotel);
        entity.setAddress(addressOpt.get());

        hotelRepository.persist(entity);

        return hotelMapper.modelToDto(hotelMapper.daoToModel(entity));
    }

    @Override
    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<HotelResponse> list(Integer offset, Integer limit, Boolean isSortedByTitle) {
        int argOffset = Optional.ofNullable(offset).orElse(0);
        int argLimit = Optional.ofNullable(limit).orElse(0);
        boolean argIsSorted = Optional.ofNullable(isSortedByTitle).orElse(false);

        PanacheQuery<HotelEntity> baseQuery = argIsSorted
                ? hotelRepository.findAll(Sort.by("title"))
                : hotelRepository.findAll();

        PanacheQuery<HotelEntity> pageQuery = argOffset != 0 || argLimit != 0
                ? baseQuery.page(argOffset, argLimit)
                : baseQuery;

        List<Hotel> result = hotelMapper.daoToModel(pageQuery.list());

        return hotelMapper.modelToDto(result);
    }
}
