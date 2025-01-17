package com.insu.backend.member.entity;

import com.insu.backend.global.BaseEntity;
import com.insu.backend.member.request.UpdateProfileRequest;
import com.insu.backend.post.entity.Post;
import com.insu.backend.project.entity.Project;
import com.insu.backend.skill.entity.Skill;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
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
    private String gitProfileUrl;
    private String profileImg;
    private String role;
    private String isProfileComplete;
    private String isDeleted;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "MEMBER_SKILL", // 중간 테이블
            joinColumns = @JoinColumn(name = "MEMBER_NO"), // 현재 엔티티의 외래키
            inverseJoinColumns = @JoinColumn(name = "SKILL_NO") // 중간 테이블의 외래키
    )
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String memberId, String memberPwd, String memberName, String email, /*String phone,*/ String gitProfileUrl, String profileImg, String role, String isProfileComplete, String isDeleted, List<Skill> skills, List<Project> projects) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberName = memberName;
        this.email = email;
//        this.phone = phone;
        this.gitProfileUrl = gitProfileUrl;
        this.profileImg = profileImg;
        this.role = role;
        this.isProfileComplete = isProfileComplete;
        this.isDeleted = isDeleted;
        this.skills = skills;
        this.projects = projects;
    }

    // 연관관계 편의 메서드
    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void changePassword(String password) {
        this.memberPwd = password;
    }

    public void changeProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
    public void updateProfile(UpdateProfileRequest request, List<Skill> skills) {
        this.email = request.getEmail();
        this.gitProfileUrl = request.getGitProfileUrl();
        this.isProfileComplete = "Y";
        this.skills = skills;
    }

    public void deleteMember() {
        this.isDeleted = "Y";
    }

}
