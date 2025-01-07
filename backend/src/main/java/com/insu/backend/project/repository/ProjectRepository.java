package com.insu.backend.project.repository;

import com.insu.backend.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long>, ProjectRepositoryCustom {
}
