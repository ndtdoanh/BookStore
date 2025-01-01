package com.ndtdoanh.post.mapper;

import com.ndtdoanh.post.dto.response.PostResponse;
import com.ndtdoanh.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
