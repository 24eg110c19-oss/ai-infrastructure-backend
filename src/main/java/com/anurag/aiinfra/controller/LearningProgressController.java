package com.anurag.aiinfra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.aiinfra.entity.LearningProgress;
import com.anurag.aiinfra.service.LearningProgressService;

@RestController
@RequestMapping("/learning-progress")
@CrossOrigin(origins = "http://localhost:5181")
public class LearningProgressController {

    private final LearningProgressService learningProgressService;

    public LearningProgressController(
            LearningProgressService learningProgressService) {
        this.learningProgressService = learningProgressService;
    }

    @GetMapping("/{userId}")
    public List<LearningProgress> getProgress(
            @PathVariable Long userId) {

        return learningProgressService.getProgressByUser(userId);
    }

    @PostMapping
    public LearningProgress saveProgress(
            @RequestBody LearningProgress progress) {

        return learningProgressService.saveProgress(progress);
    }

    @DeleteMapping("/{userId}/{moduleId}")
    public ResponseEntity<?> deleteProgress(
            @PathVariable Long userId,
            @PathVariable Long moduleId) {

        learningProgressService.deleteProgress(userId, moduleId);

        return ResponseEntity.ok(
                "Learning progress deleted successfully"
        );
    }
}