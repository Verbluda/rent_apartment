package com.example.architect_module.service;

import com.example.architect_module.model.ArchitectRequestDto;
import org.springframework.ui.Model;

import java.util.Map;

public interface ArchitectService {

    void createMigrationFile(ArchitectRequestDto architectRequestDto, Model model);
    void getMainPage(Model model);

    Map<String, String> getColumnsData(String tableName);
}
