package com.insu.backend.global.exception;

public class FailUploadFile extends BaseException{

    private static final String MESSAGE = "파일 업로드에 실패했습니다.";

    public FailUploadFile() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 500;
    }

}
