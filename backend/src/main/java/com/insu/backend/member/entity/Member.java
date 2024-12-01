package com.insu.backend.member.entity;

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
public class Member {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String email;
    private String role;

    @Builder
    public Member(String memberId, String memberPwd, String memberName, String email, String role) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.email = email;
        this.role = role;
    }
}
