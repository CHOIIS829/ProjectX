package com.insu.backend.skill.service;

import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.skill.request.InsertSkill;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public void insert(InsertSkill skillList) {

        skillList.getSkillList().forEach(skillName -> {

            Skill skill = Skill.builder()
                            .skillName(skillName)
                            .build();

            skillRepository.save(skill);
        });

    }
}
