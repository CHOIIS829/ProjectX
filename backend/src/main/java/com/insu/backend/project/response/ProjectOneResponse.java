package com.insu.backend.project.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectOneResponse {

    private String projectTitle;
    private String projectContent;
    private String category;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<String> skills;

}