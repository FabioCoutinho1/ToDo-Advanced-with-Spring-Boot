package com.Estudo.todo.module.task.controller;

import com.Estudo.todo.module.task.dto.RequestCreatNewTaskDto;
import com.Estudo.todo.module.task.dto.RequestUpdateTaskDto;
import com.Estudo.todo.module.task.dto.ResponseTaskDto;
import com.Estudo.todo.module.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseTaskDto>> getAllTasks() {
      
        

        return  ResponseEntity.ok(taskService.getAllTasks());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> getTasksById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseTaskDto> createTask(@Valid @RequestBody RequestCreatNewTaskDto task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTaskDto> updateTask(@PathVariable Long id, @RequestBody RequestUpdateTaskDto task){
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task delete successful ");
    }


}
