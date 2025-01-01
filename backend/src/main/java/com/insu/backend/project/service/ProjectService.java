package com.insu.backend.project.service;

import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.global.exception.NotFoundPost;
import com.insu.backend.global.response.PageResponse;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.project.entity.Project;
import com.insu.backend.project.repository.ProjectRepository;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;
import com.insu.backend.project.response.ProjectOne;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

        member.addProject(project);

        projectRepository.save(project);
    }

    public ProjectOne getProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(NotFoundPost::new);

        List<Skill> skills = project.getSkills();

        ProjectOne projectOne = ProjectOne.builder()
                .projectTitle(project.getProjectTitle())
                .projectContent(project.getProjectContent())
                .category(project.getCategory())
                .memberId(project.getMember().getMemberId())
                .createAt(project.getCreateAt())
                .updateAt(project.getUpdateAt())
                .skills(skills.stream()
                        .map(Skill::getSkillName)
                        .toList())
                .build();

        return projectOne;
    }

    public PageResponse<ProjectList> getList(ProjectSearch projectSearch) {
        return projectRepository.getList(projectSearch);
    }

    @Transactional
    public ProjectOne editProject(Long projectId, CreateProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(NotFoundPost::new);

        Member member = memberRepository.findByMemberId(request.getMemberId())
                .orElseThrow(NotFoundMemberId::new);

        List<Skill> skills = skillRepository.findBySkillNameIn(request.getSkills());

        project.editProject(request.getProjectTitle(), request.getProjectContent(), request.getCategory(), skills);

        return ProjectOne.builder()
                .projectTitle(project.getProjectTitle())
                .projectContent(project.getProjectContent())
                .category(project.getCategory())
                .memberId(project.getMember().getMemberId())
                .createAt(project.getCreateAt())
                .updateAt(project.getUpdateAt())
                .skills(skills.stream()
                        .map(Skill::getSkillName)
                        .toList())
                .build();
    }
}
