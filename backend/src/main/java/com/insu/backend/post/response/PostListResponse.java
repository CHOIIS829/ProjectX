package com.insu.backend.post.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PostListResponse {

    private Long postNo;
    private String postTitle;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
