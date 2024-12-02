package com.insu.backend.member.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.member.request.JoinRequest;
import com.insu.backend.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<SuccessResponse> join(@RequestBody JoinRequest request) {
        memberService.join(request);

        return ResponseEntity.ok(SuccessResponse.builder()
                .code("200")
                .message("회원가입 성공")
                .build());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
