package com.insu.backend.project.service;

import com.insu.backend.global.exception.InvalidPostAuthorException;
import com.insu.backend.global.exception.NotFoundMemberId;
import com.insu.backend.global.exception.NotFoundPost;
import com.insu.backend.global.response.PageResponse;
import com.insu.backend.member.entity.Member;
import com.insu.backend.member.repository.MemberRepository;
import com.insu.backend.project.entity.Project;
import com.insu.backend.project.repository.ProjectRepository;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.project.response.ProjectListResponse;
import com.insu.backend.project.response.ProjectOneResponse;
import com.insu.backend.skill.entity.Skill;
import com.insu.backend.skill.repository.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final SkillRepository skillRepository;

    public void createProject(CreateProjectRequest request, String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(NotFoundMemberId::new);

        List<Skill> skillS = skillRepository.findBySkillNameIn(request.getSkills()); // {"Java", "Spring"} -> List<Skill>

        Project project = Project.builder()
                .projectTitle(request.getProjectTitle())
                .projectContent(request.getProjectContent())
                .category(request.getCategory())
                .isClosed("N")
                .isDeleted("N")
                .skills(skillS)
                .member(member)
                .build();

        member.addProject(project);

        projectRepository.save(project);
    }

    public Optional<ProjectOneResponse> getProject(Long projectNo) {
        Project project = projectRepository.findById(projectNo)
                .orElseThrow(NotFoundPost::new);

        List<Skill> skills = project.getSkills();

        if(project.getIsDeleted().equals("Y")) {
            return Optional.of(ProjectOneResponse.builder()
                    .projectTitle("삭제된 프로젝트입니다.")
                    .build());
        }

        return Optional.of(ProjectOneResponse.toResponse(project, skills));
    }

    public PageResponse<ProjectListResponse> getList(PageSearchDto projectSearch) {
        return projectRepository.getList(projectSearch);
    }

    @Transactional
    public ProjectOneResponse editProject(Long projectId, CreateProjectRequest request, String memberId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(NotFoundPost::new);

        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(NotFoundMemberId::new);

        List<Skill> skills = skillRepository.findBySkillNameIn(request.getSkills());

        if(!Objects.equals(project.getMember().getMemberId(), memberId)) {
            throw new InvalidPostAuthorException();
        }

        project.editProject(request.getProjectTitle(), request.getProjectContent(), request.getCategory(), skills);

        return ProjectOneResponse.toResponse(project, skills);
    }

    @Transactional
    public void deleteProject(Long projectNo, String memberId) {
        Project project = projectRepository.findById(projectNo)
                .orElseThrow(NotFoundPost::new);

        if(Objects.equals(project.getMember().getMemberId(), memberId)) {
            project.deleteProject();
        } else {
            throw new InvalidPostAuthorException();
        }
    }

    @Transactional
    public void closeProject(Long projectNo, String memberId) {
        Project project = projectRepository.findById(projectNo)
                .orElseThrow(NotFoundPost::new);

        if(Objects.equals(project.getMember().getMemberId(), memberId)) {
            project.closeProject();
        } else {
            throw new InvalidPostAuthorException();
        }
    }
}
