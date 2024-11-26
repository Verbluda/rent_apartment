package com.example.rent_module.model.dto.geo_coder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class GeoCoderResponseDto {

    @JsonProperty(value = "results")
    private List<ResultsElem> resultsElemList;
}
