package com.insu.backend.global.exception;

public class FailMailSendException extends BaseException{

    private static final String MESSAGE = "메일 전송에 실패했습니다.";

    public FailMailSendException() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }

}
