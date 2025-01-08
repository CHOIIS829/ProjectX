package com.insu.backend.post.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostOneResponse {

    private String postTitle;
    private String postContent;
    private String category;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
