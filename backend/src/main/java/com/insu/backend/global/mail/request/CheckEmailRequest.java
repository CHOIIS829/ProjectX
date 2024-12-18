package com.insu.backend.global.mail.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckEmailRequest {
    private String email;

    @NotBlank(message = "인증번호를 입력해주세요.")
    private String code;
}
