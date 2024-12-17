package com.insu.backend.global.mail.service;

import com.insu.backend.global.exception.FailMailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String SENDER_EMAIL = "vmfhwprxmX@gmail.com";

    public String sendMail(String sendEmail) throws MessagingException{
        String number = createNumber();

        MimeMessage message = createMail(sendEmail, number);

        try {
            javaMailSender.send(message);
        }catch (MailException e) {
            log.error(">>>>> [ERROR] Fail to send mail : {}", e.getMessage());
            throw new FailMailSendException();
        }

        return number;
    }

    public MimeMessage createMail(String mail, String number) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(SENDER_EMAIL);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("인증번호 발송");
        String body = "";
        body += "<h1>요청하신 인증 번호입니다.</h1>";
        body += "<h2>인증 번호: " + number + "</h2>";
        body += "<h3>감사합니다.</h3>";
        message.setText(body, "UTF-8", "html");

        return message;
    }

    public String createNumber() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97)); // 소문자
                case 1 -> key.append((char) (random.nextInt(26) + 65)); // 대문자
                case 2 -> key.append(random.nextInt(10)); // 숫자
            }
        }
        return key.toString();
    }
}
