package com.example.auth_module.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class UserEntity {

    @Id
    @SequenceGenerator(name="userInfoSequence", sequenceName="user_info_sequence", allocationSize = 1, initialValue = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="userInfoSequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "date_registration")
    private LocalDateTime dateRegistration;

    @Column(name = "token")
    private String token;
}
