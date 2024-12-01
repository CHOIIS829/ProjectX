package com.insu.backend.global.exception;

public class LoginFailException extends BaseException{

    private static final String MESSAGE = "로그인에 실패하였습니다.";

    public LoginFailException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 401; // Unauthorized
    }

}
