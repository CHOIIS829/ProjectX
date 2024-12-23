package com.insu.backend.skill.response;

import com.insu.backend.skill.entity.Skill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillNameResponse {

    private String skillName;

    @Builder
    public SkillNameResponse(Skill skill) {
        this.skillName = skill.getSkillName();
    }

}
