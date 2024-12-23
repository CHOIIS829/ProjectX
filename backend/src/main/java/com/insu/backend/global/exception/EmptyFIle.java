package com.insu.backend.global.exception;

public class EmptyFIle extends BaseException{

    private static final String MESSAGE = "파일이 비어있습니다.";

    public EmptyFIle() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
