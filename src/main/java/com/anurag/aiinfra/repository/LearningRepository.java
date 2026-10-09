package com.anurag.aiinfra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.aiinfra.entity.LearningModule;

public interface LearningRepository extends JpaRepository<LearningModule, Long> {

}