package com.insu.backend.global.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> content; // 데이터
    private int page; // 현재 페이지
    private int size; // 페이지에 보여줄 데이터 수
    private Long totalCount; // 전체 데이터 수
    private int totalPages; // 전체 페이지 수
    private boolean last; // 마지막 페이지 여부 (마지막 페이지면 true)
}
