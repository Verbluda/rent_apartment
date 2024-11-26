package com.example.architect_module.controller;

import com.example.architect_module.model.ArchitectRequestDto;
import com.example.architect_module.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ArchitectController {

    private final ArchitectService architectService;

    @GetMapping("/")
    public String getMainPage(Model model) {
        architectService.getMainPage(model);
        return "index";
    }

    @PostMapping("/automation")
    public String createDBMigration(@RequestBody ArchitectRequestDto architectRequestDto) {
        architectService.createMigrationFile(architectRequestDto);
        return "redirect: index";
    }
}
