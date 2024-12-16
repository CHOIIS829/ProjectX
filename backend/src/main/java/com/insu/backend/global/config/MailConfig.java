package com.insu.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); // JavaMailSenderImpl 객체 생성
        mailSender.setHost("smtp.gmail.com"); // SMTP 서버 설정
        mailSender.setPort(587); // SMTP 포트 설정
        mailSender.setUsername("vmfhwprxmX@gmail.com"); // 계정 설정
        mailSender.setPassword("dxbt lkqs xove dbdz"); // 패스워드 설정

        Properties props = mailSender.getJavaMailProperties(); // JavaMailSenderImpl 객체의 Properties 객체 가져오기
        props.put("mail.transport.protocol", "smtp"); // 프로토콜 설정
        props.put("mail.smtp.auth", "true"); // SMTP 인증 설정
        props.put("mail.smtp.starttls.enable", "true"); // SMTP TLS 설정
        props.put("mail.debug", "true"); // 디버그 설정

        return mailSender;
    }
}
