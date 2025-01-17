package com.insu.backend.project.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "스킬을 입력해주세요.")
    private List<String> skills;

//    @NotBlank(message = "회원아이디는 필수입니다.")
//    private String memberId;

}
