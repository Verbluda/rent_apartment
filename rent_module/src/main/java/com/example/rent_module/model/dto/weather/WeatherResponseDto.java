package com.example.rent_module.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class WeatherResponseDto {

    @JsonProperty(value = "current")
    private CurrentWeather currentWeather;
}
