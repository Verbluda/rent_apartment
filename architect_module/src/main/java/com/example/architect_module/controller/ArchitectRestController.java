package com.example.architect_module.controller;

import com.example.architect_module.service.ArchitectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArchitectRestController {

    private final ArchitectService architectService;

    @GetMapping("/getColumnsData")
    @Operation(summary = "метод получения списка колонок и их типов данных")
    public Map<String, String> getColumnsData(@RequestParam String tableName) {
        return architectService.getColumnsData(tableName);
    }
}
