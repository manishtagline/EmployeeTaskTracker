package com.example.employeetasktracker.service;

import com.example.employeetasktracker.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);
    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(Long id);
    TaskDTO updateTask(Long id, TaskDTO dto);
    void deleteTask(Long id);
    TaskDTO assignTask(Long taskId, Long employeeId);
    List<TaskDTO> getTasksForEmployee(String username);

}
