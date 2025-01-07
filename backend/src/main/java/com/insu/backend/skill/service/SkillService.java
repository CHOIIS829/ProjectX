package com.insu.backend.skill.service;

import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import com.insu.backend.skill.request.InsertSkillRequest;
import com.insu.backend.skill.response.SkillNameResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    public void insert(InsertSkillRequest skillList) {
        List<String> existingSkillNames = skillRepository.findBySkillNameIn(skillList.getSkills())
                .stream()
                .map(Skill::getSkillName)
                .toList();

        List<Skill> newSkills = skillList.getSkills().stream()
                .filter(skillName -> !existingSkillNames.contains(skillName))
                .map(Skill::new)
                .toList();

        skillRepository.saveAll(newSkills);
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
