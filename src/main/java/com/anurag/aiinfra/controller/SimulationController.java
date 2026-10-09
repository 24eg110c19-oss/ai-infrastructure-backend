package com.anurag.aiinfra.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
@CrossOrigin(origins = "http://localhost:5181")
public class SimulationController {

    @GetMapping
    public Map<String, Integer> simulate(
            @RequestParam String type) {

        Map<String, Integer> data = new HashMap<>();

        if (type.equalsIgnoreCase("normal")) {

            data.put("cpu", 40);
            data.put("ram", 50);
            data.put("disk", 35);
            data.put("gpu", 30);

        } else if (type.equalsIgnoreCase("cpu")) {

            data.put("cpu", 90);
            data.put("ram", 55);
            data.put("disk", 40);
            data.put("gpu", 35);

        } else if (type.equalsIgnoreCase("ram")) {

            data.put("cpu", 50);
            data.put("ram", 90);
            data.put("disk", 45);
            data.put("gpu", 35);

        } else if (type.equalsIgnoreCase("gpu")) {

            data.put("cpu", 55);
            data.put("ram", 60);
            data.put("disk", 40);
            data.put("gpu", 90);

        } else if (type.equalsIgnoreCase("disk")) {

            data.put("cpu", 50);
            data.put("ram", 55);
            data.put("disk", 90);
            data.put("gpu", 30);

        } else {

            data.put("cpu", 40);
            data.put("ram", 50);
            data.put("disk", 35);
            data.put("gpu", 30);
        }

        return data;
    }
}