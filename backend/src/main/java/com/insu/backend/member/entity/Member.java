package com.insu.backend.member.entity;

import com.insu.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MEMBER_NO")
    private Long memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String email;
//    private String phone;
    private String git;
    private String role;

    @OneToMany(mappedBy = "member")
    private List<MemberSkill> memberSkills = new ArrayList<>();

    @Builder
    public Member(String memberId, String memberPwd, String memberName, String email, /*String phone,*/ String git, String role) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.email = email;
//        this.phone = phone;
        this.git = git;
        this.role = role;
    }

    // 비밀번호 변경
    public void changePassword(String password) {
        this.memberPwd = password;
    }
}
