package com.example.rent_module.controller;

import com.example.rent_module.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
@Tag(name = "Reports", description = "Выгрузка отчетности")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get_report")
    @Operation(summary = "метод выгрузки отчетности")
    public String getReport() {
        return reportService.getReport();
    }
}
