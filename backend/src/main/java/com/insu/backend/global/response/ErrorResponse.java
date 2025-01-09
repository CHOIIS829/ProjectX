package com.insu.backend.global.response;

import lombok.Builder;
import lombok.Getter;

public record ErrorResponse(String code, String message) {

    @Builder
    public ErrorResponse {
    }

}
