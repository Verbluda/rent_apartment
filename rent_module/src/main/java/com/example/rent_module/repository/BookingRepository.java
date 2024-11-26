package com.example.rent_module.repository;

import com.example.rent_module.model.entity.BookingInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingInfoEntity, Long> {

    //todo Query("select * ")

}
