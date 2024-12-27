package com.insu.backend.member.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProfileRequest {

    private String memberId;
    private String memberPwd;
    private String email;
    private String git;
    private List<String> skills;
}
