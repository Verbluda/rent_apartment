package com.example.auth_module.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthorizationRequestDto {

    private String email;

    private String password;
}
