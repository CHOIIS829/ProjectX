package com.insu.backend.global.mail.repository;

import com.insu.backend.global.mail.entity.EmailRandomCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRandomCodeRepository extends JpaRepository<EmailRandomCode, Long> {
    Optional<EmailRandomCode> findByEmailAndCode(String email, String code);
}
