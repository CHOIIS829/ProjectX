package com.insu.backend.global.mail.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class EmailRandomCode {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email;

    private String code;

    @Builder
    public EmailRandomCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
