package com.insu.backend.project.controller;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;
import com.insu.backend.project.response.ProjectOne;
import com.insu.backend.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createProject(@RequestBody @Valid CreateProjectRequest request) {
        projectService.createProject(request);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 작성 성공")
                .build());
    }

    @GetMapping("/get/{projectNo}")
    public ResponseEntity<SuccessResponse<Optional<ProjectOne>>> getProject(@PathVariable Long projectNo) {
        Optional<ProjectOne> optionalProject = projectService.getProject(projectNo);

        return ResponseEntity.ok(SuccessResponse.<Optional<ProjectOne>>builder()
                .code("200")
                .message("프로젝트 조회 성공")
                .data(optionalProject)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<PageResponse<ProjectList>>> getList(@RequestBody ProjectSearch projectSearch) {
        PageResponse<ProjectList> projects = projectService.getList(projectSearch);

        return ResponseEntity.ok(SuccessResponse.<PageResponse<ProjectList>>builder()
                .code("200")
                .message("프로젝트 목록 조회 성공")
                .data(projects)
                .build());
    }

    @PatchMapping("/edit/{projectId}")
    public ResponseEntity<SuccessResponse<ProjectOne>> editProject(@PathVariable Long projectId, @RequestBody @Valid CreateProjectRequest request) {
        ProjectOne projectOne = projectService.editProject(projectId, request);

        return ResponseEntity.ok(SuccessResponse.<ProjectOne>builder()
                .code("200")
                .message("프로젝트 수정 성공")
                .data(projectOne)
                .build());
    }

    @PostMapping("/delete/{projectNo}")
    public ResponseEntity<SuccessResponse<Void>> deleteProject(@PathVariable Long projectNo) {
        projectService.deleteProject(projectNo);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 삭제 성공")
                .build());
    }

    @PostMapping("/close/{projectNo}")
    public ResponseEntity<SuccessResponse<Void>> closeProject(@PathVariable Long projectNo) {
        projectService.closeProject(projectNo);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 종료 성공")
                .build());
    }
}
