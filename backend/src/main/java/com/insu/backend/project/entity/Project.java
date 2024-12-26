package com.insu.backend.project.entity;

import com.insu.backend.global.BaseEntity;
import com.insu.backend.member.entity.Member;
import com.insu.backend.skill.entity.Skill;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Project extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROJECT_NO")
    private Long projectNo;

    private String projectTitle;
    private String projectContent;
    private String category; // 구인 or 구직

    @ManyToMany
    @JoinTable(
            name = "PROJECT_SKILL", // 중간 테이블
            joinColumns = @JoinColumn(name = "PROJECT_NO"), // 현재 엔티티의 외래키
            inverseJoinColumns = @JoinColumn(name = "SKILL_NO") // 중간 테이블의 외래키
    )
    private List<Skill> skills = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;


}
