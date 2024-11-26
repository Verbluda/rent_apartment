package com.example.rent_module.mapper;

import com.example.rent_module.model.dto.ApartmentRequestDto;
import com.example.rent_module.model.dto.ApartmentResponseDto;
import com.example.rent_module.model.entity.AddressEntity;
import com.example.rent_module.model.entity.ApartmentEntity;
import com.example.rent_module.model.entity.BookingInfoEntity;
import com.example.rent_module.model.entity.UserEntity;
import com.example.rent_module.service.impl.Base64EncoderDecoder;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.IOException;
import java.time.LocalDate;

import static java.util.Objects.isNull;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RentMapper {

    @Mapping(target = "city", source = "cityValue")
    AddressEntity apartmentRequestDtoToAddressEntity(ApartmentRequestDto apartRequest);

    @AfterMapping
    default void afterApartmentRequestDtoToAddressEntity(@MappingTarget AddressEntity addressEntity, ApartmentRequestDto apartRequest) {

        addressEntity.setApartment(apartmentRequestDtoToApartmentEntity(apartRequest));
    }

    ApartmentEntity apartmentRequestDtoToApartmentEntity(ApartmentRequestDto apartRequest);

    @Mapping(target = "cityValue", source = "address.city")
    @Mapping(target = "street", source = "address.street")
    @Mapping(target = "numberOfHouse", source = "address.numberOfHouse")
    @Mapping(target = "numberOfApartment", source = "address.numberOfApartment")
    ApartmentResponseDto apartmentEntityToApartmentResponseDto(ApartmentEntity apartmentEntity) throws IOException;

    @AfterMapping
    default void afterApartmentEntityToApartmentResponseDto(@MappingTarget ApartmentResponseDto apartmentResponse, ApartmentEntity apartmentEntity) throws IOException {
        if (!isNull(apartmentEntity.getPhotoEntity())) {
            byte[] photoOfApartmentCoded = apartmentEntity.getPhotoEntity().getPhotoOfApartment();
            byte[] photo = Base64EncoderDecoder.decode(photoOfApartmentCoded);
            apartmentResponse.setPhoto(photo);
        }
    }

    @Mapping(target = "id", source = "apartmentEntity.id")
    BookingInfoEntity prepareBookingInfoEntityFromParams(LocalDate startDate, LocalDate endDate, ApartmentEntity apartmentEntity, UserEntity userEntity);
}

