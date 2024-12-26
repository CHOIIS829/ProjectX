package com.insu.backend.project.controller;

import com.insu.backend.global.response.SuccessResponse;
import com.insu.backend.project.request.CreateProjectRequest;
import com.insu.backend.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
