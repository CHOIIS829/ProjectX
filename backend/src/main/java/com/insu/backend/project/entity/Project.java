package com.insu.backend.project.entity;

import com.insu.backend.global.BaseEntity;
import com.insu.backend.member.entity.Member;
import com.insu.backend.skill.entity.Skill;
import jakarta.persistence.*;
import lombok.Builder;
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
    private String isClosed; // 종료 여부
    private String isDeleted; // 삭제 여부

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PROJECT_SKILL", // 중간 테이블
            joinColumns = @JoinColumn(name = "PROJECT_NO"), // 현재 엔티티의 외래키
            inverseJoinColumns = @JoinColumn(name = "SKILL_NO") // 중간 테이블의 외래키
    )
    private List<Skill> skills = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Builder
    public Project(String projectTitle, String projectContent, String category, String isClosed, String isDeleted, List<Skill> skills, Member member) {
        this.projectTitle = projectTitle;
        this.projectContent = projectContent;
        this.category = category;
        this.isClosed = isClosed;
        this.isDeleted = isDeleted;
        this.skills = skills;
        this.member = member;
    }

    public void editProject(String projectTitle, String projectContent, String category, List<Skill> skills) {
        this.projectTitle = projectTitle;
        this.projectContent = projectContent;
        this.category = category;
        this.skills = skills;
    }

    public void closeProject() {
        this.isClosed = "Y";
    }

    public void deleteProject() {
        this.isDeleted = "Y";
    }
}
