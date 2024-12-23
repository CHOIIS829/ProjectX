package com.insu.backend.member.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.member.request.InsertProfile;
import com.insu.backend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/profile")
    public ResponseEntity<SuccessResponse<String>> profile(@ModelAttribute InsertProfile request) {
        String url = memberService.insertProfile(request);

        return ResponseEntity.ok(SuccessResponse.<String>builder()
                .code("200")
                .message("프로필 등록 성공")
                .data(url)
                .build());
    }
}
