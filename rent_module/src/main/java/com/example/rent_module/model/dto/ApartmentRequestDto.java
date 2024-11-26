package com.example.rent_module.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApartmentRequestDto {

    private int numberOfRoom;
    private double pricePerDay;
    private String cityValue;
    private String street;
    private String numberOfHouse;
    private String numberOfApartment;
}
