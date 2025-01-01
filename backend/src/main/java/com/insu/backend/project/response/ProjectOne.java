package com.insu.backend.project.response;

import com.insu.backend.skill.response.SkillNameResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ProjectOne {

    private String projectTitle;
    private String projectContent;
    private String category;
    private String memberId;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private List<String> skills;

}
