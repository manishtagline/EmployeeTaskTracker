package com.example.employeetasktracker.service.impl;

import com.example.employeetasktracker.dto.TaskDTO;
import com.example.employeetasktracker.entity.Employee;
import com.example.employeetasktracker.entity.Task;
import com.example.employeetasktracker.entity.constant.Priority;
import com.example.employeetasktracker.entity.constant.TaskStatus;
import com.example.employeetasktracker.exception.ResourceNotFoundException;
import com.example.employeetasktracker.repository.EmployeeRepository;
import com.example.employeetasktracker.repository.TaskRepository;
import com.example.employeetasktracker.service.TaskService;
import com.example.employeetasktracker.util.TaskMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepo;

    private final EmployeeRepository empRepo;

    @Override
    public TaskDTO createTask(TaskDTO dto) {
        log.info("Requesting for creating task: {}", dto.getTitle());
        Task task = TaskMapper.toEntity(dto);

        Task saveTask = taskRepo.save(task);
        log.info("Task create of id: {}", task.getId());
        return TaskMapper.toDto(saveTask);
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        log.info("Request for fetching all tasks");
        List<TaskDTO> list = taskRepo.findAll()
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());
        log.info("Task founded of size: {}", list.size());
        return list;
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        log.info("Request for fetching the Task of id: {}", id);
        Task task = taskRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id "+ id));
        return TaskMapper.toDto(task);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO dto) {
        log.info("Request for updating the task with id: {}", id);

        Task task = taskRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Task not found with id: {}", id);
                    return new ResourceNotFoundException("Task not found with id "+id);
                });

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        Task updatedTask = taskRepo.save(task);

        log.info("Task updated successfully with id: {}", id);
        return TaskMapper.toDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        log.info("Deleting request for task of id: {}", id);
        if(!taskRepo.existsById(id)){
            log.error("Task not found of id: {}", id);
            throw new ResourceNotFoundException("Task not found of id :"+id);
        }

        taskRepo.deleteById(id);

        log.info("Task deleted successfully of id: {}", id);
    }

    @Override
    public TaskDTO assignTask(Long taskId, Long employeeId) {
        log.info("Request of assigning task id: {} to employee id: {}", taskId, employeeId);

        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> {
                    log.error("Task not found of id: {}", taskId);
                    return new ResourceNotFoundException("Task not found of id "+taskId);
                });

        Employee emp = empRepo.findById(employeeId)
                .orElseThrow(() -> {
                    log.error("Employee not found of id: {}", employeeId);
                    return new ResourceNotFoundException("Employee not found of id "+employeeId);
                });

        task.setAssignedTo(emp);
        Task assignTask = taskRepo.save(task);

        log.info("Task ID: {} successfully assign to employee ID: {}", taskId, employeeId);
        return TaskMapper.toDto(assignTask);
    }

    @Override
    public List<TaskDTO> getTasksForEmployee(String username) {
        log.info("Fetching all tasks for employee username: {}", username);
        List<TaskDTO> listOfTasks = taskRepo.findByAssignedTo_User_Username(username)
                .stream()
                .map(TaskMapper::toDto)
                .collect(Collectors.toList());

        log.info("Found {} tasks for employee: {}", listOfTasks.size(), username);
        return listOfTasks;
    }

    @Override
    public Page<TaskDTO> getFilteredTask(TaskStatus status, Priority priority, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Task> tasks;

        if(status != null && priority != null){
            tasks = taskRepo.findByStatusAndPriority(status, priority, pageable);
        } else if(status != null){
           tasks = taskRepo.findByStatus(status, pageable);
        }else if(priority != null){
            tasks = taskRepo.findByPriority(priority, pageable);
        }else{
            tasks = taskRepo.findAll(pageable);
        }

        return tasks.map(TaskMapper::toDto);
    }
}
