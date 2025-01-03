package com.insu.backend.member.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.member.request.InsertProfile;
import com.insu.backend.member.request.UpdateProfileRequest;
import com.insu.backend.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/isProfileComplete")
    public ResponseEntity<SuccessResponse<Void>> isProfileComplete(String memberId) {
        String isProfileComplete = memberService.isProfileComplete(memberId);

        if (isProfileComplete.equals("N")) {
            return ResponseEntity.ok(SuccessResponse.<Void>builder()
                    .code("400")
                    .message("프로필 미완성")
                    .build());
        }else {
            return ResponseEntity.ok(SuccessResponse.<Void>builder()
                    .code("200")
                    .message("프로필 완성 여부 확인 성공")
                    .build());
        }
    }

    @PostMapping("/profileImg")
    public ResponseEntity<SuccessResponse<String>> profile(@ModelAttribute InsertProfile request) {
        String url = memberService.insertProfile(request);

        return ResponseEntity.ok(SuccessResponse.<String>builder()
                .code("200")
                .message("프로필 등록 성공")
                .data(url)
                .build());
    }

    @PatchMapping("/profile")
    public ResponseEntity<SuccessResponse<Void>> updateProfile(@RequestBody @Valid UpdateProfileRequest request) {
        memberService.updateProfile(request);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로필 수정 성공")
                .build());
    }

    @PostMapping("/deleteMember")
    public ResponseEntity<SuccessResponse<Void>> deleteMember(String memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("회원 탈퇴 성공")
                .build());
    }

}
