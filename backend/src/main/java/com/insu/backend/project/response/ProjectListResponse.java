package com.insu.backend.project.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProjectListResponse {

    private Long projectNo;
    private String projectTitle;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
