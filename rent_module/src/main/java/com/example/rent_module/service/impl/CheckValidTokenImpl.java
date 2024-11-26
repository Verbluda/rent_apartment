package com.example.rent_module.service.impl;

import com.example.rent_module.model.entity.UserEntity;
import com.example.rent_module.repository.UserRepository;
import com.example.rent_module.service.CheckValidToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckValidTokenImpl implements CheckValidToken {

    private final UserRepository userRepository;

    @Override
    public UserEntity checkToken(String token) {
        return userRepository.findUserByToken(token).orElseThrow(() -> new RuntimeException("Войдите в систему"));
    }
}
