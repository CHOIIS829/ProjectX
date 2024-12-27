package com.insu.backend.member.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.member.request.FIndPwRequest;
import com.insu.backend.member.request.FindIdRequest;
import com.insu.backend.member.request.JoinRequest;
import com.insu.backend.member.service.MemberService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<SuccessResponse<Void>> join(@RequestBody @Valid JoinRequest request) {
        memberService.join(request);
        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("회원가입 성공")
                .build());
    }

    @PostMapping("/checkId")
    public ResponseEntity<SuccessResponse<Void>> checkId(String memberId) {
        memberService.checkId(memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("사용 가능한 아이디입니다.")
                .build());
    }

    @GetMapping("/testing")
    public void testing(){
        JoinRequest joinRequest = new JoinRequest();
            joinRequest.setMemberId("test01");
            joinRequest.setEmail("test01@gmail.com");
            joinRequest.setMemberName("test01");
            joinRequest.setMemberPwd("1234");
            joinRequest.setGitProfileUrl("test01ggg");
            memberService.join(joinRequest);
    }

    @PostMapping("/findId")
    public ResponseEntity<SuccessResponse<Map<String, String>>> findId(@RequestBody @Valid FindIdRequest request) {
        log.info(request.getMemberName());
        log.info(request.getEmail());
        String memberId = memberService.findId(request);

        return ResponseEntity.ok(SuccessResponse.<Map<String, String>>builder()
                .code("200")
                .message("아이디 찾기 성공")
                .data(Map.of("memberId", memberId))
                .build());
    }

    @PostMapping("/findPw")
    public ResponseEntity<SuccessResponse<Void>> findPw(@RequestBody @Valid FIndPwRequest request) throws MessagingException {
        memberService.findPw(request);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("비밀번호 찾기 성공")
                .build());
    }
}
