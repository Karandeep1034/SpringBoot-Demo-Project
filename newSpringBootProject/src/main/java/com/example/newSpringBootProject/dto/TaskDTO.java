package com.example.newSpringBootProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String taskId;
    private String description;
    private int severity;
    private String assignee;
    private int storyPoint;
}
