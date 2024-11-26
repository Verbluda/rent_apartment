package com.example.architect_module.service;

import com.example.architect_module.model.ArchitectRequestDto;
import org.springframework.ui.Model;

public interface ArchitectService {

    void createMigrationFile(ArchitectRequestDto architectRequestDto);
    void getMainPage(Model model);
}
