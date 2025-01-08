package com.insu.backend.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageSearchDto {

    private Integer page;
    private Integer size;
    private String category;
    private String isClosed;
    private String keyword;
    private String author;

}
