package com.insu.backend.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class JoinRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPwd;

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

}
