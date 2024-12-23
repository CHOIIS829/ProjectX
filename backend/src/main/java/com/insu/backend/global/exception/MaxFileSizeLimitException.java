package com.insu.backend.global.exception;

public class MaxFileSizeLimitException extends BaseException{

    private static final String MESSAGE = "파일 크기가 너무 큽니다.";

    public MaxFileSizeLimitException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
