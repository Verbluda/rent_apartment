package com.example.rent_module.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingInfoRequestDto {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int apartmentId;
}
