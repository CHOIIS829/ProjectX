package com.insu.backend.skill.repository;

import com.insu.backend.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findBySkillNameIn(List<String> skills);

    List<Skill> findBySkillNameContaining(String skillName);
}
