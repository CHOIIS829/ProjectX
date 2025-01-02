package com.insu.backend.skill.service;

import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.skill.request.InsertSkill;
import com.insu.backend.skill.response.SkillNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public void insert(InsertSkill skillList) {

        skillList.getSkills().forEach(skillName -> {

            Skill skill = Skill.builder()
                            .skillName(skillName)
                            .build();

            skillRepository.save(skill);
        });

    }

    public List<SkillNameResponse> getList(String keyword) {
        if(keyword == null) {
            return skillRepository.findAll().stream()
                    .map(SkillNameResponse::new)
                    .collect(Collectors.toList());
        } else {
            return skillRepository.findBySkillNameContaining(keyword).stream()
                    .map(SkillNameResponse::new)
                    .collect(Collectors.toList());
        }
    }

    public void delete(Long skillNo) {
        skillRepository.deleteById(skillNo);
    }
}
