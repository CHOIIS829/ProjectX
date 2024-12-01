package com.insu.backend.global.jwt.repository;

import com.insu.backend.global.jwt.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {
}
