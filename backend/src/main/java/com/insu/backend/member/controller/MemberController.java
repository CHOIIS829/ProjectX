package com.insu.backend.member.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.member.request.UpdateProfileRequest;
import com.insu.backend.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/isProfileComplete")
    public ResponseEntity<SuccessResponse<Void>> isProfileComplete(@AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();
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
    public ResponseEntity<SuccessResponse<String>> profile(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute MultipartFile file) {
        String memberId = userDetails.getUsername();
        String url = memberService.insertProfile(file, memberId);

        return ResponseEntity.ok(SuccessResponse.<String>builder()
                .code("200")
                .message("프로필 등록 성공")
                .data(url)
                .build());
    }

    @PatchMapping("/profile")
    public ResponseEntity<SuccessResponse<Void>> updateProfile(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid UpdateProfileRequest request) {
        String memberId = userDetails.getUsername();

        memberService.updateProfile(request, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로필 수정 성공")
                .build());
    }

    @PostMapping("/deleteMember")
    public ResponseEntity<SuccessResponse<Void>> deleteMember(@AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();

        memberService.deleteMember(memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("회원 탈퇴 성공")
                .build());
    }

}
