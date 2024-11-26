package com.example.email_sender.controller;

import com.example.email_sender.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @GetMapping("/send")
    public void sendEmail() {
        String subject = "Тестовое письмо";
        String sendTo = "king.russel@yandex.ru";
        String text = "Тестовое письмо";
        emailSenderService.sendEmail(subject, text, sendTo);
    }
}
