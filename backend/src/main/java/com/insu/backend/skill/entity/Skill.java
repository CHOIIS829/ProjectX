package com.insu.backend.skill.entity;

import jakarta.persistence.Column;
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
public class Skill {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "SKILL_NO")
    private Long skillNo;

    private String skillName;

//    @ManyToMany(mappedBy = "skills")
//    private List<Member> members = new ArrayList<>();

    @Builder
    public Skill(String skillName) {
        this.skillName = skillName;
    }
}
