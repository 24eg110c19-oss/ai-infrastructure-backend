package com.anurag.aiinfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.aiinfra.entity.LearningProgress;

public interface LearningProgressRepository extends JpaRepository<LearningProgress, Long> {

    List<LearningProgress> findByUserId(Long userId);

    LearningProgress findByUserIdAndModuleId(Long userId, Long moduleId);
}