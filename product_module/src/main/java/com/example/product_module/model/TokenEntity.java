package com.example.product_module.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "integration_info")
public class TokenEntity {

    @Id
    @Column(name = "service_name_id")
    private String serviceName;

    @Column(name = "path_value")
    private String pathValue;

    @Column(name = "token_value")
    private String tokenValue;
}
