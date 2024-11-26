package com.example.product_module.controller;

import com.example.product_module.model.TestObjectDto;
import com.example.product_module.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final TokenRepository tokenRepository;

    @GetMapping("/test1")
    public String test1(@RequestHeader String token) {
        tokenRepository.findToken(token).orElseThrow(() -> new RuntimeException("Неверный токен"));
        return "Этот метод из product module";
    }

    @GetMapping("/test2")
    public String test2(@RequestParam String text,
                        @RequestHeader String token) {
        tokenRepository.findToken(token).orElseThrow(() -> new RuntimeException("Неверный токен"));
        return "Этот метод из product module c параметром " + text;
    }

    @PostMapping("/test3")
    public String test3(@RequestBody TestObjectDto testObjectDto,
                        @RequestHeader String token) {
        tokenRepository.findToken(token).orElseThrow(() -> new RuntimeException("Неверный токен"));
        return "Этот метод из product module " + testObjectDto.toString();
    }
}

