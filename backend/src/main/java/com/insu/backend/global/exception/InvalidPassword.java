package com.insu.backend.global.exception;

public class InvalidPassword extends BaseException{

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다.";

    public InvalidPassword() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
