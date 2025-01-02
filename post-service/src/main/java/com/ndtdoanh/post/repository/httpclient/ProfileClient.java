package com.ndtdoanh.post.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ndtdoanh.post.dto.ApiResponse;
import com.ndtdoanh.post.dto.response.UserProfileResponse;

@FeignClient(name = "profile-service", url = "${app.services.profile.url}")
public interface ProfileClient {
    @GetMapping("/internal/users/{userId}")
    ApiResponse<UserProfileResponse> getProfile(@PathVariable String userId);
}
