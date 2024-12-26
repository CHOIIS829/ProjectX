package com.insu.backend.project.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.project.entity.Project;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.project.response.ProjectOne;
import com.insu.backend.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createProject(@RequestBody CreateProjectRequest request) {
        projectService.createProject(request);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 작성 성공")
                .build());
    }

    @GetMapping("/get/{projectId}")
    public ResponseEntity<SuccessResponse<ProjectOne>> getProject(@PathVariable Long projectId) {
        ProjectOne projectOne = projectService.getProject(projectId);

        return ResponseEntity.ok(SuccessResponse.<ProjectOne>builder()
                .code("200")
                .message("프로젝트 조회 성공")
                .data(projectOne)
                .build());
    }
}
