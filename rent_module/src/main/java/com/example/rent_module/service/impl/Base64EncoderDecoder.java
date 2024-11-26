package com.example.rent_module.service.impl;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class Base64EncoderDecoder {

    public static byte[] encode(MultipartFile multipartFile) throws IOException {

        return Base64.getEncoder().encode(multipartFile.getBytes());
    }

    public static byte[] decode(byte[] value) {

        return Base64.getDecoder().decode(value);
    }

    public static String decode(String value) {

        return new String(Base64.getDecoder().decode(value));
    }
}
