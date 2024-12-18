package com.insu.backend.global.exception;

public class InvalidEmailCodeException extends BaseException{

    private static final String MESSAGE = "이메일 인증번호가 일치하지 않습니다.";

    public InvalidEmailCodeException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
