package com.example.employeetasktracker.repository;

import com.example.employeetasktracker.entity.Task;
import com.example.employeetasktracker.entity.constant.Priority;
import com.example.employeetasktracker.entity.constant.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByStatus(TaskStatus status, Pageable pageable);

    Page<Task> findByPriority(Priority priority, Pageable pageable);

    Page<Task> findByStatusAndPriority(TaskStatus status, Priority priority, Pageable pageable);

    List<Task> findByAssignedTo_User_Username(String username);

}
