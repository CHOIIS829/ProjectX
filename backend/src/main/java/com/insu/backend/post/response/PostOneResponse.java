package com.insu.backend.post.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insu.backend.post.entity.Post;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateAt;

    public static PostOneResponse toResponse(Post post) {
        return PostOneResponse.builder()
                .postTitle(post.getPostTitle())
                .postContent(post.getPostContent())
                .category(post.getCategory())
                .author(post.getMember().getMemberId())
                .createAt(post.getCreateAt())
                .updateAt(post.getUpdateAt())
                .build();
    }
}
