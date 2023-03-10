package com.example.koo.config;

import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class RegularExpression {
    public boolean isValidEmail(String email) {
        return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", email);
    }

    public boolean isValidNickname(String nickname) {
        return Pattern.matches("^[가-힣]{1,10}$", nickname);
    }
}
