package com.example.rent_module.service;

import com.example.rent_module.model.dto.geo_coder.GeoCoderResponseDto;
import com.example.rent_module.model.dto.weather.WeatherResponseDto;

public interface IntegrationService {

    GeoCoderResponseDto findApartmentByLocation(String latitude, String longitude);

    WeatherResponseDto findWeatherByLocation(String latitude, String longitude);
}
