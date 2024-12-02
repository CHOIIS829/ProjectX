package com.insu.backend.global.exception;

public class InvalidToken extends BaseException{

    private static final String MESSAGE = "유효하지 않은 토큰입니다.";

    public InvalidToken() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401; // Unauthorized
    }

}
