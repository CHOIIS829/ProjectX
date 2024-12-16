package com.insu.backend.member.entity;

import com.insu.backend.skill.entity.Skill;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class MemberSkill {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "MEMBER_SKILL_NO")
    private Long memberSkillNo;

    @ManyToOne
    @JoinColumn(name = "MEMBER_NO")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "SKILL_NO")
    private Skill skill;

    @Builder
    public MemberSkill(Member member, Skill skill) {
        this.member = member;
        this.skill = skill;
    }
}
