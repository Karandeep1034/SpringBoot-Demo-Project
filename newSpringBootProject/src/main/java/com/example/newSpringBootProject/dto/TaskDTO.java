package com.example.newSpringBootProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String taskId;
    @NotNull
    private String description;
    private int severity;
    @NotNull
    private String assignee;
    @NotNull
    @Min(1)
    @Max(5)
    private int storyPoint;
}
