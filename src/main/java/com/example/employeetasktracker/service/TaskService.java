package com.example.employeetasktracker.service;

import com.example.employeetasktracker.dto.TaskDTO;
import com.example.employeetasktracker.entity.constant.Priority;
import com.example.employeetasktracker.entity.constant.TaskStatus;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO dto);

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO updateTask(Long id, TaskDTO dto);

    void deleteTask(Long id);

    TaskDTO assignTask(Long taskId, Long employeeId);

    List<TaskDTO> getTasksForEmployee(String username);

    Page<TaskDTO> getFilteredTask(TaskStatus status, Priority priority, int page, int size, String sortBy, String sortDir);

}
