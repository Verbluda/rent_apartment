package com.example.rent_module.service.impl;

import com.example.rent_module.model.dto.geo_coder.GeoCoderResponseDto;
import com.example.rent_module.model.dto.weather.WeatherResponseDto;
import com.example.rent_module.model.entity.IntegrationEntity;
import com.example.rent_module.repository.IntegrationRepository;
import com.example.rent_module.service.IntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class IntegrationServiceImpl implements IntegrationService {

    private final IntegrationRepository integrationRepository;
    RestTemplate restTemplate = new RestTemplate();
    public static final String GEO = "GEO";
    public static final String WEATHER = "WEATHER";
    public static final String NOT_FOUND_INTEGRATION_INFO = "Information about this integration is not found";

    @Override
    public GeoCoderResponseDto findApartmentByLocation(String latitude, String longitude) {
        return restTemplate.exchange(prepareUrlForGeo(latitude, longitude),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                GeoCoderResponseDto.class).getBody();
    }

    @Override
    public WeatherResponseDto findWeatherByLocation(String latitude, String longitude) {
        return restTemplate.exchange(preparedUrlForWeather(latitude, longitude),
                HttpMethod.GET,
                new HttpEntity<>(null, null),
                WeatherResponseDto.class).getBody();
    }

    private String prepareUrlForGeo(String latitude, String longitude) {
        IntegrationEntity integrationEntity = integrationRepository.findById(GEO)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_INTEGRATION_INFO));
        return String.format(integrationEntity.getPathValue(),
                latitude, longitude, Base64EncoderDecoder.decode(integrationEntity.getTokenValue()));
    }

    private String preparedUrlForWeather(String latitude, String longitude) {
        IntegrationEntity integrationEntity = integrationRepository.findById(WEATHER)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND_INTEGRATION_INFO));
        return String.format(integrationEntity.getPathValue(),
                Base64EncoderDecoder.decode(integrationEntity.getTokenValue()), latitude, longitude);
    }
}
