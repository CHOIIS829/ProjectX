package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.request.ProjectSearchRequest;
import com.insu.backend.project.response.ProjectListResponse;

public interface ProjectRepositoryCustom {

    PageResponse<ProjectListResponse> getList(ProjectSearchRequest projectSearch);
}
