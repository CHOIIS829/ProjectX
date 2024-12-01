package com.insu.backend.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
public class JoinRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPwd;

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberName;

    private String email;

}
