package com.example.auth_module.controller;


import com.example.auth_module.model.dto.UserAuthorizationRequestDto;
import com.example.auth_module.model.dto.UserRegistrationRequestDto;
import com.example.auth_module.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.auth_module.controller.ControllerConstant.AUTHORIZATION;
import static com.example.auth_module.controller.ControllerConstant.REGISTRATION;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth_module", description = "Модуль регистрации и авторизации пользователя")
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTRATION)
    @Operation(summary = "метод добавления нового пользователя")
    public String addUser(@RequestBody UserRegistrationRequestDto user) {
        return authService.addUser(user);
    }

    @PostMapping(AUTHORIZATION)
    @Operation(summary = "метод авторизации")
    public String logInUser(@RequestBody UserAuthorizationRequestDto user) {
        return authService.logInUser(user);
    }
}
