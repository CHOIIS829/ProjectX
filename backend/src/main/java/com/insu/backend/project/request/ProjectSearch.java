package com.insu.backend.project.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectSearch {

    private Integer page;
    private Integer size;

    private String category;
    private String keyword;


}
