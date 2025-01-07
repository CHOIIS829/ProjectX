package com.insu.backend.project.controller;

import com.insu.backend.global.jwt.dto.CustomUserDetails;
import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.project.request.ProjectSearchRequest;
import com.insu.backend.project.response.ProjectListResponse;
import com.insu.backend.project.response.ProjectOneResponse;
import com.insu.backend.project.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/create")
    public ResponseEntity<SuccessResponse<Void>> createProject(@RequestBody @Valid CreateProjectRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();

        projectService.createProject(request, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 작성 성공")
                .build());
    }

    @GetMapping("/get/{projectNo}")
    public ResponseEntity<SuccessResponse<Optional<ProjectOneResponse>>> getProject(@PathVariable Long projectNo) {
        Optional<ProjectOneResponse> optionalProject = projectService.getProject(projectNo);

        return ResponseEntity.ok(SuccessResponse.<Optional<ProjectOneResponse>>builder()
                .code("200")
                .message("프로젝트 조회 성공")
                .data(optionalProject)
                .build());
    }

    @GetMapping("/list")
    public ResponseEntity<SuccessResponse<PageResponse<ProjectListResponse>>> getList(@RequestBody ProjectSearchRequest projectSearch) {
        PageResponse<ProjectListResponse> projects = projectService.getList(projectSearch);

        return ResponseEntity.ok(SuccessResponse.<PageResponse<ProjectListResponse>>builder()
                .code("200")
                .message("프로젝트 목록 조회 성공")
                .data(projects)
                .build());
    }

    @PatchMapping("/edit/{projectId}")
    public ResponseEntity<SuccessResponse<ProjectOneResponse>> editProject(@PathVariable Long projectId, @RequestBody @Valid CreateProjectRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        String memberId = userDetails.getUsername();

        ProjectOneResponse projectOne = projectService.editProject(projectId, request, memberId);

        return ResponseEntity.ok(SuccessResponse.<ProjectOneResponse>builder()
                .code("200")
                .message("프로젝트 수정 성공")
                .data(projectOne)
                .build());
    }

    @PostMapping("/delete/{projectNo}")
    public ResponseEntity<SuccessResponse<Void>> deleteProject(@PathVariable Long projectNo, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String memberId = userDetails.getUsername();

        projectService.deleteProject(projectNo, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 삭제 성공")
                .build());
    }

    @PostMapping("/close/{projectNo}")
    public ResponseEntity<SuccessResponse<Void>> closeProject(@PathVariable Long projectNo, @AuthenticationPrincipal CustomUserDetails userDetails) {
        String memberId = userDetails.getUsername();

        projectService.closeProject(projectNo, memberId);

        return ResponseEntity.ok(SuccessResponse.<Void>builder()
                .code("200")
                .message("프로젝트 종료 성공")
                .build());
    }
}
