package com.example.architect_module.controller;

import com.example.architect_module.model.ArchitectRequestDto;
import com.example.architect_module.service.ArchitectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Tag(name = "Architect_module", description = "Модуль с реализацией и автоматизацией миграции БД")
public class ArchitectController {

    private final ArchitectService architectService;

    @GetMapping("/")
    @Operation(summary = "метод загрузки страницы админки с формой для создания новый скриптов")
    public String getMainPage(Model model) {
        architectService.getMainPage(model);
        return "index";
    }

    @PostMapping("/automation")
    @Operation(summary = "метод генерации нового скрипта для миграции БД")
    public String createDBMigration(@RequestBody ArchitectRequestDto architectRequestDto, Model model) {
        architectService.createMigrationFile(architectRequestDto, model);
        return "result";
    }
}
