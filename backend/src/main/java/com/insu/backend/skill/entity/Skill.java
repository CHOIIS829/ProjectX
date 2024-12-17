package com.insu.backend.skill.entity;

import com.insu.backend.member.entity.MemberSkill;
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
public class Skill {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SKILL_NO")
    private Long skillNo;

    private String skillName;

    @Builder
    public Skill(String skillName) {
        this.skillName = skillName;
    }
}