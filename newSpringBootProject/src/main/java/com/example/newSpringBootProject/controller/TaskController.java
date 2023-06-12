package com.example.newSpringBootProject.controller;

import com.example.newSpringBootProject.dto.TaskDTO;
import com.example.newSpringBootProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO createTask(@RequestBody @Valid TaskDTO task){
        return service.addTask(task);
    }
    @GetMapping
    public List<TaskDTO> getTask(){
        return service.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public TaskDTO getTasks(@PathVariable String taskId){
        return service.findTaskById(taskId);
    }
    @GetMapping("/severity/{severity}")
    public List<TaskDTO> findTaskBySeverity(@PathVariable int severity){
        return service.getTaskBySeverity(severity);
    }
    @GetMapping("/assignee/{assignee}")
    public List<TaskDTO> getTaskByAssignee(@PathVariable @Valid String assignee){
        return service.getTasksByAssignee(assignee);
    }

    @PutMapping
    public TaskDTO modifyTask(@RequestBody TaskDTO task){
        return service.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return service.deleteTask(taskId);
    }

}
