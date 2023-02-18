package org.shabbydev.test.middlejavadevelopertest.logic.utils;


import org.shabbydev.test.middlejavadevelopertest.logic.controller.AuthController;
import org.springframework.http.HttpHeaders;

public class AuthHeadersGenerator extends HttpHeaders {
    public AuthHeadersGenerator(String token) {
        add("Authorization", token);
    }
}
