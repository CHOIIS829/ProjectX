package com.insu.backend.member.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InsertProfileRequest {

    private String memberId;

    private MultipartFile file;

}
