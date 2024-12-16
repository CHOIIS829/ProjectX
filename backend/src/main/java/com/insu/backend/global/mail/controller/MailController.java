package com.insu.backend.global.mail.controller;

import com.insu.backend.global.mail.dto.MailDTO;
import com.insu.backend.global.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/emailCheck")
    public String emailCheck(@RequestBody MailDTO mailDTO) throws MessagingException {
        String authCode = mailService.sendMail(mailDTO.getEmail());
        return authCode;
    }
}
