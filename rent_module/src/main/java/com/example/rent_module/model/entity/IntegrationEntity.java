package com.example.rent_module.model.entity;

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
public class IntegrationEntity {

    @Id
    @Column(name = "service_name_id")
    private String serviceNameId;

    @Column(name = "path_value")
    private String pathValue;

    @Column(name = "token_value")
    private String tokenValue;
}
