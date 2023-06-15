package com.example.newSpringBootProject.service;

import com.example.newSpringBootProject.dto.TaskDTO;
import com.example.newSpringBootProject.exception.TaskException;
import com.example.newSpringBootProject.model.Task;
import com.example.newSpringBootProject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public TaskDTO addTask(TaskDTO task){
        Task taskObj = convertToTask(task);
        taskObj.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        repository.save(taskObj);
        return convertToDto(taskObj);
    }

    public List<TaskDTO> findAllTasks(){
        List<Task> tasks = repository.findAll();
        return tasks.stream()
                .map(task -> convertToDto(task))
                .collect(Collectors.toList());
    }

    private TaskDTO convertToDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getTaskId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setAssignee(task.getAssignee());
        taskDTO.setSeverity(task.getSeverity());
        taskDTO.setStoryPoint(task.getStoryPoint());
        return taskDTO;
    }

    private Task convertToTask(TaskDTO taskdto) {
        Task task = new Task();
        task.setTaskId(taskdto.getTaskId());
        task.setDescription(taskdto.getDescription());
        task.setAssignee(taskdto.getAssignee());
        task.setSeverity(taskdto.getSeverity());
        task.setStoryPoint(taskdto.getStoryPoint());
        return task;
    }

    public TaskDTO findTaskById(String taskId){
        Task TaskObj = repository.findById(taskId)
                .orElseThrow(() -> new TaskException("task not found"));
        return convertToDto(TaskObj);
    }

    public List<TaskDTO> getTaskBySeverity(int severity){
        List<Task> tasks = repository.findBySeverity(severity);
        return tasks.stream()
                .map(t -> convertToDto(t))
                .collect(Collectors.toList());
    }

    public List<TaskDTO> getTasksByAssignee(String assignee){
        List<Task> tasks = repository.getTasksByAssignee(assignee);
        return tasks.stream()
                .map(t -> convertToDto(t))
                .collect(Collectors.toList());
    }

    public TaskDTO updateTask(TaskDTO taskRequest){
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        repository.save(existingTask);
        return convertToDto(existingTask);
    }
    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId+"task deleted from repository";
    }
}
