package com.example.auth_module.repository;

import com.example.auth_module.model.entity.UserEntity;

public interface UserCriteriaRepository {

    UserEntity findUserByEmail(String email);
}
