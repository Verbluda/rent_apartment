package com.example.rent_module.exception;

import lombok.Getter;

@Getter
public class AddressException extends RuntimeException {

    private int exceptionCode;

    public AddressException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
