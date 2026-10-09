package com.anurag.aiinfra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.aiinfra.entity.LearningProgress;
import com.anurag.aiinfra.repository.LearningProgressRepository;

@Service
public class LearningProgressService {

    private final LearningProgressRepository learningProgressRepository;

    public LearningProgressService(
            LearningProgressRepository learningProgressRepository) {
        this.learningProgressRepository = learningProgressRepository;
    }

    public List<LearningProgress> getProgressByUser(Long userId) {
        return learningProgressRepository.findByUserId(userId);
    }

    public LearningProgress saveProgress(LearningProgress progress) {

        LearningProgress existingProgress =
                learningProgressRepository.findByUserIdAndModuleId(
                        progress.getUserId(),
                        progress.getModuleId()
                );

        if (existingProgress != null) {
            existingProgress.setCompleted(progress.isCompleted());

            return learningProgressRepository.save(existingProgress);
        }

        return learningProgressRepository.save(progress);
    }

    public void deleteProgress(Long userId, Long moduleId) {

        LearningProgress progress =
                learningProgressRepository.findByUserIdAndModuleId(
                        userId,
                        moduleId
                );

        if (progress != null) {
            learningProgressRepository.delete(progress);
        }
    }
}