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

import com.anurag.aiinfra.entity.LearningModule;
import com.anurag.aiinfra.service.LearningService;

@RestController
@RequestMapping("/learning")
@CrossOrigin(origins = "http://localhost:5181")
public class LearningController {

    private final LearningService learningService;

    public LearningController(LearningService learningService) {
        this.learningService = learningService;
    }

    @GetMapping
    public List<LearningModule> getAllModules() {
        return learningService.getAllModules();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long id) {

        LearningModule module = learningService.getModuleById(id);

        if (module == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(module);
    }

    @PostMapping
    public LearningModule saveModule(@RequestBody LearningModule module) {
        return learningService.saveModule(module);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {

        LearningModule module = learningService.getModuleById(id);

        if (module == null) {
            return ResponseEntity.notFound().build();
        }

        learningService.deleteModule(id);

        return ResponseEntity.ok("Learning module deleted successfully");
    }
}