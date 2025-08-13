package com.example.employeetasktracker.dto;

import com.example.employeetasktracker.entity.constant.Priority;
import com.example.employeetasktracker.entity.constant.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Priority priority;

    private TaskStatus status;

    private LocalDateTime dueDate;

    private Long assignTo;

}
