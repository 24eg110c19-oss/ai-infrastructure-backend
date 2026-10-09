package com.anurag.aiinfra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.aiinfra.entity.LearningModule;
import com.anurag.aiinfra.repository.LearningRepository;

@Service
public class LearningService {

    private final LearningRepository learningRepository;

    public LearningService(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
    }

    public List<LearningModule> getAllModules() {
        return learningRepository.findAll();
    }

    public LearningModule getModuleById(Long id) {
        return learningRepository.findById(id).orElse(null);
    }

    public LearningModule saveModule(LearningModule module) {
        return learningRepository.save(module);
    }

    public void deleteModule(Long id) {
        learningRepository.deleteById(id);
    }
}