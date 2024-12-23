package com.insu.backend.member.entity;

import com.insu.backend.global.BaseEntity;
import com.insu.backend.skill.entity.Skill;
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
    private String profileImg;
    private String role;

    @ManyToMany
    @JoinTable(
            name = "MEMBER_SKILL", // 중간 테이블
            joinColumns = @JoinColumn(name = "MEMBER_NO"), // 현재 엔티티의 외래키
            inverseJoinColumns = @JoinColumn(name = "SKILL_NO") // 중간 테이블의 외래키
    )
    private List<Skill> skills = new ArrayList<>();

    @Builder
    public Member(String memberId, String memberPwd, String memberName, String email, /*String phone,*/ String git, String profileImg, String role, List<Skill> skills) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.email = email;
//        this.phone = phone;
        this.git = git;
        this.profileImg = profileImg;
        this.role = role;
        this.skills = skills;
    }


    // 비밀번호 변경
    public void changePassword(String password) {
        this.memberPwd = password;
    }

    // 프로필 이미지 변경
    public void changeProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
}
