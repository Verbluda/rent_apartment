package com.example.rent_module.service;

import com.example.rent_module.model.dto.ApartmentRequestDto;
import com.example.rent_module.model.dto.ApartmentResponseDto;
import com.example.rent_module.model.dto.weather.WeatherResponseDto;
import com.example.rent_module.model.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface RentService {

    String addApartment(ApartmentRequestDto apart);

    String addPhotoToApartment(Long id, MultipartFile multipartFile);

    ApartmentResponseDto findApartmentById(Long id);

    List<ApartmentResponseDto> findApartmentByLocation(String latitude, String longitude);

    WeatherResponseDto findWeatherByLocation(String latitude, String longitude);

    ApartmentResponseDto bookApartment(UserEntity user, Long id, LocalDate startDate, LocalDate endDate);
}
