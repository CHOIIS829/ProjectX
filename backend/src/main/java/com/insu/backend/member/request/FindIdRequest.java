package com.insu.backend.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindIdRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
