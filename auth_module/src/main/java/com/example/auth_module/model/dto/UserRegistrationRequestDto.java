package com.example.auth_module.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDto {

    private String userName;

    private String email;

    private String password;
}
