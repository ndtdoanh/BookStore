package com.ndtdoanh.post.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.ndtdoanh.post.entity.Post;

public interface PostRepository extends MongoRepository<Post, String> {
    Page<Post> findAllByUserId(String userId, Pageable pageable);
}
