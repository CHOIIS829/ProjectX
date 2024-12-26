package com.insu.backend.project.service;

import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.project.entity.Project;
import com.insu.backend.project.repository.ProjectRepository;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;

    public void createProject(CreateProjectRequest request) {
        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(NotFoundMemberId::new);

        List<Skill> skillS = skillRepository.findBySkillNameIn(request.getSkills()); // {"Java", "Spring"} -> List<Skill>

        Project project = Project.builder()
                .projectTitle(request.getProjectTitle())
                .projectContent(request.getProjectContent())
                .category(request.getCategory())
                .skills(skillS)
                .member(member)
                .build();

        projectRepository.save(project);
    }
}
