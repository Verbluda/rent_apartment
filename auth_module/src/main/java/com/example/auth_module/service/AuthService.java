package com.example.auth_module.service;

import com.example.auth_module.model.dto.UserAuthorizationRequestDto;
import com.example.auth_module.model.dto.UserRegistrationRequestDto;

public interface AuthService {

    String addUser(UserRegistrationRequestDto user);
    String logInUser(UserAuthorizationRequestDto user);
}
