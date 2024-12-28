package com.ndtdoanh.identity.mapper;

import com.ndtdoanh.identity.dto.request.ProfileRequest;
import com.ndtdoanh.identity.dto.request.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileRequest toProfileRequest(UserRequest request);
}
