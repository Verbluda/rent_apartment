package com.example.rent_module.model.dto.geo_coder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResultsElem {

    @JsonProperty(value = "components")
    private ComponentsObject componentsObject;
}
