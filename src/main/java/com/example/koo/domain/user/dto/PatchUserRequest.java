package com.example.koo.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchUserRequest {
    @ApiModelProperty(value="이메일", example = "kisung_koo@tmax.co.kr", required = true)
    private String email;

    @ApiModelProperty(value="닉네임", example = "구기성", required = true)
    private String nickname;
}
