package com.example.employeetasktracker.controller;

import com.example.employeetasktracker.dto.TaskDTO;
import com.example.employeetasktracker.service.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
@Tag(name = "Task Management", description = "Manage tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto){
        log.info("POST /task - api calling for creating a task");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskDTO>> getAllTask(){
        log.info("GET /task - api calling for getting all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
        log.info("GET /task/{} - api calling for getting specific task", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO dto){
        log.info("PUT /task/{} - api calling for updating task", id);
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletingTask(@PathVariable Long id){
        log.info("DELETE /task/{} - api calling for deleting task", id);
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully of id: "+id);
    }

    @PatchMapping("/{taskId}/assign/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> assignTaskToEmployee(@PathVariable Long taskId, @PathVariable Long employeeId){
        log.info("PATCH /task/{}/assign/{} - api calling for assign task to employee", taskId, employeeId);
        return ResponseEntity.ok(taskService.assignTask(taskId, employeeId));
    }

    @GetMapping("/employee/{username}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TaskDTO>> getTasksForEmployee(@PathVariable String username){
        log.info("GET /task/employee/{} - api calling for getting specific employee task", username);
        return ResponseEntity.ok(taskService.getTasksForEmployee(username));
    }

}
