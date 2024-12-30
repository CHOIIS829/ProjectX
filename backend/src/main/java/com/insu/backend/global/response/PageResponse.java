package com.insu.backend.global.response;

import com.insu.backend.project.response.ProjectList;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private Long totalCount;
    private int totalPages;
    private boolean last;
}
