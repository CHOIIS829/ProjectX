package com.insu.backend.project.repository;

import com.insu.backend.global.response.PageResponse;
import com.insu.backend.project.entity.Project;
import com.insu.backend.project.request.ProjectSearch;
import com.insu.backend.project.response.ProjectList;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProjectRepositoryCustom {

    PageResponse<ProjectList> getList(ProjectSearch projectSearch);
}
