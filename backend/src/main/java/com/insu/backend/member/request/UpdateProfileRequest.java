package com.insu.backend.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProfileRequest {

    @NotBlank(message = "memberId는 필수값입니다.")
    private String memberId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String memberPwd;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "깃허브 프로필 주소를 입력해주세요.")
    private String gitProfileUrl;

    private List<String> skills;
}
