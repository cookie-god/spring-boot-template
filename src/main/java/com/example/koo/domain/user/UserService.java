package com.example.koo.domain.user;

import com.example.koo.config.BaseResponse;
import com.example.koo.config.DataResponse;
import com.example.koo.config.RegularExpression;
import com.example.koo.config.ResponseCode;
import com.example.koo.config.exception.ResponseException;
import com.example.koo.domain.model.UserInfo;
import com.example.koo.domain.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public DataResponse<GetUsersResponse> retrieveUsers() {
        List<UserInfo> userInfos = userRepository.findAll();
        ArrayList<UserResult> users = new ArrayList<>();

        for (UserInfo userInfo: userInfos) {
            UserResult userResult = new UserResult(
                    userInfo.getId(),
                    userInfo.getEmail(),
                    userInfo.getNickname(),
                    userInfo.getStatus()
            );
            users.add(userResult);
        }

        GetUsersResponse getUsersResponse = new GetUsersResponse(users);
        return new DataResponse<>(getUsersResponse);
    }

    public DataResponse<GetUserResponse> retrieveUser(int userId) {
        if (userId <= 0) {
            throw new ResponseException("유저 아이디 검증 오류", ResponseCode.INVALID_USER_ID);
        }

        UserInfo userInfo = userRepository.findById(userId).orElseThrow(() -> new ResponseException("존재하지 않는 유저", ResponseCode.NOT_FOUND_USER));
        GetUserResponse getUserResponse = new GetUserResponse(
            userInfo.getId(),
            userInfo.getEmail(),
            userInfo.getNickname(),
            userInfo.getStatus()
        );

        return new DataResponse<>(getUserResponse);
    }

    @Transactional
    public BaseResponse createUsers(PostUserRequest postUserRequest) {
        if (postUserRequest.getEmail().length() == 0) {
            throw new ResponseException("비어있는 이메일", ResponseCode.EMPTY_USER_EMAIL);
        }

        if (postUserRequest.getNickname().length() == 0) {
            throw new ResponseException("비어있는 닉네임", ResponseCode.EMPTY_USER_NICKNAME);
        }

        RegularExpression regularExpression = new RegularExpression();
        if (! regularExpression.isValidEmail(postUserRequest.getEmail())) {
            throw new ResponseException("정규식에 어긋난 이메일", ResponseCode.INVALID_USER_EMAIL);
        }

        if (! regularExpression.isValidNickname(postUserRequest.getNickname())) {
            throw new ResponseException("정규식에 어긋난 닉네임", ResponseCode.INVALID_USER_NICKNAME);
        }

        long emailCount = userRepository.countByEmailAndStatus(postUserRequest.getEmail(), "ACTIVE");
        if (emailCount > 0) {
            throw new ResponseException("중복된 이메일", ResponseCode.EXIST_USER_EMAIL);
        }

        long nicknameCount = userRepository.countByNicknameAndStatus(postUserRequest.getNickname(), "ACTIVE");
        if (nicknameCount > 0) {
            throw new ResponseException("중복된 닉네임", ResponseCode.EXIST_USER_NICKNAME);
        }

        userRepository.save(postUserRequest.toEntity());

        return new BaseResponse();
    }

    @Transactional
    public BaseResponse editUsers(int userId, PatchUserRequest patchUserRequest) {
        if (userId <= 0) {
            throw new ResponseException("유저 아이디 검증 오류", ResponseCode.INVALID_USER_ID);
        }

        UserInfo userInfo = userRepository.findByIdAndStatus(userId, "ACTIVE").orElseThrow(() -> new ResponseException("존재하지 않는 유저", ResponseCode.NOT_FOUND_USER));

        if (patchUserRequest.getEmail().length() == 0) {
            throw new ResponseException("비어있는 이메일", ResponseCode.EMPTY_USER_EMAIL);
        }

        RegularExpression regularExpression = new RegularExpression();
        if (! regularExpression.isValidEmail(patchUserRequest.getEmail())) {
            throw new ResponseException("정규식에 어긋난 이메일", ResponseCode.INVALID_USER_EMAIL);
        }

        if (! regularExpression.isValidNickname(patchUserRequest.getNickname())) {
            throw new ResponseException("정규식에 어긋난 닉네임", ResponseCode.INVALID_USER_NICKNAME);
        }

        if (patchUserRequest.getNickname().length() == 0) {
            throw new ResponseException("비어있는 닉네임", ResponseCode.EMPTY_USER_NICKNAME);
        }

        if (! userInfo.getEmail().equals(patchUserRequest.getEmail())) {
            long emailCount = userRepository.countByEmailAndStatus(patchUserRequest.getEmail(), "ACTIVE");
            if (emailCount > 0) {
                throw new ResponseException("중복된 이메일", ResponseCode.EXIST_USER_EMAIL);
            }
        }

        if (! userInfo.getNickname().equals(patchUserRequest.getNickname())) {
            long nicknameCount = userRepository.countByNicknameAndStatus(patchUserRequest.getNickname(), "ACTIVE");
            if (nicknameCount > 0) {
                throw new ResponseException("중복된 닉네임", ResponseCode.EXIST_USER_NICKNAME);
            }
        }

        userInfo.setEmail(patchUserRequest.getEmail());
        userInfo.setNickname(patchUserRequest.getNickname());
        userRepository.save(userInfo);

        return new BaseResponse();
    }

    @Transactional
    public BaseResponse editUsersStatus(int userId) {
        if (userId <= 0) {
            throw new ResponseException("유저 아이디 검증 오류", ResponseCode.INVALID_USER_ID);
        }

        UserInfo userInfo = userRepository.findByIdAndStatus(userId, "ACTIVE").orElseThrow(() -> new ResponseException("존재하지 않는 유저", ResponseCode.NOT_FOUND_USER));
        userInfo.setStatus("INACTIVE");
        userRepository.save(userInfo);

        return new BaseResponse();
    }
}
