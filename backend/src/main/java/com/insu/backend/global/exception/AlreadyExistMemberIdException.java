package com.insu.backend.global.exception;

public class AlreadyExistMemberIdException extends BaseException{

    private static final String MESSAGE = "이미 존재하는 아이디입니다.";

    public AlreadyExistMemberIdException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
