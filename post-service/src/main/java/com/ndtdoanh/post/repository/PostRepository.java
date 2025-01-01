package com.ndtdoanh.post.repository;

import com.ndtdoanh.post.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByUserId(String userId);
}
