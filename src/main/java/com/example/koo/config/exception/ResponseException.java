package com.example.koo.config.exception;

import com.example.koo.config.ResponseCode;
import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {

    private ResponseCode errorCode;

    public ResponseException(String message, ResponseCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}