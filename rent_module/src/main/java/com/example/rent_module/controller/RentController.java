package com.example.rent_module.controller;

import com.example.rent_module.model.dto.ApartmentRequestDto;
import com.example.rent_module.model.dto.ApartmentResponseDto;
import com.example.rent_module.model.dto.weather.WeatherResponseDto;
import com.example.rent_module.model.entity.UserEntity;
import com.example.rent_module.service.CheckValidToken;
import com.example.rent_module.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

import static com.example.rent_module.controller.ControllerConstant.*;
import static java.util.Objects.isNull;

@RestController
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;
    private final CheckValidToken checkValidToken;

    @PostMapping(REGISTRATION_OF_APARTMENT)
    public String addApartment(@RequestBody ApartmentRequestDto apartRegistration,
                               @RequestHeader String token) {
        checkValidToken.checkToken(token);
        return rentService.addApartment(apartRegistration);
    }

    @PostMapping(ADD_PHOTO_TO_APARTMENT)
    public String addPhotoToApartment(@RequestParam Long id,
                                      @RequestParam MultipartFile multipartFile) {
        return rentService.addPhotoToApartment(id, multipartFile);
    }

    @GetMapping(FIND_APARTMENT_BY_LOCATION)
    public List<ApartmentResponseDto> findApartmentByLocation(@RequestParam String latitude,
                                                              @RequestParam String longitude) {
        return rentService.findApartmentByLocation(latitude, longitude);
    }

    @GetMapping(FIND_WEATHER_BY_LOCATION)
    public WeatherResponseDto findWeatherByLocation(@RequestParam String latitude,
                                                    @RequestParam String longitude) {
        return rentService.findWeatherByLocation(latitude, longitude);
    }

    @PostMapping(BOOKING_APARTMENT)
    public ApartmentResponseDto bookApartment(@RequestHeader(required = false) String token, @RequestParam Long id,
                                              @RequestParam(required = false) LocalDate startDate, @RequestParam(required = false) LocalDate endDate) {
        if (isNull(startDate) && isNull(endDate)) {
            return rentService.findApartmentById(id);
        }
        UserEntity user = checkValidToken.checkToken(token);
        return rentService.bookApartment(user, id, startDate, endDate);
    }
}
