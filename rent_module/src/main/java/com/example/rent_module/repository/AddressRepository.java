package com.example.rent_module.repository;

import com.example.rent_module.model.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query(value = "select a from AddressEntity a where a.city = :city " +
            "and a.street = :street " +
            "and a.numberOfHouse = :numberOfHouse " +
            "and a.numberOfApartment = :numberOfApartment")
    Optional<AddressEntity> findByAddress(String city, String street, String numberOfHouse, String numberOfApartment);

    @Query(value = "select a from AddressEntity a where a.city = :city")
    List<AddressEntity> findByCity(String city);
}
