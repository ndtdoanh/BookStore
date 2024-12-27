package com.ndtdoanh.profile.mapper;

import org.mapstruct.Mapper;

import com.ndtdoanh.profile.dto.request.UserProfileRequest;
import com.ndtdoanh.profile.dto.response.UserProfileResponse;
import com.ndtdoanh.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
