package com.example.product_module.repository;

import com.example.product_module.model.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    @Query(nativeQuery = true, value = "select * from integration_info where token_value = :token")
    Optional<TokenEntity> findToken(String token);
}
