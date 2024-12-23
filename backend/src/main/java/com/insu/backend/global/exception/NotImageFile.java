package com.insu.backend.global.exception;

public class NotImageFile extends BaseException{

    private static final String MESSAGE = "이미지 파일이 아닙니다.";

    public NotImageFile() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
