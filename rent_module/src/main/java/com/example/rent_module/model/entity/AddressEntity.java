package com.example.rent_module.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "address")
public class AddressEntity {

    @Id
    @SequenceGenerator(name = "addressSequence", sequenceName = "address_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "addressSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_of_house")
    private String numberOfHouse;

    @Column(name = "number_of_apartment")
    private String numberOfApartment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "apartment_id")
    private ApartmentEntity apartment;
}
