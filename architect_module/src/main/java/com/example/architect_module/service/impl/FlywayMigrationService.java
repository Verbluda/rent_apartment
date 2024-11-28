package com.example.architect_module.service.impl;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FlywayMigrationService {

    @Value("${spring.flyway.url}")
    private String flywayUrl;

    @Value("${spring.flyway.user}")
    private String flywayUser;

    @Value("${spring.flyway.password}")
    private String flywayPassword;

    @Value("${spring.flyway.locations}")
    private String flywayLocations;

    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(flywayUrl, flywayUser, flywayPassword)
                .locations(flywayLocations)
                .load();
        flyway.migrate();
    }
}
