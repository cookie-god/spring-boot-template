package com.example.koo.config;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    @ApiModelProperty(value="코드", example = "200", required = true)
    private int status;

    @ApiModelProperty(value="결과 코드", example = "1000", required = true)
    private int code;

    @ApiModelProperty(value="결과 메시지", example = "성공", required = true)
    private String message;

    public BaseResponse() {
        this.status = 200;
        this.code = 1000;
        this.message = "성공";
    }
}
