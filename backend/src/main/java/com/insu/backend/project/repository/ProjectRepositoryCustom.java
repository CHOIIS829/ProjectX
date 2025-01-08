package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.global.dto.PageSearchDto;
import com.insu.backend.project.response.ProjectListResponse;

public interface ProjectRepositoryCustom {

    PageResponse<ProjectListResponse> getList(PageSearchDto projectSearch);
}
