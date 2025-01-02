package com.insu.backend.skill.entity;

import com.insu.backend.member.entity.Member;
import com.insu.backend.project.entity.Project;
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

    @ManyToMany(mappedBy = "skills", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Member> members = new ArrayList<>();

    @ManyToMany(mappedBy = "skills", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Project> projects = new ArrayList<>();

    @Builder
    public Skill(String skillName) {
        this.skillName = skillName;
    }
}
