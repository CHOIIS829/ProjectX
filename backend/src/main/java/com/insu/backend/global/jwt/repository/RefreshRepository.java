package com.insu.backend.global.jwt.repository;

import com.insu.backend.global.jwt.entity.Refresh;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {
    @Transactional
    void deleteByRefresh(String refresh);

    boolean existsByRefresh(String refresh);
}
