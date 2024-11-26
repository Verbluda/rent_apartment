package com.example.email_sender.service;

public interface EmailSenderService {

    public void sendEmail(String subject, String text, String sendTo);

}
