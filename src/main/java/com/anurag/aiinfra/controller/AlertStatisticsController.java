package com.anurag.aiinfra.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.aiinfra.entity.Alert;
import com.anurag.aiinfra.service.AlertService;

@RestController
@RequestMapping("/alert-statistics")
@CrossOrigin(origins = "http://localhost:5181")
public class AlertStatisticsController {

    private final AlertService alertService;

    public AlertStatisticsController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public Map<String, Object> getStatistics() {

        List<Alert> alerts = alertService.getAllAlerts();

        int totalAlerts = alerts.size();
        int cpuAlerts = 0;
        int ramAlerts = 0;
        int diskAlerts = 0;
        int gpuAlerts = 0;
        int highSeverityAlerts = 0;

        for (Alert alert : alerts) {

            if ("CPU".equalsIgnoreCase(alert.getResource())) {
                cpuAlerts++;
            }

            if ("RAM".equalsIgnoreCase(alert.getResource())) {
                ramAlerts++;
            }

            if ("Disk".equalsIgnoreCase(alert.getResource())) {
                diskAlerts++;
            }

            if ("GPU".equalsIgnoreCase(alert.getResource())) {
                gpuAlerts++;
            }

            if ("HIGH".equalsIgnoreCase(alert.getSeverity())) {
                highSeverityAlerts++;
            }
        }

        Map<String, Object> statistics = new HashMap<>();

        statistics.put("totalAlerts", totalAlerts);
        statistics.put("cpuAlerts", cpuAlerts);
        statistics.put("ramAlerts", ramAlerts);
        statistics.put("diskAlerts", diskAlerts);
        statistics.put("gpuAlerts", gpuAlerts);
        statistics.put("highSeverityAlerts", highSeverityAlerts);

        return statistics;
    }
}