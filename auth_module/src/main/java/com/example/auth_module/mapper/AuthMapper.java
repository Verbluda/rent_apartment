package com.example.auth_module.mapper;

import com.example.auth_module.model.dto.UserRegistrationRequestDto;
import com.example.auth_module.model.entity.UserEntity;
import com.example.auth_module.service.impl.Base64EncoderDecoder;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AuthMapper {

    UserEntity userDtoToUserEntity(UserRegistrationRequestDto user);

    @AfterMapping
    default void afterUserDtoToUserEntity(@MappingTarget UserEntity userEntity, UserRegistrationRequestDto userDto) {

        userEntity.setDateRegistration(LocalDateTime.now());
        userEntity.setPassword(Base64EncoderDecoder.encode(userDto.getPassword()));
    }
}
