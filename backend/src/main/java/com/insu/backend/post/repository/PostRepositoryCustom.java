package com.insu.backend.post.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;

public interface PostRepositoryCustom {

    PageResponse<ProjectList> getList(ProjectSearch projectSearch);
}
