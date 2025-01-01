package com.ndtdoanh.post.controller;

import com.ndtdoanh.post.dto.ApiResponse;
import com.ndtdoanh.post.dto.request.PostRequest;
import com.ndtdoanh.post.dto.response.PostResponse;
import com.ndtdoanh.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/create")
    ApiResponse<PostResponse> createPost(@RequestBody PostRequest request) {
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request))
                .build();
    }

    @GetMapping("/my-posts")
    ApiResponse<List<PostResponse>> myPosts() {
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getMyPosts())
                .build();
    }
}