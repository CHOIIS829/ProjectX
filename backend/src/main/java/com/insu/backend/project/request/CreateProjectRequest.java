package com.insu.backend.project.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProjectRequest {

    @NotBlank(message = "프로젝트 제목을 입력해주세요.")
    private String projectTitle;

    @NotBlank(message = "프로젝트 내용을 입력해주세요.")
    private String projectContent;

    @NotBlank(message = "카테고리를 입력해주세요.")
    private String category;

    private List<String> skills;

    private String memberId;

}
