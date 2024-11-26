package com.example.rent_module.repository;

import com.example.rent_module.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select a from UserEntity a where a.token = :token")
    Optional<UserEntity> findUserByToken(String token);

    List<UserEntity> findUserEntitiesByTokenIsNotNull();
}
