package com.example.email_sender.controller;

import com.example.email_sender.service.EmailSenderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Email_sender", description = "Отправка рассылок")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @GetMapping("/send")
    @Operation(summary = "метод для отправки тестового письма")
    public void sendEmail() {
        String subject = "Тестовое письмо";
        String sendTo = "king.russel@yandex.ru";
        String text = "Тестовое письмо";
        emailSenderService.sendEmail(subject, text, sendTo);
    }
}
