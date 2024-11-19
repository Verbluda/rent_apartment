package com.example.auth_module.service.impl;

import com.example.auth_module.mapper.AuthMapper;
import com.example.auth_module.model.dto.UserAuthorizationRequestDto;
import com.example.auth_module.model.dto.UserRegistrationRequestDto;
import com.example.auth_module.model.entity.UserEntity;
import com.example.auth_module.repository.UserCriteriaRepository;
import com.example.auth_module.repository.UserRepository;
import com.example.auth_module.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    public static final String NOT_UNIQUE_USER_NAME_MESSAGE = "User with this name already exists";
    public static final String NOT_UNIQUE_USER_EMAIL_MESSAGE = "User with this email already exists";
    public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect password";

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final UserCriteriaRepository userCriteriaRepository;

    @Override
    public String addUser(UserRegistrationRequestDto user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new RuntimeException(NOT_UNIQUE_USER_NAME_MESSAGE);
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException(NOT_UNIQUE_USER_EMAIL_MESSAGE);
        }
        UserEntity userEntity = authMapper.userDtoToUserEntity(user);
        return updateUserTokenInDB(userEntity);
    }

    @Override
    public String logInUser(UserAuthorizationRequestDto user) {
        UserEntity userEntity = userCriteriaRepository.findUserByEmail(user.getEmail());
        if (!Base64EncoderDecoder.decode(userEntity.getPassword()).equals(user.getPassword())) {
            throw new RuntimeException(INCORRECT_PASSWORD_MESSAGE);
        }
        return updateUserTokenInDB(userEntity);
    }

    private String generateToken() {
        return UUID.randomUUID() + "|" + LocalDateTime.now().plusDays(1);
    }

    private String updateUserTokenInDB(UserEntity userEntity) {
        String token = generateToken();
        userEntity.setToken(token);
        userRepository.save(userEntity);
        return token;
    }
}
