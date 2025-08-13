package com.example.employeetasktracker.repository;

import com.example.employeetasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedTo_User_Username(String username);

}
