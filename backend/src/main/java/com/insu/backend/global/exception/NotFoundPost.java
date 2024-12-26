package com.insu.backend.global.exception;

public class NotFoundPost extends BaseException{

    private static final String MESSAGE = "해당 게시글을 찾을 수 없습니다.";

    public NotFoundPost() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
