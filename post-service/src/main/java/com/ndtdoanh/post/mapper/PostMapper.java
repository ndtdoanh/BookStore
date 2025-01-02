package com.ndtdoanh.post.mapper;

import org.mapstruct.Mapper;

import com.ndtdoanh.post.dto.response.PostResponse;
import com.ndtdoanh.post.entity.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
