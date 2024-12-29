package com.ndtdoanh.identity.mapper;

import org.mapstruct.Mapper;

import com.ndtdoanh.identity.dto.request.ProfileRequest;
import com.ndtdoanh.identity.dto.request.UserRequest;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileRequest toProfileRequest(UserRequest request);
}
