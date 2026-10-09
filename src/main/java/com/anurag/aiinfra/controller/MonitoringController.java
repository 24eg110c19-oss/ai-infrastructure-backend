package com.anurag.aiinfra.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.aiinfra.entity.Alert;
import com.anurag.aiinfra.service.AlertService;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

@RestController
@RequestMapping("/monitoring")
@CrossOrigin(origins = "http://localhost:5181")
public class MonitoringController {

    private final AlertService alertService;

    private final SystemInfo systemInfo;

    private final CentralProcessor processor;

    private final GlobalMemory memory;

    private boolean cpuAlertActive = false;
    private boolean ramAlertActive = false;
    private boolean diskAlertActive = false;
    private boolean gpuAlertActive = false;

    private long cpuLastAlertTime = 0;
    private long ramLastAlertTime = 0;
    private long diskLastAlertTime = 0;
    private long gpuLastAlertTime = 0;

    private static final long COOLDOWN = 300000;

    public MonitoringController(AlertService alertService) {

        this.alertService = alertService;

        this.systemInfo = new SystemInfo();

        this.processor = systemInfo.getHardware().getProcessor();

        this.memory = systemInfo.getHardware().getMemory();
    }

    @GetMapping
    public Map<String, Object> getMonitoringData() {

        Map<String, Object> data = new HashMap<>();

        int cpu = getRealCPUUsage();

        int ram = getRealRAMUsage();

        int disk = getRealDiskUsage();

        int gpu = getRealGPUUsage();

        data.put("cpu", cpu);

        data.put("ram", ram);

        data.put("disk", disk);

        data.put("gpu", gpu);

        String alertMessage;

        if (ram > 70) {

            alertMessage = "High RAM usage detected";

        } else if (cpu > 75) {

            alertMessage = "High CPU usage detected";

        } else if (disk > 80) {

            alertMessage = "High Disk usage detected";

        } else if (gpu > 75) {

            alertMessage = "High GPU usage detected";

        } else {

            alertMessage = "Infrastructure operating normally";
        }

        data.put("alert", alertMessage);

        data.put("status", "RUNNING");

        checkCPU(cpu);

        checkRAM(ram);

        checkDisk(disk);

        checkGPU(gpu);

        return data;
    }

    private int getRealCPUUsage() {

        double cpuLoad = processor.getSystemCpuLoad(1000);

        int cpuPercentage = (int) Math.round(cpuLoad * 100);

        if (cpuPercentage < 0) {
            cpuPercentage = 0;
        }

        if (cpuPercentage > 100) {
            cpuPercentage = 100;
        }

        return cpuPercentage;
    }

    private int getRealRAMUsage() {

        long totalMemory = memory.getTotal();

        long availableMemory = memory.getAvailable();

        long usedMemory = totalMemory - availableMemory;

        double ramLoad =
                (double) usedMemory / totalMemory;

        int ramPercentage =
                (int) Math.round(ramLoad * 100);

        if (ramPercentage < 0) {
            ramPercentage = 0;
        }

        if (ramPercentage > 100) {
            ramPercentage = 100;
        }

        return ramPercentage;
    }

    private int getRealDiskUsage() {

        java.io.File file = new java.io.File("C:\\");

        long totalSpace = file.getTotalSpace();

        long freeSpace = file.getFreeSpace();

        long usedSpace = totalSpace - freeSpace;

        if (totalSpace == 0) {
            return 0;
        }

        double diskLoad =
                (double) usedSpace / totalSpace;

        int diskPercentage =
                (int) Math.round(diskLoad * 100);

        if (diskPercentage < 0) {
            diskPercentage = 0;
        }

        if (diskPercentage > 100) {
            diskPercentage = 100;
        }

        return diskPercentage;
    }

    private int getRealGPUUsage() {

        try {

            Process process = new ProcessBuilder(
                    "powershell.exe",
                    "-Command",
                    "(Get-Counter '\\GPU Engine(*)\\Utilization Percentage').CounterSamples | Measure-Object -Property CookedValue -Maximum | Select-Object -ExpandProperty Maximum"
            ).start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String output = reader.readLine();

            process.waitFor();

            if (output != null && !output.trim().isEmpty()) {

                double gpuLoad =
                        Double.parseDouble(output.trim());

                int gpuPercentage =
                        (int) Math.round(gpuLoad);

                if (gpuPercentage < 0) {
                    gpuPercentage = 0;
                }

                if (gpuPercentage > 100) {
                    gpuPercentage = 100;
                }

                return gpuPercentage;
            }

        } catch (Exception e) {

            System.out.println(
                    "Unable to read GPU usage: "
                            + e.getMessage()
            );
        }

        return 0;
    }

    private void checkCPU(int cpu) {

        if (cpu > 75) {

            long currentTime =
                    System.currentTimeMillis();

            if (!cpuAlertActive &&
                    currentTime - cpuLastAlertTime >= COOLDOWN) {

                createAlert(
                        "CPU",
                        cpu,
                        "High CPU usage detected"
                );

                cpuAlertActive = true;

                cpuLastAlertTime = currentTime;
            }

        } else {

            cpuAlertActive = false;
        }
    }

    private void checkRAM(int ram) {

        if (ram > 70) {

            long currentTime =
                    System.currentTimeMillis();

            if (!ramAlertActive &&
                    currentTime - ramLastAlertTime >= COOLDOWN) {

                createAlert(
                        "RAM",
                        ram,
                        "High RAM usage detected"
                );

                ramAlertActive = true;

                ramLastAlertTime = currentTime;
            }

        } else {

            ramAlertActive = false;
        }
    }

    private void checkDisk(int disk) {

        if (disk > 80) {

            long currentTime =
                    System.currentTimeMillis();

            if (!diskAlertActive &&
                    currentTime - diskLastAlertTime >= COOLDOWN) {

                createAlert(
                        "Disk",
                        disk,
                        "High Disk usage detected"
                );

                diskAlertActive = true;

                diskLastAlertTime = currentTime;
            }

        } else {

            diskAlertActive = false;
        }
    }

    private void checkGPU(int gpu) {

        if (gpu > 75) {

            long currentTime =
                    System.currentTimeMillis();

            if (!gpuAlertActive &&
                    currentTime - gpuLastAlertTime >= COOLDOWN) {

                createAlert(
                        "GPU",
                        gpu,
                        "High GPU usage detected"
                );

                gpuAlertActive = true;

                gpuLastAlertTime = currentTime;
            }

        } else {

            gpuAlertActive = false;
        }
    }

    private void createAlert(
            String resource,
            int usage,
            String message) {

        Alert alert = new Alert();

        alert.setResource(resource);

        alert.setUsage(usage);

        alert.setSeverity("HIGH");

        alert.setMessage(message);

        String currentTime =
                LocalTime.now()
                        .format(
                                DateTimeFormatter.ofPattern("HH:mm:ss")
                        );

        alert.setTime(currentTime);

        alertService.saveAlert(alert);
    }
}