package com.ndtdoanh.profile.mapper;

import org.mapstruct.Mapper;

import com.ndtdoanh.profile.dto.request.ProfileRequest;
import com.ndtdoanh.profile.dto.response.UserProfileResponse;
import com.ndtdoanh.profile.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
