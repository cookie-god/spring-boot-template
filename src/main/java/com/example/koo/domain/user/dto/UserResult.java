package com.example.koo.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class UserResult {
    @ApiModelProperty(value="유저 아이디", example = "1", required = true)
    private int userId;

    @ApiModelProperty(value="이메일", example = "kisung_koo@tmax.co.kr", required = true)
    private String email;

    @ApiModelProperty(value="닉네임", example = "구기성", required = true)
    private String nickname;

    @ApiModelProperty(value="상태값", example = "ACTIVE", required = true)
    private String status;

    public UserResult(int userId, String email, String nickname, String status) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.status = status;
    }
}
