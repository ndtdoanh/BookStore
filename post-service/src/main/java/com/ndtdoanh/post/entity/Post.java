package com.ndtdoanh.post.entity;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@Document(value = "post")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {
    @MongoId
    String id;

    String userId;
    String content;
    Instant createdDate;
    Instant modifiedDate;
}
