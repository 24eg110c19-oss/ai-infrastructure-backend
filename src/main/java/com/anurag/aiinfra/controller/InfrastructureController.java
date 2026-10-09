package com.anurag.aiinfra.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infrastructure")
@CrossOrigin(origins = "http://localhost:5174")
public class InfrastructureController {

    @GetMapping
    public List<Map<String, Object>> getInfrastructure() {

        return List.of(
            Map.of(
                "server", "Server 1",
                "status", "Running",
                "cpu", 45,
                "ram", 60,
                "disk", 40
            ),
            Map.of(
                "server", "Server 2",
                "status", "Running",
                "cpu", 32,
                "ram", 50,
                "disk", 35
            ),
            Map.of(
                "server", "Server 3",
                "status", "Stopped",
                "cpu", 0,
                "ram", 0,
                "disk", 20
            )
        );
    }
}