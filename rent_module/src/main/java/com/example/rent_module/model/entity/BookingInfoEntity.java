package com.example.rent_module.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "booking_info")
public class BookingInfoEntity {

    @Id
    @SequenceGenerator(name = "bookingInfoSequence", sequenceName = "booking_info_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingInfoSequence")
    @Column(name = "id")
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String amount;

    private String discount;

    @ManyToOne()
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartmentEntity;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
}
