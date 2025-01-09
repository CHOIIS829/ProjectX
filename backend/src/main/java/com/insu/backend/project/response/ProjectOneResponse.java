package com.insu.backend.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.insu.backend.project.entity.Project;
import com.insu.backend.skill.entity.Skill;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectOneResponse {

    private String projectTitle;
    private String projectContent;
    private String category;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateAt;
    private List<String> skills;

    public static ProjectOneResponse toResponse(Project project, List<Skill> skills) {
        return ProjectOneResponse.builder()
                .projectTitle(project.getProjectTitle())
                .projectContent(project.getProjectContent())
                .category(project.getCategory())
                .author(project.getMember().getMemberId())
                .createAt(project.getCreateAt())
                .updateAt(project.getUpdateAt())
                .skills(skills.stream()
                        .map(Skill::getSkillName)
                        .toList())
                .build();
    }

}
