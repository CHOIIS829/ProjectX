package com.insu.backend.global.response;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public class SuccessResponse<T> {

    private final String code;
    private final String message;
    private final T data;

    @Builder
    public SuccessResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
