package com.example.employeetasktracker.controller;

import com.example.employeetasktracker.dto.EmployeeDTO;
import com.example.employeetasktracker.service.EmployeeService;
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
@RequestMapping("/employee")
@Tag(name = "Employee Management", description = "Manage employees")
public class EmployeeController {

    private final EmployeeService empService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee(){
        log.info("Get /employee api calling");
        return ResponseEntity.ok(empService.getAllEmployees());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        log.info("Get /employee/{} api calling",id);
        return ResponseEntity.ok(empService.getEmployeeById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto){
        log.info("Put /employee/{} api calling", id);
        return ResponseEntity.ok(empService.updateEmployee(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        log.info("Delete /employee/{} api calling", id);
        empService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("Employee data deleted successfully...");
    }

}
