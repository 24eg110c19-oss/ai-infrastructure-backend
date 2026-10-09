package com.anurag.aiinfra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.aiinfra.entity.Alert;
import com.anurag.aiinfra.service.AlertService;

@RestController
@RequestMapping("/alerts")
@CrossOrigin(origins = "http://localhost:5181")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAlert(@PathVariable Long id) {

        alertService.deleteAlert(id);

        return ResponseEntity.ok(
            "Alert deleted successfully"
        );
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> deleteAllAlerts() {

        alertService.deleteAllAlerts();

        return ResponseEntity.ok(
            "All alerts deleted successfully"
        );
    }
}