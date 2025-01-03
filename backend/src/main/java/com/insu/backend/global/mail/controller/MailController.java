package com.insu.backend.global.mail.controller;

import com.insu.backend.global.mail.request.CheckEmailRequest;
import com.insu.backend.global.mail.service.MailService;
import com.insu.backend.global.response.SuccessResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<SuccessResponse<Void>> emailCheck(String email) throws MessagingException {
        mailService.sendMail(email);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                        .code("200")
                        .message("메일 전송 성공")
                        .build());
    }

    @PostMapping("/checkEmail")
    public ResponseEntity<SuccessResponse<Void>> checkMail(@RequestBody @Valid CheckEmailRequest request) {
        mailService.checkMail(request);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                        .code("200")
                        .message("인증 성공")
                        .build());
    }
}
