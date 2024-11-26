package com.example.rent_module.controller;

public class ControllerConstant {

    public static final String BASE_PATH = "/api/v1/rent_apartment";
    public static final String REGISTRATION_OF_APARTMENT = BASE_PATH + "/registration_of_apartment";
    public static final String ADD_PHOTO_TO_APARTMENT = BASE_PATH + "/{id}/add_photo";
    public static final String FIND_APARTMENT_BY_LOCATION = BASE_PATH + "/find_apartment_by_location";
    public static final String FIND_WEATHER_BY_LOCATION = BASE_PATH + "/find_weather_by_location";

    public static final String BOOKING_APARTMENT = BASE_PATH + "/booking_of_apartment";
}
