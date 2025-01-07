package com.insu.backend.project.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProjectSearchRequest {

    private Integer page;
    private Integer size;
    private String category;
    private String isClosed;
    private String keyword;
    private String author;

}
