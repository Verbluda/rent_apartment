package com.example.auth_module.repository.impl;

import com.example.auth_module.model.entity.UserEntity;
import com.example.auth_module.repository.UserCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserCriteriaRepositoryImpl implements UserCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public UserEntity findUserByEmail(String email) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = criteriaBuilder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);


        query.select(root)
                .where(criteriaBuilder.equal(root.get("email"), email));


        UserEntity userByEmail = entityManager.createQuery(query).getSingleResult();
        return userByEmail;
    }
}
