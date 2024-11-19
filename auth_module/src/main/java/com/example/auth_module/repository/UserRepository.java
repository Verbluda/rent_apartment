package com.example.auth_module.repository;

import com.example.auth_module.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    @Query(nativeQuery = true, value = "select * from user_info where email = :email")
    Optional<UserEntity> findUserByEmail(String email);
}
