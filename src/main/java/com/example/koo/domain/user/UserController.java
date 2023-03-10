package com.example.koo.domain.user;

import com.example.koo.config.*;
import com.example.koo.domain.user.dto.GetUserResponse;
import com.example.koo.domain.user.dto.GetUsersResponse;
import com.example.koo.domain.user.dto.PatchUserRequest;
import com.example.koo.domain.user.dto.PostUserRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Get Users", description = "Get All Users Information")
    @ApiResponse(code = 1000, message = "성공")
    @GetMapping("")
    public DataResponse<GetUsersResponse> getUsers() {
        return userService.retrieveUsers();
    }


    @Operation(summary = "Get User", description = "Get User By User Id")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "성공"),
            @ApiResponse(code = 2000, message = "유저 아이디는 0보다 커야합니다."),
            @ApiResponse(code = 2005, message = "존재하지 않는 유저입니다."),
    })
    @GetMapping("{userId}")
    public DataResponse<GetUserResponse> getUser(@PathVariable("userId") int userId) {
        return userService.retrieveUser(userId);
    }

    @Operation(summary = "Create User", description = "Create User")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "성공"),
            @ApiResponse(code = 2001, message = "이메일을 입력해주세요."),
            @ApiResponse(code = 2002, message = "닉네임을 입력해주세요"),
            @ApiResponse(code = 2003, message = "이미 존재하는 이메일입니다."),
            @ApiResponse(code = 2004, message = "이미 존재하는 닉네임입니다."),
            @ApiResponse(code = 2006, message = "정규식에 어긋난 이메일입니다."),
            @ApiResponse(code = 2007, message = "정규식에 어긋난 닉네임입니다.."),
    })
    @PostMapping("")
    public BaseResponse postUsers(@RequestBody PostUserRequest postUserRequest) {
        return userService.createUsers(postUserRequest);
    }

    @Operation(summary = "Update User", description = "Update User By User Id")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "성공"),
            @ApiResponse(code = 2000, message = "유저 아이디는 0보다 커야합니다."),
            @ApiResponse(code = 2005, message = "존재하지 않는 유저입니다."),
            @ApiResponse(code = 2001, message = "이메일을 입력해주세요."),
            @ApiResponse(code = 2002, message = "닉네임을 입력해주세요"),
            @ApiResponse(code = 2003, message = "이미 존재하는 이메일입니다."),
            @ApiResponse(code = 2004, message = "이미 존재하는 닉네임입니다."),
            @ApiResponse(code = 2006, message = "정규식에 어긋난 이메일입니다."),
            @ApiResponse(code = 2007, message = "정규식에 어긋난 닉네임입니다.."),
    })
    @PatchMapping("{userId}")
    public BaseResponse patchUsers(@PathVariable("userId") int userId, @RequestBody PatchUserRequest patchUserRequest) {
        return userService.editUsers(userId, patchUserRequest);
    }

    @Operation(summary = "Delete User", description = "Delete User By User Id")
    @ApiResponses({
            @ApiResponse(code = 1000, message = "성공"),
            @ApiResponse(code = 2000, message = "유저 아이디는 0보다 커야합니다."),
            @ApiResponse(code = 2005, message = "존재하지 않는 유저입니다."),
    })
    @PatchMapping("{userId}/status")
    public BaseResponse patchUsersStatus(@PathVariable("userId") int userId) {
        return userService.editUsersStatus(userId);
    }
}
