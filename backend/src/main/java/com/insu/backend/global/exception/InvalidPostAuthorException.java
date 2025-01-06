package com.insu.backend.global.exception;

public class InvalidPostAuthorException extends BaseException{

    private static final String MESSAGE = "작성자가 일치하지 않습니다.";

    public InvalidPostAuthorException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }

}
