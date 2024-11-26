package com.example.rent_module.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentApartmentExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> catchException(RuntimeException e) {

        return preparedResponseMessage(null, e.getMessage());
    }

    @ExceptionHandler(ApartmentException.class)
    public ResponseEntity<?> catchException(ApartmentException e) {

        return preparedResponseMessage(e.getExceptionCode(), e.getMessage());
    }

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<?> catchException(AddressException e) {

        return preparedResponseMessage(e.getExceptionCode(), e.getMessage());
    }

    private ResponseEntity<?> preparedResponseMessage(Integer code, String message) {

        return ResponseEntity.status(code).body(message);
    }
}
