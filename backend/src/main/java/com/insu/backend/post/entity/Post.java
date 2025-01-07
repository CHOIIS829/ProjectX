package com.insu.backend.post.entity;

import com.insu.backend.global.BaseEntity;
import com.insu.backend.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_NO")
    private Long postNo;

    private String postTitle;
    private String postContent;
    private String category; // 일상 / 공부 / 스터디
    private String isDeleted; // 삭제 여부

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @Builder
    public Post(String postTitle, String postContent, String category, String isDeleted, Member member) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.category = category;
        this.isDeleted = isDeleted;
        this.member = member;
    }

    public void editPost(String postTitle, String postContent, String category) {
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.category = category;
    }

    public void deleteProject() {
        this.isDeleted = "Y";
    }
}
