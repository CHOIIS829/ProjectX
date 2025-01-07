package com.insu.backend.post.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.request.ProjectSearchRequest;
import com.insu.backend.project.response.ProjectListResponse;

public interface PostRepositoryCustom {

    PageResponse<ProjectListResponse> getList(ProjectSearchRequest projectSearch);
}
