package com.anurag.aiinfra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.aiinfra.entity.Alert;
import com.anurag.aiinfra.repository.AlertRepository;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public List<Alert> getAllAlerts() {
        return alertRepository.findAllByOrderByIdDesc();
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }

    public void deleteAllAlerts() {
        alertRepository.deleteAll();
    }

    public Alert getLatestAlertByResource(String resource) {
        return alertRepository.findTopByResourceOrderByIdDesc(resource);
    }
}