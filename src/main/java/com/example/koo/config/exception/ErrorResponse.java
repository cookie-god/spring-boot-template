package com.example.koo.config.exception;

import com.example.koo.config.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private int code;
    private String message;


    public ErrorResponse(ResponseCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
    }
}
