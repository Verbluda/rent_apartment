package com.example.rent_module.repository;

import com.example.rent_module.model.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, Long> {

}
