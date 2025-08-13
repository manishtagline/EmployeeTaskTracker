package com.example.employeetasktracker.service.impl;

import com.example.employeetasktracker.dto.EmployeeDTO;
import com.example.employeetasktracker.entity.Employee;
import com.example.employeetasktracker.exception.ResourceNotFoundException;
import com.example.employeetasktracker.repository.EmployeeRepository;
import com.example.employeetasktracker.service.EmployeeService;
import com.example.employeetasktracker.util.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository empRepo;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.info("Fetching all employees");
        return empRepo.findAll().stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        log.info("Fetching employee with id: {}", id);
        Employee emp = empRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: "+ id));
        return EmployeeMapper.toDto(emp);
    }

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        return null;
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        log.info("Updating employee with id: {}", id);
        Employee emp = empRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id: {}", id);
                    return   new ResourceNotFoundException("Employee not found with id: "+id);
                });

        emp.setFirstName(employeeDTO.getFirstName());
        emp.setLastName(employeeDTO.getLastName());
        emp.setEmail(employeeDTO.getEmail());
        emp.setDepartment(employeeDTO.getDepartment());
        emp.setPosition(employeeDTO.getPosition());
        emp.setPhoneNumber(employeeDTO.getPhoneNumber());

        Employee updated = empRepo.save(emp);

        return EmployeeMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        log.info("Deleting request for employee of id: {}", id);
        if(!empRepo.existsById(id)){
            throw new ResourceNotFoundException("Employee not found with id: "+id);
        }
        empRepo.deleteById(id);

        log.info("Employee Data of id {} is successfully deleted", id);
    }
}
