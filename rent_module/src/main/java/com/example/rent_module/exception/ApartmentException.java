package com.example.rent_module.exception;

import lombok.Getter;

@Getter
public class ApartmentException extends RuntimeException {

    private int exceptionCode;

    public ApartmentException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public ApartmentException(String message) {
        super(message);
    }
}
