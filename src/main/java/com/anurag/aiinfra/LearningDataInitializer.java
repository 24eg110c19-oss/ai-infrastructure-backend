package com.anurag.aiinfra;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.anurag.aiinfra.entity.LearningModule;
import com.anurag.aiinfra.repository.LearningRepository;

@Component
public class LearningDataInitializer implements CommandLineRunner {

    private final LearningRepository learningRepository;

    public LearningDataInitializer(LearningRepository learningRepository) {
        this.learningRepository = learningRepository;
    }

    @Override
    public void run(String... args) {

        List<LearningModule> modules = learningRepository.findAll();

        for (LearningModule module : modules) {

            if (module.getTitle().equals("Physical Servers")) {

                module.setTopics(
                    "What is a physical server?|CPU and RAM|Storage|Networking|Server maintenance"
                );

            } else if (module.getTitle().equals("Virtual Machines")) {

                module.setTopics(
                    "What is virtualization?|Hypervisors|Virtual CPU and RAM|Virtual storage|Benefits of virtual machines"
                );

            } else if (module.getTitle().equals("Cloud Computing")) {

                module.setTopics(
                    "What is cloud computing?|IaaS|PaaS|SaaS|Cloud scalability"
                );

            } else if (module.getTitle().equals("Containers")) {

                module.setTopics(
                    "What is a container?|Container images|Application dependencies|Container deployment|Benefits of containers"
                );

            } else if (module.getTitle().equals("Kubernetes")) {

                module.setTopics(
                    "What is Kubernetes?|Pods|Deployments|Services|Container orchestration"
                );

            } else if (module.getTitle().equals("AI & GPU Infrastructure")) {

                module.setTopics(
                    "Why AI needs GPUs|GPU computing|AI model training|AI model inference|GPU memory|AI infrastructure monitoring"
                );
            }
        }

        learningRepository.saveAll(modules);

        System.out.println("Learning topics updated successfully.");
    }
}