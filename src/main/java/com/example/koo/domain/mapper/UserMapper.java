package com.example.koo.domain.mapper;

import com.example.koo.domain.model.UserInfo;
import com.example.koo.domain.user.dto.PostUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // PostUserRequest(Dto) -> UserInfo(Entiry)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", ignore = true)
    UserInfo toEntity(PostUserRequest postUserRequest);
}
