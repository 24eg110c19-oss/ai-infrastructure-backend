package com.anurag.aiinfra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "http://localhost:5181")
public class AIController {

    @GetMapping("/analyze")
    public Map<String, String> analyzeInfrastructure(
            @RequestParam int cpu,
            @RequestParam int ram,
            @RequestParam int disk,
            @RequestParam int gpu) {

        Map<String, String> result = new HashMap<>();

        String analysis;
        String recommendation;
        String severity;

        if (cpu > 75) {
            analysis = "CPU usage is high.";
            recommendation = "Reduce workload or scale CPU resources.";
            severity = "HIGH";

        } else if (ram > 70) {
            analysis = "RAM usage is high.";
            recommendation = "Increase memory or reduce running processes.";
            severity = "HIGH";

        } else if (disk > 80) {
            analysis = "Disk usage is high.";
            recommendation = "Clean unnecessary data or increase storage.";
            severity = "HIGH";

        } else if (gpu > 75) {
            analysis = "GPU usage is high.";
            recommendation = "Optimize AI workloads or add GPU resources.";
            severity = "HIGH";

        } else {
            analysis = "Infrastructure is operating normally.";
            recommendation = "Continue monitoring the infrastructure.";
            severity = "NORMAL";
        }

        result.put("analysis", analysis);
        result.put("recommendation", recommendation);
        result.put("severity", severity);

        return result;
    }
}