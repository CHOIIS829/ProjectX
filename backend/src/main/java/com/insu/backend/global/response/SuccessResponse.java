package com.insu.backend.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record SuccessResponse<T>(String code, String message, T data) {

    @Builder
    public SuccessResponse {
    }
}
