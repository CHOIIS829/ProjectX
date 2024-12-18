package com.insu.backend.global.exception;

public class NotFoundMemberId extends BaseException{

    private static final String MESSAGE = "존재하지 않는 아이디입니다.";

    public NotFoundMemberId() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 400; // Bad Request
    }

}
