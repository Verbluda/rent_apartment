package com.example.rent_module.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "apartment")
public class ApartmentEntity {

    @Id
    @SequenceGenerator(name = "apartmentSequence", sequenceName = "apartment_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apartmentSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_room")
    private int numberOfRoom;

    @Column(name = "is_available", columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isAvailable;

    @Column(name = "price_per_day")
    private double pricePerDay;

    @OneToOne(mappedBy = "apartment")
    private AddressEntity address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_id")
    private PhotoEntity photoEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "apartmentEntity")
    private List<BookingInfoEntity> bookingInfoEntities;

    public ApartmentEntity() {
        isAvailable = true;
    }
}
