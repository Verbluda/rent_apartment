package com.example.auth_module.service.impl;

import java.util.Base64;

public class Base64EncoderDecoder {

    public static String encode(String value) {

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(value.getBytes());
    }

    public static String decode(String value) {

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(value);
        return new String(decode);
    }
}
