package com.example.koo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS(200, 1000, "성공"),
    INVALID_USER_ID(400, 2000, "유저 아이디는 0보다 커야합니다."),
    EMPTY_USER_EMAIL(400, 2001, "이메일을 입력해주세요."),
    EMPTY_USER_NICKNAME(400, 2002, "닉네임을 입력해주세요."),
    EXIST_USER_EMAIL(400, 2003, "이미 존재하는 이메일입니다."),
    EXIST_USER_NICKNAME(400, 2004, "이미 존재하는 닉네임입니다."),
    NOT_FOUND_USER(404, 2005, "존재하지 않는 유저입니다."),
    INVALID_USER_EMAIL(400, 2006, "정규식에 어긋난 이메일입니다."),
    INVALID_USER_NICKNAME(400, 2007, "정규식에 어긋난 닉네임입니다."),
    INTER_SERVER_ERROR(500,4001,"INTER SERVER ERROR"),

    ;

    private int status;
    private int code;
    private String message;
}
