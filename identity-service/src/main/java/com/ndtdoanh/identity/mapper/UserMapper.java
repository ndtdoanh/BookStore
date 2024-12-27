package com.ndtdoanh.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.ndtdoanh.identity.dto.request.UserRequest;
import com.ndtdoanh.identity.dto.request.UserUpdateRequest;
import com.ndtdoanh.identity.dto.response.UserResponse;
import com.ndtdoanh.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
