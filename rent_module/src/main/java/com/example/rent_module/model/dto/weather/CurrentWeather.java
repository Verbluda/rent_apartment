package com.example.rent_module.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class CurrentWeather {

    @JsonProperty(value = "condition")
    private ConditionObject conditionObject;

    @JsonProperty(value = "temp_c")
    private double temperature;

    @JsonProperty("cloud")
    private int cloudiness;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("wind_dir")
    private String windDirection;

    @JsonProperty("wind_kph")
    private double windSpeed;

    @JsonProperty("pressure_mb")
    private double pressure;

    @JsonProperty("precip_mm")
    private double precStrength;
}
