package com.example.newSpringBootProject.controller;

import com.example.newSpringBootProject.model.Task;
import com.example.newSpringBootProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        return service.addTask(task);
    }
    @GetMapping
    public List<Task> getTask(){
        return service.findAllTasks();
    }

    @GetMapping("/{taskId}")
    public Task getTasks(@PathVariable String taskId){
        return service.findTaskById(taskId);
    }
    @GetMapping("/severity/{severity}")
    public List<Task> findTaskBySeverity(@PathVariable int severity){
        return service.getTaskBySeverity(severity);
    }
    @GetMapping("/assignee/{assignee}")
    public List<Task> getTaskByAssignee(@PathVariable String assignee){
        return service.getTasksByAssignee(assignee);
    }

    @PutMapping
    public Task modifyTask(@RequestBody Task task){
        return service.updateTask(task);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable String taskId){
        return service.deleteTask(taskId);
    }

}
