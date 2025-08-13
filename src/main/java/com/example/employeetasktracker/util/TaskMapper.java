package com.example.employeetasktracker.util;

import com.example.employeetasktracker.dto.TaskDTO;
import com.example.employeetasktracker.entity.Task;

public class TaskMapper {

    public static TaskDTO toDto(Task task){
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setPriority(task.getPriority());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setAssignTo(task.getAssignedTo() != null ? task.getAssignedTo().getId() : null);

        return dto;
    }

    public static Task toEntity(TaskDTO dto){
        Task task = new Task();
        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        return task;
    }

}
