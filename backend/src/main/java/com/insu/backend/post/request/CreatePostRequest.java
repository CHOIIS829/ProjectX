package com.insu.backend.post.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String postTitle;

    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String postContent;

    @NotBlank(message = "카테고리를 입력해주세요.")
    private String category;
}
