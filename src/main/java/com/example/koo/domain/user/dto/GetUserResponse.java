package com.example.koo.domain.user.dto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserResponse {
    @ApiModelProperty(value="유저 아이디", example = "1", required = true)
    private int userId;

    @ApiModelProperty(value="이메일", example = "kisung_koo@tmax.co.kr", required = true)
    private String email;

    @ApiModelProperty(value="닉네임", example = "구기성", required = true)
    private String nickname;

    @ApiModelProperty(value="상태값", example = "ACTIVE", required = true)
    private String status;
}
