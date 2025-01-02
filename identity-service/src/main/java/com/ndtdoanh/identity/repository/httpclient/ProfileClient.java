package com.ndtdoanh.identity.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ndtdoanh.identity.config.AuthenticationRequestInterceptor;
import com.ndtdoanh.identity.dto.request.ApiResponse;
import com.ndtdoanh.identity.dto.request.ProfileRequest;
import com.ndtdoanh.identity.dto.response.UserProfileResponse;

@FeignClient(
        name = "profile-service",
        url = "${app.services.profile}",
        configuration = {AuthenticationRequestInterceptor.class})
public interface ProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileRequest request);
}
