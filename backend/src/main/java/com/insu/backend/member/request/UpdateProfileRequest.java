package com.insu.backend.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProfileRequest {

//    @NotBlank(message = "memberId는 필수값입니다.")
//    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String memberPwd;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "깃허브 프로필 주소를 입력해주세요.")
    private String gitProfileUrl;

    @NotEmpty(message = "스킬을 입력해주세요.")
    private List<String> skills;
}
