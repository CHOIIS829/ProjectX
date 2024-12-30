package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;

public interface ProjectRepositoryCustom {

    PageResponse<ProjectList> getList(ProjectSearch projectSearch);
}
