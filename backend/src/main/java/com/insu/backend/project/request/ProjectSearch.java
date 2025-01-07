package com.insu.backend.project.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectSearch {

    private Integer page = 1;
    private Integer size = 10;

    private String category = "all";
    private String isClosed = "all";
    private String keyword;
    private String author;

}
