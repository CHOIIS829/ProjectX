package com.insu.backend.global.jwt.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Refresh {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String memberId;
    private String refresh;
    private String expiration;

    @Builder
    public Refresh(String memberId, String refresh, String expiration) {
        this.memberId = memberId;
        this.refresh = refresh;
        this.expiration = expiration;
    }
}
