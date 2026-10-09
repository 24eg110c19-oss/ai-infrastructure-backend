package com.anurag.aiinfra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anurag.aiinfra.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    List<Alert> findAllByOrderByIdDesc();

    Alert findTopByResourceOrderByIdDesc(String resource);
}