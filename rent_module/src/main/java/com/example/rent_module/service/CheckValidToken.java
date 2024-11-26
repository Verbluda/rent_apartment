package com.example.rent_module.service;

import com.example.rent_module.model.entity.UserEntity;

public interface CheckValidToken {

    UserEntity checkToken(String token);
}
