package com.anurag.aiinfra.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "learning_modules")
public class LearningModule {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String title;
private String level;
private String icon;
private String description;
private String what;
private String exampleText;
private String problem;
private String solution;
private String working;
private String takeaway;
private String topics;

public LearningModule() {
}

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

public String getLevel() {
    return level;
}

public void setLevel(String level) {
    this.level = level;
}

public String getIcon() {
    return icon;
}

public void setIcon(String icon) {
    this.icon = icon;
}

public String getDescription() {
    return description;
}

public void setDescription(String description) {
    this.description = description;
}

public String getWhat() {
    return what;
}

public void setWhat(String what) {
    this.what = what;
}

public String getExampleText() {
    return exampleText;
}

public void setExampleText(String exampleText) {
    this.exampleText = exampleText;
}

public String getProblem() {
    return problem;
}

public void setProblem(String problem) {
    this.problem = problem;
}

public String getSolution() {
    return solution;
}

public void setSolution(String solution) {
    this.solution = solution;
}

public String getWorking() {
    return working;
}

public void setWorking(String working) {
    this.working = working;
}

public String getTakeaway() {
    return takeaway;
}

public void setTakeaway(String takeaway) {
    this.takeaway = takeaway;
}

public String getTopics() {
    return topics;
}

public void setTopics(String topics) {
    this.topics = topics;
}


}
